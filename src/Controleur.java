package src;

import java.awt.Color;
import java.util.List;

import src.ihm.FrameMenu;
import src.ihm.FrameTable;
import src.ihm.PanelChoix;
import src.metier.Couleur;
import src.metier.Liaison;
import src.metier.Metier;
import src.metier.Poisson;
import src.metier.Zone;

public class Controleur
{
    private int        yGrille     = 1;
    private int        xGrille     = 1;
    private int        zoneActive  = 1;
    private FrameMenu  frameMenu;
    private FrameTable frameTable;
    private Metier     metier;
    private boolean    gommeActive = false;
    private PanelChoix panelChoix;


    public Controleur()
    {
        this.frameMenu = new FrameMenu( this );
    }


    public void initialiserGrille( int longueur, int largeur, int nbSymbole, int tailleCase )
    {
        this.yGrille = longueur;
        this.xGrille = largeur;
        this.metier    = new Metier( this.yGrille, this.xGrille );
        this.frameTable = new FrameTable( this, longueur, largeur, tailleCase );
        this.frameMenu.changerPanel( nbSymbole );
    }


    public void setPanelChoix( PanelChoix panelChoix )
    {
        this.panelChoix = panelChoix;
    }


    public void getImagePoisson( int i )
    {
        this.frameMenu.getImagePoisson( i );
    }


    public void setGommeSelect( boolean select )
    {
        this.gommeActive = select;
    }


    public boolean getGommeSelect()
    {
        if ( this.panelChoix != null )
        {
            return this.panelChoix.isGommeActive();
        }
        return this.gommeActive;
    }


    public void gommer( int x, int y )
    {
        this.metier.gommer( x, y );
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


    public String getPoissonSelect()
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


    public void setPoissonSelect( int numEspece )
    {
        this.metier.setPoissonSelect( numEspece );
    }


    public String[] getEspeces()
    {
        return this.metier.getEspeces();
    }


    public Zone[][] getGrilleZone()
    {
        return this.metier.getGrilleZone();
    }


    public void positionneZone( int indiceX, int indiceY, int numZone )
    {
        this.metier.positionneZone( indiceX, indiceY, numZone );
    }


    public Poisson[][] getGrillePoisson()
    {
        return this.metier.getGrillePoisson();
    }


    public void positionnePoisson( int indiceX, int indiceY, String espece )
    {
        this.metier.positionnePoisson( indiceX, indiceY, espece );
        this.metier.positionPoisson( indiceX, indiceY, espece );
    }


    public boolean deplacement( int xDest, int yDest )
    {
        if ( (xDest >= 0) && (xDest < this.xGrille) && (yDest >= 0) && (yDest < this.yGrille) )
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
    }


    public void genererLiaisons()
    {
        this.metier.genererLiaisons();
    }


    public int[][] getCoordonneesLiaisons()
    {
        if ( this.metier == null || this.metier.getLstLiaisons() == null )
        {
            return null;
        }

        List<Liaison> lias   = this.metier.getLstLiaisons();
        int[][]       coords = new int[lias.size()][4];

        for ( int i = 0; i < lias.size(); i++ )
        {
            Liaison l = lias.get( i );
            coords[i][0] = l.getP1().getX();
            coords[i][1] = l.getP1().getY();
            coords[i][2] = l.getP2().getX();
            coords[i][3] = l.getP2().getY();
        }

        return coords;
    }


    public boolean zoneExiste( int numZone )
    {
        return this.metier.zoneExiste( numZone );
    }


    public int getZoneActive()
    {
        return this.zoneActive;
    }


    public void setZoneActive( int zoneActive )
    {
        this.zoneActive = zoneActive;
    }


    public static void main( String[] args )
    {
        new Controleur();
    }
}
