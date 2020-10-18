package Modele;

public abstract class Joueur extends Observable implements Observer{
	private String nom;
	private int id;
	private Carte carteVictoire;  //Il faut la distribuer avant de jouer
	private boolean estEnTour;
	private int score;
	
	/*
	 * Constructor of Player
	 */
	public Joueur(String nom, int id, Carte carteVictoire) {
		this.nom = nom;
		this.id = id;
		this.carteVictoire = carteVictoire;
	}
	
	/*
	 * Draw a card
	 */
	public abstract void piocherCarte();
	
	/*
	 * Move a card
	 */
	public abstract void deplacerCarte();
	
	/*
	 * Set score for player
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/*
	 * Get score of one player
	 */
	public int getScore() {
		return this.score;
	}
}
