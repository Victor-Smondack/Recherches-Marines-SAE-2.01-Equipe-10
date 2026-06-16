package src.metier;

import java.util.ArrayList;
import java.util.List;

public class Plateau
{
    /**********************/
    /* Attributs */
    /**********************/

    private Poisson[][]     grillePoisson;
    private ProgressionLabo progressionLabo;

    private int             idLaboActif      = -1;
    private List<Liaison>   lstLiaisons;
    private List<Liaison>   liaisonsVisitees = new ArrayList<>();
    private List<Integer>   labosDesLiaisons = new ArrayList<>();
    private Zone[][]        grilleZone;
    private List<Poisson>   lstPoisson;
    private char[][]        grilleLiaisons;
    private int[][]         grilleLabo;
    private Points          points;

    // Liste pour suivre les poissons étudiés uniquement pendant la manche actuelle
    private List<Poisson>   poisonsManche    = new ArrayList<>();

    private int             longueur;
    private int             largeur;
    private int             nbSymbole;
    private int             tailleCases;
    private String[]        espece           = {
        "Saumon",
        "Thon",
        "Truite",
        "Sardine",
        "Bar",
        "Colin",
        "Maquereau" };

    /**********************/
    /* Constructeur */
    /**********************/

    // Constructeur créant un plateau vide avec ses différentes grilles aux tailles voulues
    public Plateau(int longueur, int largeur)
    {
        this.grillePoisson  = new Poisson[largeur][longueur];
        this.lstPoisson     = new ArrayList<Poisson>();
        this.grilleLiaisons = new char[largeur][longueur];
        this.lstLiaisons    = new ArrayList<>();
        this.grilleZone     = new Zone[largeur][longueur];
        this.grilleLabo     = new int[largeur][longueur];
        this.points         = new Points( 1, 1 );
    }


    /**********************/
    /* Méthodes */
    /**********************/


    // Génère les liaisons entre les poissons

    public void genererLiaisons()
    {
        this.lstLiaisons.clear();

        for ( int x = 0; x < grilleLiaisons.length; x++ )
            for ( int y = 0; y < grilleLiaisons[x].length; y++ )
                grilleLiaisons[x][y] = '.';

        List<Poisson> lstPoissonLocal = new ArrayList<>();

        for ( int x = 0; x < grillePoisson.length; x++ )
            for ( int y = 0; y < grillePoisson[x].length; y++ )
                if ( grillePoisson[x][y] != null )
                    lstPoissonLocal.add( grillePoisson[x][y] );

        for ( int i = 0; i < lstPoissonLocal.size(); i++ )
        {
            for ( int j = i + 1; j < lstPoissonLocal.size(); j++ )
            {
                Poisson p1      = lstPoissonLocal.get( i );
                Poisson p2      = lstPoissonLocal.get( j );

                int     dx      = p2.getX() - p1.getX();
                int     dy      = p2.getY() - p1.getY();

                boolean alignes = dx == 0 || dy == 0 || Math.abs( dx ) == Math.abs( dy );

                if ( alignes && !existePoissonIntermediaire( p1, p2 ) )
                {
                    Liaison l = new Liaison( p1, p2 );
                    this.lstLiaisons.add( l );

                    int pasX = Integer.signum( dx );
                    int pasY = Integer.signum( dy );

                    int x    = p1.getX() + pasX;
                    int y    = p1.getY() + pasY;

                    while ( x != p2.getX() || y != p2.getY() )
                    {
                        grilleLiaisons[x][y]  = '*';
                        x                    += pasX;
                        y                    += pasY;
                    }
                }
            }
        }
    }

    // Vérifie s'il existe un poisson entre p1 et p2


    private boolean existePoissonIntermediaire( Poisson p1, Poisson p2 )
    {
        int dx = Integer.signum( p2.getX() - p1.getX() );
        int dy = Integer.signum( p2.getY() - p1.getY() );
        int x  = p1.getX() + dx;
        int y  = p1.getY() + dy;

        while ( x != p2.getX() || y != p2.getY() )
        {
            if ( grillePoisson[x][y] != null )
                return true;
            x += dx;
            y += dy;
        }
        return false;
    }

    // Récupère le poisson à une position donnée


    public Poisson getPoisson( int x, int y )
    {
        return this.grillePoisson[x][y];
    }

    // Récupère la grille complète des poissons


    public Poisson[][] getGrillePoisson()
    {
        return this.grillePoisson;
    }

    // Récupère la liste des poissons


    public List<Poisson> getLstPoisson()
    {
        return this.lstPoisson;
    }

