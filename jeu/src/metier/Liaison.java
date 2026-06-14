package src.metier;

public class Liaison
{
    // attributs

    private Poisson p1;
    private Poisson p2;


    // Constructeur pour lier deux poissons ensemble
    public Liaison(Poisson p1, Poisson p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }


    // Récupère le premier poisson de la liaison
    public Poisson getP1()
    {
        return p1;
    }

    // Récupère le deuxième poisson de la liaison
    public Poisson getP2()
    {
        return p2;
    }

    // Vérifie si deux liaisons connectent les mêmes poissons dans n'importe quel ordre
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Liaison))
            return false;

        Liaison l = (Liaison) o;

        return (p1.equals(l.p1) && p2.equals(l.p2))
            || (p1.equals(l.p2) && p2.equals(l.p1));
    }

    // toString
    
    // Représentation textuelle des deux poissons liés
    @Override
    public String toString()
    {
        return "Liaison{ " +
            "p1 = " + p1 +
            ", p2 = " + p2 +
            " }";
    }
}