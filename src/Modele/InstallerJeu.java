package Modele;

import java.util.Scanner;

/**
 * @author Huu Khai NGUYEN (Alec)
 * 
 * Description: This class allows to install a game. 
 * We divide this function into 3 main phases:
	 * 	- Choose the variation for game (Rectangle 5x3 / Square 4x4 /Pyramid)
	 * 	- Choose the number of player (2 or 3)
	 * 	- Activate virtual player (yes or no)
	 * 	- Choose level of virtual player (easy ot difficult)
 */

public class InstallerJeu {

	/** Form of game */
	private static String varianteDuTapis = "";
	
	/** Number of player */
	private static int nombreDeJoueurs;
	
	/** Activate virtual player */
	private static boolean activerJoueurVir;
	public static int activerJoueurVirValidation;
	
	/** Choose level of virtual player */
	private static String niveau;
	public static int niveauValidation;
	
	/** Constructor InstallerJeu */
	public InstallerJeu() {
		this.getDonnees(); 
	}
	
	
	/** Get datas from player */
	public void getDonnees() {
		Scanner src = new Scanner(System.in);
		
		/** Set form for game (Rectangle 5x3 / Square 4x4 /Pyramid) */
		System.out.print("Entrer la forme du tapis de jeu (R/C/P): ");
		if (this.varianteDuTapis == null) this.varianteDuTapis = src.nextLine();
		else System.out.println(this.varianteDuTapis);
		
		/** Set number of players */
		System.out.print("Entrer le nombre de joueurs (2-3): ");
		if (this.nombreDeJoueurs == 0) this.nombreDeJoueurs = Integer.parseInt(src.nextLine());
		else System.out.println(this.nombreDeJoueurs);
		
		/** Activate virtual player: Yes or No */
		System.out.print("Activer le joueur virtuel (true/false): ");
		if (this.activerJoueurVirValidation == 0) this.activerJoueurVir = src.nextBoolean();	
		else System.out.println(this.activerJoueurVir);
		
		/** Set level */ 
		if (this.activerJoueurVir == true) {
			System.out.print("Choisir niveau du joueur virtuel (D/F): ");
			if (this.niveauValidation == 0) this.niveau = src.next();
			else System.out.println(this.niveau);
		} else System.out.println(this.activerJoueurVir);

		
		Partie.nombreDeCartesJouables = 17 - this.nombreDeJoueurs;
		Partie.joueursEnJeu = new Joueur[this.nombreDeJoueurs];
	}
	
	/**
	 * Get variation of game
	 */
	public static String getVarianteDuTapis() {
		return varianteDuTapis;
	}
	
	/**
	 * Set variation for game
	 */
	public static void setVarianteDuTapis(String varianteDuTapis) {
		InstallerJeu.varianteDuTapis = varianteDuTapis;
	}
	
	/**
	 * Get number of players
	 */
	public static int getNombreDeJoueurs() {
		return nombreDeJoueurs;
	}
	
	/**
	 * Set number for players
	 */
	public static void setNombreDeJoueurs(int nombreDeJoueurs) {
		InstallerJeu.nombreDeJoueurs = nombreDeJoueurs;
	}
	
	/**
	 * Get option: Activate virtual player
	 */
	public static boolean getActiverJoueurVir() {
		return activerJoueurVir;
	}
	
	/**
	 * Set option: Activate virtual player
	 */
	public static void setActiverJoueurVir(boolean activerJoueurVir) {
		InstallerJeu.activerJoueurVir = activerJoueurVir;
	}
	
	/**
	 * Get level of virtual player
	 */
	public static String getNiveau() {
		return niveau;
	}
	
	/**
	 * Set level for virtual player
	 */
	public static void setNiveau(String niveau) {
		InstallerJeu.niveau = niveau;
	}

}
