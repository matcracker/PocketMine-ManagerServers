package com.matcracker.PMManagerServers.settings;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;

public class Resetter{
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
	
	private static String[] dirsName = {
			"Data",
			"ServersName",
			"Path",
			"Performance",
			"Utils", 
			"Installations" + File.separator + "Status",
			"Installations" + File.separator + "Version",
			"Installations",
			"Backups" + File.separator + "Status",
			"Backups" + File.separator + "Servers",
			"Backups"
	};
	
	private static void deleteFile(String folder){
		File dir = new File(folder);
		File[] files = dir.listFiles();
	
		for(File file : files){
			if(!file.delete())
				System.err.println("Failed to delete " + file);
		}
	}
	
	private static void deleteFolder(String folder, int index){
		File dir = new File(folder);

		for(int i = 0; i < index; i++)
			dir.delete();
	}
	
	private static void resetProgram(){
		try{
			Utility.cleanScreen();
			System.out.println(Utility.softwareName);
			System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.reset")));
			String confirm = Utility.readString(BaseLang.translate("pm.resetter.confirmProgram") + " <y/n>: ", null);
			
			if(confirm.equalsIgnoreCase("y")){
				System.out.println(BaseLang.translate("pm.resetter.close"));
				for(int i = 0; i < dirsName.length; i++){
					deleteFile(dirsName[i]);
					deleteFolder(dirsName[i], dirsName.length);
				}
				Thread.sleep(1000);
				try{
					if(Utility.getOSName().equalsIgnoreCase("Windows"))
						Utility.openSoftware("software", Utility.getRunName());
				}catch(IllegalArgumentException e){
					System.out.println("Run.bat or run.sh not found!");
				}
				System.exit(0);
	
			}else
				resetterMenu();
		}catch(IOException | InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
	private static void resetServers(){
		try{
			Utility.cleanScreen();
			System.out.println(Utility.softwareName);
			System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.reset")));
			String confirm = Utility.readString(BaseLang.translate("pm.resetter.confirmServers") + " <Y/N>: ", null);
		
			if(confirm.equalsIgnoreCase("y")){
				for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
					if(UtilityServersAPI.checkServersFile("Path", "path_", i) || UtilityServersAPI.checkServersFile("ServersName", "ServerName_", i)){
						deleteFile("Path");
						deleteFile("ServersName");
						Utility.waitConfirm(BaseLang.translate("pm.resetter.pathsDeleted"));
						Thread.sleep(1000);
					}else
						Utility.waitConfirm(i + ")" + BaseLang.translate("pm.errors.pathNotFound"));
					
				}
			}else
				resetterMenu();
		}catch(IOException | InterruptedException e){
			e.printStackTrace();
		}
	}
	
	protected static void resetterMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.reset")));
		System.out.println("1- " + BaseLang.translate("pm.resetter.programData"));
		System.out.println("2- " + BaseLang.translate("pm.resetter.serversData"));
		System.out.println("3- " + BaseLang.translate("pm.standard.back"));
		int reset = Utility.readInt(BaseLang.translate("pm.resetter.selReset") + " ", null);
		
		if(reset == 1)
			resetProgram();
		
		if(reset == 2)
			resetServers();
		
		if(reset == 3)
			Settings.settingsMenu();
		
		resetterMenu();
	
	}

}
