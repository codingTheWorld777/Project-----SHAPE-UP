package Modele;

import java.util.ArrayList;

public class Plateau {
	/*
	 * Add and save all cards that were played
	 */
	protected static ArrayList<Carte> cartesJouees = new ArrayList<Carte>();
	
	/*
	 * Save all possibilities of the position that player can put a card in it
	 */
	protected static ArrayList<Coordonnees> possibilites = new ArrayList<Coordonnees>();
	
	
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
		 
//		for (int k = 0; k < possibilites.size(); k++) {
//			System.out.print("(" + possibilites.get(k).x + ", " + possibilites.get(k).y + "), ");
//		}
//		System.out.println();
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
	 * 
	 * Check with all possibilities defined if the position is available
	 */
	public static boolean verifierPos(int x, int y) {	
		for (int i = 0; i < possibilites.size(); i++) {
			if (possibilites.get(i).x == x && possibilites.get(i).y == y) return true;
		}
		
//		System.out.print(false + " ");
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
	 * Reduce the selection of the card's table once you have determined the edge of the rectangle
	 * Determine the shape of the final rectangle 5x3 base on cards played 
	 	* We determine this rectangle by its coordinates (x, y)
	 	* Minimize zone of cards that can be put into table, Check with all possibilities defined 
	 	 	if the position is available
	 */
	public static void determinerRec(ArrayList<Carte> cartesJouees) {
		int xMin = (int) Double.POSITIVE_INFINITY, xMax = -1;
		int yMin = (int) Double.POSITIVE_INFINITY, yMax = -1;
		
		for (int i = 0; i < cartesJouees.size(); i++) {
			 if (cartesJouees.get(i).getCoordonnees().x < xMin) xMin = cartesJouees.get(i).getCoordonnees().x;
			 
			 if (cartesJouees.get(i).getCoordonnees().x > xMax) xMax = cartesJouees.get(i).getCoordonnees().x;
			 
			 if (cartesJouees.get(i).getCoordonnees().y < yMin) yMin = cartesJouees.get(i).getCoordonnees().y;
			 
			 if (cartesJouees.get(i).getCoordonnees().y > yMax) yMax = cartesJouees.get(i).getCoordonnees().y;
		}
//		System.out.println("xMin = " + xMin + ", xMax = " + xMax);
		
		if (xMax - xMin + 1 >= 4) {
			if (xMax - xMin + 1 == 5) {
				for (int j = 0; j < possibilites.size(); j++) {
					if (possibilites.get(j).x < xMin || possibilites.get(j).x > xMax) {
						possibilites.remove(j);
					}
				}
			}
			
			if (yMax - yMin + 1 == 3) {
				for (int k = 0; k < possibilites.size(); k++) {
					if (possibilites.get(k).y < yMin || possibilites.get(k).y > yMax) {
						possibilites.remove(k);
					}
				}
			}
			
		} else if (yMax - yMin + 1 >= 4) {
			if (yMax - yMin + 1 == 5) {
				for (int j = 0; j < possibilites.size(); j++) {
					if (possibilites.get(j).y < yMin || possibilites.get(j).y > yMax) {
						possibilites.remove(j);
					}
				}
			}
			
			if (xMax - xMin + 1 == 3) {
				for (int k = 0; k < possibilites.size(); k++) {
					if (possibilites.get(k).x < xMin || possibilites.get(k).x > xMax) {
						possibilites.remove(k);
					}
				}
			}
		}
		
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
		System.out.println();
	}
	
}

