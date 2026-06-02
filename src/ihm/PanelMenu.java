package src.ihm;

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
	private JTextField	txtTailleCase;

	private Controleur	ctrl;


	public PanelMenu(Controleur ctrl)
	{
		JPanel	panelLongueur;
		JPanel	panelLargeur;
		JPanel	panelSymboles;
		JPanel	panelTaille;
		JPanel	panelAction;


		this.ctrl = ctrl;


		this.setLayout( new GridLayout( 7, 1 ) );
		/* Création des composants */

		this.btnAnnuler		= new JButton( "Annuler" );
		this.btnValider		= new JButton( "Valider" );


		this.txtLongueur	= new JTextField( 10 );
		this.txtLargeur		= new JTextField( 10 );
		this.txtNbSymbole	= new JTextField( 10 );
		this.txtTailleCase	= new JTextField( 10 );

		panelLongueur		= new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		panelLargeur		= new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		panelSymboles		= new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		panelTaille			= new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		panelAction			= new JPanel( new FlowLayout( FlowLayout.LEFT ) );


		/* Position des composants */
		this.add( new JLabel( "Taille :" ) );
		panelLongueur.add( new JLabel( "Longueur :" ) );
		panelLongueur.add( this.txtLongueur );

		this.add( panelLongueur );
		panelLargeur.add( new JLabel( "Largeur :" ) );
		panelLargeur.add( this.txtLargeur );

		this.add( panelLargeur );
		panelSymboles.add( new JLabel( "Nombre de couleurs / symboles :" ) );
		panelSymboles.add( this.txtNbSymbole );

		this.add( panelSymboles );
		panelTaille.add( new JLabel( "Taille des cases :" ) );
		panelTaille.add( this.txtTailleCase );

		this.add( panelTaille );
		panelAction.add( this.btnValider );
		panelAction.add( this.btnAnnuler );

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


	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnValider )
		{
			/* A préciser */
		}
		if ( e.getSource() == this.btnAnnuler )
		{
			/* A préciser */
		}

	}

}
