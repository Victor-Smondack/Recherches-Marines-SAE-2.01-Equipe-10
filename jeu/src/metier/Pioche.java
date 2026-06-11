package src.metier;

<<<<<<< HEAD
public class Pioche 
{
    
=======

public class Pioche
{

    private String nomPoisson;
    private String couleur;
    private String nomImage;

    private final static ArrayList<String> lstCartes = new ArrayList<>() {{
        add("Saumon clair");
        add("Saumon foncé");
        add("Thon clair");
        add("Thon foncé");
        add("Truite claire");
        add("Truite foncée");
        add("Sardine claire");
        add("Sardine foncée");
        add("Bar clair");
        add("Bar foncé");
        add("Colin clair");
        add("Colin foncé");
        add("Maquereau clair");
        add("Maquereau foncé");
        add("Joker Clair");
        add("Joker Foncé");
    }};;

    private static ArrayList<String> pioche = new ArrayList<>(lstCartes);


    public Pioche( String nomPoisson, String couleur, String nomImage )
    {
        this.nomPoisson = nomPoisson;
        this.couleur = couleur;
        this.nomImage = nomImage;
    }


    public String getNomPoisson()
    {
        return this.nomPoisson;
    }

    public String getCouleur()
    {
        return this.couleur;
    }

    public String getNomImage()
    {
        return this.nomImage;
    }

    public static ArrayList<String> getLstCartes()
    {
        return lstCartes;
    }

    public static void resetPioche()
    {
        pioche = new ArrayList<>(lstCartes);
    }

    public static String piocherCarte()
    {
        if (pioche.isEmpty()) 
        {
            System.out.println("La pioche est vide !");
            return null;
        }
        String cartePiochée = pioche.remove(0);
        System.out.println("Carte piochée : " + cartePiochée);
        return cartePiochée;
    }

    public static void melangerPioche()
    {
        java.util.Collections.shuffle(pioche);
    }

    public static int getNbCartesRestantes()
    {
        return pioche.size();
    } 

>>>>>>> main
}
