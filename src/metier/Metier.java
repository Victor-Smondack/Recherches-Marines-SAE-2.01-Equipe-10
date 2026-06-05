package src.metier;

import java.util.List;
import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Metier 
{
	private List<Poisson> lstPoisson;
    private String[] espece = {"Saumon", "Thon", "Truite", "Sardine", "Bar", "Colin", "Maquereau"};
    private Poisson[][] grillePoisson;
    private Zones[][] grilleZones;
    private String poissonSelect;

	public Metier()
	{
		this.lstPoisson = new ArrayList<Poisson>(); 
	}
	
	public List<Poisson> getLstPoisson() { return lstPoisson; }

    public void positionPoisson(int x, int y, String espece, int indiceX, int indiceY)
    {
        Poisson p = new Poisson(espece, x, y);
        this.grillePoisson[indiceX][indiceY] = p;
        this.lstPoisson.add(p);
    }

    public void echangerPoisson(int xDep, int yDep, int xDest, int yDest)
    {
        for (Poisson p : this.lstPoisson)
        {
            if ((p.getX() == xDep) && (p.getY() == yDep) && (p.getX() == xDest) && (p.getY() == yDest))
            {
                Poisson ptemp = new Poisson(p.getEspece(), p.getX(), p.getY());
                p.setX(xDest);
                p.setY(yDest);
                ptemp.setX(xDep);
                ptemp.setY(yDep);
            }
            else if ((p.getX() == xDep) && (p.getY() == yDep))
            {
                p.setX(xDest);
                p.setY(yDest);
            }
        }
    }

    public void positionZone(int x, int y)
    {
        Zones z = new Zones(x, y);
        this.grilleZones[(x-25)/50 ][(y-25)/50 ] = z;
    }

    public void initialiserGrillePoisson(int longueur, int largeur)
    {
        this.grillePoisson = new Poisson[longueur][largeur];
    }

    public void initialiserGrille(int longueur, int largeur)
    {
        this.grilleZones = new Zones[longueur][largeur];
    }

    public void sauvegarderGrille(int longueur, int largeur, int nbSymbole, int tailleCases)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("grille.txt"))) 
        {
            writer.write(longueur + " " + largeur + " " + nbSymbole + " " + tailleCases);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    

    public void setPoissonSelect(int numEspece) 
    { 
        switch (numEspece)
        {
            case 0:
                this.poissonSelect = this.espece[0];
                break;
            case 1:
                this.poissonSelect = this.espece[1];
                break;
            case 2:
                this.poissonSelect = this.espece[2];
                break;
            case 3:
                this.poissonSelect = this.espece[3];
                break;
            case 4:
                this.poissonSelect = this.espece[4];
                break;
            case 5:
                this.poissonSelect = this.espece[5];
                break;
            case 6:
                this.poissonSelect = this.espece[6];
                break;
        }
    }

    public String getPoissonSelect() { return this.poissonSelect; }




    public String[] getEspeces() { return this.espece; }
    //public String getCouleur(int indice) { return this.couleur[indice]; }


}