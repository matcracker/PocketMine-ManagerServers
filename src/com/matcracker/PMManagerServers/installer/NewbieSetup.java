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
	
package com.matcracker.PMManagerServers.installer;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.APIManager;
import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.FileChooser;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class NewbieSetup {
	public static void setupMenu() throws IOException{
		String name, path;
		String link = "https://github.com/TheDeibo/Windows-PocketMine-MP/raw/master/PocketMine-MP-x86.exe";
		File installer = new File("Utils" + File.separator + "PocketMine-MP-x86.exe");
		int nservers = UtilityServersAPI.getNumberServers();
		
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.newbieSetup")));
		if(!Utility.getOSName().equalsIgnoreCase("Windows")){
			Utility.waitConfirm("This mode is only for windows user!"
								+ "\nFor easy install go to 'Download->(select server)->Stable->(select version)'");
			ManagerInstaller.managerInstallerMenu();
		}
		
		String confirm = Utility.readString(BaseLang.translate("pm.newbie.confirmMode") + " <Y/n>: ", null);
		/*
		 * This part install PocketMine
		 */
		if(confirm.equalsIgnoreCase("N"))
			ManagerInstaller.managerInstallerMenu();
		
		System.out.println(Utility.setTitle("&a", BaseLang.translate("pm.title.newbieInstallation")));
		System.out.println(BaseLang.translate("pm.newbie.start"));
		System.out.println(BaseLang.translate("pm.newbie.download"));

		if(!installer.exists())
			Utility.downloadFile(link, "Utils");
		
		System.out.println(BaseLang.translate("pm.newbie.install"));
		Utility.openSoftware("software", "Utils" + File.separator + "PocketMine-MP-x86.exe");
		
		System.out.println(Utility.setTitle("&a", BaseLang.translate("pm.title.newbieInstallation")));
		System.out.println(BaseLang.translate("pm.newbie.setupPMMS"));
		
		/*
		 * This part install the important file of PocketMine-ManagerServers
		 */
		do{
			name = Utility.readString(BaseLang.translate("pm.newbie.selectName") + " ", null);
			if(name.contains(" "))
				Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.loader.noSpaces"));
			else if(name.equalsIgnoreCase(""))
				name = UtilityServersAPI.getDefaultServerName() + "_" + (nservers+1);
			
		}while(name.contains(" ") || name == null);
		
		UtilityServersAPI.setNameServer(nservers + 1, name);
		
		System.out.println(BaseLang.translate("pm.newbie.selectPath") + " ");
		
		do{
			if(!APIManager.isServerMode())
				path = FileChooser.getPhar("Select the file .phar where you installed PocketMine");
			else{
				System.out.println("[Example: /home/User/PocketMine-MP/]");
				path = Utility.keyword.readLine();
			}
		}while(path == null);
		
		UtilityServersAPI.setPath(nservers+1, path);
		StatusAPI.setStatus(BaseLang.translate("pm.status.install"), nservers + 1);
		StatusAPI.setVersion(BaseLang.translate("pm.status.dev"), nservers + 1);
		
		UtilityServersAPI.setNumberServer(nservers + 1);

		System.out.println(BaseLang.translate("pm.newbie.finish"));
		String run = Utility.readString(BaseLang.translate("pm.newbie.run") + " <Y/n>: ", null);
		
		if(run.equalsIgnoreCase("Y"))
			Utility.openSoftware("software", path + File.separator + Utility.getStartName()); 
		
	}
}
