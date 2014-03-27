package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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

public class PanelView extends JPanel implements Observer, MouseListener,
		MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private class TA implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			buildType = 1;
			towerInf.setText("Power: Weak\nRange: Small\nPrice: $50\n\nYou can click on the tower in the map to see the range.");

		}
	}

	private class TB implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			buildType = 2;
			towerInf.setText("Power: Medium\nRange: Medium\nPrice: $80\n\nYou can click on the tower in the map to see the range.");
		}
	}

	private class TC implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			buildType = 3;
			towerInf.setText("Power: Strong\nRange: Large\nPrice: $100\n\nYou can click on the tower in the map to see the range.");

		}
	}

	private BufferedImage bgd, twA, twB, twC, BA, BB, BC, appleA, appleB,
			appleC, EA, EB, EC;

	private ArrayList<Enemies> EnmiesInMap;
	private ArrayList<Towers> TowersInMap;
	private ArrayList<Bullet> BulletsInMap;
	private static ShadowPanelView shw;
	private static Game game;
	private JLabel money;
	private JLabel Enemy;
	private JLabel HeadQ;
	private JButton TowerA;
	private JButton TowerB;
	private JButton TowerC;
	private JTextArea towerInf;
	private int buildType;

	/* private int duckX = 0, duckY =120; */

	public PanelView() {
		this.loadImages();
		this.setSize(1250, 600);
		this.setLocation(0, 0);
		this.setLayout(null);
		shw = new ShadowPanelView();
		shw.setSize(800, 600);
		shw.setLocation(0, 0);
		shw.addMouseMotionListener(this);
		this.add(shw);
		shw.setOpaque(false);
		this.setOpaque(false);
		game = new Game(0);
		// timer = new Timer(delayInMillis, this);
		// timer.start();
		shw.addMouseListener(this);
		EnmiesInMap = new ArrayList<Enemies>();
		TowersInMap = new ArrayList<Towers>();
		BulletsInMap = new ArrayList<Bullet>();
		setInfLayout();
	}

	private void setInfLayout() {
		money = new JLabel("  money   " + game.getPlayer().getMoney());
		money.setSize(200, 30);
		money.setLocation(850, 30);
		Enemy = new JLabel("  EnemiesKilled  "
				+ (30 - game.getEnemiesListInMap().size() - game
						.getEnemiesListOutMap().size()));
		Enemy.setSize(200, 30);
		Enemy.setLocation(850, 60);
		HeadQ = new JLabel("  Apple HP:  " + game.getHeadQuater() + "");
		HeadQ.setSize(200, 30);
		HeadQ.setLocation(850, 90);
		ImageIcon imga = new ImageIcon("images/TowerA.png");
		TowerA = new JButton("Bumblebee", imga);
		TowerA.setSize(150, 40);
		TowerA.setLocation(850, 150);
		ImageIcon imgb = new ImageIcon("images/TowerB.png");

		TowerB = new JButton("Megatron", imgb);
		TowerB.setSize(150, 40);
		TowerB.setLocation(850, 200);

		ImageIcon imgc = new ImageIcon("images/TowerC.png");

		TowerC = new JButton("TowerC", imgc);
		TowerC.setSize(150, 40);
		TowerC.setLocation(850, 250);

		TowerA.addActionListener(new TA());
		TowerB.addActionListener(new TB());
		TowerC.addActionListener(new TC());
		towerInf = new JTextArea(
				"Tower information bar: \n"
						+ "Please click on tower \nbutton to show \nthe information about \nthe tower!");
		towerInf.setEditable(false);
		towerInf.setLineWrap(true);
		towerInf.setWrapStyleWord(true);
		JScrollPane pane = new JScrollPane(towerInf);
		pane.setSize(150, 100);
		pane.setLocation(850, 300);
		this.add(pane);

		this.add(money);
		this.add(Enemy);
		this.add(HeadQ);
		this.add(TowerA);
		this.add(TowerB);
		this.add(TowerC);
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

	@Override
	public void update(Observable o, Object arg) {
		game = (Game) o;
		this.repaint();
		infPanelChange();

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
			}
			else if (buildType == 2) {
			
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
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void infPanelChange() {
		money.setText("  Money  " + game.getPlayer().getMoney());
		Enemy.setText("  Pests killed  " + game.getEnemyKilled());
		HeadQ.setText("  Apple HP:  " + game.getHeadQuater());
	}

}
