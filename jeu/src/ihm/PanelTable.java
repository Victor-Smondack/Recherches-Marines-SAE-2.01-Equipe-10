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
import src.metier.Carte;
import src.metier.Poisson;

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

        this.imgFond    = new ImageIcon( "../images/Background.png" ).getImage();

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
                this.cases[i][j].setBackground( this.ctrl.getCouleur( this.ctrl.getZoneIndice( j, i ) ) );
                this.cases[i][j].setIcon( new ImageIcon( new ImageIcon( "../images/poissons/" + this.ctrl.getPoissonIndice( j, i ) + ".png" ).getImage()
                    .getScaledInstance( (int) (tailleCase * 0.7), (int) (tailleCase * 0.7), Image.SCALE_SMOOTH ) ) );
                this.cases[i][j].setHorizontalAlignment( SwingConstants.CENTER );

                int numLabo = this.ctrl.getLaboIndice( j, i );
                if ( numLabo != -1 )
                {
                    this.cases[i][j].setBorder( BorderFactory.createLineBorder( this.ctrl.getCouleur( 9 + numLabo ), 4 ) );
                }

                this.add( this.cases[i][j] );
                this.cases[i][j].addMouseListener( souris );
            }
        }

        PanelTable.this.repaint();
        this.setVisible( true );
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

        g2.setColor( Color.BLACK );
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

                if ( x1 >= 0 && x1 < largeur && y1 >= 0 && y1 < longueur && x2 >= 0 && x2 < largeur && y2 >= 0 && y2 < longueur )
                {
                    JLabel lbl1   = this.cases[y1][x1];
                    JLabel lbl2   = this.cases[y2][x2];

                    int    startX = lbl1.getX() + lbl1.getWidth() / 2;
                    int    startY = lbl1.getY() + lbl1.getHeight() / 2;
                    int    endX   = lbl2.getX() + lbl2.getWidth() / 2;
                    int    endY   = lbl2.getY() + lbl2.getHeight() / 2;

                    g2.drawLine( startX, startY, endX, endY );
                }
            }
        }
    }


    private class GereSouris extends MouseAdapter
    {
        @Override
        public void mousePressed( MouseEvent evt )
        {
            Component composantClique = (Component) evt.getSource();
            if ( composantClique instanceof JLabel lblClique )
            {
                Carte carteAffichee = PanelTable.this.ctrl.getCarteVisible();

                if ( carteAffichee == null )
                    return;

                for ( int i = 0; i < PanelTable.this.cases.length; i++ )
                {
                    for ( int j = 0; j < PanelTable.this.cases[i].length; j++ )
                    {
                        if ( PanelTable.this.cases[i][j] == lblClique )
                        {
                            Poisson poissonClique = PanelTable.this.ctrl.getPoissonObjet( j, i );
                            String  nomCarte      = carteAffichee.getNom();

                            if ( poissonClique != null )
                            {
                                boolean correspondCarte   = !PanelTable.this.ctrl.estUnLaboActif() || poissonClique.getEspece().equals( nomCarte )
                                    || nomCarte.equals( "Joker" );
                                boolean estBonneExtremite = PanelTable.this.ctrl.verifierClicValide( j, i );

                                if ( correspondCarte && estBonneExtremite )
                                {
                                    PanelTable.this.ctrl.validerEtAvancerEtude( j, i );
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
