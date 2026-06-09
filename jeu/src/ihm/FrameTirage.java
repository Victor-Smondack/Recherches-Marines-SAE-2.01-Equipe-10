package src.ihm;

public class FrameTirage extends JFrame
{
    // Attributs
    private Controleur ctrl;
    private PanelCarte panelCarte;

    // Constructeurs de la frame
    public FrameTirage(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle   ( "Tirage" );
		this.setSize    ( 100,300 );
		this.setLocation( 100, 50 );

        
        this.panelCarte = new PanelCarte( ctrl );

        this.add( this.panelCarte );
        this.setVisible( true );
    }
}