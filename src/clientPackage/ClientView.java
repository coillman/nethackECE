package clientPackage;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ClientView  {
	private String clientIpnut;
	Client instanceClient;
	
	public ClientView(int port){
		instanceClient = new Client("localhost", port);
		clientIpnut = "";
	}
	
	


	public String getClientInput() throws IOException{
		String dir;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//while (clientIpnut.equalsIgnoreCase("quit") == false) {
			System.out.println("your turn !");
			clientIpnut = in.readLine();
			if (clientIpnut.matches("z|s|q|d")){
				dir= this.getDirection(clientIpnut);
				clientIpnut = dir;
				System.out.println("cmd: "+ dir);
			}else if(clientIpnut.contains("use")){
				System.out.println("use an item");
			}else if(clientIpnut.contains("attack")){
				System.out.println("attack an opponent");	
			}else if(clientIpnut.matches("quit")) {
				System.out.println("Good bye! :)");
			}else {
				System.out.println("cmd unkown ...");
			}
			
		//}
		//in.close();
		return clientIpnut;
	}

	
	private String getDirection(String key){
		/* z: 'up'
		 * q: 'left'
		 * s: 'down'
		 * d: 'right'
		 */
		String touche;
		switch (key){
			case "z" :touche="up";		break;
			case "q" :touche="left";	break;
			case "s" :touche="down";	break;
			case "d" :touche="right";	break;
			default: touche=""; break;
		}
		return touche;
	}
	
	
	
}
