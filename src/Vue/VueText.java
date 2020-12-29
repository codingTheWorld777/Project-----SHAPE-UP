package Vue;

import java.util.InputMismatchException;
import java.util.Scanner;

import Modele.Observer;
import Modele.Partie;

public class VueText extends Thread implements Observer {
	
	public VueText(Partie partie) {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Enter a value of type 'int'
	 * @return
	 */
	public int saisirInt() {
		 Scanner src = new Scanner(System.in);
		 int num = 0;
		 
		 try {
			 num = src.nextInt();
			 
		 } catch (InputMismatchException e) {
			 System.out.println(e.toString());
			 System.out.println("Erreur! Resaisir...");
		 }
		 
		 return num;
	}
	
	/**
	 * Enter a value of type 'boolean'
	 * @return
	 */
	public boolean saisirBoolean() {
		Scanner src = new Scanner(System.in);
		boolean choix = false;
		
		try {
			 choix = src.nextBoolean();
			 
		} catch (InputMismatchException e) {
			 System.out.println(e.toString());
			 System.out.println("Erreur! Resaisir encore une fois...");
		}
		
		return choix;
	}
	

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}
}
