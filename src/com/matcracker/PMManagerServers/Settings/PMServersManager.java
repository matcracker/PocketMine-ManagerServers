package com.matcracker.PMManagerServers.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Utility.Utility;
import com.matcracker.PMManagerServers.Utility.UtilityColor;

public class PMServersManager {
   /* _____           _        _   __  __ _                   __  __                                   _____                              
	*|  __ \         | |      | | |  \/  (_)                 |  \/  |                                 / ____|                             
	*| |__) |__   ___| | _____| |_| \  / |_ _ __   ___ ______| \  / | __ _ _ __   __ _  __ _  ___ _ _| (___   ___ _ ____   _____ _ __ ___ 
	*|  ___/ _ \ / __| |/ / _ \ __| |\/| | | '_ \ / _ \______| |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '__\___ \ / _ \ '__\ \ / / _ \ '__/ __|
	*| |  | (_) | (__|   <  __/ |_| |  | | | | | |  __/      | |  | | (_| | | | | (_| | (_| |  __/ |  ____) |  __/ |   \ V /  __/ |  \__ \
	*|_|   \___/ \___|_|\_\___|\__|_|  |_|_|_| |_|\___|      |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_| |_____/ \___|_|    \_/ \___|_|  |___/
	*                                                                                   __/ |                                             
	*                                                                                  |___/                                              
	*Copyright (C) 2015-2016 @author matcracker
	*
	*This program is free software: you can redistribute it and/or modify 
	*it under the terms of the GNU Lesser General Public License as published by 
	*the Free Software Foundation, either version 3 of the License, or 
	*(at your option) any later version. 
	*/
	protected static void serverManagerMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.serversManager")));
		System.out.println("1- " + BaseLang.translate("pm.serverManager.change"));
		System.out.println("2- " + BaseLang.translate("pm.serverManager.delete"));
		System.out.println("3- " + BaseLang.translate("pm.serverManager.add"));
		System.out.println("4- " + BaseLang.translate("pm.standard.back"));
		int sel = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
		
		if(sel == 3)
			addServer();
		
		else if(sel == 1 || sel == 2){
			Utility.showServers();
			int server = Utility.readInt(BaseLang.translate("pm.choice.server") + " ", null);
			
			if(server <= UtilityServersAPI.getNumberServers()){
				if(sel == 1)
					changeServerName(server);
				
				if(sel == 2)
					deleteServer(server);
			}else
				Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.noValid"));
		}
		
		if(sel == 4)
			Settings.settingsMenu();
		
		serverManagerMenu();
	}

	private static void deleteServer(int server) throws IOException {
		int nservers = UtilityServersAPI.getNumberServers();

		Files.delete(new File("ServersName" + File.separator + "ServerName_" + server + ".pm").toPath());
		for(int i = server+1; i <= nservers; i++){
			String temp = UtilityServersAPI.getNameServer(i);
			Files.delete(new File("ServersName" + File.separator + "ServerName_" + i + ".pm").toPath());
			UtilityServersAPI.setNameServer(i-1, temp);
		}
		nservers--;
		UtilityServersAPI.setNumberServer(nservers);
		Utility.waitConfirm(UtilityColor.COLOR_GREEN + BaseLang.translate("pm.serverManager.correctDelete"));
	
	}
	
	private static void addServer(){
		String newName = Utility.readString(BaseLang.translate("pm.serverManager.choose") + " ", "[" + BaseLang.translate("pm.serverManager.suggest") + " " + UtilityServersAPI.getDefaultServerName() + "]");
		int nservers = UtilityServersAPI.getNumberServers();
		
		nservers++;
		if(newName.equalsIgnoreCase(""))
			newName = UtilityServersAPI.getDefaultServerName() + "_" + nservers;
		
		UtilityServersAPI.setNameServer(nservers, newName);
		UtilityServersAPI.setNumberServer(nservers);
		
		Utility.waitConfirm(UtilityColor.COLOR_GREEN + BaseLang.translate("pm.serverManager.correctAdd"));
	}

	private static void changeServerName(int server) {
		String newName = Utility.readString(BaseLang.translate("pm.serverManager.choose") + " ", "[" + BaseLang.translate("pm.serverManager.suggest") + " " + UtilityServersAPI.getDefaultServerName() + "]");
		File oldBackupFile = new File("Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(server) + ".zip");
		
		if(newName.equalsIgnoreCase(""))
			newName = UtilityServersAPI.getDefaultServerName() + "_" + server;
		
		File newBackupFile = new File("Backups" + File.separator + "Servers" + File.separator + newName + ".zip");
		UtilityServersAPI.setNameServer(server, newName);

		if(oldBackupFile.exists())
			oldBackupFile.renameTo(newBackupFile);
		
		
		Utility.waitConfirm(UtilityColor.COLOR_GREEN + BaseLang.translate("pm.serverManager.correctChange"));
	}
}
