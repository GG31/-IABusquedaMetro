package environment;

import java.util.ArrayList;

import javax.swing.event.EventListenerList;

import controller.FindWayEvent;
import controller.FindWayListener;

public class Model {
	private int[][] distance;
	private Station[] stations;
	private ParserData parser;
	private AlgoA algo;
	private ArrayList<Station> way;

	// MVC
	private EventListenerList ecouteurs;

	public Model() {
		this.parser = new ParserData();
		this.distance = this.parser.getDistances();
		this.stations = this.parser.getStation();
		this.way = new ArrayList<>();

		this.algo = new AlgoA(this.distance);

		this.ecouteurs = new EventListenerList();
	}

	/**
	 * Busca los objetos estaciones correspondientes al origen y destino, y
	 * llama al algoritmo para encontrar el camino
	 * 
	 * @param origin
	 * @param destination
	 */
	public void ok(String origin, String destination) {
		// Encontrar las estaciones que tienen estos nombres
		clearAllStation();
		Station si = null, sf = null;
		for (int i = 0; i < stations.length; i++) {
			if (stations[i].getName().equals(origin)) {
				si = stations[i];
			}
			if (stations[i].getName().equals(destination)) {
				sf = stations[i];
			}
			if (sf != null && si != null)
				break;
		}
		if (sf == null || si == null) {
			System.out.println("Una de las dos estaciones no existen");
			System.out.println("si " + si + " sf " + sf);
		}
		this.way = algo.findWay(si, sf);
		fireWayChanged();// Draw way
	}

	/**
	 * Limpia todas las estaciones de los posibles g, h, f y caminos registrados
	 */
	private void clearAllStation() {
		for (int i = 0; i < this.stations.length; i++) {
			this.stations[i].clear();
		}
	}

	/**
	 * Añade listener
	 * 
	 * @param ecouteur
	 */
	public void addListener(FindWayListener ecouteur) {
		ecouteurs.add(FindWayListener.class, ecouteur);
	}

	public void removeListener(FindWayListener l) {
		ecouteurs.remove(FindWayListener.class, l);
	}

	public void fireWayChanged() {
		FindWayListener[] listeEcouteurs = (FindWayListener[]) ecouteurs
				.getListeners(FindWayListener.class);
		for (FindWayListener listener : listeEcouteurs) {
			listener.wayChanged(new FindWayEvent(this, getWay()));
		}
	}

	// Getters
	public ArrayList<Station> getWay() {
		return this.way;
	}

	public Station[] getStations() {
		return this.stations;
	}

}
