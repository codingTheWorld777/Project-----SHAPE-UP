package Modele;

import java.awt.EventQueue;

import Controleur.ControleurParametre;
import Vue.FenetreParametre;

public class JouerJeu {	
	public JouerJeu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreParametre fenetreParametre = new FenetreParametre();
					
					ControleurParametre.setFenetreParametre(fenetreParametre.getFrame());
				
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
	}
	
	public static void main(String[] args) {
		JouerJeu ShapeUp = new JouerJeu();
	}
}
