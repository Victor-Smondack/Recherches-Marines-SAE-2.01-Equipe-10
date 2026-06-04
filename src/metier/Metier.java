package src.metier;

public class Metier 
{
	private List<Poisson> lstPoisson;
    private String[] couleur = {"Rouge", "Orange", "Vert", "Jaune", "Rose", "Magenta", "Noir"};
    private String[] espece = {"Saumon", "Thon", "Truite", "Sardine", "Bar", "Colin", "Maquereau"};
    private int[][] grille;

	public Metier()
	{
		this.lstPoisson = new ArrayList<Poisson>(); 
	}
	
	public List<Poisson> getLstPoisson() { return lstPoisson; }

    public void positionPoisson(int x, int y, String espece, int indiceX, int indiceY)
    {
        Poisson p = new Poisson(espece, x, y, indiceX, indiceY);
        this.grille[x][y] = p;
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
            else ((p.getX() == xDep) && (p.getY() == yDep))
            {
                p.setX(xDest);
                p.setY(yDest);
            }
        }
    }

    public void initialiserGrille(int longueur, int largeur)
    {
        this.grille = new int[longueur][largeur];
    }

    public String getEspece(int indice) { return this.espece[indice]; }
    public String getCouleur(int indice) { return this.couleur[indice]; }
}