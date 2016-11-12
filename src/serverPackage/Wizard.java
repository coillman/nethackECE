package serverPackage;

import java.util.ArrayList;
import java.util.Iterator;

public class Wizard extends Personnage{
	
	private ArrayList<Item> inventory;
	
	public Wizard(int x, int y) {
		super(x, y);
		this.setLife(50);
		this.setResistance(30);
		this.setStrength(60);
		this.setLuck(9);
		this.persoType = "wizard";
		this.inventory = new ArrayList<Item>();
		affichage = '@';
	}

	
	// Item management
	
	public ArrayList<String> getAllItem() {
		//create an ArrayList of String to pass to the BAC	
		ArrayList<String>inventoryString = new ArrayList<String>();
		for (Iterator<Item> it = inventory.iterator(); it.hasNext(); ) {
		    Item item = it.next();
		    inventoryString.add(item.getItemType());
		}
		return inventoryString;
	}

	public void deleteItem(String itemtoDel) {
		//remove the item trough iterator
		for (Iterator<Item> it = inventory.iterator(); it.hasNext(); ) {
		    Item item = it.next();
		    if (item.getItemType().equals(itemtoDel)) {
		       it.remove(); //remove the item
		    }
		}
	}

	public void pickItem(Item item) {
		System.out.println("You took: " + item.getItemType());
		inventory.add(item);
	}

	public void showItemList(){
		System.out.println("Your Items:");
		System.out.println("------------");
		for (Iterator<Item> it = inventory.iterator(); it.hasNext(); ) {
		    Item item = it.next();
		    System.out.println(item.getItemType());
		}
	}
	
	public void use(Potion potion) {
		this.setLife(this.getLife() + potion.getPotionLife());
		this.deleteItem("potion");
	}

	public void use(Food food) {
		int life, attack;
		int[] attr = food.getFoodAttribute();
		life = attr[0];
		attack = attr[1];
		this.setLife(this.getLife() + life);
		this.setStrength(this.getStrength() + attack);
		this.deleteItem("food");
	}
	
	
	public void use(Armure armure){
		int resistance = armure.getArmureResistance();
		this.setResistance(this.getResistance()+resistance);
		this.deleteItem("armure");
	}

	public void use(Weapon arme){
		int attackInc = arme.getAttackIncrease();
		this.setStrength(this.getStrength()+attackInc);
		this.deleteItem("arme");
	}
	
	// moves
	public void move(String key){}
}
