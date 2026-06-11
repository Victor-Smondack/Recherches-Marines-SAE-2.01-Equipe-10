package src.ihm;

import javax.swing.JFrame;

import src.Controleur;

public class FrameSauvegarde extends JFrame
{
    private PanelSauvegarde panelSauvegarde;
    private Controleur      ctrl;


    public FrameSauvegarde( Controleur ctrl)
    {
        this.ctrl = ctrl;
        
        this.setTitle   ( "Sauvegarde" );
		this.setSize    ( 400, 400 );
		this.setLocation( 450, 50 );


        this.panelSauvegarde = new PanelSauvegarde(this, this.ctrl);

        this.add( panelSauvegarde );

        this.setVisible(true);
    }

}