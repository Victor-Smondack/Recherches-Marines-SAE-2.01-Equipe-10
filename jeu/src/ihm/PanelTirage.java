package src.ihm;

import javax.swing.*;
import java.awt.GridLayout;
import src.Controleur;
import java.awt.event.*;


public class PanelTirage extends JPanel implements ActionListener
{
    // Attributs
    private Controleur   ctrl;
    private JLabel       carteTiree;
    private JButton      btnTirer;
    private JTextField   txtPoints;
    private JTextField   txtCartesRestantes;

    // Constructeurs du panel
    public PanelTirage( Controleur ctrl )
    {
        JPanel panelPoints;
        JPanel panelCartesRestantes;

        this.ctrl = ctrl;
        this.setLayout( new GridLayout( 4, 1 ) );
        this.add( new JLabel( "Panel Tirage" ) );

        // Création des composants
        this.carteTiree         = new JLabel( new ImageIcon( "path/to/carte/image.png" ) ); // Remplacez par le chemin de votre image
        panelPoints             = new JPanel();
        this.txtPoints          = new JTextField( 10 );
        panelCartesRestantes    = new JPanel();
        this.txtCartesRestantes = new JTextField( 10 );
        this.btnTirer           = new JButton( "Tirer une carte" );

        // Positionnement des composants
        this.add( this.carteTiree );
        panelPoints.add( new JLabel( "Points: " ) );
        panelPoints.add( this.txtPoints );
        this.add( panelPoints );
        panelCartesRestantes.add( new JLabel( "Cartes restantes: " ) );
        panelCartesRestantes.add( this.txtCartesRestantes );
        this.add( panelCartesRestantes );
        this.add( this.btnTirer );

        // Ajout de l'action du bouton
        this.btnTirer.addActionListener( this );


    }
    // Méthode pour gérer l'action du bouton
    @Override
    public void actionPerformed( ActionEvent e )
    {


    }
}