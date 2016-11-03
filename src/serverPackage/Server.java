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
			if(listSoc.indexOf(soc)==idJoueur){
				mamap.getBac().monTour=true;
				sendBac(mamap, soc);
				mamap.getBac().monTour=false;
			}
			else{
				sendBac(mamap, soc);
			}
		}
	}
	
	public void sendBac(Platform mamap, Socket soc){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
			oos.writeObject(mamap.getBac());
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String listen(int idJoueur){
		Bac monbac = new Bac();
		try{
		InputStream stream = listSoc.get(idJoueur).getInputStream();
		ObjectInputStream ois = new ObjectInputStream(stream);
		monbac = (Bac) ois.readObject();
		ois.close();
		
		}catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return monbac.action;
	}
	
	/*
	
	public void launchServer(Platform mamap) {
		System.out.println("hello");

		try {
			Socket comm = sersoc.accept();
/*
			ObjectOutputStream oos = new ObjectOutputStream(comm.getOutputStream());
			oos.writeObject(mamap.getBac());
			oos.close();

			System.out.println("sent");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}*/
}
