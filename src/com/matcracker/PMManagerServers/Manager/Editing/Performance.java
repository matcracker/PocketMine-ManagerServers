package com.matcracker.PMManagerServers.Manager.Editing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Utility.Utility;

public class Performance {
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
	public static void performanceMenu() throws IOException {
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(BaseLang.translate("pm.title.performance"));
		System.out.println("Your current performance are: ");
		for(int i = 0; i < UtilityServersAPI.getNumberServers(); i++)
			System.out.printf("%d) %s: %s\n", i+1, UtilityServersAPI.getNameServer(i), StatusAPI.getPerformace(i));
		
		int server = Utility.readInt("Select server: ", null);
		
		if(UtilityServersAPI.checkServersFile("Path", "path_", server - 1)){
			String pathContent = UtilityServersAPI.getPath(server-1);
			if(pathContent != null){			
				String confirm = null;
				
				System.out.println("What features do you want to assign to your servers?");
				System.out.println("1- High");
				System.out.println("2- Medium");
				System.out.println("3- Low");
				System.out.println("4- " + BaseLang.translate("pm.standard.back"));
				
				int feat = Utility.readInt("Choose feature: ", null);
				
				if(feat == 1){ //HIGH
					File high = new File("Utils" + File.separator + "pocketmine_HIGH.yml");
					if(high.exists()){
						System.out.println("If you choose this option, you'll need to have a PC that supports it, so if you do not own it is strongly advised not to use it.");
						confirm = Utility.readString("Do you want continue? <y/n>: ");
						
						if(confirm.equalsIgnoreCase("y")){
							changePerformaceFile(pathContent, "HIGH");
							StatusAPI.setPerformance("High", server-1);
							Utility.waitConfirm("Performace changed!");
						}
					}else
						Utility.waitConfirm("The HIGH configuration doesn't exist!");
				}
				
				if(feat == 2){ //MEDIUM
					File med = new File("Utils" + File.separator + "pocketmine_MEDIUM.yml");
					if(med.exists()){
						System.out.println("If you choose this option, you'll need a PC not too handsome, it is suitable for handling small servers.");
						confirm = Utility.readString("Do you want continue? <y/n>: ");
						
						if(confirm.equalsIgnoreCase("y")){
							changePerformaceFile(pathContent, "MEDIUM");
							StatusAPI.setPerformance("Medium", server-1);
							Utility.waitConfirm("Performace changed!");
						}
					}else
						Utility.waitConfirm("The MEDIUM configuration doesn't exist!");
				}
					
				
				if(feat == 3){ //LOW
					File low = new File("Utils" + File.separator + "pocketmine_LOW.yml");
					if(low.exists()){
						System.out.println("If you choose this option, you'll need a PC not too handsome, it is suitable to manage servers with friends.");
						confirm = Utility.readString("Do you want continue? <y/n>: ");
						
						if(confirm.equalsIgnoreCase("y")){
							changePerformaceFile(pathContent, "LOW");
							StatusAPI.setPerformance("Low", server-1);
							Utility.waitConfirm("Performace changed!");
						}
					}else
						Utility.waitConfirm("The LOW configuration doesn't exist!");				
				}

				if(feat == 4)
					Editor.editorMenu();
				
				performanceMenu();
			}else
				Utility.waitConfirm("Path can't be null!");
		}else
			Utility.waitConfirm("This server path doesn't exist!");
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