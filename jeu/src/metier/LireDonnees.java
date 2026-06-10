package src.metier;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;

public class LireDonnees 
{
    private Plateau plateau;

    private ProgressionLabo labo;
    private Liaison liaison;
    private Poisson poisson;
    private Zone zone;

    private static final String FICHIER_GRILLE = "grille.data";
    private static final String FICHIER_POISSONS = "poissons.data";
    private static final String FICHIER_LABOS = "labos.data";
    private static final String FICHIER_LIAISONS = "liaisons.data";
    private static final String FICHIER_ZONES = "zones.data";

    private static final String DOSSIER = "conception/class/src/data/"; 

    public  void lireGrille() 
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

    public  void lirePoissons() 
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
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Erreur lecture grille : " + e.getMessage());
        }
    }

    public  void lireLiaisons() 
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
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Erreur lecture grille : " + e.getMessage());
        }
    }

    public void lireZones() 
    {
        try (Scanner scFic = new Scanner(new FileInputStream(DOSSIER + FICHIER_ZONES), "UTF8")) 
        {
            System.out.println("--- Lecture des Zones ---");
            int y = 0;
            while (scFic.hasNextLine()) 
            {
                String[] cases = scFic.nextLine().split("\t");
                for (int x = 0; x < cases.length; x++) 
                {
                    String valeur = cases[x].trim(); 
                    
                    if (!valeur.isEmpty() && !valeur.equals(".")) 
                    {
                        int couleurZone = Integer.parseInt(valeur);
                        this.plateau.initZone(couleurZone, x, y);
                    }
                }
                y++;
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Erreur lecture zones : " + e.getMessage());
        }
    }

    public void lireLabo() 
    {
        try (Scanner scFic = new Scanner(new FileInputStream(DOSSIER + FICHIER_LABOS), "UTF8")) 
        {
            System.out.println("--- Lecture du Labo ---");
            int y = 0;
            
            while (scFic.hasNextLine()) 
            {
                String[] cases = scFic.nextLine().split("\t");
                
                for (int x = 0; x < cases.length; x++) 
                {
                    String valeur = cases[x].trim(); 
                    
                    if (!valeur.isEmpty() && !valeur.equals(".")) 
                    {
                        int couleurLabo = Integer.parseInt(valeur);
                        this.plateau.initLabo(couleurLabo, x, y);
                    }
                }
                y++;
            }
        }
        catch (IOException e) 
        {
            System.err.println("Erreur lecture labo : " + e.getMessage());
        }
    }
}