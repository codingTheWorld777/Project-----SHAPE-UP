# Project---SHAPE-UP_TO-DO LIST

See my repository at: [Huu Khai NGUYEN's Project-----SHAPE UP](https://github.com/codingTheWorld777/Project-----SHAPE-UP)

## Phase 1: UML  
- Updating...

## Phase 2: Jouer en Commande-line
- Ecrire les classes en java	 **[OK]**
- Créer les joueurs (Choisir les joueurs réels et virtuels) **[OK]** *(mais sans joueur - virtuel)*
- Créer toutes les cartes pour le jeu (en créant un tableau contenant toutes les cartes) et les distribuer (distribuer les carte victoires pour chaque joueur et éliminer la carte cachée, mis à jour le tableau) -> **Huu Khai [OK]** *(peut-être mettre à jour après)*
- Définir l'algorithme de choisir les positions possibles (pour le joueurPhy et le joueurVir) et de former la forme du rectangle 5x3 -> **Huu Khai [OK]**  *done with joueurPhy (sans joueurVir)*
- Définir l'algorithme de déplacer une carte pour joueurPhy vers une position possible -> **Huu Khai [OK]**
- Définir toutes les méthodes dans le fichier Partie.java -> **Huu Khai [OK]**
- Définir les méthodes dans la classe ** *JoueurVir* **: stratégie, fonctionnalités du joueurVir (déplacer, piocher une carte automatiquement) -> **Louis**
- Implémenter un patron de conception “Stratégie" compris tous les algorithmes importants du jeu: 
    + Écrire un algorithme qui permet de déplacer la carte -> **Huu Khai [OK]**
    + Régler le niveau du joueur virtuel
    + Compter les points de chacun basés sur sa carte victoire -> **Louis [OK]**
    + …


## Phase 3: Jouer en GUI
- Créer une fenêtre graphique qui permet au joueur de choisir le nombre de joueurs (2-3), activer le joueurVir (Oui or Non), choisir le niveau pour le joueurVir (dans le cas activer ce joueur), choisir le nom pour chaque joueur -> **Huu Khai [OK]**

- Créer une fenêtre du jeu qui comprend: les joueurs par rapport le nombre de joueurs choisis (2 or 3, avec le joueurVir ou pas), une table du jeu (un tableau de 7x5), les cartes jouées avec ses images affichées -> **Louis et Huu Khai**

- Définir la classe Controleur dans le fichier Controleur qui permet de prendre et d'envoyer des données, créer d'interaction entre Modele et Vue -> **Huu Khai et Louis**

# Choses à faire pour la prochaine étape:
- Créer une fenêtre du jeu qui comprend: les joueurs par rapport le nombre de joueurs choisis (2 or 3, avec le joueurVir ou pas), une table du jeu (un tableau de 7x5), la pioche de cartes -> **Huu Khai et Louis**

- Manipuler les événement de l’interface (Il faut bien choisir le component de la carte sur l’interface qui permet le joueur de la déplacer -> **Huu Khai et Louis []**

- Implémenter ‘Observable et Observer' avec les Controleurs qui vont controler et mettre à jour le changement et puis repeindre l'interface du jeu -> **Louis et Huu Khai []**

- Écrire la classe VueText.java qui permet de jouer au jeu sur l’interface graphique en même temps que sur la ligne de commande



