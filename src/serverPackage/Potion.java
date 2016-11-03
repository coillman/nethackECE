package serverPackage;

public class Potion extends Item {
	
	private int lifeValue;
	
	public Potion(){
		lifeValue=10;
	}
	
	public int getPotionValue(){
		return this.lifeValue;
	}
}
