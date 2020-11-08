package Modele;

public class JoueurPhy extends Joueur implements Strategie {
	public JoueurPhy(String name, int id) {
		super(name, id);
	}
	
	public void piocherCarte() {
		System.out.println("Hello");
	}
	
	public void deplacerCarte() {
		System.out.println("Bye");
	}
	
	/*
	 * Get level for virtuel player
	 * There are two levels of this type of player: easy and difficult
	 */
	public String getNiveau() {
		return "";
	}
	
	public void setStrategie(String niveau) {
		
	}
}
