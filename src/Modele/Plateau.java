package Modele;

import java.util.ArrayList;

public class Plateau {
	/**
	 * @author Huu Khai NGUYEN (Alec)
	 */
	
	//1) Check a position if it can be chosen to place a card
	/*
	 * Add and save all cards that were played
	 */
	protected static ArrayList<Carte> cartesJouees = new ArrayList<Carte>();
	
	/*
	 * Save all possibilities of the position that player can put a card in it
	 */
	protected static ArrayList<Coordonnees> possibilites = new ArrayList<Coordonnees>();
	
	/*
	 * Coordinates of 4 vertex of rectangle 5x3 final
	 */
	protected static int xMinDuTapis, xMaxDuTapis, yMinDuTapis, yMaxDuTapis;
	
	
	//2) Check if a card can move to another position and choose the position that player want to move the card to...
	protected static ArrayList<Coordonnees> positionDeDeplacer = new ArrayList<Coordonnees>();
	
	//If a card can be moved but after moving it, cards around it doesn't respect the rule of game
	//so we need to add(draw) next card to the position of this card before moving to the new one
	protected static boolean besoinAjouter = false;
	
	/* (1)
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
	
	
	/* (2)
	 * Remove coordinates that is similar in the list
	 */
	public static void supprimerCoordonnee(int x, int y) {
		for (int i = 0; i < Plateau.possibilites.size(); i++) {
			if (Plateau.possibilites.get(i).x == x && Plateau.possibilites.get(i).y == y) {
				Plateau.possibilites.remove(i);
				break;
			}
		}
	}
	
	
	/* (3) 
	 * 
	 * Check with all possibilities defined if the position is available
	 */
	public static boolean verifierPos(int x, int y) {	
		for (int i = 0; i < possibilites.size(); i++) {
			if (possibilites.get(i).x == x && possibilites.get(i).y == y) return true;
		}
		
		return false;
	}
	
	
	/* (4)
	 * Check position chosen with position of card played
	 */
	public static boolean verifierAvecCartesJouees(int x, int y) {
		for (int i = 0; i < cartesJouees.size(); i++) {
			if (cartesJouees.get(i).getCoordonnees().x == x && cartesJouees.get(i).getCoordonnees().y == y) 
				return true;
		}
		
		return false;
	}
	
