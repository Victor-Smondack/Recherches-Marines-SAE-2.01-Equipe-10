package src.ihm;

import javax.swing.JFrame;

import src.Controleur;


public class FrameTirage extends JFrame
{
    // Attributs
    private Controleur  ctrl;
    private PanelTirage panelTirage;

    public FrameTirage(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle( "Tirage" );
        this.setSize( 250, 500 );
        this.setLocation( 20, 50 );


        this.panelTirage = new PanelTirage( ctrl );

        this.add( this.panelTirage );
        this.setVisible( true );
    }
}
