package Modele;

import java.util.ArrayList;

public class Observable {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private String news;
	
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	public void notifyObserver(String news) {
		this.news = news;
		
		for (Observer observer: observers) {
			observer.update(news);
		}
	}
}
