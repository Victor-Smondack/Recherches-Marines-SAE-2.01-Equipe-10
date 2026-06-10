package src.metier;

import java.util.ArrayList;
import java.util.List;

public class Plateau
{
    /**********************/
    /* Attributs */
    /**********************/

    private Poisson[][]   grillePoisson;
    private String        poissonSelect;
    private boolean       zoneSelect = false;
    private boolean       laboSelect = false;
    private List<Liaison> lstLiaisons;
    private Zone[][]      grilleZone;
    private List<Poisson> lstPoisson;
    private char[][]      grilleLiaisons;
    private int[][]       grilleLabo;
    private String[]      espece     = {
        "Saumon",
        "Truite",
        "Thon",
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

    // Place un poisson à une position donnée


    public void positionnePoisson( int x, int y, String espece )
    {
        Poisson p = new Poisson( espece, x, y );
        this.grillePoisson[x][y] = p;
        this.lstPoisson.add( p );
    }

    // Récupère le poisson à une position donnée


    public Poisson getPoisson( int x, int y )
    {
        return this.grillePoisson[x][y];
    }

    // Récupère la grille des poissons positionné


    public Poisson[][] getGrillePoisson()
    {
        return this.grillePoisson;
    }

    // Récupère la liste des poissons


    public List<Poisson> getLstPoisson()
    {
        return this.lstPoisson;
    }

    // Supprime toute les donner d'une carte à une position donnée


    public void gommer( int x, int y )
    {
        for ( Poisson p : this.lstPoisson )
        {
            if ( (p.getX() == x) && (p.getY() == y) )
            {
                this.lstPoisson.remove( p );
                break;
            }
        }
        this.grillePoisson[x][y] = null;
        this.grilleZone[x][y]    = null;
        this.grilleLabo[x][y]    = 0;
        this.genererLiaisons();
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

    // Positionne les zones dans la grille


    public boolean positionneZone( int indiceX, int indiceY, int numZone )
    {
        if ( isZonePossible( indiceX, indiceY, numZone ) )
        {
            Zone z = new Zone( numZone, indiceX, indiceY );
            this.grilleZone[indiceX][indiceY] = z;
            return true;
        } else
        {
            return false;
        }
    }


    // Positionne les labo dans la grille
    public int[] positionneLabo( int indiceX, int indiceY, int numLabo )
    {
        int[] anciennesCoords = null;

        for ( int i = 0; i < this.grilleLabo.length; i++ )
        {
            for ( int j = 0; j < this.grilleLabo[i].length; j++ )
            {
                if ( this.grilleLabo[i][j] == numLabo )
                {
                    if ( i != indiceX || j != indiceY )
                    {
                        anciennesCoords       = new int[] {
                            i,
                            j };
                        this.grilleLabo[i][j] = 0;
                    }
                }
            }
        }

        this.grilleLabo[indiceX][indiceY] = numLabo;

        return anciennesCoords; //
    }


    // Récupère la zone à une position donnée


    public Zone getZone( int x, int y )
    {
        return this.grilleZone[x][y];
    }


    // Récupère la grille des zones
    public Zone[][] getGrilleZone()
    {
        return this.grilleZone;
    }

    // Vérifie si une zone existe ou pas


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

    // Récupère la liste des espèces possible


    public String[] getEspeces()
    {
        return this.espece;
    }

    // Défini le poisson choisi par l'utilisateur


    public void setPoissonSelect( int numEspece )
    {
        if ( numEspece >= 0 && numEspece < this.espece.length )
        {
            this.poissonSelect = this.espece[numEspece];
            this.zoneSelect    = false;
            this.laboSelect    = false;
        } else
        {
            this.poissonSelect = "";
        }
    }

    // Récupère le poisson selectionné


    public String getPoissonSelect()
    {
        return this.poissonSelect;
    }

    // Défini la zone choisi par l'utilisateur


    public void setZoneSelect( boolean select )
    {
        this.zoneSelect = select;
        if ( select )
        {
            this.poissonSelect = "";
            this.laboSelect    = false;
        }
    }

    // Récupère si la zone est selectionné


    public boolean isZoneSelect()
    {
        return this.zoneSelect;
    }

    // Défini si la zone est choisi par l'utilisateur


    public void setLaboSelect( boolean select )
    {
        this.laboSelect = select;
        if ( select )
        {
            this.poissonSelect = "";
            this.zoneSelect    = false;
        }
    }

    // Récupère si la zone est selectionné


    public boolean isLaboSelect()
    {
        return this.laboSelect;
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

    // Sauvegarde de la grille, des poissons, des zones et des liaisons


    public void Sauvegarder()
    {
        Sauvegarde.sauvegarderGrille( this.grillePoisson.length, this.grillePoisson[0].length, this.espece.length, 50 );
        Sauvegarde.sauvegarderPoissons( this.lstPoisson );
        Sauvegarde.sauvegarderZones( this.grilleZone );
        Sauvegarde.sauvegarderLiaisons( this.lstLiaisons );
        Sauvegarde.sauvegarderLabo( this.grilleLabo );
    }

    // Affiche la grille de poissons


    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for ( int y = 0; y < grillePoisson[0].length; y++ )
        {
            for ( int x = 0; x < grillePoisson.length; x++ )
            {
                if ( grillePoisson[x][y] != null )
                {
                    sb.append( grillePoisson[x][y].getEspece().charAt( 0 ) ).append( " " );
                } else
                {
                    sb.append( ". " );
                }
            }

            sb.append( "\n" );
        }

        return sb.toString();
    }

    // Affiche la grille des liaisons


    public String toStringLiaisons()
    {
        StringBuilder sb = new StringBuilder();

        for ( int y = 0; y < grilleLiaisons[0].length; y++ )
        {
            for ( int x = 0; x < grilleLiaisons.length; x++ )
            {
                sb.append( grilleLiaisons[x][y] ).append( " " );
            }

            sb.append( "\n" );
        }

        return sb.toString();
    }
}

