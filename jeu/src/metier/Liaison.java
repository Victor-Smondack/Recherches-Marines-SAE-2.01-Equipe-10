package src.metier;

public class Liaison
{
    // attributs

    private Poisson p1;
    private Poisson p2;

    // constructeur

    public Liaison(Poisson p1, Poisson p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }

    // getters

    public Poisson getP1()
    {
        return p1;
    }


    public Poisson getP2()
    {
        return p2;
    }

    // toString
    
    @Override
    public String toString()
    {
        return "Liaison{ " +
            "p1 = " + p1 +
            ", p2 = " + p2 +
            " }";
    }
}
