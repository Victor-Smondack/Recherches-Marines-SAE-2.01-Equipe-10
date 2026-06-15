package src.ihm;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.Controleur;
import src.metier.Carte;


public class PanelTirage extends JPanel implements ActionListener
{
    private Controleur ctrl;
    private JLabel     carteTiree;
    private JButton    btnTirer;
    private JButton    btnLancerManche;
    private JLabel     lblPoints;
    private JLabel     lblCartesRestantes;
    private JLabel     lblCouleurLabo;
    private int        numManche = 1;

    // Constructeur configurant l'affichage des boutons de pioche, du visuel de la carte et du texte d'état
    public PanelTirage(Controleur ctrl)
    {
        JPanel panelBtn;
        this.ctrl = ctrl;
        this.setLayout( new GridLayout( 0, 1 ) );

        panelBtn        = new JPanel();

        this.carteTiree = new JLabel();
        this.carteTiree.setHorizontalAlignment( SwingConstants.CENTER );

        this.lblPoints          = new JLabel( "Score actuel : " + this.ctrl.getPointsTotal() + " points" );
        this.lblCartesRestantes = new JLabel( "Commencez à piocher" );

        this.lblCouleurLabo     = new JLabel( "Couleur du labo actuel : " + this.ctrl.getCouleurLabo( this.numManche ) );

        this.btnTirer           = new JButton( "Pioche" );
        this.btnTirer.setEnabled( false );
        this.btnLancerManche = new JButton( "Début Manche" );

        this.add( this.lblCouleurLabo );
        this.add( this.carteTiree );
        this.add( this.lblPoints );
        this.add( this.lblCartesRestantes );

        panelBtn.add( this.btnTirer );
        panelBtn.add( this.btnLancerManche );
        this.add( panelBtn );

        this.btnTirer.addActionListener( this );
        this.btnLancerManche.addActionListener( this );
    }


    // Clics sur le bouton Pioche ou bouton nouvelle manche
    @Override
    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == this.btnTirer )
        {
            if ( this.ctrl.getNbCartesNoires() >= 5 || this.ctrl.getNbCartesRestantes() == 0 )
            {
                this.numManche++;

                boolean laboExiste = false;
                int     maxX       = this.ctrl.getGrillePoisson().length;
                int     maxY       = this.ctrl.getGrillePoisson()[0].length;
                for ( int x = 0; x < maxX; x++ )
                {
                    for ( int y = 0; y < maxY; y++ )
                    {
                        if ( this.ctrl.getLaboIndice( x, y ) == this.numManche )
                        {
                            laboExiste = true;
                        }
                    }
                }

                this.btnTirer.setEnabled( false );

                if ( laboExiste )
                {
                    this.btnLancerManche.setEnabled( true );
                    if ( this.ctrl.getNbCartesNoires() >= 5 )
                    {
                        this.lblCartesRestantes.setText( "5 cartes noires ! Manche terminée." );
                    } else
                    {
                        this.lblCartesRestantes.setText( "Manche terminée ! Lancez la suivante." );
                    }
                } else
                {
                    this.btnLancerManche.setEnabled( false );
                    this.lblCartesRestantes.setText( "FIN DU JEU !" );
                    this.lblCouleurLabo.setText( "Couleur du labo actuel : Aucun" );

                    this.ctrl.finirManche();
                }

                // Actualise l'affichage du score final
                this.lblPoints.setText( "Score actuel : " + this.ctrl.getPointsTotal() + " points" );
                return;
            }

            System.out.println( "Une carte est tirée" );
            if ( this.ctrl.getNbCartesRestantes() > 0 )
            {
                Carte cartePiochee = this.ctrl.piocherCarte();
                this.lblCartesRestantes.setText( "Il reste " + this.ctrl.getNbCartesRestantes() + " cartes dans la pioche." );
                this.carteTiree.setIcon( new ImageIcon(
                    new ImageIcon( "../images/cartes/" + cartePiochee.getImagePath() ).getImage().getScaledInstance( 150, 120, Image.SCALE_SMOOTH ) ) );

                this.lblPoints.setText( "Score actuel : " + this.ctrl.getPointsTotal() + " points" );
            }
        }

        if ( e.getSource() == this.btnLancerManche )
        {
            System.out.println( "Début de la Manche" );
            this.ctrl.resetPioche();
            this.ctrl.melangerPioche();

            this.lblCouleurLabo.setText( "Couleur du labo actuel : " + this.ctrl.getCouleurLabo( this.numManche ) );

            int maxX = this.ctrl.getGrillePoisson().length;
            int maxY = this.ctrl.getGrillePoisson()[0].length;
            for ( int x = 0; x < maxX; x++ )
            {
                for ( int y = 0; y < maxY; y++ )
                {
                    if ( this.ctrl.getLaboIndice( x, y ) == this.numManche )
                    {
                        this.ctrl.validerEtAvancerEtude( x, y );
                    }
                }
            }

            this.lblCartesRestantes.setText( "Il reste " + this.ctrl.getNbCartesRestantes() + " cartes dans la pioche." );
            this.carteTiree.setIcon( null );

            this.btnTirer.setEnabled( true );
            this.btnLancerManche.setEnabled( false );

            this.lblPoints.setText( "Score actuel : " + this.ctrl.getPointsTotal() + " points" );
        }
    }
}
