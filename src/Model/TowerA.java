package Model;
import java.awt.Rectangle;


public class TowerA extends Towers {
	private boolean showable;

	public TowerA() {
		super();
		super.setPrice(50);
		super.setDamage(2);
		super.setType('A');
		super.setShootRange(150, 150);
	}

}
