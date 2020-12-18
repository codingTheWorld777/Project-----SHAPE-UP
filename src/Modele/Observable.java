package Modele;

import java.util.ArrayList;

public class Observable {
	public final static int MAX_OBSERVERS = 10;
	
	private ArrayList<Observer> observers;
	private int numberOfObservers;
	private boolean hasChanged;
	
	public Observable() {
		observers = new ArrayList<Observer>();
		numberOfObservers = 0;
		hasChanged = false;
	}
	
	public void addObserver(Observer obsv) {
		observers.add(obsv);
	}
	
	public void deleteObserver(Observer obsv) {
		observers.remove(obsv);
	}
	
	public void notifyObservers(Object o) {
		if (hasChanged == true) {
			for (int i = 0; i < observers.size(); i++) {
				observers.get(i).update(o);
			}
			
			hasChanged = false;
		}
	}
	
	public void setChanged() {
		hasChanged = true;
	}
	
	public void clearChanged() {
		hasChanged = false;
	}
}
