package src;

import java.awt.Color;
import java.util.List;

import src.ihm.FrameDebut;
import src.ihm.FrameTable;
import src.ihm.FrameTirage;
import src.metier.Carte;
import src.metier.Couleur;
import src.metier.Liaison;
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
    private Poisson     selectedExtremity = null;
    private boolean     aJoueCeTour    = false;
    private int         nbCartesNoires = 0;

    // Constructeur du contrôleur qui initialise le jeu et l'IHM
    public Controleur()
    {
        this.metier     = new Plateau( this.xGrille, this.yGrille );
        this.frameDebut = new FrameDebut( this );

    }


    // Initialise les dimensions de la grille du plateau
    public void initialiserGrille( int longueur, int largeur, int nbSymbole, int tailleCase )
    {
        this.yGrille = longueur;
        this.xGrille = largeur;

        this.metier  = new Plateau( this.xGrille, this.yGrille );
    }


    // Récupère la grille des zones du plateau
    public Zone[][] getGrilleZone()
    {
        return this.metier.getGrilleZone();
    }


    // Vérifie si une zone avec ce numéro existe
    public boolean zoneExiste( int numZone )
    {
        return this.metier.zoneExiste( numZone );
    }


    // Récupère la liste des espèces de poissons disponibles
    public String[] getPoissons()
    {
        return this.metier.getEspeces();
    }


    // Récupère le tableau des espèces de poissons
    public String[] getEspeces()
    {
        return this.metier.getEspeces();
    }


    // Récupère la grille contenant les poissons
    public Poisson[][] getGrillePoisson()
    {
        return this.metier.getGrillePoisson();
    }


    // Demande la génération des liaisons entre les poissons
    public void genererLiaisons()
    {
        this.metier.genererLiaisons();
    }


    // Récupère un tableau des coordonnées de toutes les liaisons
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


    // Convertit un code couleur en un objet Color avec transparence
    public Color getCouleur( int codeCouleur )
    {
        Couleur c = Couleur.valueOf( codeCouleur );
        if ( c != null )
        {
            return new Color( c.getR(), c.getV(), c.getB(), 128 );
        }

        return new Color( 180, 230, 255, 128 );
    }


    // Récupère proprement le nom de la couleur du laboratoire pour la manche actuelle
    public String getCouleurLabo( int numManche )
    {
        int indexLabo = 0;

        for ( Couleur c : Couleur.values() )
        {
            if ( c.name().startsWith( "LABO_" ) )
            {
                indexLabo++;
                if ( indexLabo == numManche )
                {
                    return c.getLibelle().replace( "Laboratoire ", "" );
                }
            }
        }

        return "";
    }


    // Récupère l'espèce du poisson aux coordonnées demandées
    public String getPoissonIndice( int indiceX, int indiceY )
    {
        return this.metier.getPoissonIndice( indiceX, indiceY );
    }


    // Récupère le numéro de zone aux coordonnées demandées
    public int getZoneIndice( int indiceX, int indiceY )
    {
        return this.metier.getZoneIndice( indiceX, indiceY );
    }


    // Récupère le numéro de laboratoire aux coordonnées demandées
    public int getLaboIndice( int indiceX, int indiceY )
    {
        return this.metier.getLaboIndice( indiceX, indiceY );
    }


    // Pioche une carte et incrémente le compteur si elle est noire
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
        this.selectedExtremity = null;

        if ( this.carteVisible != null )
        {
            String desc = this.carteVisible.getDescription().toLowerCase();
            String img  = this.carteVisible.getImagePath().toLowerCase();

            if ( desc.contains( "noir" ) || img.contains( "noir" ) )
            {
                this.nbCartesNoires++;
            }
        }

        this.majIHM();
        return this.carteVisible;
    }


    // Récupère la carte actuellement visible
    public Carte getCarteVisible()
    {
        return this.carteVisible;
    }


    // Modifie la carte actuellement visible
    public void setCarteVisible( Carte carte )
    {
        this.carteVisible = carte;
    }


    // Vérifie si la pioche est vide
    public boolean estVide()
    {
        return this.pioche.estVide();
    }


    // Mélange le paquet de cartes de la pioche
    public void melangerPioche()
    {
        this.pioche.melanger();
    }


    // Réinitialise la pioche et les compteurs pour une nouvelle manche
    public void resetPioche()
    {
        this.metier.finirManche();
        this.pioche.reset();
        this.carteVisible   = null;
        this.aJoueCeTour    = false;
        this.nbCartesNoires = 0;
        this.selectedExtremity = null;
        this.majIHM();
    }


    // Récupère le nombre de cartes restantes dans la pioche
    public int getNbCartesRestantes()
    {
        return this.pioche.getNbCartesRestantes();
    }


    // Récupère la carte située sur le dessus de la pioche
    public Carte carteActuelle()
    {
        return this.pioche.carteActuelle();
    }


    // Récupère l'objet Poisson aux coordonnées spécifiées
    public Poisson getPoissonObjet( int x, int y )
    {
        return this.metier.getPoisson( x, y );
    }


    // Vérifie si le clic sur la case est valide selon les règles de jeu
    public boolean verifierClicValide( int x, int y )
    {
        if ( this.carteVisible == null || (this.metier.estUnLaboActif() && this.aJoueCeTour) )
        {
            return false;
        }
        Poisson p = this.getPoissonObjet( x, y );
        return this.metier.estPoissonValidePourLabo( p );
    }


    // Valide le déplacement et fait avancer l'étude sur le poisson choisi
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


    // Indique si une étude en laboratoire est actuellement en cours
    public boolean estUnLaboActif()
    {
        return this.metier.estUnLaboActif();
    }


    // Récupère le nom du dossier choisi
    public void lireDonnees( String nomDossier )
    {
        this.metier.lireDonnees( nomDossier );
    }


    // Récupère l'identifiant du laboratoire associé à une liaison précise
    public int getLaboDeLiaison( int x1, int y1, int x2, int y2 )
    {
        return this.metier.getLaboDeLiaison( x1, y1, x2, y2 );
    }


    // Récupère le nombre de cartes noires piochées dans la manche
    public int getNbCartesNoires()
    {
        return this.nbCartesNoires;
    }


    // Récupère le score global actuel du joueur depuis le métier
    public int getPointsTotal()
    {
        return this.metier.getPointsTotal();
    }

