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

	// CONSTRUCTEURS---------------------------------------------------------------
	public Platform() {
		for (int i = 0; i < lig; i++) {
			for (int j = 0; j < col; j++) {
				tab[i][j] = new Case();
			}
		}
		monBac = new Bac();
		persosOnPlat =new ArrayList<Personnage>();
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
		return isHere;
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
		String argument = parts[1]; //up , toto , potion 
		
		Personnage perso = persosOnPlat.get(idperso);
		if (perso.persoType == "wizard"){
			perso = (Wizard)perso;
		}
		else if(perso.persoType =="warrior"){
			perso = (Warrior)perso;
		}
		//for(Personnage perso : persosOnPlat) {} 
 		    		
		
		switch(category) {
	        case "move" :
	        	/** move Perso block **/
	        	move(idperso, argument);
		        break;
	        	
	        case "pick" :
	        	/** pick Item block **/

	        	break;
	        	
	        case "use" :
	        	/** use item block **/
	        	
	        	break;
	        	
	        case "attack" :
	        	/** attack opponent block **/
	        	attackPos(idperso, argument);
	        	break;
	        	
	        case "quit" :    	break;	        
	        default 	:     	System.out.println("Invalid command");
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
