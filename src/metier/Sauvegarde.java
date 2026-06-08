package src.metier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Sauvegarde
{
    private static final String FICHIER_GRILLE    = "grille.txt";
    private static final String FICHIER_POISSONS  = "poissons.txt";
    private static final String FICHIER_LIAISONS  = "liaisons.txt";

    public void sauvegarderGrille(int longueur,
                                  int largeur,
                                  int nbSymbole,
                                  int tailleCases)
    {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FICHIER_GRILLE)))
        {
            writer.write(
                longueur    + ";" +
                largeur     + ";" +
                nbSymbole   + ";" +
                tailleCases
            );
        }
        catch (IOException e)
        {
            System.err.println( "Erreur lors de la sauvegarde de la grille : " + e.getMessage() );
        }
    }

    public void sauvegarderPoissons(List<Poisson> lstPoissons)
    {
        try (BufferedWriter writer = new BufferedWriter( new FileWriter(FICHIER_POISSONS)))
        {
            for (Poisson p : lstPoissons)
            {
                writer.write(
                    p.getId()      + ";" +
                    p.getEspece()  + ";" +
                    p.getX()       + ";" +
                    p.getY()
                );

                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.err.println( "Erreur lors de la sauvegarde des poissons : " + e.getMessage()
            );
        }
    }

    public void sauvegarderLiaisons(List<Liaison> lstLiaisons)
    {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FICHIER_LIAISONS)))
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
}
