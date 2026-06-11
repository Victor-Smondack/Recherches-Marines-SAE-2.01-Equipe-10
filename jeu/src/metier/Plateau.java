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
    private List<Liaison>   lstLiaisons;
    private Zone[][]        grilleZone;
    private List<Poisson>   lstPoisson;
    private char[][]        grilleLiaisons;
    private int[][]         grilleLabo;
    private String[]        espece = {
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

    public Plateau(int longueur, int largeur)
    {
        this.grillePoisson  = new Poisson[longueur][largeur];
        this.lstPoisson     = new ArrayList<Poisson>();
        this.grilleLiaisons = new char[longueur][largeur];
        this.lstLiaisons    = new ArrayList<>();
        this.grilleZone     = new Zone[longueur][largeur];
        this.grilleLabo     = new int[longueur][largeur];
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

    // Récupère les liaisons à une position donnée


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


    public Zone[][] getGrilleZone()
    {
        return this.grilleZone;
    }

    // Place une zone à une position donnée


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

    // Échange la position de deux poissons


    public String[] getEspeces()
    {
        return this.espece;
    }

    // Récupère la zone à une position donnée


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


    // Récupère la grille des laboratoires


    public int[][] getGrilleLabo()
    {
        return this.grilleLabo;
    }

    // Vérifie si une zone peut être placée à une position donnée


    public boolean isZonePossible( int x, int y, int zone )
    {
        if ( !zoneExiste( zone ) )
        {
            return true;
        }

        if ( x > 0 && this.grilleZone[x - 1][y] != null && this.grilleZone[x - 1][y].getNumZone() == zone )
        {
            return true;
        }

        if ( x < this.grilleZone.length - 1 && this.grilleZone[x + 1][y] != null && this.grilleZone[x + 1][y].getNumZone() == zone )
        {
            return true;
        }

        if ( y > 0 && this.grilleZone[x][y - 1] != null && this.grilleZone[x][y - 1].getNumZone() == zone )
        {
            return true;
        }

        if ( y < this.grilleZone[0].length - 1 && this.grilleZone[x][y + 1] != null && this.grilleZone[x][y + 1].getNumZone() == zone )
        {
            return true;
        }

        return false;
    }


    public String etudePoisson( Poisson p )
    {
        if ( p.getEstLab() == true && this.progressionLabo == null )
        {
            this.progressionLabo = new ProgressionLabo( p );
            return "Début de l'étude du laboratoire sur un(e) " + p.getEspece() + " avec le laboratoire " + p.getCouleurLab();
        } else
        {
            if ( this.progressionLabo.getExtremite2() == null && estLie( this.progressionLabo.getExtremite1(), p ) )
            {
                this.progressionLabo.setExtremite2( p );
                return "Première étude du laboratoire sur un(e) " + p.getEspece();

            } else
            {
                if ( estLie( this.progressionLabo.getExtremite1(), p ) )
                {
                    this.progressionLabo.setExtremite1( p );
                    return "Nouvelle étude du laboratoire sur un(e) " + p.getEspece();
                } else if ( estLie( this.progressionLabo.getExtremite2(), p ) )
                {
                    this.progressionLabo.setExtremite2( p );
                    return "Nouvelle étude du laboratoire sur un(e) " + p.getEspece();
                } else
                {
                    return "Ce/Cette " + p.getEspece() + " n'est pas lié à l'étude en cours.";
                }
            }
        }
    }

    // Initialise la grille, des poissons, des zones et des liaisons


    public void initTableau( int longueur, int largeur, int nbSymbole, int tailleCases )
    {
        this.grillePoisson  = new Poisson[longueur][largeur];
        this.grilleLiaisons = new char[longueur][largeur];
        this.grilleZone     = new Zone[longueur][largeur];
        this.grilleLabo     = new int[longueur][largeur];
    }


    public void initPoisson( int id, String espece, int x, int y )
    {
        Poisson p = new Poisson( espece, x, y );
        this.grillePoisson[x][y] = p;
        this.lstPoisson.add( p );
    }


    public void initZone( int numZone, int x, int y )
    {
        Zone z = new Zone( numZone, x, y );
        this.grilleZone[x][y] = z;
    }


    public void initLabo( int numLabo, int x, int y )
    {
        this.grilleLabo[x][y] = numLabo;
    }


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
}

