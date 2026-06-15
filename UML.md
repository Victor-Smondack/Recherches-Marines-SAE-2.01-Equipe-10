```mermaid
classDiagram
    direction TB

    %% ===================== CONTROLEUR =====================
    class Controleur {
        -int xGrille
        -int yGrille
        -FrameTirage frameTirage
        -FrameTable frameTable
        -FrameDebut frameDebut
        -Plateau metier
        -Pioche pioche
        -Carte carteVisible
        -Poisson selectedExtremity
        -boolean aJoueCeTour
        -int nbCartesNoires
        +Controleur()
        +initialiserGrille(longueur, largeur, nbSymbole, tailleCase) void
        +lireDonnees(nomDossier) void
        +lancerJeu() void
        +piocherCarte() Carte
        +melangerPioche() void
        +resetPioche() void
        +verifierClicValide(x, y) boolean
        +validerEtAvancerEtude(x, y) void
        +validerEtAvancerEtudeAvecExtremite(xExt, yExt, xDest, yDest) void
        +verifierDeplacement(xExt, yExt, xDest, yDest) boolean
        +estUnLaboActif() boolean
        +finirManche() void
        +majIHM() void
        +getPointsTotal() int
        +main(args)$ void
    }

    %% ===================== IHM (VUE) =====================
    class FrameDebut {
        -Controleur ctrl
        -PanelDebut panelDebut
        +FrameDebut(ctrl)
    }
    class PanelDebut {
        -Controleur ctrl
        -JButton btnChoisirSauvegarde
        +actionPerformed(e) void
    }
    class FrameTable {
        -Controleur ctrl
        +FrameTable(ctrl, longueur, largeur, tailleCase)
    }
    class PanelTable {
        -int longueur
        -int largeur
        -int tailleCase
        -Image imgFond
        -JLabel[][] cases
        -Controleur ctrl
        +paintComponent(g) void
    }
    class GereSouris {
        +mousePressed(evt) void
    }
    class FrameTirage {
        -Controleur ctrl
        -PanelTirage panelTirage
        +FrameTirage(ctrl)
    }
    class PanelTirage {
        -Controleur ctrl
        -JLabel carteTiree
        -JButton btnTirer
        -JButton btnLancerManche
        -JLabel lblPoints
        -JLabel lblCartesRestantes
        -JLabel lblCouleurLabo
        -int numManche
        +actionPerformed(e) void
    }

    %% ===================== METIER (MODELE) =====================
    class Plateau {
        -Poisson[][] grillePoisson
        -ProgressionLabo progressionLabo
        -int idLaboActif
        -List~Liaison~ lstLiaisons
        -List~Liaison~ liaisonsVisitees
        -List~Integer~ labosDesLiaisons
        -Zone[][] grilleZone
        -List~Poisson~ lstPoisson
        -int[][] grilleLabo
        -Points points
        -List~Poisson~ poisonsManche
        -int longueur
        -int largeur
        -int nbSymbole
        -String[] espece
        +Plateau(longueur, largeur)
        +genererLiaisons() void
        +etudePoisson(p) String
        +estPoissonValidePourLabo(p) boolean
        +verifierDeplacement(ext, dest) boolean
        +validerEtAvancerEtudeAvecExtremite(ext, dest) String
        +finirManche() void
        +initTableau(...) void
        +initPoisson(id, espece, x, y) void
        +initZone(numZone, x, y) void
        +initLabo(numLabo, x, y) void
        +initLiaisonCoord(x1, y1, x2, y2) void
        +lireDonnees(dossier) void
        +getPointsTotal() int
    }
    class Poisson {
        -int nbId$
        -int id
        -String espece
        -boolean estLab
        -String couleurLab
        -int x
        -int y
        +Poisson(espece, x, y)
        +getEspece() String
        +getX() int
        +getY() int
        +setEstLab(estLab) void
        +toString() String
    }
    class Liaison {
        -Poisson p1
        -Poisson p2
        +Liaison(p1, p2)
        +getP1() Poisson
        +getP2() Poisson
        +equals(o) boolean
        +toString() String
    }
    class Zone {
        -int numZone
        -int x
        -int y
        +Zone(numZone, x, y)
        +getNumZone() int
        +getZoneX() int
        +getZoneY() int
    }
    class Carte {
        -int id
        -String nom
        -String description
        -String imagePath
        +Carte(id, nom, description, imagePath)
        +getNom() String
        +getDescription() String
        +getImagePath() String
    }
    class Pioche {
        -ArrayList~Carte~ lstCartes
        -ArrayList~Carte~ pioche
        -ArrayList~Carte~ defausse
        -Carte carteCourante
        +Pioche(nbSymboles)
        +reset() void
        +melanger() void
        +piocher() Carte
        +estVide() boolean
        +getNbCartesRestantes() int
    }
    class Points {
        -int idJoueur
        -int idManche
        -int pointsZonesVisitees
        -int pointsPoissons
        -int pointsManche
        -int pointsTotal
        +Points(idJoueur, idManche)
        +calculerPointsTotal() void
        +pointsZonesVisitees(nb) int
        +pointsPoissonsCapturesZones(nb) int
        +resetPointsManche() void
        +getPointsTotal() int
    }
    class ProgressionLabo {
        -Poisson extremite1
        -Poisson extremite2
        -int x1, y1, x2, y2
        -String couleurLabo
        -ArrayList~Liaison~ lstLiaisonsParcourues
        +ProgressionLabo(p)
        +getExtremite1() Poisson
        +getExtremite2() Poisson
        +setExtremite1(p) void
        +setExtremite2(p) void
        +ajouterLiaison(l) void
        +contientLiaison(l) boolean
    }
    class LireDonnees {
        -Plateau plateau
        +LireDonnees(plateau)
        +lireGrille(dossier) void
        +lirePoissons(dossier) void
        +lireLiaisons(dossier) void
        +lireZones(dossier) void
        +lireLabo(dossier) void
    }
    class Couleur {
        <<enumeration>>
        BLEU_GLACIAIRE
        BLEU_LAGON
        ...
        LABO_ROUGE
        LABO_BLANC
        -String libelle
        -int r, v, b
        +getLibelle() String
        +getR() int
        +getV() int
        +getB() int
        +valueOf(code)$ Couleur
    }

    %% Héritage Swing
    JFrame <|-- FrameDebut
    JFrame <|-- FrameTable
    JFrame <|-- FrameTirage
    JPanel <|-- PanelDebut
    JPanel <|-- PanelTable
    JPanel <|-- PanelTirage
    ActionListener <|.. PanelDebut
    ActionListener <|.. PanelTirage
    MouseAdapter <|-- GereSouris

    %% Contrôleur <-> IHM
    Controleur "1" --> "1" FrameDebut
    Controleur "1" --> "1" FrameTable
    Controleur "1" --> "1" FrameTirage
    FrameDebut --> Controleur
    FrameTable --> Controleur
    FrameTirage --> Controleur
    FrameDebut *-- PanelDebut
    FrameTirage *-- PanelTirage
    FrameTable ..> PanelTable : crée
    PanelDebut --> Controleur
    PanelTable --> Controleur
    PanelTirage --> Controleur
    PanelTable +-- GereSouris

    %% Contrôleur <-> Métier
    Controleur "1" --> "1" Plateau : metier
    Controleur "1" --> "1" Pioche
    Controleur ..> Carte
    Controleur ..> Poisson

    %% Relations internes au métier
    Plateau "1" --> "*" Poisson : grillePoisson
    Plateau "1" --> "*" Zone : grilleZone
    Plateau "1" --> "*" Liaison : lstLiaisons
    Plateau "1" --> "1" Points
    Plateau "1" --> "0..1" ProgressionLabo
    Plateau ..> LireDonnees : utilise
    Plateau ..> Couleur
    LireDonnees --> Plateau
    Liaison "1" --> "2" Poisson : p1, p2
    ProgressionLabo --> Poisson : extremite1/2
    ProgressionLabo "1" --> "*" Liaison
    Pioche "1" --> "*" Carte
```
