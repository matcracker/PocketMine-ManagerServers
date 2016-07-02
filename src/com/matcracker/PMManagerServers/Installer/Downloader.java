package com.matcracker.PMManagerServers.installer;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class Downloader {
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
	
	protected static void downloaderMenu() throws IOException{
		Utility.cleanScreen();
		int nservers = UtilityServersAPI.getNumberServers();
		
		String linkstable = "https://github.com/TheDeibo/Windows-PocketMine-MP/raw/master/PocketMine-MP-x86.exe";
		String linkbeta = "https://github.com/PocketMine/PocketMine-MP/releases/download/1.4.1dev-936/PocketMine-MP_1.4.1dev-936.phar";
		String linkdev = "https://bintray.com/pocketmine/PocketMine/download_file?file_path=PocketMine-MP_1.6dev-25_e2d079a7_API-2.0.0.phar";
		String linksoft = "http://jenkins.pocketmine.net/job/PocketMine-Soft/lastSuccessfulBuild/artifact/PocketMine-Soft_1.5dev-245_cb9f360e_API-1.12.0.phar";
		
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.download")));
		
		for(int i = 1; i <= nservers; i++)
			System.out.printf("%d) %s | %s: %s\n", i, UtilityServersAPI.getNameServer(i), BaseLang.translate("pm.standard.status"), StatusAPI.getStatus(i));
		
		System.out.println((nservers + 1) + ") " + BaseLang.translate("pm.standard.back"));
		int server = Utility.readInt(BaseLang.translate("pm.choice.server") + " ", null);
		
		if(server == (nservers + 1)) //Back
			ManagerInstaller.managerInstallerMenu();
		
		if(server <= nservers){
			System.out.println("\n1- " + BaseLang.translate("pm.status.stable") + " (Setup File)");
			System.out.println("2- " + BaseLang.translate("pm.status.beta") + " (Phar File)");
			System.out.println("3- " + BaseLang.translate("pm.status.dev") + " (Phar File)");
			System.out.println("4- " + BaseLang.translate("pm.status.soft") + " (Phar File)");
			System.out.println("5- " + BaseLang.translate("pm.standard.back"));
			
			int ver = Utility.readInt(BaseLang.translate("pm.downloader.versionserv") + " ", null);
			
			if(ver == 1){ //Stable
				System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
				System.out.println("1) 1.6 API 2.0.0 Unleashed {MC:PE 0.14.x}");
				int type = Utility.readInt(BaseLang.translate("pm.downloader.types") + " ", null);
				
				if(type == 1){
					File installer = new File("Utils" + File.separator + "PocketMine-MP_Installer_1.4.1_x86.exe");
					if(installer.exists()){
						Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.downloader.instDownloaded"));				
					}else{
						System.out.println(BaseLang.translate("pm.downloader.startDown"));
						Utility.downloadFile(linkstable, "Utils");
						StatusAPI.setStatus(BaseLang.translate("pm.status.download"), server);
						Utility.waitConfirm(BaseLang.translate("pm.downloader.succInst"));
					}
				}
			}
			
			if(ver == 2){ //Beta
				System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
				System.out.println("1) 1.4.1 API 1.11.0 Zekkou-Cake {MC:PE 0.10.x}");
				int type = Utility.readInt(BaseLang.translate("pm.downloader.types") + " ", null);
				
				if(type == 1){
					File beta = new File("Utils" + File.separator + "PocketMine-MP_BETA.phar");
					
					if(beta.exists()){
						Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.downloader.fileDownloaded"));
					}else{
						System.out.println(BaseLang.translate("pm.downloader.downPhar"));
						Utility.downloadFile(linkbeta, "Utils");
						ManagerInstaller.renameDownloadedFile("PocketMine-MP_1.4.1dev-936.phar", "BETA");
						StatusAPI.setStatus(BaseLang.translate("pm.status.download"), server);
						Utility.waitConfirm(BaseLang.translate("pm.downloader.succDownPhar"));
					}
				}
			}
			
			if(ver == 3){ //Dev
				System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
				System.out.println("1) 1.6 API 2.0.0 [#Dev Build 25] {MC:PE 0.14.3}");
				
				int type = Utility.readInt(BaseLang.translate("pm.downloader.types") + " ", null);
				
				if(type == 1){
					File dev = new File("Utils" + File.separator + "PocketMine-MP_DEV.phar");
					
					if(dev.exists()){
						Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.downloader.fileDownloaded"));
						
					}else{
						System.out.println(BaseLang.translate("pm.downloader.downPhar"));
						Utility.downloadFile(linkdev, "Utils");
						ManagerInstaller.renameDownloadedFile(" PocketMine-MP_1.6dev-25_e2d079a7_API-2.0.0.phar ", "DEV");
						StatusAPI.setStatus(BaseLang.translate("pm.status.download"), server);
						Utility.waitConfirm(BaseLang.translate("pm.downloader.succDownPhar"));
					}
				}
			}
			
			if(ver == 4){ //Soft
				System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
				System.out.println("1) 1.5 API 1.12.0 Kappatsu-Fugu {MC:PE 0.11.x}");
				int type = Utility.readInt(BaseLang.translate("pm.downloader.types") + " ", null);
				
				if(type == 1){
					File soft = new File("Utils" + File.separator + "PocketMine-MP_SOFT.phar");
					
					if(soft.exists()){
						Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.downloader.fileDownloaded"));
					}else{
						System.out.println(BaseLang.translate("pm.downloader.downPhar"));
						Utility.downloadFile(linksoft, "Utils");
						ManagerInstaller.renameDownloadedFile("PocketMine-Soft_1.5dev-245_cb9f360e_API-1.12.0.phar", "SOFT");
						StatusAPI.setStatus(BaseLang.translate("pm.status.download"), server);
						Utility.waitConfirm(BaseLang.translate("pm.downloader.succDownPhar"));

					}
				}
			}
			
			if(ver == 5)
				downloaderMenu();
		}
		
		downloaderMenu();
	}

}
