package src.metier;

import java.util.ArrayList;
import java.util.List;

public class Plateau
{
    private Poisson[][]   grillePoisson;
    private List<Liaison> lstLiaisons;
    private Zone[][]      grilleZone;
    private List<Poisson> lstPoisson;
    private String[]      espece = { "Saumon",
        "Thon",
        "Truite",
        "Sardine",
        "Bar",
        "Colin",
        "Maquereau" };

    public Plateau(int longueur, int largeur)
    {
        this.grillePoisson = new Poisson[longueur][largeur];
        this.lstLiaisons   = new ArrayList<>();
        this.grilleZone    = new Zone[longueur][largeur];
        this.lstPoisson    = new ArrayList<Poisson>();
    }


    public void genererLiaisons()
    {
        this.lstLiaisons.clear();

        int           dx         = 0;
        int           dy         = 0;
        Poisson       p1         = new Poisson( "", 0, 0 );
        Poisson       p2         = new Poisson( "", 0, 0 );

        // Collecte de tous les poissons présents dans la grille
        List<Poisson> lstPoisson = new ArrayList<>();
        for ( int x = 0; x < grillePoisson.length; x++ )
            for ( int y = 0; y < grillePoisson[x].length; y++ )
                if ( grillePoisson[x][y] != null )
                    lstPoisson.add( grillePoisson[x][y] );

        // Génération des liaisons entre paires sans intermédiaire
        for ( int i = 0; i < lstPoisson.size(); i++ )
        {
            for ( int j = i + 1; j < lstPoisson.size(); j++ )
            {
                p1 = lstPoisson.get( i );
                p2 = lstPoisson.get( j );

                dx = p2.getX() - p1.getX();
                dy = p2.getY() - p1.getY();

                boolean alignes = dx == 0 || dy == 0 || Math.abs( dx ) == Math.abs( dy );

                if ( alignes && !existePoissonIntermediaire( p1, p2 ) )
                {
                    Liaison l = new Liaison( p1, p2 );
                    lstLiaisons.add( l );
                    System.out.println( l );
                }
            }
        }
    }


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


    public Poisson getPoisson( int x, int y )
    {
        return this.grillePoisson[x][y];
    }


    public Zone getZone( int x, int y )
    {
        return this.grilleZone[x][y];
    }


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


    public boolean estLie( Poisson p1, Poisson p2 )
    {
        for ( Liaison l : this.lstLiaisons )
        {
            if ( (l.getP1().equals( p1 ) && l.getP2().equals( p2 ))
                || (l.getP1().equals( p2 ) && l.getP2().equals( p1 )) )
            {
                return true;
            }
        }
        return false;
    }


    public Zone[][] getGrilleZone()
    {
        return this.grilleZone;
    }


    public Poisson[][] getGrillePoisson()
    {
        return this.grillePoisson;
    }


    public List<Liaison> getLstLiaisons()
    {
        return this.lstLiaisons;
    }


    public List<Poisson> getLstPoisson()
    {
        return this.lstPoisson;
    }


    public String[] getEspece()
    {
        return this.espece;
    }


    public void positionPoisson( int x, int y, String espece, int indiceX, int indiceY )
    {
        Poisson p = new Poisson( espece, x, y );
        this.grillePoisson[indiceX][indiceY] = p;
        this.lstPoisson.add( p );
    }


    public void echangerPoisson( int xDep, int yDep, int xDest, int yDest )
    {
        for ( Poisson p : this.lstPoisson )
        {
            if ( (p.getX() == xDep) && (p.getY() == yDep) && (p.getX() == xDest) && (p.getY() == yDest) )
            {
                Poisson ptemp = new Poisson( p.getEspece(), p.getX(), p.getY() );
                p.setX( xDest );
                p.setY( yDest );
                ptemp.setX( xDep );
                ptemp.setY( yDep );
            } else if ( (p.getX() == xDep) && (p.getY() == yDep) )
            {
                p.setX( xDest );
                p.setY( yDest );
            }
        }
    }


    public void supprimerPoisson( int x, int y )
    {
        for ( Poisson p : this.lstPoisson )
        {
            if ( (p.getX() == x) && (p.getY() == y) )
            {
                this.lstPoisson.remove( p );
                this.grillePoisson[x][y] = null;
                break;
            }
        }
    }

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
}
