package com.matcracker.PMManagerServers;

import com.matcracker.PMManagerServers.Informations.Informations;
import com.matcracker.PMManagerServers.Installer.ManagerInstaller;
import com.matcracker.PMManagerServers.Loaders.Loader;
import com.matcracker.PMManagerServers.Manager.Manager;
import com.matcracker.PMManagerServers.Settings.Settings;
import com.matcracker.PMManagerServers.Utility.Utility;

public class Main{
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
		
	public static void main(String[] args) throws Exception{
		String menu = "", quit = "n";
		String version = "0.1J";
						
		Loader.startLoader();
				
		try{
			while(quit.equalsIgnoreCase("n")){
				Utility.cleanScreen();
				System.out.println("========================<PocketMine Manager Servers>============================");
				System.out.println("---------------------------------<Main menu>------------------------------------");
				System.out.println("Developed by matcracker                                            Version: " + version);
				System.out.println("1- Download/Install PocketMine-MP");
				System.out.println("2- Manage Servers");
				System.out.println("3- Options");
				System.out.println("4- Informations");
				System.out.println("5- Exit\n");
				System.out.print("What would you like to do? ");
				menu = Utility.keyword.readLine();
	
				if(menu.equalsIgnoreCase("1"))
					ManagerInstaller.managerInstallerMenu();
					
				if(menu.equalsIgnoreCase("2"))
					Manager.managerMenu();

				if(menu.equalsIgnoreCase("3"))
					Settings.settingsMenu();
				
				if(menu.equalsIgnoreCase("4"))
					Informations.informationsMenu();
				
				if(menu.equalsIgnoreCase("5")){
					Utility.cleanScreen();
					System.out.println("========================<PocketMine Manager Servers>============================");
					System.out.println("=====================================<Exit>=====================================");
					System.out.print("Are you sure you want to quit? <y/n>: ");
					quit = Utility.keyword.readLine();
					
					if(quit.equalsIgnoreCase("y"))
						System.exit(0);
					else
						quit = "n";
					
				}
			}
		}catch(Exception e){
			System.out.println(Utility.inputError);
		}
	}
}