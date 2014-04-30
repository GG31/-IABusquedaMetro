package Environment;

import java.util.ArrayList;

import Algorithm.AlgoA;
import Interface.Map;

public class Environment {
	private int[][] distance;
	private Station[] stations;
	private ArrayList<Line> lines;
	private ParserData parser;
	private Map map;
	private AlgoA algo;

	public Environment() {
		this.parser = new ParserData();
		this.distance = this.parser.getDistances();
		this.stations = this.parser.getStation();
		this.lines = new ArrayList<>();
		this.lines = this.parser.getLines();
		
		this.algo = new AlgoA();
		this.map = new Map();
	}
	
	/*Si pinchas en ok*/
	public void ok(){
		Station si = null,sf = null;
		map.drawCamino(algo.findWay(si, sf));
	}

	/*public String toStringStation(){
		String s = "";
		return s;
	}
	
	public String toStringLine(){
		String s = "";
		//for(int i=0; i<this.lines.size(); i++){
			for(int j=0; j<this.lines.get(0).getStationsWay().size(); j++){
				s += this.lines.get(0).getStationsWay().get(j).getName();
			}
		//}
		return s;
	}
	public String toStringDistance() {
		String s = "";
		for (int i = 0; i < this.distance.length; i++) {
			for (int j = 0; j < this.distance.length; j++) {
				s += " " + this.distance[i][j];
			}
			s += "\n";
		}
		return s;
	}*/
	

}
