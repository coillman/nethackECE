package serverPackage;

public class Weapon extends Item{
	private int attackIncrease;
	
	public Weapon(){
		this.attackIncrease = 18;
		super.setItemType("weapon");
		affichage ='a';
	}
	
	public int getAttackIncrease(){
		return this.attackIncrease;
	}
}
