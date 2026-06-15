package src.metier;

public class Zone
{
    // Attributs

    private int numZone;
    private int x;
    private int y;

    // Constructeur

    // Constructeur d'une zone avec son numéro et sa position dans la grille
    public Zone(int numZone, int x, int y)
    {
        this.x       = x;
        this.y       = y;
        this.numZone = numZone;
    }

    // Getters

    // Récupère le numéro identifiant la couleur ou le type de la zone
    public int getNumZone()
    {
        return this.numZone;
    }

    // Récupère l'emplacement horizontal X de la case de la zone
    public int getZoneX()
    {
        return this.x;
    }

    // Récupère l'emplacement vertical Y de la case de la zone
    public int getZoneY()
    {
        return this.y;
    }
}