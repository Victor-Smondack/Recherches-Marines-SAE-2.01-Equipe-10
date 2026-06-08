package src.metier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.Controleur;

public class Metiertemp
{
    private List<Poisson> lstPoisson;
    private String[]      espece = { "Saumon",
        "Thon",
        "Truite",
        "Sardine",
        "Bar",
        "Colin",
        "Maquereau" };
    private Poisson[][]   grillePoisson;
    private Zone[][]      grilleZone;
    private String        poissonSelect;
    private Metier       plateau;

    private Controleur    ctrl;

    public Metiertemp(int longueur, int largeur)
    {
        this.lstPoisson    = new ArrayList<>();
        this.grillePoisson = new Poisson[longueur][largeur];
        this.grilleZone    = new Zone[longueur][largeur];
    }


    public List<Poisson> getLstPoisson()
    {
        return this.lstPoisson;
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


    public void positionneZone( int indiceX, int indiceY, int numZone )
    {
        Zone z = new Zone( numZone );
        this.grilleZone[indiceX][indiceY] = z;
    }


    public void positionnePoisson( int indiceX, int indiceY, String espece )
    {
        Poisson p = new Poisson( espece, indiceX, indiceY );
        this.grillePoisson[indiceX][indiceY] = p;
    }


    public Poisson[][] getGrillePoisson()
    {
        return this.plateau.getGrillePoisson();
    }


    public Zone[][] getGrilleZone()
    {
        return this.plateau.getGrilleZone();
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


    public String getPoissonSelect()
    {
        return this.poissonSelect;
    }


    public String[] getEspeces()
    {
        return this.espece;
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

    // public String getCouleur(int indice) { return this.couleur[indice]; }


}
