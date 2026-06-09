package src.ihm;

public class FrameCarte extends JFrame
{
    // Attributs
    private Controleur ctrl;
    private PanelCarte panelCarte;

    // Constructeurs de la frame
    public FrameCarte(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle   ( "Carte" );
		this.setSize    ( 100,300 );
		this.setLocation( 100, 50 );

        
        this.panelCarte = new PanelCarte( ctrl );

        this.add( this.panelCarte );
        this.setVisible( true );
    }
}