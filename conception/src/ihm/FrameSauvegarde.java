package src.ihm;


public class FrameSauvegarde extends JFrame
{
    private PanelSauvegarde panelSauvegarde;


    public FrameSauvegarde()
    {
        
        this.setTitle   ( "Sauvegarde" );
		this.setSize    ( 400, 400 );
		this.setLocation( 450, 50 );


        this.panelSauvegarde = new PanelSauvegarde();

        this.add( panelSauvegarde );

        this.setVisible(true);
    }

}