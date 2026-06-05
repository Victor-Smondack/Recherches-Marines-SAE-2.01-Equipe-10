package src.ihm;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Controleur;

public class PanelTable extends JPanel
{

	private int			longueur;
	private int			largeur;
	private int			tailleCase;

	private JLabel[][]	cases;
	private Controleur	ctrl;

	public PanelTable(Controleur ctrl, int longueur, int largeur, int tailleCase)
	{
		this.longueur	= longueur;
		this.largeur	= largeur;
		this.tailleCase	= tailleCase;
		this.ctrl		= ctrl;

		this.setLayout( new GridLayout( this.longueur, this.largeur ) );

		this.cases = new JLabel[this.longueur][this.largeur];


		GereSouris souris = new GereSouris();

		for ( int i = 0; i < this.longueur; i++ )
		{
			for ( int j = 0; j < this.largeur; j++ )
			{
				this.cases[i][j] = new JLabel();
				this.cases[i][j].setSize( tailleCase, tailleCase );
				this.cases[i][j].setBackground( ctrl.getCouleur( i ) );
				this.cases[i][j].setOpaque( true );
				this.add( this.cases[i][j] );
				this.cases[i][j].addMouseListener( souris );
			}
		}

	}


	private class GereSouris extends MouseAdapter
	{

		@Override
		public void mousePressed( MouseEvent e )
		{
			Component composantClique = (Component) e.getSource();

			if ( composantClique instanceof JLabel lblClique )
			{
				if ( tailleCase < 2 )
				{
					tailleCase = PanelTable.this.getWidth();
				}

				String poissonSelected = this.ctrl.getPoissonSelect();

				lblClique.setIcon(
									new ImageIcon(
										new ImageIcon( "./src/ihm/images/poissons/" + poissonSelected + ".png" )
											.getImage()
											.getScaledInstance(
																tailleCase / 2,
																tailleCase / 2,
																Image.SCALE_SMOOTH ) ) );
				PanelTable.this.repaint();
			}
		}
	}

}
