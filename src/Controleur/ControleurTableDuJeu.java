package Controleur;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import Modele.Carte;
import Modele.Coordonnees;
import Modele.InstallerJeu;
import Modele.InstallerTour;
import Modele.Joueur;
import Modele.JoueurPhy;
import Modele.Partie;
import Modele.PiocheCartes;
import Modele.Plateau;
import Vue.ButtonCard;
import Vue.FenetreTableDuJeu;

public class ControleurTableDuJeu {
	private static InstallerJeu installerJeu;
	private static InstallerTour installerTour;
	

	
	public void ControleurTableDuJeu(Joueur joueur, ButtonCard btnCarte) {
		btnCarte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Coordonnees coord;
				
				try {
					if (FenetreTableDuJeu.carteJouee.getCarteTiree()) {
						if (Partie.tour == 0 && joueur.getEnTour() == true) {
							coord = btnCarte.getCoordonnees();
							joueur.setCoordAPlacer(coord.x, coord.y);
							Image imgRecto = ControleurTableDuJeu.getCartePiochee().getCarteImageRecto();
							imgRecto = imgRecto.getScaledInstance(btnCarte.getWidth(), btnCarte.getHeight(), Image.SCALE_DEFAULT);
							btnCarte.setIcon(new ImageIcon(imgRecto));
							FenetreTableDuJeu.carteJouee.setCarteTiree(false);
						} else if (joueur.getEnTour() == true){
							coord = btnCarte.getCoordonnees();
							if (Plateau.isInPossibilites(coord.x, coord.y)) {
								joueur.setCoordAPlacer(coord.x, coord.y);
								Image imgRecto = ControleurTableDuJeu.getCartePiochee().getCarteImageRecto();
								imgRecto = imgRecto.getScaledInstance(btnCarte.getWidth(), btnCarte.getHeight(), Image.SCALE_DEFAULT);
								btnCarte.setIcon(new ImageIcon(imgRecto));
								FenetreTableDuJeu.carteJouee.setCarteTiree(false);
							} else {
								System.out.println("réessayez");
							}
						
						
						}
					} else {
						System.out.println("Piochez une carte !");
					}
				} catch (Exception err) {
					System.out.println(err.toString());
				}
			}
		});
	}
	
	public boolean checkPossibilites(ButtonCard[][] tabBtnCarte) {
		boolean isInPossibilites = true;
		for (int i = 0; i<tabBtnCarte.length; i++) {
			for (int j = 0; j<tabBtnCarte[i].length; j++) {
				if (!Plateau.isInPossibilites(tabBtnCarte[i][j].getCoordonnees().x, tabBtnCarte[i][j].getCoordonnees().y)) {
					isInPossibilites = false;
				}
			}
		}
			
			
		return isInPossibilites;
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