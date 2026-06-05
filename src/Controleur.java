package src;

import java.awt.Color;

import ihm.FrameTable;
import metier.*;
import src.ihm.FrameMenu;
import src.ihm.PanelChoix;
import src.metier.Couleur;

public class Controleur
{
    // --- ATTRIBUTS IHM ---
    private FrameMenu  frameMenu;
    private FrameTable frameTable;
    private PanelChoix panelChoix;

    // --- ATTRIBUTS METIER (ET BROUILLONS) ---
    // private FrameMenu ihm;
    /* private Metier metier; */

    private int        yGrille = 7;
    private int        xGrille = 7;
    private Plateau    plateau;


    // --- CONSTRUCTEUR FUSIONNÉ ---
    public Controleur()
    {
        // Initialisation IHM
        this.frameMenu = new FrameMenu( this );

        // Initialisation Métier
        /* this.metier = new Metier (); */
        // this.ihm = new FrameMenu(this);
        this.plateau = new Tableau();
    }


    // --- MÉTHODES IHM ---
    public void initialiserGrille( int longueur, int largeur, int nbSymbole, int tailleCase )
    {
        this.frameTable = new FrameTable( this, longueur, largeur, tailleCase );
        this.frameMenu.changerPanel( nbSymbole );
    }

    /*
     * public void getImagePoisson( int i ) { this.panelChoix.getImagePoisson(
     * int i); }
     */


    public Color getCouleur( int codeCouleur )
    {
        Couleur c = Couleur.valueOf( codeCouleur );
        if ( c != null )
        {
            return new Color( c.getR(), c.getV(), c.getB(), 128 );
        }

        return new Color( 180, 230, 255, 128 );
    }


    // --- MÉTHODES METIER (ACTIVES ET COMMENTÉES) ---
    /*
     * public String getPoissonSelect() // Envoyer vers l'IHM le poisson
     * sélectionné { return this.metier.getPoissonSelect(); }
     * 
     * public void setPoissonSelect(int numEspece) // Envoyer vers le métier le
     * poisson sélectionné { this.metier.setPoissonSelect(numEspece); }
     * 
     * public String[] getEspeces() { return this.metier.getEspeces(); }
     * 
     * public boolean deplacement(int xDest, int yDest) { if ((xDest >= 0) &&
     * (xDest < this.xGrille) && (yDest >= 0) && (yDest < this.yGrille)) {
     * deplacer(xDest, yDest); return true; } else { return false; } }
     * 
     * private void deplacer(int xDest, int yDest) {
     * 
     * }
     */


    public void genererLiaisons()
    {
        this.plateau.genererLiaisons();
    }


    // --- MAIN ---
    public static void main( String[] args )
    {
        new Controleur();
    }
}
