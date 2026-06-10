package src.ihm;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.Controleur;

public class PanelMenu extends JPanel implements ActionListener
{
	private JButton		btnValider;
	private JButton		btnAnnuler;

	private JTextField	txtLongueur;
	private JTextField	txtLargeur;
	private JTextField	txtNbSymbole;
	private JTextField  txtNbLabo;		
	private JTextField	txtTailleCase;

	private JLabel		lblMessage;

	private Controleur	ctrl;


	public PanelMenu(Controleur ctrl)
	{
		JPanel	panelLongueur;
		JPanel	panelLargeur;
		JPanel	panelSymboles;
		JPanel  panelLabo;
		JPanel	panelTaille;
		JPanel	panelAction;


		this.ctrl = ctrl;


		this.setLayout( new GridLayout( 0, 1 ) );
		/* Création des composants */

		this.btnAnnuler		= new JButton( "Annuler" );
		this.btnValider		= new JButton( "Valider" );


		this.txtLongueur	= new JTextField( 10 );
		this.txtLargeur		= new JTextField( 10 );
		this.txtNbSymbole	= new JTextField( 10 );
		this.txtNbLabo      = new JTextField( 10 );
		this.txtTailleCase	= new JTextField( 10 );

		this.lblMessage		= new JLabel( "" );
		this.lblMessage.setForeground( Color.RED );

		panelLongueur	= new JPanel();
		panelLargeur	= new JPanel();
		panelSymboles	= new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		panelLabo    	= new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		panelTaille		= new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		panelAction		= new JPanel();


		/* Position des composants */
		this.add( new JLabel( "Taille :" ) );
		panelLongueur.add( new JLabel( "Nombre de colonnes :" ) );
		panelLongueur.add( this.txtLongueur );
		this.add( panelLongueur );

		panelLargeur.add( new JLabel( "Nombre de lignes :" ) );
		panelLargeur.add( this.txtLargeur );
		this.add( panelLargeur );

		panelSymboles.add( new JLabel( "Nombre d'especes de poissons :" ) );
		panelSymboles.add( this.txtNbSymbole );
		this.add( panelSymboles );

		panelLabo.add( new JLabel( "Nombre de laboratoires :" ) );
		panelLabo.add( this.txtNbLabo );
		this.add( panelLabo );

		panelTaille.add( new JLabel( "Taille des cases :" ) );
		panelTaille.add( this.txtTailleCase );
		this.add( panelTaille );

		panelAction.add( this.btnValider );
		panelAction.add( this.btnAnnuler );

		this.add( this.lblMessage );

		this.add( panelAction );

		/* Activation des composants */

		this.btnValider.addActionListener( this );
		this.btnAnnuler.addActionListener( this );

		this.txtLongueur.addActionListener( this );
		this.txtLargeur.addActionListener( this );
		this.txtNbSymbole.addActionListener( this );
		this.txtTailleCase.addActionListener( this );


		this.setVisible( true );

	}


	// Réinitialise tout le panel
	public void reinitialierPanel()
	{
		this.txtLongueur.setText( "" );
		this.txtLargeur.setText( "" );
		this.txtNbSymbole.setText( "" );
		this.txtTailleCase.setText( "" );
	}

	// Envoie toutes les informations en fonctions des valeurs


	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnValider )
		{
			if ( !this.txtLongueur.getText().isEmpty() && !this.txtLargeur.getText().isEmpty()
				&& !this.txtNbSymbole.getText().isEmpty() && !this.txtTailleCase.getText().isEmpty() )
			{
				try
				{
					int	longueur	= Integer.parseInt( this.txtLongueur.getText() );
					int	largeur		= Integer.parseInt( this.txtLargeur.getText() );
					int	nbSymbole	= Integer.parseInt( this.txtNbSymbole.getText() );
					int nbLabo     = Integer.parseInt( this.txtNbLabo.getText() );
					int	tailleCase	= Integer.parseInt( this.txtTailleCase.getText() );

					if ( nbSymbole > 7 )
					{
						this.lblMessage.setText( "Le nombre de symbole doit être inférieur ou égal à 7." );
						return;
					} else
					{
						this.ctrl.initialiserGrille( longueur, largeur, nbSymbole, tailleCase, nbLabo );
					}

				}

				catch (Exception exception)
				{
					this.lblMessage.setText( "Un des champs n'est pas remplie par un nombre" );
					return;
				}


			}

			else
			{
				this.lblMessage.setText( "Veuillez remplir tous les champs." );
			}
		}
		if ( e.getSource() == this.btnAnnuler )
		{
			this.reinitialierPanel();
		}

	}

}
