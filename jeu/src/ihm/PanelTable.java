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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.Controleur;

public class PanelTable extends JPanel
{
    private int        longueur;
    private int        largeur;
    private int        tailleCase;
    private Graphics2D g2;
    private Image      imgFond;
    private JLabel[][] cases;
    private Controleur ctrl;

    public PanelTable(Controleur ctrl, int longueur, int largeur, int tailleCase)
    {
        this.longueur   = longueur;
        this.largeur    = largeur;
        this.tailleCase = tailleCase;
        this.ctrl       = ctrl;

        this.imgFond    = new ImageIcon( "../../images/Background.png" ).getImage();

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
                this.cases[i][j].setBackground( this.ctrl.getCouleur( i + 1 ) /*
                                                                               * this . getCouleur ( this . getZone ( i, j ) )
                                                                               */ );
                this.cases[i][j]
                    .setIcon( new ImageIcon( new ImageIcon( "../../images/poissons/Thon.png" /*
                                                                                              * this . ctrl . getImagePoisson ( This . getPoisson ( i , j ) )
                                                                                              */ ).getImage().getScaledInstance( tailleCase / 2, tailleCase / 2,
                        Image.SCALE_SMOOTH ) ) );
                this.cases[i][j].setHorizontalAlignment( SwingConstants.CENTER );
                // this.cases[i][j].setBorder( BorderFactory.createLineBorder(
                // this.getColor() ) );


                this.add( this.cases[i][j] );
                this.cases[i][j].addMouseListener( souris );

            }
        }

        PanelTable.this.repaint();

        this.setVisible( true );
    }


    @Override
    protected void paintChildren( Graphics g )
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


    @Override
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );

        g2 = (Graphics2D) g;

        if ( imgFond != null )
        {
            g2.drawImage( imgFond, 0, 0, this.getWidth(), this.getHeight(), this );
        }
    }

    // Méthode privée qui détecte le clique de la souris sur la panel et fait //
    // l'action en
    // fonction de
    // ce qui
    // est selectionné

    private class GereSouris extends MouseAdapter
    {
        @Override
        public void mousePressed( MouseEvent evt )
        {
            Component composantClique = (Component) evt.getSource();
            if ( composantClique instanceof JLabel lblClique )
            {

                for ( int i = 0; i < PanelTable.this.cases.length; i++ )
                {
                    for ( int j = 0; j < PanelTable.this.cases[i].length; j++ )
                        if ( PanelTable.this.cases[i][j] == lblClique )
                        {
                            PanelTable.this.ctrl.getPoissonSelect( i, j );
                        }
                }


            }
        }
    }
}

