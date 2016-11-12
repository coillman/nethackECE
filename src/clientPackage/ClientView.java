package clientPackage;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import serverPackage.Item;


public class ClientView  {
	private String clientIpnut;
	Client instanceClient;
	
	public ClientView(int port){
		instanceClient = new Client(null, port);
		clientIpnut = "";
	}
	

	public void getClientInput() throws IOException{
		String dir;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (clientIpnut.equalsIgnoreCase("quit") == false) {
			
			clientIpnut = in.readLine();
			
			if (clientIpnut.matches("z|s|q|d")){
				//direction key event
				dir= this.getDirection(clientIpnut);
				System.out.println("cmd: "+ dir);
				
			}else if(clientIpnut.contains("use")){
				// use item event
				String itemName = this.getItemToUse(clientIpnut);
				if(this.checkInInventory(itemName)){
					// check if item is in inventory
				}
				System.out.println("use an item");
			
			}else if(clientIpnut.contains("pick")){
				// pick item event
				String itemtoAdd = getItemtoPick(clientIpnut);
				// pass to itemtoAdd to an Inventory
				
			}else if(clientIpnut.contains("attack")){
				//attack opponent event
				String perso = this.getOpponentToAttack(clientIpnut);
				
			
			}else if(clientIpnut.matches("quit")) {
				//test if player wants to quit
				System.out.println("Good bye! :)");
			
			}else {
				// otherwise the event is unknown
				System.out.println("cmd unkown ...");
			}
			
		}
		in.close();
		
		
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
	
	public String getOpponentToAttack(String command){
		String perso = command.substring(command.lastIndexOf("attack")+7);
		perso = perso.trim();
		return perso;
	}
	
	public String getItemToUse(String command){
		String item = command.substring(command.lastIndexOf("use")+4).trim();
		return item;
	}
	
	public String getItemtoPick(String command){
		// need to pass this in the BAC to add to player inventory List
		String item = command.substring(command.lastIndexOf("pick")+5).trim();
		return item;
	}
	
	public void showInventoryList(){
		//need to get the inventory List from BAC
	}
	
	public boolean checkInInventory(String itemName){
		//invetory is retrieved from BAC
		boolean itemState = false;
		ArrayList<String>inventory = new ArrayList<String>();
		for (Iterator<String> it = inventory.iterator(); it.hasNext(); ) {
		    String item = it.next();
		    if (item.equals(itemName)) {
		    	itemState = true;
		    }else{
		    	itemState = false;
		    } 
		}
		return itemState;	
	}
	
}
