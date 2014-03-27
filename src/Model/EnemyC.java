package Model;


public class EnemyC extends Enemies{
	private boolean showable =true;

	public EnemyC( ) {
		super();
		super.setType('C');
		super.setReWard(30);
		super.setHP(60);
		super.setPrice(30);
		super.setSpeed(10);
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
