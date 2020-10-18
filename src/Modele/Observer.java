package Modele;

public interface Observer {
	public default void update(Object news) {
		System.out.println((String) news);
	}
}
