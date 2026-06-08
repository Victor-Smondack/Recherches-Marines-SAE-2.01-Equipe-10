package src.metier;

public class Zone
{
    private int numZone;
    private int x;
    private int y;

    public Zone(int numZone, int x, int y)
    {
        this.x       = x;
        this.y       = y;
        this.numZone = numZone;
    }


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
