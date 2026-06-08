package src.ihm;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import src.Controleur;

public class FrameMenu extends JFrame
{
	private PanelMenu	panelMenu;
	private PanelChoix	panelChoix;
	private Controleur	ctrl;

	// Création d'un controleur qui gére la configuration de la grille
	public FrameMenu(Controleur ctrl)
	{
		this.setTitle( "Configuration de la partie" );
		this.setSize( 400, 400 );
		this.setLocation( 50, 50 );

		this.ctrl		= ctrl;

		this.panelMenu	= new PanelMenu( ctrl );
		this.add( panelMenu );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}


	// Méthode qui permet de changer entre le panel de configuration de la
	// grille et de positionnement
	public void changerPanel( int nbSymbole )
	{
		this.remove( this.panelMenu );

		this.panelChoix = new PanelChoix( this.ctrl, nbSymbole );
		this.add( this.panelChoix );

		this.revalidate();
		this.repaint();
	}


	// Retourne l'image associé à un poissson
	public ImageIcon getImagePoisson( int i )
	{
		if ( this.panelChoix != null )
		{
			return this.panelChoix.getImagePoisson( i );
		}
		return null;
	}
}