	/* (5) : If the variation of game is classic (Rectangle 5x3)
	 * Reduce the selection of the card's table once you have determined the edge of the rectangle
	 * Determine the shape of the final rectangle 5x3 base on cards played 
	 	* We determine this rectangle by its coordinates (x, y)
	 	* Minimize zone of cards that can be put into table, Check with all possibilities defined 
	 	 	if the position is available
	 */
	public static void determinerFormeDuTapis(ArrayList<Carte> cartesJouees) {
		int xMin = (int) Double.POSITIVE_INFINITY, xMax = -1;
		int yMin = (int) Double.POSITIVE_INFINITY, yMax = -1;
		
		for (int i = 0; i < cartesJouees.size(); i++) {
			 if (cartesJouees.get(i).getCoordonnees().x < xMin) xMin = cartesJouees.get(i).getCoordonnees().x;
			 
			 if (cartesJouees.get(i).getCoordonnees().x > xMax) xMax = cartesJouees.get(i).getCoordonnees().x;
			 
			 if (cartesJouees.get(i).getCoordonnees().y < yMin) yMin = cartesJouees.get(i).getCoordonnees().y;
			 
			 if (cartesJouees.get(i).getCoordonnees().y > yMax) yMax = cartesJouees.get(i).getCoordonnees().y;
		}
		
		Plateau.xMinDuTapis = xMin;
		Plateau.xMaxDuTapis = xMax;
		Plateau.yMinDuTapis = yMin;
		Plateau.yMaxDuTapis = yMax;
		
		/*
		 * Determination of the form of game based on the variation which was chosen by player from the beginning of game
		 */
		if (InstallerJeu.getVarianteDuTapis().equals("R")) {	// *forme du tapis est rectangle 5x3*
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
			
		} else if (InstallerJeu.getVarianteDuTapis().equals("C")) {	// *forme du tapis est un carre 4x4*
			if (xMax - xMin + 1 == 4) {
				for (int j = 0; j < possibilites.size(); j++) {
					if (possibilites.get(j).x < xMin || possibilites.get(j).x > xMax) {
						possibilites.remove(j);
					}
				}
			}
			
			if (yMax - yMin + 1 == 4) {
				for (int j = 0; j < possibilites.size(); j++) {
					if (possibilites.get(j).y < yMin || possibilites.get(j).y > yMax) {
						possibilites.remove(j);
					}
				}
			}
			
		} else if (InstallerJeu.getVarianteDuTapis().equals("P")) {  //*forme du tapis: pyramide
			// Check the number of card in each row for variation "Escalade" (nombre de cartes par colonne)
			ArrayList<Integer> nombreDeCartesParCol = new ArrayList<Integer>();
			for (int i = 0; i < 7; i++)
				nombreDeCartesParCol.add(i, 0);
			
			int compteur;
			
			for (int j = 0; j < 7; j++) {
				compteur = 0;
				
				for (int i = 0; i < 5; i++) {
					if (Partie.getTableDuJeu()[i][j] != null) compteur++;
				}
				
				if (compteur > 0) nombreDeCartesParCol.set(j, compteur);
			}
			
			//Delete position (x, y) = (3, 2) - center of table
			for (int i = 0; i < possibilites.size(); i++) {
				if (possibilites.get(i).x == 3 && possibilites.get(i).y == 2) {
					possibilites.remove(i);
					break;
				}
			}		
			
			/*
			 * Determine the form based on the fisrt card on the table (column 0 or column 6)
			 * -> We force the player to place the first card in the column 0 (or 6) at the beginning
			 */
			int parcoursX, parcoursY;
			if (nombreDeCartesParCol.get(0) > 0) {
				if ((Partie.getTableDuJeu()[4][1] == null && Partie.getTableDuJeu()[0][1] != null) 
					|| Partie.getTableDuJeu()[1][2] != null) {
					
					parcoursX = 1;
					parcoursY = 4;
					
					while (parcoursX <= 5 && parcoursY >= 0) {
						for (int k = 0; k < possibilites.size(); k++) {
							for (int index = parcoursX; index <= 5; index++) {
								try {
									if (possibilites.get(k).y == parcoursY && possibilites.get(k).x == index) {
										possibilites.remove(k);
										break;
									}
								} catch (Exception e) {
									System.out.println(e.toString());
								}
							}
						}
						parcoursX++;
						parcoursY--;
						
						if (InstallerJeu.getNombreDeJoueurs() == 3 && parcoursX == 5) parcoursX = 4;
					}	
					
				} else if ((Partie.getTableDuJeu()[0][1] == null && Partie.getTableDuJeu()[4][1] != null)
							|| Partie.getTableDuJeu()[3][2] != null) {
					
					parcoursX = 1;
					parcoursY = 0;
					
					while (parcoursX <= 5 && parcoursY <= 4) {
						for (int k = 0; k < possibilites.size(); k++) {
							for (int index = parcoursX; index <= 5; index++) {
								try {
									if (possibilites.get(k).y == parcoursY && possibilites.get(k).x == index) {
										possibilites.remove(k);
									}
								} catch (Exception e) {
									System.out.println(e.toString());
								}
							}
						}
						parcoursX++;
						parcoursY++;
						
						if (InstallerJeu.getNombreDeJoueurs() == 3 && parcoursX == 5) parcoursX = 4;
					}
				}
				
			} else if (nombreDeCartesParCol.get(6) > 0) {
				if ((Partie.getTableDuJeu()[4][5] == null && Partie.getTableDuJeu()[0][5] != null) 
						|| Partie.getTableDuJeu()[1][4] != null) {
						
					parcoursX = 5;
					parcoursY = 4;
					
					while (parcoursX >= 1 && parcoursY >= 0) {
						for (int k = 0; k < possibilites.size(); k++) {
							for (int index = parcoursX; index >= 1; index--) {
								try {
									if (possibilites.get(k).y == parcoursY && possibilites.get(k).x == index) {
										possibilites.remove(k);
										break;
									}
								} catch (Exception e) {
									System.out.println(e.toString());
								}
							}
						}
						parcoursX--;
						parcoursY--;
						
						if (InstallerJeu.getNombreDeJoueurs() == 3 && parcoursX == 5) parcoursX = 2;
					}
						
				} else if ((Partie.getTableDuJeu()[0][5] == null && Partie.getTableDuJeu()[4][5] != null)
								|| Partie.getTableDuJeu()[3][4] != null) {
						
					parcoursX = 5;
					parcoursY = 0;
					
					while (parcoursX >= 1 && parcoursY <= 5) {
						for (int k = 0; k < possibilites.size(); k++) {
							for (int index = parcoursX; index >= 1; index--) {
								try {
									if (possibilites.get(k).y == parcoursY && possibilites.get(k).x == index) {
										possibilites.remove(k);
									}
								} catch (Exception e) {
									System.out.println(e.toString());
								}
							}
						}
						parcoursX--;
						parcoursY++;
						
						if (InstallerJeu.getNombreDeJoueurs() == 3 && parcoursX == 5) parcoursX = 2;
					}
				}
			}
			
		} 
		
	}
	

