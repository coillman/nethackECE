package serverPackage;

import java.awt.event.KeyEvent;
public class Warrior extends Personnage{

	public Warrior() {
		this.setLife(50);
		this.setResistance(70);
		this.setStrength(80);
		this.setLuck(5);
	}

	
	public void move(KeyEvent key){
		
		int touche = key.getKeyCode();
		switch (touche){
			case KeyEvent.VK_UP : 
			break;
			case KeyEvent.VK_DOWN : 
			break;
			case KeyEvent.VK_LEFT :
			break;
			case KeyEvent.VK_RIGHT : 
			break;
		}
	}
	
}
