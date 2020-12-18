package Controleur;

import Modele.InstallerJeu;
import Modele.Partie;
import Vue.FenetreTableDuJeu;

public class ControleurTableDuJeu {
	private static InstallerJeu installerJeu;
	
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
	
	public static String getNomDeJoueur(int id) {
		return Partie.joueursEnJeu[id].getNom();
	}
	
	/*
	 * Set and get installerJeu variable from Class InstallerJeu in package.Modele when it is created
	 */
	public static void setInstallerJeu(InstallerJeu installerJeu) {
		ControleurTableDuJeu.installerJeu = installerJeu;
	}
	
	public static InstallerJeu getInstallerJeu() {
		return ControleurTableDuJeu.installerJeu;
	}

}
