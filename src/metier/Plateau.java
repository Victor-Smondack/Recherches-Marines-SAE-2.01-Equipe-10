package src.metier;

import java.util.ArrayList;
import java.util.List;

public class Plateau
{
    private Poisson[][] grillePoisson;
    private List<Liaison> lstLiaisons;

    public Plateau()
    {
        // Initialisation de la grille 5x5 en dur (null = case vide)
        grillePoisson = new Poisson[5][5];
        lstLiaisons = new ArrayList<>();

        grillePoisson[0][0] = new Poisson("Saumon", 0, 0);
        grillePoisson[0][3] = new Poisson("Thon", 0, 3);
        grillePoisson[1][1] = new Poisson("Truite", 1, 1);
        grillePoisson[2][4] = new Poisson("Sardine", 2, 4);
        grillePoisson[3][2] = new Poisson("Bar", 3, 2);
        grillePoisson[4][0] = new Poisson("Colin", 4, 0);
        grillePoisson[4][4] = new Poisson("Maquereau", 4, 4);
    }

    public void genererLiaisons()
    {
        this.lstLiaisons.clear();

        // Collecte de tous les poissons présents dans la grille
        List<Poisson> lstPoisson = new ArrayList<>();
        for (int x = 0; x < grillePoisson.length; x++)
            for (int y = 0; y < grillePoisson[x].length; y++)
                if (grillePoisson[x][y] != null)
                    lstPoisson.add(grillePoisson[x][y]);

        // Génération des liaisons entre paires sans intermédiaire
        for (int i = 0; i < lstPoisson.size(); i++)
        {
            for (int j = i + 1; j < lstPoisson.size(); j++)
            {
                Poisson p1 = lstPoisson.get(i);
                Poisson p2 = lstPoisson.get(j);

                int dx = p2.getX() - p1.getX();
                int dy = p2.getY() - p1.getY();

                // Vérifie alignement : horizontal, vertical ou diagonal strict
                boolean alignes = dx == 0 || dy == 0 || Math.abs(dx) == Math.abs(dy);

                if (alignes && !existePoissonIntermediaire(p1, p2))
                {
                    Liaison l = new Liaison(p1, p2);
                    this.lstLiaisons.add(l);
                    System.out.println(l);
                }
            }
        }
    }

    private boolean existePoissonIntermediaire(Poisson p1, Poisson p2)
    {
        int dx = Integer.signum(p2.getX() - p1.getX());
        int dy = Integer.signum(p2.getY() - p1.getY());
        int x = p1.getX() + dx;
        int y = p1.getY() + dy;

        while (x != p2.getX() || y != p2.getY())
        {
            if (grillePoisson[x][y] != null)
                return true;
            x += dx;
            y += dy;
        }
        return false;
    }
}