package src.metier;

public class Labo
{
    private int idLabo;
    private String couleur;
    private int x;
    private int y;

    public Labo( int idLabo, String couleur, int x, int y )
    {
        this.idLabo = idLabo;
        this.couleur = couleur;
        this.x = x;
        this.y = y;
    }

    public int getIdLabo()
    {
        return this.idLabo;
    }

    public String getCouleur()
    {
        return this.couleur;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

}