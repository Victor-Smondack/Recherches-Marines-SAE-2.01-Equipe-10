package src.metier;

public class Carte 
{
    private int id;
    private String nom;
    private String description;
    private String imagePath;

    // Constructeur pour initialiser une carte de jeu
    public Carte( int id, String nom, String description, String imagePath )
    {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Récupère l'identifiant unique de la carte
    public int getId()
    {
        return this.id;
    }

    // Récupère le nom de la carte
    public String getNom()
    {
        return this.nom;
    }

    // Récupère la description de la carte
    public String getDescription()
    {
        return this.description;
    }

    // Récupère le chemin vers l'image de la carte
    public String getImagePath()
    {
        return this.imagePath;
    }
}