package com.matcracker.PMManagerServers.Installer;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Languages.BaseLang;
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
	
	protected static void downloaderMenu() throws IOException{
		Utility.cleanScreen();
		int nservers = UtilityServersAPI.getNumberServers();
		
		String linkstable = "https://github.com/PocketMine/PocketMine-MP/releases/download/1.4.1/PocketMine-MP_Installer_1.4.1_x86.exe";
		String linkbeta = "https://github.com/PocketMine/PocketMine-MP/releases/download/1.4.1dev-936/PocketMine-MP_1.4.1dev-936.phar";
		String linkdev = "https://bintray.com/artifact/download/pocketmine/PocketMine/PocketMine-MP_1.6dev-23_6ba0abf5_API-2.0.0.phar";
		String linksoft = "http://jenkins.pocketmine.net/job/PocketMine-Soft/lastSuccessfulBuild/artifact/PocketMine-Soft_1.5dev-245_cb9f360e_API-1.12.0.phar";
		
		System.out.println(Utility.softwareName);
		System.out.println(BaseLang.translate("pm.title.download"));
		for(int i = 0; i < nservers; i++){
			System.out.printf("%d) %s -> "+BaseLang.translate("pm.standard.status")+": %s\n", i+1, UtilityServersAPI.getNameServer(i), StatusAPI.getStatus(i));
		}
		System.out.println((nservers + 1) + ") " + BaseLang.translate("pm.standard.back"));
		System.out.print("\n" + BaseLang.translate("pm.downloader.version")+ " ");
		String sel = Utility.keyword.readLine();
		
		if(sel.equalsIgnoreCase(String.valueOf(nservers + 1))){ //Back
			ManagerInstaller.managerInstallerMenu();
		}else{
			System.out.println("\n1- " + BaseLang.translate("pm.managerInstaller.stable") + " (Setup File)");
			System.out.println("2- " + BaseLang.translate("pm.managerInstaller.beta") + " (Phar File)");
			System.out.println("3- " + BaseLang.translate("pm.managerInstaller.dev") + " (Phar File)");
			System.out.println("4- " + BaseLang.translate("pm.managerInstaller.soft") + " (Phar File)");
			System.out.println("5- " + BaseLang.translate("pm.standard.back"));
			
			System.out.print("\n" + BaseLang.translate("pm.downloader.versionserv") + " ");
			String ver = Utility.keyword.readLine();
			
			if(ver.equalsIgnoreCase("1")){ //Stable
				System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
				System.out.println("1) 1.4.1 API 1.11.0 Zekkou-Cake {MC:PE 0.10.x}");
				System.out.print("\n" + BaseLang.translate("pm.downloader.types") + " ");
				String type = Utility.keyword.readLine();
				
				if(type.equalsIgnoreCase("1")){
					File installer = new File("Utils" + File.separator + "PocketMine-MP_Installer_1.4.1_x86.exe");
					if(installer.exists()){
						System.err.println(BaseLang.translate("pm.errors.instDownloaded"));
						Utility.keyword.readLine();
						
					}else{
						System.out.println(BaseLang.translate("pm.downloader.startDown"));
						Utility.openSoftware("url", linkstable);
						System.out.println(BaseLang.translate("pm.downloader.succInst"));
						StatusAPI.setStatus("Downloaded", Integer.valueOf(sel) - 1);
						Utility.keyword.readLine();
					}
				}
			}
			
			if(ver.equalsIgnoreCase("2")){ //Beta
				if(UtilityServersAPI.checkServersFile("Path", "path_", nservers-1)){
					System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
					System.out.println("1) 1.4.1 API 1.11.0 Zekkou-Cake {MC:PE 0.10.x}");
					System.out.print("\n" + BaseLang.translate("pm.downloader.types") + " ");
					String type = Utility.keyword.readLine();
					
					if(type.equalsIgnoreCase("1")){
						File beta = new File("Utils" + File.separator + "PocketMine-MP_BETA.phar");
						
						if(beta.exists()){
							System.err.println(BaseLang.translate("pm.errors.fileDownloaded"));
							Utility.keyword.readLine();
							
						}else{
							System.out.println(BaseLang.translate("pm.downloader.downPhar"));
							Utility.openSoftware("url", linkbeta);
							System.out.println(BaseLang.translate("pm.downloader.succDownPhar"));
							Utility.keyword.readLine();
						}
					}
				}else{
					System.err.println(BaseLang.translate("pm.errors.pathNotFound"));
					Utility.keyword.readLine();
				}
			}
			
			if(ver.equalsIgnoreCase("3")){ //Dev
				if(UtilityServersAPI.checkServersFile("Path", "path_", nservers-1)){
					System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
					System.out.println("1) 1.6 API 2.0.0 [#Dev Build 23] {MC:PE 0.15.x}");
					System.out.print("\n" + BaseLang.translate("pm.downloader.types") + " ");
					String type = Utility.keyword.readLine();
					
					if(type.equalsIgnoreCase("1")){
						File dev = new File("Utils" + File.separator + "PocketMine-MP_DEV.phar");
						
						if(dev.exists()){
							System.err.println(BaseLang.translate("pm.errors.fileDownloaded"));
							Utility.keyword.readLine();
							
						}else{
							System.out.println(BaseLang.translate("pm.downloader.downPhar"));
							Utility.openSoftware("url", linkdev);
							System.out.println(BaseLang.translate("pm.downloader.succDownPhar"));
							Utility.keyword.readLine();
						}
					}
				}else{
					System.err.println(BaseLang.translate("pm.errors.pathNotFound"));
					Utility.keyword.readLine();
				}
			}
			
			if(ver.equalsIgnoreCase("4")){ //Soft
				if(UtilityServersAPI.checkServersFile("Path", "path_", nservers-1)){
					System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
					System.out.println("1) 1.5 API 1.12.0 Kappatsu-Fugu {MC:PE 0.11.x}");
					System.out.print("\n" + BaseLang.translate("pm.downloader.types") + " ");
					String type = Utility.keyword.readLine();
					
					if(type.equalsIgnoreCase("1")){
						File soft = new File("Utils" + File.separator + "PocketMine-MP_SOFT.phar");
						
						if(soft.exists()){
							System.err.println(BaseLang.translate("pm.errors.fileDownloaded"));
							Utility.keyword.readLine();
							
						}else{
							System.out.println(BaseLang.translate("pm.downloader.downPhar"));
							Utility.openSoftware("url", linksoft);
							System.out.println(BaseLang.translate("pm.downloader.succDownPhar"));
							Utility.keyword.readLine();
						}
					}
				}else{
					System.err.println(BaseLang.translate("pm.errors.pathNotFound"));
					Utility.keyword.readLine();
				}
			}
			
			if(ver.equalsIgnoreCase("5"))
				downloaderMenu();
			
			downloaderMenu();
		
		}
	}

}
