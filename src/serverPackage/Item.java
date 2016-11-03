package serverPackage;

public abstract class Item {

	protected char affichage;

	private String type;
	private String attribute;
	private int positionX;
	private int positionY;

	public Item() {

	}

	public int[] getItemPosition() {
		int[] position = new int[1];
		position[0] = this.positionX;
		position[1] = this.positionY;
		return position;
	}

}
