package Vue;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import Controleur.ControleurTableDuJeu;
import Modele.Compteur;
import Modele.Coordonnees;
import Modele.Observer;
import Modele.Partie;
import Modele.Joueur;
import Modele.Observable;

/** 
 * @author Huu Khai NGUYEN (Alec), Pierre-Louis DAMBRAINE
 *
 * Description: This class allows players to play on the console at the same time on the GUI
 */

public class VueText extends Thread implements Observer, Runnable {
	
	public static String PROMPT = ">";
	public static String QUITTER = "Quit";
	public static String ENTRERCOORDONNEES = "enter coord";
	public static String PLACEROUDEPLACER;
	public static String FINIRTOUR = "end tour";
	
	/** Constructor */
	public VueText() {
		for (int i = 0; i < ControleurTableDuJeu.getInstallerJeu().getNombreDeJoueurs(); i++) {
			Partie.joueursEnJeu[i].addObserver(this);
		}
		
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		String saisie = null;
		boolean quitter = false;

		System.out.println("Taper " + VueText.ENTRERCOORDONNEES + " pour entrer des coordonnees OU " + VueText.QUITTER + " pour quitter.");

		do {
		    saisie = this.lireChaine();

		    if (saisie != null) {
		  
		    	/** Player's round */
		    	if (saisie.equals(VueText.ENTRERCOORDONNEES) == true) {
		    		System.out.print("DEPLACER OU PLACER: >");
		    		Scanner src = new Scanner(System.in);
		    		String option = src.nextLine();
		    		
		    		if (option.equals("PLACER")) {		/** Player want to place a card */
		    			Coordonnees coord = this.entrerCoord();
			    		for (int k = 0; k < ControleurTableDuJeu.getInstallerJeu().getNombreDeJoueurs(); k++) {
			    			if (ControleurTableDuJeu.getJoueur(k).getEnTour() == true && ControleurTableDuJeu.getJoueur(k).aPiocheUneCarte == false) {
			    				/** Set to run joueursEnJeu[k].jouerSonTour(...) in Partie.java */
			    				ControleurTableDuJeu.getJoueur(k).setCoordAPlacer(coord.x, coord.y);
			    				break;
			    				
			    			} else if (ControleurTableDuJeu.getJoueur(k).getEnTour() == true && ControleurTableDuJeu.getJoueur(k).aPiocheUneCarte == true) {
			    				System.out.println("Vous avez PIOCHE une carte!!!!");
			    				break;
			    			}
			    		}
			    		
		    		} else if (option.equals("DEPLACER")) {	/** Player want to move a card */
		    			if (!ControleurTableDuJeu.getPermettreDeDeplacer()) {
		    				System.out.println("Vous avez DEPLACE une carte!!!!");
		    				continue;
		    			}
		    			
		    			System.out.println("Choisir les coordonnees d'une carte que vous voulez deplacer: ");
		    			
		    			Coordonnees coord = this.entrerCoord();
			    		for (int k = 0; k < ControleurTableDuJeu.getInstallerJeu().getNombreDeJoueurs(); k++) {
			    			if (ControleurTableDuJeu.getJoueur(k).getEnTour() && ControleurTableDuJeu.estDeplacable(coord.x, coord.y)) {
			    				ControleurTableDuJeu.getJoueur(k).setCoordChoisieADeplacer(coord.x, coord.y);
			    				
			    				System.out.println("Choisir les coordonnees que vous voulez deplacer la carte vers: ");
			    				coord = this.entrerCoord();
			    				if (ControleurTableDuJeu.isInPositionDeDeplacer(coord.x, coord.y) && ControleurTableDuJeu.getPermettreDeDeplacer()) {
			    					ControleurTableDuJeu.getJoueur(k).setCoordADeplacer(coord.x, coord.y);
			    					ControleurTableDuJeu.getJoueur(k).deplacerCarte();
			    					ControleurTableDuJeu.setPermettreDeDeplacer(false);
			    					
					    		} else System.out.println("Cette postion ne correspond pas.");

			    				break;
			    				
			    			} else if (ControleurTableDuJeu.getJoueur(k).getEnTour() && !ControleurTableDuJeu.estDeplacable(coord.x, coord.y)){
				    			System.out.println("Cette position ne correspond pas.");
				    			break;
				    		}
			    		}
			    		
		    		}
		    		
		    	
		    	/** Finish a player round */
		    	} else if (saisie.equals(VueText.FINIRTOUR) == true) {
		    		for (int k = 0; k < ControleurTableDuJeu.getInstallerJeu().getNombreDeJoueurs(); k++) {
		    			Joueur joueur = ControleurTableDuJeu.getJoueur(k);
		    			
		    		if (joueur.getId() == Partie.tourDeJoueur) {
						if (joueur.aPiocheUneCarte == true) {
							joueur.pouvoirFinirMonTour = true;
							ControleurTableDuJeu.setPermettreDeDeplacer(true);
						} 

						/** 
						 * 1) Change color of player in turn to green and the others to pink
						 * 2) Count scores of each player after 1 turn and show its in screen
						 */
						Compteur compteurPoint = new Compteur();
						compteurPoint.compter(Partie.getTableDuJeu());
						
						for (int i = 0; i < ControleurTableDuJeu.getInstallerJeu().getNombreDeJoueurs(); i++) {
							//1)
							if (ControleurTableDuJeu.getJoueur(i).getEnTour()) FenetreTableDuJeu.getJoueurPanel(i + 1).setBackground(ControleurTableDuJeu.getJoueurBackg());
							else FenetreTableDuJeu.getJoueurPanel(i + 1).setBackground(UIManager.getColor("Button.select"));
							
							//2)
							if (i == 0) 
								FenetreTableDuJeu.point1.setText("Point: " + compteurPoint.getPointsJoueurs(ControleurTableDuJeu.getJoueur(i).getId()));
							
							if (i == 1) 
								FenetreTableDuJeu.point2.setText("Point: " + compteurPoint.getPointsJoueurs(ControleurTableDuJeu.getJoueur(i).getId()));
						
							if (ControleurTableDuJeu.getInstallerJeu().getNombreDeJoueurs() == 3 && i == 2) 
								FenetreTableDuJeu.point3.setText("Point: " + compteurPoint.getPointsJoueurs(ControleurTableDuJeu.getJoueur(i).getId()));

						}

						ControleurTableDuJeu.setPouvoirPiocher(true);
						joueur.coordChoisieADeplacer = null;
						joueur.coordADeplacer = null;
						
						ControleurTableDuJeu.setBorderColorToOrg();
					}
		    	}
		    		
		    	/** Exit game */
		    	} else if (saisie.equals(VueText.QUITTER) == true) {
		    		quitter = true;	
		    		
		    	} else System.out.println("Commande non reconnue...");		
		    }
		    
		} while (quitter == false);
			System.exit(0);
	    }
	
	/** 
	 * Read input of player's option
	 * @return
	 */
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
		System.out.println("Choisir l'ordonnee y");
		y = saisirInt();
		Coordonnees coord = new Coordonnees(x, y);
		return coord;
	}


	@Override
	public void update(Object o) {
		if (((Joueur) o).aPiocheUneCarte == true) {
			System.out.println(((Joueur) o).getNom() + " a pioche et place une carte.");
		}
	}
}