    // Récupère les liaisons à une position donnée


    public Liaison getLiaisons( int x, int y )
    {
        for ( Liaison l : this.lstLiaisons )
        {
            if ( (l.getP1().getX() == x && l.getP1().getY() == y) || (l.getP2().getX() == x && l.getP2().getY() == y) )
            {
                return l;
            }
        }
        return null;
    }

    // Récupère la liste des liaisons


    public List<Liaison> getLstLiaisons()
    {
        return this.lstLiaisons;
    }

    // Vérifie si deux poissons sont liés


    public boolean estLie( Poisson p1, Poisson p2 )
    {
        for ( Liaison l : this.lstLiaisons )
        {
            if ( (l.getP1().equals( p1 ) && l.getP2().equals( p2 )) || (l.getP1().equals( p2 ) && l.getP2().equals( p1 )) )
            {
                return true;
            }
        }
        return false;
    }

    // Récupère la zone à une position donnée


    public Zone getZone( int x, int y )
    {
        return this.grilleZone[x][y];
    }


    // Récupère la grille des zones complète
    public Zone[][] getGrilleZone()
    {
        return this.grilleZone;
    }

    // Vérifie si une zone existe avec ce numéro


    public boolean zoneExiste( int numZone )
    {
        if ( this.grilleZone == null )
            return false;

        for ( int i = 0; i < this.grilleZone.length; i++ )
        {
            for ( int j = 0; j < this.grilleZone[i].length; j++ )
            {
                if ( this.grilleZone[i][j] != null && this.grilleZone[i][j].getNumZone() == numZone )
                {
                    return true;
                }
            }
        }
        return false;
    }

    // Récupère la liste des espèces disponibles


    public String[] getEspeces()
    {
        return this.espece;
    }

    // Récupère le poisson sélectionné à une position donnée


    public String getPoissonIndice( int indiceX, int indiceY )
    {
        for ( int i = 0; i < this.grillePoisson.length; i++ )
        {
            for ( int j = 0; j < this.grillePoisson[i].length; j++ )
                if ( this.grillePoisson[i][j] != null && i == indiceX && j == indiceY )
                {
                    return this.grillePoisson[i][j].getEspece();
                }
        }

        return "";
    }


    // Récupère le numéro de la zone aux indices donnés
    public int getZoneIndice( int indiceX, int indiceY )
    {
        for ( int i = 0; i < this.grilleZone.length; i++ )
        {
            for ( int j = 0; j < this.grilleZone[i].length; j++ )
                if ( this.grilleZone[i][j] != null && i == indiceX && j == indiceY )
                {
                    return this.grilleZone[i][j].getNumZone();
                }
        }

        return -1;
    }


    // Récupère le numéro de laboratoire aux coordonnées données
    public int getLaboIndice( int indiceX, int indiceY )
    {
        for ( int i = 0; i < this.grilleLabo.length; i++ )
        {
            for ( int j = 0; j < this.grilleLabo[i].length; j++ )
            {

                if ( this.grilleLabo[i][j] != 0 && i == indiceX && j == indiceY )
                {
                    return this.grilleLabo[i][j];
                }
            }
        }

        return -1;
    }


    // Récupère la grille des laboratoires


    public int[][] getGrilleLabo()
    {
        return this.grilleLabo;
    }


