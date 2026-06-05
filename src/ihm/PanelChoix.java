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
	private JToggleButton	tgbGomme;

	private String[]		tabEspece;
	private Controleur		ctrl;
	private JToggleButton	dernierBoutonPresse	= null;
	private int				numZoneActive		= 1;
	


	public PanelChoix(Controleur ctrl, int nbSymbole)
	{
		this.ctrl		= ctrl;
		this.tabEspece	= this.ctrl.getEspeces();
		this.setLayout( new GridLayout( 4, 1 ) );

		this.btngChoix		= new ButtonGroup();
		this.tabTgbPoisson	= new JToggleButton[nbSymbole + 1];

		JPanel pnlSymbole = new JPanel();


		for ( int i = 0; i < nbSymbole; i++ )
		{
			Image			imgPoisson	= new ImageIcon( "./src/ihm/images/poissons/" + tabEspece[i] + ".png" )
				.getImage()
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
		this.tgbGomme	= new JToggleButton( "Gomme" );


		this.add( pnlSymbole );
		this.add( lblZone );
		pnlZone.add( this.btnGauche );
		pnlZone.add( this.tgbZone );
		pnlZone.add( this.btnDroite );
		this.add( pnlZone );


		this.tgbZone.addActionListener( this );
		this.btnGauche.addActionListener( this );
		this.btnDroite.addActionListener( this );
		this.tgbGomme.addActionListener( this );


		this.setVisible( true );
	}


	public ImageIcon getImagePoisson( int i )
	{
		return (ImageIcon) this.tabTgbPoisson[i].getIcon();
	}


	public int getNumZoneActive()
	{
		return this.numZoneActive;
	}


	@Override
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() instanceof JToggleButton && e.getSource() != this.tgbZone )
		{
			JToggleButton boutonClique = (JToggleButton) e.getSource();

			if ( boutonClique == this.dernierBoutonPresse )
			{
				this.btngChoix.clearSelection();
				this.ctrl.setPoissonSelect( -1 );
				this.dernierBoutonPresse = null;
			}

			else
			{
				for ( int i = 0; i < this.tabTgbPoisson.length; i++ )
				{
					if ( boutonClique == this.tabTgbPoisson[i] )
					{
						this.ctrl.setPoissonSelect( i );
						this.dernierBoutonPresse = boutonClique;
						break;
					}
				}
			}
		} else
		{
			if ( e.getSource() == this.tgbZone )
			{
				JToggleButton boutonZone = (JToggleButton) e.getSource();

				if ( boutonZone == this.dernierBoutonPresse )
				{
					this.btngChoix.clearSelection();
					this.ctrl.setZoneSelect( false );
					this.dernierBoutonPresse = null;
				} else
				{
					this.ctrl.setZoneSelect( true );
					this.dernierBoutonPresse = boutonZone;
				}
			}
			if ( e.getSource() == this.btnGauche && this.numZoneActive > 1 )
			{
				this.numZoneActive--;
				this.tgbZone.setText( "Zone " + this.numZoneActive );
				this.lblZone.setBackground( this.ctrl.getCouleur( this.numZoneActive ) );
				this.ctrl.setZoneActive( this.numZoneActive );
			} else
			{
				if ( e.getSource() == this.btnDroite && this.ctrl.zoneExiste( this.numZoneActive )
					&& this.numZoneActive < 10 )
				{
					this.numZoneActive++;
					this.tgbZone.setText( "Zone " + this.numZoneActive );
					this.lblZone.setBackground( this.ctrl.getCouleur( this.numZoneActive ) );
					this.ctrl.setZoneActive( this.numZoneActive );
				}
			}
			if ( e.getSource() == this.tgbGomme )
			{
				this.ctrl.setGommeSelect( true );
			}
		}
	}
}
