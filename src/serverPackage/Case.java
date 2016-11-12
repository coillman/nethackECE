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
	
	// GETTERS
	public ArrayList<Personnage> getListePerso(){
		return listePerso;
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
	
	public void removePersos(){
		for(int i = 0; i<listePerso.size(); i++){
			listePerso.remove(i);
		}
		
	}
	
	public void addItem(Item item){
		this.item = item;
	}
	public void removeItem(){
		//remove item on the case
		this.item = null;
	}

}
