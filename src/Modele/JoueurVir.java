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
		
		if (this.niveau.compareTo("F") == 0) {
			int i = (int) (Math.random() * Plateau.possibilites.size());
			int x = Plateau.possibilites.get(i).x;
			int y = Plateau.possibilites.get(i).y;
			tableDuJeu[y][x] = PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1);
			Plateau.misAJourListeCartesJouees(tableDuJeu[y][x], x, y);
			Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
			Plateau.supprimerCoordonnee(x, y);
			Plateau.ajouterCoordonneePossible(x, y);
			Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
			PiocheCartes.getPiocheCartes().remove(Partie.nombreDeCartesJouables - 1);
			Partie.nombreDeCartesJouables--;
			
		} else if (this.niveau.compareTo("D") == 0 ) {
			
		}
		
		
		for (int t = 0; t < Plateau.possibilites.size(); t++) {
			System.out.print("(" + Plateau.possibilites.get(t).x + ", " + Plateau.possibilites.get(t).y + "), ");
		}
		System.out.println();
		
		Plateau.updateTableDuJeu();
	}
	
	public void deplacerCarte() {
		if (this.niveau.compareTo("F") == 0) {
			int y = (int) (Math.random() * Partie.getTableDuJeu().length);
			int x = (int) (Math.random() * Partie.getTableDuJeu()[y].length);
			boolean check = Plateau.estDeplacable(x, y);
			System.out.println(x + ", " + y);
			while (Partie.getTableDuJeu()[y][x] == null && check == false) {
				y = (int) (Math.random() * Partie.getTableDuJeu().length);
				x = (int) (Math.random() * Partie.getTableDuJeu()[y].length);
				System.out.println(x + ", " + y);
				check = Plateau.estDeplacable(x, y);
				
			} // thus Plateau.tableDuJeu[x][y] is moveable
			Plateau.estDeplacable(x, y);
			if (Plateau.positionDeDeplacer.size() != 0) {
				int i = (int) (Math.random() * Plateau.positionDeDeplacer.size());
				int x1 = Plateau.positionDeDeplacer.get(i).x;
				int y1 = Plateau.positionDeDeplacer.get(i).y;
				
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
				System.out.println("Le joueur virtuel à fini de déplacer une carte");
			}
			
			
		} else if (this.niveau.compareTo("D") == 0 ) {
			
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
