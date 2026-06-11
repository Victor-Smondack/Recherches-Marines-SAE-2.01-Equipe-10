package src.ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.Controleur;

public class PanelTable extends JPanel
{
    // Attributs
    private int        longueur;
    private int        largeur;
    private int        tailleCase;

    private JLabel[][] cases;
    private Controleur ctrl;

    // Constructeurs du panel
    public PanelTable(Controleur ctrl, int longueur, int largeur, int tailleCase)
    {
        this.longueur   = longueur;
        this.largeur    = largeur;
        this.tailleCase = tailleCase;
        this.ctrl       = ctrl;

        this.setLayout( new GridLayout( this.longueur, this.largeur ) );

        this.cases = new JLabel[this.longueur][this.largeur];

        GereSouris souris = new GereSouris();

        for ( int i = 0; i < this.longueur; i++ )
        {
            for ( int j = 0; j < this.largeur; j++ )
            {
                this.cases[i][j] = new JLabel();
                this.cases[i][j].setSize( tailleCase, tailleCase );
                this.cases[i][j].setOpaque( true );

                this.cases[i][j].setBorder( BorderFactory.createLineBorder( Color.LIGHT_GRAY ) );

                this.add( this.cases[i][j] );
                this.cases[i][j].addMouseListener( souris );
                this.cases[i][j].addMouseMotionListener( souris );
            }
        }
    }


    @Override
    // Méthode pour dessiner les liaisons
    protected void paintComponent( Graphics g )
    {
        super.paintChildren( g );

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor( Color.GRAY );
        g2.setStroke( new BasicStroke( 3 ) );

        int[][] liaisons = this.ctrl.getCoordonneesLiaisons();

        if ( liaisons != null )
        {
            for ( int[] l : liaisons )
            {
                int x1 = l[0];
                int y1 = l[1];
                int x2 = l[2];
                int y2 = l[3];

                if ( x1 >= 0 && x1 < longueur && y1 >= 0 && y1 < largeur && x2 >= 0 && x2 < longueur && y2 >= 0 && y2 < largeur )
                {
                    JLabel lbl1   = this.cases[x1][y1];
                    JLabel lbl2   = this.cases[x2][y2];

                    int    startX = lbl1.getX() + lbl1.getWidth() / 2;
                    int    startY = lbl1.getY() + lbl1.getHeight() / 2;
                    int    endX   = lbl2.getX() + lbl2.getWidth() / 2;
                    int    endY   = lbl2.getY() + lbl2.getHeight() / 2;

                    g2.drawLine( startX, startY, endX, endY );
                }
            }
        }
    }

    // Méthode privée qui détecte le clique de la souris sur la panel et fait
    // l'action en fonction de ce qui est selectionné

