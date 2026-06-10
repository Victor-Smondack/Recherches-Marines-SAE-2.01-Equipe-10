package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

	private JButton			btnValider;

	private String[]		tabEspece;
	private Controleur		ctrl;
	private JToggleButton	dernierBoutonPresse	= null;
	private int				numZoneActive		= 1;
	private int				numLaboActive		= 1;


	// Création du panel des choix de positionnement poissons labo zone
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
			Image			imgPoisson	= new ImageIcon( "./src/ihm/images/poissons/" + tabEspece[i] + ".png" ).getImage().getScaledInstance( 50, 50,
				Image.SCALE_SMOOTH );
			JToggleButton	button		= new JToggleButton( new ImageIcon( imgPoisson ) );
			button.setBackground( new Color( 150, 150, 150 ) );

			this.btngChoix.add( button );
			pnlSymbole.add( button );
			button.addActionListener( this );
			this.tabTgbPoisson[i] = button;
		}

		Image imgGomme = new ImageIcon( "./src/ihm/images/Gomme.png" ).getImage().getScaledInstance( 50, 50, Image.SCALE_SMOOTH );
		this.tgbGomme = new JToggleButton( new ImageIcon( imgGomme ) );
		this.tgbGomme.setBackground( new Color( 150, 150, 150 ) );
		this.btngChoix.add( this.tgbGomme );
		pnlSymbole.add( this.tgbGomme );

		Image	flecheGauche	= new ImageIcon( "./src/ihm/images/flecheGauche.png" ).getImage().getScaledInstance( 50, 50, Image.SCALE_SMOOTH );

		Image	flecheDroite	= new ImageIcon( "./src/ihm/images/flecheDroite.png" ).getImage().getScaledInstance( 50, 50, Image.SCALE_SMOOTH );

		JPanel	pnlZoneGlobal	= new JPanel( new BorderLayout() );
		JPanel	pnlZoneAction	= new JPanel();
		this.btnGaucheZone	= new JButton( new ImageIcon( flecheGauche ) );
		this.tgbZone		= new JToggleButton( "Mer " + this.numZoneActive );
		this.btnDroiteZone	= new JButton( new ImageIcon( flecheDroite ) );

		this.btngChoix.add( this.tgbZone );

		pnlZoneAction.add( this.btnGaucheZone );
		pnlZoneAction.add( this.tgbZone );
		pnlZoneAction.add( this.btnDroiteZone );

		this.lblZone = new JLabel( " " );
		this.lblZone.setOpaque( true );
		this.lblZone.setBackground( this.ctrl.getCouleur( 1 ) );
		this.lblZone.setPreferredSize( new Dimension( 0, 30 ) );

		pnlZoneGlobal.add( this.lblZone, BorderLayout.NORTH );
		pnlZoneGlobal.add( pnlZoneAction, BorderLayout.CENTER );

		JPanel	pnlLaboGlobal	= new JPanel( new BorderLayout() );
		JPanel	pnlLaboAction	= new JPanel();
		this.btnGaucheLabo	= new JButton( new ImageIcon( flecheGauche ) );
		this.tgbLabo		= new JToggleButton( "Labo " + this.numZoneActive );
		this.btnDroiteLabo	= new JButton( new ImageIcon( flecheDroite ) );

		this.btngChoix.add( this.tgbLabo );

		pnlLaboAction.add( this.btnGaucheLabo );
		pnlLaboAction.add( this.tgbLabo );
		pnlLaboAction.add( this.btnDroiteLabo );

		this.lblLabo = new JLabel( " " );
		this.lblLabo.setOpaque( true );
		this.lblLabo.setBackground( this.ctrl.getCouleur( 10 ) );
		this.lblLabo.setPreferredSize( new Dimension( 0, 30 ) );

		pnlLaboGlobal.add( this.lblLabo, BorderLayout.NORTH );
		pnlLaboGlobal.add( pnlLaboAction, BorderLayout.CENTER );

		JPanel pnlBas = new JPanel( new BorderLayout() );
		pnlBas.add( pnlLaboGlobal, BorderLayout.CENTER );

		this.btnValider = new JButton( "Valider" );
		this.btnValider.setPreferredSize( new Dimension( 0, 40 ) );
		pnlBas.add( this.btnValider, BorderLayout.SOUTH );

		this.add( pnlSymbole, BorderLayout.NORTH );
		this.add( pnlZoneGlobal, BorderLayout.CENTER );
		this.add( pnlBas, BorderLayout.SOUTH );

		this.tgbZone.addActionListener( this );
		this.btnGaucheZone.addActionListener( this );
		this.btnDroiteZone.addActionListener( this );
		this.tgbGomme.addActionListener( this );

		this.tgbLabo.addActionListener( this );
		this.btnGaucheLabo.addActionListener( this );
		this.btnDroiteLabo.addActionListener( this );

		this.btnValider.addActionListener( this );

		this.setVisible( true );
	}


	// Retourne l'image d'un poisson à partir de l'indice du boutton de poissson
	public ImageIcon getImagePoisson( int i )
	{
		if ( i >= 0 && i < this.tabTgbPoisson.length )
		{
			return (ImageIcon) this.tabTgbPoisson[i].getIcon();
		}
		return null;
	}


	// Retourne la zone qui est choisi
	public int getNumZoneActive()
	{
		return this.numZoneActive;
	}


	// Retourne si la gomm est active ou pas
	public boolean isGommeActive()
	{
		return this.tgbGomme.isSelected();
	}


	// Commande en fonction du bouton
	@Override
	public void actionPerformed( ActionEvent e )
	{
		// Si le bouton valider est cliqué on sauvegarde
		if ( e.getSource() == this.btnValider )
		{
			this.ctrl.Sauvergarder();
			return;
		}

		// Si le bouton est les flèches des zone on change le numéro de la zone
		// qu'on place'
		if ( e.getSource() == this.btnGaucheZone || e.getSource() == this.btnDroiteZone )
		{
			if ( e.getSource() == this.btnGaucheZone && this.numZoneActive > 1 )
			{
				this.numZoneActive--;
			} else if ( e.getSource() == this.btnDroiteZone && this.ctrl.zoneExiste( this.numZoneActive ) && this.numZoneActive < 10 )
			{
				this.numZoneActive++;
			}
			this.tgbZone.setText( "Mer " + this.numZoneActive );
			this.lblZone.setBackground( this.ctrl.getCouleur( this.numZoneActive ) );
			this.ctrl.setZoneActive( this.numZoneActive );
			return;
		}


		// Si le bouton est les flèches des zone on change le numéro du labo
		// qu'on place
		if ( e.getSource() == this.btnGaucheLabo || e.getSource() == this.btnDroiteLabo )
		{
			if ( e.getSource() == this.btnGaucheLabo && this.numLaboActive > 1 )
			{
				this.numLaboActive--;
			} else if ( e.getSource() == this.btnDroiteLabo && this.numLaboActive < this.tabTgbPoisson.length )
			{
				this.numLaboActive++;
			}
			this.tgbLabo.setText( "Labo " + this.numLaboActive );
			this.lblLabo.setBackground( this.ctrl.getCouleur( 9 + this.numLaboActive ) );
			this.ctrl.setLaboActive( this.numLaboActive );
			return;
		}

		JToggleButton boutonClique = (JToggleButton) e.getSource();

		// Permet de déselectionné si on reclique sur le bouton qu'on a
		// selectionné'
		if ( boutonClique == this.dernierBoutonPresse )
		{
			this.btngChoix.clearSelection();
			this.ctrl.setPoissonSelect( -1 );
			this.ctrl.setZoneSelect( false );
			this.ctrl.setGommeSelect( false );
			this.ctrl.setLaboSelect( false );
			this.dernierBoutonPresse = null;
		} else
		{
			this.dernierBoutonPresse = boutonClique;

			this.ctrl.setPoissonSelect( -1 );
			this.ctrl.setZoneSelect( false );
			this.ctrl.setGommeSelect( false );
			this.ctrl.setLaboSelect( false );

			if ( boutonClique == this.tgbZone )
			{
				this.ctrl.setZoneSelect( true );
			} else
			{
				if ( boutonClique == this.tgbLabo )
				{
					this.ctrl.setLaboSelect( true );
				} else
				{
					if ( boutonClique == this.tgbGomme )
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
	}
}
