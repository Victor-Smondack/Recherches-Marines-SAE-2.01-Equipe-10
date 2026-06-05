package src.ihm;

import javax.swing.JFrame;

import src.Controleur;

public class FrameMenu extends JFrame
{
	private PanelMenu	panelActif;
	private Controleur	ctrl;

	public FrameMenu(Controleur ctrl)
	{
		this.setTitle( "Menu" );
		this.setSize( 400, 400 );
		this.setLocation( 50, 50 );

		this.ctrl		= ctrl;
		// Création et ajout du Panel


		this.panelActif	= new PanelMenu( ctrl );
		this.add( panelActif );

		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.setVisible( true );

	}


	public void changerPanel( int nbSymbole )
	{
		this.remove( this.panelActif );
		this.add( new PanelChoix( this.ctrl, nbSymbole ) );
		this.revalidate();
		this.repaint();
	}
}
