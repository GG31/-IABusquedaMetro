package Environment;

import java.util.ArrayList;

import javax.swing.event.EventListenerList;

import Algorithm.AlgoA;
import Controller.FindWayEvent;
import Controller.FindWayListener;

public class Model {
	private int[][] distance;
	private Station[] stations;
	private ArrayList<Line> lines;
	private ParserData parser;
	private AlgoA algo;

	// MVC
	private EventListenerList ecouteurs;

	public Model() {
		this.parser = new ParserData();
		this.distance = this.parser.getDistances();
		this.stations = this.parser.getStation();
		this.lines = new ArrayList<>();
		this.lines = this.parser.getLines();

		this.algo = new AlgoA();

		this.ecouteurs = new EventListenerList();
	}

	/* Si pinchas en ok */
	public void ok(String origin, String destination) {
		//Encontrar las estaciones que tienen estos nombres
		Station si = null, sf = null;
		algo.findWay(si, sf);
		this.stations = algo.getWay();
		fireWayChanged();//Draw way
	}

	/*
	 * public String toStringStation(){ String s = ""; return s; }
	 * 
	 * public String toStringLine(){ String s = ""; //for(int i=0;
	 * i<this.lines.size(); i++){ for(int j=0;
	 * j<this.lines.get(0).getStationsWay().size(); j++){ s +=
	 * this.lines.get(0).getStationsWay().get(j).getName(); } //} return s; }
	 * public String toStringDistance() { String s = ""; for (int i = 0; i <
	 * this.distance.length; i++) { for (int j = 0; j < this.distance.length;
	 * j++) { s += " " + this.distance[i][j]; } s += "\n"; } return s; }
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
			listener.wayChanged(new FindWayEvent(this, getStations()));
		}
	}
	
	public Station[] getStations(){
		return this.stations;
	}

}
