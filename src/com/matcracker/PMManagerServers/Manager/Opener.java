package com.matcracker.PMManagerServers.Manager;

import java.io.IOException;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Utility.Utility;

public class Opener {
	public static void openerMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println("-----------------------------------<Opener>-------------------------------------");
		System.out.println("1- Servers");
		System.out.println("2- Folders");
		System.out.println("3- Back");
		int opt = Utility.readInt("Select option: ", null);
		
		if(opt == 1)
			open(true);
		
		if(opt == 2)
			open(false);
		
		if(opt == 3)
			Manager.managerMenu();
		
		openerMenu();
	}
	
	/**
	 * @param isServer if true open servers else folders
	 */
	private static void open(boolean isServer){
		Utility.showServers();
		int server = Utility.readInt("Select server: ", "[Select -1 for open all servers]");
		
		if(server == -1){
			for(int i = 0; i < UtilityServersAPI.getNumberServers(); i++){
				if(UtilityServersAPI.checkServersFile("Path", "path_", i)){
					String pathContent = UtilityServersAPI.getPath(i);
					if(pathContent != null){
						System.out.println("Opening " + UtilityServersAPI.getNameServer(i));
						if(isServer)
							Utility.openSoftware("software", pathContent + "/start.cmd");
						else
							Utility.openSoftware("software", pathContent);
					}else{
						Utility.waitConfirm("Path can't be null!");
						break;
					}
				}else{
					Utility.waitConfirm("This server path doesn't exist!");
					break;
				}
			}
		}else{
			if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
				String pathContent = UtilityServersAPI.getPath(server-1);
				if(pathContent != null){
					System.out.println("Opening " + UtilityServersAPI.getNameServer(server - 1));
					if(isServer)
						Utility.openSoftware("software", pathContent + "start.cmd");
					else
						Utility.openSoftware("software", pathContent);
				}else
					Utility.waitConfirm("Path can't be null!");
			}else
				Utility.waitConfirm("This server path doesn't exist!");
		}
	}
}
