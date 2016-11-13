package serverPackage;

import commonPackage.Bac;
import java.io.*;
import java.util.ArrayList;

public class Platform {

	// ATTRIBUTS------------------------------------------------------------------
	private int col = 80;
	private int lig = 24;
	private Case[][] tab = new Case[lig][col];
	private Bac monBac;
	private ArrayList<Personnage> persosOnPlat;
	private ArrayList<Personnage> monstersOnPlat;

	// CONSTRUCTEURS---------------------------------------------------------------
	public Platform() {
		for (int i = 0; i < lig; i++) {
			for (int j = 0; j < col; j++) {
				tab[i][j] = new Case();
			}
		}
		monBac = new Bac();
		persosOnPlat =new ArrayList<Personnage>();
		monstersOnPlat =new ArrayList<Personnage>();
	}

	// GETTERS---------------------------------------------------------------------
	public Bac getBac() {
		return monBac;
	}

	// METHODES--------------------------------------------------------------------
	public void loadPlateform(String levelFile) {
		/*
		 * charge la plateforme du fichier txt en paramètre dans le tableau de
		 * cases.
		 */
		try {
			FileInputStream myfile = new FileInputStream(levelFile);
			DataInputStream dis = new DataInputStream(myfile);
			for (int i = 0; i < lig; i++) {
				dis.readChar();
				for (int j = 0; j < col; j++) {
					tab[i][j].initial = dis.readChar(); // initialise l'attribut
														// "initial" de la case
														// avec le contenu du
														// fichier txt
				}
				dis.readChar();
			}

			dis.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void createView() {
		/*
		 * créé le tableau de char qui sert d'affichage en choisissant le char
		 * prioritaire à afficher
		 */
		for (int i = 0; i < lig; i++) {
			for (int j = 0; j < col; j++) {
				tab[i][j].setPriority();
				this.getBac().affichetab[i][j] = tab[i][j].affichage;
			}
		}

	}
	
	public Personnage persoIsHere(int[] attCo){
		Personnage isHere = null;
		for (Personnage perso : this.persosOnPlat) {
			if(perso.getPersoPosY() == attCo[0] && perso.getPersoPosX() == attCo[1]){
				isHere = perso;
			}
		}
		for (Personnage perso : this.monstersOnPlat) {
			if(perso.getPersoPosY() == attCo[0] && perso.getPersoPosX() == attCo[1]){
				isHere = perso;
			}
		}
		return isHere;
	}
	
	public Item itemIsHere(int[] itmCo){
		Item itemThere = null;
		itemThere = tab[itmCo[0]][itmCo[1]].getItem();
		tab[itmCo[0]][itmCo[1]].removeItem(); //remove item from map
		return itemThere; 
	}
	
	public void placeItems(){
		Potion elixir = new Potion();
		Armure shield = new Armure();
		Food apple = new Food();
		Weapon wand = new Weapon();
		Weapon sword = new Weapon();
		this.tab[2][5].addItem(elixir);
	}
	
	public void placeMonsters(){
		Orc bossMonster=new Orc(7,9);
		Gobelin smallFry = new Gobelin(5,3);
		this.tab[bossMonster.getPersoPosY()][bossMonster.getPersoPosX()].addPerso(bossMonster);
		this.tab[smallFry.getPersoPosY()][smallFry.getPersoPosX()].addPerso(smallFry);
	}

	public void placePersos() {
		/*
		 * parcours la liste de personnages et les places dans les cases de la
		 * map
		 */
		for (Personnage perso : this.persosOnPlat) {
			if(!tab[perso.getPersoPosY()][perso.getPersoPosX()].getListePerso().contains(perso)){
				this.tab[perso.getPersoPosY()][perso.getPersoPosX()].addPerso(perso);
			}
		}
		for (Personnage perso : this.monstersOnPlat) {
			if(!tab[perso.getPersoPosY()][perso.getPersoPosX()].getListePerso().contains(perso)){
				this.tab[perso.getPersoPosY()][perso.getPersoPosX()].addPerso(perso);
			}
		}
	}
	
	public int[] getSortieCo(){
		int[] coo = new int[2];
		for (int i = 0; i < lig; i++) {
			for (int j = 0; j < col; j++) {
				if(tab[i][j].initial == '>'){
					//System.out.println("trouvé");
					coo[0]=i;
					coo[1]=j;
				}
			}
		}
		//System.out.println(coo[0] + coo[1]);
		return coo;
	}

	public void addPersos(int nb) {
		/*
		 * ajoute le perso (Warrior) à la liste persosOnPlat
		 */
		for(int i=0; i<nb; i++){
			persosOnPlat.add(new Warrior(getSortieCo()[1], getSortieCo()[0]));
			//persosOnPlat.add(new Warrior(2,5));
		}
	}

	public void apply(String action, int idperso) {
		/*
		 * en fonction de action, appel la bonne méthode
		 */
		String[] parts = action.split(":");
		String category = parts[0]; //move , use , pick
		String argument = parts[1]; //up  , potion 
		
		/*Personnage perso = persosOnPlat.get(idperso);
		
		if (perso.persoType == "wizard"){
			Wizard persoWiz = (Wizard)perso;
		}
		else if(perso.persoType =="warrior"){
			Warrior persoWar = (Warrior)perso;
		}*/
		//for(Personnage perso : persosOnPlat) {} 
 		    		
		
		switch(category) {
	        case "move" :
	        	/** move Perso block **/
	        	move(idperso, argument);
		        break;
	        	
	        case "pick" :
	        	/** pick Item block **/
	        	pickItem(idperso);
	        	break;
	        	
	        case "use" :
	        	/** use item block **/
	        	useItemOnplayer(idperso, argument);
	        	break;
	        
	        case "show" :
	        	/** show player items list **/
	        	break;
	        	
	        case "attack" :
	        	/** attack opponent block **/
	        	attackPos(idperso, argument);
	        	break;
	        	
	        case "quit" :    	break;	        
	        default 	:     	System.out.println("Invalid command");
		}

		
	}
	
	public void pickItem(int idperso){
		int[] coo = new int[2];
		coo[0] = persosOnPlat.get(idperso).getPersoPosY();
		coo[1] = persosOnPlat.get(idperso).getPersoPosX();
		Item temp = itemIsHere(coo);
		if(temp !=null){
			
			Personnage perso = persosOnPlat.get(idperso);
			if (persosOnPlat.get(idperso).persoType == "wizard"){ 
				Wizard persoWiz = (Wizard)perso;
				persoWiz.pickItem(temp);
			}
			else if(persosOnPlat.get(idperso).persoType =="warrior"){ 
				Warrior persoWar = (Warrior)perso;
				persoWar.pickItem(temp);
			}		
		}
	}
	
	public void useItemOnplayer(int idperso, String item ){
		Personnage perso = persosOnPlat.get(idperso);
		
		if (persosOnPlat.get(idperso).persoType == "wizard"){ 
			Wizard persoWiz = (Wizard)perso;
			persoWiz.use(persoWiz.getItemFromName(item));
		}
		else if(persosOnPlat.get(idperso).persoType =="warrior"){ 
			Warrior persoWar = (Warrior)perso;
			persoWar.use(persoWar.getItemFromName(item));
		}	
	}
	
	public void attackPos(int idperso, String dir){
		int[] coo = new int[2];
		coo = dirToCo(dir);
		int[] attCo = new int[2];
		attCo[0] = persosOnPlat.get(idperso).getPersoPosY() + coo[0];
		attCo[1] = persosOnPlat.get(idperso).getPersoPosX() + coo[1];
		if(persoIsHere(attCo) != null){
			persosOnPlat.get(idperso).attack(persoIsHere(attCo));
		}
	}
	
	
	public void move(int idperso, String dir){
		int[] coo = new int[2];
		coo = dirToCo(dir);
		int[] nextCo = new int[2];
		nextCo[0] = persosOnPlat.get(idperso).getPersoPosY() + coo[0];
		nextCo[1] = persosOnPlat.get(idperso).getPersoPosX() + coo[1];
		if (isMovePossible(nextCo[0],nextCo[1])){
			tab[persosOnPlat.get(idperso).getPersoPosY()][persosOnPlat.get(idperso).getPersoPosX()].removePersos();
			persosOnPlat.get(idperso).setPersoPosY(nextCo[0]);
			persosOnPlat.get(idperso).setPersoPosX(nextCo[1]);
		}
		tab[persosOnPlat.get(idperso).getPersoPosY()][persosOnPlat.get(idperso).getPersoPosX()].removePersos();
	}

	public int[] dirToCo(String dir){
		int[] coo = new int[2];
		switch (dir){
		case "up":
			coo[0] = -1;
			coo[1] = 0;
			break;
		case "down":
			coo[0] = 1;
			coo[1] = 0;
			break;
		case "left":
			coo[0] = 0;
			coo[1] = -1;
			break;
		case "right":
			coo[0] = 0;
			coo[1] = 1;
			break;
		}
		return coo;
	}
	
	public boolean isMovePossible(int li, int co) {
		/*
		 * condition sur les cases qui ne permettent pas le déplacement
		 */
		boolean ispossible = true;
		if(tab[li][co].affichage == '-' || tab[li][co].initial == '|' || tab[li][co].initial == ' '){
			
			ispossible = false;
		}
		for (Personnage perso : persosOnPlat){
			if(perso.getPersoPosY() == li && perso.getPersoPosX() == co && tab[li][co].initial != '>' && tab[li][co].initial != '<'){
				ispossible = false;
			}
		}
		
		return ispossible;
		
	}
	

}
