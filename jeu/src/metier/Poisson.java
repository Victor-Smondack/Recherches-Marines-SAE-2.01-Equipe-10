package src.metier;

public class Poisson
{
    // Attributs

    private static int nbId = 0;
    private int        id;

    private String     espece;

    private boolean    estLab;
    private String     couleurLab;

    private int        x;
    private int        y;

    // Constructeur

    // Constructeur créant un poisson avec une espèce et sa position sur la grille
    public Poisson(String espece, int x, int y)
    {
        this.id     = ++nbId;
        this.espece = espece;
        this.x = x;
        this.y = y;
        this.estLab = false;
        this.couleurLab = "";
    }

    // Getters et Setters

    // Récupère le nom de l'espèce de ce poisson
    public String getEspece()
    {
        return this.espece;
    }

    // Récupère l'emplacement en abscisse (X) du poisson
    public int getX()
    {
        return this.x;
    }

    // Récupère l'emplacement en ordonnée (Y) du poisson
    public int getY()
    {
        return this.y;
    }

    // Indique si ce poisson fait office de laboratoire
    public boolean getEstLab()
    {
        return this.estLab;
    }

    // Récupère le nom de la couleur du laboratoire lié
    public String getCouleurLab()
    {
        return this.couleurLab;
    }

    // Récupère le numéro d'identifiant unique du poisson
    public int getId()
    {
        return this.id;
    }

    // Modifie la position en X du poisson
    public void setX( int x )
    {
        this.x = x;
    }

    // Modifie la position en Y du poisson
    public void setY( int y )
    {
        this.y = y;
    }

    // Active ou désactive le statut laboratoire de ce poisson
    public void setEstLab( boolean estLab )
    {
        this.estLab = estLab;
    }

    // Modifie le nom de couleur affecté à ce laboratoire
    public void setCouleurLab( String couleurLab )
    {
        this.couleurLab = couleurLab;
    }


    // toString
    
    // Génère un résumé textuel complet avec l'identifiant, l'espèce et les coordonnées du poisson
    public String toString()
    {
        return "Poisson " + this.id + " : " + this.espece + " (" + this.x + ", " + this.y + ")";
    }
}