package serverPackage;

import java.util.ArrayList;
import java.util.Iterator;

public class Warrior extends Personnage{

	private ArrayList<Item> inventory;
	
	public Warrior(int x, int y) {
		super(x, y);
		this.setLife(10);
		this.setResistance(70);
		this.setStrength(80);
		this.setLuck(5);
		this.persoType = "warrior";
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
	
	public ArrayList<Item> getInventoryList() {
		//create an ArrayList of String to pass to the BAC	
		ArrayList<Item>inventoryList = new ArrayList<Item>();
		for (Iterator<Item> it = inventory.iterator(); it.hasNext(); ) {
		    Item item = it.next();
		    inventoryList.add(item);
		}
		return inventoryList;
	}
	
	public Item getItemFromName(String itemName){
		Item item = null;
		for (Iterator<Item> it = inventory.iterator(); it.hasNext(); ) {
		    Item itemtemp = it.next();
		    //check if it's in inventory
		    if (itemtemp.getItemType().equals(itemName)) {
		    	//create the adequate Item
		    	item =itemtemp;
		    } 
		}
		return item;	
	}
	
	public void use(Item item){
		String itemtype =item.getItemType(); 
		switch (itemtype){
			case "potion" 	:this.use((Potion)item);	break;
			case "food" 	:this.use((Food)item);	break;
			case "weapon" 	:this.use((Weapon)item);	break;
			case "armure" 	:this.use((Armure)item);	break;
		}
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
		setMessage("You took a " + item.getItemType());
		System.out.println(getMessage());
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
	
	//moves
	public void move(String key){}
	
}
