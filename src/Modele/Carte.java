package Modele;

import java.awt.image.BufferedImage;

public class Carte {
	/*
	 * There are 3 possible shapes for a card: circle, triangle, square.
	 */
	private String forme;
	
	/*
	 * There are 3 possible colors for a card: red, blue or green
	 */
	private String couleur;
	
	/*
	 * There are only 2 main states of card: hollow and solid
	 */
	private String nature;
	
	/*
	 * This is card's image in the game.
	 * Each card has a different image
	 */
	private BufferedImage carteImage;
	
	/*
	 * Constructor of card
	 */
	public Carte(String forme, String couleur, String nature, BufferedImage carteImage) {
		this.forme = forme;
		this.couleur = couleur;
		this.nature = nature;
		this.carteImage = carteImage;
	}
	
	/*
	 * Get card's Image
	 */
	public BufferedImage getCarteImage() {
		return this.carteImage;
	}
	
	/*
	 * get card's form
	 */
	public String getForme() {
		return this.forme;
	}
	
	/*
	 * Set card's form
	 */
	public void setForme(String forme) {
		this.forme = forme;
	}
	
	/*
	 * get card's form
	 */
	public String getCouleur() {
		return this.couleur;
	}
	
	/*
	 * Set card's form
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	/*
	 * get card's form
	 */
	public String getNature() {
		return this.nature;
	}
	
	/*
	 * Set card's form
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}
}
