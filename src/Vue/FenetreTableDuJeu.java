package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Controleur.ControleurTableDuJeu;
import Modele.Coordonnees;
import Modele.Partie;

import java.util.Observer;
import java.util.Observable;


public class FenetreTableDuJeu extends JFrame implements MouseListener, MouseMotionListener, Observer {
	/**
	 * @author Huu Khai NGUYEN (Alec)
	 */
	
	private static JPanel zoneDeCartePanel;
	private static JPanel joueur1Panel, joueur2Panel,joueur3Panel;
	private static JPanel piochesCartesPanel;
	private static ButtonCard carteCacheeBtn;
	public static ButtonCard carteJouee;
	

	/**
	 * Create the application.
	 */
	public FenetreTableDuJeu() {
		initialize();
	}
	
	public void update(Observable instanceObservable, Object arg1) {
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ControleurTableDuJeu controleurJeu = new ControleurTableDuJeu();
		
		
		//Set background for game's board
		try {
			JLabel background = new JLabel(new ImageIcon(this.getClass().getResource("../images/background_of_desk.png")));
			this.setContentPane(background);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		this.setTitle("SHAPE UP!");
		this.setSize(1200, 740);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setVisible(true);
		
		//Create a table that contains card of game
		zoneDeCartePanel = new JPanel();
		zoneDeCartePanel.setBounds(290, 110, 620, 520);
		zoneDeCartePanel.setLayout(new GridLayout(5, 7, 7, 4));
		this.getContentPane().add(zoneDeCartePanel);
		
		ButtonCard[][] cartesBtn = new ButtonCard[5][7];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				ButtonCard carteBtn = new ButtonCard(j, i);
				cartesBtn[i][j] = carteBtn;
				for (int k = 0; k<Partie.joueursEnJeu.length; k++) {
					controleurJeu.ControleurTableDuJeu(Partie.joueursEnJeu[k], carteBtn);
				}
			}
		}
		

		
		GridBagConstraints gridConstraints = new GridBagConstraints();
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 7; x++) {
				gridConstraints.gridx = x;
				gridConstraints.gridy = y;
				Coordonnees coordonnees = new Coordonnees(x, y);
				cartesBtn[y][x].setCoordonnees(coordonnees);
				zoneDeCartePanel.add(cartesBtn[y][x], gridConstraints);
			}
		}
		
		
		// ********Zone of player ********
		//******** Player 1 ********
		joueur1Panel = new JPanel();
		joueur1Panel.setBackground(UIManager.getColor("Button.select"));
		joueur1Panel.setBounds(50, 38, 164, 229);
		joueur1Panel.setLayout(null);
		this.getContentPane().add(joueur1Panel);
		
		JLabel joueur1Label = new JLabel("Joueur 1");
		joueur1Label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		joueur1Label.setBounds(52, 6, 63, 21);
		joueur1Panel.add(joueur1Label);
		
		JLabel point1 = new JLabel("Point: 0");
		point1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		point1.setBounds(12, 39, 113, 21);
		joueur1Panel.add(point1);
		
		//Set image for victory card (faceDown-verso)
		ButtonCard carteVictoire1 = new ButtonCard(controleurJeu.getJoueur(0).getCarteVictoire());;
		carteVictoire1.setBounds(41, 72, 81, 100);
		try {
			Image imgVerso = controleurJeu.getJoueur(0).getCarteVictoire().getCarteImageVerso();
			imgVerso = imgVerso.getScaledInstance(carteVictoire1.getWidth(), carteVictoire1.getHeight(), Image.SCALE_DEFAULT);
			carteVictoire1.setIcon(new ImageIcon(imgVerso));
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}
		joueur1Panel.add(carteVictoire1);
		
		
		JButton finirMonTour1 = new JButton("Finir mon tour");
		finirMonTour1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		finirMonTour1.setBounds(21, 180, 120, 34);
		joueur1Panel.add(finirMonTour1);
		
		//******** Zone of hidden card ********
		carteCacheeBtn = new ButtonCard(controleurJeu.getCarteCachee());
		carteCacheeBtn.setBounds(89, 312, 81, 100);
		 
		try {
			Image imgVerso = controleurJeu.getCarteCachee().getCarteImageVerso();
			imgVerso = imgVerso.getScaledInstance(carteCacheeBtn.getWidth(), carteCacheeBtn.getHeight(), Image.SCALE_DEFAULT);
			carteCacheeBtn.setIcon(new ImageIcon(imgVerso));
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}
		this.getContentPane().add(carteCacheeBtn);
		
		
		//******** Player 2 ********
		joueur2Panel = new JPanel();
		joueur2Panel.setBackground(UIManager.getColor("Button.select"));
		joueur2Panel.setBounds(50, 451, 164, 229);
		joueur2Panel.setLayout(null);
		this.getContentPane().add(joueur2Panel);
		
		JLabel joueur2Label = new JLabel();
		joueur2Label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		try {
			if (controleurJeu.getJoueur(1).getNom().equals("Joueur Virtuel")) joueur2Label.setBounds(37, 6, 104, 21);
			else joueur2Label.setBounds(52, 6, 63, 21);
			
			joueur2Label.setText(controleurJeu.getJoueur(1).getNom());
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}
		joueur2Panel.add(joueur2Label);
		
		JLabel point2 = new JLabel("Point: 0");
		point2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		point2.setBounds(12, 39, 113, 21);
		joueur2Panel.add(point2);
		
		//Set image for victory card (faceDown-verso)
		ButtonCard carteVictoire2 = new ButtonCard(controleurJeu.getJoueur(1).getCarteVictoire());
		carteVictoire2.setBounds(41, 72, 81, 100);
		joueur2Panel.add(carteVictoire2);
		try {
			Image imgVerso = controleurJeu.getJoueur(1).getCarteVictoire().getCarteImageVerso();
			imgVerso = imgVerso.getScaledInstance(carteVictoire2.getWidth(), carteVictoire2.getHeight(), Image.SCALE_DEFAULT);
			carteVictoire2.setIcon(new ImageIcon(imgVerso));
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}
		
		
		JButton finirMonTour2 = new JButton("Finir mon tour");
		finirMonTour2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		finirMonTour2.setBounds(21, 180, 120, 34);
		joueur2Panel.add(finirMonTour2);
		
		
		//******** Player 3 ********
		if (ControleurTableDuJeu.paintJoueur3(controleurJeu.getInstallerJeu(), this) == true) {
			joueur3Panel = new JPanel();
			joueur3Panel.setBackground(UIManager.getColor("Button.select"));
			joueur3Panel.setBounds(986, 38, 164, 229);
			joueur3Panel.setLayout(null);
			this.getContentPane().add(joueur3Panel);
			
			try {
				JLabel joueur3Label = new JLabel(controleurJeu.getJoueur(2).getNom());
				joueur3Label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
				
				if (controleurJeu.getJoueur(2).getNom().equals("Joueur Virtuel")) joueur3Label.setBounds(37, 6, 104, 21);
				else joueur3Label.setBounds(52, 6, 63, 21);
				
				joueur3Panel.add(joueur3Label);
				
			} catch (NullPointerException e) {
				System.out.println(e.toString());
			}
			
			JLabel point3 = new JLabel("Point: 0");
			point3.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			point3.setBounds(12, 39, 113, 21);
			joueur3Panel.add(point3);
			
			//Set image for victory card (faceDown-verso)
			ButtonCard carteVictoire3 = new ButtonCard(controleurJeu.getJoueur(2).getCarteVictoire());
			carteVictoire3.setBounds(41, 72, 81, 100);
			joueur3Panel.add(carteVictoire3);
			try {
				Image imgVerso = controleurJeu.getJoueur(2).getCarteVictoire().getCarteImageVerso();
				imgVerso = imgVerso.getScaledInstance(carteVictoire3.getWidth(), carteVictoire3.getHeight(), Image.SCALE_DEFAULT);
				carteVictoire3.setIcon(new ImageIcon(imgVerso));
			} catch (NullPointerException e) {
				System.out.println(e.toString());
			}
			
			
			JButton finirMonTour3 = new JButton("Finir mon tour");
			finirMonTour3.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			finirMonTour3.setBounds(21, 180, 120, 34);
			joueur3Panel.add(finirMonTour3);	
			
		}
		
		//******** Zone of card draw ********
		piochesCartesPanel = new JPanel();
		piochesCartesPanel.setBackground(UIManager.getColor("Button.select"));
		piochesCartesPanel.setBounds(986, 370, 164, 300);
		piochesCartesPanel.setLayout(null);
		this.getContentPane().add(piochesCartesPanel);
		
		JButton piocheCarte = new JButton();
		piocheCarte.setBounds(41, 180, 81, 100);
		
		try {
			Image imgVerso = controleurJeu.getCartePiochee().getCarteImageVerso();
			imgVerso = imgVerso.getScaledInstance(piocheCarte.getWidth(), piocheCarte.getHeight(), Image.SCALE_DEFAULT);
			piocheCarte.setIcon(new ImageIcon(imgVerso));
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}
		
		
		carteJouee = new ButtonCard(piocheCarte);
		carteJouee.setBounds(41, 27, 81, 100);
		piochesCartesPanel.add(carteJouee);
		
		
		
		JLabel piocheLabel = new JLabel("Piocher une carte");
		piocheLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		piocheLabel.setBounds(21, 144, 127, 17);
		
		piochesCartesPanel.add(piocheLabel);
		piochesCartesPanel.add(piocheCarte);
		
		this.validate();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point pos = this.getLocationOnScreen();
		System.out.println(pos.x + ", " + pos.y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}