    // Gère l'avancement ou le début d'une étude de poisson dans un laboratoire
    public String etudePoisson( Poisson p )
    {
        if ( this.getLaboIndice( p.getX(), p.getY() ) != -1 && this.progressionLabo == null )
        {
            this.idLaboActif     = this.getLaboIndice( p.getX(), p.getY() );
            this.progressionLabo = new ProgressionLabo( p );

            // On mémorise le poisson de départ de la recherche
            if ( !this.poisonsManche.contains( p ) )
            {
                this.poisonsManche.add( p );
            }

            return "Début de l'étude du laboratoire sur un(e) " + p.getEspece();
        } else if ( this.progressionLabo != null )
        {
            Liaison l1 = getLiaisonEntre( this.progressionLabo.getExtremite1(), p );
            Liaison l2 = getLiaisonEntre( this.progressionLabo.getExtremite2(), p );

            if ( this.progressionLabo.getExtremite2() == null && l1 != null && !this.croiseUneLiaisonVisitee( l1 ) )
            {
                this.liaisonsVisitees.add( l1 );
                this.labosDesLiaisons.add( this.idLaboActif );
                this.progressionLabo.setExtremite2( p );

                // On mémorise le poisson étudié
                if ( !this.poisonsManche.contains( p ) )
                {
                    this.poisonsManche.add( p );
                }

                return "Première étude du laboratoire sur un(e) " + p.getEspece();
            } else
            {
                if ( l1 != null && !this.croiseUneLiaisonVisitee( l1 ) )
                {
                    this.liaisonsVisitees.add( l1 );
                    this.labosDesLiaisons.add( this.idLaboActif );
                    this.progressionLabo.setExtremite1( p );

                    // On mémorise le poisson étudié
                    if ( !this.poisonsManche.contains( p ) )
                    {
                        this.poisonsManche.add( p );
                    }

                    return "Nouvelle étude du laboratoire sur un(e) " + p.getEspece();
                } else if ( l2 != null && !this.croiseUneLiaisonVisitee( l2 ) )
                {
                    this.liaisonsVisitees.add( l2 );
                    this.labosDesLiaisons.add( this.idLaboActif );
                    this.progressionLabo.setExtremite2( p );

                    // On mémorise le poisson étudié
                    if ( !this.poisonsManche.contains( p ) )
                    {
                        this.poisonsManche.add( p );
                    }

                    return "Nouvelle étude du laboratoire sur un(e) " + p.getEspece();
                } else
                {
                    return "Ce/Cette " + p.getEspece() + " n'est pas lié à l'étude en cours ou croise un chemin.";
                }
            }
        }
        return "";
    }


    // Recherche la liaison directe existante entre deux poissons donnés
    private Liaison getLiaisonEntre( Poisson p1, Poisson p2 )
    {
        for ( Liaison l : this.lstLiaisons )
        {
            if ( (l.getP1().equals( p1 ) && l.getP2().equals( p2 )) || (l.getP1().equals( p2 ) && l.getP2().equals( p1 )) )
                return l;
        }
        return null;
    }


    // Récupère l'identifiant du laboratoire qui utilise une liaison spécifique
    public int getLaboDeLiaison( int x1, int y1, int x2, int y2 )
    {
        for ( int i = 0; i < this.liaisonsVisitees.size(); i++ )
        {
            Liaison l = this.liaisonsVisitees.get( i );
            if ( ((l.getP1().getX() == x1 && l.getP1().getY() == y1 && l.getP2().getX() == x2 && l.getP2().getY() == y2)
                || (l.getP1().getX() == x2 && l.getP1().getY() == y2 && l.getP2().getX() == x1 && l.getP2().getY() == y1)) )
            {
                return this.labosDesLiaisons.get( i );
            }
        }
        return -1;
    }


    // Algorithme qui détermine mathématiquement si deux segments de droite se croisent
    private boolean segmentsCroisent( int xA, int yA, int xB, int yB, int xC, int yC, int xD, int yD )
    {
        if ( (xA == xC && yA == yC) || (xA == xD && yA == yD) || (xB == xC && yB == yC) || (xB == xD && yB == yD) )
        {
            return false;
        }
        long cp1 = (long) (xB - xA) * (yC - yA) - (long) (yB - yA) * (xC - xA);
        long cp2 = (long) (xB - xA) * (yD - yA) - (long) (yB - yA) * (xD - xA);
        long cp3 = (long) (xD - xC) * (yA - yC) - (long) (yD - yC) * (xA - xC);
        long cp4 = (long) (xD - xC) * (yB - yC) - (long) (yD - yC) * (xB - xC);
        return ((cp1 > 0 && cp2 < 0) || (cp1 < 0 && cp2 > 0)) && ((cp3 > 0 && cp4 < 0) || (cp3 < 0 && cp4 > 0));
    }


    // Vérifie si la nouvelle liaison coupe une liaison qui a déjà été validée
    private boolean croiseUneLiaisonVisitee( Liaison nouvelle )
    {
        if ( nouvelle == null )
            return false;

        int xA = nouvelle.getP1().getX();
        int yA = nouvelle.getP1().getY();
        int xB = nouvelle.getP2().getX();
        int yB = nouvelle.getP2().getY();

        for ( Liaison l : this.liaisonsVisitees )
        {
            int xC = l.getP1().getX();
            int yC = l.getP1().getY();
            int xD = l.getP2().getX();
            int yD = l.getP2().getY();

            if ( ((xA == xC && yA == yC) && (xB == xD && yB == yD)) || ((xA == xD && yA == yD) && (xB == xC && yB == yC)) )
            {
                return true;
            }

            if ( segmentsCroisent( xA, yA, xB, yB, xC, yC, xD, yD ) )
            {
                return true;
            }
        }
        return false;
    }


