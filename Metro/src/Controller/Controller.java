package Controller;

import Environment.Model;
import Interface.Map;

public class Controller {
	public Map vue1 = null;
	private Model model = null;

	public Controller(Model model) {
		this.model = model;
		this.vue1 = new Map(this);
		addListenersToModel();
	}

	private void addListenersToModel() {
		this.model.addListener(vue1);
	}

	/*public void displayViews() {
		vue1.display();
	}

	public void closeViews() {
		vue1.close();
	}*/

	public void notifyWayChanged(String origin, String destination)
			throws Exception {
		model.ok(origin, destination);
	}
}
