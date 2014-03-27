package Model;


public class EnemyA extends Enemies {
	private boolean showable =true;
	public EnemyA( ) {
		super();
		super.setType('A');
		super.setReWard(10);
		super.setHP(20);
		super.setPrice(10);
		super.setSpeed(5);
		super.setCurrRangeSize(5, 5);
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
