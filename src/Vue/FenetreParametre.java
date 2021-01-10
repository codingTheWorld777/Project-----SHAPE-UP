package Vue;

import java.awt.EventQueue;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import Controleur.ControleurParametre;

/**
 * @author Huu Khai NGUYEN (Alec)
 * <br>
 * Description: This class is a installation window that allows player to parameter the game 
 */

public class FenetreParametre {
	private JFrame frame;
	
	/**
	 * Component of frame
	 */
	private static JPanel variantePanel;
	private static JPanel nombreDeJoueursPanel;
	private static JPanel activerJoueurVirPanel;
	private static JPanel niveauPanel;
	private static JPanel activerLeJeuSurConsolePnl;
	private static JButton valider;
	

	/**
	 * Create the application.
	 */
	public FenetreParametre() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		ControleurParametre controleurParametre = new ControleurParametre();
		
		/* ****************** Forme of game option {classique, carre, pyramide} ******************* */
		variantePanel = new JPanel();
		variantePanel.setBounds(20, 27, 677, 38);
		frame.getContentPane().add(variantePanel);
		variantePanel.setLayout(null);
		
		JLabel varianteLabel = new JLabel("Choisir la variante du tapis de jeu: ");
		varianteLabel.setBounds(0, 1, 328, 35);
		varianteLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		variantePanel.add(varianteLabel);
		
		String[] variantes = {"", "classique", "caree", "pyramide"};
		
		/** Handling event** >>ControleurParametre */
		JComboBox comboBoxVariante = new JComboBox(variantes);
		controleurParametre.controleurParametre(ControleurParametre.getInstallerJeu(), comboBoxVariante); 	//Controleur: render variation
		
		comboBoxVariante.setBounds(340, 6, 119, 27);
		variantePanel.add(comboBoxVariante);
		
		
		/** ******************* Number of player option (2-3) ******************* */
		JPanel nombreDeJoueursPanel = new JPanel();
		nombreDeJoueursPanel.setBounds(20, 107, 677, 38);
		frame.getContentPane().add(nombreDeJoueursPanel);
		nombreDeJoueursPanel.setLayout(null);
		
		JLabel nombreDeJoueursLabel = new JLabel("Choisir le nombre de joueurs: ");
		nombreDeJoueursLabel.setBounds(0, 3, 281, 30);
		nombreDeJoueursLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		nombreDeJoueursPanel.add(nombreDeJoueursLabel);
		
		String[] nombreDeJoueurs = {"", "2", "3"};
		JComboBox comboBoxNombreDeJoueurs = new JComboBox(nombreDeJoueurs);
		
		/** Handling event** >> ControleurParametre */
		controleurParametre.controleurParametre(ControleurParametre.getInstallerJeu(), comboBoxNombreDeJoueurs);	//Controleur: render number of player
		
		comboBoxNombreDeJoueurs.setBounds(292, 6, 62, 27);
		nombreDeJoueursPanel.add(comboBoxNombreDeJoueurs);
		
		
		/** ******************* Activate virtual player option (Oui/Non) ******************* */
		JPanel activerJoueurVirPanel = new JPanel();
		activerJoueurVirPanel.setBounds(20, 187, 677, 38);
		frame.getContentPane().add(activerJoueurVirPanel);
		activerJoueurVirPanel.setLayout(null);
		
		JLabel activerJoueurVirLabel = new JLabel("Activer le joueur virtuel: ");
		activerJoueurVirLabel.setBounds(0, 6, 237, 30);
		activerJoueurVirLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		activerJoueurVirPanel.add(activerJoueurVirLabel);
		
		JRadioButton oui = new JRadioButton("Oui");
		oui.setBounds(246, 11, 57, 23);
		activerJoueurVirPanel.add(oui);
		
		JRadioButton non = new JRadioButton("Non");
		non.setBounds(324, 11, 69, 23);
		activerJoueurVirPanel.add(non);
		
		ButtonGroup group = new ButtonGroup();
		group.add(oui);
		group.add(non);
		//**Handling event** >> ControleurParametre
		controleurParametre.controleurParametre(ControleurParametre.getInstallerJeu(), oui);
		controleurParametre.controleurParametre(ControleurParametre.getInstallerJeu(), non);
		
		
		/** ******************* Choose niveau of virtual player (Facile/Difficile) ******************* */
		niveauPanel = new JPanel();
		niveauPanel.setBounds(57, 235, 637, 38);
		frame.getContentPane().add(niveauPanel);
		niveauPanel.setLayout(null);
		
		JLabel niveauLabel = new JLabel("Choisir niveau du joueur virtuel:");
		niveauLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		niveauLabel.setBounds(6, 6, 229, 26);
		niveauPanel.add(niveauLabel);
		
		JRadioButton facile = new JRadioButton("Facile");
		facile.setFont(new Font("SansSerif", Font.PLAIN, 13));
		facile.setBounds(247, 8, 77, 23);
		niveauPanel.add(facile);
		
		JRadioButton difficile = new JRadioButton("Difficile");
		difficile.setFont(new Font("SansSerif", Font.PLAIN, 13));
		difficile.setBounds(330, 8, 91, 23);
		niveauPanel.add(difficile);
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(facile);
		group1.add(difficile);
		//**Handling event** >>ControleurParametre
		controleurParametre.controleurParametre(controleurParametre.getInstallerJeu(), facile);
		controleurParametre.controleurParametre(controleurParametre.getInstallerJeu(), difficile);
		
		
		/** Activate the option "Play on the console" */
		activerLeJeuSurConsolePnl = new JPanel();
		activerLeJeuSurConsolePnl.setLayout(null);
		activerLeJeuSurConsolePnl.setBounds(20, 307, 677, 74);
		frame.getContentPane().add(activerLeJeuSurConsolePnl);
		
		JLabel activerLeJeuSurConsoleLbl = new JLabel("Activer le jeu sur la console:");
		activerLeJeuSurConsoleLbl.setFont(new Font("SansSerif", Font.BOLD, 18));
		activerLeJeuSurConsoleLbl.setBounds(0, 6, 275, 30);
		activerLeJeuSurConsolePnl.add(activerLeJeuSurConsoleLbl);
		
		JRadioButton oui_1 = new JRadioButton("Oui");
		oui_1.setBounds(285, 11, 57, 23);
		activerLeJeuSurConsolePnl.add(oui_1);
		
		JRadioButton non_1 = new JRadioButton("Non");
		non_1.setBounds(366, 11, 69, 23);
		activerLeJeuSurConsolePnl.add(non_1);
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(oui_1);
		group2.add(non_1);
		//**Handling event** >> ControleurParametre
		controleurParametre.controleurParametre(ControleurParametre.getInstallerJeu(), oui_1, "Oui");
		controleurParametre.controleurParametre(ControleurParametre.getInstallerJeu(), non_1, "Oui");
		
		JLabel lblNewLabel = new JLabel("(A utiliser uniquement lors du text du jeu)");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblNewLabel.setBounds(40, 36, 314, 16);
		activerLeJeuSurConsolePnl.add(lblNewLabel);
		
		
		/** ******************* Validate button ******************* */
		valider = new JButton("Valider");
		
		valider.setBounds(300, 418, 118, 34);
		frame.getContentPane().add(valider);
		
		/** **Handling event** >>ControleurParametre** */
		controleurParametre.controleurParametre(controleurParametre.getInstallerJeu(), valider);
	}
	
	/**
	 * Get level Panel
	 * @return JPanel
	 */
	public static JPanel getNiveauPanel() {
		return FenetreParametre.niveauPanel;
	}
	
	/**
	 * Get setting Frame
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return this.frame;
	}
}
