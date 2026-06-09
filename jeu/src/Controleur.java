package src;

import java.awt.Color;

import src.ihm.FrameTable;
import src.metier.Couleur;
import src.metier.Plateau;
import src.metier.Poisson;
import src.metier.Zone;

public class Controleur
{
<<<<<<< HEAD
    private int        xGrille     = 1;
    private int        yGrille     = 1;
    private int        zoneActive  = 1;
    private int        laboActive  = 1;
    private boolean    gommeActive = false;
    private FrameMenu  frameMenu;
    private FrameTable frameTable;
    private Plateau    metier;
    private PanelChoix panelChoix;
    private Carte      carte;
=======
    private int         xGrille = 1;
    private int         yGrille = 1;
    private FrameTable  frameTable;
    private Plateau     metier;
    private FrameTirage frameTirage;
>>>>>>> a837ff73ed67bfdc514c6eea823168f3ed632cd5


    public Controleur()
    {
        this.frameTable = new FrameTable( this, 7, 6, 100 );
        this.metier     = new Plateau( 7, 6 );
    }


    public void initialiserGrille( int longueur, int largeur, int nbSymbole, int tailleCase )
    {
        this.yGrille    = longueur;
        this.xGrille    = largeur;

        this.frameTable = new FrameTable( this, longueur, largeur, tailleCase );
    }


    public void setZoneSelect( boolean select )
    {
        this.metier.setZoneSelect( select );
    }


    public boolean isZoneSelect()
    {
        return this.metier.isZoneSelect();
    }


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


    public String getPoissonSelect()
    {
        return this.metier.getPoissonSelect();
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


    public Color getCouleur( int codeCouleur )
    {
        Couleur c = Couleur.valueOf( codeCouleur );
        if ( c != null )
        {
            return new Color( c.getR(), c.getV(), c.getB(), 128 );
        }

        return new Color( 180, 230, 255, 128 );
    }


<<<<<<< HEAD
    public void Sauvergarder()
    {
        this.metier.Sauvegarder();
    }

    public void piocherCarte()
    {
        this.carte.piocherCarte();
    }

    public void melangerPioche()
    {
        this.carte.melangerPioche();
    }

    public void resetPioche()
    {
        this.carte.resetPioche();
    }


=======
>>>>>>> a837ff73ed67bfdc514c6eea823168f3ed632cd5
    public static void main( String[] args )
    {
        new Controleur();
    }


    
}
