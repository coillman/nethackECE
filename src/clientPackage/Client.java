package clientPackage;

import commonPackage.Bac;

import java.net.*;
import java.io.*;
//import java.util.*;

public class Client {
	private Socket comm;
	private int port;
	private String host;
	
	

	public Client(String host, int port) {
		this.port = port;
		this.host = host;
		try{
			comm = new Socket(host, port);
		} catch (IOException r) {
			r.printStackTrace();
		}
	}
	
	
	
	public void sendRequest(Bac bacOut){
		System.out.println("info to send to the server:" + bacOut.action);
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(comm.getOutputStream());
			oos.writeObject(bacOut);
			//oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}


// code pour la reception de données
/*
try {
	comm = new Socket(host, port);

	InputStream stream = comm.getInputStream();
	ObjectInputStream ois = new ObjectInputStream(stream);
	Bac monbac = (Bac) ois.readObject();
	ois.close();

	for (int i = 0; i < 24; i++) {
		System.out.println(monbac.affichetab[i]);
	}

	System.out.println("recieved");
} catch (ClassNotFoundException | IOException r) {
	r.printStackTrace();
}*/