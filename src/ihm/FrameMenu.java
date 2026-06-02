package src.ihm;

import javax.swing.JFrame;

import src.Controleur;

public class FrameMenu extends JFrame
{
	public FrameMenu(Controleur ctrl)
	{
		this.setTitle( "Menu" );
		this.setSize( 400, 900 );
		this.setLocation( 50, 50 );

		// Création et ajout du Panel
		this.add( new PanelMenu( ctrl ) );


		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.setVisible( true );

	}
}
