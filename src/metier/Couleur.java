package src.metier;

public enum Couleur
{
   	BLEU_GLACIAIRE ("Océan Pacifique", 180, 230, 255),
	BLEU_LAGON ("Océan Atlantique", 64, 224, 208),
	TURQUOISE_OCEANIQUE ("Océan Indien", 72, 209, 204),
	BLEU_CARAIBES ("Océan Austral", 0, 191, 255),
	BLEU_AZUR_MARIN ("Océan Arctique", 0, 127, 255),
	BLEU_OCEAN_PROFOND ("Mer de java", 0, 105, 148),
	BLEU_ATLANTIQUE ("Mer méditerranée", 0, 119, 190),
	BLEU_SAPHIR_MARIN ("Mer caspienne", 15, 82, 186),
	BLEU_ABYSSAL ("Mer du nord", 0, 51, 102),
	BLEU_TEMPETE ("Mer noire", 47, 79, 79);

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