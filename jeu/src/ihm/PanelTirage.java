package src.ihm;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Controleur;


public class PanelTirage extends JPanel implements ActionListener
{
    // Attributs
    private Controleur ctrl;
    private JLabel     carteTiree;
    private JButton    btnTirer;
    private JLabel     lblPoints;
    private JLabel     lblCartesRestantes;

    // Constructeurs du panel
    public PanelTirage(Controleur ctrl)
    {

        this.ctrl = ctrl;
        this.setLayout( new GridLayout( 0, 1 ) );
        this.add( new JLabel( "Panel Tirage" ) );

        // Création des composants
        this.carteTiree         = new JLabel(
            new ImageIcon( new ImageIcon( "./src/ihm/images/cartes/JokerNoir.png" ).getImage()
                .getScaledInstance( 75, 200, Image.SCALE_SMOOTH ) ) );

        this.lblPoints          = new JLabel( "Un nombre de points à déterminer" );
        this.lblCartesRestantes = new JLabel( "Il reste "+ this.ctrl.getNbCartesRestantes() + " cartes dans la pioche" );

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
        {
            System.out.println( "Une carte est tirée" );
            this.ctrl.piocherCarte();
            this.lblCartesRestantes.setText( "Il reste "+ this.ctrl.getNbCartesRestantes() + " cartes dans la pioche" );
        }

    }
}
