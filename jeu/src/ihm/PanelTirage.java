package src.ihm;

public class PanelTirage extends JPanel
{
    // Attributs
    private Controleur ctrl;

    // Constructeurs du panel
    public PanelTirage( Controleur ctrl )
    {
        this.ctrl = ctrl;
        this.setLayout( new BorderLayout() );
        this.add( new JLabel( "Panel Tirage" ), BorderLayout.CENTER );
    }
}