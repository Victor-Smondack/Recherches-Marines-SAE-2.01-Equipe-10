package src.metier;

public class Zone
{

    private int indiceX;
    private int indiceY;
    private int numZone;

    public Zone(int indiceX, int indiceY, int numZone)
    {
        this.indiceX = indiceX;
        this.indiceY = indiceY;
        this.numZone = numZone;
    }


    public int getIndiceX()
    {
        return this.indiceX;
    }


    public int getIndiceY()
    {
        return this.indiceY;
    }


    public int getNumZone()
    {
        return this.numZone;
    }
}
