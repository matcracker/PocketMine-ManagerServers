package com.matcracker.PMManagerServers.Settings;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Utility.Utility;

public class Resetter{
  /** _____           _        _   __  __ _                   __  __                                   _____                              
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
			//"Languages",
			"Backups" + File.separator + "Status",
			"Backups" + File.separator + "Servers",
			"Backups"
	};
	
	public static void resetterMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println("-------------------------------<Reset Program>----------------------------------");
		System.out.println("1- Reset data of servers (Only program)");
		System.out.println("2- Reset data of servers (Only servers path and names)");
		System.out.println("3- Back");
		
		int reset = Utility.readInt("\nChoose what do you want to reset: ", null);
		
		if(reset == 1)
			resetProgram();
		
		if(reset == 2)
			resetServers();
		
		if(reset == 3)
			Settings.settingsMenu();
	
	}
	
	private static void resetProgram(){
		try{
			Utility.cleanScreen();
			System.out.println("========================<PocketMine Manager Servers>============================");
			System.out.println("-------------------------------<Reset Program>----------------------------------");
			String confirm = Utility.readString("Are you sure to want reset data of servers (only program)? <y/n>: ");
			
			if(confirm.equalsIgnoreCase("y")){
				for(int i = 0; i < dirsName.length; i++){
					Utility.deleteFile(dirsName[i]);
					Utility.deleteFolder(dirsName[i], dirsName.length);
				}
				System.out.println("Closing program...");
				Thread.sleep(1000);
				Desktop.getDesktop().open(new File("run.bat"));
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
			System.out.println("========================<PocketMine Manager Servers>============================");
			System.out.println("-------------------------------<Reset Program>----------------------------------");
			String confirm = Utility.readString("WARNING! Are you sure to want to reset data of servers (paths and names)? <Y/N>: ");
		
			if(confirm.equalsIgnoreCase("y")){
				for(int i = 0; i < UtilityServersAPI.getNumberServers(); i++){
					if(UtilityServersAPI.checkServersFile("Path", "path_", i) && UtilityServersAPI.checkServersFile("ServersName", "ServerName_", i)){
						Utility.deleteFile("Path");
						Utility.deleteFile("ServersName");
						Utility.waitConfirm("Paths deleted!");
						Thread.sleep(1000);
					}else{
						System.out.println((i+1) + ")This server path doesn't exist!");
						Utility.waitConfirm("");
					}
				}
			}else
				resetterMenu();
		}catch(IOException | InterruptedException e){
			e.printStackTrace();
		}
	}
}
