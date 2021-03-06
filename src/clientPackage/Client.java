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
	
	public Bac listen(){
		Bac bacIn = new Bac();
		try {
			InputStream stream = comm.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(stream);
			bacIn = (Bac) ois.readObject();
			//ois.close();
			
		} catch (ClassNotFoundException | IOException r) {
			r.printStackTrace();
		}
		return bacIn;
	}
	
	public void close(){
		try{
			comm.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}


