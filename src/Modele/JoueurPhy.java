package Modele;

import java.util.Scanner;

public class JoueurPhy extends Joueur implements Strategie{
	public JoueurPhy(String name, int id) {
		super(name, id);
	}
	
	public void piocherCarte(Carte[][] tableDuJeu) {
		System.out.println("Choisir l'abscisse x de carte sur le table du jeu: ");
		Scanner src = new Scanner(System.in);
		int x = src.nextInt();
		
		System.out.println("Choisir l'ordonnée y de carte sur le table du jeu: ");
		int y = src.nextInt();
		
		System.out.println(x + ", " + y);
		tableDuJeu[y][x] = PiocheCartes.getPiocheCartes().get(Partie.nombreDeCartesJouables - 1);
		PiocheCartes.getPiocheCartes().remove(Partie.nombreDeCartesJouables - 1);
		Partie.nombreDeCartesJouables--;
		
		Partie.updateTableDuJeu();
	
	}
	
	public void deplacerCarte() {
		System.out.println("Choisir  (x, y) de carte déplacée: ");
	
	}
	
	/*
	 * Get level for virtuel player
	 * There are two levels of this type of player: easy and difficult
	 */
	public String getNiveau() {
		return "";
	}
	
	public void setStrategie(String niveau) {
		
	}
	
}
