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

import java.io.IOException;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.managers.editing.Performance;
import com.matcracker.PMManagerServers.managers.editing.Properties;
import com.matcracker.PMManagerServers.utility.Utility;

public class CommandEdit {
	public static void command(String[] args){
		try{
			if(args.length > 1){
				int server = -1;
				if(args[1].equalsIgnoreCase("properties")){
					if(Utility.is_numeric(args[2]))
						server = Integer.parseInt(args[2]);
					else{
						for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
							if(args[2].equalsIgnoreCase(UtilityServersAPI.getNameServer(i))){
								server = i;
								break;
							}
						}
					}
					try{
						Properties.editProperties(UtilityServersAPI.getPath(server));
					}catch (IOException ignored){}
				}else if(args[1].equalsIgnoreCase("performance")){
					if(Utility.is_numeric(args[3]))
						server = Integer.parseInt(args[3]);
					else{
						for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
							if(args[3].equalsIgnoreCase(UtilityServersAPI.getNameServer(i))){
								server = i;
								break;
							}
						}
					}
					
					switch(args[2].toUpperCase()){
						case "HIGH":
						case "MEDIUM":
						case "LOW":
							Performance.changePerformaceFile(UtilityServersAPI.getPath(server), args[2]);
							StatusAPI.setPerformance(BaseLang.translate("pm.status." + args[2].toLowerCase()), server);
						default:
							Performance.changePerformaceFile(UtilityServersAPI.getPath(server), "LOW");
							StatusAPI.setPerformance(BaseLang.translate("pm.status.low"), server);
					}
					
					Utility.waitConfirm(BaseLang.translate("pm.performance.complete"));
					
				}else
					System.out.println(BaseLang.translate("pm.cmdMode.tooFew"));
			}else
				System.out.println(BaseLang.translate("pm.cmdMode.tooFew"));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(BaseLang.translate("pm.cmdMode.tooFewMuch"));
		}
	}
}
