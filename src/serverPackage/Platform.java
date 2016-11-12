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
			this.tab[perso.getPersoPosX()][perso.getPersoPosY()].addPerso(perso);
		}
	}

	public void addPerso(Personnage perso) {
		/*
		 * ajoute le perso à la liste persosOnPlat
		 */
	}

	public void apply(String action, int idperso) {
		/*
		 * en fonction de action, appel la bonne méthode
		 */
		

	}

	public boolean isMovePossible(int cooX, int cooY) {
		/*
		 * condition sur les cases qui ne permettent pas le déplacement
		 */
		boolean ispossible = true;
		if(tab[cooX][cooY].affichage == '-' | tab[cooX][cooY].initial == '|'){
			
			ispossible = false;
		}
		for (Personnage perso : persosOnPlat){
			if(perso.getPersoPosX() == cooX && perso.getPersoPosY() == cooY && tab[cooX][cooY].initial != '>' && tab[cooX][cooY].initial != '<'){
				ispossible = false;
			}
		}
		
		return ispossible;
		
	}

}
