package Modele;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Carte {
	/**
	 * @author Huu Khai NGUYEN (Alec), Pierre-Louis DAMBRAINE
	 */
	
	/**
	 * There are 3 possible shapes for a card: circle, triangle, square.
	 */
	protected enum formePossible {cercle, carre, triangle};
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
	 * Each card has a index in order to get image
	 */
	private BufferedImage carteImageRecto;
	private BufferedImage carteImageVerso;
	private static int nombreDeCarte;
	
	/**
	 * coordinates of card (of card played) 
	 */
	Coordonnees coordonnesDeCarte;
	

	/**
	 * Constructor
	 * @param forme
	 * @param nature
	 * @param couleur
	 */
	public Carte(formePossible forme, naturePossible nature, couleurPossible couleur) { //+ BufferedImage carteImage
		this.forme = forme;
		this.nature = nature;
		this.couleur = couleur;

		this.toVerso();

		try {
			//Load the image for the current file to render "face up" of card
			URL url = getClass().getResource("../images/" + Carte.nombreDeCarte + ".png");
			this.carteImageRecto = ImageIO.read(url);
			
			//Load the backup of card
			url = getClass().getResource("../images/dos.png");
			this.carteImageVerso = ImageIO.read(url);
			
//			this.setBounds(0, 0, 100, 50);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	 * Set number for card
	 */
	public static void setNombreDeCarte(int nombreDeCarte) {
		Carte.nombreDeCarte = nombreDeCarte;
	}
	
	/**
	 * Get number of card to render image 
	 */
	public static int getNombreDeCarte() {
		return Carte.nombreDeCarte;
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
