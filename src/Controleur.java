package src;

import metier.*;

public class Controleur 
{
    //private FrameMenu ihm;
	/*private Metier  metier;

    private int yGrille = 7;
    private int xGrille = 7;

	public Controleur ()
	{
		this.metier = new Metier ();
		//this.ihm    = new FrameMenu(this);
	}

    public String getPoissonSelect() // Envoyer vers l'IHM le poisson sélectionné
    {
        return this.metier.getPoissonSelect();
    }

    public void setPoissonSelect(int numEspece) // Envoyer vers le métier le poisson sélectionné
    {
        this.metier.setPoissonSelect(numEspece);
    }

    public String[] getEspeces()
    {
        return this.metier.getEspeces();
    }

    public boolean deplacement(int xDest, int yDest)
    {
        if ((xDest >= 0) && (xDest < this.xGrille) && (yDest >= 0) && (yDest < this.yGrille))
        {
            deplacer(xDest, yDest);
            return true;
        }
        else
        {
            return false;
        }
    }

    private void deplacer(int xDest, int yDest)
    {
        
    }*/

    private Plateau plateau;

    public Controleur()
    {
        this.plateau = new Tableau();
    }

    public void genererLiaisons()
    {
        this.plateau.genererLiaisons();
    }

}