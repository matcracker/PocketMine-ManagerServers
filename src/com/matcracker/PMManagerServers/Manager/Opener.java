package com.matcracker.PMManagerServers.Manager;

import java.io.IOException;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Utility.Utility;

public class Opener {
	public static void openerMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(BaseLang.translate("pm.title.opener"));
		System.out.println("1- " + BaseLang.translate("pm.opener.server"));
		System.out.println("2- " + BaseLang.translate("pm.opener.folders"));
		System.out.println("3- " + BaseLang.translate("pm.standard.back"));
		int opt = Utility.readInt(BaseLang.translate("pm.chooise.option") + " ", null);
		
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
		int server = Utility.readInt(BaseLang.translate("pm.chooise.server") + " ", "[" + BaseLang.translate("pm.opener.suggest") + "]");
		
		if(server == -1){
			for(int i = 0; i < UtilityServersAPI.getNumberServers(); i++){
				if(UtilityServersAPI.checkServersFile("Path", "path_", i)){
					String pathContent = UtilityServersAPI.getPath(i);
					if(pathContent != null){
						System.out.println(BaseLang.translate("pm.opener.opening") + " " + UtilityServersAPI.getNameServer(i));
						if(isServer)
							Utility.openSoftware("software", pathContent + "/start.cmd");
						else
							Utility.openSoftware("software", pathContent);
					}else{
						Utility.waitConfirm(BaseLang.translate("pm.errors.pathNull"));
						break;
					}
				}else{
					Utility.waitConfirm(BaseLang.translate("pm.errors.pathNotFound"));
					break;
				}
			}
		}else{
			if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
				String pathContent = UtilityServersAPI.getPath(server-1);
				if(pathContent != null){
					System.out.println(BaseLang.translate("pm.opener.opening") + " " + UtilityServersAPI.getNameServer(server - 1));
					if(isServer)
						Utility.openSoftware("software", pathContent + "start.cmd");
					else
						Utility.openSoftware("software", pathContent);
				}else
					Utility.waitConfirm(BaseLang.translate("pm.errors.pathNull"));
			}else
				Utility.waitConfirm(BaseLang.translate("pm.errors.pathNotFound"));
		}
	}
}
