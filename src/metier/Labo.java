package src.metier;

public class Labo
{
    private static int nbIdL = 0;
    private int idL;
    
    private static int nbIdClr = 0;
    private int idClr;

    private int znVis;
    private int maxPZ;
    private int nbP;

    private int ligEx1;
    private int colEx1;
    private int ligEx2;
    private int colEx2;
    
    
    
    public Labo(String espece)
    {
        this.idL   = ++nbIdL;
        this.idClr = ++nbIdClr;
        this.znVis = 0;
        this.maxPZ = 0;
        this.nbP   = this.znVis * this.maxPZ;
        this.ligEx1 = 0;
        this.colEx1 = 0;
        this.ligEx2 = 0;
        this.colEx2 = 0;
    }

    public int getIdLabo()               { return this.idL;    }
    public int getIdCouleur()            { return this.idClr;  }
    public int getZonesVisitees()        { return this.znVis;  }
    public int getMaxPointsZone()        { return this.maxPZ;  }
    public int getPoints()               { return this.nbP;    }
    public int getLigneExtremite1()      { return this.ligEx1; }
    public int getColonneExtremite1()    { return this.colEx1; }
    public int getLigneExtremite2()      { return this.ligEx2; }
    public int getColonneExtremite2()    { return this.colEx2; }

    public void setIdLabo(int idL)                { this.idL = idL;       }
    public void setIdCouleur(int idClr)           { this.idClr = idClr;   }
    public void setZonesVisitees(int znVis)       { this.znVis = znVis;   }
    public void setMaxPointsZone(int maxPZ)       { this.maxPZ = maxPZ;   }
    public void setPoints(int nbP)                { this.nbP = nbP;       }
    public void setLigneExtremite1(int ligEx1)    { this.ligEx1 = ligEx1; }
    public void setColonneExtremite1(int colEx1)  { this.colEx1 = colEx1; }
    public void setLigneExtremite2(int ligEx2)    { this.ligEx2 = ligEx2; }
    public void setColonneExtremite2(int colEx2)  { this.colEx2 = colEx2; }
}