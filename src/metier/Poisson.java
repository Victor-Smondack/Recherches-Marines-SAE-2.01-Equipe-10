package src.metier;

public class Poisson
{
    private static int nbId = 0;
    private int Id;

    private String espece;

    private int x;
    private int y;
    private int indiceX;
    private int indiceY;

    public Poisson(String espece, int x, int y, int indiceX, int indiceY)
    {
        this.id = ++nbId;
        this.espece = espece;
        this.x = x;
        this.y = y;
        this.indiceX = indiceX;
        this.indiceY = indiceY;
    }

    public String getEspece() { return this.espece; }
    public int getX()     { return this.x;    }
    public int getY()   { return this.y;    }
    public int getIndiceX() { return this.indiceX; }
    public int getIndiceY() { return this.indiceY; }

    public void setX(int x)     { this.x = x;    }
    public void setY(int y)   { this.y = y;    }
}