package src.metier;

public class Points
{
    private int idJoueur;
    private int idManche;
    private int pointsZonesVisitees;
    private int pointsPoissons;
    private int pointsManche;
    private int pointsTotal;

    // Constructeur pour initialiser les fiches de score d'une manche pour un joueur précis
    public Points( int idJoueur, int idManche)
    {
        this.idJoueur = idJoueur;
        this.idManche = idManche;
        this.pointsManche = 0;
        this.pointsTotal = 0;
        this.pointsZonesVisitees = 0;
        this.pointsPoissons = 0;
    }

    // Récupère le numéro identifiant le joueur
    public int getIdJoueur()
    {
        return this.idJoueur;
    }

    // Récupère le numéro identifiant la manche
    public int getIdManche()
    {
        return this.idManche;
    }

    // Récupère le score obtenu sur la manche actuelle
    public int getPointsManche()
    {
        return this.pointsManche;
    }

    // Récupère le score global cumulé depuis le début
    public int getPointsTotal()
    {
        return this.pointsTotal;
    }

    // Modifie les points obtenus pour la manche
    public void setPointsManche( int pointsManche )
    {
        this.pointsManche = pointsManche;
    }

    // Modifie la valeur totale du score global
    public void setPointsTotal( int pointsTotal )
    {
        this.pointsTotal = pointsTotal;
    }

    // Applique le calcul multiplicatif pour ajouter les points de manche au total général
    public void calculerPointsTotal()
    {
        this.pointsTotal += this.pointsZonesVisitees * this.pointsPoissons;
    }

    // Remet à zéro les compteurs intermédiaires de points de la manche
    public void resetPointsManche()
    {
        this.pointsZonesVisitees = 0;
        this.pointsPoissons = 0;
    }

    // Remet le compteur de score global à zéro
    public void resetPointsTotal()
    {
        this.pointsTotal = 0;
    }

    // Ajoute et comptabilise des points liés aux zones qui ont été visitées
    public int pointsZonesVisitees( int nbZonesVisitees )
    {
        this.pointsZonesVisitees += nbZonesVisitees;
        return this.pointsZonesVisitees;
    }

    // Ajoute et comptabilise des points selon les captures de poissons effectuées
    public int pointsPoissonsCapturesZones( int nbPoissonsCapturesMax )
    {
        this.pointsPoissons += nbPoissonsCapturesMax;
        return this.pointsPoissons;
    }
}