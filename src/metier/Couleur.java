public enum Couleur
{
   	BLEU_GLACIAIRE (180, 230, 255),
	BLEU_LAGON (64, 224, 208),
	TURQUOISE_OCEANIQUE (72, 209, 204),
	BLEU_CARAIBES (0, 191, 255),
	BLEU_AZUR_MARIN (0, 127, 255),
	BLEU_OCEAN_PROFOND (0, 105, 148),
	BLEU_ATLANTIQUE (0, 119, 190),
	BLEU_SAPHIR_MARIN (15, 82, 186),
	BLEU_ABYSSAL (0, 51, 102),
	BLEU_TEMPETE (47, 79, 79);

    private String libelle;

	private int r;
	private int v;
	private int b;

	private Couleur(String libelle, int r, int v, int b)
	{
		this.libelle = libelle;
		this.r = r;
		this.v = v;
		this.b = b;
	}

	public String getLibelle()
	{
		return libelle;
	}

	public String getValeurs()
	{
		int valeur = r * 256 * 256 + v * 256 + b;

		return String.format("%-10s (%-7s) %10d , [%3d,%3d,%3d]",
		                     libelle, this, valeur, r, v, b);
	}
}