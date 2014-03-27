package Model;
import java.awt.Point;
import java.awt.Rectangle;


public class Bullet implements Showable{
	private Towers tower;
	private Enemies enemy;
	private Point startPoint;
	private Rectangle range;
	private Point upperleft;
	private int MOVEMENT_PIXELS=7;
	private boolean showable = true;
	private boolean intersect =false;
	public Bullet(Towers t,Enemies e){
		tower=t;
		enemy=e;
		range=new Rectangle(2,2);
		startPoint=new Point(tower.getLocation().x+25,tower.getLocation().y+25);
	    upperleft=startPoint;
		range.setLocation(upperleft);
	}
	
	public Point getLocation(){
		return upperleft;
	}
	
	public Rectangle getRange(){
		return range;
	}
	public Towers getTower(){
		return tower;
	}
	public Enemies getTarget(){
		return enemy;
	}
	
	public boolean hasmet(){
		
		return intersect;

		
	}
	
	public void changeLocation(){
		int dis =(int) upperleft.distance(new Point(enemy.getLocation().x,enemy.getLocation().y));

		//Point temp = new Point(upperleft.x+(enemy.getLocation().x-upperleft.x)*(MOVEMENT_PIXELS/((int)(upperleft.distance(enemy.getLocation())-upperleft.distance(enemy.getLocation())%1))),upperleft.y+(enemy.getLocation().y-upperleft.y)*(MOVEMENT_PIXELS/((int)(upperleft.distance(enemy.getLocation())-upperleft.distance(enemy.getLocation())%1))));
		if(this.getRange().contains(new Point(enemy.getLocation().x+22,enemy.getLocation().y+25))||dis==0)
			intersect = true;
		if(!hasmet()){
		Point temp = new Point(upperleft.x+MOVEMENT_PIXELS*(enemy.getLocation().x-upperleft.x)/dis,upperleft.y+MOVEMENT_PIXELS*(enemy.getLocation().y-upperleft.y)/dis);
		upperleft = temp;
//		System.out.println("The position of bullet:"+upperleft.x+","+upperleft.y);
//		System.out.println("The position of enemy:"+enemy.getLocation().x+","+enemy.getLocation().y);
		range.setLocation(upperleft);
		}
		
	}

	@Override
	public void setShowable(boolean showable) {
		// TODO Auto-generated method stub
		this.showable = showable;
	}

	@Override
	public boolean showable() {
		return showable;
	}
}