<<<<<<< HEAD

    // Force la fin de la manche actuelle (essentiel pour valider les points du dernier labo)
=======
    public Poisson getSelectedExtremity()
    {
        return this.selectedExtremity;
    }

    public void setSelectedExtremity( Poisson p )
    {
        this.selectedExtremity = p;
    }

    public boolean aJoueCeTour()
    {
        return this.aJoueCeTour;
    }

    public Poisson getExtremite1()
    {
        return this.metier.getExtremite1();
    }

    public Poisson getExtremite2()
    {
        return this.metier.getExtremite2();
    }

    public boolean verifierDeplacement( int xExt, int yExt, int xDest, int yDest )
    {
        Poisson ext = this.getPoissonObjet( xExt, yExt );
        Poisson dest = this.getPoissonObjet( xDest, yDest );
        return this.metier.verifierDeplacement( ext, dest );
    }

    public void validerEtAvancerEtudeAvecExtremite( int xExt, int yExt, int xDest, int yDest )
    {
        Poisson ext = this.getPoissonObjet( xExt, yExt );
        Poisson dest = this.getPoissonObjet( xDest, yDest );
        if ( ext != null && dest != null )
        {
            String message = this.metier.validerEtAvancerEtudeAvecExtremite( ext, dest );
            System.out.println( message );
            this.aJoueCeTour = true;
        }
    }

// Force la fin de la manche actuelle (essentiel pour valider les points du dernier labo)
>>>>>>> f671d78 (Changement selection poisson)
    public void finirManche()
    {
        this.metier.finirManche();
    }

<<<<<<< HEAD
=======
    public void majIHM()
    {
        if ( this.frameTable != null )
        {
            this.frameTable.repaint();
        }
    }
>>>>>>> f671d78 (Changement selection poisson)

    public void lancerJeu()
    {

        this.metier.restaurerLabos();
        this.metier.genererLiaisons();

        this.pioche       = new Pioche( this.metier.getNbSymbole() );
        this.carteVisible = null;

        this.frameTirage  = new FrameTirage( this );
        this.frameTable   = new FrameTable( this, this.metier.getLongueur(), this.metier.getLargeur(), this.metier.getTailleCase() );
        this.frameDebut.setVisible(false);
    }


    // Méthode principale qui lance l'application
    public static void main( String[] args )
    {
        new Controleur();
    }
}
