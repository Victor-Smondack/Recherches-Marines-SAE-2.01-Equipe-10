package src.metier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Sauvegarde
{
    // Constantes pour les noms de fichiers et le dossier de sauvegarde

    private static final String FICHIER_GRILLE   = "grille.txt";
    private static final String FICHIER_POISSONS = "poissons.txt";
    private static final String FICHIER_LIAISONS = "liaisons.txt";
    private static final String DOSSIER = "src/txt/";

    // Constructeur privé pour empêcher l'instanciation de la classe
    private Sauvegarde()
    {
        // Empêche l'instanciation de la classe
    }

    // Méthodes de sauvegarde
    public static void sauvegarderGrille(int longueur,
                                         int largeur,
                                         int nbSymbole,
                                         int tailleCases)
    {
        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(DOSSIER + FICHIER_GRILLE)))
        {
            writer.write(
                longueur + ";" +
                largeur + ";" +
                nbSymbole + ";" +
                tailleCases
            );
        }
        catch (IOException e)
        {
            System.err.println( "Erreur lors de la sauvegarde de la grille : " + e.getMessage());
        }
    }

    // Sauvegarde de la liste des poissons

    public static void sauvegarderPoissons(List<Poisson> lstPoissons)
    {
        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(DOSSIER + FICHIER_POISSONS)))
        {
            for (Poisson p : lstPoissons)
            {
                writer.write(
                    p.getId() + ";" +
                    p.getEspece() + ";" +
                    p.getX() + ";" +
                    p.getY()
                );

                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.err.println( "Erreur lors de la sauvegarde des poissons : " + e.getMessage() );
        }
    }

    // Sauvegarde de la liste des liaisons

    public static void sauvegarderLiaisons(List<Liaison> lstLiaisons)
    {
        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(DOSSIER + FICHIER_LIAISONS)))
        {
            for (Liaison liaison : lstLiaisons)
            {
                writer.write(
                    liaison.getP1().getId() + ";" +
                    liaison.getP2().getId()
                );

                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.err.println( "Erreur lors de la sauvegarde des liaisons : " + e.getMessage() );
        }
    }

    // Sauvegarde de la grille des zones
    
    public static void sauvegarderZones(Zone[][] grilleZone)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSSIER + "zones.txt")))
        {
            for (int y = 0; y < grilleZone[0].length; y++)
            {
                for (int x = 0; x < grilleZone.length; x++)
                {
                    if (grilleZone[x][y] != null)
                    {
                        writer.write(grilleZone[x][y].getNumZone() + ";");
                    }
                    else
                    {
                        writer.write(".;");
                    }
                }
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.err.println( "Erreur lors de la sauvegarde des zones : " + e.getMessage() );
        }
    }
}