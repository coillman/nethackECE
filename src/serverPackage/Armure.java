package serverPackage;

public class Armure extends Item{
	private int armureResistance;
	
	public Armure(){
		this.armureResistance = 15;
	}
	
	public int getArmureRes(){
		return this.armureResistance;
	}
}
