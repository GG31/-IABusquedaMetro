package Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParserData {
	private final String FILENAME = "resources/data.txt";
	private int[][] distances;
	private Station[] stations;

	public ParserData() {
		read();
	}

	private void read() {
		Scanner sc;
		try {
			sc = new Scanner(new File(FILENAME));
			while (sc.hasNextLine()) {
				String s = sc.nextLine();
				// char first = s.charAt(0);
				switch (s) {
				case "%":
					break;
				case "*Stations":
					s = sc.nextLine();
					parseStations(s);
					break;
				/*case "*Lines":
					int j = 0;
					s = sc.nextLine();
					this.lines = new ArrayList<>();
					s = sc.nextLine();
					do {
						parseLines(s, j);
						j++;
						s = sc.nextLine();
					} while (!s.equals("*EndLines"));
					break;*/
				case "*Links":
					int i = 0;
					while (sc.hasNextLine()) {
						s = sc.nextLine();
						if (s.charAt(0) == '%') {
							// if (sc.hasNextLine())
							// s = sc.nextLine();
						} else {
							parseLinks(s, i);
							i++;
						}
					}
					break;
				default:
					break;
				}

			}
			sc.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Ok */
	private void parseStations(String s) {
		String[] station = s.split(":");
		int n = station.length;
		this.distances = new int[n][n];
		this.stations = new Station[n];
		for (int i = 0; i < n; i++) {
			stations[i] = new Station(station[i], i);
		}
	}

	/* ??? */
	private void parseLines(String s, int numLine) {
		ArrayList<Station> stationLine = new ArrayList<>();
		String[] block = s.split(";");
		for (int i = 0; i < block.length; i++) {
			String[] sBlock = block[i].split("-");
			if (sBlock.length > 1) {
				// Añade todas las estaciones entre sBlock[0] y sBlock[1]
				for (int j = Integer.parseInt(sBlock[0]); j <= Integer
						.parseInt(sBlock[1]); j++) {
					stationLine.add(this.stations[j]);
					// System.out.println(this.stations[j].getName());
					// System.out.println(stationLine.get(stationLine.size()-1).getName());
				}
			} else {
				stationLine.add(this.stations[Integer.parseInt(sBlock[0])]);
			}

		}
	}

	/* Ok */
	private void parseLinks(String s, int stationTreat) {
		String[] block = s.split(";");
		parseDistance(block[0], stationTreat);
		parseNeighbours(block[1], stationTreat);
	}

	/* Ok */
	private void parseDistance(String s, int stationTreat) {
		String[] block = s.split(":");
		for (int i = 0; i < this.distances.length; i++) {
			this.distances[stationTreat][i] = Integer.parseInt(block[i]);
		}
	}

	/* Ok */
	private void parseNeighbours(String s, int stationTreat) {
		String[] block = s.split(":");
		for (int i = 0; i < block.length; i++) {
			this.stations[stationTreat]
					.addStationNeighbours(this.stations[Integer
							.parseInt(block[i])]);
		}
	}

	public Station[] getStation() {
		return this.stations;
	}

	public int[][] getDistances() {
		return this.distances;
	}
}
