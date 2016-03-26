package com.matcracker.PMManagerServers.Installer;

import java.io.IOException;

import com.matcracker.PMManagerServers.Utility.Utility;

public class ManagerInstaller {
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
	
	public static void managerInstallerMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println("========================<PocketMine Manager Servers>============================");
		System.out.println("-------------------------<Initialize PocketMine-MP>-----------------------------");
		System.out.println("1- Download");
		System.out.println("2- Install");
		System.out.println("3- Back");
		System.out.print("\nChoose what do you want to do: ");
		String inst = Utility.keyword.readLine();
		
		if(inst.equalsIgnoreCase("1"))
			Downloader.downloaderMenu();
		
		if(inst.equalsIgnoreCase("2"))
			Installator.installatorMenu();
		
		if(inst.equalsIgnoreCase("3"))
			return;
		
	}
	
	public static void changeServerVersion(){
		
	}
}
