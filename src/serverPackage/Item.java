package serverPackage;

public abstract class Item {

	protected char affichage;

	private String type;
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
	
	public void setItemPosition(int[] coordXY){
		this.positionX = coordXY[0];
		this.positionY = coordXY[1];
	}
	
	public String getItemType(){
		return this.type;
	}
	public void setItemType(String type){
		this.type = type;
	}
}
