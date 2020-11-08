package Modele;

public abstract class Joueur extends Observable implements Observer{
	/*
	 * Designation of player 
	 */
	
	/*
	 * To show if player is taking their turn of not
	 */
	protected String nom;
	
	/*
	 * ID of player is used in necessary case
	 */
	protected int id;
	
	/*
	 * Victory card of player after distributing, is used by GUI after finishing a round
	 * and is used to calculate score for each player.
	 */
	protected Carte carteVictoire;  //Il faut la distribuer avant de jouer le jeu
	
	/*
	 * To check if player is in turn or not.
	 */
	public boolean estEnTour;
	
	/*
	 * Be used by GUI to show score of each player at the end of a round
	 */
	protected int score;
	
	/*
	 * Constructor of Player
	 */
	public Joueur(String nom, int id) {
		this.nom = nom;
		this.id = id;
//		this.carteVictoire = carteVictoire;
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
	 * Set victory card for player
	 */
	public void setCarteVictoire(Carte carteVictoire) {
		this.carteVictoire = carteVictoire;
	}
	
	/*
	 * Get victory card of player
	 */
	public Carte getCarteVictoire() {
		return this.carteVictoire; 
	}
	
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
