package environment;

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

	/**
	 * Lee el fichero, y rellena distances y stations
	 */
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
				/*
				 * case "*Lines": int j = 0; s = sc.nextLine(); this.lines = new
				 * ArrayList<>(); s = sc.nextLine(); do { parseLines(s, j); j++;
				 * s = sc.nextLine(); } while (!s.equals("*EndLines")); break;
				 */
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

	/**
	 * Rellena station con todas la estaciones encontradas en el fichero
	 * 
	 * @param s
	 */
	private void parseStations(String s) {
		String[] station = s.split(":");
		int n = station.length;
		this.distances = new int[n][n];
		this.stations = new Station[n];
		for (int i = 0; i < n; i++) {
			stations[i] = new Station(station[i], i);
		}
	}

	/**
	 * Rellena las distancias entre las estaciones y añade las estaciones
	 * vecinas a las estaciones encontrados en s
	 * 
	 * @param s
	 * @param stationTreat
	 */
	private void parseLinks(String s, int stationTreat) {
		String[] block = s.split(";");
		parseDistance(block[0], stationTreat);
		parseNeighbours(block[1], stationTreat);
	}

	/**
	 * Rellena distances[][] con s por la estación stationTreat
	 * 
	 * @param s
	 * @param stationTreat
	 */
	private void parseDistance(String s, int stationTreat) {
		String[] block = s.split(":");
		for (int i = 0; i < this.distances.length; i++) {
			this.distances[stationTreat][i] = Integer.parseInt(block[i]);
		}
	}

	/**
	 * Añade los vecinos a la estación stationTreat
	 * 
	 * @param s
	 * @param stationTreat
	 */
	private void parseNeighbours(String s, int stationTreat) {
		String[] block = s.split(":");
		for (int i = 0; i < block.length; i++) {
			this.stations[stationTreat]
					.addStationNeighbours(this.stations[Integer
							.parseInt(block[i])]);
		}
	}

	// Getters
	public Station[] getStation() {
		return this.stations;
	}

	public int[][] getDistances() {
		return this.distances;
	}
}
