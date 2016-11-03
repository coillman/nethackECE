package serverPackage;

import java.awt.event.KeyEvent;

public class Orc extends Personnage{

	public Orc() {
		this.setLife(90);
		this.setResistance(90);
		this.setStrength(40);
		this.setLuck(3);
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
