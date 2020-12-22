package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Modele.Carte;
import Modele.Coordonnees;
import Modele.InstallerJeu;
import Modele.InstallerTour;
import Modele.Joueur;
import Modele.JoueurPhy;
import Modele.Partie;
import Modele.PiocheCartes;
import Vue.ButtonCard;
import Vue.FenetreTableDuJeu;

public class ControleurTableDuJeu {
	private static InstallerJeu installerJeu;
	private static InstallerTour installerTour;
	
	private JButton btnCarte;
	
	public void ControleurTableDuJeu(Joueur joueur, ButtonCard btnCarte) {
		btnCarte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ah oui");
				Coordonnees coord = btnCarte.getCoordonnees();
				
				try {
					joueur.setCoordAPlacer(coord);
					System.out.println("ah oui");
				} catch (Exception err) {
					System.out.println(err.toString());
				}
			}
		});
	}
	


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
