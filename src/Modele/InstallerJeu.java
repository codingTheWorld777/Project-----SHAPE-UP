package Modele;

import java.util.Scanner;

public class InstallerJeu {
	/**
	 * @author Huu Khai NGUYEN (Alec)
	 */
	
	/**
	 * We divide this function into 3 main phases:
	 * 	- Choose the variation for game (Rectangle 5x3 / Square 4x4 /Pyramid)
	 * 	- Choose the number of player (2 or 3)
	 * 	- Activate virtual player (yes or no)
	 * 	- Choose level of virtual player (easy ot difficult)
	 */
	
	//Form of game
	private static String varianteDuTapis;
	
	//Number of player
	private static int nombreDeJoueurs;
	
	//Activate virtual player
	private static boolean activerJoueurVir;
	
	//Choose level of virtual player
	private static String niveau;
	
	public InstallerJeu() {
		this.getDonnees(); 
	}
	
	public void getDonnees() {
		
		Scanner src = new Scanner(System.in);
		
		//Set form for game (Rectangle 5x3 / Square 4x4 /Pyramid)
		System.out.println("Entrer la forme du tapis de jeu (R/C/P): ");
		this.varianteDuTapis = src.nextLine();
		
		//Set number of players
		System.out.println("Entrer le nombre de joueurs (2-3): ");
		this.nombreDeJoueurs = Integer.parseInt(src.nextLine());
		
		//Activate virtual player: Yes or No
		System.out.println("Activer le joueur virtuel (true/false): ");
		this.activerJoueurVir = src.nextBoolean();		
		
		//Set level 
//		System.out.println("Choisir niveau du joueur virtuel (D/F): ");

		
		Partie.nombreDeCartesJouables = 17 - this.nombreDeJoueurs;
		Partie.joueursEnJeu = new Joueur[this.nombreDeJoueurs];
//		System.out.println(this.nombreDeJoueurs + " " + this.activerJoueurVir + " " + this.niveau + " Niveau");
	}
	
	/*
	 * Get variation of game
	 */
	public static String getVarianteDuTapis() {
		return varianteDuTapis;
	}
	
	/*
	 * Get number of players
	 */
	public static int getNombreDeJoueurs() {
		return nombreDeJoueurs;
	}
	
	/*
	 * Get option: Activate virtual player
	 */
	public static boolean getActiverJoueurVir() {
		return activerJoueurVir;
	}
	
	/*
	 * Get level of virtual player
	 */
	public static String getNiveau() {
		return niveau;
	}
}
