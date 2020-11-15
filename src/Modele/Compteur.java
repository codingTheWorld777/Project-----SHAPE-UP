package Modele;

public class Compteur {
	
	private int[] pointsJoueurs;
	
	//constructor
	public Compteur(Carte[][] tableDuJeu) {
		//initialization of pointsJoueurs 
		this.pointsJoueurs = new int [Partie.joueursEnJeu.length];
		


		for (int k = 0; k<Partie.joueursEnJeu.length; k++) {//k parcours les joueurs en jeu
			
			for (int i=0; i<tableDuJeu.length; i++) { //i parcours les lignes
				
				int compteur = 0;
				for (int j=0; j<tableDuJeu[i].length; j++) {//j parcours les colonnes
					
					//on compte les points obtenus du joueur k sur les formes
					while ( Partie.joueursEnJeu[k].getCarteVictoire().getForme() != tableDuJeu[i][j].getForme() ) {
						j++;
					} /*la boucle s'arrête dès que la  forme de la carte victoire est égale
						à la forme de la carte de coordonné (i, j) */
					
					int l= 1;
					while ( Partie.joueursEnJeu[k].getCarteVictoire().getForme() == tableDuJeu[i][j+l].getForme() ) {
						l++;
						this.pointsJoueurs[k]++;
					} /* la boucle s'arrête quand la forme de la carte sur le plateau ne correspond plus à celle
						de la carte victoire */
					
					/**********************************************************************************/
					
					//on compte les points obtenus du joueur k sur les natures
					j = 0;//on réinitialise j 
					while ( Partie.joueursEnJeu[k].getCarteVictoire().getNature() != tableDuJeu[i][j].getNature() ) {
						j++;
					} /*la boucle s'arrête dès que la  nature de la carte victoire est égale
					à celle de la carte de coordonné (i, j) */
					
					l= 1;
					compteur = 0;
					while ( Partie.joueursEnJeu[k].getCarteVictoire().getNature() == tableDuJeu[i][j+l].getNature() ) {
						l++;
						compteur++;
					} /* la boucle s'arrête quand la nature de la carte sur le plateau ne correspond plus à celle
					de la carte victoire */
					
					if (compteur > 1) {
						compteur++;
						this.pointsJoueurs[k] += compteur; 
					} //on doit respecter la distribution des points définie dans la règle du jeu
					
					/**********************************************************************************/
					
					//on compte les points obtenus du joueur k sur les couleurs
					j = 0;//on réinitialise j 
					while ( Partie.joueursEnJeu[k].getCarteVictoire().getCouleur() != tableDuJeu[i][j].getCouleur() ) {
						j++;
					} /*la boucle s'arrête dès que la couleur de la carte victoire est égale
					à celle de la carte de coordonné (i, j) */
					
					l= 1;
					compteur = 0;
					while ( Partie.joueursEnJeu[k].getCarteVictoire().getCouleur() == tableDuJeu[i][j+l].getCouleur() ) {
						l++;
						compteur++;
					} /* la boucle s'arrête quand la couleur de la carte sur le plateau ne correspond plus à celle
					de la carte victoire */
					
					if (compteur > 1) {
						compteur += 2;
						this.pointsJoueurs[k] += compteur; 
					} //on doit respecter la distribution des points définie sur la règle du jeu
				}		
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
			

