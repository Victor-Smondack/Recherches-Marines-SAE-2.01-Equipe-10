package src.ihm;

import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.*;

import src.Controleur;

public class PanelSauvegarde extends JPanel implements ActionListener
{
    private JButton         btnOk;
    private JButton         btnRetour;
    private FrameSauvegarde frameSauvegarde;
    private Controleur      ctrl;

    public PanelSauvegarde(FrameSauvegarde frameSauvegarde, Controleur ctrl)
    {
        JPanel panelBouton;
        
        this.frameSauvegarde = frameSauvegarde;
        this.ctrl            = ctrl;

        this.setLayout( new GridLayout( 0, 1 ) );


        //Création des Composants
        panelBouton   = new JPanel();

        this.btnOk     = new JButton ( "Ok"   );
        this.btnRetour = new JButton ("Retour");
        

        //Positionnement des composants

        this.add( new JLabel( "Etes-vous sur d'enregistrez votre plateau ?", javax.swing.SwingConstants.CENTER ) );
        
        panelBouton.add( btnOk       );
        panelBouton.add( btnRetour   );
        this       .add( panelBouton );

        //Actionnement des composants

        this.btnOk    .addActionListener( this );
        this.btnRetour.addActionListener( this );
    }

    @Override
	public void actionPerformed( ActionEvent e )
	{
        if ( e.getSource() == this.btnOk )
        {
            this.ctrl.sauvegarder();
            this.ctrl.getFrameMenu ().dispose();
            this.ctrl.getFrameTable().dispose();
            this.frameSauvegarde     .dispose();
            
        }
        if ( e.getSource() == this.btnRetour )
        {
            this.frameSauvegarde.setVisible(false);
        }
    }
}