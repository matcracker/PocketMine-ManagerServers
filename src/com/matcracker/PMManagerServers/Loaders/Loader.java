package com.matcracker.PMManagerServers.loaders;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.APIManager;
import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.lang.LangSelector;
import com.matcracker.PMManagerServers.utility.FileChooser;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class Loader {
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
		
	public static void startLoader(){
		String[] dirsName = {
				"Data",
				"ServersName",
				"Path",
				"Performance",
				"Utils", 
				"Installations",
				"Installations" + File.separator + "Status",
				"Installations" + File.separator + "Version",
				"Languages",
				"Backups",
				"Backups" + File.separator + "Status",
				"Backups" + File.separator + "Servers"
		};
		
		File checkLicense = new File("LICENSE.pdf");
		File dirMaker;
				
		boolean[] firstStart = new boolean[dirsName.length];
		
		for(int i = 0; i < dirsName.length; i++){
			firstStart[i] = false;
			dirMaker = new File(dirsName[i]);
			if(!dirMaker.exists()){
				firstStart[i] = true;
				dirMaker.mkdir();
			}
		}
		
		if(!firstStart[(int)(Math.random() * dirsName.length)] && checkLicense.exists() && UtilityServersAPI.checkServersFile("Data", "langSel", -1)){
			return;
		}else{
			try{
				System.out.println("&fPreparing the first start...");
				
				Thread.sleep(500);
	
				for(int i = 1; i < dirsName.length; i++){
					dirMaker = new File(dirsName[i]);
					dirMaker.mkdir();
				}
				
				System.out.print("&3[");
				for(int i = 0; i <= 77; i++){
					System.out.print("=");
					Thread.sleep(50);
				}
				APIManager.setDevMode(false);
				System.out.print("]");
				
				Thread.sleep(1000);
				completeLoader();
			}catch (InterruptedException | IOException e) {
				System.err.println(Utility.generalError);
			}
		}
	}
	
	public static void completeLoader() throws IOException{
		int nservers = 0;
		if(UtilityServersAPI.checkServersFile("Data", "nservers", -1) && UtilityServersAPI.checkServersFile("Data", "langSel", -1)){
			nservers = Utility.readIntData(new File("Data" + File.separator + "nservers.pm"));
			return;
		}else{	
			String temp = "";
			LangSelector.langMenu();
			do{
				Utility.cleanScreen();
				System.out.println(Utility.softwareName);
				System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.completeLoad")));
				temp = Utility.readString(BaseLang.translate("pm.choice.servers") + " <1/2/3/...>: ", null);
				
				if(Utility.is_numeric(temp))
					if(Integer.parseInt(temp) <= 0)
						Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.fewServers"));
				
			}while(Integer.parseInt(temp) <= 0 || !Utility.is_numeric(temp));
			
			nservers = Integer.parseInt(temp);
			
			UtilityServersAPI.setNumberServer(nservers);
			
		}

			
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.completeLoad")));
		System.out.printf(BaseLang.translate("pm.loader.caution") + " '%s'\n", Utility.defaultServersName);
		
		if(nservers >= 1){
			String[] nameServers = new String[nservers];
			String[] path = new String[nservers];
			
			if(UtilityServersAPI.checkServersFile("ServersName", "ServerName_", nservers - 1)){
				return;
			}else{
				
				for(int i = 1; i <= nservers; i++){
					Utility.defaultServersName = "Server_Minecraft_PE_" + i;
					System.out.printf("%d) Name of %d° server: ", i, i);
					
					try{
						nameServers[i-1] = Utility.keyword.readLine();
						
						if(nameServers[i-1].contains(" ")){
							Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.loader.noSpaces"));
							Loader.completeLoader();
							
						}else if(nameServers[i-1].equalsIgnoreCase("")){
							nameServers[i-1] = Utility.defaultServersName;
						}
					}catch (IOException e){
						e.printStackTrace();
					}
				}	
				
				for(int i = 1; i <= nservers; i++){
					System.out.printf("\n%d) Path of %d° server?: ", i, i);
					
					if(Utility.getOSName().contains("server"))
						path[i-1] = Utility.keyword.readLine();
					else
						path[i-1] = FileChooser.getPhar("Select " + i + "° path of PocketMine-MP.phar");
				}

				for(int i = 1; i <= nservers; i++){
					UtilityServersAPI.setNameServer(i, nameServers[i-1]);
					StatusAPI.setStatus(BaseLang.translate("pm.status.noDownload"), i);
					StatusAPI.setVersion(BaseLang.translate("pm.status.noVersion"), i);
					StatusAPI.setPerformance(BaseLang.translate("pm.status.personal"), i);
					StatusAPI.setBackuped(BaseLang.translate("pm.status.noBackuped"), i);
					UtilityServersAPI.setPath(i, path[i-1]);
				}
			}
		}else{
			System.out.println(Utility.generalError);
		}
		
		Utility.waitConfirm(BaseLang.translate("pm.loader.complete"));
	}
}
