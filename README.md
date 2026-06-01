# SAE-2.01-2.02-2.05


RECHERCHES MARINES

Règles du jeu

MATÉRIEL

Une grille 7x7 (ou configurable) découpée en régions (mers et océans)
Chaque case contient un poisson (espèce distincte)
Un paquet de cartes: (nombre de couleurs + 1) × 2 cartes au total
X points de départ, un par couleur
1 ou plusieurs joueurs (ou joueur unique)
Un ordinateur avec le jeu exécuté en Java


OBJECTIF

Obtenir le score maximum à la fin de toutes les manches. Le score provient 
des régions explorées et des poissons dominés.


COMPOSITION DU PAQUET DE CARTES

Le paquet contient (nombre de couleurs + 1) × 2 cartes réparties ainsi:

Moitié claires + Moitié foncées (50/50)
Dans les cartes claires: 4 cartes Poisson + 1 carte Joker
Dans les cartes foncées: 4 cartes Poisson + 1 carte Joker

Exemple avec 3 couleurs (Vert, Bleu, Rouge):
Total de cartes: (3 + 1) × 2 = 8 cartes
4 cartes claires: Morue, Saumon, Thon, Truite, Joker
4 cartes foncées: Morue, Saumon, Thon, Truite, Joker


DÉROULEMENT DU JEU


Avant le jeu

1. Définir le nombre de couleurs (et donc de manches).
2. Assigner un point de départ par couleur sur la grille.
3. Mélanger le paquet de cartes et le placer face cachée.


Déroulement d'une manche

Une manche = une couleur = un joueur (ou plusieurs joueurs jouent tour à 
tour avec la même couleur).

1. Le joueur démarre sur son point de départ (case colorée initiale).

2. À chaque tour, le joueur pioche une carte du paquet.

3. Selon la carte piocher:
   
    Carte Poisson (Morue, Saumon, etc.): Le joueur regarde les 8 cases 
    adjacentes (haut, bas, gauche, droite, et 4 diagonales). S'il existe 
    une case voisine avec ce poisson, il peut s'y déplacer. Cette case 
    prend alors sa couleur. S'il n'y a pas ce poisson voisin, il ne bouge 
    pas et passe au prochain tour.
   
    Carte Joker: Le joueur peut se déplacer sur n'importe quelle case 
    voisine (8 cases adjacentes), peu importe le poisson. Cette case 
    prend sa couleur.

4. Dès que le joueur pioche une carte foncée: il vérifie si c'est la 
   dernière carte foncée du paquet. Si oui, la manche s'arrête immédiatement. 
   Sinon, le jeu continue.

5. Fin de manche: Quand la dernière carte foncée est piocher, la manche 
   s'arrête. Le paquet est remélangé et préparer pour la manche suivante. 
   On change de couleur et on passe au point de départ de la nouvelle 
   couleur.


CALCUL DES POINTS


À la fin de toutes les manches, on calcule le score final pour chaque 
couleur:


Points d'exploration

Pour chaque couleur: 1 point par région (mer ou océan) dans laquelle la 
couleur a posé au moins un poisson.

Exemple: Si le Vert a visité 3 régions différentes, il marque 3 points 
d'exploration.


Points de domination

Pour chaque région:

1. Compter les poissons coloriés par chaque couleur.
2. La couleur ayant le plus de poissons dans cette région gagne 1 point 
   par poisson qu'elle possède dans celle-ci.

Exemple: Dans la région Océan Atlantique, Vert a 5 poissons, Bleu en a 3, 
Rouge en a 2. Vert domine et marque 5 points pour cette région. Bleu et 
Rouge ne marquent rien ici.


Score total

Score final = Points d'exploration + Points de domination

Le joueur (ou la couleur) avec le score le plus haut gagne.


MOUVEMENTS AUTORISÉS

Un joueur peut se déplacer vers 8 cases adjacentes (incluant les diagonales):

    ↖  ↑  ↗
    ← [X] →
    ↙  ↓  ↘


CAS PARTICULIERS

Pas de poisson voisin correspondant à la carte: Le joueur ne bouge pas 
et passe son tour.

Une case est déjà colorée par une autre couleur: Elle peut être reprise. 
La couleur précédente n'obtient pas de points rétroactifs.

Égalité en domination: Si deux couleurs ont le même nombre de poissons 
dans une région, aucune ne marque le point de domination pour cette 
région.


RÉSUMÉ RAPIDE

1. Chaque manche = une couleur
2. Piocher une carte et avancer si possible
3. Arrêter quand la dernière carte foncée est piocher
4. Compter régions visitées + poissons dominés
5. Plus haut score gagne

