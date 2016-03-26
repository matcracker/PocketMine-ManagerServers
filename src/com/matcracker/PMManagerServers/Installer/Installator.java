package com.matcracker.PMManagerServers.Installer;

import java.io.IOException;

import com.matcracker.PMManagerServers.API.InstallatorAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Utility.Utility;

public class Installator {
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
	
	public static void installatorMenu() throws IOException{
		Utility.cleanScreen();
		int nservers = UtilityServersAPI.getNumberServers();
		
		System.out.println("========================<PocketMine Manager Servers>============================");
		System.out.println("---------------------------<Install PocketMine-MP>------------------------------");
		for(int i = 0; i < nservers; i++){
			System.out.printf("%d) %s -> Version: s -> Status: s\n", i+1, UtilityServersAPI.getNameServer(i));//, InstallatorAPI.getVersion(i));
		}
		System.out.println((nservers + 1) + ") Back");
		System.out.print("\nOn which server do you want install PocketMine-MP?");
		String sel = Utility.keyword.readLine();
		
		
		if(sel.equalsIgnoreCase(String.valueOf(nservers + 1))){
			ManagerInstaller.managerInstallerMenu();
		}
	
	}
}
