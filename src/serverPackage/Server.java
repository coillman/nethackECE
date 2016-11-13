package serverPackage;

import java.net.*;
import java.util.ArrayList;

import commonPackage.Bac;

import java.io.*;

public class Server {
	private int port;
	private ServerSocket sersoc;
	protected ArrayList<Socket> listSoc;	

	public Server(int port){
		try{
		this.port = port;
		listSoc = new ArrayList<Socket>();
		sersoc = new ServerSocket(port);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void acceptCo(int n){
		/*
		 * dans une boucle de taille n, stock les sockets d'entrée 
		 * dans une ArrayList attribut de Server (listSock)
		 */
		try{
		for(int i=0; i<n; i++){
			listSoc.add(sersoc.accept());
		}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void spread(Platform mamap, int idJoueur) {
		/*
		 * envoie le Bac de la plateforme en paramètre
		 */
		for (Socket soc : listSoc) {
			//if(mamap.persoIsAlive(listSoc.indexOf(soc))){
				int id = listSoc.indexOf(soc);
				Bac monbac = mamap.createPersoBac(id);
				if(id == idJoueur && mamap.persoIsAlive(idJoueur)){
					monbac.monTour=true;
					sendBac(monbac, soc);
				}else{
					monbac.monTour=false;
					sendBac(monbac, soc);
				}
			//}
		}
	}
	
	public void sendBac(Bac monbac, Socket soc){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
			oos.writeObject(monbac);
			//oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String listen(Platform mamap, int idJoueur){
		Bac bacIn = new Bac();
		if (mamap.persoIsAlive(idJoueur)) {
			try {
				ObjectInputStream ois = new ObjectInputStream(listSoc.get(idJoueur).getInputStream());
				bacIn = (Bac) ois.readObject();
				// ois.close();

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return bacIn.action;
	}
	
	
	public void closeAll(){
		for (Socket soc : listSoc) {
			try{
				soc.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		try{
			sersoc.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
