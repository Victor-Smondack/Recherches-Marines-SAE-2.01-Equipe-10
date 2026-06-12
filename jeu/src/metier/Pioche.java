package src.metier;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche
{
    private ArrayList<Carte> lstCartes = new ArrayList<>();
    private ArrayList<Carte> pioche    = new ArrayList<>();
    private ArrayList<Carte> defausse;
    private Carte carteCourante;

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


    public void reset()
    {
        this.pioche = new ArrayList<>( lstCartes );
    }


    public void melanger()
    {
        Collections.shuffle( pioche );
    }


    public Carte piocherCarte()
    {
        if (this.pioche.isEmpty())
            return null;

        if (this.carteCourante != null)
            this.defausse.add(0, this.carteCourante);

        this.carteCourante = this.pioche.remove(0);

        return this.carteCourante;
    }


    public boolean estVide()
    {
        return this.pioche.isEmpty();
    }


    public int getNbCartesRestantes()
    {
        return this.pioche.size();
    }

    public Carte getCarteCourante()
    {
        return this.carteCourante;
    }

    public ArrayList<Carte> getDefausse()
    {
        return this.defausse;
    }

    public Carte carteActuelle()
    {
        if ( this.pioche.isEmpty() )
            return null;
        return this.pioche.get( 0 );
    }

    public Carte piocher()
{
    if (this.pioche.isEmpty()) return null;

    Carte carte = this.pioche.remove(0);

    if (this.carteCourante != null)
        this.defausse.add(0, this.carteCourante);

    this.carteCourante = carte;

    return carte;
}
}
