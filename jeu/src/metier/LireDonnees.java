package src.metier;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;

public class LireDonnees 
{
    //private Plateau plateau;

    private static final String FICHIER_GRILLE = "grille.data";
    private static final String FICHIER_POISSONS = "poissons.data";
    private static final String FICHIER_LIAISONS = "liaisons.data";
    private static final String FICHIER_ZONES = "zones.data";

    private static final String DOSSIER = "conception/class/src/data/"; 

    public static void lireGrille() 
    {
        try (Scanner scFic = new Scanner(new FileInputStream(DOSSIER + FICHIER_GRILLE), "UTF8")) 
        {
            while (scFic.hasNextLine()) 
            {
                String[] dec = scFic.nextLine().split("\t");
                if (dec.length >= 4) 
                {
                    int longueur = Integer.parseInt(dec[0]);
                    int largeur = Integer.parseInt(dec[1]);
                    int nbSymbole = Integer.parseInt(dec[2]);
                    int taille = Integer.parseInt(dec[3]);
                    this.plateau.initTableau(longueur, largeur, nbSymbole, taille);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Erreur lecture grille : " + e.getMessage());
        }
    }

    public static void lirePoissons() 
    {
        try (Scanner scFic = new Scanner(new FileInputStream(DOSSIER + FICHIER_POISSONS), "UTF8")) 
        {
            while (scFic.hasNextLine()) 
            {
                String[] dec = scFic.nextLine().split("\t");
                if (dec.length >= 4) 
                {
                    int id = Integer.parseInt(dec[0]);
                    String espece = dec[1];
                    int x = Integer.parseInt(dec[2]);
                    int y = Integer.parseInt(dec[3]);
                    //this.plateau.initPoisson(id, espece, x, y);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Erreur lecture grille : " + e.getMessage());
        }
    }

    public static void lireLiaisons() 
    {
        try (Scanner scFic = new Scanner(new FileInputStream(DOSSIER + FICHIER_LIAISONS), "UTF8")) 
        {
            while (scFic.hasNextLine()) 
            {
                String[] dec = scFic.nextLine().split("\t");
                if (dec.length >= 2) 
                {
                    int id1 = Integer.parseInt(dec[0]);
                    int id2 = Integer.parseInt(dec[1]);
                    //this.plateau.initLiaison(id1, id2);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Erreur lecture grille : " + e.getMessage());
        }
    }

    public static void lireZones() 
    {
        try (Scanner scFic = new Scanner(new FileInputStream(DOSSIER + FICHIER_ZONES), "UTF8")) 
        {
            System.out.println("--- Lecture des Zones ---");
            int ligneCourante = 0;
            
            while (scFic.hasNextLine()) 
            {
                String[] cases = scFic.nextLine().split("\t");
                
                System.out.print("Ligne " + ligneCourante + " : ");
                
                for (int x = 0; x < cases.length; x++) 
                {
                    String valeur = cases[x].trim(); 
                    
                    if (!valeur.isEmpty()) 
                    {
                        if (valeur.equals(".")) 
                        {
                            System.out.print("[Vide] ");
                        } 
                        else 
                        {
                            int numZone = Integer.parseInt(valeur);
                            System.out.print("[Zone " + numZone + "] ");
                        }
                    }
                }
                System.out.println(); 
                ligneCourante++;
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Erreur lecture zones : " + e.getMessage());
        }
    }


}