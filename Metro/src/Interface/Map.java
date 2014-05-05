package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Controller.Controller;
import Controller.FindWayEvent;
import Controller.FindWayListener;
import Environment.Station;

public class Map implements ActionListener, FindWayListener {
	private Controller controleur = null;

	public Map(Controller controleur) {
		this.controleur = controleur;
		this.draw();
	}

	private void draw() {
		// Dibuja el mapa "vacía"
	}

	public void drawCamino(ArrayList<Station> stations) {
		// Hace el camino según las estaciones en parámetro
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// if (e.getSource() == buttonOk) { }
		try {
			// this.controleur.notifyWayChanged(labelDepart.getText().toString()),
			// labelDestination.getText().toString());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void wayChanged(FindWayEvent event) {
		clear(); // Clean the way existing to do other
		Station[] s = event.getStationsWay();

	}

	public void clear() {

	}
}
