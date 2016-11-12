package clientPackage;

import java.io.*;
//import java.net.*;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) throws IOException {

		// demande du numéro de port à l'utilisatreur
		System.out.println("numero de port :");
		Scanner sc1 = new Scanner(System.in);
		int port = sc1.nextInt();
		// sc1.close();

		ClientView monclient = new ClientView(port);
		monclient.getClientInput();
	}

}