    // Vérifie si le poisson peut être sélectionné par rapport aux extrémités du chemin actuel
    public boolean estPoissonValidePourLabo( Poisson p )
    {
        if ( p == null )
            return false;
        if ( this.progressionLabo == null )
        {
            return this.getLaboIndice( p.getX(), p.getY() ) != -1;
        }

        if ( this.progressionLabo.getExtremite2() == null )
        {
            Liaison l = getLiaisonEntre( this.progressionLabo.getExtremite1(), p );
            return l != null && !this.croiseUneLiaisonVisitee( l );
        }

        Liaison l1               = getLiaisonEntre( this.progressionLabo.getExtremite1(), p );
        Liaison l2               = getLiaisonEntre( this.progressionLabo.getExtremite2(), p );

        boolean valideExtremite1 = (l1 != null && !this.croiseUneLiaisonVisitee( l1 ));
        boolean valideExtremite2 = (l2 != null && !this.croiseUneLiaisonVisitee( l2 ));

        return valideExtremite1 || valideExtremite2;
    }


    // Indique si un laboratoire est en cours d'étude
    public boolean estUnLaboActif()
    {
        return this.progressionLabo != null;
    }


    // Renvoie la première extrémité de l'étude en cours
    public Poisson getExtremite1()
    {
        return this.progressionLabo != null ? this.progressionLabo.getExtremite1() : null;
    }


    // Renvoie la deuxième extrémité de l'étude en cours
    public Poisson getExtremite2()
    {
        return this.progressionLabo != null ? this.progressionLabo.getExtremite2() : null;
    }


    // Vérifie s'il est possible de se déplacer depuis une extrémité choisie vers un autre poisson
    public boolean verifierDeplacement( Poisson ext, Poisson dest )
    {
        if ( this.progressionLabo == null || ext == null || dest == null )
            return false;

        if ( ext != this.progressionLabo.getExtremite1() && ext != this.progressionLabo.getExtremite2() )
            return false;

        Liaison l = getLiaisonEntre( ext, dest );
        return l != null && !this.croiseUneLiaisonVisitee( l );
    }


    // Valide et enregistre un déplacement depuis une extrémité spécifique vers un poisson de destination
    public String validerEtAvancerEtudeAvecExtremite( Poisson ext, Poisson dest )
    {
        if ( this.progressionLabo == null )
            return "";

        Liaison l = getLiaisonEntre( ext, dest );
        if ( l != null && !this.croiseUneLiaisonVisitee( l ) )
        {
            this.liaisonsVisitees.add( l );
            this.labosDesLiaisons.add( this.idLaboActif );

            if ( this.progressionLabo.getExtremite2() == null )
            {
                this.progressionLabo.setExtremite2( dest );
            } else
            {
                if ( ext == this.progressionLabo.getExtremite1() )
                {
                    this.progressionLabo.setExtremite1( dest );
                } else if ( ext == this.progressionLabo.getExtremite2() )
                {
                    this.progressionLabo.setExtremite2( dest );
                }
            }
            if ( !this.poisonsManche.contains( dest ) )
            {
                this.poisonsManche.add( dest );
            }
            return "Nouvelle étude du laboratoire sur un(e) " + dest.getEspece();
        }
        return "Mouvement invalide";
    }


    // Finit la manche en appliquant le vrai calcul de barème de la SAÉ
    public void finirManche()
    {
        if ( this.points != null && !this.poisonsManche.isEmpty() )
        {
            // 1. Déterminer les régions distinctes visitées au cours de la manche
            List<Integer> regionsDistinctes = new ArrayList<>();
            for ( Poisson p : this.poisonsManche )
            {
                int idRegion = this.getZoneIndice( p.getX(), p.getY() );
                if ( idRegion != -1 && !regionsDistinctes.contains( idRegion ) )
                {
                    regionsDistinctes.add( idRegion );
                }
            }
            int ptRegions   = regionsDistinctes.size(); // 1 point par région distincte

            // 2. Déterminer la région dominante (le maximum de poissons étudiés dans une seule région)
            int ptRecherche = 0;
            for ( int idRegion : regionsDistinctes )
            {
                int countFishesInRegion = 0;
                for ( Poisson p : this.poisonsManche )
                {
                    if ( this.getZoneIndice( p.getX(), p.getY() ) == idRegion )
                    {
                        countFishesInRegion++;
                    }
                }

                // On garde uniquement le score de la région la plus peuplée
                if ( countFishesInRegion > ptRecherche )
                {
                    ptRecherche = countFishesInRegion;
                }
            }

            // 3. Envoyer les points officiels calculés à l'objet Fiche de score
            this.points.pointsZonesVisitees( ptRegions );
            this.points.pointsPoissonsCapturesZones( ptRecherche );

            // Fait la multiplication (Total += Régions * Recherche) et nettoie la manche
            this.points.calculerPointsTotal();
            this.points.resetPointsManche();
        }

        // On vide la liste pour être prêt pour la manche suivante
        this.poisonsManche.clear();

        this.progressionLabo = null;
        this.idLaboActif     = -1;
    }

