package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
	private static final long serialVersionUID = 1L;
	private final int RADIUS = 10;
	private final String MAPFILE = "resources/MapaMetroMonterrey.jpg";
	private final ArrayList<Position> POSITIONS = new ArrayList<Position>() {
		{
			add(new Position(144, 127));
			add(new Position(203, 179));
			add(new Position(242, 215));
			add(new Position(280, 248));
			add(new Position(312, 285));// Penitenciana
			add(new Position(312, 325));
			add(new Position(312, 359));
			add(new Position(312, 399));
			add(new Position(312, 438));
			add(new Position(358, 451));// Edison
			add(new Position(410, 451));
			add(new Position(472, 451));
			add(new Position(533, 451));
			add(new Position(591, 451));
			add(new Position(648, 451));
			add(new Position(701, 451));
			add(new Position(753, 463));
			add(new Position(805, 463));
			add(new Position(855, 463));
			add(new Position(491, 86));// Sendero
			add(new Position(491, 128));
			add(new Position(491, 168));
			add(new Position(491, 210));
			add(new Position(491, 273));
			add(new Position(491, 310));
			add(new Position(472, 349));
			add(new Position(472, 392));

			add(new Position(472, 523));
			add(new Position(472, 573));
			add(new Position(493, 603));
			add(new Position(539, 603));
			add(new Position(591, 578));
			add(new Position(591, 520));
			add(new Position(591, 392));
			add(new Position(606, 339));
			add(new Position(646, 302));

			add(new Position(686, 269));
			add(new Position(723, 233));
		}
	};
	private Controller controleur;
	private Station[] stations;
	private HashMap<Station, Position> positionStation;

	// Graphics
	private JComboBox<String> comboOrigin;
	private JComboBox<String> comboDestination;
	private JButton buttonOk;
	private BufferedImage myPicture;
	private Graphics2D g;
	private JLabel labelImage;
	private JPanel panelLeft;

	public Map(Controller controleur, Station[] stations){
		this.controleur = controleur;
		this.stations = stations;
		this.positionStation = new HashMap<>();
		for (int i = 0; i < stations.length; i++) {
			this.positionStation.put(stations[i], POSITIONS.get(i));
		}
		try {
			this.drawCombo();
			this.draw(new ArrayList<Station>(), 0, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Inicializa los JCombobox
	 */
	private void drawCombo() {
		this.comboOrigin = new JComboBox<String>();
		this.comboDestination = new JComboBox<String>();
		
		for (int i = 0; i < stations.length; i++) {
			this.comboOrigin.addItem(stations[i].getName());
			this.comboDestination.addItem(stations[i].getName());
		}
		this.buttonOk = new JButton("Ok");
		buttonOk.addActionListener(this);
	}

	// Dibuja el mapa "vacía"
	private void draw(ArrayList<Station> s, int o, int d) throws IOException {
		// Window
		this.setTitle("Metro Monterrey");
		this.setSize(1150, 700);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// Map
		this.myPicture = ImageIO.read(new File(MAPFILE));
		this.labelImage = new JLabel(new ImageIcon(myPicture));
		labelImage.setBounds(0, 0, 1000, 700);

		// Panel right
		JPanel panelRight = new JPanel();
		// Add layout to panel
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.PAGE_AXIS));
		// Add component to panel
		panelRight.add(comboOrigin);
		panelRight.add(comboDestination);
		panelRight.add(buttonOk);

		// Panel left (image)
		this.panelLeft = new JPanel();
		panelLeft.add(labelImage);

		// Para dibujar en la imagen, crea un graphic
		this.g = myPicture.createGraphics();

		// Dibuja el camino
		drawCamino(s);

		// Add panel right to panelWindow
		this.getContentPane().add(panelRight, BorderLayout.EAST);
		this.getContentPane().add(panelLeft, BorderLayout.WEST);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Dibuja un círculo a cada estación en parámetro
	 * 
	 * @param stations
	 */
	private void drawCamino(ArrayList<Station> stations) {
		// Hace el camino según las estaciones en parámetro
		System.out.println("draw camino");
		for (Station s : stations) {
			System.out.println("draw " + s.getName());
			g.setColor(Color.RED);
			g.fillOval(positionStation.get(s).getX() - 5, positionStation
					.get(s).getY() - 5, RADIUS, RADIUS);
		}
	}

	/**
	 * Da señal al algoritmo para que busque entre la origen y el destino
	 * seleccionado
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonOk) {
			this.comboOrigin.updateUI();
		try {
			System.out.println("Origin : " + this.comboOrigin.getSelectedItem()
					+ " Dest : " + this.comboDestination.getSelectedItem());
			this.controleur.notifyWayChanged(
					(String) this.comboOrigin.getSelectedItem(),
					(String) this.comboDestination.getSelectedItem());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}

	/**
	 * Una vez encontrado el camino, el modelo señala al mapa por este método
	 */
	public void wayChanged(FindWayEvent event) {
		System.out.println("Way change " + event.getStationsWay().size());
		ArrayList<Station> s = event.getStationsWay();
		try {
			this.draw(s, s.get(0).getId(), s.get(s.size() - 1).getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
