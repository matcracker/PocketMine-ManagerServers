package com.matcracker.PMManagerServers.Manager.Editing;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Manager.Manager;
import com.matcracker.PMManagerServers.Utility.Utility;
import com.matcracker.PMManagerServers.Utility.Zipper;

import net.lingala.zip4j.exception.ZipException;

public class Rescuer {
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
	
	public static final String backupedServersPath = "Backups" + File.separator + "Servers";
	public static final String extractedServersPath = "Backups" + File.separator + "Servers" + File.separator + "Extracted";
	
	public static void rescuerMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(BaseLang.translate("pm.title.rescuer"));
		System.out.println("1- " + BaseLang.translate("pm.rescuer.backup"));
		System.out.println("2- " + BaseLang.translate("pm.rescuer.restore"));
		System.out.println("3- " + BaseLang.translate("pm.standard.back"));
		
		int sel = Utility.readInt(BaseLang.translate("pm.chooise.option") + " ", null);
		
		if(sel == 1)
			backup();
		
		if(sel == 2)
			restore();
			
		if(sel == 3)
			Manager.managerMenu();
		
		rescuerMenu();
	}
	
	private static void restore() {
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(BaseLang.translate("pm.title.restoreen.ini"));
		Utility.showServers();
		int server = Utility.readInt(BaseLang.translate("pm.chooise.server"), null);
		
		if(server >= UtilityServersAPI.getNumberServers())
			backup();
		else{
			if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
				String pathContent = UtilityServersAPI.getPath(server);
				if(pathContent != null){
					Zipper.unzip(pathContent, backupedServersPath, null);
					
				}else
					Utility.waitConfirm(BaseLang.translate("pm.errors.pathNull"));
			}else
				Utility.waitConfirm(BaseLang.translate("pm.errors.pathNotFound"));
			
		}
	}

	private static void backup(){
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(BaseLang.translate("pm.title.backup"));
		Utility.showServers();
		int server = Utility.readInt(BaseLang.translate("pm.chooise.server"), null);
		
		if(server >= UtilityServersAPI.getNumberServers())
			backup();
		else{
			if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
				String pathContent = UtilityServersAPI.getPath(server);
				if(pathContent != null){
					try{
						Zipper.zip(pathContent, backupedServersPath, null);
						StatusAPI.setBackuped("Backuped", server);
					}catch (ZipException e){
						e.printStackTrace();
					}
					
				}else
					Utility.waitConfirm(BaseLang.translate("pm.errors.pathNull"));
			}else
				Utility.waitConfirm(BaseLang.translate("pm.errors.pathNotFound"));
			
		}
	}
}
