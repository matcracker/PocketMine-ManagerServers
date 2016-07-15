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
	
package com.matcracker.PMManagerServers.commands;

import java.io.File;

import org.rauschig.jarchivelib.ArchiveFormat;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;
import com.matcracker.PMManagerServers.utility.Zipper;

public class CommandRescuer {
	public static void commandBackup(String[] args){
		try{
			if(args.length > 1){
				if(args[1].equalsIgnoreCase("all")){
					for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
						if(StatusAPI.getBackuped(i).equalsIgnoreCase(BaseLang.translate("pm.status.noBackuped"))){
							String backupPath = "Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(i) + ".zip";
							Zipper.zip(null, UtilityServersAPI.getPath(i), backupPath, ArchiveFormat.ZIP, null);
						}else
							System.out.println(UtilityColor.RED + BaseLang.translate("pm.rescuer.existBackup"));
					}
				}else{
					int server = -1;
					if(Utility.is_numeric(args[1])){
						server = Integer.parseInt(args[1]);
					}else{
						for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
							if(args[1].equalsIgnoreCase(UtilityServersAPI.getNameServer(i))){
								server = i;
								break;
							}
						}
					}
					
					String backupPath = "Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(server) + ".zip";
					if(StatusAPI.getBackuped(server).equalsIgnoreCase(BaseLang.translate("pm.status.noBackuped"))){
						System.out.println(BaseLang.translate("pm.rescuer.create"));
						Zipper.zip(null, UtilityServersAPI.getPath(server), backupPath, ArchiveFormat.ZIP, null);
						StatusAPI.setBackuped(BaseLang.translate("pm.status.backuped"), server);
						Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.rescuer.backuped"));
					}else
						Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.rescuer.existBackup"));
				}
			}else
				System.out.println(BaseLang.translate("pm.cmdMode.tooFew"));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(BaseLang.translate("pm.cmdMode.tooFewMuch"));
		}
	}
	
	public static void commandRestore(String[] args){
		try{
			if(args.length > 1){
				String extractServersPath = "";
				String destinationPath = "Backups" + File.separator + "Servers" + File.separator + "Extracted";
				if(args[1].equalsIgnoreCase("all")){
					for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
						extractServersPath = "Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(i) + ".zip";
						if(StatusAPI.getBackuped(i).equalsIgnoreCase(BaseLang.translate("pm.status.backuped"))){
							System.out.println(i + ")"+ BaseLang.translate("pm.rescuer.extracting"));
							Zipper.unzip(extractServersPath, destinationPath, ArchiveFormat.ZIP, null);
							System.out.println(i + ")" + BaseLang.translate("pm.rescuer.extracted"));
						}else
							System.out.println(UtilityColor.RED + BaseLang.translate("pm.rescuer.noBackup"));
					}
				}else{
					int server = -1;
					if(Utility.is_numeric(args[1])){
						server = Integer.parseInt(args[1]);
					}else{
						for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
							if(args[1].equalsIgnoreCase(UtilityServersAPI.getNameServer(i))){
								server = i;
								break;
							}
						}
					}
					extractServersPath = "Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(server) + ".zip";
					
					if(StatusAPI.getBackuped(server).equalsIgnoreCase(BaseLang.translate("pm.status.backuped"))){
						System.out.println(BaseLang.translate("pm.rescuer.extracting"));
						Zipper.unzip(extractServersPath, destinationPath, ArchiveFormat.ZIP, null);
						StatusAPI.setBackuped(BaseLang.translate("pm.status.backuped"), server);
						Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.rescuer.extracted"));
					}else
						Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.rescuer.noBackup"));
				}
			}else
				System.out.println(BaseLang.translate("pm.cmdMode.tooFew"));
		
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(BaseLang.translate("pm.cmdMode.tooFewMuch"));
		}
	}
}
