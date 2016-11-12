package serverPackage;

public class Potion extends Item {
	
	private int lifeBonus;
	private int attackBonus;
	
	public Potion(){
		this.lifeBonus=10;
		this.attackBonus=7;
		super.setItemType("potion");
	}
	
	public int getPotionLife(){
		return this.lifeBonus;
	}
	public int getPotionAttack(){
		return this.attackBonus;
	}
	
	
}
