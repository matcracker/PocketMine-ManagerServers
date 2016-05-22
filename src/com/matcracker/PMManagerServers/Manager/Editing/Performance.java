package com.matcracker.PMManagerServers.Manager.Editing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Utility.Utility;
import com.matcracker.PMManagerServers.Utility.UtilityColor;

public class Performance {
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
	protected static void performanceMenu() throws IOException {
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.performance")));
		System.out.println(BaseLang.translate("pm.performance.select") + " ");
		for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++)
			System.out.printf("%d) %s: %s\n", i, UtilityServersAPI.getNameServer(i), StatusAPI.getPerformace(i));
		
		int server = Utility.readInt(BaseLang.translate("pm.choice.server") + " ", null);
		
		if(server > UtilityServersAPI.getNumberServers())
			performanceMenu();
		
		if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
			String pathContent = UtilityServersAPI.getPath(server);
			if(pathContent != null){			
				String confirm = null;
				
				System.out.println(BaseLang.translate("pm.performance.type"));
				System.out.println("1- " + BaseLang.translate("pm.status.high"));
				System.out.println("2- " + BaseLang.translate("pm.status.medium"));
				System.out.println("3- " + BaseLang.translate("pm.status.low"));
				System.out.println("4- " + BaseLang.translate("pm.standard.back"));
				
				int feat = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
				
				if(feat == 1){ //HIGH
					File high = new File("Utils" + File.separator + "pocketmine_HIGH.yml");
					if(high.exists()){
						System.out.println(BaseLang.translate("pm.performance.selHigh"));
						confirm = Utility.readString(BaseLang.translate("pm.performance.continue") + " <y/n>: ", null);
						
						if(confirm.equalsIgnoreCase("y")){
							changePerformaceFile(pathContent, BaseLang.translate("pm.status.high").toUpperCase());
							StatusAPI.setPerformance(BaseLang.translate("pm.status.high"), server);
							Utility.waitConfirm(BaseLang.translate("pm.performance.complete"));
						}
					}else
						Utility.waitConfirm(BaseLang.translate("pm.performance.noHighConf"));
				}
				
				if(feat == 2){ //MEDIUM
					File med = new File("Utils" + File.separator + "pocketmine_MEDIUM.yml");
					if(med.exists()){
						System.out.println(BaseLang.translate("pm.performance.selMedium"));
						confirm = Utility.readString(BaseLang.translate("pm.performance.continue") + " <y/n>: ", null);
						
						if(confirm.equalsIgnoreCase("y")){
							changePerformaceFile(pathContent, BaseLang.translate("pm.status.medium").toUpperCase());
							StatusAPI.setPerformance(BaseLang.translate("pm.status.medium"), server);
							Utility.waitConfirm(BaseLang.translate("pm.performance.complete"));
						}
					}else
						Utility.waitConfirm(BaseLang.translate("pm.performance.noMediumConf"));
				}
					
				
				if(feat == 3){ //LOW
					File low = new File("Utils" + File.separator + "pocketmine_LOW.yml");
					if(low.exists()){
						System.out.println(BaseLang.translate("pm.performance.selLow"));
						confirm = Utility.readString(BaseLang.translate("pm.performance.continue") + " <y/n>: ", null);
						
						if(confirm.equalsIgnoreCase("y")){
							changePerformaceFile(pathContent, BaseLang.translate("pm.status.low").toUpperCase());
							StatusAPI.setPerformance(BaseLang.translate("pm.status.low"), server);
							Utility.waitConfirm(BaseLang.translate("pm.performance.complete"));
						}
					}else
						Utility.waitConfirm(BaseLang.translate("pm.performance.noLowConf"));				
				}

				if(feat == 4)
					Editor.editorMenu();
				
				performanceMenu();
			}else
				Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNull"));
		}else
			Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNotFound"));
		
		performanceMenu();
	}
	
	protected static void changePerformaceFile(String path, String feature) throws IOException{
		File newPhar = new File(path + File.separator + "pocketmine.yml");
		File oldPhar = new File(path + File.separator + "pocketmine_OLD.yml");
		
		File pharToMove = new File("Utils" + File.separator + "pocketmine_" + feature + ".yml");
		File pharDest = new File(path + File.separator + "pocketmine_" + feature + ".yml");
		
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
