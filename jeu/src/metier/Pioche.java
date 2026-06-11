package src.metier;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche
{
    private ArrayList<Carte> lstCartes = new ArrayList<>();
    private ArrayList<Carte> pioche = new ArrayList<>();

    public Pioche(int nbSymboles)
    {
        int id = 1;

        String[] poissons = { "Bar", "Colin", "Maquereau", "Sardine", "Saumon", "Thon", "Truite" };
        String[] couleurs = { "Blanc", "Noir" };

        int max = Math.min(nbSymboles, poissons.length);

        for (int i = 0; i < max; i++)
        {
            for (String couleur : couleurs)
            {
                lstCartes.add(new Carte(id++, poissons[i], couleur, poissons[i] + couleur + ".png"));
            }
        }

        reset();
    }

    public void reset()
    {
        pioche = new ArrayList<>(lstCartes);
    }

    public void melanger()
    {
        Collections.shuffle(pioche);
    }

    public Carte piocher()
    {
        if (pioche.isEmpty()) return null;
        return pioche.remove(0);
    }

    public boolean estVide()
    {
        return pioche.isEmpty();
    }

    public int getNbCartesRestantes()
    {
        return pioche.size();
    }

    public Carte carteActuelle()
    {
        if (pioche.isEmpty()) return null;
        return pioche.get(0);
    }
}