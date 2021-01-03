# Project-----SHAPE UP

See my repository at: [Huu Khai NGUYEN's Project-----SHAPE UP](https://github.com/codingTheWorld777/Project-----SHAPE-UP)

## --[FR-Description]--
### 1) Introduction
- Dans le cadre d'un [cours de branche sur la programmation en Java (LO02)](https://moodle.utt.fr/course/search.php?search=lo02) dirigé par **Guillaume DOYEN** à l'[Université de Technologie de Troyes](https://www.utt.fr), j'ai réalisé en binôme avec **Pierre-Louis DAMBRAINE** un jeu appelé **SHAPE UP**. 
- Les règles sont trouvables ici: [SHAPE UP-règles](http://goodlittlegames.co.uk/games/06-shape-up.html). Vous trouverez aussi le sujet du projet dans [ce fichier PDF](https://moodle.utt.fr/pluginfile.php/22853/mod_resource/content/3/PROJET%20LO02.pdf)

### 2) Mode d'emploi

#### a) Paramétrer le jeu: 
- Après avoir démarré le jeu, une fenêtre de paramètre va afficher qui permet le joueur de paramétrer le jeu: 
	+ Choisir la variante de tapis du jeu
	+ Choisir le nombre de joueurs
	+ Activer le joueur virtuel et choisir son niveau
	
Clique sur le bouton **"Valider"** pour afficher l'interface graphique du jeu.

#### b) Jouer le jeu:    
- Le joueur peut réaliser 2 actions principales: **Déplacer** ou **Placer** une carte. Vous trouverez au dessous une illustration du jeu qui vous permet de visualiser facilement le fonctionnement du jeu:
	+ Pour **placer** une carte, tout d'abord double-clique sur le bouton du bas dans la zone "Piocher une carte" dans le coin inférieur droit de l'écran pour afficher une carte piochée. Quand le joueur clique sur le bouton du bas dans le zone "Piocher une carte", les positions qu'il peut placer la carte deviendront vertes (sauf la première fois que vous tirez la carte). Le joueur peut ensuite placer la carte sélectionnée dans la position indiquée en vert.
	+ Pour **déplacer** une carte, le joueur doit cliquer une carte sur la table du jeu, si cette carte est deplacable, les positions dans lesquelles la carte peut être déplacée deviendront vertes. Sinon, rien ne changera. 
    
- Le joueur peut finir son tour si et seulement s'il a déjà placé une carte. A partir le moment où le joueur placé une carte, il peut arrêter son tour. (peut-être l'arrêter sans déplacer une carte). Quand le joueur a terminé le tour avec succès, le panel de ce joueur deviendra rouge et le Panel du joueur suivant deviendra verte pour annoncer le tour du joueur suivant, et le point de chaque joueur sera mis à jour.

- Si le jeu a fini, clique sur le bouton **"Tour suivant"** pour passer sur le nouveau tour.
	
**Petit note:** </u> Comme nous ne savons pas quelle action le joueur choisissira en premier (il peut choisir de ne déplacer que la première carte ou de piocher la première carte), donc comme le joueur change continuellement son choix. Pour cette raison, le jeu peut prendre 1 à 2 secondes à traiter, il peut donc ne pas être en mesure de mettre à jour l'interface graphique immédiatement. Nous avons 2 situations qui pourraient se produire ici:
- Lorsque vous cliquez sur le bouton de sélection mais qu'il y a des endroits où la carte ne devient pas verte, veuillez cliquer sur ce bouton au moins une fois pour mettre à jour l'interface graphique.
- Lorsque vous ne parvenez pas à placer une carte après en avoir déplacée (bien que les emplacements deviennent verts), cliquez sur votre bouton "Finir mon tour" si vous êtes actuellement à votre tour.

# Screenshots
![Screenshot-Window selection](https://imgur.com/qoYsd8V.png)
![Screenshot-SHAPE UP game](https://imgur.com/ly8JobM.png)

## --[EN-Description]--
### 1) Introduction
- As part of a [branch course of Oriented-Object Programming in java (LO02)](https://moodle.utt.fr/course/search.php?search=lo02) directed by **Guillaume DOYEN** at the [University of Technology of Troyes](https://www.utt.fr),
I realized in pairs with my friend **Pierre-Louis DAMBRAINE** a game called **SHAPE UP**.
- The rules of game can be found here: [SHAPE UP-rules](http://goodlittlegames.co.uk/games/06-shape-up.html). You will also find the subject of the project in [this PDF file](https://moodle.utt.fr/pluginfile.php/22853/mod_resource/content/3/PROJET%20LO02.pdf)

### 2) User manual 
#### a) Parameter the game

#### b) Play game on GUI
- The player can perform 2 main actions: **Move** or **Place** a card. Above is an illustration of the game that allows you to easily see how the game works:
	+ To **place a card**, first double-click the bottom button in the **"Draw Card"** area in the lower right corner of the screen to display a drawn card. When the player clicks the bottom button in the **“Draw a card”** area, the positions that the player can place the card will turn green (except the first time you draw the card). The player can then place the selected card in the position indicated in green.
	+ To **move a card**, the player must click a card on the game table, if this card is movable, the positions in which the card can be moved will turn green. Otherwise, nothing will change.
	
- The player can end his turn if and only if he has already placed a card. From the moment the player places a card, he can stop his turn. (maybe stop it without moving a card). When the player has successfully completed his turn, that player's panel will turn red and the next player's panel will turn green to announce the next player's turn, and each player's point will be updated.

If the game is over, click on the "Next round" button to move on to the new round.

**Small note**: As we do not know which action the player will choose first (he can choose to move only the first card or to draw the first card), so as the player continually changes his choice. Because of this, the game may take 1-2 seconds to process, so it may not be able to update the GUI immediately. We have 2 situations that could occur here:
- When you click the select button in the **"Draw Card"** area but there are maybe some places where the possible position(s) does not turn green, please click this button at least once to update the GUI.
- When you are unable to place a card after moving one (although the spaces turn green), click your **"End my turn"** button if you are currently on your turn.

