package Controleur;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import Modele.InstallerJeu;
import Modele.Partie;
import Vue.FenetreParametre;
import Vue.FenetreTableDuJeu;
import Vue.VueText;

/**
 * @author Huu Khai NGUYEN (Alec) 
 * 
 * <br>
 * Description: This class allows to control the installation window
 */

public class ControleurParametre {
	
	private static InstallerJeu installerJeu;
	
	private static JFrame fenetreParametreFrame;
	
	/**
	 * Render variation/numberOfPlayer option
	 * @param installerJeu : InstallerJeu variable
	 * @param comboBox : component JComboBox
	 */
	public void controleurParametre(InstallerJeu installerJeu, JComboBox comboBox) {
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = (String) comboBox.getSelectedItem();
				
				try {
					InstallerJeu.setNombreDeJoueurs(Integer.parseInt(str));
					
				} catch (NumberFormatException err) {
					if (str.equals("classique")) InstallerJeu.setVarianteDuTapis("R");
					else if (str.equals("caree")) InstallerJeu.setVarianteDuTapis("C");
					else if (str.equals("pyramide")) InstallerJeu.setVarianteDuTapis("P");
				}
			}
		});
	}
	
	/**
	 * @Overload
	 * Render activerJoueurVir / Niveau option
	 * @param installerJeu : InstallerJeu varibale
	 * @param radioBtn : component JRadioButton
	 */
	public void controleurParametre(InstallerJeu installerJeu, JRadioButton radioBtn) {
		radioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbstractButton selectedValue = (AbstractButton) e.getSource();
				
				try {
					/** In case activerJoueurVirRadioBtn is selected */
					if (selectedValue.getText().equals("Oui")) {
						InstallerJeu.setActiverJoueurVir(true);
						InstallerJeu.activerJoueurVirValidation = 1;
						
						FenetreParametre.getNiveauPanel().setVisible(true);

					} else if ((selectedValue.getText().equals("Non"))) {
						InstallerJeu.setActiverJoueurVir(false);
						InstallerJeu.activerJoueurVirValidation = 1;
						
						FenetreParametre.getNiveauPanel().setVisible(false);
					}
						
					/** In case niveauRaidoBtn is selected */
					if (selectedValue.getText().equals("Facile")) {
						InstallerJeu.setNiveau("F");
						InstallerJeu.niveauValidation = 1;
					}
					else if (selectedValue.getText().equals("Difficile")) {
						InstallerJeu.setNiveau("D");
						InstallerJeu.niveauValidation = 1;
					}
	
				} catch (Exception err) {
					System.out.println("Some errors happened...");
				}
			}
		});
	}
	
	/**
	 * @Overload
	 * Render activerLeJeuSurConsole 
	 * @param installerJeu : InstallerJeu variale
	 * @param radioBtn : component JRadioButton
	 */
	public void controleurParametre(InstallerJeu installerJeu, JRadioButton radioBtn, String consoleOption) {
		radioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbstractButton selectedValue = (AbstractButton) e.getSource();
				
				if (consoleOption.equals("Oui")) {
					try {
						/** In case activerLeJeuSurConsole is selected */
						if (selectedValue.getText().equals("Oui")) {
							InstallerJeu.setConsoleOption(true);
							InstallerJeu.activerLeJeuSurConsoleValidation = 1;

						} else if ((selectedValue.getText().equals("Non"))) {
							InstallerJeu.setConsoleOption(false);
							InstallerJeu.activerLeJeuSurConsoleValidation = 1;
						}

					} catch (Exception err) {
						System.out.println("Some errors happened...");
					}
				}
			}
		});
	}
	
	/**
	 * @Overload
	 * Validate options from players -> Close parameted window -> Open SHAPE UP's GUI
	 * @param installerJeu : Installer variable
	 * @param button : component JButton
	 */
	public void controleurParametre(InstallerJeu installerJeu, JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean condition = (ControleurTableDuJeu.getInstallerJeu().getVarianteDuTapis() != "")
						&& (ControleurTableDuJeu.getInstallerJeu().getNombreDeJoueurs() == 2 || ControleurTableDuJeu.getInstallerJeu().getNombreDeJoueurs() == 3)
						&& (ControleurTableDuJeu.getInstallerJeu().activerJoueurVirValidation != 0);
				
				if (condition) {
					if ((ControleurTableDuJeu.getInstallerJeu().getActiverJoueurVir() == true && ControleurTableDuJeu.getInstallerJeu().niveauValidation != 0)
							|| ControleurTableDuJeu.getInstallerJeu().getActiverJoueurVir() == false) {
						ControleurParametre.fenetreParametreFrame.dispose();
						
						/** Thread for game in CMD. Thread's name: Thread-0 */
						Thread threadCMD = new Thread() {
							public void run() {
								System.out.println("Start " + Thread.currentThread().getName());
								new Partie();
							}
						};
						threadCMD.start();
						

						/**
						 * Thread for GUI
						 * Run this thread of "FenetreTableDuJeu" after 2 seconds to wait for "InstallerTour" is finish
						 * Thread's name: AWT-EventQueue-0
						 */
						try {
							Thread.sleep(1800);
						} catch (InterruptedException er) {
							System.out.println(er.toString());
						}
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {								
									FenetreTableDuJeu tableDuJeu = new FenetreTableDuJeu();
									ControleurTableDuJeu.setFenetreTableDuJeu(tableDuJeu);
		
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
					
						/** Thread of VueText */
						if (InstallerJeu.getConsoleOption()) {
							try {
								new VueText();
							} catch (NullPointerException err) {
								System.out.println(err.toString());
							}
						}
					}
				}
			}
		});
	}
	
	
	/**
	 * Set and get installerJeu variable from Class InstallerJeu in package.Modele when it is created
	 * @param installerJeu : Installer variable
	 */
	public static void setInstallerJeu(InstallerJeu installerJeu) {
		ControleurParametre.installerJeu = installerJeu;
	}
	
	/**
	 * Get InstallerJeu object
	 * @return InstallerJeu object
	 */
	public static InstallerJeu getInstallerJeu() {
		return ControleurParametre.installerJeu;
	}
	
	/**
	 * Set fenetreParametre's frame for handling event of JButton "valider"
	 * @param fenetreParametreFrame : JFrame-settings window frame
	 */
	public static void setFenetreParametre(JFrame fenetreParametreFrame) {
		ControleurParametre.fenetreParametreFrame = fenetreParametreFrame;
	}
	
	/**
	 * Get fenetreParametre's frame for handling event of JButton "valider"
	 * @return fenetreParametre object
	 */
	public static JFrame getFenetreParametre() {
		return ControleurParametre.fenetreParametreFrame;
	}
}