    // Initialise la grille, des poissons, des zones et des liaisons


    public void initTableau( int longueur, int largeur, int nbSymbole, int nbLabo, int tailleCases )
    {
        this.longueur       = longueur;
        this.largeur        = largeur;
        this.nbSymbole      = nbSymbole;
        this.tailleCases    = tailleCases;
        this.grillePoisson  = new Poisson[largeur][longueur];
        this.grilleLiaisons = new char[largeur][longueur];
        this.grilleZone     = new Zone[largeur][longueur];
        this.grilleLabo     = new int[largeur][longueur];
    }


    // Instancie et place un nouveau poisson sur la grille
    public void initPoisson( int id, String espece, int x, int y )
    {
        Poisson p = new Poisson( espece, x, y );
        this.grillePoisson[x][y] = p;
        this.lstPoisson.add( p );
    }


    // Instancie et place une zone sur la grille du plateau
    public void initZone( int numZone, int x, int y )
    {
        Zone z = new Zone( numZone, x, y );
        this.grilleZone[x][y] = z;
    }


    // Associe un numéro de laboratoire aux coordonnées indiquées
    public void initLabo( int numLabo, int x, int y )
    {
        this.grilleLabo[x][y] = numLabo;
    }


    // Crée une liaison entre deux poissons identifiés par leurs numéros uniques
    public void initLiaison( int id1, int id2 )
    {
        Poisson p1 = null;
        Poisson p2 = null;

        for ( Poisson p : this.lstPoisson )
        {
            if ( p.getId() == id1 )
            {
                p1 = p;
            }
            if ( p.getId() == id2 )
            {
                p2 = p;
            }
        }

        if ( p1 != null && p2 != null )
        {
            Liaison l = new Liaison( p1, p2 );
            this.lstLiaisons.add( l );
        }
    }


    // Crée une liaison entre deux poissons à partir de leurs coordonnées
    public void initLiaisonCoord( int x1, int y1, int x2, int y2 )
    {
        if ( x1 < 0 || x1 >= this.grillePoisson.length || y1 < 0 || y1 >= this.grillePoisson[0].length || x2 < 0 || x2 >= this.grillePoisson.length || y2 < 0
            || y2 >= this.grillePoisson[0].length )
        {
            return;
        }

        Poisson p1 = this.grillePoisson[x1][y1];
        Poisson p2 = this.grillePoisson[x2][y2];

        if ( p1 != null && p2 != null )
        {
            this.lstLiaisons.add( new Liaison( p1, p2 ) );
        }
    }


    // Parcourt les poissons pour savoir s'ils sont placés sur une case laboratoire
    public void restaurerLabos()
    {
        for ( int x = 0; x < this.grillePoisson.length; x++ )
        {
            for ( int y = 0; y < this.grillePoisson[x].length; y++ )
            {
                Poisson p = this.grillePoisson[x][y];

                if ( p != null && this.grilleLabo[x][y] != 0 )
                {
                    p.setEstLab( true );
                }
            }
        }
    }


    // Remplace la liste complète des liaisons par une nouvelle liste
    public void setLstLiaisons( ArrayList<Liaison> lst )
    {
        this.lstLiaisons = lst;
    }


    // Récupère la longueur maximale de la grille
    public int getLongueur()
    {
        return this.longueur;
    }


    // Récupère la largeur maximale de la grille
    public int getLargeur()
    {
        return this.largeur;
    }


    // Récupère la quantité de symboles différents configurés
    public int getNbSymbole()
    {
        return this.nbSymbole;
    }


    // Récupère la taille d'affichage d'une case de la grille
    public int getTailleCase()
    {
        return this.tailleCases;
    }


    public void lireDonnees( String dossier )
    {
        LireDonnees lecteur = new LireDonnees( this );

        lecteur.lireGrille( dossier );
        lecteur.lirePoissons( dossier );
        lecteur.lireZones( dossier );
        lecteur.lireLiaisons( dossier );
        lecteur.lireLabo( dossier );
    }


    // Récupère le score total actuel calculé par l'objet Points
    public int getPointsTotal()
    {
        return this.points.getPointsTotal();
    }
}
