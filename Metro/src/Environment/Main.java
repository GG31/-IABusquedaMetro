package Environment;

import Controller.Controller;

public class Main {

	public static void main(String[] args) {
		//Model e = new Model();
		new Controller(new Model());
		//System.out.println(e.toStringLine());
		//System.out.println(e.toStringDistance());
	}

}
