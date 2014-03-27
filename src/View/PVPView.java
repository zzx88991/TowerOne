package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Bullet;
import Model.Enemies;
import Model.EnemyA;
import Model.EnemyB;
import Model.EnemyC;
import Model.Game;
import Model.TowerA;
import Model.TowerB;
import Model.TowerC;
import Model.Towers;
import javax.swing.Timer;

public class PVPView extends JPanel implements Observer, MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private ObjectOutputStream outputToP;
	private ObjectInputStream inputFromP;

	private JLabel opponame;
	private JLabel enemyopposent;
	private JLabel oppoHP;
	private JLabel oppoTower;
	private JLabel money;
	private JLabel HeadQ;
	private JLabel timer;
	private JTextField input;
	private JTextArea console;
	private String name = null;
	private String text = "";
	private boolean runing = true;
	private boolean ready = false;
	private boolean startGame = false;
	private JButton start;
	private JButton ToA;
	private JButton ToB;
	private JButton ToC;
	private JButton EneA;
	private JButton EneB;
	private JButton EneC;
	private JTextArea towerInf;
	private String hostName;
	private int port;
	private Game game;
	private int type;
	private boolean haveOP;
	private int potentialResult;
	private int count;

	// animation things
	private BufferedImage bgd, twA, twB, twC, BA, BB, BC, appleA, appleB,
			appleC, EA, EB, EC;
	private ArrayList<Enemies> EnmiesInMap;
	private ArrayList<Towers> TowersInMap;
	private ArrayList<Bullet> BulletsInMap;
	private Vector<Object> myInf;
	private Vector<Object> opInf;
	private static ShadowPanelView shw;
	private int buildType;

	public PVPView(int type) {
		this.type = type;
		this.setSize(1250, 600);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setOpaque(false);
		haveOP = false;
		game = new Game(1);
		myInf = new Vector<Object>();
		opInf = new Vector<Object>();
		shw = new ShadowPanelView();
		shw.setSize(800, 600);
		shw.setLocation(0, 0);
		shw.addMouseMotionListener(this);
		this.add(shw);
		shw.setOpaque(false);
		this.setOpaque(false);
		shw.addMouseListener(this);
		setupInfBar();
		setInputBar();
		setConsole();
		loadImages();
		count = 0;
	}

	private void loadImages() {
		try {
			bgd = ImageIO.read(new File("images" + File.separator
					+ "JPGforMap.jpg"));

		} catch (Exception e) {
			System.out.println("Could not find 'map1.png'");

		}
		try {
			EA = ImageIO.read(new File("images" + File.separator
					+ "guaiwu1.png"));

		} catch (Exception e) {
			System.out.println("Could not find 'guaiwu1.png'");

		}
		try {
			EB = ImageIO.read(new File("images" + File.separator
					+ "guaiwuB.png"));

		} catch (Exception e) {
			System.out.println("Could not find 'guaiwuB.png'");

		}
		try {
			EC = ImageIO.read(new File("images" + File.separator
					+ "guaiwuC.png"));

		} catch (Exception e) {
			System.out.println("Could not find 'guaiwuC.png'");

		}
		try {
			twA = ImageIO.read(new File("images" + File.separator
					+ "TowerA.png"));
		} catch (Exception e) {
			System.out.println("Could not find 'twA.gif'");

		}
		try {
			twB = ImageIO.read(new File("images" + File.separator
					+ "TowerB.png"));
		} catch (Exception e) {
			System.out.println("Could not find 'twB.png'");

		}
		try {
			twC = ImageIO.read(new File("images" + File.separator
					+ "TowerC.png"));
		} catch (Exception e) {
			System.out.println("Could not find 'twC.png'");

		}
		try {
			BA = ImageIO.read(new File("images" + File.separator + "BA.png"));

		} catch (Exception e) {
			System.out.println("Could not find 'BA.png'");

		}
		try {
			BB = ImageIO.read(new File("images" + File.separator + "BB.png"));

		} catch (Exception e) {
			System.out.println("Could not find 'BB.png'");

		}
		try {
			BC = ImageIO.read(new File("images" + File.separator + "BC.png"));

		} catch (Exception e) {
			System.out.println("Could not find 'BC.png'");

		}
		try {
			appleA = ImageIO.read(new File("images" + File.separator
					+ "apple1.png"));

		} catch (Exception e) {
			System.out.println("Could not find 'apple1.png'");

		}
		try {
			appleB = ImageIO.read(new File("images" + File.separator
					+ "appleB.png"));

		} catch (Exception e) {
			System.out.println("Could not find 'appleB.png'");

		}
		try {
			appleC = ImageIO.read(new File("images" + File.separator
					+ "appplec.png"));

		} catch (Exception e) {
			System.out.println("Could not find 'applec.png'");

		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.drawImage(bgd, 0, 0, null);
		if (game.getHeadQuater() >= 7) {
			gr.drawImage(appleA, 750, 200, null);
		}
		if (game.getHeadQuater() >= 3 && game.getHeadQuater() < 7)
			gr.drawImage(appleB, 750, 200, null);
		if (game.getHeadQuater() < 3)
			gr.drawImage(appleC, 750, 200, null);

		EnmiesInMap = game.getEnemiesListInMap();
		TowersInMap = game.getTowersInMap();
		BulletsInMap = game.getBulletList();
		for (int i = 0; i < EnmiesInMap.size(); i++) {
			if (EnmiesInMap.get(i).showable()) {
				if (EnmiesInMap.get(i) instanceof EnemyA)
					gr.drawImage(EA, EnmiesInMap.get(i).getLocation().x,
							EnmiesInMap.get(i).getLocation().y, null);
				if (EnmiesInMap.get(i) instanceof EnemyB)
					gr.drawImage(EB, EnmiesInMap.get(i).getLocation().x,
							EnmiesInMap.get(i).getLocation().y, null);
				if (EnmiesInMap.get(i) instanceof EnemyC)
					gr.drawImage(EC, EnmiesInMap.get(i).getLocation().x,
							EnmiesInMap.get(i).getLocation().y, null);
			}
		}
		for (int i = 0; i < TowersInMap.size(); i++) {
			if (TowersInMap.get(i).showable()) {
				gr.setColor(Color.BLACK);
				gr.draw(TowersInMap.get(i).getShootRange());
			}
			if (TowersInMap.get(i) instanceof TowerA)
				gr.drawImage(twA, TowersInMap.get(i).getLocation().x,
						TowersInMap.get(i).getLocation().y, null);
			if (TowersInMap.get(i) instanceof TowerB)
				gr.drawImage(twB, TowersInMap.get(i).getLocation().x,
						TowersInMap.get(i).getLocation().y, null);
			if (TowersInMap.get(i) instanceof TowerC)
				gr.drawImage(twC, TowersInMap.get(i).getLocation().x,
						TowersInMap.get(i).getLocation().y, null);
		}
		for (int i = 0; i < BulletsInMap.size(); i++) {
			if (BulletsInMap.get(i).showable()) {
				if (BulletsInMap.get(i).getTower() instanceof TowerA)
					gr.drawImage(BA, BulletsInMap.get(i).getLocation().x,
							BulletsInMap.get(i).getLocation().y, null);
				if (BulletsInMap.get(i).getTower() instanceof TowerB)
					gr.drawImage(BB, BulletsInMap.get(i).getLocation().x,
							BulletsInMap.get(i).getLocation().y, null);
				if (BulletsInMap.get(i).getTower() instanceof TowerC)
					gr.drawImage(BC, BulletsInMap.get(i).getLocation().x,
							BulletsInMap.get(i).getLocation().y, null);
			}
		}

	}

	private void setupInfBar() {
		JPanel infPane = new JPanel();
		infPane.setSize(200, 200);
		infPane.setLocation(820, 30);
		infPane.setLayout(new GridLayout(7, 1));

		opponame = new JLabel("  Rival's name:  " + "joining...");
		enemyopposent = new JLabel("  Pests Rival sends:  " + "joining...");
		oppoHP = new JLabel("  Rival's Apple HP:  " + "joining...");
		oppoTower = new JLabel("  Rival's Tower:  " + "joining...");
		money = new JLabel("  Money you have: " + game.getPlayer().getMoney());
		HeadQ = new JLabel("  Apple HP:  " + game.getHeadQuater());
		timer = new JLabel("  Time left (seconds): " + (6000 - count) / 100);
		infPane.add(opponame);
		infPane.add(enemyopposent);
		infPane.add(oppoHP);
		infPane.add(oppoTower);
		infPane.add(money);
		infPane.add(HeadQ);
		infPane.add(timer);
		this.add(infPane);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setSize(180, 240);
		buttonPanel.setLocation(820, 240);
		buttonPanel.setLayout(new GridLayout(7, 1));
		start = new JButton("Start!");
		start.addActionListener(new StartButton());
		buttonPanel.add(start);
		ImageIcon imga = new ImageIcon("images/TowerA.png");
		ToA = new JButton("Bumblebee", imga);
		// TowerA.setSize(150,30);
		// TowerA.setLocation(800, 270);
		ImageIcon imgb = new ImageIcon("images/TowerB.png");

		ToB = new JButton("Megatron", imgb);
		// TowerB.setSize(150,30);
		// TowerB.setLocation(800, 300);

		ImageIcon imgc = new ImageIcon("images/TowerC.png");

		ToC = new JButton("Optimus prime", imgc);
		// TowerC.setSize(150,30);
		// TowerC.setLocation(800, 330);

		ToA.addActionListener(new TA());
		ToB.addActionListener(new TB());
		ToC.addActionListener(new TC());

		ImageIcon img1 = new ImageIcon("images/guaiwu1.png");
		ImageIcon img2 = new ImageIcon("images/guaiwuB.png");
		ImageIcon img3 = new ImageIcon("images/guaiwuC.png");
		EneA = new JButton("Nora", img1);
		// EneA.setSize(150,40);
		// EneA.setLocation(800, 360);
		EneB = new JButton("Flona", img2);
		// EneB.setSize(150,40);
		// EneB.setLocation(800, 390);
		EneC = new JButton("Maeve", img3);
		// EneC.setSize(150,40);
		// EneC.setLocation(800, 420);
		EneA.addActionListener(new EA());
		EneB.addActionListener(new EB());
		EneC.addActionListener(new EC());

		towerInf = new JTextArea(
				"Information bar: \n"
						+ "Please click on the items \nbutton to show \nthe information");
		towerInf.setEditable(false);
		towerInf.setLineWrap(true);
		towerInf.setWrapStyleWord(true);

		JScrollPane pane = new JScrollPane(towerInf);
		pane.setSize(180, 100);
		pane.setLocation(820, 490);
		this.add(pane);

		buttonPanel.add(ToA);
		buttonPanel.add(ToB);
		buttonPanel.add(ToC);
		buttonPanel.add(EneA);
		buttonPanel.add(EneB);
		buttonPanel.add(EneC);
		this.add(buttonPanel);

	}

	private void setInputBar() {
		input = new JTextField("Write Your Name Here");
		input.setSize(190, 30);
		input.setLocation(1030, 50);
		input.addActionListener(new TextFieldListener());
		this.add(input);
	}

	private void setConsole() {
		console = new JTextArea();

		console.setSize(190, 480);
		console.setEditable(false);
		console.setLineWrap(true);
		console.setWrapStyleWord(true);
		JScrollPane p1 = new JScrollPane(console);
		p1.setSize(190, 480);
		p1.setLocation(1030, 100);
		this.add(p1);
	}

	public int connectToServer() {
		if (type == 1) {
			Socket sock = null;
			JTextField hostInput = new JTextField(15);
			JTextField portInput = new JTextField(15);
			JPanel inputP = new JPanel();
			inputP.add(new JLabel("Host name: "));
			inputP.add(hostInput);
			inputP.add(Box.createHorizontalStrut(15));
			inputP.add(new JLabel("Port Number: "));
			inputP.add(portInput);

			int result = JOptionPane
					.showConfirmDialog(
							null,
							inputP,
							"Please enter host name and port number to connect to the server!",
							JOptionPane.OK_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				hostName = hostInput.getText();
				try {
					port = Integer.parseInt(portInput.getText());
				} catch (Exception e) {
					JOptionPane
							.showMessageDialog(null, "Please enter numbers!");
//					e.printStackTrace();
				}
			}
			if (result == JOptionPane.CANCEL_OPTION) {
				game.setHasEnd(true);
				return 0;
			}
			// connect to the server
			try {
				// if(hostName.equals(NetWork.Server.HOST_NAME)&&port==NetWork.Server.PORT_NUMBER)
				sock = new Socket(hostName, port);
				// else{
				// JOptionPane.showMessageDialog(null, "Host doest not exist!");
				// game.setHasEnd(true);
				// return 0;
				// }
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "The host does not exist!");
				game.setHasEnd(true);
				return 1;
			}
			try {
				outputToP = new ObjectOutputStream(sock.getOutputStream());
				inputFromP = new ObjectInputStream(sock.getInputStream());
			} catch (Exception e) {
//				System.out
//						.println("Unable to obtain Input & Output streams from Socket");
//				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Connect Successfully!");
			return 2;
		} else {
			Socket sock = null;
			try {
				// if(hostName.equals(NetWork.Server.HOST_NAME)&&port==NetWork.Server.PORT_NUMBER)
				sock = new Socket(NetWork.Server.HOST_NAME,
						NetWork.Server.PORT_NUMBER);
				// else{
				// JOptionPane.showMessageDialog(null, "Host doest not exist!");
				// game.setHasEnd(true);
				// return 0;
				// }
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "The host does not exist!");
				return 0;
			}
			try {
				outputToP = new ObjectOutputStream(sock.getOutputStream());
				inputFromP = new ObjectInputStream(sock.getInputStream());
			} catch (Exception e) {
//				System.out
//						.println("Unable to obtain Input & Output streams from Socket");
//				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, "Port: "
					+ NetWork.Server.PORT_NUMBER);
			return 2;
		}
	}

	@SuppressWarnings("unchecked")
	public void loop() {
		// this.connectToServer();
		try {

			while (runing) {
				Object content = inputFromP.readObject();
				haveOP = true;
				if (content instanceof String)
					console.append(content + "\n");
				if (content instanceof Vector<?>) {
					opInf = (Vector<Object>) content;
					updateInfBar();
					if (!(boolean) opInf.get(4)) {
						haveOP = false;
						JOptionPane.showMessageDialog(null, opInf.get(0)
								+ " has Escaped. You win!");
						// outputToP.writeObject('q');
						// outputToP.reset();
						// outputToP.flush();
						runing = false;
						game.setHasEnd(true);
						break;
					}

//					if (opInf.get(2) instanceof Integer) {
						if (count >= 6000) {
							if ((Integer) opInf.get(2) > game.getHeadQuater())
								JOptionPane
										.showMessageDialog(null,
												"Time is up, your apple looks worse, you lose...\nMoney you get: "
											+ game.getPlayer().getMoney()
											+ "\nMonstor you killed: "
											+ game.getEnemyKilled());
							else if ((Integer) opInf.get(2) == game
									.getHeadQuater())
								JOptionPane.showMessageDialog(null,
										"Time is up..It is a tie!\nMoney you get: "
											+ game.getPlayer().getMoney()
											+ "\nMonstor you killed: "
											+ game.getEnemyKilled());

							else
								JOptionPane
										.showMessageDialog(null,
												"Time is up! Your apple is healthier, you win!\nMoney you get: "
											+ game.getPlayer().getMoney()
											+ "\nMonstor you killed: "
											+ game.getEnemyKilled());

							runing = false;
							game.setHasEnd(true);
							break;
						}
//					}
						if ((boolean) opInf.get(7)) {
							// outputToP.writeObject('q');
							// outputToP.reset();
							// outputToP.flush();
							JOptionPane.showMessageDialog(
									null,
									"You Win!\nMoney you get: "
											+ game.getPlayer().getMoney()
											+ "\nMonstor you killed: "
											+ game.getEnemyKilled());
							runing = false;
							game.setHasEnd(true);
							break;
						}
						if ((boolean) opInf.get(5) && ready)
							startGame = true;
					}
					if (content instanceof Character) {
						if (content.equals('a'))
							game.addEnemy(new EnemyA());
						if (content.equals('b'))
							game.addEnemy(new EnemyB());
						if (content.equals('c'))
							game.addEnemy(new EnemyC());
					}

				}
			
		} catch (IOException e) {
//			System.out.println("In Client.connectToServer" + name);
			return;
		} catch (ClassNotFoundException e) {
//			System.out
//					.println("Trying to read an object of a different written type from Liason");
//			e.printStackTrace();
		}
	}

	private void updateInfBar() {
		if (opInf.size() != 0) {
			opponame.setText("  Rival's name:  " + opInf.get(0));
			enemyopposent.setText("  Pests rival sends:  " + opInf.get(1));
			oppoHP.setText("  Rival's Apple HP:  " + opInf.get(2));
			oppoTower.setText("  Rival's Tower numer:  " + opInf.get(3));
			money.setText("  Money you have: " + game.getPlayer().getMoney());
			HeadQ.setText("  Apple HP:  " + game.getHeadQuater());
			timer.setText(" Time left(seconds): " +(6000-count)/100);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		game = (Game) arg0;
		if (runing)
			this.sendMyInf();
		this.repaint();
		count++;

	}

	private void sendMyInf() {

		if (myInf.size() == 0) {
			myInf.add(name);
			myInf.add(game.getPlayer().getNumofUnitA()
					+ game.getPlayer().getNumofUnitB()
					+ game.getPlayer().getNumofUnitC());
			myInf.add(game.getHeadQuater());
			myInf.add(game.getPlayer().getNumofTowerA()
					+ game.getPlayer().getNumofTowerB()
					+ game.getPlayer().getNumofTowerC());
			myInf.add(runing);
			myInf.add(ready);
			myInf.add(game.multiGameHasTied());
			myInf.add(game.singleGameHasLost());
		} else {
			myInf.set(0, name);
			myInf.set(1, game.getPlayer().getNumofUnitA()
					+ game.getPlayer().getNumofUnitB()
					+ game.getPlayer().getNumofUnitC());
			myInf.set(2, game.getHeadQuater());
			myInf.set(3, game.getPlayer().getNumofTowerA()
					+ game.getPlayer().getNumofTowerB()
					+ game.getPlayer().getNumofTowerC());
			myInf.set(4, runing);
			myInf.set(5, ready);
			myInf.set(6, game.multiGameHasTied());
			myInf.set(7, game.singleGameHasLost());
		}
		if (game.singleGameHasLost()) {
			JOptionPane.showMessageDialog(null, "You lose!\nMoney you get: "
					+ game.getPlayer().getMoney() + "\nMonstor you killed: "
					+ game.getEnemyKilled());
			game.setHasEnd(true);
		}
		try {

			outputToP.writeObject(myInf);
			outputToP.reset();

			outputToP.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

	private class TextFieldListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			text = input.getText();
			if (name == null) {
				name = text;
				input.setText("");

				try {

					outputToP.writeObject(name + " has joined.");
					outputToP.reset();
					outputToP.flush();
					console.append("I have joined.\n");

				} catch (IOException e) {
//					e.printStackTrace();
				}

			} else
				try {

					outputToP.writeObject(name + ": " + text);
					outputToP.reset();

					outputToP.flush();
					console.append("Me: " + text + "\n");

				} catch (IOException e) {
//					e.printStackTrace();
				}
			input.setText("");

		}

	}

	public boolean isReadyToStart() {
		return startGame;
	}

	public int getType() {
		return type;
	}

	public class EC implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			towerInf.setText("Price: $30\nSpeed: fast\nHP: 60");
			Enemies c = new EnemyC();
			if (game.getPlayer().getMoney() >= c.getPrice()) {
				try {
					game.getPlayer().setMoney(
							game.getPlayer().getMoney() - c.getPrice());

					outputToP.writeObject('c');
					outputToP.reset();

					outputToP.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(null,
						"Sorry, you don't have enough money to buy a pest!");
		}

	}

	public class EB implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			towerInf.setText("Price: $20\nSpeed: slow\nHP: 40");

			Enemies b = new EnemyB();
			if (game.getPlayer().getMoney() >= b.getPrice()) {
				try {
					game.getPlayer().setMoney(
							game.getPlayer().getMoney() - b.getPrice());

					outputToP.writeObject('b');
					outputToP.reset();

					outputToP.flush();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(null,
						"Sorry, you don't have enough money to buy a pest!");
		}

	}

	public class EA implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			towerInf.setText("Price: $10\nSpeed: slow\nHP: 20");
			Enemies a = new EnemyA();
			if (game.getPlayer().getMoney() >= a.getPrice()) {
				try {
					game.getPlayer().setMoney(
							game.getPlayer().getMoney() - a.getPrice());

					outputToP.writeObject('a');
					outputToP.reset();
					outputToP.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(null,
						"Sorry, you don't have enough money to buy a pest!");
		}

	}

	public class TC implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			buildType = 3;
			towerInf.setText("Power: Weak\nRange: Small\nPrice: $100\n\nYou can click on the tower in the map to see the range.");

		}

	}

	public class TB implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			buildType = 2;
			towerInf.setText("Power: Medium\nRange: Medium\nPrice: $80\n\nYou can click on the tower in the map to see the range.");
		}

	}

	public class TA implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buildType = 1;
			towerInf.setText("Power: Strong\nRange: Large\nPrice: $50\n\nYou can click on the tower in the map to see the range.");
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		shw.setPoint(new Point(arg0.getX() - arg0.getX() % 50, arg0.getY()
				- arg0.getY() % 50));
		shw.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < game.getTowersInMap().size(); i++) {
			if (game.getTowersInMap()
					.get(i)
					.getLocation()
					.equals(new Point(e.getX() - e.getX() % 50, e.getY()
							- e.getY() % 50)))
				game.getTowersInMap().get(i)
						.setShowable(!game.getTowersInMap().get(i).showable());

		}
		if (buildType == 1) {
			game.buildTowerA(new Point(e.getX() - e.getX() % 50, e.getY()
					- e.getY() % 50));
		} else if (buildType == 2) {

			game.buildTowerB(new Point(e.getX() - e.getX() % 50, e.getY()
					- e.getY() % 50));
		}

		else if (buildType == 3) {
			game.buildTowerC(new Point(e.getX() - e.getX() % 50, e.getY()
					- e.getY() % 50));

		}

		else {
			JOptionPane.showMessageDialog(null,
					"You need to choose a tower to build!");
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void sendEscape() {
		if (haveOP) {
			try {
				if (startGame) {
					runing = false;
					myInf.set(4, false);
					outputToP.writeObject(myInf);
					outputToP.reset();

					outputToP.flush();
					game.setHasEnd(true);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}

	public class StartButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (name == null)
				JOptionPane.showMessageDialog(null,
						"Please Eneter Your Name First!");
			else {
				try {
					outputToP.writeObject(name + " is ready.");
					outputToP.reset();

					outputToP.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
				ready = true;
				sendMyInf();
				console.append("You are ready!\n");

				if (opInf.size() != 0) {
					if ((boolean) opInf.get(5)) {
						console.append("Game starts!\n");
						try {
							outputToP.writeObject("Game starts!");
							outputToP.reset();

							outputToP.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
//							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	public int gerResult() {
		return potentialResult;
	}
	// public class TimerListener implements ActionListener {
	//
	// @Override
	// public void actionPerformed(ActionEvent arg0) {
	// game.update();
	// }
	//
	// }

}
