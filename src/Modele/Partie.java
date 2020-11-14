package Modele;

import java.util.Scanner;

public class Partie {
	/*
	 * Player of game (from 2-3 players)
	 */
	public static Joueur joueur1;
	public static Joueur joueur2;
	public static Joueur joueur3;
	
	//Sets of players 
	public static Joueur[] joueursEnJeu;;
	
	/*
	 * Number of playable cards 
	 */
	public static int nombreDeCartesJouables;
	
	/*
	 * Turn
	 */
	public static int tour = 0;
	
	/*
	 * Board of game
	 */
	private static Carte[][] tableDuJeu = new Carte[5][7];
	
	//Constructor
	public Partie() {
		boolean deplacerUneCarte;
		
		/*
		 * Step 1:
		 * Install game: choose number of players, activer virtual player(Yes or No) and choose its level
		 */
		InstallerJeu installerJeu = new InstallerJeu();
		
		/*
		 * Step 2:
		 * Install a round:
		 * 	+ Eliminate a card
		 * 	+ Draw victory card to each player
		 */
		InstallerTour installerTour = new InstallerTour(installerJeu.getNombreDeJoueurs(), installerJeu.getActiverJoueurVir());
		
		
		/*
		 * Step 3:
		 * All player in turn of game and play their turn:
		 * + Move a card
		 * + Draw ad put that card in a possible position 
		 */
		joueursEnJeu[0].setEnTour(true);  //Choosing Player 1 for the first turn
		while (Partie.nombreDeCartesJouables > 0) {    //15 just for this case: 2 players and no virtual player, 

			for (int i = 0; i < InstallerJeu.getNombreDeJoueurs() && Partie.nombreDeCartesJouables > 0; i++) {
				System.out.println("Joueur " + joueursEnJeu[i].id);
				this.jouerSonTour(joueursEnJeu[i], joueursEnJeu[i].estEnTour, Partie.tour);
				Partie.tour++;
			}
		}
	}
	
	/*
	 * Player play their turn:
	 * 	+ Move a card
	 * 	+ Draw ad put that card in a possible position
	 */
	public void jouerSonTour(Joueur joueur, boolean estEnTour, int tour) {
		if (estEnTour) {
			if (InstallerJeu.getActiverJoueurVir() == false && InstallerJeu.getNombreDeJoueurs() == 2) {
				joueur = (JoueurPhy) joueur;
				if (joueur.id == 1) {
					joueur.piocherCarte(this.tableDuJeu, tour);
					
					joueur.setEnTour(false);
					joueursEnJeu[joueur.id].setEnTour(true);
				} else if (joueur.id == 2) {
					joueur.piocherCarte(this.tableDuJeu, tour);
					
					joueur.setEnTour(false);
					joueursEnJeu[0].setEnTour(true);
				}
			} else if (InstallerJeu.getActiverJoueurVir() == false && InstallerJeu.getNombreDeJoueurs() == 3) {
				joueur = (JoueurPhy) joueur;
				if (joueur.id == 1) {
					joueur.piocherCarte(this.tableDuJeu, tour);
					
					joueur.setEnTour(false);
					joueursEnJeu[1].setEnTour(true);
					
				} else if (joueur.id == 2) {
					joueur.piocherCarte(this.tableDuJeu, tour);
					
					joueur.setEnTour(false);
					joueursEnJeu[2].setEnTour(true);
					
				} else if (joueur.id == 3) {
					joueur.piocherCarte(this.tableDuJeu, tour);
					
					joueur.setEnTour(false);
					joueursEnJeu[0].setEnTour(true);
				}
			}
		}
	}
	
	/*
	 * Get table of game "tableDuJeu"
	 */
	public static Carte[][] getTableDuJeu() {
		return Partie.tableDuJeu;
	}
}
