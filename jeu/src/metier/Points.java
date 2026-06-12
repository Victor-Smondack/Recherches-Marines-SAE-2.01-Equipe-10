package src.metier;

public class Points
{
    private int idJoueur;
    private int idManche;
    private int pointsZonesVisitees;
    private int pointsPoissons;
    private int pointsManche;
    private int pointsTotal;

    public Points( int idJoueur, int idManche)
    {
        this.idJoueur = idJoueur;
        this.idManche = idManche;
        this.pointsManche = 0;
        this.pointsTotal = 0;
        this.pointsZonesVisitees = 0;
        this.pointsPoissons = 0;
    }

    public int getIdJoueur()
    {
        return this.idJoueur;
    }

    public int getIdManche()
    {
        return this.idManche;
    }

    public int getPointsManche()
    {
        return this.pointsManche;
    }

    public int getPointsTotal()
    {
        return this.pointsTotal;
    }

    public void setPointsManche( int pointsManche )
    {
        this.pointsManche = pointsManche;
    }

    public void setPointsTotal( int pointsTotal )
    {
        this.pointsTotal = pointsTotal;
    }

    public void calculerPointsTotal()
    {
        this.pointsTotal += this.pointsZonesVisitees * this.pointsPoissons;
    }


    public void resetPointsManche()
    {
        this.pointsZonesVisitees = 0;
        this.pointsPoissons = 0;
    }

    public void resetPointsTotal()
    {
        this.pointsTotal = 0;
    }

    public int pointsZonesVisitees( int nbZonesVisitees )
    {
        this.pointsZonesVisitees += nbZonesVisitees;
        return this.pointsZonesVisitees;
    }

    public int pointsPoissonsCapturesZones( int nbPoissonsCapturesMax )
    {
        this.pointsPoissons += nbPoissonsCapturesMax;
        return this.pointsPoissons;
    }





    
}