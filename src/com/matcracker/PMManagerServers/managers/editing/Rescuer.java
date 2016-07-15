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
	
package com.matcracker.PMManagerServers.managers.editing;

import java.io.File;
import java.io.IOException;

import org.rauschig.jarchivelib.ArchiveFormat;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.managers.Manager;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;
import com.matcracker.PMManagerServers.utility.Zipper;

public class Rescuer {
	private static void backup(){
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.backup")));
		for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++)
			System.out.printf("%d) %s -> %s: %s\n", i, UtilityServersAPI.getNameServer(i), BaseLang.translate("pm.standard.status"), StatusAPI.getBackuped(i));
		
		int server = Utility.readInt(BaseLang.translate("pm.choice.server") + " ", null);
		
		if(server > UtilityServersAPI.getNumberServers())
			backup();
		else{
			if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
				String pathContent = UtilityServersAPI.getPath(server);
				if(pathContent != null){
					String backupedServersPath = "Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(server) + ".zip";
					if(StatusAPI.getBackuped(server).equalsIgnoreCase(BaseLang.translate("pm.status.noBackuped"))){
						System.out.println(BaseLang.translate("pm.rescuer.create"));
						Zipper.zip(null, pathContent, backupedServersPath, ArchiveFormat.ZIP, null);
						StatusAPI.setBackuped(BaseLang.translate("pm.status.backuped"), server);
						Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.rescuer.backuped"));
					}else
						Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.rescuer.existBackup"));
				}else
					Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.errors.pathNull"));
			}else
				Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.errors.pathNotFound"));
			
		}
	}
	
	public static void rescuerMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.rescuer")));
		System.out.println("1- " + BaseLang.translate("pm.rescuer.backup"));
		System.out.println("2- " + BaseLang.translate("pm.rescuer.restore"));
		System.out.println("3- " + BaseLang.translate("pm.standard.back"));
		
		int sel = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
		
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
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.restore")));
		for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++)
			System.out.printf("%d) %s -> %s: %s\n", i, UtilityServersAPI.getNameServer(i), BaseLang.translate("pm.standard.status"), StatusAPI.getBackuped(i));
		
		int server = Utility.readInt(BaseLang.translate("pm.choice.server") + " ", null);
		
		if(server > UtilityServersAPI.getNumberServers())
			backup();
		else{
			if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
				String pathContent = UtilityServersAPI.getPath(server);
				if(pathContent != null){
					if(StatusAPI.getBackuped(server).equalsIgnoreCase(BaseLang.translate("pm.status.backuped"))){
						String extractServersPath = "Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(server) + ".zip";
						String destinationPath = "Backups" + File.separator + "Servers" + File.separator + "Extracted";
						
						System.out.println(BaseLang.translate("pm.rescuer.extracting"));
						Zipper.unzip(extractServersPath, destinationPath, ArchiveFormat.ZIP, null);
						Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.rescuer.extracted"));
					}else
						Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.rescuer.noBackup"));
				}else
					Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.errors.pathNull"));
			}else
				Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.errors.pathNotFound"));
			
		}
	}
}
