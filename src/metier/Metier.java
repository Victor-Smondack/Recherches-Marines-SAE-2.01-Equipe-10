package src.metier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Metier
{
    private Poisson[][]   grillePoisson;
    private String        poissonSelect;
    private List<Liaison> lstLiaisons;
    private Zone[][]      grilleZone;
    private List<Poisson> lstPoisson;
    private char[][]      grilleLiaisons;
    private String[]      espece = { "Saumon",
        "Thon",
        "Truite",
        "Sardine",
        "Bar",
        "Colin",
        "Maquereau" };

    public Metier(int longueur, int largeur)
    {
        this.grillePoisson  = new Poisson[longueur][largeur];
        this.lstPoisson     = new ArrayList<Poisson>();
        this.grilleLiaisons = new char[longueur][largeur];
        this.lstLiaisons    = new ArrayList<>();
        this.grilleZone     = new Zone[longueur][largeur];
    }


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

                boolean alignes = dx == 0 ||
                    dy == 0 ||
                    Math.abs( dx ) == Math.abs( dy );

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


    public void positionPoisson( int x, int y, String espece )
    {
        Poisson p = new Poisson( espece, x, y );
        this.grillePoisson[x][y] = p;
        this.lstPoisson.add( p );
    }


    public Poisson getPoisson( int x, int y )
    {
        return this.grillePoisson[x][y];
    }


    public Poisson[][] getGrillePoisson()
    {
        return this.grillePoisson;
    }


    public List<Poisson> getLstPoisson()
    {
        return this.lstPoisson;
    }


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
        this.genererLiaisons();
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


    public List<Liaison> getLstLiaisons()
    {
        return this.lstLiaisons;
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


    public void positionneZone( int indiceX, int indiceY, int numZone )
    {
        Zone z = new Zone( numZone );
        this.grilleZone[indiceX][indiceY] = z;
    }


    public Zone getZone( int x, int y )
    {
        return this.grilleZone[x][y];
    }


    public Zone[][] getGrilleZone()
    {
        return this.grilleZone;
    }


    public void sauvegarderGrille( int longueur, int largeur, int nbSymbole, int tailleCases )
    {
        try (BufferedWriter writer = new BufferedWriter( new FileWriter( "grille.txt" ) ))
        {
            writer.write( longueur + " " + largeur + " " + nbSymbole + " " + tailleCases );
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void sauvegarderPoissons( List<Poisson> lstPoisson )
    {
        try (BufferedWriter writer = new BufferedWriter( new FileWriter( "poissons.txt" ) ))
        {
            for ( Poisson p : lstPoisson )
            {
                writer.write( p.getEspece() + " " + p.getX() + " " + p.getY() );
                writer.newLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void sauvegarderZones( Zone[][] grilleZone )
    {
        try (BufferedWriter writer = new BufferedWriter( new FileWriter( "zones.txt" ) ))
        {
            for ( int y = 0; y < grilleZone[0].length; y++ )
            {
                for ( int x = 0; x < grilleZone.length; x++ )
                {
                    if ( grilleZone[x][y] != null )
                    {
                        writer.write( grilleZone[x][y].getNumZone() + " " );
                    } else
                    {
                        writer.write( ". " );
                    }
                }
                writer.newLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void sauvegarderLiaisons( List<Liaison> lstLiaisons )
    {
        try (BufferedWriter writer = new BufferedWriter( new FileWriter( "liaisons.txt" ) ))
        {
            for ( Liaison l : lstLiaisons )
            {
                writer.write(
                              l.getP1().getEspece() + " (" + l.getP1().getX() + ", " + l.getP1().getY() + ") <-> "
                                  + l.getP2().getEspece() + " (" + l.getP2().getX() + ", " + l.getP2().getY() + ")" );
                writer.newLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
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


    public void positionnePoisson( int indiceX, int indiceY, String espece )
    {
        Poisson p = new Poisson( espece, indiceX, indiceY );
        this.grillePoisson[indiceX][indiceY] = p;
    }


    public String[] getEspeces()
    {
        return this.espece;
    }

    private boolean zoneSelect = false;

    public void setPoissonSelect( int numEspece )
    {
        if ( numEspece >= 0 && numEspece < this.espece.length )
        {
            this.poissonSelect = this.espece[numEspece];
            this.zoneSelect    = false;
        } else
        {
            this.poissonSelect = "";
        }
    }


    public String getPoissonSelect()
    {
        return this.poissonSelect;
    }


    public void setZoneSelect( boolean select )
    {
        this.zoneSelect = select;
        if ( select )
        {
            this.poissonSelect = "";
        }
    }


    public boolean isZoneSelect()
    {
        return this.zoneSelect;
    }
}
