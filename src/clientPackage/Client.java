package clientPackage;

import commonPackage.Bac;

import java.net.*;
import java.io.*;
//import java.util.*;

public class Client {
	private Socket comm;
	private int x = 1;

	public Client(String host, int port) {
		System.out.println("hello");
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

	}
	
	public void sendRequest(String instruction){
		System.out.println("info to send to the server:" + instruction);
	}
}
