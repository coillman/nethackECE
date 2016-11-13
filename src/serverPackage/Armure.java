package serverPackage;

public class Armure extends Item{
	private int armureResistance;
	
	public Armure(){
		this.armureResistance = 15;
		super.setItemType("armure");
		affichage ='s';
	}
	
	public int getArmureResistance(){
		return this.armureResistance;
	}
}
