package src.ihm;

import javax.swing.JFrame;

import src.Controleur;


public class FrameDebut extends JFrame
{
    private Controleur ctrl;
    private PanelDebut panelDebut;

    public FrameDebut(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle( "Menu du jeu" );
        this.setSize( 600, 1000 );
        this.setLocation( 20, 50 );

        this.panelDebut = new PanelDebut( ctrl );

        this.add( this.panelDebut );
        this.setVisible( true );
    }
}
