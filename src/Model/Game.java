package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JOptionPane;

public class Game extends Observable {
	private static final int MULTIPLAY_AMOUNT = 250;
	private Player player;
	private ArrayList<Enemies> EnemiesList;
	private ArrayList<Enemies> EnemiesListInMap;
	private ArrayList<Towers> TowerCollection;
	private ArrayList<Bullet> BulletCollection;
	private int type;
	private int headQuater;
	private int enemykilled;
	private int addNum;
	private int addBulletNum;
	private int chageEneNum;
	private boolean multiHasTied;
	private boolean hasEnd;

	public Game(int type) {

		player = new Player();
		headQuater = 10;
		EnemiesList = new ArrayList<Enemies>();
		this.type = type;
		if (type == 0) {
			for (int i = 0; i < 100; i++) {
				if (i <= 25) {
					EnemiesList.add(new EnemyA());

				} else if (i > 25 && i <= 75) {
					EnemiesList.add(new EnemyB());

				} else if (i > 75 && i < 100) {
					EnemiesList.add(new EnemyC());

				}
			}
		}
		if (type == 1) {
			player.setMoney(MULTIPLAY_AMOUNT);
		}
		EnemiesListInMap = new ArrayList<Enemies>();
		TowerCollection = new ArrayList<Towers>();
		BulletCollection = new ArrayList<Bullet>();
		multiHasTied = false;
		hasEnd = false;
		addNum = 0;
		enemykilled = 0;
		addBulletNum = 0;
		chageEneNum = 0;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Enemies> getEnemiesOutMap() {
		return EnemiesList;
	}

	public ArrayList<Enemies> getEnemiesListInMap() {
		return EnemiesListInMap;
	}

	public ArrayList<Towers> getTowersInMap() {
		return TowerCollection;
	}

	public ArrayList<Bullet> getBulletCollection() {
		return BulletCollection;
	}

	public ArrayList<Enemies> getEnemiesListOutMap() {
		// TODO Auto-generated method stub
		return EnemiesList;
	}

	public ArrayList<Bullet> getBulletList() {
		return BulletCollection;
	}

	public boolean buildTowerA(Point location) {
		Towers A = new TowerA();
		if (player.getMoney() < A.getPrice()) {
			JOptionPane.showMessageDialog(null,
					"Sorry, you do not have enough money!");
			return false;
		} else {
			if (A.setLocation(location.x, location.y)) {
				for (int i = 0; i < TowerCollection.size(); i++) {
					if (A.getRange().intersects(
							TowerCollection.get(i).getRange())) {
						JOptionPane.showMessageDialog(null,
								"You can't build a tower here!");
						return false;
					}

				}
				TowerCollection.add(A);
				player.setMoney(player.getMoney() - A.getPrice());
				player.countTowerA();
				return true;
			} else {
				JOptionPane.showMessageDialog(null,
						"Sorry, you can't build tower on the road");
				return false;
			}
		}

	}

	public boolean buildTowerB(Point location) {
		Towers B = new TowerB();
		if (player.getMoney() < B.getPrice()) {
			JOptionPane.showMessageDialog(null,
					"Sorry, you do not have enough money!");
			return false;
		} else {
			if (B.setLocation(location.x, location.y)) {
				for (int i = 0; i < TowerCollection.size(); i++) {
					if (B.getRange().intersects(
							TowerCollection.get(i).getRange())) {
						JOptionPane.showMessageDialog(null,
								"There exists a tower!");
						return false;
					}

				}
				TowerCollection.add(B);
				player.setMoney(player.getMoney() - B.getPrice());
				player.countTowerB();
				return true;
			} else {
				JOptionPane.showMessageDialog(null,
						"Sorry, you can't build tower on the road");
				return false;
			}
		}
	}

	public boolean buildTowerC(Point location) {
		Towers C = new TowerC();
		if (player.getMoney() < C.getPrice()) {
			JOptionPane.showMessageDialog(null,
					"Sorry, you do not have enough money!");
			return false;
		} else {
			if (C.setLocation(location.x, location.y)) {
				for (int i = 0; i < TowerCollection.size(); i++) {
					if (C.getRange().intersects(
							TowerCollection.get(i).getRange())) {
						JOptionPane.showMessageDialog(null,
								"There exists a tower!");
						return false;
					}

				}
				TowerCollection.add(C);
				player.setMoney(player.getMoney() - C.getPrice());
				player.countTowerC();
				return true;
			} else {
				JOptionPane.showMessageDialog(null,
						"Sorry, you can't build tower on the road");
				return false;
			}
		}
	}

	public int getEnemyKilled() {

		return enemykilled;
	}

	public int getHeadQuater() {
		return headQuater;
	}

	public boolean singleGameHasLost() {
		return headQuater <= 0;
	}

	public boolean singleGamwIsWin() {

		return EnemiesList.size() == 0 && EnemiesListInMap.size() == 0
				&& !singleGameHasLost();
	}

	public boolean multiGameHasTied() {
		if (player.getMoney() < 10 && EnemiesList.size() == 0
				&& EnemiesListInMap.size() == 0 && headQuater > 0)
			multiHasTied = true;
		return multiHasTied;
	}

	public void update() {

		loadEnemyintoMap();
		makebullet();
		updateBulletLocation();
		updateBullet();
		updateEnemyLocation();
		updateEnemy();
		super.setChanged();
		super.notifyObservers();
	}

	private void loadEnemyintoMap() {
		if (EnemiesList.size() > 0) {
			if (addNum == 0) {
				EnemiesListInMap.add(EnemiesList.remove(0));

				addNum = 40;
			} else
				addNum--;
		}
	}

	private void makebullet() {
		if (EnemiesListInMap.size() > 0) {
			if (addBulletNum == 0) {

				for (int j = 0; j < TowerCollection.size(); j++) {
					int k = 0;
					while (k < EnemiesListInMap.size()) {
						if (TowerCollection.get(j).getShootRange().contains(

						EnemiesListInMap.get(k).getCenterPoint())) {
							BulletCollection.add(new Bullet(TowerCollection
									.get(j), EnemiesListInMap.get(k)));
							break;
						}
						k++;
					}
				}
				addBulletNum = 5;
			} else
				addBulletNum--;

		}
	}

	private void updateBulletLocation() {
		if (BulletCollection.size() > 0) {
			for (int h = 0; h < BulletCollection.size(); h++) {
				if (BulletCollection.get(h).hasmet()
						&& !BulletCollection.get(h).getTarget().isDead()

				) {
					BulletCollection
							.get(h)
							.getTarget()
							.getHurt(
									BulletCollection.get(h).getTower()
											.getDamage());
					BulletCollection.get(h).setShowable(false);

				} else if (BulletCollection.get(h).getTarget().isDead()
						|| !BulletCollection.get(h).getTarget().showable()) {
					BulletCollection.get(h).setShowable(false);
					BulletCollection.get(h).getTarget().setShowable(false);
				} else if (BulletCollection.get(h).showable())
					BulletCollection.get(h).changeLocation();
			}
		}

	}

	private void updateEnemyLocation() {
		if (EnemiesListInMap.size() > 0) {
			if (chageEneNum == 0) {
				for (int i = 0; i < EnemiesListInMap.size(); i++) {
					if (EnemiesListInMap.get(i).getLocation().x >= 750
							&& EnemiesListInMap.get(i).getLocation().y >= 200) {
						EnemiesListInMap.get(i).setShowable(false);
						headQuater--;
					} else if (EnemiesListInMap.get(i).showable())
						EnemiesListInMap.get(i).changeLocation();
				}
				chageEneNum = 6;
			} else
				chageEneNum--;
		}
	}

	private void updateBullet() {
		for (int i = 0; i < BulletCollection.size(); i++) {
			if (!BulletCollection.get(i).showable())
				BulletCollection.remove(i);
		}
	}

	private void updateEnemy() {
		for (int i = 0; i < EnemiesListInMap.size(); i++) {
			if (EnemiesListInMap.get(i).isDead()) {
				player.setMoney(player.getMoney()
						+ EnemiesListInMap.get(i).getPrice());
				enemykilled++;
				EnemiesListInMap.get(i).setShowable(false);
			}

			if (!EnemiesListInMap.get(i).showable())
				EnemiesListInMap.remove(i);
		}
	}

	public void addEnemy(Enemies enemy) {
		EnemiesList.add(enemy);
		if (enemy instanceof EnemyA)
			player.countUnitA();

		if (enemy instanceof EnemyB)
			player.countUnitB();

		if (enemy instanceof EnemyC)
			player.countUnitC();

	}

	public boolean hasEnd() {
		return hasEnd;
	}

	public void setHasEnd(boolean b) {
		hasEnd = b;
	}

	public int getType() {
		return type;
	}

}