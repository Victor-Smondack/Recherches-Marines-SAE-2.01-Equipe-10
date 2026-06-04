package src.ihm;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import src.Controleur;

public class PanelChoix extends JPanel implements ActionListener
{
	private ButtonGroup		btngChoix;
	private JToggleButton[]	tabTgbPoisson;
	private JLabel			lblZone;
	private JButton			btnGauche;
	private JToggleButton	tgbZone;
	private JButton			btnDroite;
	private Controleur		ctrl;


	public PanelChoix(Controleur ctrl, int nbSymbole)
	{
		this.ctrl = ctrl;
		this.setLayout( new GridLayout( 3, 1 ) );

		/* Création des composants */
		this.btngChoix		= new ButtonGroup();
		this.tabTgbPoisson	= new JToggleButton[nbSymbole + 1];

		JPanel pnlSymbole = new JPanel();
		for ( int i = 0; i < nbSymbole; i++ )
		{
			Image			imgPoisson	= new ImageIcon( "./src/ihm/images/poissons/" + (i + 1) + ".png" ).getImage()
				.getScaledInstance( 50, 50, Image.SCALE_SMOOTH );
			JToggleButton	button		= new JToggleButton( new ImageIcon( imgPoisson ) );
			button.setBackground( new Color( 150, 150, 150 ) );
			this.btngChoix.add( button );
			pnlSymbole.add( button );
			button.addActionListener( this );
			this.tabTgbPoisson[i] = button;
		}

		this.lblZone = new JLabel( "" );
		this.lblZone.setOpaque( true );
		this.lblZone.setBackground( this.ctrl.getCouleur( 1 ) );

		Image	flecheGauche	= new ImageIcon( "./src/ihm/images/flecheGauche.png" ).getImage()
			.getScaledInstance( 50, 50, Image.SCALE_SMOOTH );

		Image	flecheDroite	= new ImageIcon( "./src/ihm/images/flecheDroite.png" ).getImage()
			.getScaledInstance( 50, 50, Image.SCALE_SMOOTH );

		JPanel	pnlZone			= new JPanel();
		this.btnGauche	= new JButton( new ImageIcon( flecheGauche ) );
		this.tgbZone	= new JToggleButton( "Zone 1" );
		this.btngChoix.add( this.tgbZone );
		this.btnDroite = new JButton( new ImageIcon( flecheDroite ) );


		this.add( pnlSymbole );
		this.add( lblZone );
		pnlZone.add( this.btnGauche );
		pnlZone.add( this.tgbZone );
		pnlZone.add( this.btnDroite );
		this.add( pnlZone );


		this.tgbZone.addActionListener( this );
		this.btnGauche.addActionListener( this );
		this.btnDroite.addActionListener( this );


		this.setVisible( true );
	}


	// public ImageIcon getImagePoisson(int i)
	// {
	// return (ImageIcon) this.tabTgbPoisson[i].getIcon();
	// }


	@Override

	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() instanceof JToggleButton && e.getSource() != this.tgbZone )
		{
			for ( int i = 0; i < this.tabTgbPoisson.length; i++ )
			{
				if ( e.getSource() == this.tabTgbPoisson[i] )
				{
					System.out.println( "Poisson " + (i + 1) + " sélectionné" );
					// this.ctrl.setPoissonSelect( i );
				}
			}

		}

		else
		{
			if ( e.getSource() == this.tgbZone )
			{
				System.out.println( "Zone selectionée" );
			}

		}
	}
}
