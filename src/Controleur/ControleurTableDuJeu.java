package Controleur;

import Modele.Carte;
import Modele.InstallerJeu;
import Modele.InstallerTour;
import Modele.Joueur;
import Modele.Partie;
import Modele.PiocheCartes;
import Vue.FenetreTableDuJeu;

public class ControleurTableDuJeu {
	private static InstallerJeu installerJeu;
	private static InstallerTour installerTour;
	
	/**
	 * Check if the game includes a three player and paint it 
	 * @param installerJeu
	 * @param tableDuJeu
	 * @return
	 */
	public static boolean paintJoueur3(InstallerJeu installerJeu, FenetreTableDuJeu tableDuJeu) {
		if (InstallerJeu.getNombreDeJoueurs() == 3) return true;
		
		return false;
	}
	
	public static Joueur getJoueur(int id) {
		return Partie.joueursEnJeu[id];
	}
	
	/*
	 * Get hidden card from "Partie"
	 */
	public Carte getCarteCachee() {
		return Partie.carteCachee;
	}
	
	/*
	 * Get the drawn card from "PiocheCartes"
	 */
	public static Carte getCartePiochee() {
		return PiocheCartes.getPiocheCartes().get(PiocheCartes.getPiocheCartes().size() - 1);
	}
	
	/*
	 * Get the number of playable cards
	 */
	public int getNombreCarteJouable() {
		return PiocheCartes.getPiocheCartes().size();
	}
	
	/*
	 * Set and get installerJeu variable from class Partie in package.Modele when it is created
	 */
	public static void setInstallerJeu(InstallerJeu installerJeu) {
		ControleurTableDuJeu.installerJeu = installerJeu;
	}
	
	public static InstallerJeu getInstallerJeu() {
		return ControleurTableDuJeu.installerJeu;
	}
	
	/*
	 * Set and get installerTour variable fron class Partie in package,Modele when it is created
	 */
	public static void setInstallerTour(InstallerTour installerTour) {
		ControleurTableDuJeu.installerTour = installerTour;
	}
	
	public static InstallerTour getInstallerTour() {
		return ControleurTableDuJeu.installerTour;
	}
	

}
