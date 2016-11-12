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

	public void placePersos() {
		/*
		 * parcours la liste de personnages et les places dans les cases de la
		 * map
		 */
		for (Personnage perso : this.persosOnPlat) {
			this.tab[perso.getPersoPosY()][perso.getPersoPosX()].addPerso(perso);
		}
	}
	
	public int[] getSortieCo(){
		int[] coo = new int[2];
		for (int i = 0; i < lig; i++) {
			for (int j = 0; j < col; j++) {
				if(tab[i][j].initial == '>'){
					coo[0]=lig;
					coo[1]=col;
				}
			}
		}
		return coo;
	}

	public void addPersos(int nb) {
		/*
		 * ajoute le perso (Warrior) à la liste persosOnPlat
		 */
		for(int i=0; i<nb; i++){
			persosOnPlat.add(new Warrior(getSortieCo()[1], getSortieCo()[0]));
		}
	}

	public void apply(String action, int idperso) {
		/*
		 * en fonction de action, appel la bonne méthode
		 */
		
	}
	
	
	public void move(int idperso, String dir){
		int[] coo = new int[2];
		coo = dirToCo(dir);
		int[] nextCo = new int[2];
		nextCo[0] = persosOnPlat.get(idperso).getPersoPosY() + coo[0];
		nextCo[1] = persosOnPlat.get(idperso).getPersoPosX() + coo[1];
		if (isMovePossible(nextCo[0],nextCo[1])){
			persosOnPlat.get(idperso).setPersoPosY(nextCo[0]);
			persosOnPlat.get(idperso).setPersoPosX(nextCo[1]);
		}
	}

	public int[] dirToCo(String dir){
		int[] coo = new int[2];
		switch (dir){
		case "up":
			coo[0] = 0;
			coo[1] = 1;
			break;
		case "down":
			coo[0] = 0;
			coo[1] = -1;
			break;
		case "left":
			coo[0] = -1;
			coo[1] = 0;
			break;
		case "right":
			coo[0] = 1;
			coo[1] = 0;
			break;
		}
		return coo;
	}
	
	public boolean isMovePossible(int lig, int col) {
		/*
		 * condition sur les cases qui ne permettent pas le déplacement
		 */
		boolean ispossible = true;
		if(tab[lig][col].affichage == '-' | tab[lig][col].initial == '|'){
			
			ispossible = false;
		}
		for (Personnage perso : persosOnPlat){
			if(perso.getPersoPosY() == lig && perso.getPersoPosX() == col && tab[lig][col].initial != '>' && tab[lig][col].initial != '<'){
				ispossible = false;
			}
		}
		
		return ispossible;
		
	}
	

}
