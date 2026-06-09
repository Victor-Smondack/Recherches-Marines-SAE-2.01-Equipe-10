package src.ihm;

import javax.swing.*;
import java.awt.event.*;
import src.ihm.*;
import src.Controleur;


public class FrameTirage extends JFrame
{
    // Attributs
    private Controleur ctrl;
    private PanelTirage panelTirage;

    // Constructeurs de la frame
    public FrameTirage(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle   ( "Tirage" );
		this.setSize    ( 200,500 );
		this.setLocation( 100, 50 );

        
        this.panelTirage = new PanelTirage( ctrl );

        this.add( this.panelTirage );
        this.setVisible( true );
    }
}