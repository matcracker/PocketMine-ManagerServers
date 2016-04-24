package com.matcracker.PMManagerServers.Installer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.matcracker.PMManagerServers.Languages.BaseLang;
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
		System.out.println(Utility.softwareName);
		System.out.println(BaseLang.translate("pm.title.managerInstaller"));
		System.out.println("1- " + BaseLang.translate("pm.managerInstaller.download"));
		System.out.println("2- " + BaseLang.translate("pm.managerInstaller.install"));
		System.out.println("3- " + BaseLang.translate("pm.standard.back"));
		System.out.print("\n" + BaseLang.translate("pm.chooise.ask") + ": ");
		String inst = Utility.keyword.readLine();
		
		if(inst.equalsIgnoreCase("1"))
			Downloader.downloaderMenu();
		
		if(inst.equalsIgnoreCase("2"))
			Installator.installatorMenu();
		
		if(inst.equalsIgnoreCase("3"))
			return;
		
	}
	
	protected static void changeInstallationsFile(String path, String version) throws IOException{
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
}
