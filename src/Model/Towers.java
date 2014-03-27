package Model;


import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class  Towers implements Showable{

	private Enemies target;
	private int damage;
	private char type;
	private int price;
	private int shootWidth;
	private int shootHeight;
	private Point upperleft;
	private ArrayList<Rectangle> acceptArea;
	private Rectangle range;
	private Rectangle shootRange;
	private Rectangle bullet;
	private Point upperleftBullet;
	private boolean showable;
	private ArrayList<Rectangle> road;

	public Towers() {
		range = new Rectangle(50,50);
		target=null;
		shootRange = new Rectangle();
		upperleft = new Point();
		showable = false;
		road = new ArrayList<Rectangle>();
		road.add(new Rectangle(0, 450, 250, 50));
		road.add(new Rectangle(200, 100, 50, 350));
		road.add(new Rectangle(250, 100, 150, 50));
		road.add(new Rectangle(400, 100, 50, 300));
		road.add(new Rectangle(450,350,200,50));
		road.add(new Rectangle(600,200,50,150));
		road.add(new Rectangle(650,200,150,50));
	}
	
	public void setDamage(int Damage) {
		this.damage = Damage;
	}

	public void setShootRange(int width,int height){
		shootWidth=width;
		shootHeight=height;
		shootRange.setSize(shootWidth,shootHeight);
	}
	
	public boolean setLocation(int x, int y){
		for (int i = 0; i < road.size(); i++) {
			if (road.get(i).contains(
					new Rectangle(x, y, range.width, range.height))) {
				return false;
			}
		}
		upperleft.x = x - x % 50;
		upperleft.y = y - y % 50;
		range.setLocation(upperleft);
		shootRange.setLocation(upperleft.x - (shootRange.width - range.width)
				/ 2, upperleft.y - (shootRange.height - range.height) / 2);
		return true;
	}

	public void setTarget(Enemies t){
		target=t;
	}
	
	public Enemies getTarget(){
		return target;
	}
	
	public void setType(char Type) {
		this.type = Type;
	}
	
	public int getType() {
		return type;
	}

	public Point getLocation(){
		return upperleft;
	}

	

	public void setPrice(int p){
		price=p;
	}
	
	public Rectangle getRange(){
		return range;
	}
	
	public int getPrice(){
		return price;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public Rectangle getShootRange(){
		return shootRange;
	}

	@Override
	public void setShowable(boolean showable) {
		this.showable = showable;
	}

	@Override
	public boolean showable() {
		// TODO Auto-generated method stub
		return showable;
	}
	
	

}
