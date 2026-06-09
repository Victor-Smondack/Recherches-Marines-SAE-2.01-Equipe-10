package src.ihm;

import javax.swing.JFrame;

import src.Controleur;

public class FrameTable extends JFrame
{
	private Controleur ctrl;

	public FrameTable(Controleur ctrl, int longueur, int largeur, int tailleCase)
	{
		this.ctrl = ctrl;

		int	longueurFrame	= (longueur * tailleCase);
		int	largeurFrame	= (largeur * tailleCase);

		this.setTitle( "Jeu" );
		this.setSize( longueurFrame, largeurFrame );
		this.setLocation( 450, 50 );

		// Création et ajout du Panel
		this.add( new PanelTable( this.ctrl, longueur, largeur, tailleCase ) );


		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.setVisible( true );

	}
}
