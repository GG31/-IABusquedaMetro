package Controller;

import java.util.EventObject;

import Environment.Station;

public class FindWayEvent extends EventObject{
	private Station[] stations;
	
	public FindWayEvent(Object source, Station[] station) {
		super(source);
		this.stations = station;
		// TODO Auto-generated constructor stub
	}
	
	public Station[] getStationsWay(){
		return this.stations;
	}
	
}
