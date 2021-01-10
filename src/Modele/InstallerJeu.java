package Modele;

import java.util.Scanner;

/**
 * @author Huu Khai NGUYEN (Alec)
 * <br>
 * Description: This class allows to install a game. <br>
 * We divide this function into 3 main phases:
 * <ul>
	 <li>Choose the variation for game (Rectangle 5x3 / Square 4x4 /Pyramid)</li>
	 <li> Choose the number of player (2 or 3)</li>
	 <li> Activate virtual player (yes or no)</li>
	 <li>Choose level of virtual player (easy ot difficult)</li>
 * </ul>
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
	
	/** Activate the game on console */
	private static boolean activerLeJeuSurConsole;
	public static int activerLeJeuSurConsoleValidation;
	
	/** Constructor InstallerJeu */
	public InstallerJeu() {
		this.getDonnees(); 
	}
	
	
	/** Get datas from player concerning the game (variation of game, number of player, etc...) */
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
		
		/** Set the game on console */
		if (this.activerLeJeuSurConsole == true) {
			System.out.println("Vous pouvez jouer le jeu sur la console !");
		} 
		
		System.out.println();
		
		Partie.nombreDeCartesJouables = 17 - this.nombreDeJoueurs;
		Partie.joueursEnJeu = new Joueur[this.nombreDeJoueurs];
	}
	
	/**
	 * Get variation of game
	 * @return game's variation : String
	 */
	public static String getVarianteDuTapis() {
		return varianteDuTapis;
	}
	
	/**
	 * Set variation for game
	 * @param varianteDuTapis : String
	 */
	public static void setVarianteDuTapis(String varianteDuTapis) {
		InstallerJeu.varianteDuTapis = varianteDuTapis;
	}
	
	/**
	 * Get number of players
	 * @return number of player
	 */
	public static int getNombreDeJoueurs() {
		return nombreDeJoueurs;
	}
	
	/**
	 * Set number for players
	 * @param nombreDeJoueurs : int
	 */
	public static void setNombreDeJoueurs(int nombreDeJoueurs) {
		InstallerJeu.nombreDeJoueurs = nombreDeJoueurs;
	}
	
	/**
	 * Get option: Activate virtual player
	 * @return boolean
	 */
	public static boolean getActiverJoueurVir() {
		return activerJoueurVir;
	}
	
	/**
	 * Set option: Activate virtual player
	 * @param activerJoueurVir : boolean
	 */
	public static void setActiverJoueurVir(boolean activerJoueurVir) {
		InstallerJeu.activerJoueurVir = activerJoueurVir;
	}
	
	/**
	 * Get level of virtual player
	 * @return level: String
	 */
	public static String getNiveau() {
		return niveau;
	}
	
	/**
	 * Set level for virtual player
	 * @param niveau : String
	 */
	public static void setNiveau(String niveau) {
		InstallerJeu.niveau = niveau;
	}
	
	/**
	 * Get console option
	 * @return boolean
	 */
	public static boolean getConsoleOption() {
		return InstallerJeu.activerLeJeuSurConsole;
	}
	
	/**
	 * Set option "Activate the console"
	 * @param option : boolean
	 */
	public static void setConsoleOption(boolean option) {
		InstallerJeu.activerLeJeuSurConsole = option;
	}
}