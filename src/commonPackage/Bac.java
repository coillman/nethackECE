package commonPackage;


import java.awt.event.KeyEvent;
import java.io.Serializable;


public class Bac implements Serializable {

	// ATTRIBUTS------------------------------------------------------------------
	private static final long serialVersionUID = 1L; // par défaut, c'est quoi ?

	private transient int col = 80;
	private transient int lig = 24;
	public char[][] affichetab = new char[lig][col]; // vue cote client, seul
	public String message;
	public String action;
	public String items;
	public boolean monTour=false;

	// CONSTRUCTEURS---------------------------------------------------------------
	public Bac() {
		for (int i = 0; i < lig; i++) {
			for (int j = 0; j < col; j++) {
				affichetab[i][j] = 'x';
			}
		}

	}
}
