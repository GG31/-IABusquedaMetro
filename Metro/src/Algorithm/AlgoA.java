package Algorithm;

import java.util.ArrayList;

import Environment.Station;

public class AlgoA {
	private Station[] way;
	
	public AlgoA() {

	}

	/* Devuelve el camino más corto entre si y sf */
	public ArrayList<Station> findWay(Station si, Station sf) {
		find();
		return null;
	}

	/*
	 * Encuentra recursivamente el mejor camino con el algo A*, almacena en los
	 * atributos, almacena en wayToHere de cada nodo el camino más corto para ir
	 * al nodo desde si
	 */
	private void find() {

	}
	
	public Station[] getWay(){
		return this.way;
	}
	
	
}
