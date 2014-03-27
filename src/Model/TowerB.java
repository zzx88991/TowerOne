package Model;


public class TowerB extends Towers {
	private boolean showable;

	public TowerB(){
		super();
		super.setPrice(80);
		super.setDamage(4);
		super.setType('B');
		super.setShootRange(200, 200);
		
	}
	

}
