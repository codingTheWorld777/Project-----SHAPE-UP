package Modele;

import java.util.ArrayList;
import java.util.Collections;

public class JoueurVir extends Joueur implements Strategie {
	/**
	 * @author Huu Khai NGUYEN (Alec), Pierre-Louis DAMBRAINE
	 */
	
	private String niveau;
	
	//constructor
	public JoueurVir(String name, int id) {
		super(name, id);
		this.niveau = this.getNiveau();
	}
	
	public void piocherCarte(Carte[][] tableDuJeu, int tour) {
		Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
		
		if (this.niveau.compareTo("F") == 0) { //easy level
			int i = (int) (Math.random() * Plateau.possibilites.size());
			int x = Plateau.possibilites.get(i).x;
			int y = Plateau.possibilites.get(i).y;
			
			tableDuJeu[y][x] = PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1);
			Plateau.misAJourListeCartesJouees(tableDuJeu[y][x], x, y);
			Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
			Plateau.supprimerCoordonnee(x, y);
			
			if (Plateau.besoinAjouter == false) {
				Plateau.ajouterCoordonneePossible(x, y);
			} else {
				Plateau.reloadListePossibilites();
				Plateau.besoinAjouter = false;
			}
			
			Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
			PiocheCartes.getPiocheCartes().remove(Partie.nombreDeCartesJouables - 1);
			Partie.nombreDeCartesJouables--;
			
			/*
			 * Set changed and notify observer
			 */
			this.setChanged();
			this.notifyObservers(null);
			
			this.aPiocheUneCarte = true;
			
		} else if (this.niveau.compareTo("D") == 0) { //hard level
			int X = 0;
			int Y = 0;
			
			int point = 0;
			
			for (int i = 0; i < Plateau.possibilites.size(); i++) {
				Carte[][] tableVir = Partie.getTableDuJeu();
				int x = Plateau.possibilites.get(i).x;
				int y = Plateau.possibilites.get(i).y;
				tableVir[y][x] = PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1);
				Compteur compteurPoint = new Compteur();
				compteurPoint.compter(tableVir);
				
				if (i == 0) {
					point = compteurPoint.getPointsJoueurs(this.id);
					X = x;
					Y = y;
				} else {
					if (compteurPoint.getPointsJoueurs(this.id) > point) {
						point = compteurPoint.getPointsJoueurs(this.id);
						X = x;
						Y = y;
					}
				}
				Partie.getTableDuJeu()[y][x] = null;
			} 	//JoueurVir put his card where he has the more point 
			
