package src.ihm;

import src.Controleur;

import java.io.File;

import javax.swing.*;

import java.awt.BorderLayout;

import java.awt.event.*;

public class PanelDebut extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JButton    btnChoisirSauvegarde;

    public PanelDebut(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout( new BorderLayout() );

        //Création des composants

        this.btnChoisirSauvegarde = new JButton( "ChoisirSauvegarde" );

        //Position des Composants

        this.add( btnChoisirSauvegarde, BorderLayout.NORTH );

        //Actionnment des composants
        this.btnChoisirSauvegarde.addActionListener( this );
    }


    // Intercepte les clics sur le bouton pour ouvrir l'explorateur de sélection de dossier
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
