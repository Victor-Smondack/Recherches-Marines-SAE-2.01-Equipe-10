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

    // Constructeur créant la fenêtre d'affichage pour le tirage des cartes
    public FrameTirage(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle   ( "Tirage" );
		this.setSize    ( 250,500 );
		this.setLocation( 20, 50 );

        
        this.panelTirage = new PanelTirage( ctrl );

        this.add( this.panelTirage );
        this.setVisible( true );
    }
}