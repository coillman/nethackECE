package serverPackage;


public class Gobelin extends Personnage{

	public Gobelin(int x, int y) {
		super(x,y);
		this.setLife(40);
		this.setResistance(50);
		this.setStrength(50);
		this.setLuck(8);
		this.persoType = "monster";
		affichage = 'G';
	}
	

}
