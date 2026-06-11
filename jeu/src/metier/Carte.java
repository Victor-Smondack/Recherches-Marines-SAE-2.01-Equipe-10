package src.metier;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Carte 
{
    private int id;
    private String nom;
    private String description;
    private String imagePath;

    private int nbLabo;

    private final static ArrayList<String> lstCartes = new ArrayList<>() {{
        add("Saumon clair");
        add("Saumon foncé");
        add("Thon clair");
        add("Thon foncé");
        add("Truite claire");
        add("Truite foncée");
        add("Sardine claire");
        add("Sardine foncée");
        add("Bar clair");
        add("Bar foncé");
        add("Colin clair");
        add("Colin foncé");
        add("Maquereau clair");
        add("Maquereau foncé");
        add("Joker Clair");
        add("Joker Foncé");
    }};;

    private    static          ArrayList<String> pioche = new ArrayList<>(lstCartes);

    public Carte( int id, String nom, String description, String imagePath )
    {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.imagePath = imagePath;
    }

    public int getId()
    {
        return this.id;
    }

    public String getNom()
    {
        return this.nom;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getImagePath()
    {
        return this.imagePath;
    }

    public static ArrayList<String> getPioche()
    {
        return pioche;
    }

    public static ArrayList<String> getLstCartes()
    {
        return lstCartes;   
    }

    public static void resetPioche()
    {
        pioche = new ArrayList<>(lstCartes);
    }

    public static String piocherCarte()
    {
        if (pioche.isEmpty()) 
        {
            System.out.println("La pioche est vide !");
            return null;
        }
        String cartePiochée = pioche.remove(0);
        System.out.println("Carte piochée : " + cartePiochée);
        return cartePiochée;
    }

    public static void melangerPioche()
    {
        java.util.Collections.shuffle(pioche);
    }

    public static int getNbCartesRestantes()
    {
        return pioche.size();
    }



}