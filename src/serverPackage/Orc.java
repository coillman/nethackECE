package serverPackage;


public class Orc extends Personnage{

	public Orc(int x, int y) {
		super(x,y);
		this.setLife(90);
		this.setResistance(90);
		this.setStrength(40);
		this.setLuck(3);
		this.persoType = "monster";
		affichage = 'M';
	}


}