	/* (6)
	 * Check a position (x, y) of a card if it is moveable
	 */
	public static boolean estDeplacable(int x, int y) {
		int X, Y;
		boolean estDeplacable = false;
		boolean besoinAjouter = false;
		byte[] nombreEspace = new byte[4];
		
		/* 1) Check if this card is movable
		 * 2) Check the cards around the cards you want to move
			 * 	if there exists at least one of them does not satisfy the movement condition
			 	* when the selected card changes positions: -> Must add 1 card to the position of selected card 
			 * 	else OK
		 */
		Plateau.reloadListePossibilites();
		Plateau.determinerFormeDuTapis(cartesJouees);
		
		if (Partie.getTableDuJeu()[y][x] != null) {
			if (x + 1 <= 6) {
				//1)
				if (Partie.getTableDuJeu()[y][x + 1] == null) {
					Y = y;
					X = x + 1;
					
					if ((X + 1 <= 6 && Partie.getTableDuJeu()[Y][X + 1] != null)
						|| (Y - 1 >= 0 && Partie.getTableDuJeu()[Y - 1][X] != null)
						|| (Y + 1 <= 4 && Partie.getTableDuJeu()[Y + 1][X] != null)) {
						
						if (Plateau.isInPossibilites(X, Y)) {
							Coordonnees position = new Coordonnees(X, Y);
							Plateau.positionDeDeplacer.add(position);
						}
					}
				}
				//2)
				else if (Partie.getTableDuJeu()[y][x + 1] != null) {
					if (y - 1 >= 0 && Partie.getTableDuJeu()[y - 1][x + 1] == null && Plateau.isInPossibilites(x + 1, y - 1) == true) 
						nombreEspace[0]++;
					
					if (y + 1 <= 4 && Partie.getTableDuJeu()[y + 1][x + 1] == null && Plateau.isInPossibilites(x + 1, y + 1) == true) 
						nombreEspace[0]++;
					
					if (nombreEspace[0] == 2) nombreEspace[0]++;
					
					if (x + 2 <= 6 && Partie.getTableDuJeu()[y][x + 2] == null && Plateau.isInPossibilites(x + 2, y) == true) 
						nombreEspace[0]++;
					
//					if (x - 1 >= 0 && Partie.getTableDuJeu()[y][x - 1] == null && Plateau.isInPossibilites(x - 1, y) == true) 
//						nombreEspace[0]++;
				}
			}
			
			if (x - 1 >= 0) {
				//1)
				if (Partie.getTableDuJeu()[y][x - 1] == null) {
					Y = y;
					X = x - 1;
					
					if ((X - 1 >= 0 && Partie.getTableDuJeu()[Y][X - 1] != null)
						|| (Y - 1 >= 0 && Partie.getTableDuJeu()[Y - 1][X] != null)
						|| (Y + 1 <= 4 && Partie.getTableDuJeu()[Y + 1][X] != null)) {
						
						if (Plateau.isInPossibilites(X, Y)) {
							Coordonnees position = new Coordonnees(X, Y);
							Plateau.positionDeDeplacer.add(position);
						}
					}
				}
				//2)
				else if (Partie.getTableDuJeu()[y][x - 1] != null) {
					
					if (y - 1 >= 0 && Partie.getTableDuJeu()[y - 1][x - 1] == null && Plateau.isInPossibilites(x - 1, y - 1) == true) 
						nombreEspace[1]++;
					
					if (y + 1 <= 4 && Partie.getTableDuJeu()[y + 1][x - 1] == null && Plateau.isInPossibilites(x - 1, y + 1) == true) 
						nombreEspace[1]++;
					
					if (nombreEspace[1] == 2) nombreEspace[1]++;
					
					if (x - 2 >= 0 && Partie.getTableDuJeu()[y][x - 2] == null && Plateau.isInPossibilites(x - 2, y) == true) 
						nombreEspace[1]++;		
//					if (x + 1 <= 6 && Partie.getTableDuJeu()[y][x + 1] == null && Plateau.isInPossibilites(x + 1, y) == true) 
//						nombreEspace[1]++;
				}
			}
			
			if (y + 1 <= 4) {  
				//1)
				if (Partie.getTableDuJeu()[y + 1][x] == null) {
					Y = y + 1;
					X = x;
					
					if ((Y + 1 <= 4 && Partie.getTableDuJeu()[Y + 1][X] != null)
						|| (X - 1 >= 0 && Partie.getTableDuJeu()[Y][X - 1] != null) 
						|| (X + 1 <= 6 && Partie.getTableDuJeu()[Y][X + 1] != null)) {
						
						if (Plateau.isInPossibilites(X, Y)) {
							Coordonnees position = new Coordonnees(X, Y);
							Plateau.positionDeDeplacer.add(position);
						}
					}
				}
				//2)
				else if (Partie.getTableDuJeu()[y + 1][x] != null) {
					if (x - 1 >= 0 && Partie.getTableDuJeu()[y + 1][x - 1] == null && Plateau.isInPossibilites(x - 1, y + 1) == true) 
						nombreEspace[2]++;
					
					if (x + 1 <= 6 && Partie.getTableDuJeu()[y + 1][x + 1] == null && Plateau.isInPossibilites(x + 1, y + 1) == true) 
						nombreEspace[2]++;
					
					if (nombreEspace[2] == 2) nombreEspace[2]++;
					
					if (y + 2 <= 4 && Partie.getTableDuJeu()[y + 2][x] == null && Plateau.isInPossibilites(x, y + 2) == true) 
						nombreEspace[2]++;
//					if (y - 1 >= 0 && Partie.getTableDuJeu()[y - 1][x] == null && Plateau.isInPossibilites(x, y - 1) == true) 
//						nombreEspace[2]++;
				}
			}
			
			if (y - 1 >= 0) {
				//1)
				if (Partie.getTableDuJeu()[y - 1][x] == null) {
					Y = y - 1;
					X = x;
					
					if ((Y - 1 >= 0 && Partie.getTableDuJeu()[Y - 1][X] != null)
						|| (X - 1 >= 0 && Partie.getTableDuJeu()[Y][X - 1] != null)
						|| (X + 1 <= 6 && Partie.getTableDuJeu()[Y][X + 1] != null)) {
						
						if (Plateau.isInPossibilites(X, Y)) {
							Coordonnees position = new Coordonnees(X, Y);
							Plateau.positionDeDeplacer.add(position);
						}
					}
				}
				//2
				else if (Partie.getTableDuJeu()[y - 1][x] != null) {
					
					if (x - 1 >= 0 && Partie.getTableDuJeu()[y - 1][x - 1] == null && Plateau.isInPossibilites(x - 1, y - 1) == true) 
						nombreEspace[3]++;
					
					if (x + 1 <= 6 && Partie.getTableDuJeu()[y - 1][x + 1] == null && Plateau.isInPossibilites(x + 1, y - 1) == true) 
						nombreEspace[3]++;
					
					if (nombreEspace[3] == 2) nombreEspace[3]++;
					
					if (y - 2 >= 0 && Partie.getTableDuJeu()[y - 2][x] == null && Plateau.isInPossibilites(x, y - 2) == true) 
						nombreEspace[3]++;

//					if (y + 1 <= 4 && Partie.getTableDuJeu()[y + 1][x] == null && Plateau.isInPossibilites(x, y + 1) == true) 
//						nombreEspace[3]++;
					
				}
			}
			
			//1)
			if (!Plateau.positionDeDeplacer.isEmpty()) estDeplacable = true;
			
			if (estDeplacable == true) {
				for (int i = 0; i < nombreEspace.length; i++) {
//					System.out.print(nombreEspace[i] + "> ");
					if (nombreEspace[i] >= 3 && 
							((Plateau.xMaxDuTapis - Plateau.xMinDuTapis >= 2) || (Plateau.yMaxDuTapis - Plateau.yMinDuTapis >= 2))) {
						Plateau.besoinAjouter = true;
						break;
					} 
				}
			}
			
			//2) use in deplacerCarte() method	
		}
		
		return estDeplacable;
	}
	
