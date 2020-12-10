package Modele;

import java.util.*;

public class JoueurVir extends Joueur implements Strategie {
	
	private String niveau;
	
	//constructor
	public JoueurVir(String name, int id) {
		super(name, id);
		this.niveau = this.getNiveau();
	}
	
	public void piocherCarte(Carte[][] tableDuJeu, int tour) {
		
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
			
		} else if (this.niveau.compareTo("D") == 0) { //hard level
			int X = 0;
			int Y = 0;
			ArrayList<Integer> listPoint = new ArrayList<Integer>();
			for (int i = 0; i < Plateau.possibilites.size(); i++) {
				Carte[][] tableVir = Partie.getTableDuJeu();
				int x = Plateau.possibilites.get(i).x;
				int y = Plateau.possibilites.get(i).y;
				tableVir[y][x] = PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1);
				Compteur compteur = new Compteur(tableVir);
				listPoint.add(compteur.getPointsJoueurs(this.id));
				if (i == 0) {
					X = x;
					Y = y;
				} else if (listPoint.get(i) > listPoint.get(i - 1)) {
					X = x;
					Y = y;
				}
				Partie.getTableDuJeu()[y][x] = null;
			} //JoueurVir put his cart where he has the more point


			
			tableDuJeu[Y][X] = PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1);
			Plateau.misAJourListeCartesJouees(tableDuJeu[Y][X], X, Y);
			Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
			Plateau.supprimerCoordonnee(X, Y);
			Plateau.ajouterCoordonneePossible(X, Y);
			Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
			PiocheCartes.getPiocheCartes().remove(Partie.nombreDeCartesJouables - 1);
			Partie.nombreDeCartesJouables--;
		}
		
		
		for (int t = 0; t < Plateau.possibilites.size(); t++) {
			System.out.print("(" + Plateau.possibilites.get(t).x + ", " + Plateau.possibilites.get(t).y + "), ");
		}
		System.out.println();
		
		Plateau.updateTableDuJeu();
		
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
				} else if (Plateau.besoinAjouter) {
					Plateau.possibilites.clear();
					Coordonnees position = new Coordonnees(x, y);
					Plateau.possibilites.add(position);
				}
				
				
				Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
				Plateau.positionDeDeplacer.clear();
				
				//This loop can be deleted
				for (int t = 0; t < Plateau.possibilites.size(); t++) {
					System.out.print("(" + Plateau.possibilites.get(t).x + ", " + Plateau.possibilites.get(t).y + "), ");
				}
				System.out.println();
				
				Plateau.updateTableDuJeu();
				System.out.println("Le joueur virtuel fini de deplacer une carte");
			}
			
			
		} else if (this.niveau.compareTo("D") == 0) { 
			//first step : choose the card to move 
			int x = 0;
			int y = 0;
			ArrayList<Integer> listPoint = new ArrayList<Integer>();
			for (int i = 0; i < Partie.getTableDuJeu().length; i++) {
				for (int j = 0; j < Partie.getTableDuJeu()[i].length; j++) {
					boolean check = Plateau.estDeplacable(j, i);
					int scorePointCommun = 0; //1 for shape; 2 for nature and 3 for color
					if (check) {
						if (Partie.getTableDuJeu()[i][j] != null) {
							if (Partie.getTableDuJeu()[i][j].getForme() == this.getCarteVictoire().getForme()) {
								scorePointCommun++;
							}
							if (Partie.getTableDuJeu()[i][j].getNature() == this.getCarteVictoire().getNature()) {
								scorePointCommun+=2;
							}
							if (Partie.getTableDuJeu()[i][j].getCouleur() == this.getCarteVictoire().getCouleur()) {
								scorePointCommun+=3;
							}
							listPoint.add(scorePointCommun);
							
							//select the card with highest scoreCommun
							if (listPoint.size() == 1) {
								x = Partie.getTableDuJeu()[i][j].getCoordonnees().x;
								y = Partie.getTableDuJeu()[i][j].getCoordonnees().y;
							} else if (listPoint.get(listPoint.size() - 1) == Collections.max(listPoint)) {
								x = Partie.getTableDuJeu()[i][j].getCoordonnees().x;
								y = Partie.getTableDuJeu()[i][j].getCoordonnees().y;
							}
						}
					}
					
				}
			}
			
			if (Partie.getTableDuJeu()[y][x] != null && Plateau.estDeplacable(x, y) == true) {
				
				//second step : choose the final coordinates of the card
				int X = 0;
				int Y = 0;
				ArrayList<Integer> listPointDeplacer = new ArrayList<Integer>();
				for (int i = 0; i < Plateau.positionDeDeplacer.size(); i++) {
					Carte[][] tableVir = Partie.getTableDuJeu();
					int x1 = Plateau.positionDeDeplacer.get(i).x;
					int y1 = Plateau.positionDeDeplacer.get(i).y;
					tableVir[y1][x1] = Partie.getTableDuJeu()[y][x];
					Compteur compteur = new Compteur(tableVir);
					listPointDeplacer.add(compteur.getPointsJoueurs(this.id));
					if (i == 0) {
						X = x1;
						Y = y1;
					} else if (listPointDeplacer.get(i) > listPointDeplacer.get(i - 1)) {
						X = x1;
						Y = y1;
					}
					Partie.getTableDuJeu()[y1][x1] = null;
				} //JoueurVir put his cart where he has the more point
				

				Carte carte = Partie.getTableDuJeu()[y][x];
				carte.setCoordonnees(X, Y); 	//reset coordinate of card
				Partie.getTableDuJeu()[y][x] = null;
				Plateau.supprimerCoordonnee(x, y);
				Plateau.misAJourListePossibilites(x, y);
				Partie.getTableDuJeu()[Y][X] = carte;
				Plateau.ajouterCoordonneePossible(X, Y);
				Plateau.supprimerCoordonnee(X, Y);
				
				/*
				 * Check if there are some possible positions of mouvement of card that has the same possible position
				 * 	with are positioned around this card 
				 */
				for (int compteur = X; compteur < 7; compteur++) {
					if (Y + 1 <= 4 && Partie.getTableDuJeu()[Y + 1][X] != null) Plateau.ajouterCoordonneePossible(X, Y + 1);
					
					if (Y - 1 >= 0 && Partie.getTableDuJeu()[Y - 1][X] != null) Plateau.ajouterCoordonneePossible(X, Y - 1);	
				}
				
				for (int compteur = X; compteur >= 0; compteur--) {
					if (Y + 1 <= 4 && Partie.getTableDuJeu()[Y + 1][X] != null) Plateau.ajouterCoordonneePossible(X, Y + 1);
					
					if (Y - 1 >= 0 && Partie.getTableDuJeu()[Y - 1][X] != null) Plateau.ajouterCoordonneePossible(X, Y - 1);
				}
				
				Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
				Plateau.positionDeDeplacer.clear();
				
				//This loop can be deleted
				for (int t = 0; t < Plateau.possibilites.size(); t++) {
					System.out.print("(" + Plateau.possibilites.get(t).x + ", " + Plateau.possibilites.get(t).y + "), ");
				}
				System.out.println();
				
				Plateau.updateTableDuJeu();
				System.out.println("Le joueur virtuel fini de deplacer une carte");
				return;
			} 
			
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
}
