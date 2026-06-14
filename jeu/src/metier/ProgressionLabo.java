package src.metier;

import java.util.ArrayList;

public class ProgressionLabo 
{
    Poisson extremite1, extremite2;
    int x1, y1, x2, y2;
    String couleurLabo;
    private ArrayList<Liaison> lstLiaisonsParcourues;

    // Constructeur créant un suivi d'étude à partir d'un premier poisson de départ
    public ProgressionLabo(Poisson p)
    {
        this.extremite1 = p;
        this.x1 = p.getX();
        this.y1 = p.getY();
        this.couleurLabo = p.getCouleurLab();
        this.extremite2 = null;
        this.lstLiaisonsParcourues = new ArrayList<>();
    }

    // Récupère le poisson situé à la première extrémité du tracé d'étude
    public Poisson getExtremite1()
    {
        return this.extremite1;
    }

    // Récupère le poisson situé à la deuxième extrémité du tracé d'étude
    public Poisson getExtremite2()
    {
        return this.extremite2;
    }

    // Modifie et met à jour le poisson de la première extrémité
    public void setExtremite1(Poisson p)
    {
        this.extremite1 = p;
        this.x1 = p.getX();
        this.y1 = p.getY();
    }

    // Modifie et met à jour le poisson de la deuxième extrémité
    public void setExtremite2(Poisson p)
    {
        this.extremite2 = p;
        this.x2 = p.getX();
        this.y2 = p.getY();
    }

    // Ajoute un segment de liaison dans l'historique du chemin parcouru par ce laboratoire
    public void ajouterLiaison(Liaison l)
    {
        this.lstLiaisonsParcourues.add(l);
    }

    // Vérifie si un segment de liaison fait déjà partie du tracé validé
    public boolean contientLiaison(Liaison l)
    {
        return this.lstLiaisonsParcourues.contains(l);
    }
}