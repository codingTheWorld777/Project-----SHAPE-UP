package Modele;

import java.util.ArrayList;

public class InstallerTour {
	
	/*
	 * Choose one random card from 18 cards in order to set a "hidden card"
	 * Remove this card out of the game
	 * Return this hidden card
	 */
	public Carte retirerCarteCachee(ArrayList<Carte> piocheCartes) {
		int pos = (int) (Math.random() * 18);
		
		Carte carteCachee = piocheCartes.get(pos);
		piocheCartes.remove(pos);
		
		return carteCachee;
	}
	
	/*
	 * Distribute victory card to each player (in the case of 2 players)
	 */
	public void distribuerCarteVictoire(ArrayList<Carte> piocheCartes, Joueur joueur1, Joueur joueur2) {
		int pos1 = 0, pos2 = 0;
		
		while (pos1 == pos2) {
			pos1 = (int) (Math.random() * 18);
			pos2 = (int) (Math.random() * 18);
		}
		
		/*
		 * Distribute victory card
		 */
		joueur1.setCarteVictoire(piocheCartes.get(pos1));
		joueur2.setCarteVictoire(piocheCartes.get(pos2));
		
		
		/*
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
	
	/*
	 * Distribute card to each player (in the case for 2 players and 1 virtuel players)
	 */
	public void distribuerCarteVictoire(ArrayList<Carte> piocheCartes, Joueur joueur1, Joueur joueur2, Joueur joueur3) {
		int pos1 = 0, pos2 = 0, pos3 = 0;
		
		while (pos1 == pos2) {
			pos1 = (int) (Math.random() * 18);
			pos2 = (int) (Math.random() * 18);
		}
		
		
		while (pos3 == pos1 && pos3 == pos2) {
			pos3 = (int) (Math.random() * 18);			
		}
		
		/*
		 * Distribute victory card
		 */
		joueur1.setCarteVictoire(piocheCartes.get(pos1));
		joueur2.setCarteVictoire(piocheCartes.get(pos2));
		joueur3.setCarteVictoire(piocheCartes.get(pos3));
		
		/*
		 * Remove victory cards out of the game
		 */
		if (pos1 < pos2 && pos2 < pos3) {
			piocheCartes.remove(pos3);
			piocheCartes.remove(pos2);
			piocheCartes.remove(pos1);
		} else if (pos2 < pos3 && pos3 < pos1) {
			piocheCartes.remove(pos1);
			piocheCartes.remove(pos3);
			piocheCartes.remove(pos2);
		} else if (pos3 < pos1 && pos1 < pos2) {
			piocheCartes.remove(pos2);
			piocheCartes.remove(pos1);
			piocheCartes.remove(pos3);
		}
		
//		int pos = (int) (Math.random() * 18);
//		int x = (int) (Math.random() * 18);
//		
//		while (pos + 2 * x >= 18) {
//			pos = (int) (Math.random() * 18);
//			x = (int) (Math.random() * 18);
//		}
//		
//		/*
//		 * Distribute victory card to player
//		 */
//		joueur1.setCarteVictoire(piocheCartes.get(pos));
//		joueur2.setCarteVictoire(piocheCartes.get(pos + x));
//		joueur3.setCarteVictoire(piocheCartes.get(pos + 2 * x));
//		
//		/*
//		 * Update and get ready for the game 
//		 */
//		piocheCartes.remove(pos + 2 * x);
//		piocheCartes.remove(pos + x);
//		piocheCartes.remove(pos);
	}
	
}
