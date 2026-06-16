package src.metier;

public enum Couleur
{
		// Couleurs des zones

		BLEU_GLACIAIRE("Océan Pacifique", 180, 230, 255),
		BLEU_LAGON("Océan Atlantique", 14, 154, 208),
		TURQUOISE_OCEANIQUE("Océan Indien", 72, 209, 204),
		BLEU_CARAIBES("Océan Austral", 0, 191, 255),
		BLEU_AZUR_MARIN("Océan Arctique", 0, 127, 255),
		BLEU_OCEAN_PROFOND("Mer de java", 0, 105, 148),
		BLEU_ATLANTIQUE("Mer méditerranée", 0, 119, 190),
		BLEU_SAPHIR_MARIN("Mer caspienne", 15, 82, 186),
		BLEU_ABYSSAL("Mer du nord", 0, 51, 102),
		BLEU_TEMPETE("Mer noire", 47, 79, 79),

		LABO_ROUGE("Laboratoire Rouge", 255, 50, 50),
		LABO_VERT("Laboratoire Vert", 50, 205, 50),
		LABO_JAUNE("Laboratoire Jaune", 255, 215, 0),
		LABO_VIOLET("Laboratoire Violet", 148, 0, 211),
		LABO_ORANGE("Laboratoire Orange", 255, 140, 0),
		LABO_ROSE("Laboratoire Rose", 255, 20, 147),
		LABO_BLANC("Laboratoire Blanc", 225, 225, 225);

	private String	libelle;
	private int		r;
	private int		v;
	private int		b;

	// Constructeur initialiser les valeurs RGB le libellé d'une couleur
	private Couleur(String libelle, int r, int v, int b)
	{
		this.libelle	= libelle;
		this.r			= r;
		this.v			= v;
		this.b			= b;
	}


	// Getters
    
	// Récupère le nom de la zone ou du laboratoire
	public String getLibelle()
	{
		return libelle;
	}

	// Récupère la valeur de la composante Rouge
	public int getR()
	{
		return this.r;
	}

	// Récupère la valeur de la composante Verte
	public int getV()
	{
		return this.v;
	}

	// Récupère la valeur de la composante Bleue
	public int getB()
	{
		return this.b;
	}


	// Méthode pour obtenir une couleur à partir de son code
	public static Couleur valueOf( int code )
	{
		for ( Couleur c : Couleur.values() )
		{
			if ( c.ordinal() == code )
			{
				return c;
			}
		}
		return null;
	}
}