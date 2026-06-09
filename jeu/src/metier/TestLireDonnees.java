package src.metier;


public class TestLireDonnees
{
    public static void main(String[] args)
    {
        //System.out.println("Test de la classe LireDonnees :");
        //LireDonnees.lirePoissons();
        //LireDonnees.lireLiaisons();
        //LireDonnees.lireZones();
        //ld.lireGrille();
        //System.out.println("Test de LireDonneesGrille terminé.");

        //Carte.getPioche();

        /*lstCartes.add("Saumon clair");
        lstCartes.add("Saumon foncé");
        lstCartes.add("Thon clair");
        lstCartes.add("Thon foncé");*/
        System.out.println(Carte.getLstCartes());
        System.out.println(Carte.getPioche());

        Carte.melangerPioche();
        System.out.println(Carte.getPioche());


        Carte.piocherCarte();
        System.out.println(Carte.getPioche());

        Carte.piocherCarte();
        System.out.println(Carte.getPioche());

        Carte.piocherCarte();
        System.out.println(Carte.getPioche());


        Carte.resetPioche();
        System.out.println(Carte.getPioche());

        Carte.melangerPioche();
        System.out.println(Carte.getPioche());





    }
}