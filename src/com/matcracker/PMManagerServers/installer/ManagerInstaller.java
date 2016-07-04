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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.matcracker.PMManagerServers.Main;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class ManagerInstaller {
	/**
	 * @param path where the PocketMine-MP.phar is located
	 * @param version name of PocketMine-MP (STABLE, BETA, DEV, SOFT)
	 * @throws IOException
	 */
	public static void changeInstallationsFile(String path, String version) throws IOException{
		File newPhar = new File(path + File.separator + "PocketMine-MP.phar");
		File oldPhar = new File(path + File.separator + "PocketMine-MP_OLD.phar");
		
		File pharToMove = new File("Utils" + File.separator + "PocketMine-MP_" + version + ".phar");
		File pharDest = new File(path + File.separator + "PocketMine-MP_" + version + ".phar");
		
		if(newPhar.exists()){
			if(oldPhar.exists()){
				Files.delete(oldPhar.toPath());
				Files.copy(pharToMove.toPath(), pharDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
				newPhar.renameTo(oldPhar);
				pharDest.renameTo(newPhar);
			}else{
				newPhar.renameTo(oldPhar);
				Files.copy(pharToMove.toPath(), pharDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
				pharDest.renameTo(newPhar);
			}
		}else{
			Files.copy(pharToMove.toPath(), pharDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
			pharDest.renameTo(newPhar);
		}
	}
	
	public static void managerInstallerMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.managerInstaller")));
		System.out.println("1- " + BaseLang.translate("pm.managerInstaller.newbie"));
		System.out.println("2- " + BaseLang.translate("pm.managerInstaller.download") + " PocketMine-MP");
		System.out.println("3- " + BaseLang.translate("pm.managerInstaller.install") + " PocketMine-MP");
		System.out.println("4- " + BaseLang.translate("pm.managerInstaller.downloadPHP"));
		System.out.println("5- " + BaseLang.translate("pm.managerInstaller.installPHP"));
		System.out.println("6- " + BaseLang.translate("pm.standard.back"));
		int inst = Utility.readInt(BaseLang.translate("pm.choice.ask") + ": ", null);
		
		if(inst == 1)
			NewbieSetup.setupMenu();
		
		if(inst == 2)
			Downloader.downloaderMenu();
		
		if(inst == 3)
			Installator.installatorMenu();
		
		if(inst == 4)
			Downloader.downloadPHP();
		
		if(inst == 5)
			Installator.installPHP(null);
		
		if(inst == 6)
			Main.mainMenu();
		
		managerInstallerMenu();
	}
	
	
	/**
	 * @param downloadPath where the user usually download files
	 * @param file name of file
	 * @param type version name of PocketMine-MP (STABLE, BETA, DEV, SOFT)
	 */
	@Deprecated
	public static void moveDownloadedFiles(String downloadPath, String file, String type){
		File destFolder = new File("Utils" + File.separator + file);
		File downloadFile = new File(downloadPath + File.separator + file);
		
		if(UtilityServersAPI.checkServersFile("Path", "downloadPath", -1)){
			try{
				Files.copy(downloadFile.toPath(), destFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}catch (IOException e){
				System.out.println(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.fileNotFound"));
			}
			if(type != null)
				destFolder.renameTo(new File("Utils" + File.separator + "PocketMine-MP_" + type.toUpperCase() + ".phar"));
			
		}else
			System.out.println(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.noDownloadPath"));
	}
	/**
	 * When you download a file, you can rename it and add a suffix for recognize it.
	 * @param file
	 * @param type
	 */
	public static void renameDownloadedFile(String file, String type){
		File fileFolder = new File("Utils" + File.separator + file);
		
		if(fileFolder.exists()){
			if(type != null)
				fileFolder.renameTo(new File("Utils" + File.separator + "PocketMine-MP_" + type.toUpperCase() + ".phar"));
		}else
			System.out.println(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.noDownloadPath"));
	}
}
