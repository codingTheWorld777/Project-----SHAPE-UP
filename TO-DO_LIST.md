# Project---SHAPE-UP_TO-DO LIST

See my repository at: [Huu Khai NGUYEN's Project-----SHAPE UP](https://github.com/codingTheWorld777/Project-----SHAPE-UP)

## Phase 1: UML  
- Updating...

## Phase 2: Jouer en Commande-line
- Ecrire les classes en java	 **[OK]**
- Créer les joueurs (Choisir les joueurs réels et virtuels) **[OK]** *(mais sans joueur - virtuel)*
- Créer toutes les cartes pour le jeu (en créant un tableau contenant toutes les cartes) et les distribuer (distribuer les carte victoires pour chaque joueur et éliminer la carte cachée, mis à jour le tableau) -> **Huu Khai [OK]** *(peut-être mettre à jour après)*
- Définir l'algorithme de choisir les positions possibles (pour le joueurPhy et le joueurVir) et de former la forme du rectangle 5x3 -> **Huu Khai [OK]**  *done with joueurPhy (sans joueurVir)*
- Implémenter la classe Plateau.java qui est le coeur du programme et qui va controller toutes opérations du jeu (déterminer la forme du tapis,...) -> **Huu Khai [OK]**
- Définir l'algorithme de déplacer une carte pour joueurPhy vers une position possible -> **Huu Khai [OK]**
- Définir toutes les méthodes dans le fichier Partie.java -> **Huu Khai [OK]**
- Définir les méthodes dans la classe ** *JoueurVir* **: stratégie, fonctionnalités du joueurVir (déplacer, piocher une carte automatiquement) -> **Huu Khai etLouis [OK]**
- Implémenter un patron de conception “Stratégie" comprend tous les algorithmes importants du jeu: 
    + Écrire un algorithme qui permet de déplacer la carte -> **Huu Khai [OK]**
    + Régler le niveau du joueur virtuel **[OK]**
    + Compter les points de chacun basés sur sa carte victoire -> **Louis [OK]**
    + …


## Phase 3: Jouer en GUI
- Créer une fenêtre graphique qui permet au joueur de choisir le nombre de joueurs (2-3), activer le joueurVir (Oui or Non), choisir le niveau pour le joueurVir (dans le cas activer ce joueur), choisir le nom pour chaque joueur -> **Huu Khai [OK]**

- Créer une fenêtre du jeu qui comprend: les joueurs par rapport le nombre de joueurs choisis (2 or 3, avec le joueurVir ou pas), une table du jeu (un tableau de 7x5), les cartes jouées avec ses images affichées -> **Huu Khai [OK]**

- Définir la classe Controleur dans le fichier Controleur qui permet de prendre et d'envoyer des données, créer d'interaction entre Modele et Vue:
	+ Implémenter ControleurParametre qui permetre de controler les choix de joueur et de renvoyer les données pour créer la fenêtre 	du jeu -> **Huu Khai [OK]**
	+ Implémenter ControleurParametre qui permetre de controler l'interface graphiphique du jeu -> **Huu Khai et Louis [OK]**
	
# Choses à faire pour la prochaine étape:
- Créer une fenêtre du jeu qui comprend: les joueurs par rapport le nombre de joueurs choisis (2 or 3, avec le joueurVir ou pas), une table du jeu (un tableau de 7x5), la pioche de cartes -> **Huu Khai [OK]**

- Manipuler les événement de l’interface (Il faut bien choisir le component de la carte sur l’interface qui permet le joueur de la déplacer -> **Huu Khai et Louis [OK]**

- Implémenter ‘Observable et Observer' avec les Controleurs qui vont controler et mettre à jour le changement et puis repeindre l'interface du jeu -> **Louis et Huu Khai [OK]**

- Écrire la classe VueText.java qui permet de jouer au jeu sur l’interface graphique en même temps que sur la ligne de commande -> **Huu Khai et Louis [OK]**



