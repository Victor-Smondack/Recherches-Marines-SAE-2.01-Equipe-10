package src;

import src.ihm.FrameMenu;

public class Controleur
{
	private FrameMenu frameMenu;

	public Controleur()
	{
		this.frameMenu = new FrameMenu( this );
	}


	public static void main( String[] args )
	{
		new Controleur();
	}
}
