package com.matcracker.PMManagerServers;

import com.matcracker.PMManagerServers.Informations.Informations;
import com.matcracker.PMManagerServers.Installer.ManagerInstaller;
import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Loaders.Loader;
import com.matcracker.PMManagerServers.Manager.Manager;
import com.matcracker.PMManagerServers.Settings.Settings;
import com.matcracker.PMManagerServers.Utility.PMPrintStream;
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
	
	public static int mode = 0;
	
	public static void main(String[] args){
		String menu = "", quit = "n";
		String version = "1.0 Alpha 1";
		
		System.setOut(new PMPrintStream(System.out));
		System.setErr(new PMPrintStream(System.out));
		
		Loader.startLoader();
		
		try{
			while(quit.equalsIgnoreCase("n")){
				Utility.cleanScreen();
				System.out.println(Utility.softwareName);
				System.out.println(BaseLang.translate("pm.title.mainMenu"));
				System.out.println("&eDeveloped by matcracker                                     Version: " + version);
				System.out.println("&f1- " + BaseLang.translate("pm.mainMenu.download-install"));
				System.out.println("2- " + BaseLang.translate("pm.mainMenu.manager"));
				System.out.println("3- " + BaseLang.translate("pm.mainMenu.options"));
				System.out.println("4- " + BaseLang.translate("pm.mainMenu.informations"));
				System.out.println("5- " + BaseLang.translate("pm.mainMenu.exit") + "\n");
				System.out.print(BaseLang.translate("pm.chooise.ask")+ " ");
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
					System.out.println(Utility.softwareName);
					System.out.println(BaseLang.translate("pm.title.exit"));
					System.out.print(BaseLang.translate("pm.chooise.exit") + " <y/n>: ");
					quit = Utility.keyword.readLine();
					
					if(quit.equalsIgnoreCase("y"))
						System.exit(0);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}