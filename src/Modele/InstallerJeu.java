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
		if (this.activerJoueurVir == true ) {
			System.out.println("Choisir niveau du joueur virtuel (D/F): ");
			this.niveau = src.next();
		}

		
		Partie.nombreDeCartesJouables = 17 - this.nombreDeJoueurs;
		Partie.joueursEnJeu = new Joueur[this.nombreDeJoueurs];
//		System.out.println(this.nombreDeJoueurs + " " + this.activerJoueurVir + " " + "Niveau " + this.niveau);
	}
	
	/*
	 * Get variation of game
	 */
	public static String getVarianteDuTapis() {
		return varianteDuTapis;
	}
	
	/*
	 * Set variation for game
	 */
	public void setVarianteDuTapis(String varianteDuTapis) {
		InstallerJeu.varianteDuTapis = varianteDuTapis;
	}
	
	/*
	 * Get number of players
	 */
	public static int getNombreDeJoueurs() {
		return nombreDeJoueurs;
	}
	
	/*
	 * Set number for players
	 */
	public void setNombreDeJoueurs(int nombreDeJoueurs) {
		InstallerJeu.nombreDeJoueurs = nombreDeJoueurs;
	}
	
	/*
	 * Get option: Activate virtual player
	 */
	public static boolean getActiverJoueurVir() {
		return activerJoueurVir;
	}
	
	/*
	 * Set option: Activate virtual player
	 */
	public void setActiverJoueurVir(boolean activerJoueurVir) {
		InstallerJeu.activerJoueurVir = activerJoueurVir;
	}
	
	/*
	 * Get level of virtual player
	 */
	public static String getNiveau() {
		return niveau;
	}
	
	/*
	 * Set level for virtual player
	 */
	public void setNiveau(String niveau) {
		InstallerJeu.niveau = niveau;
	}

}
