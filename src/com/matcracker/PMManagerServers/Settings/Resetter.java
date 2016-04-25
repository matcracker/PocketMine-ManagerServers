package com.matcracker.PMManagerServers.Settings;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

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
			"Installations",
			"Installations" + File.separator + "Status",
			"Installations" + File.separator + "Version",
			"Languages",
			"Backups",
			"Backups" + File.separator + "Status",
			"Backups" + File.separator + "Servers"
	};
	
	public static void resetterMenu() throws IOException{
		Utility.cleanScreen();
		
		System.out.println("========================<PocketMine Manager Servers>============================");
		System.out.println("-------------------------------<Reset Program>----------------------------------");
		System.out.println("1- Reset data of servers (Only program)");
		System.out.println("2- Reset data of servers (Only your specified server)");
		System.out.println("3- Reset all data/folders of program");
		System.out.println("4- Back");
		
		System.out.print("\nChoose what do you want to reset: ");
		String reset = Utility.keyword.readLine();
		
		if(reset.equalsIgnoreCase("1"))
			resetProgram();
		
		if(reset.equalsIgnoreCase("2"))
			resetServers();
		
		if(reset.equalsIgnoreCase("3"))
			resetAllData();
		
		if(reset.equalsIgnoreCase("4"))
			Settings.settingsMenu();
	
	}
	
	private static void resetAllData() {
		// TODO Auto-generated method stub
		
	}

	public static void resetProgram() throws IOException{
		String confirm = null;
		//File dirDeleter;
		
		do{
			Utility.cleanScreen();
			System.out.println("========================<PocketMine Manager Servers>============================");
			System.out.println("-------------------------------<Reset Program>----------------------------------");
			System.out.print("Are you sure to want reset data of servers (only program)? <y/n>: ");
			confirm = Utility.keyword.readLine();
		}while(!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));
		
		if(confirm.equalsIgnoreCase("y")){
			for(int i = 0; i < dirsName.length; i++){
				Utility.deleteFile(dirsName[i]);
				Utility.deleteFolder(dirsName[i], dirsName.length);
			}
			System.out.println("Closing program...");
			System.exit(0);
			Desktop.getDesktop().open(new File("run.bat"));

		}else{
			Resetter.resetterMenu();
		}
	}
	
	public static void resetServers(){
		Utility.cleanScreen();
		System.out.println("========================<PocketMine Manager Servers>============================");
		System.out.println("-------------------------------<Reset Program>----------------------------------");
	}
}
