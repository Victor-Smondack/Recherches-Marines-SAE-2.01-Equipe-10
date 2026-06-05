package src.ihm;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import src.Controleur;

public class FrameMenu extends JFrame
{
	private PanelMenu	panelMenu;
	private PanelChoix	panelChoix;	// Référence conservée ici
	private Controleur	ctrl;

	public FrameMenu(Controleur ctrl)
	{
		this.setTitle( "Menu" );
		this.setSize( 400, 400 );
		this.setLocation( 50, 50 );

		this.ctrl		= ctrl;

		this.panelMenu	= new PanelMenu( ctrl );
		this.add( panelMenu );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}


	public void changerPanel( int nbSymbole )
	{
		this.remove( this.panelMenu );

		this.panelChoix = new PanelChoix( this.ctrl, nbSymbole );
		this.add( this.panelChoix );

		this.revalidate();
		this.repaint();
	}


	public ImageIcon getImagePoisson( int i )
	{
		if ( this.panelChoix != null )
		{
			return this.panelChoix.getImagePoisson( i );
		}
		return null;
	}
}
