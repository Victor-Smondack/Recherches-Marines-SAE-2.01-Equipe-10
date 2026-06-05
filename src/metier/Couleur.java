package src.metier;

public enum Couleur
{
		BLEU_GLACIAIRE(128, 192, 255),
		BLEU_LAGON(64, 224, 208),
		TURQUOISE_OCEANIQUE(72, 209, 204),
		BLEU_CARAIBES(0, 191, 255),
		BLEU_AZUR_MARIN(0, 127, 255),
		BLEU_OCEAN_PROFOND(0, 105, 148),
		BLEU_ATLANTIQUE(0, 119, 190),
		BLEU_SAPHIR_MARIN(15, 82, 186),
		BLEU_ABYSSAL(0, 51, 102),
		BLEU_TEMPETE(47, 79, 79);

	private int	r;
	private int	v;
	private int	b;

	private Couleur(int r, int v, int b)
	{
		this.r	= r;
		this.v	= v;
		this.b	= b;
	}


	public int getR()
	{
		return this.r;
	}


	public int getV()
	{
		return this.v;
	}


	public int getB()
	{
		return this.b;
	}


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
