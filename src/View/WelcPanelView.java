package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WelcPanelView extends JPanel {
	private BufferedImage logo;

	public WelcPanelView (){
		this.setSize(1250, 600);
		this.setLocation(0, 0);
		this.setLayout(null);
		JTextArea towerInf = new JTextArea(
				"Welcome to play Apple Defense.\nThis is a normal real time strategy game. Base on defense our cute apple.\n1.Menu:\n(1)The New Game button on the rightupper corner is the main menu of the game \n(2) After clicking the New Game button you can see two options: Single Player and Multiple player \n(3) Single Player botton can start a Single Player game \n(4) Multiple Player button can give you two options: start a network game as a host or join an existing network game\n(5)  Host game start a new game by using it's won ip and port number\n(6)Join game will start a game which require host's name(ip) and port number.\n2.Single Game\n(1) Single game is played by a single player\n(2)Player can use the menu on the right of the window to build tower and see:Money left, pests killed and apple(headquater)'s HP\n(3) In the right menu, click the tower button can build the tower and show the information about tower\n3.Multiple Player\n(1)Multiple Player's Menu has the same function of Single Player's Menu, but it also has the function to spawn units on oppoent's map\n4.Rules\nSingle-player: \nLose: When 10 enemies reach the Apple. \nWin: after 3 waves enemies, apple HP still positive, you are WINNER!!!! \nMulti-player: \nFor multi you can choose to be the host or join other¡‘s game, \nIf you want to the host let other people know your computer name or IP address, and port number. \nThen enter a name you want, press start wait other people join your game.\nIf you choose ¡°join game¡± you need enter the host¡¯s computer name or IP address and port number. \nThen enter a name you want start game\nYou can check on the chatting area did the host start or not. \nIn multi you have 60s limit to win the game\nLose: When the other¡¯s player has more HP. \nWin: When you apple have more HP then other player. \nTie: 2 players have same HP. \nIn this game you can chat with other people knows the current information. \nWhen game over will back to welcome screen. \nPictures come from Internet. \nEnjoy~~~ Protect out Apple!!! \nThanks for all the Section Leader!!! \n With out the help we could not to be success. \nDesigned by Team: Tower One\nSincerely\n\nChangchangHu, Chenqi Deng, Qiming Shao, Zixiang Zhou\n12/04/12\nWorks Cited\n Guaiwu1. Digital image. N.p., n.d. Web. <https://itunes.apple.com/cn/app/bao-wei-luo-bo/id534453594?mt=8>. \nGuaiwuB. N.d. Photograph. App Store. Web. 04 Dec. 2012. <https://itunes.apple.com/cn/app/bao-wei-luo-bo/id534453594?mt=8>. \n'GuaiwuC.' App Store. N.p., n.d. Web. 04 Dec. 2012. <https://itunes.apple.com/cn/app/bao-wei-luo-bo/id534453594?mt=8>. \n'TowerA.' N.p., n.d. Web. 04 Dec. 2012. <http://so.mypsd.com.cn/keywords/?750701>. \n'TowerB.' N.p., n.d. Web. 04 Dec. 2012. <http://so.mypsd.com.cn/keywords/?750701>. \n'TowerC.' N.p., n.d. Web. 04 Dec. 2012. <http://so.mypsd.com.cn/keywords/?750701>. ");
		towerInf.setEditable(false);
		towerInf.setLineWrap(true);
		towerInf.setWrapStyleWord(true);

		JScrollPane pane = new JScrollPane(towerInf);
		pane.setSize(400, 570);
		pane.setLocation(825, 30);
		this.add(pane);
		loadImage();
	}

	private void loadImage() {
		try {
			logo = ImageIO
					.read(new File("images" + File.separator + "Logo.png"));

		} catch (Exception e) {
			System.out.println("Could not find 'map1.png'");

		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D gr = (Graphics2D) g;
		gr.drawImage(logo, 0, 0, null);
	}
}
