package serverPackage;

import java.awt.event.KeyEvent;

public class Wizard extends Personnage{

	public Wizard() {
		this.setLife(50);
		this.setResistance(30);
		this.setStrength(60);
		this.setLuck(9);
		
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
