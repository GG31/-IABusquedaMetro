package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Controller;
import Controller.FindWayEvent;
import Controller.FindWayListener;
import Environment.Station;

public class Map extends JFrame implements ActionListener, FindWayListener {
	private Controller controleur = null;
	private Station[] stations;

	// Graphics
	private JComboBox comboOrigin;
	private JComboBox comboDestination;

	public Map(Station[] stations) {
		this.stations = stations;
		try {
			this.draw();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map(Controller controleur, Station[] stations) throws IOException {
		this.controleur = controleur;
		this.draw();
	}

	// Dibuja el mapa "vacía"
	private void draw() throws IOException {
		// Window
		this.setTitle("Metro Monterrey");
		this.setSize(1150, 700);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// Map
		BufferedImage myPicture = ImageIO.read(new File(
				"resources/MapaMetroMonterrey.jpg"));
		JLabel labelImage = new JLabel(new ImageIcon(myPicture));
		labelImage.setBounds(0, 0, 1000, 700);
		// Add image to window
		//this.getContentPane().add(labelImage, BorderLayout.WEST);

		// Button and Box
		this.comboOrigin = new JComboBox();
		this.comboDestination = new JComboBox();
		for (int i = 0; i < stations.length; i++){
			this.comboOrigin.addItem(stations[i].getName());
			this.comboDestination.addItem(stations[i].getName());
		}
		
		JButton jbuton = new JButton("Ok");

		// Panel right
		JPanel panelRight = new JPanel();
		// Add layout to panel
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.PAGE_AXIS));
		//Add component to panel
		panelRight.add(comboOrigin);
		panelRight.add(comboDestination);
		panelRight.add(jbuton);
		
		//Panel left (image)
		JPanel panelLeft = new JPanel();
		panelLeft.add(labelImage);
		
		//Dessiner sur l'image
		Graphics2D g2 = myPicture.createGraphics();
		drawCircles(g2);
		g2.dispose();
		
		// Add panel right to panelWindow
		this.getContentPane().add(panelRight, BorderLayout.EAST);
		this.getContentPane().add(panelLeft, BorderLayout.WEST);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void drawCircles(Graphics2D g){
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(10,10,80,80);
		g.setColor(Color.BLUE);
		g.fillOval(150,50,80,80);
		g.setColor(c);
	}

	public void drawCamino(ArrayList<Station> stations) {
		// Hace el camino según las estaciones en parámetro
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// if (e.getSource() == buttonOk) { }
		try {
			// this.controleur.notifyWayChanged(labelDepart.getText().toString()),
			// labelDestination.getText().toString());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void wayChanged(FindWayEvent event) {
		clear(); // Clean the way existing to do other
		ArrayList<Station> s = event.getStationsWay();

	}

	public void clear() {

	}
}
