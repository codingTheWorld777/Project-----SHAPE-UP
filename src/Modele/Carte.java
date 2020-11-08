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
	 * Face of card: UP or DOWN
	 */
	private boolean recto;
	
	/*
	 * This is card's image in the game.
	 * Each card has a different image
	 * Each card can be up or down (recto vs verso)
	 */
	private BufferedImage carteImage;
	
	/*
	 * Constructor of card
	 */
	public Carte(String forme, String couleur, String nature) { //+ BufferedImage carteImage
		this.forme = forme;
		this.couleur = couleur;
		this.nature = nature;
//		this.carteImage = carteImage;
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
	 * get card's color
	 */
	public String getCouleur() {
		return this.couleur;
	}
	
	/*
	 * Set card's color
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	/*
	 * get card's nature
	 */
	public String getNature() {
		return this.nature;
	}
	
	/*
	 * Set card's nature
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	/*
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
	
	/*
	 * Set image to card: Face up OR Face down
	 */
	public void setCarteImage(boolean recto) {
		if (recto) {
			System.out.println("Recto");
		} else {
			System.out.println("Verso");
		}
	}
	
	
}

