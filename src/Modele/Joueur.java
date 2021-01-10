package Modele;

/**
 * @author Huu Khai NGUYEN (Alec)
 * <br>
 * Description: This class describes all properties and actions that a player has (-> Designation of player)
 */

public abstract class Joueur extends Observable {
	/**
	 * To show if player is taking their turn of not
	 */
	protected String nom;
	
	/**
	 * ID of player is used in necessary case
	 */
	protected int id;
	
	/**
	 * Victory card of player after distributing, is used by GUI after finishing a round
	 * and is used to calculate score for each player.
	 */
	private Carte carteVictoire;  //Il faut la distribuer avant de jouer le jeu
	
	/**
	 * To check if player is in turn or not.
	 */
	protected boolean estEnTour;
	
	/**
	 * Be used by GUI to show score of each player at the end of a round
	 */
	protected int score;

	/** Coords where player wants his card to be */
	protected Coordonnees coordAPlacer; 
	
	/** Coords of card that is chosen by player to move it to new position */
	public Coordonnees coordChoisieADeplacer;
	public Coordonnees coordADeplacer;
	
	/** Check if player want to stop his turn (of course he must draw a card before) */
	public boolean pouvoirFinirMonTour = false;  
	public boolean aPiocheUneCarte = false;
	
	/**
	 * Constructor of Player
	 * @param nom : String
	 * @param id : int
	 */
	public Joueur(String nom, int id) {
		this.nom = nom;
		this.id = id;
		this.coordAPlacer = null;
	}
	
	/**
	 * Player draws a card
	 * @param tableDuJeu : Carte[][]
	 * @param tour : int
	 */
	public abstract void piocherCarte(Carte[][] tableDuJeu, int tour);
	
	/**
	 *  Player moves a card
	 */
	public abstract void deplacerCarte();
	
	/**
	 * Get player's name
	 * @return player's name
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Set victory card for player
	 * @param carteVictoire : Carte
	 */
	public void setCarteVictoire(Carte carteVictoire) {
		this.carteVictoire = carteVictoire;
	}
	
	/**
	 * Get victory card of player
	 * @return player's victory card
	 */
	public Carte getCarteVictoire() {
		return this.carteVictoire; 
	}
	
	/**
	 * Set score for player
	 * @param score : int
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Get score of one player
	 * @return player's score
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Set player's status (in turn or not)
	 * @param estEnTour : boolean
	 */
	public void setEnTour(boolean estEnTour) {
		this.estEnTour = estEnTour;
	}
	
	/**
	 * Set player's coordAPlacer
	 * @param x : int
	 * @param y : int
	 */
	public void setCoordAPlacer(int x, int y) {
		this.coordAPlacer = new Coordonnees(x, y);
	}
	
	/**
	 * Set and get player's coordChoisieADeplacer et coordADeplacer
	 * @param x : int
	 * @param y : int
	 */
	public void setCoordChoisieADeplacer(int x, int y) {
		this.coordChoisieADeplacer = new Coordonnees(x, y);
	}
	
	/**
	 * Get card's coordinate that player want to move
	 * @return card's coordinate
	 */
	public Coordonnees getCoordChoisieADeplacer() {
		return this.coordChoisieADeplacer;
	}
	
	/**
	 * Set card's coordinate that player want to move to
	 * @param x
	 * @param y
	 */
	public void setCoordADeplacer(int x, int y) {
		this.coordADeplacer = new Coordonnees(x, y);
	}
	
	/**
	 * Check if player is in turn or not
	 * @return boolean
	 */
	public boolean getEnTour() {
		return this.estEnTour;
	}
	
	/**
	 * Get player's id
	 * @return player's id
	 */
	public int getId() {
		return this.id;
	}
}

