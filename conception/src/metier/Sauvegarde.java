package src.metier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public class Sauvegarde
{
    private static final String FICHIER_GRILLE   = "grille.data";
    private static final String FICHIER_POISSONS = "poissons.data";
    private static final String FICHIER_LABOS    = "labos.data";
    private static final String FICHIER_LIAISONS = "liaisons.data";
    private static final String FICHIER_ZONES    = "zones.data";

    private static final String DOSSIER          = "../";

    private Sauvegarde()
    {
    }


    private static void creerDossierSiInexistant(String nvDossier)
    {
        File dossier = new File( DOSSIER + nvDossier);
        if ( !dossier.exists() )
        {
            dossier.mkdirs();
        }
    }


    public static void sauvegarderGrille( int longueur, int largeur, int nbSymbole, int tailleCases,int nbLabo, String nvDossier )
    {
        creerDossierSiInexistant(nvDossier);
        try (PrintWriter pw = new PrintWriter( new OutputStreamWriter( new FileOutputStream( DOSSIER + nvDossier + "/" + FICHIER_GRILLE ), "UTF8" ) ))
        {
            pw.println( longueur + "\t" + largeur + "\t" + nbSymbole +"\t" + nbLabo + "\t" + tailleCases );
        } catch (IOException e)
        {
            System.err.println( "Erreur lors de la sauvegarde de la grille : " + e.getMessage() );
        }
    }


    public static void sauvegarderPoissons( List<Poisson> lstPoissons, String nvDossier )
    {
        creerDossierSiInexistant(nvDossier);
        try (PrintWriter pw = new PrintWriter( new OutputStreamWriter( new FileOutputStream( DOSSIER + nvDossier + "/" + FICHIER_POISSONS ), "UTF8" ) ))
        {
            for ( Poisson p : lstPoissons )
            {
                pw.println( p.getId() + "\t" + p.getEspece() + "\t" + p.getX() + "\t" + p.getY() + "\t" + p.getEstLab() + "\t" + p.getCouleurLab() );
            }
        } catch (IOException e)
        {
            System.err.println( "Erreur lors de la sauvegarde des poissons : " + e.getMessage() );
        }
    }


    public static void sauvegarderLiaisons( List<Liaison> lstLiaisons, String nvDossier )
    {
        creerDossierSiInexistant(nvDossier);
        try (PrintWriter pw = new PrintWriter( new OutputStreamWriter( new FileOutputStream( DOSSIER + nvDossier + "/" + FICHIER_LIAISONS ), "UTF8" ) ))
        {
            for ( Liaison liaison : lstLiaisons )
            {
                pw.println( liaison.getP1().getX() + "\t" + liaison.getP1().getY() + "\t" + liaison.getP2().getX() + "\t" + liaison.getP2().getY() );
            }
        } catch (IOException e)
        {
            System.err.println( "Erreur lors de la sauvegarde des liaisons : " + e.getMessage() );
        }
    }


    public static void sauvegarderZones( Zone[][] grilleZone, String nvDossier )
    {
        creerDossierSiInexistant(nvDossier);
        try (PrintWriter pw = new PrintWriter( new OutputStreamWriter( new FileOutputStream( DOSSIER + nvDossier + "/" + FICHIER_ZONES ), "UTF8" ) ))
        {
            for ( int i = 0; i < grilleZone.length; i++ )
            {
                for ( int j = 0; j < grilleZone[i].length; j++ )
                {
                    if ( grilleZone[i][j] != null )
                        pw.print( grilleZone[i][j].getNumZone() + "\t" );
                    else
                        pw.print( ".\t" );
                }
                pw.println();
            }
        } catch (IOException e)
        {
            System.err.println( "Erreur lors de la sauvegarde des zones : " + e.getMessage() );
        }
    }


    public static void sauvegarderLabo( int[][] grilleLabo, String nvDossier )
    {
        creerDossierSiInexistant(nvDossier);
        try (PrintWriter pw = new PrintWriter( new OutputStreamWriter( new FileOutputStream( DOSSIER + nvDossier + "/" + FICHIER_LABOS ), "UTF8" ) ))
        {
            for ( int i = 0; i < grilleLabo.length; i++ )
            {
                for ( int j = 0; j < grilleLabo[i].length; j++ )
                {
                    if ( grilleLabo[i][j] != 0 )
                        pw.print( grilleLabo[i][j] + "\t" );
                    else
                        pw.print( ".\t" );
                }
                pw.println();
            }
        } catch (IOException e)
        {
            System.err.println( "Erreur lors de la sauvegarde des labos : " + e.getMessage() );
        }
    }
}