	/* (7)
	 * Check the game's table if it has at least 1 position that is moveable
	 */
	public static boolean nePasDeplacer() {
		for (int y = Plateau.yMinDuTapis; y <= Plateau.yMaxDuTapis; y++) {
			for (int x = Plateau.xMinDuTapis; x <= Plateau.xMaxDuTapis; x++) {
				if (Plateau.estDeplacable(x, y)) return false;
			}
		}
		
		return true;
	}
	
	
	/* (8)
	 * Update list of cards played 
	 */
	public static void misAJourListeCartesJouees(Carte carte, int x, int y) {
		Plateau.cartesJouees.add(carte);
		carte.setCoordonnees(x, y);
	}
	
	
	/* (9)
	 * Update list of possible positions for drawing a card to a position
	 * -> (this method is used after moving (déplacer) a card in order to eliminate some possible position
	 * 	that is related to this card's position)
	 */
	public static void misAJourListePossibilites(int x, int y) {
		if (x - 1 >= 0 && Partie.getTableDuJeu()[y][x - 1] == null) Plateau.supprimerCoordonnee(x - 1, y);
		if (x + 1 <= 6 && Partie.getTableDuJeu()[y][x + 1] == null) Plateau.supprimerCoordonnee(x + 1, y);
		if (y - 1 >= 0 && Partie.getTableDuJeu()[y - 1][x] == null) Plateau.supprimerCoordonnee(x, y - 1);
		if (y + 1 <= 4 && Partie.getTableDuJeu()[y + 1][x] == null) Plateau.supprimerCoordonnee(x, y + 1);
		
	}
	
