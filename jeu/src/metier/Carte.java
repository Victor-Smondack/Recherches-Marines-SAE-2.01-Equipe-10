package src.metier;

public class Carte 
{
    private int id;
    private String nom;
    private String description;
    private String imagePath;

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
}