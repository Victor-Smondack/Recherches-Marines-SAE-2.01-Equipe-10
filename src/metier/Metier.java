package src.metier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Metier
{
    private Poisson[][]   grillePoisson;
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
        this.grillePoisson = new Poisson[longueur][largeur];
        this.lstPoisson    = new ArrayList<Poisson>();
        this.grilleLiaisons = new char[longueur][largeur];
        this.lstLiaisons   = new ArrayList<>();
        this.grilleZone    = new Zone[longueur][largeur];
    }


    public void genererLiaisons()
    {
        this.lstLiaisons.clear();

        // Réinitialisation de la grille des liaisons
        for (int x = 0; x < grilleLiaisons.length; x++)
            for (int y = 0; y < grilleLiaisons[x].length; y++)
                grilleLiaisons[x][y] = '.';

        // Collecte de tous les poissons présents dans la grille
        List<Poisson> lstPoisson = new ArrayList<>();

        for (int x = 0; x < grillePoisson.length; x++)
            for (int y = 0; y < grillePoisson[x].length; y++)
                if (grillePoisson[x][y] != null)
                    lstPoisson.add(grillePoisson[x][y]);

        // Génération des liaisons
        for (int i = 0; i < lstPoisson.size(); i++)
        {
            for (int j = i + 1; j < lstPoisson.size(); j++)
            {
                Poisson p1 = lstPoisson.get(i);
                Poisson p2 = lstPoisson.get(j);

                int dx = p2.getX() - p1.getX();
                int dy = p2.getY() - p1.getY();

                boolean alignes =
                    dx == 0 ||
                    dy == 0 ||
                    Math.abs(dx) == Math.abs(dy);

                if (alignes && !existePoissonIntermediaire(p1, p2))
                {
                    Liaison l = new Liaison(p1, p2);
                    this.lstLiaisons.add(l);

                    // Marquage des cases traversées
                    int pasX = Integer.signum(dx);
                    int pasY = Integer.signum(dy);

                    int x = p1.getX() + pasX;
                    int y = p1.getY() + pasY;

                    while (x != p2.getX() || y != p2.getY())
                    {
                        // On ne marque que les cases intermédiaires
                        grilleLiaisons[x][y] = '*';

                        x += pasX;
                        y += pasY;
                    }
                }
            }
        }
    }

    // Méthode pour vérifier s'il existe un poisson intermédiaire entre deux poissons alignés

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

    // Méthode pour positionner un poisson à une position donnée

    public void positionPoisson( int x, int y, String espece)
    {
        Poisson p = new Poisson( espece, x, y );
        this.grillePoisson[x][y] = p;
        this.lstPoisson.add( p );
    }

    // Méthodes pour obtenir un poisson à une position donnée et pour obtenir la grille des poissons

    public Poisson getPoisson( int x, int y )
    {
        return this.grillePoisson[x][y];
    }

    // Méthode pour obtenir la grille des poissons

    public Poisson[][] getGrillePoisson()
    {
        return this.grillePoisson;
    }

    // Méthode pour obtenir la liste de tous les poissons

    public List<Poisson> getLstPoisson()
    {
        return this.lstPoisson;
    }

    // Méthode pour supprimer un poisson à une position donnée

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

    //méthodes pour obtenir les liaisons d'un poisson à une position donnée

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

    // Méthode pour obtenir la liste de toutes les liaisons

    public List<Liaison> getLstLiaisons()
    {
        return this.lstLiaisons;
    }

    // Méthode pour vérifier si deux poissons sont liés

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

    // Méthode de positionnement d'une zone dans la grille

    public void positionneZone( int indiceX, int indiceY, int numZone )
    {
        Zone z = new Zone( numZone );
        this.grilleZone[indiceX][indiceY] = z;
    }
    
    // Méthode pour obtenir une zone à une position donnée

    public Zone getZone( int x, int y )
    {
        return this.grilleZone[x][y];
    }

    // Méthode pour obtenir la grille des zones

    public Zone[][] getGrilleZone()
    {
        return this.grilleZone;
    }

    // Méthodes de sauvegarde
    
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
                writer.write( l.getP1().getEspece() + " (" + l.getP1().getX() + ", " + l.getP1().getY() + ") <-> "
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

        for (int y = 0; y < grilleLiaisons[0].length; y++)
        {
            for (int x = 0; x < grilleLiaisons.length; x++)
            {
                sb.append(grilleLiaisons[x][y]).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
