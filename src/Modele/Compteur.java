package Modele;

public class Compteur {
	
	private int[] pointsJoueurs;
	
	//constructor
	public Compteur(Carte[][] tableDuJeu) {
		//initialization of pointsJoueurs 
		this.pointsJoueurs = new int [Partie.joueursEnJeu.length];

		for (int k = 0; k<Partie.joueursEnJeu.length; k++) {//k parcours les joueurs en jeu
			this.pointsJoueurs[k] = 0;
			
			//1. On compte les points obtenus sur les lignes
			//2. On compte les points obtunus sur les colonnes
			
			//1.
			//on compte les points obtenus du joueur k sur les formes
			for (int i=0; i<tableDuJeu.length; i++) { //i parcours les lignes
				
				for (int j=0; j<tableDuJeu[i].length; j++) {//j parcours les colonnes
					
					if (tableDuJeu[i][j] != null) {
					
						while ( tableDuJeu[i][j] != null && Partie.joueursEnJeu[k].getCarteVictoire().getForme() != tableDuJeu[i][j].getForme() ) {
							if (j != 6) {
								j++;
							}
							
						} /*la boucle s'arrête dès que la  forme de la carte victoire est égale
							à la forme de la carte de coordonné (i, j) */
						int n = j;
						int l= 1;
						
						while ( tableDuJeu[i][n+l] != null && Partie.joueursEnJeu[k].getCarteVictoire().getForme() == tableDuJeu[i][n+l].getForme() ) {
							l++;
							if (j + l >= 6) {
								j = 6;
							} else { j += l; }
							this.pointsJoueurs[k]++;
						} /* la boucle s'arrête quand la forme de la carte sur le plateau ne correspond plus à celle
						de la carte victoire */
						
					}
				}
			}
			
			//on compte les points obtenus du joueur k sur les natures
			for (int i=0; i<tableDuJeu.length; i++) { //i parcours les lignes
				int compteur = 0;
						
				for (int j=0; j<tableDuJeu[i].length; j++) {//j parcours les colonnes
					
					if (tableDuJeu[i][j] != null) {
						while ( tableDuJeu[i][j] != null && Partie.joueursEnJeu[k].getCarteVictoire().getNature() != tableDuJeu[i][j].getNature() ) {
							if (j != 6) {
								j++;
							}
							
						} /*la boucle s'arrête dès que la  nature de la carte victoire est égale
							à celle de la carte de coordonné (i, j) */
						int n = j;
						int l= 1;
						while ( tableDuJeu[i][n+l] != null && Partie.joueursEnJeu[k].getCarteVictoire().getNature() == tableDuJeu[i][n+l].getNature() ) {
							l++;
							if (j + l >= 6) {
								j = 6;
							} else { j += l; }
							compteur++;
						} /* la boucle s'arrête quand la nature de la carte sur le plateau ne correspond plus à celle
							de la carte victoire */
					
						if (compteur > 1) {
							compteur++;
							this.pointsJoueurs[k]+=compteur; 
						} //on doit respecter la distribution des points définie dans la règle du jeu
						
					}
				}
			}
	
			
	
			//on compte les points obtenus du joueur k sur les couleurs	
			for (int i=0; i<tableDuJeu.length; i++) { //i parcours les lignes
				int compteur = 0;
			
				for (int j=0; j<tableDuJeu[i].length; j++) {//j parcours les colonnes
					
					if (tableDuJeu[i][j] != null) {

						while ( tableDuJeu[i][j] != null && Partie.joueursEnJeu[k].getCarteVictoire().getCouleur() != tableDuJeu[i][j].getCouleur() ) {
							if (j != 6) {
								j++;
							}
							
								
						} /*la boucle s'arrête dès que la couleur de la carte victoire est égale
						à celle de la carte de coordonné (i, j) */
						int n = j;
						int l= 1;
						while ( tableDuJeu[i][n+l] != null && Partie.joueursEnJeu[k].getCarteVictoire().getCouleur() == tableDuJeu[i][n+l].getCouleur() ) {
							l++;
							if (j + l >= 6) {
								j = 6;
							} else { j += l; }
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
			
			/******************************************************************************************************************************************/
			
			//2.
			//on compte les points obtenus du joueur k sur les formes
			for (int j=0; j<tableDuJeu[0].length; j++) { //j parcours les colonnes
				
				for (int i=0; i<tableDuJeu.length; i++) {//i parcours les lignes
					
					if (tableDuJeu[i][j] != null) {
					
						while ( tableDuJeu[i][j] != null && Partie.joueursEnJeu[k].getCarteVictoire().getForme() != tableDuJeu[i][j].getForme() ) {
						if ( i != 4) {
							i++;
						}
						
						} /*la boucle s'arrête dès que la  forme de la carte victoire est égale
							à la forme de la carte de coordonné (i, j) */
						int n = i;
						int l= 1;
						
						while ( tableDuJeu[n+l][j] != null && Partie.joueursEnJeu[k].getCarteVictoire().getForme() == tableDuJeu[n+l][j].getForme() ) {
							l++;
							if (i + l >= 4) {
								i = 4;
							} else { i += l; }
							this.pointsJoueurs[k]++;
						} /* la boucle s'arrête quand la forme de la carte sur le plateau ne correspond plus à celle
						de la carte victoire */
					}
				}
			}
			
			
			//on compte les points obtenus du joueur k sur les natures
			for (int j=0; j<tableDuJeu[0].length; j++) { //j parcours les colonnes
				int compteur = 0;
						
				for (int i=0; i<tableDuJeu.length; i++) {//i parcours les lignes
					
					if (tableDuJeu[i][j] != null) {
						while ( tableDuJeu[i][j] != null && Partie.joueursEnJeu[k].getCarteVictoire().getNature() != tableDuJeu[i][j].getNature() ) {
							if ( i != 4) {
								i++;
							}
							
						} /*la boucle s'arrête dès que la  nature de la carte victoire est égale
							à celle de la carte de coordonné (i, j) */
						int n = i;
						int l= 1;
						while ( tableDuJeu[n+l][j] != null && Partie.joueursEnJeu[k].getCarteVictoire().getNature() == tableDuJeu[n+l][j].getNature() ) {
							l++;
							if (i + l >= 4) {
								i = 4;
							} else { i += l; }
							compteur++;
						} /* la boucle s'arrête quand la nature de la carte sur le plateau ne correspond plus à celle
							de la carte victoire */
					
						if (compteur > 1) {
							compteur++;
							this.pointsJoueurs[k] += compteur; 
						} //on doit respecter la distribution des points définie dans la règle du jeu
					}
				}
			}
	
			
	
			//on compte les points obtenus du joueur k sur les couleurs	
			for (int j=0; j<tableDuJeu[0].length; j++) { //j parcours les colonnes
				int compteur = 0;
			
				for (int i=0; i<tableDuJeu.length; i++) {//i parcours les lignes
					
					if (tableDuJeu[i][j] != null) {

						while ( tableDuJeu[i][j] != null && Partie.joueursEnJeu[k].getCarteVictoire().getCouleur() != tableDuJeu[i][j].getCouleur() ) {
							if ( i != 4) {
								i++;
							}
							
						} /*la boucle s'arrête dès que la couleur de la carte victoire est égale
						à celle de la carte de coordonné (i, j) */
						int n = i;
						int l = 1;
						while ( tableDuJeu[n+l][j] != null && Partie.joueursEnJeu[k].getCarteVictoire().getCouleur() == tableDuJeu[n+l][j].getCouleur() ) {
							l++;
							if (i + l >= 4) {
								i = 4;
							} else { i += l; }
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
			

