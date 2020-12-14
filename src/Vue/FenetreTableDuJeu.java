package Vue;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import Modele.PiocheCartes;

public class FenetreTableDuJeu extends JFrame {
	public FenetreTableDuJeu() {
		
		setLayout(new BorderLayout());

//		add(PiocheCartes.getPiocheCartes().get(7), BorderLayout.EAST);
		setSize(1200, 740);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
