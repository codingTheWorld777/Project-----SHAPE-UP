package Modele;

import java.util.Scanner;

public class JoueurPhy extends Joueur {
	/**
	 *@author Huu Khai NGUYEN (Alec)
	 */
	public JoueurPhy(String name, int id) {
		super(name, id);
	}
	
	public void piocherCarte(Carte[][] tableDuJeu, int tour) {
		System.out.println("Choisir l'abscisse x de carte sur le table du jeu: ");
		Scanner src = new Scanner(System.in);
		int x = src.nextInt();
		
		System.out.println("Choisir l'ordonnée y de carte sur le table du jeu: ");
		int y = src.nextInt();
		
		/*
		 * First card on the table:
		 	* Choose coordinates (x, y) 
		 	* Add this card to table in order to print it on the screen
		 	* Add this card to "CartesJouees" list by misAJourListe()
		 	* Add possbilities (x, y) to "possibilities" list of possible coordinates
		 	* Remove this card from "piocheCartes"
		 	* Substract 1 to "nombreDeCartesJouables" and update table of game
		 */
		if (tour == 0) {
			tableDuJeu[y][x] = PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1);
			Plateau.misAJourListeCartesJouees(tableDuJeu[y][x], x, y);
			Plateau.ajouterCoordonneePossible(x, y);
			PiocheCartes.getPiocheCartes().remove(Partie.nombreDeCartesJouables - 1);
			Partie.nombreDeCartesJouables--;
			
			for (int t = 0; t < Plateau.possibilites.size(); t++) {
				System.out.print("(" + Plateau.possibilites.get(t).x + ", " + Plateau.possibilites.get(t).y + "), ");
			}
			System.out.println();
			
			Plateau.updateTableDuJeu();
		} 
		
		/**
		 * From second card 
		 	* Choose coordinates (x, y)
		 	* Check this coordinates with "CartesJouees's coordinates " with "verifierPos(x, y)":
		 	  * if failed, run again
		 	  * if passed: 
		 	  	* Add this card to "CartesJouees" by...
		 	  	* Add possible coordinates to "possibilites" by ajouterCoordonneesPossible	 
		 */
		if (tour >= 1) {
			if (Plateau.verifierPos(x, y) && !Plateau.verifierAvecCartesJouees(x, y)) {
				tableDuJeu[y][x] = PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1);
				Plateau.misAJourListeCartesJouees(tableDuJeu[y][x], x, y);
				Plateau.determinerRec(Plateau.cartesJouees);
				Plateau.supprimerCoordonnee(x, y);
				Plateau.ajouterCoordonneePossible(x, y);
				Plateau.determinerRec(Plateau.cartesJouees);
				PiocheCartes.getPiocheCartes().remove(Partie.nombreDeCartesJouables - 1);
				Partie.nombreDeCartesJouables--;
				
				for (int t = 0; t < Plateau.possibilites.size(); t++) {
					System.out.print("(" + Plateau.possibilites.get(t).x + ", " + Plateau.possibilites.get(t).y + "), ");
				}
				System.out.println();
				
				Plateau.updateTableDuJeu();
				
			} else {
				System.out.println("Cette position ne correspond pas");
				
				this.piocherCarte(tableDuJeu, tour);
			}
		}
	
	}
	
	/*
	 * Choose and move a card to a new position (if it's possible) by respecting the rule of mouvement
	 */
	public void deplacerCarte() {
		System.out.println("Voulez-vous déplacer une carte?: (0/1): ");
		Scanner src = new Scanner(System.in);
		int deplacer = src.nextInt();
		
		if (deplacer == 1) {
			System.out.println("Choisir l'abscisse x de carte que vous voulez déplacer: ");
			int x = src.nextInt();
			
			System.out.println("Choisir l'ordonnée y de carte que vous voulez déplacer: ");
			int y = src.nextInt();
			boolean check = Plateau.estDeplacable(x, y);
			System.out.println(check);
			
			if (check) {
				System.out.println("Choisir l'abscisse x de position que vous voulez déplacer carte à: ");
				int x1 = src.nextInt();
				
				System.out.println("Choisir l'ordonnée y de position que vous voulez déplacer carte à: ");
				int y1 = src.nextInt();
				
				for (Coordonnees positionDeDeplacer: Plateau.positionDeDeplacer) {
					if (positionDeDeplacer.x == x1 && positionDeDeplacer.y == y1) {
						/*
						 * if all conditions of mouvement are satisfied: 
						 * 	+ Remove card (for example card X) out of the table of game
						 * 	+ Delete its position out of Plateau.possibilites and update all possible position
						 * 		which are related to this card's position
						 * 	+ Move card X to new position
						 * 	+ Update Plateau.possibilities by using Plateau.ajouterCoordonneePossible
						 * 	+ Release Plateau.positionDeDeplacer to save memory
						 */
						Carte carte = Partie.getTableDuJeu()[y][x];
						carte.setCoordonnees(x1, y1); 	//reset coordinate of card
						Partie.getTableDuJeu()[y][x] = null;
						Plateau.misAJourListePossibilites(x, y);
						Partie.getTableDuJeu()[y1][x1] = carte;
						Plateau.ajouterCoordonneePossible(x1, y1);
						
						/*
						 * Check if there are some possible position of mouvement card that has the same possible position
						 * 	with is are positioned around this card 
						 */
						if (x1 - 1 >= 0 && Partie.getTableDuJeu()[y1][x1 - 1] != null && ((y - 1 == y1) || (y + 1 == y1))) 
							Plateau.ajouterCoordonneePossible(x1 - 1, y1); 
						if (x1 + 1 <= 6 && Partie.getTableDuJeu()[y1][x1 + 1] != null && ((y - 1 == y1) || (y + 1 == y1))) 
							Plateau.ajouterCoordonneePossible(x1 + 1, y1);
						if (y1 - 1 >= 0 && Partie.getTableDuJeu()[y1 - 1][x1] != null && ((x - 1 == x1) || (x + 1 == x1))) 
							Plateau.ajouterCoordonneePossible(x1, y1 - 1);
						if (y1 + 1 <= 4 && Partie.getTableDuJeu()[y1 + 1][x1] != null && ((x - 1 == x1) || (x + 1 == x1))) 
							Plateau.ajouterCoordonneePossible(x1, y1 + 1);
						
						
						Plateau.positionDeDeplacer.clear();
						Plateau.updateTableDuJeu();
						
						break;
					}
				}
				
			} else {
				System.out.println("Cette position ne correspond pas. Choissiez-vous encore une fois!");
				deplacerCarte();
			}
		}
	}

	
}
