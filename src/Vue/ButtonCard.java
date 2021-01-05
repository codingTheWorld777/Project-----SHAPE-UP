package Vue;

/**
 * @author Huu Khai NGUYEN (Alec), Pierre-Louis DAMBRAINE
 * Description: This class allows to dipslay a card on GUI in different situation
 */

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Modele.Carte;
import Modele.Coordonnees;
import Modele.Partie;
import Modele.Plateau;
import Controleur.ControleurTableDuJeu;

public class ButtonCard extends JButton {
	private boolean recto = false;
	private Coordonnees coordonnees;
	private Image carteImage;
	private boolean carteTiree = false;
	
	public static ButtonCard[][] cartesBtn;
	
	public ButtonCard(int x, int y) {
		this.coordonnees = new Coordonnees(x, y);
	}
	
	/**
	 * Players click this type of button to draw one card
	 * @param carteJouee
	 */
	public ButtonCard(JButton piocheCarte) {
		piocheCarte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ControleurTableDuJeu.setBorderColorToOrg1();
				
				for (int i = 0; i < Plateau.getPossibilites().size(); i++) {
					int x = Plateau.getPossibilites().get(i).x;
					int y = Plateau.getPossibilites().get(i).y;
					cartesBtn[y][x].setBorder(BorderFactory.createLineBorder(Color.green));
				}
				
				if (recto == false) {
					recto = true;

					try {
						Image imgRecto = ControleurTableDuJeu.getCartePiochee().getCarteImageRecto();
						imgRecto = imgRecto.getScaledInstance(piocheCarte.getWidth(), piocheCarte.getHeight(), Image.SCALE_DEFAULT);
						setIcon(new ImageIcon(imgRecto));
						carteTiree = true;
					} catch (Exception err) {
						System.out.println(err.toString());
					}
					
				} else recto = false;
				
				ControleurTableDuJeu.setBorderColorToOrg();
			}
			
		});
	}
	
	/**
	 * Players click button to show their card or hidden card (of course at the end...)
	 * @param carteVictoire
	 */
	public ButtonCard(Carte carte) {
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (recto == false) {
					recto = true;

					try {
						carteImage = carte.getCarteImageRecto().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
						setIcon(new ImageIcon(carteImage));
					} catch (Exception err) {
						System.out.println(err.toString());
					}
					
				} else {
					recto = false;
					
					try {
						carteImage = carte.getCarteImageVerso().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
						setIcon(new ImageIcon(carteImage));
					} catch (Exception err) {
						System.out.println(err.toString());
					}
				}
			}
			
		});
	}
	
	/**
	 * Get or set coordinates of card in boardgame
	 */
	public Coordonnees getCoordonnees() {
		return this.coordonnees;
	}
	
	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	/**
	 * Get or set boolean carteTiree
	 */
	public boolean getCarteTiree() {
		return this.carteTiree;
	}
	
	public void setCarteTiree(boolean b) {
		this.carteTiree = b;
	}
	 
}