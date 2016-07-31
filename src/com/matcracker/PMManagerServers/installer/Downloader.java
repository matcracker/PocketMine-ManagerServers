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

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class Downloader {

	protected static void downloaderMenu(){
		Utility.cleanScreen();
		int nservers = UtilityServersAPI.getNumberServers();
		
		String linkstable = "https://github.com/PocketMine/PocketMine-MP/releases/download/1.4.1/PocketMine-MP_Installer_1.4.1_x86.exe";
		String linkstable2 = "https://github.com/TheDeibo/Windows-PocketMine-MP/raw/master/PocketMine-MP-x86.exe";
		String linkbeta = "https://github.com/PocketMine/PocketMine-MP/releases/download/1.4.1dev-936/PocketMine-MP_1.4.1dev-936.phar";
		String linkdev = "https://bintray.com/pocketmine/PocketMine/download_file?file_path=PocketMine-MP_1.6dev-25_e2d079a7_API-2.0.0.phar";
		String linkdev2 = "https://dl.bintray.com/pocketmine/PocketMine/PocketMine-MP_1.6dev-27_ef8227a0_API-2.0.0.phar";
		String linksoft = "http://jenkins.pocketmine.net/job/PocketMine-Soft/lastSuccessfulBuild/artifact/PocketMine-Soft_1.5dev-245_cb9f360e_API-1.12.0.phar";
		
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.download")));
		
		for(int i = 1; i <= nservers; i++)
			System.out.printf("%d) %s | %s: %s\n", i, UtilityServersAPI.getNameServer(i), BaseLang.translate("pm.standard.status"), StatusAPI.getStatus(i));
		
		System.out.println((nservers + 1) + ") " + BaseLang.translate("pm.standard.back"));
		int server = Utility.readInt(BaseLang.translate("pm.choice.server") + " ", null);
		
		if(server == (nservers + 1)) //Back
			ManagerInstaller.managerInstallerMenu();
		
		if(server >= 1 && server <= nservers){
			System.out.println("\n1- " + BaseLang.translate("pm.status.stable") + " (Setup File)");
			System.out.println("2- " + BaseLang.translate("pm.status.beta") + " (Phar File)");
			System.out.println("3- " + BaseLang.translate("pm.status.dev") + " (Phar File)");
			System.out.println("4- " + BaseLang.translate("pm.status.soft") + " (Phar File)");
			System.out.println("5- " + BaseLang.translate("pm.standard.back"));
			
			int type = Utility.readInt(BaseLang.translate("pm.downloader.types") + " ", null);
			
			if(type == 1){ //Stable
				System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
				System.out.println("1) 1.4.1 API 1.11.0 Zekkou-Cake {MC:PE 0.10.x}");
				System.out.println("2) 1.6 API 2.0.0 Unleashed {MC:PE 0.14.0}");
				
				int ver = Utility.readInt(BaseLang.translate("pm.downloader.versionserv") + " ", null);
				
				File installer = new File("Utils" + File.separator + "PocketMine-MP_Installer_1.4.1_x86.exe");
				File installer2 = new File("Utils" + File.separator + "PocketMine-MP-x86.exe");
							
				if(Utility.getOSName().equalsIgnoreCase("Windows")){
					System.out.println(BaseLang.translate("pm.downloader.startDown"));
					if(ver == 1){
						if(installer.exists()){
							Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.instDownloaded"));
							downloaderMenu();
						}
						
						try{
							Utility.downloadFile(linkstable, "Utils");
						}catch(IOException e){
							System.out.println("Error on downloading file!");
						}
						
						if(installer.exists()){
							StatusAPI.setStatus(BaseLang.translate("pm.status.download"), server);
							Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.downloader.succInst")); 
						}else
							Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.failedInst"));
					}
					
					if(ver == 2){
						if(installer2.exists()){
							Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.instDownloaded"));	
							downloaderMenu();
						}
						try{
							Utility.downloadFile(linkstable2, "Utils");
						}catch(IOException e){
							System.out.println("Error on downloading file!");
						}
						
						if(installer2.exists()){
							StatusAPI.setStatus(BaseLang.translate("pm.status.download"), server);
							Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.downloader.succInst"));
						}else
							Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.failedInst"));
					}
				} else
					try{
						Runtime.getRuntime().exec("wget -q -O - https://raw.githubusercontent.com/PocketMine/php-build-scripts/master/installer.sh | bash -s -");
					}catch(IOException e){
						System.out.println("Error on getting installer!");
					}
			}

			if(type == 2){ //Beta
				System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
				System.out.println("1) 1.4.1 API 1.11.0 Zekkou-Cake {MC:PE 0.10.x}");
				int ver = Utility.readInt(BaseLang.translate("pm.downloader.versionserv") + " ", null);
				
				if(ver == 1){
					File beta = new File("Utils" + File.separator + "PocketMine-MP_BETA.phar");
					
					if(beta.exists()){
						Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.fileDownloaded"));
					}else{
						System.out.println(BaseLang.translate("pm.downloader.downPhar"));
						try{
							Utility.downloadFile(linkbeta, "Utils");
						}catch(IOException e){
							System.out.println("Error on downloading file!");
						}
						ManagerInstaller.renameDownloadedFile("PocketMine-MP_1.4.1dev-936.phar", "BETA");
						if(beta.exists()){
							StatusAPI.setStatus(BaseLang.translate("pm.status.download"), server);
							Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.downloader.succDownPhar"));
						}else
							Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.failedDownPhar"));
					}
				}
			}
			
			if(type == 3){ //Dev
				System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
				System.out.println("1) 1.6 API 2.0.0 [#Dev Build 25] {MC:PE 0.14.3}");
				System.out.println("2) 1.6 API 2.0.0 [#Dev Build 27] {MC:PE 0.15.1}");
				
				int ver = Utility.readInt(BaseLang.translate("pm.downloader.versionserv") + " ", null);
				
				File dev = new File("Utils" + File.separator + "PocketMine-MP_DEV.phar");
				File dev2 = new File("Utils" + File.separator + "PocketMine-MP_DEV_2.phar");
				
				
				if(dev2.exists()){
					Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.fileDownloaded"));
					downloaderMenu();
				}
				
				System.out.println(BaseLang.translate("pm.downloader.downPhar"));
				if(ver == 1){
					if(dev.exists()){
						Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.fileDownloaded"));
						downloaderMenu();
					}
					
					try{
						Utility.downloadFile(linkdev, "Utils");
					}catch(IOException e){
						System.out.println("Error on downloading file!");
					}
					ManagerInstaller.renameDownloadedFile(" PocketMine-MP_1.6dev-25_e2d079a7_API-2.0.0.phar ", "DEV");
					
					if(dev.exists()){
						StatusAPI.setStatus(BaseLang.translate("pm.status.download"), server);
						Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.downloader.succDownPhar"));
					}else
						Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.failedDownPhar"));
						
				}
				
				if(ver == 2){
					try{
						Utility.downloadFile(linkdev2, "Utils");
					}catch(IOException e){
						System.out.println("Error on downloading file!");
					}
					ManagerInstaller.renameDownloadedFile(" PocketMine-MP_1.6dev-27_ef8227a0_API-2.0.0.phar ", "DEV_2");
					
					if(dev2.exists()){
						StatusAPI.setStatus(BaseLang.translate("pm.status.download"), server);
						Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.downloader.succDownPhar"));
					}else
						Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.failedDownPhar"));
				}				
			}
			
			if(type == 4){ //Soft
				System.out.println("\n" + BaseLang.translate("pm.downloader.avaiable"));
				System.out.println("1) 1.5 API 1.12.0 Kappatsu-Fugu {MC:PE 0.11.x}");
				int ver = Utility.readInt(BaseLang.translate("pm.downloader.versionserv") + " ", null);
				
				if(ver == 1){
					File soft = new File("Utils" + File.separator + "PocketMine-MP_SOFT.phar");
					
					if(soft.exists()){
						Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.fileDownloaded"));
					}else{
						System.out.println(BaseLang.translate("pm.downloader.downPhar"));
						try{
							Utility.downloadFile(linksoft, "Utils");
						}catch(IOException e){
							System.out.println("Error on downloading file!");
						}
						ManagerInstaller.renameDownloadedFile("PocketMine-Soft_1.5dev-245_cb9f360e_API-1.12.0.phar", "SOFT");
						if(soft.exists()){
							StatusAPI.setStatus(BaseLang.translate("pm.status.download"), server);
							Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.downloader.succDownPhar"));
						}else
							Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.downloader.failedDownPhar"));
					}
				}
			}
		}
		
		downloaderMenu();
	}

	protected static void downloadPHP(){
		String phplink;
		
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.downloader.phpBinaries")));
		System.out.println("1- " + BaseLang.translate("pm.managerInstaller.download") + " PHP 5.6.10");
		System.out.println("2- " + BaseLang.translate("pm.managerInstaller.download") + " PHP 7.0.3");
		System.out.println("3- " + BaseLang.translate("pm.php.downloadOthers"));
		System.out.println("4- " + BaseLang.translate("pm.standard.back"));
		int opt = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
		
		if(opt < 1 || opt > 4) downloadPHP();
		
		String arch = System.getProperty("sun.arch.data.model");
		String os = Utility.getOSName();
		
		if(opt == 3)
			Utility.openSoftware("url", "https://dl.bintray.com/pocketmine/PocketMine/");

		if(arch.contains("32"))
			arch = "86";
		else if(arch.contains("64"))
			arch = "64";
		else if(arch.contains("64") && !os.equalsIgnoreCase("Windows"))
			arch = "86-64";

		if(opt == 1 || opt == 2){
			String php;
			
			if(opt == 1)
				php = "5.6.10";
			else
				php = "7.0.3";
			
			phplink = "https://dl.bintray.com/pocketmine/PocketMine/PHP_" + php + "_x" + arch + "_" + os + ".tar.gz";
			try {
				Utility.downloadFile(phplink, "Utils");
			}catch(IOException e){
				System.out.println("Error on downloading file.");
			}
			Utility.waitConfirm(BaseLang.translate("pm.php.binariesDownloaded"));
			String confirm = Utility.readString(BaseLang.translate("pm.php.extractIn") + " <Y/n>: ", null);
			String filePHP = " PHP_" + php + "_x" + arch + "_" + os + ".tar.gz";
			
			if(confirm.equalsIgnoreCase("Y"))
				Installator.installPHP(filePHP);
				
			ManagerInstaller.managerInstallerMenu();
		}
		
	}

}
