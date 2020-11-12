package Modele;

import java.util.ArrayList;

import Modele.Carte.*;

public class PiocheCartes {
	private static ArrayList<Carte> piocheCartes = new ArrayList<Carte>();
	private static Carte[] cartesOrg = new Carte[18];
	
	public PiocheCartes() {
		this.creerCartes();
	}
	
	/*
	 * Create an array that contains all of the card
	 */
	public void creerCartes() {
		
		naturePossible[] natures = {naturePossible.plein, naturePossible.vide};
		formePossible[] formes = {formePossible.cercle, formePossible.carré, formePossible.triangle};
		couleurPossible[] couleurs = {couleurPossible.bleu, couleurPossible.vert, couleurPossible.rouge};
		
		int compteur = 0;
		int ind;
		
		/*
		 * Create and shuffle card
		 */
		for (int i = 0; i < formes.length; i++) {
			for (int j = 0; j < natures.length; j++) {
				for (int k = 0; k < couleurs.length; k++) {
					Carte carte = new Carte(formes[i], natures[j], couleurs[k]);  //need to add BufferedImage carteImage
					
					while (compteur < cartesOrg.length) {
						ind = (int) (Math.random() * cartesOrg.length);
						
						if (cartesOrg[ind] == null) {
							cartesOrg[ind] = carte;
							compteur++;
							break;
						} else continue;
					}
				
				}
			}
		}
		
		for (int i = 0; i < cartesOrg.length; i++) {
			piocheCartes.add(cartesOrg[i]);
		}
		
//		for (int i = 0; i < cartesOrg.length; i++) {
//			System.out.println(i + 1 + ") "
//					+ piocheCartes.get(i).getForme() + " "
//					+ piocheCartes.get(i).getNature() + " "
//					+ piocheCartes.get(i).getCouleur());
//		}
		
	}
	
	/*
	 * Get the set of cards that we have just created
	 */
	public static ArrayList<Carte> getPiocheCartes() {
		return PiocheCartes.piocheCartes;
	}
}
