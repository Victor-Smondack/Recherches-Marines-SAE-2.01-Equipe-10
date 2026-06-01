package ihm;

public class FrameMenu
{
    public FrameMenu ( Controleur ctrl )
	{
		this.setTitle   ( "Parametres");
		this.setSize    ( 640,660 );
		this.setLocation(  50, 50 );

		// Création et ajout du Panel
		this.add ( new PanelMenu( ctrl ) );


		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}
}