			tableDuJeu[Y][X] = PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1);
			Plateau.misAJourListeCartesJouees(tableDuJeu[Y][X], X, Y);
			Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
			Plateau.supprimerCoordonnee(X, Y);
			Plateau.ajouterCoordonneePossible(X, Y);
			Plateau.reloadListePossibilites(); //
			Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
			PiocheCartes.getPiocheCartes().remove(Partie.nombreDeCartesJouables - 1);
			Partie.nombreDeCartesJouables--;
		}
		
		
		for (int t = 0; t < Plateau.possibilites.size(); t++) {
			System.out.print("(" + Plateau.possibilites.get(t).x + ", " + Plateau.possibilites.get(t).y + "), ");
		}
		System.out.println();
		
		Plateau.updateTableDuJeu();
		
		/*
		 * Set changed and notify observer
		 */
		this.setChanged();
		this.notifyObservers(null);
		
		this.aPiocheUneCarte = true;
		
	}
	
	public void deplacerCarte() {
		Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
		
		if (this.niveau.compareTo("F") == 0) { // easy level
			int y = (int) (Math.random() * Partie.getTableDuJeu().length);
			int x = (int) (Math.random() * Partie.getTableDuJeu()[y].length);
			boolean check = Plateau.estDeplacable(x, y);
			
			while (Partie.getTableDuJeu()[y][x] == null && check == false) {
				y = (int) (Math.random() * Partie.getTableDuJeu().length);
				x = (int) (Math.random() * Partie.getTableDuJeu()[y].length);
			
				check = Plateau.estDeplacable(x, y);
			} // thus Plateau.tableDuJeu[y][x] is moveable
			
			Plateau.estDeplacable(x, y);
			if (Plateau.positionDeDeplacer.size() != 0) {
				int i = (int) (Math.random() * Plateau.positionDeDeplacer.size());
				int x1 = Plateau.positionDeDeplacer.get(i).x;
				int y1 = Plateau.positionDeDeplacer.get(i).y;
				
				Carte carte = Partie.getTableDuJeu()[y][x];
				carte.setCoordonnees(x1, y1); 	//reset coordinate of card
				Partie.getTableDuJeu()[y][x] = null;
				Plateau.supprimerCoordonnee(x, y);
				Plateau.misAJourListePossibilites(x, y);
				Partie.getTableDuJeu()[y1][x1] = carte;
				if (Plateau.besoinAjouter == false) Plateau.ajouterCoordonneePossible(x1, y1);
				
				/*
				 * Check if there are some possible positions of mouvement card that has the same possible position
				 * 	with are positioned around this card 
				 */
				if (Plateau.besoinAjouter == false) {
					for (int compteur = x1; compteur <= 6; compteur++) {
						if (y1 + 1 <= 4 && Partie.getTableDuJeu()[y1 + 1][x1] != null) Plateau.ajouterCoordonneePossible(x1, y1 + 1);
						
						if (y1 - 1 >= 0 && Partie.getTableDuJeu()[y1 - 1][x1] != null) Plateau.ajouterCoordonneePossible(x1, y1 - 1);
					}
					
					for (int compteur = x1; compteur >= 0; compteur--) {
						if (y1 + 1 <= 4 && Partie.getTableDuJeu()[y1 + 1][x1] != null) Plateau.ajouterCoordonneePossible(x1, y1 + 1);
						
						if (y1 - 1 >= 0 && Partie.getTableDuJeu()[y1 - 1][x1] != null) Plateau.ajouterCoordonneePossible(x1, y1 - 1);
					}
				} else if (Plateau.besoinAjouter == true) {
					Plateau.possibilites.clear();
					Coordonnees position = new Coordonnees(x, y);
					Plateau.possibilites.add(position);
					Plateau.besoinAjouter = false;
				}
				
				Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
				Plateau.positionDeDeplacer.clear();
				
				Plateau.updateTableDuJeu();
				System.out.println("Le joueur virtuel fini de deplacer une carte");
				
				/*
				 * Set changed and notify observer
				 */
				this.setChanged();
				this.notifyObservers(null);
				
			}
			
		} else if (this.niveau.compareTo("D") == 0) { 
			/**
			 * 1) 
			 * Find a list of moveable cards 
			 * Check each card of this list to find its moveable position which has the highest point when move to this position
			 * Save each moveable position to 'listPosDeCarteDeplacable' and its new position to 'listPosDeDeplacer'
			 * 
			 * 2)
			 * If player can get point when move a card to new position:
			 	* Choose from the list 'listPosDeCarteDeplacable' a position that has the highest point, and also its new position
			 	* Move card to new position and finish player's turn
			 * If player cannot get any point when move a card
			 	* We check a draw card (-> carteJouee[nombreDeCartesJouees - 1]) with all cards on the table
			 	*  with comparison of attributes
			 */
			
			//1)
			int X = -1, Y = -1;  		//X, Y: original position 
			int X1 = -1, Y1 = -1;		//X1, Y1: position after moving
			Coordonnees pos1, pos2;		//positions in case 2 options aren't sastified
			
			ArrayList<Integer> setOfMaximumPoints = new ArrayList<Integer>();
			ArrayList<Coordonnees> listPosDeCarteDeplacable = new ArrayList<Coordonnees>();
			ArrayList<Coordonnees> listPosDeDeplacer = new ArrayList<Coordonnees>();
			
			for (int i = 0; i < Plateau.cartesJouees.size(); i++) {
				int x = Plateau.cartesJouees.get(i).getCoordonnees().x;
				int y = Plateau.cartesJouees.get(i).getCoordonnees().y;
				boolean check = Plateau.estDeplacable(x, y);
				
				ArrayList<Integer> listPoint = new ArrayList<Integer>();
				ArrayList<Coordonnees> listCoordonnees = new ArrayList<Coordonnees>();
				
				if (check) {
					Carte carteChoisie = Partie.getTableDuJeu()[y][x];
					Compteur compteurPoint = new Compteur();
					
					if (x + 1 <= 6 && Partie.getTableDuJeu()[y][x + 1] == null && Plateau.isInPositionDeDeplacer(x + 1, y)) {
						Partie.getTableDuJeu()[y][x + 1] = Partie.getTableDuJeu()[y][x];
						Partie.getTableDuJeu()[y][x] = null;
						compteurPoint.compter(Partie.getTableDuJeu());
						listPoint.add(compteurPoint.getPointsJoueurs(this.id));
						
						Partie.getTableDuJeu()[y][x + 1] = null;
						Partie.getTableDuJeu()[y][x] = carteChoisie;
						listCoordonnees.add(new Coordonnees(x + 1, y));
					} 
					
					if (x - 1 >= 0 && Partie.getTableDuJeu()[y][x - 1] == null && Plateau.isInPositionDeDeplacer(x - 1, y)) {
						Partie.getTableDuJeu()[y][x - 1] = Partie.getTableDuJeu()[y][x];
						Partie.getTableDuJeu()[y][x] = null;
						compteurPoint.compter(Partie.getTableDuJeu());
						listPoint.add(compteurPoint.getPointsJoueurs(this.id));
						
						Partie.getTableDuJeu()[y][x - 1] = null;
						Partie.getTableDuJeu()[y][x] = carteChoisie;
						listCoordonnees.add(new Coordonnees(x - 1, y));
					}
					
					if (y + 1 <= 4 && Partie.getTableDuJeu()[y + 1][x] == null && Plateau.isInPositionDeDeplacer(x, y + 1)) {
						Partie.getTableDuJeu()[y + 1][x] = Partie.getTableDuJeu()[y][x];
						Partie.getTableDuJeu()[y][x] = null;
						compteurPoint.compter(Partie.getTableDuJeu());
						listPoint.add(compteurPoint.getPointsJoueurs(this.id));
						
						Partie.getTableDuJeu()[y + 1][x] = null;
						Partie.getTableDuJeu()[y][x] = carteChoisie;
						listCoordonnees.add(new Coordonnees(x, y + 1));
					}
					
					if (y - 1 >= 0 && Partie.getTableDuJeu()[y - 1][x] == null && Plateau.isInPositionDeDeplacer(x, y - 1)) {
						Partie.getTableDuJeu()[y - 1][x] = Partie.getTableDuJeu()[y][x];
						Partie.getTableDuJeu()[y][x] = null;
						compteurPoint.compter(Partie.getTableDuJeu());
						listPoint.add(compteurPoint.getPointsJoueurs(this.id));
						
						Partie.getTableDuJeu()[y - 1][x] = null;
						Partie.getTableDuJeu()[y][x] = carteChoisie;
						listCoordonnees.add(new Coordonnees(x, y - 1));
					}
					
					for (int k = 0; !listPoint.isEmpty() && k < listPoint.size(); k++) {
						if (listPoint.get(k) == Collections.max(listPoint)) {
							setOfMaximumPoints.add(listPoint.get(k));
							listPosDeCarteDeplacable.add(new Coordonnees(x, y));
							listPosDeDeplacer.add(listCoordonnees.get(k));
							
							listPoint.clear();
							listCoordonnees.clear();
							break;
						}
					}
				}
				
			}
			
			//2)
			if (!setOfMaximumPoints.isEmpty()) {
//				for (int i = 0; !setOfMaximumPoints.isEmpty() && i < setOfMaximumPoints.size(); i++) {
//					System.out.print("SetOfMaximumPoints: " + setOfMaximumPoints.get(i) + ", ");
//				}
				for (int i = 0; !setOfMaximumPoints.isEmpty() && i < setOfMaximumPoints.size(); i++) {
					if (setOfMaximumPoints.get(i) == Collections.max(setOfMaximumPoints)) {
						X = listPosDeCarteDeplacable.get(i).x;
						Y = listPosDeCarteDeplacable.get(i).y;
						
						X1 = listPosDeDeplacer.get(i).x;
						Y1 = listPosDeDeplacer.get(i).y;
						
						break;
					}
				}
				
			} else {
				//In this case, the player's most beneficial ability is to move a card on the board to a position such that 
				//exactly 1 adjacent card has the same nature (or color) as it and is the same as the winning card.
				boolean check = false;
				
				outerLoop: //Label to break out the nested loops
				for (int i = 0; i < Partie.getTableDuJeu().length; i++) {
					for (int j = 0; j < Partie.getTableDuJeu()[i].length; j++) {
						check = Plateau.estDeplacable(j, i);
						
						if (check) {
							X = j;
							Y = i;
							Carte carteDeplacee = Partie.getTableDuJeu()[Y][X];
							
							for (int k = 0; k < Plateau.positionDeDeplacer.size(); k++) {
								if (X + 1 <= 6 && Partie.getTableDuJeu()[Y][X + 1] == null 
										&& (X + 1 == Plateau.positionDeDeplacer.get(k).x) && (Y == Plateau.positionDeDeplacer.get(k).y)) {
									X1 = X + 1;
									Y1 = Y;
									
									if (X1 + 1 <= 6 && Partie.getTableDuJeu()[Y1][X1 + 1] != null) {
										boolean condition1 = (carteDeplacee.getNature() == Partie.getTableDuJeu()[Y1][X1 + 1].getNature())
												&& (Partie.getTableDuJeu()[Y1][X1 + 1].getNature() == this.getCarteVictoire().getNature());
										
										boolean condition2 = (carteDeplacee.getCouleur() == Partie.getTableDuJeu()[Y1][X1 + 1].getCouleur())
												&& (Partie.getTableDuJeu()[Y1][X1 + 1].getCouleur() == this.getCarteVictoire().getCouleur());
										
										if ((condition1 && condition2) || (condition2 || condition1)) break outerLoop; 
									}
									if (conditionParcoursY(X1, Y1, carteDeplacee)) break outerLoop;
								} 
								
								if (X - 1 >= 0 && Partie.getTableDuJeu()[Y][X - 1] == null 
										&& (Y - 1 == Plateau.positionDeDeplacer.get(k).x) && (Y == Plateau.positionDeDeplacer.get(k).y)) {
									X1 = X - 1;
									Y1 = Y;
									
									if (X1 - 1 >= 0 && Partie.getTableDuJeu()[Y1][X1 - 1] != null) {
										boolean condition1 = (carteDeplacee.getNature() == Partie.getTableDuJeu()[Y1][X1 - 1].getNature())
												&& (Partie.getTableDuJeu()[Y1][X1 - 1].getNature() == this.getCarteVictoire().getNature());
										
										boolean condition2 = (carteDeplacee.getCouleur() == Partie.getTableDuJeu()[Y1][X1 - 1].getCouleur())
												&& (Partie.getTableDuJeu()[Y1][X1 - 1].getCouleur() == this.getCarteVictoire().getCouleur());
										
										if ((condition1 && condition2) || (condition2 || condition1)) break outerLoop; 
									} 
									if (conditionParcoursY(X1, Y1, carteDeplacee)) break outerLoop;
								}
								
								if (Y + 1 <= 4 && Partie.getTableDuJeu()[Y + 1][X] == null
										&& (X == Plateau.positionDeDeplacer.get(k).x) && (Y + 1 == Plateau.positionDeDeplacer.get(k).y)) {
									X1 = X;
									Y1 = Y + 1;

									if (Y1 + 1 <= 4 && Partie.getTableDuJeu()[Y1 + 1][X1] != null) {
										boolean condition1 = (carteDeplacee.getNature() == Partie.getTableDuJeu()[Y1 + 1][X1].getNature())
												&& (Partie.getTableDuJeu()[Y1 + 1][X1].getNature() == this.getCarteVictoire().getNature());
										
										boolean condition2 = (carteDeplacee.getCouleur() == Partie.getTableDuJeu()[Y1 + 1][X1].getCouleur())
												&& (Partie.getTableDuJeu()[Y1 + 1][X1].getCouleur() == this.getCarteVictoire().getCouleur());
										
										if ((condition1 && condition2) || (condition2 || condition1)) break outerLoop; 
									} 
									if (conditionParcoursX(X1, Y1, carteDeplacee)) break outerLoop;
								}
								
								if (Y - 1 >= 0 && Partie.getTableDuJeu()[Y - 1][X] == null
										&& (X == Plateau.positionDeDeplacer.get(k).x) && (Y - 1 == Plateau.positionDeDeplacer.get(k).y)) {
									X1 = X;
									Y1 = Y - 1;
									
									if (Y1 - 1 >= 0 && Partie.getTableDuJeu()[Y1 - 1][X1] != null) {
										boolean condition1 = (carteDeplacee.getNature() == Partie.getTableDuJeu()[Y1 - 1][X1].getNature())
												&& (Partie.getTableDuJeu()[Y1 - 1][X1].getNature() == this.getCarteVictoire().getNature());
										
										boolean condition2 = (carteDeplacee.getCouleur() == Partie.getTableDuJeu()[Y1 - 1][X1].getCouleur())
												&& (Partie.getTableDuJeu()[Y1 - 1][X1].getCouleur() == this.getCarteVictoire().getCouleur());
										
										if ((condition1 && condition2) || (condition2 || condition1)) break outerLoop; 

									}
									if (conditionParcoursX(X1, Y1, carteDeplacee)) break outerLoop;
								}
							} //end loops by k
						}
						
					} //end loops by j
				} //end loops by i
			} 
			
			//In case the first two steps aren't sastified 
			if (X < 0 && Y < 0) {
				for (int i = 0; i < Plateau.cartesJouees.size(); i++) {
					int x0 = Plateau.cartesJouees.get(i).getCoordonnees().x;
					int y0 = Plateau.cartesJouees.get(i).getCoordonnees().y;
					
					if (Plateau.estDeplacable(x0, y0)) {
						X = x0;
						Y = y0;
						X1 = Plateau.positionDeDeplacer.get(0).x;
						Y1 = Plateau.positionDeDeplacer.get(0).y;
						break;
					}
				}
			}
		
			try {
				Carte carte = Partie.getTableDuJeu()[Y][X];
				carte.setCoordonnees(X1, Y1); 	
				Partie.getTableDuJeu()[Y][X] = null;
				Plateau.supprimerCoordonnee(X, Y);
				Plateau.misAJourListePossibilites(X, Y);
				Partie.getTableDuJeu()[Y1][X1] = carte;
			} catch (Exception e) {
				return;
			}
			if (Plateau.besoinAjouter == false) Plateau.ajouterCoordonneePossible(X1, Y1);
				
			/*
			 * Check if there are some possible positions of mouvement of card that has the same possible position
			 * 	with are positioned around this card 
			 */
			if (Plateau.besoinAjouter == false)  {
				for (int compteur = X1; compteur < 7; compteur++) {
					if (Y1 + 1 <= 4 && Partie.getTableDuJeu()[Y1 + 1][X1] != null) Plateau.ajouterCoordonneePossible(X1, Y1 + 1);
					
					if (Y1 - 1 >= 0 && Partie.getTableDuJeu()[Y1 - 1][X1] != null) Plateau.ajouterCoordonneePossible(X1, Y1 - 1);	
				}
				
				for (int compteur = X1; compteur >= 0; compteur--) {
					if (Y1 + 1 <= 4 && Partie.getTableDuJeu()[Y1 + 1][X1] != null) Plateau.ajouterCoordonneePossible(X1, Y1 + 1);
					
					if (Y1 - 1 >= 0 && Partie.getTableDuJeu()[Y1 - 1][X1] != null) Plateau.ajouterCoordonneePossible(X1, Y1 - 1);
				}
			} else if (Plateau.besoinAjouter == true) {
				Plateau.possibilites.clear();
				Coordonnees position = new Coordonnees(X, Y);
				Plateau.possibilites.add(position);
				Plateau.besoinAjouter = false;
			}
			
			Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
			Plateau.positionDeDeplacer.clear();
			
			Plateau.updateTableDuJeu();
			System.out.println("Le joueur virtuel fini de deplacer une carte");
			
			/*
			 * Set changed and notify observer
			 */
			this.setChanged();
			this.notifyObservers(this);
			
		}	
	}
	
	/*
	 * Get level for virtuel player
	 * There are two levels of this type of player: easy and difficult
	 */
	public String getNiveau() {
		return this.niveau;
	}
	
	// D or F
	public void setStrategie(String niveau) {
		this.niveau = niveau;
	}
	
	//Condition in section 2) of method 'deplacerCarte' hard level
	public boolean conditionParcoursX(int X1, int Y1, Carte carteDeplacee) {
		if (X1 - 1 >= 0 && Partie.getTableDuJeu()[Y1][X1 - 1] != null) {
			boolean condition1 = (carteDeplacee.getNature() == Partie.getTableDuJeu()[Y1][X1 - 1].getNature())
					&& (Partie.getTableDuJeu()[Y1][X1 - 1].getNature() == this.getCarteVictoire().getNature());
			
			boolean condition2 = (carteDeplacee.getCouleur() == Partie.getTableDuJeu()[Y1][X1 - 1].getCouleur())
					&& (Partie.getTableDuJeu()[Y1][X1 - 1].getCouleur() == this.getCarteVictoire().getCouleur());
			
			if ((condition1 && condition2) || (condition2 || condition1)) return true; 
			
		}
		if (X1 + 1 <= 6 && Partie.getTableDuJeu()[Y1][X1 + 1] != null) {
			boolean condition1 = (carteDeplacee.getNature() == Partie.getTableDuJeu()[Y1][X1 + 1].getNature())
					&& (Partie.getTableDuJeu()[Y1][X1 + 1].getNature() == this.getCarteVictoire().getNature());
			
			boolean condition2 = (carteDeplacee.getCouleur() == Partie.getTableDuJeu()[Y1][X1 + 1].getCouleur())
					&& (Partie.getTableDuJeu()[Y1][X1 + 1].getCouleur() == this.getCarteVictoire().getCouleur());
			
			if ((condition1 && condition2) || (condition2 || condition1)) return true; 
		} 
		return false;
	}
	
	
	public boolean conditionParcoursY(int X1, int Y1, Carte carteDeplacee) {
		if (Y1 - 1 >= 0 && Partie.getTableDuJeu()[Y1 - 1][X1] != null) {
			boolean condition1 = (carteDeplacee.getNature() == Partie.getTableDuJeu()[Y1 - 1][X1].getNature())
					&& (Partie.getTableDuJeu()[Y1 - 1][X1].getNature() == this.getCarteVictoire().getNature());
			
			boolean condition2 = (carteDeplacee.getCouleur() == Partie.getTableDuJeu()[Y1 - 1][X1].getCouleur())
					&& (Partie.getTableDuJeu()[Y1 - 1][X1].getCouleur() == this.getCarteVictoire().getCouleur());
			
			if ((condition1 && condition2) || (condition2 || condition1)) return true; 
			
		}
		if (Y1 + 1 <= 4 && Partie.getTableDuJeu()[Y1 + 1][X1] != null) {
			boolean condition1 = (carteDeplacee.getNature() == Partie.getTableDuJeu()[Y1 + 1][X1].getNature())
					&& (Partie.getTableDuJeu()[Y1 + 1][X1].getNature() == this.getCarteVictoire().getNature());
			
			boolean condition2 = (carteDeplacee.getCouleur() == Partie.getTableDuJeu()[Y1 + 1][X1].getCouleur())
					&& (Partie.getTableDuJeu()[Y1 + 1][X1].getCouleur() == this.getCarteVictoire().getCouleur());
			
			if ((condition1 && condition2) || (condition2 || condition1)) return true; 
		}
		return false;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}
}
