#SAE-2.01-2.02-2.05 : RECHERCHES MARINES

Règles du jeu

MATÉRIEL

Une grille configurable découpée en régions (mers et océans).
Certaines cases contiennent un poisson d'une espèce spécifique.
Un ensemble de laboratoires de recherche de couleurs différentes.
Chaque laboratoire correspond au point de départ d'une manche.
Un paquet de cartes composé de (Nombre de laboratoire + 1) x 2 cartes au total.
X points de départ répartis sur la grille (un par couleur de laboratoire).
Conçu pour 1 ou plusieurs joueurs.
Un ordinateur exécutant le jeu en Java.

OBJECTIF

Obtenir le score total le plus élevé à la fin de toutes les manches.
Les points se gagnent en explorant les régions et en étudiant les poissons.

COMPOSITION DU PAQUET DE CARTES

Le paquet est divisé équitablement en deux catégories :
50% de cartes claires et 50% de cartes foncées.

Exemple pour un jeu à 4 laboratoire:

- Cartes claires : 4 cartes Poisson + 1 carte Joker
- Cartes foncées : 4 cartes Poisson + 1 carte Joker

Donc un total de 10 cartes

DÉROULEMENT DU JEU

Avant de commencer (Configuration)

1. Définir la taille de la grille de jeu.
2. Définir le nombre d'espèces de poissons différentes.
3. Définir le nombre de laboratoires de recherche (ce qui détermine le nombre de manches).
4. Assigner un point de départ sur le plateau pour chaque laboratoire.
5. Mélanger le paquet de cartes et le placer face cachée ou face visible.

Déroulement d'une manche

Une manche correspond à un laboratoire de recherche. Si plusieurs joueurs s'affrontent,
ils jouent sur la même grille mais démarrent chacun leur manche depuis un laboratoire
(et donc un point de départ) différent.

1. Le joueur commence sur la case de son laboratoire de recherche.
2. À chaque tour, le joueur pioche une carte.
3. Action selon la carte piochée :

- Carte Poisson : Le joueur observe les cases adjacentes disponible
  (horizontal, vertical et diagonal). S'il existe une case voisine contenant ce poisson,
  il peut s'y déplacer. Le chemin emprunté prend alors la couleur du laboratoire actuel.
  Si aucun poisson correspondant n'est disponible autour de lui, le joueur reste sur place.
- Carte Joker : Le joueur peut se déplacer sur n'importe quelle case adjacente, peu importe le poisson qui s'y trouve. Le chemin emprunté prend la couleur du laboratoire.

4. Option de jeu : Après avoir pioché, un joueur a toujours le droit de passer son tour, même s'il a la possibilité de se déplacer.
5. Fin de la manche : Dès que la dernière carte foncée est piochée, la manche s'arrête immédiatement. Le paquet est remélangé pour la manche suivante. On change de laboratoire et le jeu reprend depuis le nouveau point de départ.

MOUVEMENTS AUTORISÉS

Les déplacements se font dans les 8 directions adjacentes (haut, bas, gauche, droite et les 4 diagonales).

CALCUL DES POINTS

À la fin de chaque manche, on calcule le score du laboratoire actif de la manière suivante :

Points de région :
Le joueur marque 1 point par région distincte visitée au cours de la manche.
Exemple : Si le laboratoire Vert a exploré 3 régions différentes, il gagne 3 points d'exploration.

Points de recherche :
On analyse le nombre de poissons étudiés (recherchés) dans chaque région :

1. Compter les poissons validés par le joueur dans chaque région.
2. La région qui possède le plus grand nombre de poissons étudiés est désignée comme région dominante. Elle rapporte autant de points que son nombre de poissons étudiés.
Exemple : Durant la manche du laboratoire Vert, le joueur a étudié 5 poissons dans la région A et 3 poissons dans la région B. La région A est dominante. Elle rapporte 5 points de recherche pour cette manche.

Score de la manche = Points de région x Points de recherche

Fin de partie :
Une fois toutes les manches terminées, on additionne les scores de chaque manche pour obtenir le score total. Le joueur ayant le score total le plus élevé est déclaré vainqueur.

CAS PARTICULIERS

* Aucun poisson correspondant : Si la carte Poisson piochée ne correspond à aucun poisson sur les cases adjacentes, le joueur reste sur place et passe son tour.
* Croisement de chemins : Un joueur a tout à fait le droit d'emprunter un chemin ou de traverser une case déjà colorée par un autre laboratoire lors d'une manche précédente.
* Égalité finale : Si deux joueurs obtiennent le même score total, le joueur ayant réalisé le meilleur score sur une seule et unique manche remporte la victoire. Si l'égalité persiste, les joueurs partagent la victoire.
