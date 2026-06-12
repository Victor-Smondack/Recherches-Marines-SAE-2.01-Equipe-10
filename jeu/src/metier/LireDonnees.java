package src.metier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LireDonnees
{
    private Plateau             plateau;

    private ProgressionLabo     labo;
    private Liaison             liaison;
    private Poisson             poisson;
    private Zone                zone;

    private static final String FICHIER_GRILLE   = "grille.data";
    private static final String FICHIER_POISSONS = "poissons.data";
    private static final String FICHIER_LABOS    = "labos.data";
    private static final String FICHIER_LIAISONS = "liaisons.data";
    private static final String FICHIER_ZONES    = "zones.data";

    private static final String DOSSIER          = "../../conception/class/src/data/";

    public LireDonnees(Plateau plateau)
    {
        this.plateau = plateau;
    }


    public void lireGrille()
    {
        try (Scanner scFic = new Scanner( new FileInputStream( DOSSIER + FICHIER_GRILLE ), "UTF8" ))
        {
            while ( scFic.hasNextLine() )
            {
                String[] dec = scFic.nextLine().split( "\t" );
                if ( dec.length >= 4 )
                {
                    int longueur  = Integer.parseInt( dec[0] );
                    int largeur   = Integer.parseInt( dec[1] );
                    int nbSymbole = Integer.parseInt( dec[2] );
                    int nbLabo    = Integer.parseInt( dec[3] );
                    int taille    = Integer.parseInt( dec[4] );
                    this.plateau.initTableau( longueur, largeur, nbSymbole, nbLabo, taille );
                }
            }
        } catch (IOException e)
        {
            System.err.println( "Erreur lecture grille : " + e.getMessage() );
        }
    }


    public void lirePoissons()
    {
        try (Scanner scFic = new Scanner( new FileInputStream( DOSSIER + FICHIER_POISSONS ), "UTF8" ))
        {
            while ( scFic.hasNextLine() )
            {
                String[] dec = scFic.nextLine().split( "\t" );
                if ( dec.length >= 4 )
                {
                    int    id     = Integer.parseInt( dec[0] );
                    String espece = dec[1];
                    int    x      = Integer.parseInt( dec[2] );
                    int    y      = Integer.parseInt( dec[3] );

                    this.plateau.initPoisson( id, espece, x, y );
                }
            }
        } catch (IOException e)
        {
            System.err.println( "Erreur lecture grille : " + e.getMessage() );
        }
    }


    public static ArrayList<Liaison> lireLiaisons(String nomFichier, Poisson[][] grillePoisson)
    {
        ArrayList<Liaison> lstLiaisons = new ArrayList<>();

        try (Scanner scFic = new Scanner( new FileInputStream( DOSSIER + FICHIER_LIAISONS ), "UTF8" ))
        {

            while (scFic.hasNextLine())
            {
                String ligne = scFic.nextLine();

                if (ligne.trim().isEmpty())
                    continue;

                String[] tab = ligne.split("\t");

                int x1 = Integer.parseInt(tab[0]);
                int y1 = Integer.parseInt(tab[1]);

                int x2 = Integer.parseInt(tab[2]);
                int y2 = Integer.parseInt(tab[3]);

                Poisson p1 = grillePoisson[x1][y1];
                Poisson p2 = grillePoisson[x2][y2];

                if (p1 != null && p2 != null)
                    lstLiaisons.add(new Liaison(p1, p2));
            }

            scFic.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return lstLiaisons;
    }


    public void lireZones()
    {
        try (Scanner scFic = new Scanner( new FileInputStream( DOSSIER + FICHIER_ZONES ), "UTF8" ))
        {
            System.out.println( "--- Lecture des Zones ---" );
            int y = 0;
            while ( scFic.hasNextLine() )
            {
                String[] cases = scFic.nextLine().split( "\t" );
                for ( int x = 0; x < cases.length; x++ )
                {
                    String valeur = cases[x].trim();

                    if ( !valeur.isEmpty() && !valeur.equals( "." ) )
                    {
                        int couleurZone = Integer.parseInt( valeur );
                        this.plateau.initZone( couleurZone, x, y );
                    }
                }
                y++;
            }
        } catch (IOException e)
        {
            System.err.println( "Erreur lecture zones : " + e.getMessage() );
        }
    }


    public void lireLabo()
    {
        try (Scanner scFic = new Scanner( new FileInputStream( DOSSIER + FICHIER_LABOS ), "UTF8" ))
        {
            System.out.println( "--- Lecture du Labo ---" );
            int y = 0;

            while ( scFic.hasNextLine() )
            {
                String[] cases = scFic.nextLine().split( "\t" );

                for ( int x = 0; x < cases.length; x++ )
                {
                    String valeur = cases[x].trim();

                    if ( !valeur.isEmpty() && !valeur.equals( "." ) )
                    {
                        int couleurLabo = Integer.parseInt( valeur );
                        this.plateau.initLabo( couleurLabo, x, y );
                    }
                }
                y++;
            }
        } catch (IOException e)
        {
            System.err.println( "Erreur lecture labo : " + e.getMessage() );
        }
    }
}
