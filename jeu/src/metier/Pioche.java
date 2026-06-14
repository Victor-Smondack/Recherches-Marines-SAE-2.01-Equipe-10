package src.metier;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche
{
    private ArrayList<Carte> lstCartes = new ArrayList<>();
    private ArrayList<Carte> pioche    = new ArrayList<>();
    private ArrayList<Carte> defausse;
    private Carte carteCourante;

    // Constructeur créant et remplissant le paquet de cartes selon le nombre de symboles du jeu
    public Pioche(int nbSymboles)
    {
        int      id       = 1;

        String[] poissons = {
            "Saumon",
            "Thon",
            "Truite",
            "Sardine",
            "Bar",
            "Colin",
            "Maquereau" };
        String[] couleurs = {
            "Blanc",
            "Noir" };

        for ( int i = 0; i < nbSymboles; i++ )
        {
            for ( String couleur : couleurs )
            {
                lstCartes.add( new Carte( id++, poissons[i], couleur, poissons[i] + couleur + ".png" ) );
            }
        }

        for ( String couleur : couleurs )
        {
            lstCartes.add( new Carte( id++, "Joker", couleur, "Joker" + couleur + ".png" ) );
        }

        this.defausse = new ArrayList<>();
        this.carteCourante = null;

        this.reset();
    }

    // Remet toutes les cartes d'origine dans la pioche principale
    public void reset()
    {
        this.pioche = new ArrayList<>( lstCartes );
    }

    // Mélange le paquet de cartes disponible de façon aléatoire
    public void melanger()
    {
        Collections.shuffle( pioche );
    }

    // Retire et renvoie la première carte tout en plaçant l'ancienne dans la défausse
    public Carte piocherCarte()
    {
        if (this.pioche.isEmpty())
            return null;

        if (this.carteCourante != null)
            this.defausse.add(0, this.carteCourante);

        this.carteCourante = this.pioche.remove(0);

        return this.carteCourante;
    }

    // Vérifie s'il ne reste aucune carte dans la pioche
    public boolean estVide()
    {
        return this.pioche.isEmpty();
    }

    // Donne le nombre total de cartes qui restent à piocher
    public int getNbCartesRestantes()
    {
        return this.pioche.size();
    }

    // Récupère la carte actuellement jouée
    public Carte getCarteCourante()
    {
        return this.carteCourante;
    }

    // Récupère la liste de toutes les cartes défaussées
    public ArrayList<Carte> getDefausse()
    {
        return this.defausse;
    }

    // Permet d'observer la première carte de la pile sans la piocher
    public Carte carteActuelle()
    {
        if ( this.pioche.isEmpty() )
            return null;
        return this.pioche.get( 0 );
    }

    // Pioche des cartes dans la pioche ou retire la première pour la rendre active
    public Carte piocher()
    {
        if (this.pioche.isEmpty()) return null;

        Carte carte = this.pioche.remove(0);

        if (this.carteCourante != null)
            this.defausse.add(0, this.carteCourante);

        this.carteCourante = carte;

        return carte;
    }

    // Vérifie s'il n'y a plus aucune carte noire présente dans la pioche
    public boolean derniereCarteNoirePiochee()
    {
        int nbNoires = 0;

        for (Carte c : this.pioche)
        {
            if (c.getDescription().equals("Noir"))
                nbNoires++;
        }

        return nbNoires == 0;
    }
}