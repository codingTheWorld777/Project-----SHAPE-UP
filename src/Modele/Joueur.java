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
	private Carte carteVictoire;  //Il faut la distribuer avant de jouer le jeu
	
	/*
	 * To check if player is in turn or not.
	 */
	protected boolean estEnTour;
	
	/*
	 * Be used by GUI to show score of each player at the end of a round
	 */
	protected int score;

	//Coords where player wants his card to be
	protected Coordonnees coordAPlacer; 
	
	//Coords of card that is chosen by player to move it to new position
	protected Coordonnees coordChoisieADeplacer;
	protected Coordonnees coordADeplacer;
	
	//Check if player want to stop his turn (of course he must draw a card before)
	public boolean pouvoirFinirMonTour = false;  
	public boolean aPiocheUneCarte = false;
	
	/*
	 * Constructor of Player
	 */
	public Joueur(String nom, int id) {
		this.nom = nom;
		this.id = id;
		this.coordAPlacer = null;
	}
	
	/*
	 * Draw a card
	 */
	public abstract void piocherCarte(Carte[][] tableDuJeu, int tour);
	
	/*
	 * Move a card
	 */
	public abstract void deplacerCarte();
	
	/*
	 * Get player's name
	 */
	public String getNom() {
		return this.nom;
	}
	
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
	
	/*
	 * Set player's status (in turn or not)
	 */
	public void setEnTour(boolean estEnTour) {
		this.estEnTour = estEnTour;
	}
	
	/*
	 * Set player's coordAPlacer
	 */
	public void setCoordAPlacer(int x, int y) {
		this.coordAPlacer = new Coordonnees(x, y);
	}
	
	/*
	 * Set and get player's coordChoisieADeplacer et coordADeplacer
	 */
	public void setCoordChoisieADeplacer(int x, int y) {
		this.coordChoisieADeplacer = new Coordonnees(x, y);
	}
	
	public Coordonnees getCoordChoisieADeplacer() {
		return this.coordChoisieADeplacer;
	}
	
	public void setCoordADeplacer(int x, int y) {
		this.coordADeplacer = new Coordonnees(x, y);
	}
	
	/*
	 * Check if player is in turn or not
	 */
	public boolean getEnTour() {
		return this.estEnTour;
	}
	
	/*
	 * Get player's id
	 */
	public int getId() {
		return this.id;
	}
}
