package serverPackage;

public class Food extends Item{
	private int lifeValue;
	private int attackValue;
	
	public Food(){
		this.lifeValue = 8;
		this.attackValue = 5;
		super.setItemType("food");
		affichage ='f';
	}
	
	public int[] getFoodAttribute(){
		int[] attribute = new int[2];
		attribute[0]= this.lifeValue; //life in food
		attribute[1]= this.attackValue; //attack in food
		return attribute;
	}
}
