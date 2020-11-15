package Modele;

public class Compteur {
	
	private int[] pointsJoueurs;
	
	//constructor
	public Compteur(Partie partie) {
		//initialization of pointsJoueurs 
		this.pointsJoueurs = new int [Partie.joueursEnJeu.length];
		
		Carte[][] tableDuJeu = Partie.getTableDuJeu();
		for (int k = 0; k < Partie.joueursEnJeu.length; k++) {
			
			
			for (int i = 0; i < tableDuJeu.length; i++) { 
				
				//on compte les points obtenus du joueur k sur les formes
				int j = 0;
				while (Partie.joueursEnJeu[k].getCarteVictoire().getForme() != tableDuJeu[i][j].getForme()) {
					j++;
				} /*la boucle s'arr�te d�s que la  forme de la carte victoire est �gale
				� la forme de la carte de coordonn� (i, j) */
				
				int l = 1;
				while (Partie.joueursEnJeu[k].getCarteVictoire().getForme() == tableDuJeu[i][j+l].getForme()) {
					l++;
					this.pointsJoueurs[k]++;
				} /* la boucle s'arr�te quand la forme de la carte sur le plateau ne correspond plus � celle
				de la carte victoire */
				
				/**********************************************************************************/
				
				//on compte les points obtenus du joueur k sur les natures
				j = 0;
				while (Partie.joueursEnJeu[k].getCarteVictoire().getNature() != tableDuJeu[i][j].getNature()) {
					j++;
				} /*la boucle s'arr�te d�s que la  nature de la carte victoire est �gale
				� celle de la carte de coordonn� (i, j) */
				
				l = 1;
				int compteur = 0;
				while (Partie.joueursEnJeu[k].getCarteVictoire().getNature() == tableDuJeu[i][j+l].getNature()) {
					l++;
					compteur++;
				} /* la boucle s'arr�te quand la nature de la carte sur le plateau ne correspond plus � celle
				de la carte victoire */
				
				if (compteur > 1) {
					compteur++;
					this.pointsJoueurs[k] += compteur; 
				} //on doit respecter la distribution des points d�finie sur la r�gle du jeu
				
				/**********************************************************************************/
				
				//on compte les points obtenus du joueur k sur les couleurs
				j = 0;
				while (Partie.joueursEnJeu[k].getCarteVictoire().getCouleur() != tableDuJeu[i][j].getCouleur() ) {
					j++;
				} /*la boucle s'arr�te d�s que la couleur de la carte victoire est �gale
				� celle de la carte de coordonn� (i, j) */
				
				l= 1;
				compteur = 0;
				while (Partie.joueursEnJeu[k].getCarteVictoire().getCouleur() == tableDuJeu[i][j + l].getCouleur() ) {
					l++;
					compteur++;
				} /* la boucle s'arr�te quand la couleur de la carte sur le plateau ne correspond plus � celle
				de la carte victoire */
				
				if (compteur > 1) {
					compteur += 2;
					this.pointsJoueurs[k] += compteur; 
				} //on doit respecter la distribution des points d�finie sur la r�gle du jeu
			}	
		}
	}
	
	
	/*
	 * Get scores of players
	 */
	public int[] getPointsJoueurs() {
		return this.pointsJoueurs;
	}
	
	/*
	 * Get score of a player
	 */
	public int getPointsJoueurs(int joueur_id) {
		return this.pointsJoueurs[joueur_id - 1];
	}
}
			

