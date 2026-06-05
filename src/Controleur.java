package src;

import java.awt.Color;

import src.ihm.FrameMenu;
import src.ihm.FrameTable;
import src.ihm.PanelChoix;
import src.metier.Couleur;

public class Controleur
{
	private FrameMenu	frameMenu;
	private FrameTable	frameTable;
	private PanelChoix	panelChoix;

	public Controleur()
	{
		this.frameMenu = new FrameMenu( this );
	}


	public void initialiserGrille( int longueur, int largeur, int nbSymbole, int tailleCase )
	{
		this.frameTable = new FrameTable( this, longueur, largeur, tailleCase );
		this.frameMenu.changerPanel( nbSymbole );
	}

	/*
	 * public void getImagePoisson( int i ) { this.panelChoix.getImagePoisson(
	 * int i); }
	 */


	public Color getCouleur( int codeCouleur )
	{
		Couleur c = Couleur.valueOf( codeCouleur );
		if ( c != null )
		{
			return new Color( c.getR(), c.getV(), c.getB(), 128 );
		}

		return new Color( 180, 230, 255, 128 );
	}


	public static void main( String[] args )
	{
		new Controleur();
	}
}
