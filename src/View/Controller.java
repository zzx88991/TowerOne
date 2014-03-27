package View;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import Model.Game;
import NetWork.Server;

public class Controller extends JFrame {

	

	public class AboutInf implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JOptionPane.showMessageDialog(null, "Hints:\n\n\n1.Please only run Controller.java(Server.java is not needed to run).\n\n2.When you are playing multiplayer game, please set up the host first, and then another player can join the host.\n\n3.When you are playing multiplayer game, please press start to get ready.\n\n4.Only when name is entered, the player can get ready.\n\n5.Only when two players are ready, the game will start.");
		}

	}


	private static final long serialVersionUID = 1L;

	private static final int delayInMillis = 10;
	private Container cp;
	private static JPanel currentPanel;
	private static PanelView panel;
	private static PVPView pvp;
	private static Game game;
	private WelcPanelView welPanel;
	private JMenuBar menuBar;
	private JMenu newGame;
	private JMenu help;
	private JMenuItem about;
	private JMenuItem single;
	private JMenu multi;
	private JMenuItem host;
	private JMenuItem join;
	private Timer timer1;
	private static boolean serverRuning;
	private static boolean connect;
	private static boolean reconnect = true;

	public static void main(String[] args) {
		Controller window = new Controller();
		window.setVisible(true);

		// do{
		while (true) {
			while (!connect) {

				// connect =false;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();

				}
			}
			if (pvp.getType() == 0) {
				if(!serverRuning){
				Server.main(args);
				serverRuning =true;
				}
				if(pvp.connectToServer()==2)
				pvp.loop();
				connect = false;
			} else {
				if(pvp.connectToServer()==2)
				pvp.loop();
				connect = false;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}

		}
		// connect=false;
		// }
		// }while(reconnect);

		// if(pvp.connectToServer()==true){
		// pvp.loop();
		// }else{
		// Server.main(args);
		//
		// }

	}

	public Controller() {
		this.setSize(1250, 639);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.addWindowListener(new WindowCloseOperation());
		cp = this.getContentPane();
		cp.setLayout(null);
		serverRuning = false;
		connect = false;
		setupMenu();
		setupDefaultView();

	}

	private void setupDefaultView() {
		welPanel = new WelcPanelView();
		
		welPanel.repaint();
		currentPanel = welPanel;
		cp.add(currentPanel);
		this.repaint();
	}

	private void setPVEView() {
		panel = new PanelView();
		game = new Game(0);
		game.addObserver(panel);
		currentPanel = panel;
		timer1 = new Timer(delayInMillis, new TimerListener());
		timer1.start();
		cp.add(currentPanel);
	}

	private void setHostPVPView() {
		pvp = new PVPView(0);
		game = new Game(1);
		game.addObserver(pvp);

		currentPanel = pvp;

		timer1 = new Timer(delayInMillis, new TimerListener());
		timer1.start();
		
		cp.add(currentPanel);
		currentPanel.validate();
		currentPanel.repaint();
		connect = true;
		
	}

	private void setJoinPVPView() {
		pvp = new PVPView(1);
		game = new Game(1);
		game.addObserver(pvp);

		currentPanel = pvp;

		timer1 = new Timer(delayInMillis, new TimerListener());
		timer1.start();
		cp.add(currentPanel);

		currentPanel.validate();
		currentPanel.repaint();
		connect = true;


	}

	private void setupMenu() {

		menuBar = new JMenuBar();
		menuBar.setSize(450, 30);
		menuBar.setLocation(800, 0);
		newGame = new JMenu("New Game");
		menuBar.add(newGame);
		single = new JMenuItem("Single Player");
		single.addActionListener(new SingleGame());
		newGame.add(single);
		multi = new JMenu("Multiple Player");
		host = new JMenuItem("Host game");
		join = new JMenuItem("Join game");
		host.addActionListener(new MultiGame1());
		join.addActionListener(new MultiGame2());

		multi.add(host);
		multi.add(join);
		newGame.add(multi);
		help = new JMenu("help");
		about = new JMenuItem("Hints");
		about.addActionListener(new AboutInf());
		help.add(about);
		menuBar.add(help);
		cp.add(menuBar);
	}

	public void reset() {
		timer1.stop();
		cp.remove(currentPanel);
		this.setupDefaultView();
		this.update(getGraphics());
	}

	public void restart() {
		cp.remove(currentPanel);
		timer1.stop();
		if (panel != null) {
			timer1.stop();
			setPVEView();
		} else {
			setPVEView();
		}
	}

	public class SingleGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cp.remove(currentPanel);
			if (currentPanel instanceof PVPView||currentPanel instanceof PanelView) {
				
				timer1.stop();
				 }
			setPVEView();
		}
	}

	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (game.getType() == 0) {
				if (game.singleGameHasLost()) {
					int option = JOptionPane.showConfirmDialog(
							null,
							"Game Over! Apple was eaten by pests\nMoney you get: "
									+ game.getPlayer().getMoney()
									+ "\nMonstor you killed: "
									+ game.getEnemyKilled()
									+ "\n\nDo you want to start a new game?");
					if (option == 0)
						restart();

					else if (option == 1 || option == 2) {
						reset();
					}
				} else if (game.singleGamwIsWin()) {
					int option = JOptionPane.showConfirmDialog(
							null,
							"You win! Apple survies!\nMoney you get: "
									+ game.getPlayer().getMoney()
									+ "\nPests you killed: "
									+ game.getEnemyKilled()
									+ "\n\nDo you want to start a new game?");
					if (option == 0)
						restart();

					else if (option == 1 || option == 2) {
						reset();
					}

				} else {
					game.update();
				}
			}
			else if (game.getType() == 1) {
				if (game.hasEnd()) {
					reset();
				} else if (pvp.isReadyToStart()) {
					game.update();
				}

			}
		}

	}

	public class MultiGame1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cp.remove(currentPanel);
			if (currentPanel instanceof PVPView||currentPanel instanceof PanelView) {
			
			timer1.stop();
			 }
			setHostPVPView();
		}

	}
	public class MultiGame2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cp.remove(currentPanel);
			if (currentPanel instanceof PVPView||currentPanel instanceof PanelView) {
				
				timer1.stop();
				 }
			 
			setJoinPVPView();
		}

	}
	

	public class WindowCloseOperation implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			if(game!=null&&game.getType()==1)
			 pvp.sendEscape();
			System.exit(0);
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
