package serverPackage;


public class Gobelin extends Personnage{

	public Gobelin(int x, int y) {
		super(x,y);
		this.setLife(80);
		this.setResistance(50);
		this.setStrength(50);
		this.setLuck(8);
		this.persoType = "monster";
	}
	

}
