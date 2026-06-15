package src.ihm;

import src.Controleur;

import javax.swing.*;


public class FrameDebut extends JFrame
{
    private Controleur ctrl;
    private PanelDebut panelDebut;

    public FrameDebut ( Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle   ( "Menu du jeu" );
		this.setSize    ( 250,500 );
		this.setLocation( 20, 50 );

        this.panelDebut = new PanelDebut( ctrl );

        this.add( this.panelDebut );
        this.setVisible( true );
    }
}