package src.metier;

public class Zone
{
    // Attributs

    private int numZone;
    private int x;
    private int y;

    // Constructeur

    public Zone(int numZone, int x, int y)
    {
        this.x       = x;
        this.y       = y;
        this.numZone = numZone;
    }

    // Getters

    public int getNumZone()
    {
        return this.numZone;
    }


    public int getZoneX()
    {
        return this.x;
    }


    public int getZoneY()
    {
        return this.y;
    }
}
