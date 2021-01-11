package Modele;

import java.util.ArrayList;

/**
 * @author Huu Khai NGUYEN (Alec)
 * <br>
 * Description: This class allows to set up a game based on the datas which is gotten from player (variation of game, number of player,..) 
 */

public class InstallerTour {
	
	/**
	 * Constructor InstallerTour
	 * @param nombreDeJoueurs : int
	 * @param activerJoueurVir : boolean
	 */
	public InstallerTour(int nombreDeJoueurs, boolean activerJoueurVir) {
		new PiocheCartes();
		this.retirerCarteCachee(PiocheCartes.getPiocheCartes());
		
		/** Player 1 is always JoueurPhy */
		JoueurPhy joueur1 = new JoueurPhy("Joueur 1", 1);
		Partie.joueur1 = joueur1;
		Partie.joueur1 = (JoueurPhy) joueur1;
		Partie.joueursEnJeu[0] = joueur1;
		
		if (nombreDeJoueurs == 2 && activerJoueurVir == false) {  /** 2 player and no virtual player */
			JoueurPhy joueur2 = new JoueurPhy("Joueur 2", 2);
			Partie.joueur2 = joueur2;
			Partie.joueur2 = (JoueurPhy) joueur2;
			
			this.distribuerCarteVictoire(PiocheCartes.getPiocheCartes(), Partie.joueur1, Partie.joueur2);
			
			Partie.joueursEnJeu[1] = joueur2;
			
		} else if (nombreDeJoueurs == 2 && activerJoueurVir == true) {  /** 2 players with a virtual player */
			JoueurVir joueur2 = new JoueurVir("Joueur Virtuel", 2);
			Partie.joueur2 = joueur2;
			Partie.joueur2 = (JoueurVir) joueur2;
			joueur2.setStrategie(InstallerJeu.getNiveau());
			
			this.distribuerCarteVictoire(PiocheCartes.getPiocheCartes(), Partie.joueur1, Partie.joueur2);
			
			Partie.joueursEnJeu[1] = joueur2;
			
		} else if (nombreDeJoueurs == 3 && activerJoueurVir == false) { /** 3 players and no virtual player */
			JoueurPhy joueur2 = new JoueurPhy("Joueur 2", 2);
			Partie.joueur2 = joueur2;
			Partie.joueur2 = (JoueurPhy) joueur2;
			
			JoueurPhy joueur3 = new JoueurPhy("Joueur 3", 3);
			Partie.joueur3 = joueur3;
			Partie.joueur3 = (JoueurPhy) joueur3;

			this.distribuerCarteVictoire(PiocheCartes.getPiocheCartes(), Partie.joueur1, Partie.joueur2, Partie.joueur3);
			
			Partie.joueursEnJeu[1] = joueur2;
			Partie.joueursEnJeu[2] = joueur3;
			
		} else if (nombreDeJoueurs == 3 && activerJoueurVir == true) {	/** 3 players with a virtual player */
			JoueurPhy joueur2 = new JoueurPhy("Joueur 2", 2);
			Partie.joueur2 = joueur2;
			Partie.joueur2 = (JoueurPhy) joueur2;
			
			JoueurVir joueur3 = new JoueurVir("Joueur Virtuel", 3);
			Partie.joueur3 = joueur3;
			Partie.joueur3 = (JoueurVir) joueur3;
			joueur3.setStrategie(InstallerJeu.getNiveau());
			
			this.distribuerCarteVictoire(PiocheCartes.getPiocheCartes(), Partie.joueur1, Partie.joueur2, Partie.joueur3);
			
			Partie.joueursEnJeu[1] = joueur2;
			Partie.joueursEnJeu[2] = joueur3;
		}
	} 
	

	/**
	 * Choose one random card from 18 cards in order to set a "hidden card".
	 * Remove this card out of the game.
	 * Return this hidden card.
	 * @param piocheCartes : ArrayList<Carte>
	 * @return hidden card
	 */
	public Carte retirerCarteCachee(ArrayList<Carte> piocheCartes) {
		int pos = (int) (Math.random() * 18);
		
		Carte carteCachee = piocheCartes.get(pos);
		piocheCartes.remove(pos);
		
		Partie.carteCachee = carteCachee;
		
		return carteCachee;
	}
	
	/**
	 * Distribute victory card to each player (in the case of 2 players)
	 * @param piocheCartes : ArrayList<Carte>
	 * @param joueur1 : Joueur
	 * @param joueur2 : Joueur
	 */
	public void distribuerCarteVictoire(ArrayList<Carte> piocheCartes, Joueur joueur1, Joueur joueur2) {
		int pos1 = 0, pos2 = 0;
		
		while (pos1 == pos2) {
			pos1 = (int) (Math.random() * 17);	//multiple by 17 (because we eliminate a card (hidden card) first
			pos2 = (int) (Math.random() * 17);
		}
		
		/**
		 * Distribute victory card
		 */
		joueur1.setCarteVictoire(piocheCartes.get(pos1));
		joueur2.setCarteVictoire(piocheCartes.get(pos2));
		
		
		/**
		 * Update and get ready for the game
		 */
		if (pos1 > pos2) {
			piocheCartes.remove(pos1);
			piocheCartes.remove(pos2);
		} else {
			piocheCartes.remove(pos2);
			piocheCartes.remove(pos1);
		}
	}
	
	/**
	 * Distribute card to each player (in the case for 2 players and 1 virtual player)
	 * @param piocheCartes : ArrayList<Carte> 
	 * @param joueur1 : Joueur
	 * @param joueur2 : Joueur
	 * @param joueur3 : Joueur
	 */
	public void distribuerCarteVictoire(ArrayList<Carte> piocheCartes, Joueur joueur1, Joueur joueur2, Joueur joueur3) {
		int pos = (int) (Math.random() * 17);
		int x = (int) (Math.random() * 17);
		
		while (pos + 2 * x >= 17 || x == 0) {
			pos = (int) (Math.random() * 17);
			x = (int) (Math.random() * 17);
		}
		
		/**
		 * Distribute victory card to player
		 */
		joueur1.setCarteVictoire(piocheCartes.get(pos));
		joueur2.setCarteVictoire(piocheCartes.get(pos + x));
		joueur3.setCarteVictoire(piocheCartes.get(pos + 2 * x));
		
		/**
		 * Update and get ready for the game 
		 */
		piocheCartes.remove(pos + 2 * x);
		piocheCartes.remove(pos + x);
		piocheCartes.remove(pos);
	}
}