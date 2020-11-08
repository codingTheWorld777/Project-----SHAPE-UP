package Modele;

import java.util.ArrayList;

public class PiocheCartes {
	private ArrayList<Carte> piocheCartes = new ArrayList<Carte>();
	private Carte[] cartesOrg = new Carte[18];
	
	/*
	 * Create an array that contains all of the card
	 */
	public void creerCartes() {
		String[] natures = {"solid", "hollow"};
		String[] formes = {"circle", "square", "triangle"};
		String[] couleurs = {couleurPossible.rouge, couleurPossible.vert, couleurPossible.bleu};
		
		int compteur = 0;
		
		int ind;
		
		for (int i = 0; i < natures.length; i++) {
			for (int j = 0; j < formes.length; j++) {
				for (int k = 0; k < couleurs.length; k++) {
					Carte carte = new Carte(natures[i], formes[j], couleurs[k]);  //need to add BufferedImage carteImage
					
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
//					+ piocheCartes.get(i).getNature() + " "
//					+ piocheCartes.get(i).getForme() + " "
//					+ piocheCartes.get(i).getCouleur());
//		}
	}
	
	/*
	 * Get the set of cards that we have just created
	 */
	public ArrayList<Carte> getPiocheCartes() {
		return this.piocheCartes;
	}
}
