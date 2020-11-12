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
	public static Joueur[] joueursEnJeu = new Joueur[2];
	
	/*
	 * Number of playable cards
	 */
	public static int nombreDeCartesJouables;
	
	/*
	 * Board of game
	 */
	private static Carte[][] tableDuJeu = new Carte[5][7];
	
	//Constructor
	public Partie() {
		int compteur = 0;
		boolean deplacerUneCarte;
		
		/*
		 * Install game: choose number of players, activer virtual player(Yes or No) and choose its level
		 */
		InstallerJeu installerJeu = new InstallerJeu();
		
		/*
		 * Install a round:
		 * 	+ Eliminate a card
		 * 	+ Draw victory card to each player
		 */
		InstallerTour installerTour = new InstallerTour(installerJeu.getNombreDeJoueurs(), installerJeu.getActiverJoueurVir());
		
		
		/*
		 * All player in turn of game and play their turn:
		 * + Move a card
		 * + Draw ad put that card in a possible position 
		 */
		joueursEnJeu[0].setEnTour(true);  //Choosing Player 1 for the first turn
		
		while (compteur < 15) {    //15 just for this case: 2 players and no virtual player

			for (int i = 0; i < InstallerJeu.getNombreDeJoueurs() && compteur < 15; i++) {
				jouerSonTour(joueursEnJeu[i], joueursEnJeu[i].estEnTour);
				compteur++;
			}
		}
	}
	
	/*
	 * Player play their turn:
	 * 	+ Move a card
	 * 	+ Draw ad put that card in a possible position
	 */
	public void jouerSonTour(Joueur joueur, boolean estEnTour) {
		if (estEnTour) {
			if (InstallerJeu.getActiverJoueurVir() == false && InstallerJeu.getNombreDeJoueurs() == 2) {
				joueur = (JoueurPhy) joueur;
				if (joueur.id == 1) {
					joueur.piocherCarte(this.tableDuJeu);
					
					joueur.setEnTour(false);
					joueursEnJeu[joueur.id].setEnTour(true);
				} else if (joueur.id == 2) {
					joueur.piocherCarte(this.tableDuJeu);
					
					joueur.setEnTour(false);
					joueursEnJeu[0].setEnTour(true);
				}
			}
		}
	}
	
	public static void updateTableDuJeu() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				if (Partie.tableDuJeu[i][j] != null) {
					System.out.print("|X|");
				} else {
					System.out.print("| |");
				}
			}
			System.out.println();
		}
	}
	
}
