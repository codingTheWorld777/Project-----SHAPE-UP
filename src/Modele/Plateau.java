package Modele;

import java.util.ArrayList;

public class Plateau {
	/*
	 * Add and save all cards that were played
	 */
	private static ArrayList<Carte> cartesJouees = new ArrayList<Carte>();
	
	/*
	 * Save all possibilities of the position that player can put a card in it
	 */
	private static ArrayList<Coordonnees> possibilites = new ArrayList<Coordonnees>();
	
	
	/*
	 * If player can add card to one position, add new possible position that player can choose for next turn
	 	 * Compare with list before (in possibilities[])
		 * Compare with position of each card (in cartesJouees[])
		
	 * Note that for each position that required by player, there are 4 possible options which can add to possibilities[]
	 */
	public static void ajouterCoordonneePossible(int x, int y) {
		int i, j;
		int compteur = 1;
		
		while (compteur <= 4) {
			j = x;
			i = y;
			
			//Need to check with coordinates of cards played @@@@
			if (compteur == 1 && ++j <= 6) {		
				Coordonnees coordonnees = new Coordonnees(j, i);
				if (!Plateau.verifierPos(j, i) && !Plateau.verifierAvecCartesJouees(j, i)) 
					Plateau.possibilites.add(coordonnees);
				
			} else if (compteur == 2 && --j >= 0) {
				Coordonnees coordonnees = new Coordonnees(j, i);
				if (!Plateau.verifierPos(j, i) && !Plateau.verifierAvecCartesJouees(j, i)) 
					Plateau.possibilites.add(coordonnees);
				
			} else if (compteur == 3 && ++i <= 4) {
				Coordonnees coordonnees = new Coordonnees(j, i);
				if (!Plateau.verifierPos(j, i) && !Plateau.verifierAvecCartesJouees(j, i)) 
					Plateau.possibilites.add(coordonnees);
				
			} else if (compteur == 4 && --i >= 0) {
				Coordonnees coordonnees = new Coordonnees(j, i);
				if (!Plateau.verifierPos(j, i) && !Plateau.verifierAvecCartesJouees(j, i)) 
					Plateau.possibilites.add(coordonnees);	
			}
			
			compteur++;
		}
		
		for (int k = 0; k < possibilites.size(); k++) {
			System.out.println("(" + possibilites.get(k).x + ", " + possibilites.get(k).y + ")");
		}
	}
	
	
	/*
	 * Remove coordinates that is similar in the list
	 */
	public static void supprimerCoordonnee(int x, int y) {
		for (int i = 0; i < Plateau.possibilites.size(); i++) {
			if (Plateau.possibilites.get(i).x == x && Plateau.possibilites.get(i).y == y) {
				Plateau.possibilites.remove(i);
			}
		}
	}
	
	
	/*
	 * Check with all possibilities defined if the position is available
	 */
	public static boolean verifierPos(int x, int y) {	
		for (int i = 0; i < possibilites.size(); i++) {
			if (possibilites.get(i).x == x && possibilites.get(i).y == y) return true;
		}
		
		System.out.print(false + " ");
		return false;
	}
	
	
	/*
	 * Check position chosen with position of card played
	 */
	public static boolean verifierAvecCartesJouees(int x, int y) {
		for (int i = 0; i < cartesJouees.size(); i++) {
			if (cartesJouees.get(i).getCoordonnees().x == x && cartesJouees.get(i).getCoordonnees().y == y) 
				return true;
		}
		
		return false;
	}
	
	
	/*
	 * Update list of cards played 
	 */
	public static void misAJourListe(Carte carte, int x, int y) {
		Plateau.cartesJouees.add(carte);
		carte.setCoordonnees(x, y);
	}
	
	
	/*
	 * Print table of game to screen
	 */
	public static void updateTableDuJeu() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				if (Partie.getTableDuJeu()[i][j] != null) {
					System.out.print("|X|");
				} else {
					System.out.print("| |");
				}
			}
			System.out.println();
		}
	}
	
}

