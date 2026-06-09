package src.ihm;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import src.Controleur;
import java.awt.event.*;
import java.awt.Dimension;


public class PanelTirage extends JPanel implements ActionListener
{
    // Attributs
    private Controleur ctrl;
    private JLabel     carteTiree;
    private JButton    btnTirer;
    private JLabel     lblPoints;
    private JLabel     lblCartesRestantes;

    // Constructeurs du panel
    public PanelTirage( Controleur ctrl )
    {

        this.ctrl = ctrl;
        this.setLayout( new GridLayout( 0, 1 ) );
        this.add( new JLabel( "Panel Tirage" ) );

        // Création des composants
        this.carteTiree         = new JLabel( new ImageIcon( "path/to/carte/image.png" ) ); // Remplacez par le chemin de votre image
        
        this.lblPoints          = new JLabel( "Un nombre de points à déterminer");
        this.lblCartesRestantes = new JLabel( "Un nombre de cartes restantes à déterminer" );
    
        this.btnTirer           = new JButton( "Tirer une carte" );

        // Positionnement des composants
        this.add( this.carteTiree );

        this.add( this.lblPoints );

        this.add( this.lblCartesRestantes );

        this.add( this.btnTirer );

        // Ajout de l'action du bouton
        this.btnTirer.addActionListener( this );


    }
    // Méthode pour gérer l'action du bouton
    @Override
    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == this.btnTirer )
            System.out.println( "Une carte est tirée");

    }
}