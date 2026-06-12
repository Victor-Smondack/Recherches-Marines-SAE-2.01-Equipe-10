package src;

import java.awt.Color;
import java.util.List;

import src.ihm.FrameTable;
import src.ihm.FrameTirage;
import src.ihm.FrameDebut;
import src.metier.Carte;
import src.metier.Couleur;
import src.metier.Liaison;
import src.metier.LireDonnees;
import src.metier.Pioche;
import src.metier.Plateau;
import src.metier.Poisson;
import src.metier.Zone;

public class Controleur
{
    private int         xGrille        = 1;
    private int         yGrille        = 1;
    private FrameTirage frameTirage;
    private FrameTable  frameTable;
    private FrameDebut  frameDebut;
    private Plateau     metier;
    private Pioche      pioche;
    private Carte       carteVisible;
    private boolean     aJoueCeTour    = false;
    private int         nbCartesNoires = 0;


    public Controleur()
    {
        this.frameDebut = new FrameDebut( this );
        this.metier = new Plateau( this.xGrille, this.yGrille );
        LireDonnees lecteur = new LireDonnees( this.metier );

        lecteur.lireGrille();

        lecteur.lirePoissons();

        lecteur.lireLiaisons();

        lecteur.lireZones();

        lecteur.lireLabo();

        this.metier.restaurerLabos();

        this.pioche = new Pioche(this.metier.getNbSymbole());

        this.pioche       = new Pioche( this.metier.getNbSymbole() );
        this.carteVisible = null;

        this.frameTirage  = new FrameTirage( this );
        this.frameTable   = new FrameTable( this, this.metier.getLongueur(), this.metier.getLargeur(), this.metier.getTailleCase() );
    }


    public void initialiserGrille( int longueur, int largeur, int nbSymbole, int tailleCase )
    {
        this.yGrille = longueur;
        this.xGrille = largeur;

        this.metier  = new Plateau( this.xGrille, this.yGrille );
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


    public int getLaboIndice( int indiceX, int indiceY )
    {
        return this.metier.getLaboIndice( indiceX, indiceY );
    }


    public Carte piocherCarte()
    {
        if ( this.pioche.estVide() )
        {
            this.metier.finirManche();
            this.pioche.reset();
            this.carteVisible = null;
            this.aJoueCeTour  = false;
            return null;
        }
        this.carteVisible = this.pioche.piocher();
        this.aJoueCeTour  = false;

        if ( this.carteVisible != null )
        {
            String desc = this.carteVisible.getDescription().toLowerCase();
            String img  = this.carteVisible.getImagePath().toLowerCase();

            if ( desc.contains( "noir" ) || img.contains( "noir" ) )
            {
                this.nbCartesNoires++;
            }
        }

        return this.carteVisible;
    }


    public Carte getCarteVisible()
    {
        return this.carteVisible;
    }


    public void setCarteVisible( Carte carte )
    {
        this.carteVisible = carte;
    }


    public boolean estVide()
    {
        return this.pioche.estVide();
    }


    public void melangerPioche()
    {
        this.pioche.melanger();
    }


    public void resetPioche()
    {
        this.metier.finirManche();
        this.pioche.reset();
        this.carteVisible   = null;
        this.aJoueCeTour    = false;
        this.nbCartesNoires = 0;
    }


    public int getNbCartesRestantes()
    {
        return this.pioche.getNbCartesRestantes();
    }


    public Carte carteActuelle()
    {
        return this.pioche.carteActuelle();
    }


    public Poisson getPoissonObjet( int x, int y )
    {
        return this.metier.getPoisson( x, y );
    }


    public boolean verifierClicValide( int x, int y )
    {
        if ( this.carteVisible == null || (this.metier.estUnLaboActif() && this.aJoueCeTour) )
        {
            return false;
        }
        Poisson p = this.getPoissonObjet( x, y );
        return this.metier.estPoissonValidePourLabo( p );
    }


    public void validerEtAvancerEtude( int x, int y )
    {
        boolean laboDejaActif = this.metier.estUnLaboActif();
        Poisson p             = this.getPoissonObjet( x, y );
        if ( p != null )
        {
            String message = this.metier.etudePoisson( p );
            System.out.println( message );
            if ( laboDejaActif )
            {
                this.aJoueCeTour = true;
            }
        }
    }


    public boolean estUnLaboActif()
    {
        return this.metier.estUnLaboActif();
    }

    public String getDossier( String nomDossier)
    {
        return "pas grand chose pour l'instant";
    }


    public int getLaboDeLiaison( int x1, int y1, int x2, int y2 )
    {
        return this.metier.getLaboDeLiaison( x1, y1, x2, y2 );
    }


    public int getNbCartesNoires()
    {
        return this.nbCartesNoires;
    }


    public static void main( String[] args )
    {
        new Controleur();
    }
}
