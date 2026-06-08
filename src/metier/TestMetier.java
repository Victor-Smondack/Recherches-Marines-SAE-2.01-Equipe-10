package src.metier;

public class TestMetier
{
	public static void main( String[] args )
	{
		String[]	espece		= {
			"Saumon",
			"Thon",
			"Truite",
			"Sardine",
			"Bar",
			"Colin",
			"Maquereau"
		};

		int			longueur	= 5;
		int			largeur		= 5;

		Metier		p			= new Metier( longueur, largeur );

		// Placement des poissons dans le plateau
		p.positionPoisson( 0, 0, espece[0], 0, 0 );
		p.positionPoisson( 1, 1, espece[1], 1, 1 );
		p.positionPoisson( 2, 2, espece[2], 2, 2 );
		p.positionPoisson( 0, 2, espece[3], 0, 2 );
		p.positionPoisson( 4, 4, espece[4], 4, 4 );

		// Génération des liaisons
		p.genererLiaisons();

		// Affichage
		System.out.println( p );
		System.out.println(p.toStringLiaisons());

		// Test de la méthode getPoisson
		System.out.println( "Poisson à (1, 1) : " + p.getPoisson( 1, 1 ) );
		System.out.println( "Poisson à (3, 3) : " + p.getPoisson( 3, 3 ) );

		// Test de la méthode getLiaisons
		System.out.println( "Liaisons à (0, 0) : " + p.getLiaisons( 0, 0 ) );
		System.out.println( "Liaisons à (2, 2) : " + p.getLiaisons( 2, 2 ) );

		// Test de la méthode supprimerPoisson
		System.out.println( "Suppression du poisson à (1, 1)" );
		p.supprimerPoisson( 1, 1 );
		System.out.println( "Poisson à (1, 1) après suppression : " + p.getPoisson( 1, 1 ) );

		System.out.println(
							"Poisson à (0, 0) est lié à Poisson à (0 , 2) : "
								+ p.estLie( p.getPoisson( 0, 0 ), p.getPoisson( 0, 2 ) ) );
		System.out.println(
							"Poisson à (0, 0) est lié à Poisson à (4 , 4) : "
								+ p.estLie( p.getPoisson( 0, 0 ), p.getPoisson( 4, 4 ) ) );

		System.out.println( p );
		System.out.println(p.toStringLiaisons());

		//test de la méthode echangerPoisson
		System.out.println( "Échange du poisson à (0, 0) avec le poisson à (4, 4)" );
		System.out.println( p );
		System.out.println(p.toStringLiaisons());
	}
}
