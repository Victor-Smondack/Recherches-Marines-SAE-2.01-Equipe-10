package src.metier;

public class TestPoints
{
    public static void main( String[] args )
    {
        Points points = new Points( 1, 1);
        System.out.println( "ID joueur : " + points.getIdJoueur() );
        System.out.println( "ID manche : " + points.getIdManche() );
        System.out.println( "Points de la manche : " + points.getPointsManche() );
        System.out.println( "Points total : " + points.getPointsTotal() );

        points.pointsZonesVisitees(3);
        points.pointsPoissonsCapturesZones(5);
        points.calculerPointsTotal();
        points.resetPointsManche();
        System.out.println( "Points de la manche après mise à jour : " + points.getPointsManche() );
        System.out.println( "Points total après calcul : " + points.getPointsTotal() );

        System.out.println("");

        points.pointsZonesVisitees(4);
        points.pointsPoissonsCapturesZones(2);
        points.calculerPointsTotal();
        System.out.println( "Points total après capture de poissons : " + points.getPointsTotal() );
        points.calculerPointsTotal();
        System.out.println( "Points total après calcul : " + points.getPointsTotal() );
        points.resetPointsManche();

        System.out.println("");

        points.setPointsManche( 20 );
        points.calculerPointsTotal();
        System.out.println( "Points total après mise à jour de la manche : " + points.getPointsTotal() );
    }
}