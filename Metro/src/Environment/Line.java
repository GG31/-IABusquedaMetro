package Environment;

import java.util.ArrayList;

public class Line {
	private int id;
	private ArrayList<Station> stationsWay;

	public Line(int id, ArrayList<Station>stationsWay ) {
		this.id = id;
		this.stationsWay = new ArrayList<>();
		this.stationsWay.addAll(stationsWay);
		//System.out.println("FUCK");
		//System.out.println(stationsWay);
		//System.out.println(this.stationsWay);
		
	}
	
	public ArrayList<Station> getStationsWay(){
		return this.getStationsWay();
	}
}
