package src.metier;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche
{
    private ArrayList<Carte> lstCartes = new ArrayList<>();
    private ArrayList<Carte> pioche    = new ArrayList<>();

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


    public Carte piocher()
    {
        if ( this.pioche.isEmpty() )
            return null;
        return this.pioche.remove( 0 );
    }


    public boolean estVide()
    {
        return this.pioche.isEmpty();
    }


    public int getNbCartesRestantes()
    {
        return this.pioche.size();
    }


    public Carte carteActuelle()
    {
        if ( this.pioche.isEmpty() )
            return null;
        return this.pioche.get( 0 );
    }
}
