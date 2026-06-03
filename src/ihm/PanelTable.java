package src.ihm;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import src.Controleur;

public class PanelTable extends JPanel
{

	private int			longueur;
	private int			largeur;
	private int			tailleCase;

	private Image		imgFond;

	private JPanel[][]	cases;
	private Controleur	ctrl;

	public PanelTable(Controleur ctrl, int longueur, int largeur, int tailleCase)
	{
		this.longueur	= longueur;
		this.largeur	= largeur;
		this.tailleCase	= tailleCase;
		this.ctrl		= ctrl;

		this.imgFond	= new ImageIcon( "./src/ihm/images/Background.png" ).getImage();
		System.out.println( "imgFond = " + this.imgFond );

		this.setLayout( new GridLayout( this.longueur, this.largeur ) );
		this.setOpaque( false );

		this.cases = new JPanel[this.longueur][this.largeur];


		GereSouris souris = new GereSouris();

		for ( int i = 0; i < this.longueur; i++ )
		{
			for ( int j = 0; j < this.largeur; j++ )
			{
				this.cases[i][j] = new JPanel();
				this.cases[i][j].setSize( tailleCase, tailleCase );
				this.cases[i][j].setBackground( ctrl.getCouleur( i ) );
				this.add( this.cases[i][j] );
				this.cases[i][j].addMouseListener( souris );
				this.cases[i][j].addMouseMotionListener( souris );
			}
		}


	}


	private class GereSouris extends MouseAdapter
	{
		/*
		 * public void mouseClicked( MouseEvent e ) { System.out.println(
		 * "Click: " + e.getX() + ", " + e.getY() ); }
		 */

		@Override
		public void mouseDragged( MouseEvent e )
		{

			System.out.println( e );
		}
	}

}
