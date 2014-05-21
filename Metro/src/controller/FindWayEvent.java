package Controller;

import java.util.ArrayList;
import java.util.EventObject;

import Environment.Station;

public class FindWayEvent extends EventObject{
	private ArrayList<Station> stations;
	
	public FindWayEvent(Object source, ArrayList<Station> station) {
		super(source);
		this.stations = station;
	}
	
	public ArrayList<Station> getStationsWay(){
		return this.stations;
	}
	
}
