/* _____           _        _   __  __ _                   __  __                                   _____                              
 *|  __ \         | |      | | |  \/  (_)                 |  \/  |                                 / ____|                             
 *| |__) |__   ___| | _____| |_| \  / |_ _ __   ___ ______| \  / | __ _ _ __   __ _  __ _  ___ _ _| (___   ___ _ ____   _____ _ __ ___ 
 *|  ___/ _ \ / __| |/ / _ \ __| |\/| | | '_ \ / _ \______| |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '__\___ \ / _ \ '__\ \ / / _ \ '__/ __|
 *| |  | (_) | (__|   <  __/ |_| |  | | | | | |  __/      | |  | | (_| | | | | (_| | (_| |  __/ |  ____) |  __/ |   \ V /  __/ |  \__ \
 *|_|   \___/ \___|_|\_\___|\__|_|  |_|_|_| |_|\___|      |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_| |_____/ \___|_|    \_/ \___|_|  |___/
 *                                                                                   __/ |                                             
 *                                                                                  |___/                                              
 *Copyright (C) 2015-2017 @author matcracker
 *
 *This program is free software: you can redistribute it and/or modify 
 *it under the terms of the GNU Lesser General Public License as published by 
 *the Free Software Foundation, either version 3 of the License, or 
 *(at your option) any later version.
*/
	
package com.matcracker.PMManagerServers.managers;

import java.io.File;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class Opener {
	/**
	 * @param isServer if true open servers else folders
	 */
	private static void open(boolean isServer){
		Utility.showServers();
		System.out.println(UtilityServersAPI.getNumberServers() + 1 + ") " + BaseLang.translate("pm.standard.back"));
		int server = Utility.readInt(BaseLang.translate("pm.choice.server") + " ", "[" + BaseLang.translate("pm.opener.suggest") + "]");
		
		if(server == UtilityServersAPI.getNumberServers() + 1) return;
		
		if(server == -1){
			for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
				if(UtilityServersAPI.checkServersFile("Path", "path_", i)){
					String pathContent = UtilityServersAPI.getPath(i);
					if(pathContent != null){
						System.out.println(BaseLang.translate("pm.opener.opening") + " " + UtilityServersAPI.getNameServer(i));
						if(isServer)
							Utility.openSoftware("software", pathContent + File.separator + Utility.getStartName());
						else
							Utility.openSoftware("software", pathContent);
					}else{
						Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.errors.pathNull"));
						break;
					}
				}else{
					Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.errors.pathNotFound"));
					break;
				}
			}
			return;
		}
		
		if(server >= 1 && server <= UtilityServersAPI.getNumberServers()){
			if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
				String pathContent = UtilityServersAPI.getPath(server);
				if(pathContent != null){
					System.out.println(BaseLang.translate("pm.opener.opening") + " " + UtilityServersAPI.getNameServer(server));
					if(isServer)
						Utility.openSoftware("software", pathContent + File.separator + Utility.getStartName());
					else
						Utility.openSoftware("software", pathContent);
				}else
					Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.errors.pathNull"));
			}else
				Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.errors.pathNotFound"));
			
			return;
		}
		
		open(isServer);
	}
	
	protected static void openerMenu(){
		Utility.cleanScreen();
		System.out.println(Utility.setTitle('=', UtilityColor.GREEN, Utility.softwareName));
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.opener")));
		System.out.println("1- " + BaseLang.translate("pm.opener.server"));
		System.out.println("2- " + BaseLang.translate("pm.opener.folders"));
		System.out.println("3- " + BaseLang.translate("pm.standard.back"));
		int opt = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
		
		if(opt == 1)
			open(true);
		
		if(opt == 2)
			open(false);
		
		if(opt == 3)
			Manager.managerMenu();
		
		openerMenu();
	}
}
