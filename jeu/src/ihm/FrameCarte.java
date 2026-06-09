package src.ihm;

public class FrameCarte extends JFrame
{
    // Attributs
    private Controleur ctrl;
    private PanelCarte panelCarte;

    // Constructeurs de la frame
    public FrameCarte(Controleur ctrl)
    {

        this.panelCarte = new PanelCarte( ctrl );

        this.add( this.panelCarte );
        this.setVisible( true );
    }
}