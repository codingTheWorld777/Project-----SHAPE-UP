package Modele;

public class JouerJeu {	
	public static void main(String[] args) {
		System.out.println("Commencer à jouer!");
		
		PiocheCartes piocheCartes = new PiocheCartes();
		
		piocheCartes.creerCartes();
		
		
		JoueurPhy joueurPhy = new JoueurPhy("JoueurPhy", 1);
	}
}
