package Model;


public class Player {
	
	private int money;
	private String name;
	private int headquater;
	private int numOfTowerA;
	private int numOfTowerB;
	private int numOfTowerC;
	private int numOfUnitA;
	private int numOfUnitB;
	private int numOfUnitC;
	private int HP;
	
	public static final int Default_Amount_Money = 200; 


	//build up constructor with player's information;	
	public Player( ) {
		money= Default_Amount_Money;
		name = null;
		numOfTowerA = 0;
		numOfTowerB = 0;
		numOfTowerC = 0;
		numOfUnitA = 0;
		numOfUnitB = 0;
		numOfUnitC = 0;
		
	}
	
	public void setMoney(int AmountOfMoney){
		this.money = AmountOfMoney;
	}
	
	public int getMoney(){
		return money;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void countTowerA(){
		numOfTowerA+=1;
	}
	
	public int getNumofTowerA(){
		return  numOfTowerA;
	}
	
	public void countTowerB(){
		numOfTowerB+=1;
	}
	
	public int getNumofTowerB(){
		return  numOfTowerB;
	}
	
	public void countTowerC(){
		numOfTowerC+=1;
	}
	
	public int getNumofTowerC(){
		return  numOfTowerC;
	}
	
	public void countUnitA(){
		numOfUnitA++;
	}
	
	public int getNumofUnitA(){
		return  numOfUnitA;
	}
	
	public void countUnitB(){
		numOfUnitB++;
	}
	
	public int getNumofUnitB(){
		return  numOfUnitB;
	}
	
	public void countUnitC(){
		numOfUnitC++;
	}
	
	public int getNumofUnitC(){
		return  numOfUnitC;
	}
	/*public void addTower(char type) {
		if(type =='A')
			numOfTowerA++;
		if(type =='B')
			numOfTowerB++;
		
		if(type =='C')
			numOfTowerC++;
		
	}
	public void addUnit(char type) {
		if(type =='A')
			numOfUnitA++;
		if(type =='B')
			numOfUnitB++;
		
		if(type =='C')
			numOfUnitC++;
		
	}*/
	
}