package Vue;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Controleur.ControleurTableDuJeu;


public class FenetreTableDuJeu implements MouseListener {

	private JFrame frame;
	
	private static JPanel zoneDeCartePanel;
	private static JPanel joueur1Panel, joueur2Panel,joueur3Panel;
	private static JPanel piochesCartesPanel;
	private static JButton carteCacheeBtn;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FenetreTableDuJeu tableDuJeu = new FenetreTableDuJeu();
//				
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public FenetreTableDuJeu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("SHAPE UP!");
		
		//Set background for desk
		try {
			JLabel background = new JLabel(new ImageIcon(this.getClass().getResource("../images/background_of_desk.png")));
			frame.setContentPane(background);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		ControleurTableDuJeu controleurJeu = new ControleurTableDuJeu();
		
		frame.setSize(1200, 740);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		//Create a table that contains card of game
		zoneDeCartePanel = new JPanel();
		zoneDeCartePanel.setBounds(290, 110, 620, 520);
		zoneDeCartePanel.setLayout(new GridLayout(5, 7, 2, 4));
		frame.getContentPane().add(zoneDeCartePanel);
		
		GridBagConstraints gridConstraints = new GridBagConstraints();
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 7; x++) {
				JButton carteBtn = new JButton();
				
//				try {
//					BufferedImage image = ImageIO.read(this.getClass().getResource("../images/dos.png"));
//					ImageIcon icon = new ImageIcon(
//							image.getScaledInstance(carteBtn.getWidth(), carteBtn.getHeight(), image.SCALE_SMOOTH));
//					carteBtn.setIcon(icon);
//					
//				} catch(IOException e) {
//					e.printStackTrace();
//				}
				
//				ImageIcon carteImage = new ImageIcon(new ImageIcon(
//						getClass().getResource("../images/dos.png")).getImage().getScaledInstance(
//								carteBtn.getWidth(), carteBtn.getHeight(), Image.SCALE_SMOOTH));
//				ImageIcon carteImage = new ImageIcon(getClass().getResource("../images/dos.png"));
//				carteBtn.setIcon(carteImage);
				gridConstraints.gridx = x;
				gridConstraints.gridy = y;
				zoneDeCartePanel.add(carteBtn, gridConstraints);
			}
		}
		
		
		// ********Zone of player ********
		//******** Player 1 ********
		joueur1Panel = new JPanel();
		joueur1Panel.setBackground(UIManager.getColor("Button.select"));
		joueur1Panel.setBounds(50, 38, 164, 229);
		joueur1Panel.setLayout(null);
		frame.getContentPane().add(joueur1Panel);
		
		JLabel joueur1Label = new JLabel("Joueur 1");
		joueur1Label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		joueur1Label.setBounds(52, 6, 63, 21);
		joueur1Panel.add(joueur1Label);
		
		JLabel point1 = new JLabel("Point: 0");
		point1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		point1.setBounds(12, 39, 113, 21);
		joueur1Panel.add(point1);
		
		JButton carteVictoire1 = new JButton();
		carteVictoire1.setBounds(39, 72, 86, 96);
		joueur1Panel.add(carteVictoire1);
		
		JButton finirMonTour1 = new JButton("Finir mon tour");
		finirMonTour1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		finirMonTour1.setBounds(21, 180, 120, 34);
		joueur1Panel.add(finirMonTour1);
		
		//******** Zone of hidden card ********
		carteCacheeBtn = new JButton();
		carteCacheeBtn.setBounds(89, 312, 86, 96);
		frame.getContentPane().add(carteCacheeBtn);
		
		//******** Player 2 ********
		joueur2Panel = new JPanel();
		joueur2Panel.setBackground(UIManager.getColor("Button.select"));
		joueur2Panel.setBounds(50, 451, 164, 229);
		joueur2Panel.setLayout(null);
		frame.getContentPane().add(joueur2Panel);
		
		JLabel joueur2Label = new JLabel();
		joueur2Label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		joueur2Label.setBounds(52, 6, 63, 21);
		try {
			joueur2Label.setText(controleurJeu.getNomDeJoueur(1));
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}
		joueur2Panel.add(joueur2Label);
		
		JLabel point2 = new JLabel("Point: 0");
		point2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		point2.setBounds(12, 39, 113, 21);
		joueur2Panel.add(point2);
		
		JButton carteVictoire2 = new JButton();
		carteVictoire2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		carteVictoire2.setBounds(39, 72, 86, 96);
		joueur2Panel.add(carteVictoire2);
		
		JButton finirMonTour2 = new JButton("Finir mon tour");
		finirMonTour2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		finirMonTour2.setBounds(21, 180, 120, 34);
		joueur2Panel.add(finirMonTour2);

		
		//******** Zone of card draw ********
		piochesCartesPanel = new JPanel();
		piochesCartesPanel.setBackground(UIManager.getColor("Button.select"));
		piochesCartesPanel.setBounds(986, 370, 164, 300);
		piochesCartesPanel.setLayout(null);
		frame.getContentPane().add(piochesCartesPanel);
		
		JButton carteJouee = new JButton();
		carteJouee.setBounds(39, 27, 86, 96);
		piochesCartesPanel.add(carteJouee);
		
		JLabel piocheLabel = new JLabel("Piocher une carte");
		piocheLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		piocheLabel.setBounds(21, 144, 127, 17);
		piochesCartesPanel.add(piocheLabel);
		
		
		//******** Player 3 ********
		if (ControleurTableDuJeu.paintJoueur3(controleurJeu.getInstallerJeu(), this) == true) {
			joueur3Panel = new JPanel();
			joueur3Panel.setBackground(UIManager.getColor("Button.select"));
			joueur3Panel.setBounds(986, 38, 164, 229);
			joueur3Panel.setLayout(null);
			frame.getContentPane().add(joueur3Panel);
			
			try {
				JLabel joueur3Label = new JLabel(controleurJeu.getNomDeJoueur(2));
				joueur3Label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
				joueur3Label.setBounds(52, 6, 63, 21);
				joueur3Panel.add(joueur3Label);
				
			} catch (NullPointerException e) {
				System.out.println(e.toString());
			}
			
			JLabel point3 = new JLabel("Point: 0");
			point3.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			point3.setBounds(12, 39, 113, 21);
			joueur3Panel.add(point3);
			
			JButton carteVictoire3 = new JButton();
			carteVictoire3.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			carteVictoire3.setBounds(39, 72, 86, 96);
			joueur3Panel.add(carteVictoire3);
			
			JButton finirMonTour3 = new JButton("Finir mon tour");
			finirMonTour3.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			finirMonTour3.setBounds(21, 180, 120, 34);
			joueur3Panel.add(finirMonTour3);	
			
		}
		
	}
	
	public void addControleur(ControleurTableDuJeu tableDuJeu) {
		
	}
	
	public JFrame getFrame() {
		return this.frame;
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
		this.frame.getContentPane().repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
