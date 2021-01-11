package Modele;

import java.util.Scanner;

/**
 * @author Huu Khai NGUYEN (Alec)
 * <br>
 * Description: JoueurPhy class allows us to have a better view of what physics players might be able to have. 
 * A real player can do: 
 * <ul>
 	<li> Move a card </li> 
 	<li> Place a card </li>	 
 * </ul>
 */

public class JoueurPhy extends Joueur {
	/** Controler that control the game */
//	private ControleurTableDuJeu controleurJeu =  new ControleurTableDuJeu();
	
	/** Constructor JoueurPhy */
	public JoueurPhy(String name, int id) {
		super(name, id);
	}
	
	/**
	 * Draw and place a card
	 * @param tableDuJeu : Carte[][]
	 * @param tour : int
	 */
	public void piocherCarte(Carte[][] tableDuJeu, int tour) {
		Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
		System.out.println("Vous avez pioché la carte : " + PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1).getForme() + " " + PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1).getNature() + " " + PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1).getCouleur());
		if (tour >= 1) {
			for (int i = 0; i < Plateau.possibilites.size(); i++) {
				System.out.print("(" + Plateau.possibilites.get(i).x + ", " + Plateau.possibilites.get(i).y + "), ");
			}
			System.out.println();
		}
		
		System.out.print("Choisir l'abscisse x de carte sur la table du jeu: ");
		int x, y;
		
		while (this.coordAPlacer == null) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		
		x = this.coordAPlacer.x;
		System.out.println(x);

		
		while (tour == 0 && InstallerJeu.getVarianteDuTapis() == "P") {
			if (x == 0 || x == 6) break;
			System.out.println("Choisir l'abscisse x de carte parmis 0 et 6 pour la premiere fois (Pyramide): ");
			if (x == 0 || x == 6) break;
		}
		
		System.out.print("Choisir l'ordonnée y de carte sur le table du jeu: ");
		y = this.coordAPlacer.y;
		System.out.println(y);
		
		
		/**
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
			this.coordAPlacer = null;
			this.aPiocheUneCarte = true;
			
			for (int t = 0; t < Plateau.possibilites.size(); t++) {
				System.out.print("(" + Plateau.possibilites.get(t).x + ", " + Plateau.possibilites.get(t).y + "), ");
			}
			System.out.println();
			
			Plateau.updateTableDuJeu();
			
			/*
			 * Notify observers
			 */
			this.setChanged();
			this.notifyObservers(this);
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
				tableDuJeu[y][x].estSurTableDuJeu = true;
				Plateau.misAJourListeCartesJouees(tableDuJeu[y][x], x, y);
				Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
				

				this.coordAPlacer = null;
				
				if (Plateau.besoinAjouter == false) {
					Plateau.ajouterCoordonneePossible(x, y);
				} else {
					Plateau.reloadListePossibilites();
					Plateau.besoinAjouter = false;
				}
				
				if (Plateau.isInPossibilites(x, y)) Plateau.supprimerCoordonnee(x, y);
				Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
				PiocheCartes.getPiocheCartes().remove(Partie.nombreDeCartesJouables - 1);
				Partie.nombreDeCartesJouables--;
				
				for (int t = 0; t < Plateau.possibilites.size(); t++) {
					System.out.print("(" + Plateau.possibilites.get(t).x + ", " + Plateau.possibilites.get(t).y + "), ");
				}
				System.out.println();
				
				Plateau.updateTableDuJeu();
				
				this.aPiocheUneCarte = true;
				
				/*
				 * Notify observers
				 */
				this.setChanged();
				this.notifyObservers(this);

				return;
				
			} else {
				System.out.println("Cette position ne correspond pas");
				this.coordAPlacer = null;
				this.piocherCarte(tableDuJeu, tour);
				return;
			}
		}
	}
	
	/**
	 * Move a card <nr>
	 * Choose and move a card to a new position (if it's possible) by respecting the rule of mouvement
	 */
	public void deplacerCarte() {
		Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
		
		@SuppressWarnings("resource")
		Scanner src = new Scanner(System.in);
		
		System.out.println(coordChoisieADeplacer.x + ", " + coordChoisieADeplacer.y);
		int deplacer ;
		if (this.coordChoisieADeplacer != null) {
			deplacer = 1;
			System.out.println(deplacer);
		} else deplacer = src.nextInt();
		
		int x, y;
		if (deplacer == 1) {
			System.out.println("Choisir l'abscisse x de carte que vous voulez déplacer: ");
			
			if (this.coordChoisieADeplacer != null) {
				x = this.coordChoisieADeplacer.x;
				System.out.println(x);
			} else x = src.nextInt();
			
			System.out.println("Choisir l'ordonnée y de carte que vous voulez déplacer: ");
			if (this.coordChoisieADeplacer != null) {
				y = this.coordChoisieADeplacer.y;
				System.out.println(y);
			} else y = src.nextInt();
			
			boolean check = Plateau.estDeplacable(x, y);
			System.out.println("La carte sur cette position est deplacable? " + check);
			
			if (check) {
				//Can delete this loop
				System.out.print("Deplacer vers: ");
				for (int k = 0; k < Plateau.positionDeDeplacer.size(); k++) {
					System.out.print("(" + Plateau.positionDeDeplacer.get(k).x + ", " + Plateau.positionDeDeplacer.get(k).y + "), ");
				}
				System.out.println();
				
				int x1, y1; 
				System.out.print("Choisir l'abscisse x de position que vous voulez déplacer carte à: ");
				
				while (this.coordADeplacer == null) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				x1 = this.coordADeplacer.x; 
				System.out.println(x1);
				
				System.out.print("Choisir l'ordonnée y de position que vous voulez déplacer carte à: ");
				y1 = this.coordADeplacer.y;
				System.out.println(y1);
				
				
				for (Coordonnees positionDeDeplacer: Plateau.positionDeDeplacer) {
					if (positionDeDeplacer.x == x1 && positionDeDeplacer.y == y1) {
						/**
						 * If all conditions of mouvement are satisfied: 
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
						Plateau.supprimerCoordonnee(x, y);
						Plateau.misAJourListePossibilites(x, y);
						Partie.getTableDuJeu()[y1][x1] = carte;
						if (Plateau.besoinAjouter == false) Plateau.ajouterCoordonneePossible(x1, y1);
						
						/**
						 * Check if there are some possible positions of mouvement of card that has the same possible position
						 * 	with are positioned around this card 
						 */
						if (Plateau.besoinAjouter == false)  {
							Plateau.reloadListePossibilites();
							
						} else if (Plateau.besoinAjouter == true) {
							System.out.println("Vous avez besoin d'ajouter une nouvelle carte vers (" + x + ", " + y + ")");
							Plateau.possibilites.clear();
							Coordonnees position = new Coordonnees(x, y);
							Plateau.possibilites.add(position);
							Plateau.besoinAjouter = false;
						}
						
						Plateau.positionDeDeplacer.clear();
						this.coordChoisieADeplacer = null;
						this.coordADeplacer = null;
						
						Plateau.determinerFormeDuTapis(Plateau.cartesJouees);
						
						//This loop can be deleted
						for (int t = 0; t < Plateau.possibilites.size(); t++) {
							System.out.print("(" + Plateau.possibilites.get(t).x + ", " + Plateau.possibilites.get(t).y + "), ");
						}
						System.out.println();
						
						Plateau.updateTableDuJeu();
						
						/*
						 * Notify observers
						 */
						this.setChanged();
						this.notifyObservers(this);

						return;
					}
				}
				
				System.out.println("Vous ne pouvez pas deplacer cette carte vers " + "(" + x1 + ", " + y1 + ")");
				Plateau.positionDeDeplacer.clear();
//				deplacerCarte();
				return;
				
			} else return;

		}
	}
}