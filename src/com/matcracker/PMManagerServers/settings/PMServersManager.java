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
	
package com.matcracker.PMManagerServers.settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.matcracker.PMManagerServers.API.APIManager;
import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.FileChooser;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class PMServersManager {
	private static void addServer(){
		String newName = Utility.readString(BaseLang.translate("pm.serverManager.choose") + " ", "[" + BaseLang.translate("pm.serverManager.suggest") + " " + UtilityServersAPI.getDefaultServerName() + "]");
		String newPath = "";
		
		if(APIManager.isServerMode())
			newPath = Utility.readString(BaseLang.translate("pm.serverManager.choosePath") + " ", "[" + BaseLang.translate("pm.standard.example") + " /home/User/PocketMine-MP/]");
		else
			newPath = FileChooser.getPhar(BaseLang.translate("pm.serverManager.choosePath"));
		
		int nservers = UtilityServersAPI.getNumberServers();
		
		nservers++;
		if(newName.equalsIgnoreCase(""))
			newName = UtilityServersAPI.getDefaultServerName() + "_" + nservers;
		
		UtilityServersAPI.setNameServer(nservers, newName);
		
		if(newPath != null)
			UtilityServersAPI.setPath(nservers, newPath);
		
		StatusAPI.setStatus(BaseLang.translate("pm.status.noDownload"), nservers);
		StatusAPI.setVersion(BaseLang.translate("pm.status.noVersion"), nservers);
		StatusAPI.setBackuped(BaseLang.translate("pm.status.noBackuped"), nservers);
		StatusAPI.setPerformance(BaseLang.translate("pm.status.personal"), nservers);
		
		UtilityServersAPI.setNumberServer(nservers);

		Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.serverManager.correctAdd"));
	}
	
	private static void changeServerName(int server){
		String newName = Utility.readString(BaseLang.translate("pm.serverManager.choose") + " ", "[" + BaseLang.translate("pm.serverManager.suggest") + " " + UtilityServersAPI.getDefaultServerName() + "]");
		String newPath = "";
		
		File oldBackupFile = new File("Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(server) + ".zip");
		
		if(newName.equalsIgnoreCase(""))
			newName = UtilityServersAPI.getDefaultServerName() + "_" + server;
		
		if(APIManager.isServerMode())
			newPath = Utility.readString(BaseLang.translate("pm.serverManager.choosePath") + " ", "[" + BaseLang.translate("pm.standard.example") + " /home/User/PocketMine-MP/]");
		else
			newPath = FileChooser.getPhar(BaseLang.translate("pm.serverManager.choosePath"));
		
		File newBackupFile = new File("Backups" + File.separator + "Servers" + File.separator + newName + ".zip");
		UtilityServersAPI.setNameServer(server, newName);
		UtilityServersAPI.setPath(server, newPath);
		
		if(oldBackupFile.exists())
			oldBackupFile.renameTo(newBackupFile);
		
		Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.serverManager.correctChange"));
	}
	
	private static void deleteServer(int server) throws IOException {
		int nservers = UtilityServersAPI.getNumberServers();
		int npath = UtilityServersAPI.getNumberPaths();
		
		if(nservers > 1 && npath > 1){
			File serverName = new File("ServersName" + File.separator + "ServerName_" + server + ".pm");
			File pathName = new File("Path" + File.separator + "path_" + server + ".pm");
			if(!serverName.exists()) return;
			if(!pathName.exists()) return;
			
			Files.delete(serverName.toPath());
			Files.delete(pathName.toPath());
			for(int i = server+1; i <= nservers; i++){
				String temp = UtilityServersAPI.getNameServer(i);
				String tempPath = UtilityServersAPI.getPath(i);
				
				Files.delete(new File("ServersName" + File.separator + "ServerName_" + i + ".pm").toPath());
				Files.delete(new File("Path" + File.separator + "path_" + i + ".pm").toPath());
				
				UtilityServersAPI.setNameServer(i-1, temp);
				UtilityServersAPI.setPath(i-1, tempPath);
			}
		
			nservers--;
			UtilityServersAPI.setNumberServer(nservers);
			Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.serverManager.correctDelete"));
		}else
			Utility.waitConfirm(BaseLang.translate("pm.serverManager.cantDelete"));
	
	}

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
			System.out.println(UtilityServersAPI.getNumberServers() + 1 + ") " + BaseLang.translate("pm.standard.back"));
			int server = Utility.readInt(BaseLang.translate("pm.choice.server") + " ", null);
			
			if(server == UtilityServersAPI.getNumberServers() + 1) serverManagerMenu();;
			
			if(server >= 1 && server <= UtilityServersAPI.getNumberServers() && server > 0){
				if(sel == 1)
					changeServerName(server);
				
				if(sel == 2)
					deleteServer(server);
			}else
				Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.errors.noValid"));
		}
		
		if(sel == 4)
			Settings.settingsMenu();
		
		serverManagerMenu();
	}	
}
