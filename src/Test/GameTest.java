package Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.Game;

public class GameTest {

	@Test
	public void testEnemyNum() {
		Game game = new Game(0);
		game.update();
		game.update();
		game.update();
		game.update();
		game.update();
		game.update();
		System.out.println(game.getEnemiesListInMap().size());
	}
    @Test
    public void testBullet(){
    	Game game = new Game(0);
    	game.update();
    	game.update();
    	game.update();
    	game.update();
		game.update();
		game.update();
		game.update();
		game.update();
		game.update();
		for(int i = 0; i<game.getEnemiesListInMap().size();i++){
			System.out.println(game.getEnemiesListInMap().get(i).getLocation()+ " " + game.getEnemiesListInMap().get(i).getHP());
		}
    	game.buildTowerA(new Point(100,80));
    	game.update();
    	game.update();
    	game.update();
    	game.update();
    	game.update();
    	game.update();
    	game.update();
    	System.out.println();
    	System.out.println();
    	System.out.println();
    	System.out.println();
    	System.out.println();
    	System.out.println();
    	System.out.println();

    	for(int i = 0; i<game.getEnemiesListInMap().size();i++){
			System.out.println(game.getEnemiesListInMap().get(i).getLocation()+ " " + game.getEnemiesListInMap().get(i).getHP());
		}
    	for(int i = 0; i<game.getBulletList().size();i++){
			System.out.println(game.getBulletList().get(i).getLocation());
		}
    }
    
    
    
    
    @Test
    public void testShoot(){
    	Game game = new Game(0);
    	System.out.println("Money1:"+game.getPlayer().getMoney());
    	game.buildTowerA(new Point(50,400));
    	System.out.println("Money1:"+game.getPlayer().getMoney());
    	game.update();
    	game.update();
    	game.update();
    	System.out.println("\n");
    	game.update();
		game.update();
		game.update();
		System.out.println("\n");
		game.update();
		game.update();
		game.update();
		System.out.println("\n");
		game.update();
		game.update();
		game.update();
		System.out.println("\n");
		game.update();
		game.update();
		game.update();
		for(int i = 0; i<game.getEnemiesListInMap().size();i++){
			System.out.println(game.getEnemiesListInMap().get(i).getLocation()+ " " + game.getEnemiesListInMap().get(i).getHP());
		}
		
//		System.out.println("\n Bullet:");
//		for(int i = 0; i<game.getBulletCollection().size();i++){
//			System.out.println(game.getBulletCollection().get(i).getLocation()+ " ");
//		}
		System.out.println("\n");
		game.update();
		game.update();
		game.update();
		System.out.println("\n");
		game.update();
		game.update();
		game.update();
		System.out.println("\n");
		for(int i = 0; i<game.getEnemiesListInMap().size();i++){
			System.out.println(game.getEnemiesListInMap().get(i).getLocation()+ " " + game.getEnemiesListInMap().get(i).getHP());
		}

//		System.out.println("\n Bullet:");
//		for(int i = 0; i<game.getBulletCollection().size();i++){
//			System.out.println(game.getBulletCollection().get(i).getLocation()+ " ");
//		}
    }
}
