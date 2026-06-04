package src;

public class Controleur 
{
    private FrameMenu ihm;
	private Metier  metier;

    private int yGrille = 7;
    private int xGrille = 7;

	public Controleur ()
	{
		this.metier = new Metier ();
		this.ihm    = new FrameMenu(this);
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
        
    }

    

}