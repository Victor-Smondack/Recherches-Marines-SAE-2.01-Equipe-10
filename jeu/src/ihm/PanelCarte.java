package src.ihm;

public PanelCarte extends JPanel
{
    // Attributs
    private Controleur ctrl;

    // Constructeurs du panel
    public PanelCarte( Controleur ctrl )
    {
        this.ctrl = ctrl;
        this.setLayout( new BorderLayout() );
        this.add( new JLabel( "Panel Carte" ), BorderLayout.CENTER );
    }
}