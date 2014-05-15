package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Environment.AlgoA;
import Environment.Model;
import Environment.ParserData;
import Environment.Station;

public class AlgoTest {
	AlgoA algo;
	ParserData parser;

	@Before
	public void setUp() {
		parser = new ParserData();
		algo = new AlgoA(parser.getDistances());
	}

	public void tearDown() {
		parser = null;
		algo = null;
	}

	@Test
	public void testFindMin() {
		ArrayList<Station> s = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			s.add(new Station(i + "", i));
			s.get(i).setF(i);
		}
		assertTrue(algo.findMin(s) == 0);
	}

	@Test
	public void testFindMin1() {
		ArrayList<Station> s = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			s.add(new Station(i + "", i));
			if (i == 5) {
				s.get(i).setF(2);
			} else
				s.get(i).setF(i + 10);
		}
		assertTrue(algo.findMin(s) == 5);
	}

	@Test
	public void testReordenar() {
		ArrayList<Station> s = new ArrayList<>();
		s.add(new Station("", 0));
		s.get(0).setF(29);
		s.add(new Station("", 1));
		s.get(1).setF(26);
		s.add(new Station("", 2));
		s.get(2).setF(12);
		s.add(new Station("", 3));
		s.get(3).setF(10);
		s.add(new Station("", 4));
		s.get(4).setF(15);
		algo.reordenar(s);
		assertTrue(algo.getOpenedList().get(0).getId() == 3);
		assertTrue(algo.getOpenedList().get(1).getId() == 2);
		assertTrue(algo.getOpenedList().get(2).getId() == 4);
		assertTrue(algo.getOpenedList().get(3).getId() == 1);
		assertTrue(algo.getOpenedList().get(4).getId() == 0);
	}

	@Test
	public void testAlmacenar() {
		ArrayList<Station> s = new ArrayList<>();
		s.add(new Station("", 0));
		s.get(0).setF(29);
		s.add(new Station("", 1));
		s.get(1).setF(26);
		s.add(new Station("", 2));
		s.get(2).setF(12);
		s.add(new Station("", 3));
		s.get(3).setF(10);
		s.add(new Station("", 4));
		s.get(4).setF(15);

		algo.setClosedList(s.get(0));
		algo.almacenar(s);
		assertTrue(algo.getOpenedList().size() == 4);
		algo.almacenar(s);
		assertTrue(algo.getOpenedList().size() == 4);
		s.add(new Station("", 5));
		s.get(5).setF(15);
		algo.almacenar(s);
		assertTrue(algo.getOpenedList().size() == 5);
	}

	@Test
	public void testFind() {
		Model model = new Model();
		model.ok("San Bernabé", "Penitenciaña");
		System.out.println(model.getWay());
		for(int i=0; i<model.getWay().size(); i++){
			System.out.print(model.getWay().get(i).getName()+"-");
		}
	}
	
	@Test
	public void testFind1() {
		Model model = new Model();
		model.ok("Central", "General I. Zaragoza");
		System.out.println(model.getWay());
		for(int i=0; i<model.getWay().size(); i++){
			System.out.print(model.getWay().get(i).getName()+"-");
		}
	}
}
