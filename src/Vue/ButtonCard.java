package Vue;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Modele.Carte;
import Modele.Coordonnees;

public class ButtonCard extends JButton {
	private boolean recto = false;
	private Coordonnees coordonnees;
	
	private Image carteImage;
	
	public ButtonCard() {
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(((ButtonCard) e.getSource()).getCoordonnees().x + ", " + ((ButtonCard) e.getSource()).getCoordonnees().y);
				
			}
			
		});
	}
	
	/**
	 * Players click this type of button to draw one card
	 * @param carteJouee
	 */
	public ButtonCard(JButton carteJouee) {
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (recto == false) {
					recto = true;

					try {
						Image carteImage = ImageIO.read(getClass().getResource("../images/" + Carte.getNombreDeCarte() + ".png"));
						carteJouee.setIcon(new ImageIcon(carteImage));
					} catch (Exception err) {
						System.out.println(err.toString());
					}
					
				} else recto = false;
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
}
