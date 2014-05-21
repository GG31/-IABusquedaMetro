package tests;

import java.io.IOException;

import org.junit.Test;

import Environment.Model;
import Interface.Map;

public class MapTest {
	@Test
	public void testInit() {
		Model model = new Model();
		Map map = new Map(model.getStations());
		
		while (true)
			;
	}
}
