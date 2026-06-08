package src.ihm;

import java.awt.BorderLayout;
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
	private JButton			btnGaucheZone;
	private JToggleButton	tgbZone;
	private JButton			btnDroiteZone;

	private JLabel			lblLabo;
	private JButton			btnGaucheLabo;
	private JToggleButton	tgbLabo;
	private JButton			btnDroiteLabo;
	private JToggleButton	tgbGomme;

	private String[]		tabEspece;
	private Controleur		ctrl;
	private JToggleButton	dernierBoutonPresse	= null;
	private int				numZoneActive		= 1;


	public PanelChoix(Controleur ctrl, int nbSymbole)
	{
		this.ctrl = ctrl;
		this.ctrl.setPanelChoix( this );

		this.tabEspece = this.ctrl.getEspeces();
		this.setLayout( new BorderLayout() );

		this.btngChoix		= new ButtonGroup();
		this.tabTgbPoisson	= new JToggleButton[nbSymbole];


		JPanel pnlSymbole = new JPanel( new GridLayout( 0, 4 ) );


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
		this.btnGaucheZone	= new JButton( new ImageIcon( flecheGauche ) );
		this.tgbZone		= new JToggleButton( "Zone " + this.numZoneActive );
		this.btnDroiteZone	= new JButton( new ImageIcon( flecheDroite ) );
		this.tgbGomme		= new JToggleButton( "Gomme" );

		this.btngChoix.add( this.tgbZone );
		this.btngChoix.add( this.tgbGomme );


		pnlZone.add( this.btnGaucheZone );
		pnlZone.add( this.tgbZone );
		pnlZone.add( this.btnDroiteZone );

		JPanel pnlBas = new JPanel( new GridLayout( 2, 1 ) );
		pnlBas.add( pnlZone );
		pnlBas.add( this.tgbGomme );

		this.add( pnlSymbole, BorderLayout.NORTH );
		this.add( this.lblZone, BorderLayout.CENTER );
		this.add( pnlBas, BorderLayout.SOUTH );


		this.tgbZone.addActionListener( this );
		this.btnGaucheZone.addActionListener( this );
		this.btnDroiteZone.addActionListener( this );
		this.tgbGomme.addActionListener( this );


		this.setVisible( true );
	}


	public ImageIcon getImagePoisson( int i )
	{
		if ( i >= 0 && i < this.tabTgbPoisson.length )
		{
			return (ImageIcon) this.tabTgbPoisson[i].getIcon();
		}
		return null;
	}


	public int getNumZoneActive()
	{
		return this.numZoneActive;
	}


	public boolean isGommeActive()
	{
		return this.tgbGomme.isSelected();
	}


	@Override
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnGaucheZone || e.getSource() == this.btnDroiteZone )
		{
			if ( e.getSource() == this.btnGaucheZone && this.numZoneActive > 1 )
			{
				this.numZoneActive--;
			} else if ( e.getSource() == this.btnDroiteZone && this.ctrl.zoneExiste( this.numZoneActive )
				&& this.numZoneActive < 10 )
			{
				this.numZoneActive++;
			}
			this.tgbZone.setText( "Zone " + this.numZoneActive );
			this.lblZone.setBackground( this.ctrl.getCouleur( this.numZoneActive ) );
			this.ctrl.setZoneActive( this.numZoneActive );
			return;
		}

		JToggleButton boutonClique = (JToggleButton) e.getSource();

		if ( boutonClique == this.dernierBoutonPresse )
		{
			this.btngChoix.clearSelection();
			this.ctrl.setPoissonSelect( -1 );
			this.ctrl.setZoneSelect( false );
			this.ctrl.setGommeSelect( false );
			this.dernierBoutonPresse = null;
		}

		else
		{
			this.dernierBoutonPresse = boutonClique;

			this.ctrl.setPoissonSelect( -1 );
			this.ctrl.setZoneSelect( false );
			this.ctrl.setGommeSelect( false );

			if ( boutonClique == this.tgbZone )
			{
				this.ctrl.setZoneSelect( true );
			} else if ( boutonClique == this.tgbGomme )
			{
				this.ctrl.setGommeSelect( true );
			} else
			{
				for ( int i = 0; i < this.tabTgbPoisson.length; i++ )
				{
					if ( boutonClique == this.tabTgbPoisson[i] )
					{
						this.ctrl.setPoissonSelect( i );
						break;
					}
				}
			}
		}
	}
}
