package Environment;

import java.util.ArrayList;

import Environment.Station;

public class AlgoA {
	private Station sf;
	private int[][] distances;
	private ArrayList<Station> openedList;
	private ArrayList<Station> closedList;

	private ArrayList<Station> way;

	public AlgoA(int[][] distances) {
		this.distances = distances;
		
		this.openedList = new ArrayList<>();
		this.closedList = new ArrayList<>();
		this.way = new ArrayList<>();
		
		this.sf = null;
	}

	/* Devuelve el camino más corto entre si y sf */
	public ArrayList<Station> findWay(Station si, Station sf) {
		this.sf = sf;

		// Limpiar
		this.openedList.clear();
		this.closedList.clear();
		this.way.clear();
		
		this.openedList.add(si);
		find(si);
		// Si el origin es igual al destino
		if (si.equals(sf)) {
			ArrayList<Station> a = new ArrayList<>();
			a.add(sf);
			return a;
		}
		System.out.println("Longueur this.way " + this.way.size());
		return this.way;
	}

	/**
	 * Encuentra recursivamente el mejor camino con el algo A*, almacena en los
	 * atributos, almacena en wayToHere de cada nodo el camino más corto para ir
	 * al nodo desde si
	 * 
	 * @param padre
	 */

	private void find(Station padre) {
		// Para cada hijo del padre
		for (int i = 0; i < padre.getNeighbours().size(); i++) {
			// Encuentra distancia entre padre e hijo
			int gPH = this.distances[padre.getId()][padre.getNeighbours()
					.get(i).getId()];
			// Calcula g, la distancia hasta el padre + la distance padre-hijo
			int g = gPH + padre.getG();
			// Calcula f, g+ la distancia hasta el destino sf
			int f = g
					+ this.distances[padre.getNeighbours().get(i).getId()][this.sf
							.getId()];

			// Si f calculado es menor que otro f calculado para este hijo i
			if ((f < padre.getNeighbours().get(i).getF() && padre
					.getNeighbours().get(i).getF() != 0)
					|| padre.getNeighbours().get(i).getF() == 0) {
				// Actualiza g y f
				padre.getNeighbours().get(i).setG(g);
				padre.getNeighbours().get(i).setF(f);
				// Actualiza el camino
				padre.getNeighbours().get(i).addWayToHere(padre.getWayToHere());
				padre.getNeighbours().get(i).addWayToHere(padre);
				// Si el hijo hace parte de la lista cerrada

				/*if (this.closedList.contains(padre.getNeighbours().get(i))
						&& this.openedList.contains(padre.getNeighbours()
								.get(i).equals(padre))) {
					// Faire hériter du nouveau chemin d'accés à tous les fils
					find(padre.getNeighbours().get(i));
				}*/

			}
		}

		// Almacenar los vecinos en la lista abierta
		almacenar(padre.getNeighbours());

		// Reordenar la lista abierta por orden de f
		ArrayList<Station> stationClone = (ArrayList<Station>) this.openedList
				.clone();
		this.openedList.clear();
		reordenar(stationClone);

		// Poner el primer nodo de la lista abierta en la lista cerrada
		this.closedList.add(this.openedList.get(0));
		this.openedList.remove(0);

		// Calcular f por cada hijo
		Station padre1 = this.closedList.get(this.closedList.size() - 1);
		// Si el último nodo puesto en la lista cerrada no es el nodo final
		if (!padre1.equals(sf)) {
			// Llamar de nuevo find
			find(padre1);
		} else {// Si el último nodo de la lista cerrada es el destino
			this.way.addAll(padre1.getWayToHere());
			this.way.add(sf);
		}
	}

	/**
	 * Pone en la lista abierta todos las estaciones de s que no ya están en la
	 * lista abierta ni en la lista cerrada
	 * 
	 * @param s
	 */
	public void almacenar(ArrayList<Station> s) {
		for (int i = 0; i < s.size(); i++) {
			if (!this.openedList.contains(s.get(i))
					&& !this.closedList.contains(s.get(i))) {
				this.openedList.add(s.get(i));
			}
		}
	}

	/**
	 * Reordena la lista de estación por mínimo de f, almacena la nueva lista en
	 * la lista abierta : openedList
	 * 
	 * @param stationClone
	 */
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

	/**
	 * Devuelve el id de la estación que tiene el f mínimo en toda la lista
	 * 
	 * @param s
	 * @return
	 */
	public int findMin(ArrayList<Station> s) {
		int j = 0;
		int min = s.get(0).getF();
		for (int i = 1; i < s.size(); i++) {
			if (min > s.get(i).getF()) {
				min = s.get(i).getF();
				j = i;
			}
		}
		return j;
	}

	// Getters and Setters
	public ArrayList<Station> getOpenedList() {
		return this.openedList;
	}

	public ArrayList<Station> getClosedList() {
		return this.closedList;
	}

	public void setClosedList(Station s) {
		this.closedList.add(s);
	}
}
