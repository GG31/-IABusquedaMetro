package Environment;

import java.util.ArrayList;

public class Station {
	private int id;
	private String name;
	private ArrayList<Station> stationNeighbours;
	private int g;
	private int f;
	private ArrayList<Station> wayToHere;

	public Station(String name, int id) {
		this.id = id;
		this.stationNeighbours = new ArrayList<>();
		this.wayToHere = new ArrayList<>();
		this.name = name;
	}
	
	public void addStationNeighbours(Station neighbour){
		this.stationNeighbours.add(neighbour);
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Station> getNeighbours(){
		return this.stationNeighbours;
	}
}
