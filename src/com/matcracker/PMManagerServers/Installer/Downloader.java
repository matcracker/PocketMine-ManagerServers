package com.matcracker.PMManagerServers.Installer;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Utility.Utility;

public class Downloader {
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
	
	public static void downloaderMenu() throws IOException{
		Utility.cleanScreen();
		int nservers = UtilityServersAPI.getNumberServers();
		
		String linkstable = "https://github.com/PocketMine/PocketMine-MP/releases/download/1.4.1/PocketMine-MP_Installer_1.4.1_x86.exe";
		String linkbeta = "https://github.com/PocketMine/PocketMine-MP/releases/download/1.4.1dev-936/PocketMine-MP_1.4.1dev-936.phar";
		String linkdev = "https://bintray.com/artifact/download/pocketmine/PocketMine/PocketMine-MP_1.6dev-23_6ba0abf5_API-2.0.0.phar";
		String linksoft = "http://jenkins.pocketmine.net/job/PocketMine-Soft/lastSuccessfulBuild/artifact/PocketMine-Soft_1.5dev-245_cb9f360e_API-1.12.0.phar";
		
		System.out.println("========================<PocketMine Manager Servers>============================");
		System.out.println("---------------------------<Download PocketMine-MP>-----------------------------");
		for(int i = 0; i < nservers; i++){
			System.out.printf("%d) %s -> Status: %s\n", i+1, UtilityServersAPI.getNameServer(i), StatusAPI.getStatus(i));
		}
		System.out.println((nservers + 1) + ") Back");
		System.out.print("\nWhat kind of version you want to download?: ");
		String sel = Utility.keyword.readLine();
		
		if(sel.equalsIgnoreCase(String.valueOf(nservers + 1))){ //Back
			ManagerInstaller.managerInstallerMenu();
		}else{
			System.out.println("\n1- Stable (Setup File)");
			System.out.println("2- Beta (Phar File)");
			System.out.println("3- Dev (Phar File)");
			System.out.println("4- Soft (Phar File)");
			System.out.println("5- Back");
			
			System.out.print("\nSelect a version for your server: ");
			String ver = Utility.keyword.readLine();
			
			if(ver.equalsIgnoreCase("1")){ //Stable
				System.out.println("\nAvaiable types:");
				System.out.println("1) 1.4.1 API 1.11.0 Zekkou-Cake {MC:PE 0.10.x}");
				System.out.print("\nSelect the type of version: ");
				String type = Utility.keyword.readLine();
				
				if(type.equalsIgnoreCase("1")){
					File installer = new File("Utils" + File.separator + "PocketMine-MP_Installer_1.4.1_x86.exe");
					if(installer.exists()){
						System.err.println("You have already downloaded this installer!");
						Utility.keyword.readLine();
						
					}else{
						System.out.println("Starting download installer...");
						Utility.openSoftware("url", linkstable);
						System.out.println("Installer downloaded! Press ENTER to continue.");
						StatusAPI.setStatus("Downloaded", Integer.valueOf(sel) - 1);
						Utility.keyword.readLine();
					}
				}
			}
			
			if(ver.equalsIgnoreCase("2")){ //Beta
				if(UtilityServersAPI.checkServersFile("Path", "path_", nservers-1)){
					System.out.println("\nAvaiable types:");
					System.out.println("1) 1.4.1 API 1.11.0 Zekkou-Cake {MC:PE 0.10.x}");
					System.out.print("\nSelect the type of version: ");
					String type = Utility.keyword.readLine();
					
					if(type.equalsIgnoreCase("1")){
						File beta = new File("Utils" + File.separator + "PocketMine-MP_BETA.phar");
						
						if(beta.exists()){
							System.err.println("You have already downloaded this file!");
							Utility.keyword.readLine();
							
						}else{
							System.out.println("Starting download phar file...!");
							Utility.openSoftware("url", linkbeta);
							System.out.println("Phar file downloaded! Press ENTER to continue.");
							Utility.keyword.readLine();
						}
					}
				}else{
					System.err.println("ERROR! You don't select a path in the first start");
					Utility.keyword.readLine();
				}
			}
			
			if(ver.equalsIgnoreCase("3")){ //Dev
				if(UtilityServersAPI.checkServersFile("Path", "path_", nservers-1)){
					System.out.println("\nAvaiable types:");
					System.out.println("1) 1.6 API 2.0.0 [#Dev Build 23] {MC:PE 0.15.x}");
					System.out.print("\nSelect the type of version: ");
					String type = Utility.keyword.readLine();
					
					if(type.equalsIgnoreCase("1")){
						File dev = new File("Utils" + File.separator + "PocketMine-MP_DEV.phar");
						
						if(dev.exists()){
							System.err.println("You have already downloaded this file!");
							Utility.keyword.readLine();
							
						}else{
							System.out.println("Starting download phar file...!");
							Utility.openSoftware("url", linkdev);
							System.out.println("Phar file downloaded! Press ENTER to continue.");
							Utility.keyword.readLine();
						}
					}
				}else{
					System.err.println("ERROR! You don't select a path in the first start");
					Utility.keyword.readLine();
				}
			}
			
			if(ver.equalsIgnoreCase("4")){ //Soft
				if(UtilityServersAPI.checkServersFile("Path", "path_", nservers-1)){
					System.out.println("\nAvaiable types:");
					System.out.println("1) 1.5 API 1.12.0 Kappatsu-Fugu {MC:PE 0.11.x}");
					System.out.print("\nSelect the type of version: ");
					String type = Utility.keyword.readLine();
					
					if(type.equalsIgnoreCase("1")){
						File soft = new File("Utils" + File.separator + "PocketMine-MP_SOFT.phar");
						
						if(soft.exists()){
							System.err.println("You have already downloaded this file!");
							Utility.keyword.readLine();
							
						}else{
							System.out.println("Starting download phar file...!");
							Utility.openSoftware("url", linksoft);
							System.out.println("Phar file downloaded! Press ENTER to continue.");
							Utility.keyword.readLine();
						}
					}
				}else{
					System.err.println("ERROR! You don't select a path in the first start");
					Utility.keyword.readLine();
				}
			}
			
			if(ver.equalsIgnoreCase("5"))
				downloaderMenu();
			
			downloaderMenu();
		
		}
	}

}
