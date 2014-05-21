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
		this.g = 0;
	}

	public void addStationNeighbours(Station neighbour) {
		this.stationNeighbours.add(neighbour);
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}

	public ArrayList<Station> getNeighbours() {
		return this.stationNeighbours;
	}

	public int getG() {
		return this.g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public void calculF(int h) {
		this.f = this.g + h;
	}

	public int getF() {
		return this.f;
	}

	public void setF(int f) {
		this.f = f;
	}
	
	public void addWayToHere(ArrayList<Station>s){
		this.wayToHere.clear();
		this.wayToHere.addAll(s);
	}
	
	public void addWayToHere(Station s){
		this.wayToHere.add(s);
	}
	
	public ArrayList<Station> getWayToHere(){
		return this.wayToHere;
	}
	
	public void clear(){
		this.g = 0;
		this.f = 0;
		this.wayToHere.clear();
	}
}
