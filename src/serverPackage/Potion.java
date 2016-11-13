package serverPackage;

public class Potion extends Item {
	
	private int luckBonus;
	
	public Potion(){
		this.luckBonus=1;
		super.setItemType("potion");
		affichage ='p';
	}
	
	public int getPotionLuck(){
		return this.luckBonus;
	}

	
	
}
