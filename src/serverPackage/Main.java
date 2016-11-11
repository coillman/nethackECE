package serverPackage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import serverPackage.Platform;

public class Main {
	
	
	public static void main(String[] args) {
		
		/*
		 * try{ Server monserv = new Server(18000); monserv.launchServer(); }
		 * catch(IOException e){ }
		 */
/*
		Platform mamap = new Platform();
		mamap.loadPlateform("Plateforme/level1.txt");
		mamap.createView();
		/*
		 * // affichage test for(int i=0; i<24; i++){
		 * System.out.println(mamap.getBac().affichetab[i]); }
		 */
/*
		try {
			Server monserv = new Server(18000);
			monserv.launchServer(mamap);
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
		
		  //test kevin
		  
		  Wizard gandalf = new Wizard(); 
		  Gobelin gob = new Gobelin(); 
		  
		  Food theFood = new Food();
		  Weapon hache = new Weapon();
		  
		  Potion blue = new Potion();
		  
		  
		  System.out.println(gandalf.getLife());
		  System.out.println(gob.getLife());
		  
		  gandalf.use(blue); gandalf.attack(gob);
		  
		  System.out.println(gandalf.getLife());
		  System.out.println(gob.getLife());
		  
		  gandalf.pickItem(theFood);
		  gandalf.pickItem(blue);
		  
		  gandalf.showItemList();
		  
		  gandalf.use(theFood);
		  gandalf.pickItem(hache);
		  
		  gandalf.showItemList();
		  
		  System.out.println(gandalf.getLife());
		  System.out.println(gob.getLife());
		 
		
		
//-------------------------------------------------------------------------------------------
	/*	**** 11/11 2016 *********
	 * 
		// déclaration des varibales 
		boolean levelup = false;
		int level = 0;
		Server monserv;
		int whoseTurn =0;
		int port;
		int nbJoueur;
		String action;
		
		//demande du numéro de port à l'utilisatreur
		System.out.println("numero de port :");
		Scanner sc1 = new Scanner(System.in);
		port = sc1.nextInt();
		sc1.close();
		
		//demande du nombre de joueurs de la partie
		System.out.println("nombre de joueurs :");
		Scanner sc2 = new Scanner(System.in);
		nbJoueur = sc2.nextInt();
		sc2.close();
		
		//ouverture du server et attente de la connexion des joueurs
		monserv = new Server(port);
		System.out.println("attente des joueurs...");
		monserv.acceptCo(nbJoueur);

		System.out.println("la partie peut commencer !");
		
		// instantiation de la plateformes de jeux
		Platform mamap = new Platform();
		
		
		//boucle de jeu
		
		while(level>0){
			//remplissage de la map avec le fichier en paramètre
			mamap.loadPlateform("Plateforme/level1.txt");
			
			
			while(!levelup){
				mamap.createView();
				monserv.spread(mamap, whoseTurn);
				action = monserv.listen(whoseTurn);
				
				
			}
		}
		
		********** fin 11/11/2016 ****************
		*/
	}

}
