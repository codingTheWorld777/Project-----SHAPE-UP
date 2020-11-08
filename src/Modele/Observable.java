package Modele;

import java.util.ArrayList;

public class Observable {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	protected String news;
	
	public void addObserver(Observer obsv) {
		observers.add(obsv);
	}
	
	public void removeObserver(Observer obsv) {
		observers.remove(obsv);
	}
	
	public void notifyObserver(String news) {
		this.news = news;
		
		for (Observer observer: observers) {
			observer.update(news);
		}
	}
}
