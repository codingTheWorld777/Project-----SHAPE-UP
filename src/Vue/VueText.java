package Vue;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.ImageIcon;

import Modele.Coordonnees;
import Modele.Observer;
import Modele.Partie;
import Modele.Joueur;
import Modele.Observable;

public class VueText extends Thread implements Observer, Runnable {
	
	public static String PROMPT = ">";
	public static String QUITTER = "Quit";
	public static String ENTRERCOORDONNEES = "enter coord";
	
	public VueText(Partie partie) {
		for (int i = 0; i<Partie.joueursEnJeu.length; i++) {
			Partie.joueursEnJeu[i].addObserver(this);
		}
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {

		String saisie = null;
		boolean quitter = false;

		System.out.println("Taper " + VueText.ENTRERCOORDONNEES + " pour entrer des coordonnees ; " + VueText.QUITTER + " pour quitter.");

		do {
		    saisie = this.lireChaine();

		    if (saisie != null) {
		    	if (saisie.equals(VueText.ENTRERCOORDONNEES) == true) {
		    		this.entrerCoord();
		    		// faire jouer le joueur ici (placer ou déplacer une carte)
		    	} else if (saisie.equals(VueText.QUITTER) == true) {
		    		quitter = true;		    
		    		} else {
		    			System.out.println("Commande non reconnue...");
		    		}		
		    }
		} while (quitter == false);
		System.exit(0);
	    }
	
	private String lireChaine() {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String resultat = null;
		try {
		    System.out.print(VueText.PROMPT);
		    resultat = br.readLine();
		} catch (IOException e) {
		    System.err.println(e.getMessage());
		}
		return resultat;	
	    }
	
	/**
	 * Enter a value of type 'int'
	 * @return
	 */
	public int saisirInt() {
		 Scanner src = new Scanner(System.in);
		 int num = 0;
		 
		 try {
			 num = src.nextInt();
			 
		 } catch (InputMismatchException e) {
			 System.out.println(e.toString());
			 System.out.println("Erreur! Resaisir...");
		 }
		 
		 return num;
	}
	
	/**
	 * Enter a value of type 'boolean'
	 * @return
	 */
	public boolean saisirBoolean() {
		Scanner src = new Scanner(System.in);
		boolean choix = false;
		
		try {
			 choix = src.nextBoolean();
			 
		} catch (InputMismatchException e) {
			 System.out.println(e.toString());
			 System.out.println("Erreur! Resaisir encore une fois...");
		}
		
		return choix;
	}
	
	/*
	 * Enter a value of type 'coord'
	 */
	public Coordonnees entrerCoord() {
		int x, y;
		System.out.println("Choisir l'abscisse x");
		x = saisirInt();
		System.out.println("Choisir l'ordonnée y");
		y = saisirInt();
		Coordonnees coord = new Coordonnees(x, y);
		return coord;
	}


	@Override
	public void update(Object o) {
		if (((Joueur) o).aPiocheUneCarte == true) {
			System.out.println("Le joueur " + ((Joueur) o).getId() + " à pioché et placé une carte.");
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
