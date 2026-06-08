package src.metier;

public class Poisson
{

    private static int nbId = 0;
    private int        id;

    private String     espece;

    private boolean    estLab;
    private String     couleurLab;

    private int        x;
    private int        y;


    public Poisson(String espece, int x, int y)
    {
        this.id     = ++nbId;
        this.espece = espece;
        this.x = x;
        this.y = y;
        this.estLab = false;
        this.couleurLab = "";
    }


    public String getEspece()
    {
        return this.espece;
    }


    public int getX()
    {
        return this.x;
    }


    public int getY()
    {
        return this.y;
    }


    public boolean getEstLab()
    {
        return this.estLab;
    }


    public String getCouleurLab()
    {
        return this.couleurLab;
    }


    public int getId()
    {
        return this.id;
    }


    public void setX( int x )
    {
        this.x = x;
    }


    public void setY( int y )
    {
        this.y = y;
    }


    public void setEstLab( boolean estLab )
    {
        this.estLab = estLab;
    }


    public void setCouleurLab( String couleurLab )
    {
        this.couleurLab = couleurLab;
    }


    public String toString()
    {
        return "Poisson " + this.id + " : " + this.espece + " (" + this.x + ", " + this.y + ")";
    }
}
