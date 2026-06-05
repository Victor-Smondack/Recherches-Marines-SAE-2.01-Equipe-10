package src.metier;

public class Poisson
{

    private static int nbId = 0;
    private int        id;

    private String     espece;

    private boolean    estLab;


    public Poisson(String espece)
    {
        this.id     = ++nbId;
        this.espece = espece;
    }


    public String getEspece()
    {
        return this.espece;
    }


    public int getindiceX()
    {
        return this.indiceX;
    }


    public int getindiceY()
    {
        return this.indiceY;
    }


    public boolean getEstLab()
    {
        return this.estLab;
    }


    public int getId()
    {
        return this.id;
    }


    public void setindiceX( int indiceX )
    {
        this.indiceX = indiceX;
    }


    public void setindiceY( int indiceY )
    {
        this.indiceY = indiceY;
    }


    public void setEstLab( boolean estLab )
    {
        this.estLab = estLab;
    }


    public String toString()
    {
        return "Poisson " + this.id + " : " + this.espece + " (" + this.indiceX + ", " + this.indiceY + ")";
    }
}
