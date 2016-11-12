package serverPackage;

import java.util.ArrayList;

public class Case {

	// ATTRIBUTS
	private ArrayList<Personnage> listePerso;
	private Item item;
	protected char initial;
	protected char affichage;

	// CONSTRUCTEURS
	public Case() {
		listePerso = new ArrayList<Personnage>();
		item = null;
		initial = 'x';
		affichage = 'x';
	}

	// METHODES
	public void setPriority() {
		/*
		 * defifnit le charactère à afficher chez le client
		 */
		if ((listePerso.isEmpty()) != true) {
			affichage = listePerso.get(listePerso.size() - 1).affichage;
		} else if (item != null) {
			affichage = item.affichage;
		} else {
			affichage = initial;
		}
	}
	
	public void addPerso(Personnage perso){
		listePerso.add(perso);
	}

}
