package src.metier;

public class ProgressionLabo 
{
    Poisson extremite1, extremite2;
    int x1, y1, x2, y2;
    String couleurLabo;

    public ProgressionLabo(Poisson p)
    {
        this.extremite1 = p;
        this.x1 = p.getX();
        this.y1 = p.getY();
        this.couleurLabo = p.getCouleurLab();
        this.extremite2 = null;
    }

    public Poisson getExtremite1()
    {
        return this.extremite1;
    }

    public Poisson getExtremite2()
    {
        return this.extremite2;
    }

    public void setExtremite1(Poisson p)
    {
        this.extremite1 = p;
        this.x1 = p.getX();
        this.y1 = p.getY();
    }

    public void setExtremite2(Poisson p)
    {
        this.extremite2 = p;
        this.x2 = p.getX();
        this.y2 = p.getY();
    }
}
