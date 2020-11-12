package Modele;

import java.util.Scanner;

public class JoueurPhy extends Joueur {
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
			Plateau.misAJourListe(tableDuJeu[y][x], x, y);
			Plateau.ajouterCoordonneePossible(x, y);
			PiocheCartes.getPiocheCartes().remove(Partie.nombreDeCartesJouables - 1);
			Partie.nombreDeCartesJouables--;
			
			Plateau.updateTableDuJeu();
		} 
		
		/*
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
				Plateau.misAJourListe(tableDuJeu[y][x], x, y);
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
	
	public void deplacerCarte() {
		System.out.println("Déplacer la carte à la position (x, y) = ");
	
	}
	
}
