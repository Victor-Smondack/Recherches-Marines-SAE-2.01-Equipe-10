package src;

import java.awt.Color;
import java.util.List;

import src.ihm.FrameTable;
import src.ihm.FrameTirage;
import src.metier.Carte;
import src.metier.Couleur;
import src.metier.Liaison;
import src.metier.LireDonnees;
import src.metier.Plateau;
import src.metier.Poisson;
import src.metier.Zone;

public class Controleur
{
    private int         xGrille = 1;
    private int         yGrille = 1;
    private FrameTirage frameTirage;
    private FrameTable  frameTable;
    private Plateau     metier;
    private Carte       carte;


    public Controleur()
    {
        this.metier = new Plateau( this.xGrille, this.yGrille );
        LireDonnees lecteur = new LireDonnees( this.metier );

        lecteur.lireGrille();

        lecteur.lirePoissons();
        lecteur.lireLiaisons();
        lecteur.lireZones();
        lecteur.lireLabo();

        this.metier.genererLiaisons();

        this.frameTirage = new FrameTirage( this );
        this.frameTable  = new FrameTable( this, 7, 7, 100 );
    }


    public void initialiserGrille( int longueur, int largeur, int nbSymbole, int tailleCase )
    {
        this.yGrille = longueur;
        this.xGrille = largeur;

        this.metier  = new Plateau( this.xGrille, this.yGrille );
        // this.frameMenu.changerPanel( nbSymbole );
    }


    /*
     * public void getNbSymbole() { this.nbSymbole = this.metier.getNbSymbole(); }
     */
    /*
     * public int getNbLabo() { return this.metier.getNbLabo(); }
     */


    /*
     * public void getImagePoisson( int i ) { this.frameMenu.getImagePoisson( i ); }
     */

    /*
     * public boolean getGommeSelect() { if ( this.panelChoix != null ) { return this.panelChoix.isGommeActive(); } return this.gommeActive; }
     */


    public Zone[][] getGrilleZone()
    {
        return this.metier.getGrilleZone();
    }


    public boolean zoneExiste( int numZone )
    {
        return this.metier.zoneExiste( numZone );
    }


    public String[] getPoissons()
    {
        return this.metier.getEspeces();
    }


    public String[] getEspeces()
    {
        return this.metier.getEspeces();
    }


    public Poisson[][] getGrillePoisson()
    {
        return this.metier.getGrillePoisson();
    }


    public void genererLiaisons()
    {
        this.metier.genererLiaisons();
    }


    public int[][] getCoordonneesLiaisons()
    {
        if ( this.metier == null || this.metier.getLstLiaisons() == null )
        {
            return null;
        }

        List<Liaison> lias   = this.metier.getLstLiaisons();
        int[][]       coords = new int[lias.size()][4];

        for ( int i = 0; i < lias.size(); i++ )
        {
            Liaison l = lias.get( i );
            coords[i][0] = l.getP1().getX();
            coords[i][1] = l.getP1().getY();
            coords[i][2] = l.getP2().getX();
            coords[i][3] = l.getP2().getY();
        }

        return coords;
    }


    public Color getCouleur( int codeCouleur )
    {
        Couleur c = Couleur.valueOf( codeCouleur );
        if ( c != null )
        {
            return new Color( c.getR(), c.getV(), c.getB(), 128 );
        }

        return new Color( 180, 230, 255, 128 );
    }


    public String getPoissonIndice( int indiceX, int indiceY )
    {
        return this.metier.getPoissonIndice( indiceX, indiceY );
    }


    public int getZoneIndice( int indiceX, int indiceY )
    {
        return this.metier.getZoneIndice( indiceX, indiceY );
    }


    public String piocherCarte()
    {
        return this.carte.piocherCarte();
    }


    public void melangerPioche()
    {
        this.carte.melangerPioche();
    }


    public void resetPioche()
    {
        this.carte.resetPioche();
    }


    public int getNbCartesRestantes()
    {
        return this.carte.getNbCartesRestantes();
    }


    public static void main( String[] args )
    {
        new Controleur();
    }

}
