package src.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import src.Controleur;

public class PanelDebut extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JButton    btnChoisirSauvegarde;


    private Graphics2D g2;
    private Image      imgFond;

    public PanelDebut(Controleur ctrl)
    {
        this.ctrl    = ctrl;

        this.imgFond = new ImageIcon( "../images/Couverture.png" ).getImage().getScaledInstance( 600, 1000, Image.SCALE_SMOOTH );
        this.setLayout( new BorderLayout() );

        //Création des composants

        this.btnChoisirSauvegarde = new JButton( "" );
        this.btnChoisirSauvegarde.setPreferredSize( new Dimension( 600, 110 ) );
        this.btnChoisirSauvegarde.setContentAreaFilled( false );

        //Position des Composants

        this.add( btnChoisirSauvegarde, BorderLayout.SOUTH );

        //Actionnment des composants
        this.btnChoisirSauvegarde.addActionListener( this );
    }


    @Override
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );

        this.g2 = (Graphics2D) g;

        if ( this.imgFond != null )
        {
            this.g2.drawImage( this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this );
        }
    }


    // Détecte les clics sur le bouton pour ouvrir l'explorateur de sélection de dossier
    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == this.btnChoisirSauvegarde )
        {

            JFileChooser choixDossier = new JFileChooser();
            choixDossier.setCurrentDirectory( new File( "../data/" ) );
            choixDossier.setDialogTitle( "Choisir un dossier de sauvegarde" );
            choixDossier.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
            choixDossier.setAcceptAllFileFilterUsed( false );

            int retour = choixDossier.showOpenDialog( null );

            if ( retour == JFileChooser.APPROVE_OPTION )
            {
                File   dossier    = choixDossier.getSelectedFile();
                String nomDossier = dossier.getName();
                System.out.println( "Dossier choisi : " + dossier.getAbsolutePath() + "Nom du dossier : " + nomDossier );
                this.ctrl.lireDonnees( dossier.getAbsolutePath() );
                this.ctrl.lancerJeu();
            }
        }
    }
}
