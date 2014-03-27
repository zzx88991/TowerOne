package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

//这个class已经全部重新整理，如有任何地方需要更改请通知 傲娇的！

public  class Enemies implements Showable,Serializable{

	public int MOVEMENT_PIXELS;
	private char type;
	private int HP;
	private int reward;
	private Point upperleft;
	private Rectangle currRange;
	private Point startPoint;
	private int endPoint;
	private int price;
	private int nextSteps=1;
	private Bullet object;
	
	public Enemies(){
		startPoint=new Point(0,450);
		upperleft=startPoint;
		currRange = new Rectangle(new Point(upperleft.x+25,upperleft.y+25));
	}
	
	public void changeLocation() {
		if(!this.isDead()){
		
        if(upperleft.getX()<200 && upperleft.getY()==450){
            
            upperleft.setLocation(MOVEMENT_PIXELS+upperleft.getX(), 450);
        }
        else if(upperleft.getX()==200&&upperleft.getY()<=450&&upperleft.getY()>100){
        	upperleft.setLocation(upperleft.getX(),upperleft.getY()-MOVEMENT_PIXELS);
        }
        else if(upperleft.getX()>=200&&upperleft.getX()<400&&upperleft.getY()==100){
        	upperleft.setLocation(MOVEMENT_PIXELS+upperleft.getX(),upperleft.getY());

        }
        else if(upperleft.getX()==400&&upperleft.getY()>=100&&upperleft.getY()<350){
        	upperleft.setLocation(upperleft.getX(),MOVEMENT_PIXELS+upperleft.getY());

        }
        else if(upperleft.getX()>=400&&upperleft.getX()<600&&upperleft.getY()==350){
        	upperleft.setLocation(MOVEMENT_PIXELS+upperleft.getX(),upperleft.getY());

        
        }
        else if(upperleft.getX()==600&&upperleft.getY()<=350&&upperleft.getY()>200){
        	upperleft.setLocation(upperleft.getX(),upperleft.getY()-MOVEMENT_PIXELS);

        
        }
        else if(upperleft.getX()>=600&&upperleft.getY()==200){
        	upperleft.setLocation(MOVEMENT_PIXELS+upperleft.getX(),upperleft.getY());

        
        }
        
        currRange.setLocation(new Point(upperleft.x+25,upperleft.y+25));
    }
	}
	
	public void setCurrRangeSize(int width, int height){
		currRange.setSize(width,height);
	}
	
	public void setHP(int hp) {
		HP=hp;	
	}
	
	public int getHP(){
		return HP;
	}
	
	public void getHurt(int killHP ){
		HP=HP-killHP;	
	}
	
	public boolean isDead(){
		if(HP<=0)
			return true;
		else 
			return false;
	}
	
	public int getSpeed(){
		return MOVEMENT_PIXELS;
	}
	
	public void setSpeed(int M){
		MOVEMENT_PIXELS=M;
	}
	
	public void setType(char t){
		type=t;
	}
	
	public char getType(){
		return type;
	}
	
	public void setReWard(int r){
		reward = r;
	}
	
	public int getReward(){
		return reward;
	}
	
	public Point getLocation(){
		return upperleft;	
	}

	public int getPrice(){
		return price;
	}
	
	public void setPrice(int n){
		price = n;
	}
	
	public Rectangle getRange(){
		return currRange;
	}
	
	
	public void setRange(Rectangle r){
		currRange = r;
	}
	
	public Point nextLocation(){
		Point temp = upperleft;
		for(int i=0;i<nextSteps;i++){
		this.changeLocation();
		}
		Point nextLocation = upperleft;
		upperleft=temp;
		return nextLocation;
	}

	public Point getCenterPoint() {
		// TODO Auto-generated method stub
		return new Point(upperleft.x+25,upperleft.y+25);
	}

	@Override
	public void setShowable(boolean showable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean showable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	}
