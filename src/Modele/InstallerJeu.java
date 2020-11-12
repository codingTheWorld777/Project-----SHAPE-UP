package Modele;

import java.util.Scanner;

public class InstallerJeu {
	/**
	 * We divide this function into 3 main phases:
	 * 	- Choose the number of player (2 or 3)
	 * 	- Activate virtual player (yes or no)
	 * 	- Choose level of virtual player (easy ot difficult)
	 */
	
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
		
		//Set number of players
		System.out.println("Entrer le nombre de joueurs (2-3): ");
		this.nombreDeJoueurs = Integer.parseInt(src.nextLine());
		
		//Activate virtual player: Yes or No
		System.out.println("Activer le joueur virtuel (Y/N): ");
		this.activerJoueurVir = src.nextBoolean();		
		
		//Set level 
		System.out.println("Choisir niveau du joueur virtuel (D/F): ");
		this.niveau = src.nextLine();
		
		Partie.nombreDeCartesJouables = 17 - this.nombreDeJoueurs;
		System.out.println(this.nombreDeJoueurs + " " + this.activerJoueurVir + " " + this.niveau + " Niveau");
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
