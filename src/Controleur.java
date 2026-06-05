package src;

import java.awt.Color;

import src.ihm.FrameMenu;
import src.ihm.FrameTable;
import src.metier.Couleur;
import src.metier.Metier;
import src.metier.Plateau;
import src.metier.Poisson;
import src.metier.Zone;

public class Controleur
{
    // --- ATTRIBUTS IHM ---
    private int        yGrille    = 7;
    private int        xGrille    = 7;
    private int        zoneActive = 1;
    private FrameMenu  frameMenu;
    private FrameTable frameTable;
    private Metier     metier;
    private Plateau    plateau;
    private boolean    gommeActive = false;


    public Controleur()
    {

        this.metier    = new Metier( this.yGrille, this.xGrille );
        this.frameMenu = new FrameMenu( this );
    }


    // --- MÉTHODES IHM ---
    public void initialiserGrille( int longueur, int largeur, int nbSymbole, int tailleCase )
    {
        this.frameTable = new FrameTable( this, longueur, largeur, tailleCase );
        this.frameMenu.changerPanel( nbSymbole );
    }


    public void getImagePoisson( int i )
    {
        this.frameMenu.getImagePoisson( i );
    }

    public void setGommeSelect( boolean select )
    {
        this.gommeActive = select;
    }

    public void getGommeSelect()
    {
        return this.gommeActive;
    }

    public Color getCouleur( int codeCouleur )
    {
        Couleur c = Couleur.valueOf( codeCouleur );
        if ( c != null )
        {
            return new Color( c.getR(), c.getV(), c.getB(), 128 );
        }

        return new Color( 180, 230, 255, 128 );
    }


    public String[] getPoissons()
    {
        return this.metier.getEspeces();
    }


    public String getPoissonSelect() // Envoyer vers l'IHM le poisson
                                     // sélectionné
    {
        return this.metier.getPoissonSelect();
    }


    public void setZoneSelect( boolean select )
    {
        this.metier.setZoneSelect( select );
    }


    public boolean isZoneSelect()
    {
        return this.metier.isZoneSelect();
    }


    public void setPoissonSelect( int numEspece ) // Envoyer vers le métier le
                                                  // poisson sélectionné
    {
        this.metier.setPoissonSelect( numEspece );
    }


    public String[] getEspeces()
    {
        return this.metier.getEspeces();
    }


    public Zone[][] getGrilleZone()
    {
        return this.plateau.getGrilleZone();
    }


    public Poisson[][] getGrillePoisson()
    {
        return this.plateau.getGrillePoisson();
    }


    public boolean deplacement( int xDest, int yDest )
    {
        if ( (xDest >= 0) &&
            (xDest < this.xGrille) && (yDest >= 0) && (yDest < this.yGrille) )
        {
            deplacer( xDest, yDest );
            return true;
        } else
        {
            return false;
        }
    }


    private void deplacer( int xDest, int yDest )
    {
        /* Rien */
    }


    public void genererLiaisons()
    {
        this.plateau.genererLiaisons();
    }


    public boolean zoneExiste( int numZone )
    {
        return this.metier.zoneExiste( numZone );
    }


    public void positionZone( int indiceX, int indiceY, int numZone )
    {
        this.metier.positionZone( indiceX, indiceY, numZone );
    }


    public int getZoneActive()
    {
        return this.zoneActive;
    }


    public void setZoneActive( int zoneActive )
    {
        this.zoneActive = zoneActive;
    }


    // --- MAIN ---
    public static void main( String[] args )
    {
        new Controleur();
    }
}
