package Modele;

import Controleur.ControleurParametre;
import Controleur.ControleurTableDuJeu;

public class Partie extends Observable {
	/**
	 * @author Huu Khai NGUYEN (Alec)
	 */
	
		
	/*
	 * Player of game (from 2-3 players)
	 */
	public static Joueur joueur1;
	public static Joueur joueur2;
	public static Joueur joueur3;
	
	//Sets of players 
	public static Joueur[] joueursEnJeu;
	
	//Hidden card
	public static Carte carteCachee;
	
	/*
	 * Number of playable cards 
	 */
	public static int nombreDeCartesJouables;
	
	/*
	 * Turn
	 */
	public static int tour = 0;
	public static int tourDeJoueur = 1;
	
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
		ControleurParametre.setInstallerJeu(installerJeu);
		ControleurTableDuJeu.setInstallerJeu(installerJeu);
		
		/*
		 * Step 2:
		 * Install a round:
		 * 	+ Eliminate a card
		 * 	+ Draw victory card to each player
		 */
		InstallerTour installerTour = new InstallerTour(installerJeu.getNombreDeJoueurs(), installerJeu.getActiverJoueurVir());
		ControleurTableDuJeu.setInstallerTour(installerTour);
		
		/*
		 * Step 3:
		 * All player in turn of game and play their turn:
		 * + Move a card	[OK]
		 * + Draw and put that card in a possible position 	[OK]
		 */
		joueursEnJeu[0].setEnTour(true);  //Choosing Player 1 for the first turn
		while (Partie.nombreDeCartesJouables > 0) {    
			for (int i = 0; i < InstallerJeu.getNombreDeJoueurs() && Partie.nombreDeCartesJouables > 0; i++) {
				for (int k = 0; k < InstallerJeu.getNombreDeJoueurs(); k++) {
					if (Partie.joueursEnJeu[k].getEnTour() == true) Partie.tourDeJoueur = Partie.joueursEnJeu[k].getId();
				}
				
				System.out.println("Joueur " + joueursEnJeu[i].id);
				if (Partie.tour == 0) {
					Partie.jouerSonTour(joueursEnJeu[i], joueursEnJeu[i].estEnTour, Partie.tour);		//draw and play a card
					Partie.tour++;
					
					while (joueursEnJeu[i].pouvoirFinirMonTour == false) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} 
					
					joueursEnJeu[i].aPiocheUneCarte = false;
					joueursEnJeu[i].pouvoirFinirMonTour = false;
					
				} else if (Partie.tour >= 1) {
//					if (joueursEnJeu[i].coordChoisieADeplacer != null) {
//						//Move a card: Yes/No. From turn 3 (there were already 3 card on the table and check...)
//						if (Partie.tour >= 3 && Plateau.nePasDeplacer() == false) 
//							joueursEnJeu[i].deplacerCarte();
//					}	
					
					Partie.jouerSonTour(joueursEnJeu[i], joueursEnJeu[i].estEnTour, Partie.tour);		//draw and play a card
					Partie.tour++;	
				
					while (joueursEnJeu[i].pouvoirFinirMonTour == false) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} 
					
					joueursEnJeu[i].aPiocheUneCarte = false;
					joueursEnJeu[i].pouvoirFinirMonTour = false;
					
				}
				System.out.println("Carte restant: " + Partie.nombreDeCartesJouables);
			}
		}
		
		/* Step 4:
		 * The game is now finish: 
		 * 	+ Compter points of each player and print it on the screen
		 */
		System.out.println("\n \n");
		this.imprimerResult();
		
		Compteur compteurPoint = new Compteur();
		compteurPoint.compter(tableDuJeu);
		
		for (int i = 0; i < Partie.joueursEnJeu.length; i++) {
			System.out.print(Partie.joueursEnJeu[i].nom 
					+ ": " + compteurPoint.getPointsJoueurs()[i] + " points." + "\t");
		}
	}
	
	/*
	 * Player play their turn:
	 * 	+ Move a card
	 * 	+ Draw ad put that card in a possible position
	 */
	public static void jouerSonTour(Joueur joueur, boolean estEnTour, int tour) {
		if (estEnTour) {
			if (InstallerJeu.getNombreDeJoueurs() == 2) {
				if (joueur.id == 1) {
					joueur.piocherCarte(Partie.tableDuJeu, tour);
					
					joueur.setEnTour(false);
					joueursEnJeu[joueur.id].setEnTour(true);
				} else if (joueur.id == 2) {
					joueur.piocherCarte(Partie.tableDuJeu, tour);
					
					joueur.setEnTour(false);
					joueursEnJeu[0].setEnTour(true);
				}

			} else if (InstallerJeu.getNombreDeJoueurs() == 3) {
				if (joueur.id == 1) {
					joueur.piocherCarte(Partie.tableDuJeu, tour);
					
					joueur.setEnTour(false);
					joueursEnJeu[1].setEnTour(true);
					
				} else if (joueur.id == 2) {
					joueur.piocherCarte(Partie.tableDuJeu, tour);
					
					joueur.setEnTour(false);
					joueursEnJeu[2].setEnTour(true);
					
				} else if (joueur.id == 3) {
					joueur.piocherCarte(Partie.tableDuJeu, tour);
					
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
	
	/*
	 * Show the table of game with all features of cards
	 */
	public void imprimerResult() {
		for (int i = 0; i < Partie.joueursEnJeu.length; i++) {
			System.out.println("Carte victoire de " + joueursEnJeu[i].nom + " est: " 
					+ " " + joueursEnJeu[i].getCarteVictoire().getForme()
					+ " " + joueursEnJeu[i].getCarteVictoire().getCouleur()
					+ " " + joueursEnJeu[i].getCarteVictoire().getNature());
		}
		System.out.println();
		
		String result;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				if (Partie.getTableDuJeu()[i][j] != null) {
					result = "|" + Partie.getTableDuJeu()[i][j].getForme() + " "
							+ Partie.getTableDuJeu()[i][j].getCouleur() + " "
							+ Partie.getTableDuJeu()[i][j].getNature();
			
					while (result.length() < 22) {
						if (result.length() == 21) result += "|";
						else result += " ";;
					}
					
					System.out.print(result);
				} else if (Partie.getTableDuJeu()[i][j] == null) {
					result = "| ";
					
					while (result.length() < 22) {
						if (result.length() == 21) result += "|";
						else result += " ";;
					}
					
					System.out.print(result);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
