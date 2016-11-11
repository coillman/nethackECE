package serverPackage;

public class Armure extends Item{
	private int armureResistance;
	
	public Armure(){
		this.armureResistance = 15;
		super.setItemType("armure");
	}
	
	public int getArmureResistance(){
		return this.armureResistance;
	}
}
