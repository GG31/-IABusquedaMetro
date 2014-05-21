package Controller;

import Environment.Model;
import Interface.Map;

public class Controller {
	public Map vue1 = null;
	private Model model = null;

	public Controller(Model model) {
		this.model = model;
		this.vue1 = new Map(this, model.getStations());
		addListenersToModel();
	}

	/**
	 * Añade un listener al modelo
	 */
	private void addListenersToModel() {
		this.model.addListener(vue1);
	}

	/**
	 * Si pinchamos en ok, este método está llamado para empezar a buscar el
	 * camino
	 * 
	 * @param origin
	 * @param destination
	 * @throws Exception
	 */
	public void notifyWayChanged(String origin, String destination)
			throws Exception {
		model.ok(origin, destination);
	}
}