	/* (10)
	 * Reload list of cards played
	 */
	public static void reloadListePossibilites() {
		for (int y = 0; y <= 4; y++) {
			for (int x = 0; x <= 6; x++) {
				if (Partie.getTableDuJeu()[y][x] != null) Plateau.ajouterCoordonneePossible(x, y);
			}
 		}
		Plateau.determinerFormeDuTapis(cartesJouees);
	}
	
	/* (11)
	 * Check if a coordinate is in possibilites list
	 */
	public static boolean isInPossibilites(int x, int y) {
		for (int i = 0; i < possibilites.size(); i++) {
			if (possibilites.get(i).x == x && possibilites.get(i).y == y)
				return true;
		}
		return false;
	}
	
	/* (12)
	 * Check if a coordinate is in 'positionDeDeplacer' list
	 */
	public static boolean isInPositionDeDeplacer(int x, int y) {
		for (int i = 0; i < Plateau.positionDeDeplacer.size(); i++) {
			if (positionDeDeplacer.get(i).x == x && positionDeDeplacer.get(i).y == y) 
				return true;
		}
		return false;
	}
	
	/* (13)
	 * Check if a card is in 'cartesJouees' list
	 */
	public static boolean isInCartesJouees(int x, int y) {
		for (int i = 0; i < Plateau.cartesJouees.size(); i++) {
			if (cartesJouees.get(i).getCoordonnees().x == x && cartesJouees.get(i).getCoordonnees().y == y) 
				return true;
		}
		return false;
	}
	
	/* (14)
	 * Get list of drawed cards 
	 */
	public static ArrayList<Carte> getListeDeCartesJouees() {
		return Plateau.cartesJouees;
	}
	
	/* (15)
	 * get list of 'possibilites'
	 */
	public static ArrayList<Coordonnees> getPositionDeDeplacer() {
		return Plateau.positionDeDeplacer;
	}
	
	
	/* (16)
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

