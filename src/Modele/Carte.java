package Modele;

public class Carte {
	/**
	 * There are 3 possible shapes for a card: circle, triangle, square.
	 */
	protected enum formePossible {cercle, carré, triangle};
	private formePossible forme;
	
	/**
	 * There are only 2 main states of card: hollow and solid
	 */
	protected enum naturePossible {plein, vide};
	private naturePossible nature;
	
	/**
	 * There are 3 possible colors for a card: red, blue or green
	 */
	protected enum couleurPossible {bleu, vert, rouge};
	private couleurPossible couleur;
	
	/**
	 * Face of card: UP or DOWN
	 */
	private boolean recto;
	
	/**
	 * This is card's image in the game.
	 * Each card has a different image
	 * Each card can be up or down (recto vs verso)
	 */
//	private BufferedImage carteImage;
	
	/**
	 * coordinates of card (of card played) 
	 */
	Coordonnees coordonnesDeCarte;
	

	/**
	 * 
	 * @param forme
	 * @param nature
	 * @param couleur
	 */
	public Carte(formePossible forme, naturePossible nature, couleurPossible couleur) { //+ BufferedImage carteImage
		this.forme = forme;
		this.nature = nature;
		this.couleur = couleur;
//		this.carteImage = carteImage;
	}
	
	/**
	 * get card's form
	 */
	public formePossible getForme() {
		return this.forme;
	}
	
	/**
	 * Set card's form
	 */
	public void setForme(formePossible forme) {
		this.forme = forme;
	}
	
	/**
	 * get card's color
	 */
	public couleurPossible getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Set card's color
	 */
	public void setCouleur(couleurPossible couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * get card's nature
	 */
	public naturePossible getNature() {
		return this.nature;
	}
	
	/**
	 * Set card's nature
	 */
	public void setNature(naturePossible nature) {
		this.nature = nature;
	}
	
	/**
	 * Change the face of card
	 */
	public void toRecto() {
		this.recto = true;
	}
	
	public void toVerso() {
		this.recto = false;
	}
	
	public boolean getFace() {
		return this.recto;
	}
	
	/**
	 * Set image to card: Face up OR Face down
	 */
	public void setCarteImage(boolean recto) {
		if (recto) {
			System.out.println("Recto");
		} else {
			System.out.println("Verso");
		}
	}
	
	/**
	 * Set coordinates for card
	 */
	public void setCoordonnees(int x, int y) {
		this.coordonnesDeCarte = new Coordonnees(x, y);
	}
	
	/**
	 * Get coordinates for card
	 */
	public Coordonnees getCoordonnees() {
		return this.coordonnesDeCarte;
	}
}