    private class GereSouris extends MouseAdapter
    {
        @Override
        public void mousePressed( MouseEvent evt )
        {
            Component composantClique = (Component) evt.getSource();

            if ( composantClique instanceof JLabel lblClique )
            {
                if ( tailleCase < 2 )
                {
                    tailleCase = PanelTable.this.getWidth() + 1;
                }

                if ( PanelTable.this.ctrl.isZoneSelect() )
                {
                    int zoneActive = PanelTable.this.ctrl.getZoneActive();

                    for ( int i = 0; i < PanelTable.this.longueur; i++ )
                    {
                        for ( int j = 0; j < PanelTable.this.largeur; j++ )
                        {
                            if ( lblClique == PanelTable.this.cases[i][j] )
                            {
                                if ( PanelTable.this.ctrl.positionneZone( i, j, zoneActive ) )
                                {
                                    lblClique.setBackground( PanelTable.this.ctrl.getCouleur( zoneActive ) );
                                }
                                break;
                            }
                        }
                    }
                } else
                {
                    if ( PanelTable.this.ctrl.isLaboSelect() )
                    {
                        int laboActive = PanelTable.this.ctrl.getLaboActive();

                        for ( int i = 0; i < PanelTable.this.longueur; i++ )
                        {
                            for ( int j = 0; j < PanelTable.this.largeur; j++ )
                            {
                                if ( lblClique == PanelTable.this.cases[i][j] )
                                {
                                    if ( PanelTable.this.ctrl.getGrillePoisson()[i][j] != null )
                                    {
                                        int[] anciennesCoords = PanelTable.this.ctrl.positionneLabo( i, j, laboActive );

                                        if ( anciennesCoords != null )
                                        {
                                            int oldX = anciennesCoords[0];
                                            int oldY = anciennesCoords[1];
                                            PanelTable.this.cases[oldX][oldY].setBorder( BorderFactory.createLineBorder( Color.LIGHT_GRAY ) );
                                        }

                                        Color colLabo = PanelTable.this.ctrl.getCouleur( 9 + laboActive );
                                        lblClique.setBorder( BorderFactory.createLineBorder( colLabo, 4 ) );
                                    }
                                    break;
                                }
                            }
                        }
                    } else
                    {
                        if ( PanelTable.this.ctrl.getGommeSelect() )
                        {
                            for ( int i = 0; i < PanelTable.this.longueur; i++ )
                            {
                                for ( int j = 0; j < PanelTable.this.largeur; j++ )
                                {
                                    if ( lblClique == PanelTable.this.cases[i][j] )
                                    {
                                        lblClique.setIcon( null );
                                        lblClique.setBackground( null );
                                        lblClique.setBorder( BorderFactory.createLineBorder( Color.LIGHT_GRAY ) );

                                        PanelTable.this.ctrl.gommer( i, j );
                                        break;
                                    }
                                }
                            }
                        } else
                        {
                            String poissonSelected = "";
                            try
                            {
                                poissonSelected = PanelTable.this.ctrl.getPoissonSelect();
                            } catch (Exception e)
                            {
                            }

                            if ( poissonSelected != null && !poissonSelected.equals( "" ) )
                            {
                                for ( int i = 0; i < PanelTable.this.longueur; i++ )
                                {
                                    for ( int j = 0; j < PanelTable.this.largeur; j++ )
                                    {
                                        if ( lblClique == PanelTable.this.cases[i][j] )
                                        {
                                            lblClique.setIcon( new ImageIcon( new ImageIcon( "../images/poissons/" + poissonSelected + ".png" ).getImage()
                                                .getScaledInstance( tailleCase / 2, tailleCase / 2, Image.SCALE_SMOOTH ) ) );
                                            lblClique.setHorizontalAlignment( SwingConstants.CENTER );
                                            PanelTable.this.ctrl.positionnePoisson( i, j, poissonSelected );
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                PanelTable.this.ctrl.genererLiaisons();
                PanelTable.this.repaint();
            }
        }


        @Override
        public void mouseDragged( MouseEvent evt )
        {
            Component composantClique = (Component) evt.getSource();

            if ( PanelTable.this.ctrl.isZoneSelect() )
            {
                int    zoneActive = PanelTable.this.ctrl.getZoneActive();

                JLabel lblDepart  = (JLabel) evt.getSource();

                int    sourisX    = lblDepart.getX() + evt.getX();
                int    sourisY    = lblDepart.getY() + evt.getY();

                int    j          = sourisX / tailleCase;
                int    i          = sourisY / tailleCase;

                if ( i >= 0 && i < PanelTable.this.longueur && j >= 0 && j < PanelTable.this.largeur )
                {
                    if ( PanelTable.this.ctrl.positionneZone( i, j, zoneActive ) )
                    {
                        PanelTable.this.cases[i][j].setBackground( PanelTable.this.ctrl.getCouleur( zoneActive ) );
                    }
                }
            }

            PanelTable.this.ctrl.genererLiaisons();
            PanelTable.this.repaint();
        }
    }
}

