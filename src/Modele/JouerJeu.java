package Modele;

public class JouerJeu {	
	public static void main(String[] args) {
		Partie partie = new Partie();
		
		Compteur compteurPoint = new Compteur(Partie.getTableDuJeu());
		
		for (int i = 0; i < Partie.joueursEnJeu.length; i++) {
			System.out.print(Partie.joueursEnJeu[i].nom 
					+ compteurPoint.getPointsJoueurs(Partie.joueursEnJeu[i].id) + "\t");
		}
	}
}
