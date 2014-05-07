package Algorithm;

import java.util.ArrayList;

import Environment.Station;

public class AlgoA {
	private Station si, sf;
	private Station[] stations;
	private int[][] distances;
	private ArrayList<Station> openedList;
	private ArrayList<Station> closedList;

	private ArrayList<Station> way;

	public AlgoA(int[][] distances, Station[] stations) {
		this.distances = distances;
		this.stations = stations;
		this.openedList = new ArrayList<>();
		this.closedList = new ArrayList<>();
		this.way = new ArrayList<>();
		this.si = null;
		this.sf = null;
	}

	/* Devuelve el camino más corto entre si y sf */
	public ArrayList<Station> findWay(Station si, Station sf) {
		this.si = si;
		this.sf = sf;
		this.openedList.add(si);
		find();
		return null;
	}

	/*
	 * Encuentra recursivamente el mejor camino con el algo A*, almacena en los
	 * atributos, almacena en wayToHere de cada nodo el camino más corto para ir
	 * al nodo desde si
	 */
	private void find() {
		// Poner el primer nodo de la lista abierta en la lista cerrada
		this.closedList.add(this.openedList.get(0));
		this.openedList.remove(0);

		// Calcular f por cada hijo
		Station padre = this.closedList.get(this.closedList.size() - 1);
		for (int i = 0; i < padre.getNeighbours().size(); i++) {
			int g = this.distances[padre.getId()][padre.getNeighbours().get(i)
					.getId()];
			padre.getNeighbours().get(i).setG(g + padre.getG());

			int h = this.distances[padre.getNeighbours().get(i).getId()][this.sf
					.getId()];
			padre.getNeighbours().get(i).calculF(h);
		}

		// Almacenar los vecinos
		almacenar(padre.getNeighbours());

		// Reordenar la lista abierta por orden de f
		ArrayList<Station> stationClone = (ArrayList<Station>) this.openedList
				.clone();
		this.openedList.clear();
		reordenar(stationClone);
		
		
	}

	public void almacenar(ArrayList<Station> s) {
		for (int i = 0; i < s.size(); i++) {
			if (!this.openedList.contains(s.get(i))) {
				this.openedList.add(s.get(i));
			}
		}
	}

	public void reordenar(ArrayList<Station> stationClone) {
		if (stationClone.size() > 0) {
			for (int i = 0; i < stationClone.size(); i++) {
				int j = findMin(stationClone);
				this.openedList.add(stationClone.get(j));
				stationClone.remove(j);
				reordenar(stationClone);
			}
		}
	}
	
	public int findMin(ArrayList<Station>s){
		int j=0;
		int max = s.get(0).getF();
		for(int i=1; i<s.size(); i++){
			if(max<s.get(i).getF()){
				max = s.get(i).getF();
				j = i;
			}
		}
		return j;
	}
}
