package com.matcracker.PMManagerServers.installer;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class Installator {
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
	
	protected static void installatorMenu() throws IOException{
		Utility.cleanScreen();
		int nservers = UtilityServersAPI.getNumberServers();
		
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.installator")));
		
		for(int i = 1; i <= nservers; i++)
			System.out.printf("%d) %s | %s: %s | %s: %s\n", i, UtilityServersAPI.getNameServer(i), BaseLang.translate("pm.standard.version"), StatusAPI.getVersion(i), BaseLang.translate("pm.standard.status"), StatusAPI.getStatus(i));
		
		System.out.println((nservers + 1) + ") " + BaseLang.translate("pm.standard.back"));
		int server = Utility.readInt(BaseLang.translate("pm.choice.installServers") + ": ", null);
		
		if(server == (nservers + 1)){ //Back
			ManagerInstaller.managerInstallerMenu();
		}else{
			System.out.println("\n1- " + BaseLang.translate("pm.status.stable") + " (Setup File)");
			System.out.println("2- " + BaseLang.translate("pm.status.beta") + " (Phar File)");
			System.out.println("3- " + BaseLang.translate("pm.status.dev") + " (Phar File)");
			System.out.println("4- " + BaseLang.translate("pm.status.soft") + " (Phar File)");
			System.out.println("5- " + BaseLang.translate("pm.standard.back"));
			
			int ver = Utility.readInt(BaseLang.translate("pm.choice.version") + ": ", null);
			
			if(ver == 1){ //Stable
				System.out.println("\n" + BaseLang.translate("pm.installer.avaiable"));
				System.out.println("1) 1.4.1 API 1.11.0 Zekkou-Cake {MC:PE 0.10.x}");
				int type = Utility.readInt(BaseLang.translate("pm.installer.types") + " ", null);

				if(type == 1){
					File installer = new File("Utils" + File.separator + "PocketMine-MP_Installer_1.4.1_x86.exe");
					
					if(installer.exists()){
						Utility.openSoftware("software", String.valueOf(installer));
						StatusAPI.setVersion(BaseLang.translate("pm.status.stable"), server);
						StatusAPI.setStatus(BaseLang.translate("pm.status.install"), server);
					}else
						Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.installer.instNotFound"));					
				}
			}
			
			if(ver == 2){ //Beta
				if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
					System.out.println("\n" + BaseLang.translate("pm.installer.avaiable"));
					System.out.println("1) 1.4.1 API 1.11.0 Zekkou-Cake {MC:PE 0.10.x}");
					int type = Utility.readInt(BaseLang.translate("pm.installer.types") + " ", null);
					
					if(type == 1){
						File beta = new File("Utils" + File.separator + "PocketMine-MP_BETA.phar");
						
						if(beta.exists()){
							String confirm = Utility.readString(BaseLang.translate("pm.installer.replace") + " <Y/n>: ", null);
							
							if(confirm.equalsIgnoreCase("y")){
								ManagerInstaller.changeInstallationsFile(UtilityServersAPI.getPath(server), "BETA");
								StatusAPI.setVersion(BaseLang.translate("pm.status.beta"), server);
							}	
						}else
							Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.installer.pharNotFound"));
					}
				}else
					Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNotFound"));
			}
			
			if(ver == 3){ //Dev
				if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
					System.out.println("\n" + BaseLang.translate("pm.installer.avaiable"));
					System.out.println("1) 1.6 API 2.0.0 [#Dev Build 25] {MC:PE 0.14.3}");
					int type = Utility.readInt(BaseLang.translate("pm.installer.types") + " ", null);

					if(type == 1){
						File dev = new File("Utils" + File.separator + "PocketMine-MP_DEV.phar");
						
						if(dev.exists()){
							String confirm = Utility.readString(BaseLang.translate("pm.installer.replace") + " <Y/n>: ", null);
							
							if(confirm.equalsIgnoreCase("y")){
								ManagerInstaller.changeInstallationsFile(UtilityServersAPI.getPath(server), "DEV");
								StatusAPI.setVersion(BaseLang.translate("pm.status.dev"), server);
							}	
						}else
							Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.installer.pharNotFound"));

					}
				}else
					Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNotFound"));
				
			}
			
			if(ver == 4){ //Soft
				if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
					System.out.println("\n" + BaseLang.translate("pm.installer.avaiable"));
					System.out.println("1) 1.5 API 1.12.0 Kappatsu-Fugu {MC:PE 0.11.x}");
					int type = Utility.readInt(BaseLang.translate("pm.installer.types") + " ", null);
					
					if(type == 1){
						File soft = new File("Utils" + File.separator + "PocketMine-MP_SOFT.phar");
						
						if(soft.exists()){
							String confirm = Utility.readString(BaseLang.translate("pm.installer.replace") + " <Y/n>: ", null);
							
							if(confirm.equalsIgnoreCase("y")){
								ManagerInstaller.changeInstallationsFile(UtilityServersAPI.getPath(server), "SOFT");
								StatusAPI.setVersion(BaseLang.translate("pm.status.soft"), server);
							}	
						}else
							Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.installer.pharNotFound"));
						
					}
				}else
					Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNotFound"));
				
			}
			
			if(ver == 5)
				installatorMenu();
			
			installatorMenu();
		}
		
		
	
	}
}
