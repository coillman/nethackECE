
package serverPackage;

import java.util.ArrayList;
import java.lang.Math;

public abstract class Personnage {

	private int life;
	private int strength;
	private int luck;
	private int resistance;
	
	private int posX;
	private int posY;
	
	private int id;
	
	private ArrayList<String> listItem;
	protected char affichage;

	

	public Personnage() {
		// default value
		/*
		 * this.life =50 ; this.strength= 50; this.luck = 6; this.resistance =
		 * 50;
		 * 
		 * this.listItem.add("");
		 */
	}

	// getter and setters
	public int getLife() {
		return this.life;
	}

	public void setLife(int newLife) {
		this.life = newLife;
	}
	
	/* getter and setter position */
	public int getPersoPosX() {
		return this.posX;
	}
	public void setPersoPosX(int newPosX) {
		this.posX= newPosX;
	}
	public int getPersoPosY() {
		return this.posY;
	}
	public void setPersoPosY(int newPosY) {
		this.posY= newPosY;
	}
	
	/* getter and setter Luck */
	public int getLuck() {
		return this.luck;
	}

	public void setLuck(int newluck) {
		this.luck = newluck;
	}

	public int getResistance() {
		return this.resistance;
	}

	public void setResistance(int newResistance) {
		this.resistance = newResistance;
	}

	public int getStrength() {
		return this.strength;
	}

	public void setStrength(int newStrength) {
		this.strength = newStrength;
	}

	public ArrayList<String> getAllItem() {
		return this.listItem;
	}

	public void addNewItem(String newItem) {
		listItem.add(newItem);
	}

	public void deleteItem(String itemtoDel) {
		// ???
	}

	// methods to implement

	public void attack(Personnage target) {
		int damage = 0;
		float factor = ((float) this.getLuck() / 12);
		damage = ((int) (this.getStrength() * (Math.random() + factor)) - target.getResistance());
		System.out.println("damage: " + damage);
		if (damage >= 0) {
			int targetNewLife = target.getLife() - damage;
			if (targetNewLife <= 0) {
				target.die();
			} else {
				target.setLife(targetNewLife);
			}
		} else {
			System.out.println("attack not effective!");
		}

	}

	public void die() {
		this.setLife(0);
		System.out.println("you're dead!");

	}

	public void dropItem() {

	}

	public void pickItem() {

	}

	public void use(Potion potion) {
		this.setLife(this.getLife() + potion.getPotionValue());
	}

	public void use(Food food) {
		int life, attack;
		int[] attr = food.getFoodAttribute();
		life = attr[0];
		attack = attr[1];
		this.setLife(this.getLife() + life);
		this.setStrength(this.getStrength() + attack);
	}

	public void move(String key) {
	}

}
