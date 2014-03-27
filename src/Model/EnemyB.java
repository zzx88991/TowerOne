package Model;


public class EnemyB extends Enemies {
	private boolean showable =true;

	public EnemyB( ) {
		super();
		super.setType('B');
		super.setReWard(20);
		super.setHP(40);
		super.setPrice(20);
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
