package src.ihm;

import src.Controleur;

import javax.swing.*;

import java.awt.BorderLayout;

import java.awt.event.*;

public class PanelDebut extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JButton    btnValider;

    public PanelDebut ( Controleur ctrl )
    {
        this.ctrl = ctrl;

        this.setLayout( new BorderLayout( ) );

        //Création des composants

        this.btnValider = new JButton( "Valider" );

        //Position des Composants

        this.add( btnValider, BorderLayout.NORTH );
    }

    public void actionPerformed(ActionEvent e)
    {
    }
}