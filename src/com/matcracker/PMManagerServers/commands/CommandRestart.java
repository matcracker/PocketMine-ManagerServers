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

import com.matcracker.PMManagerServers.DevMode;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.managers.editing.BackgroundTask;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class CommandRestart {
	public static void command(String[] args){
		try{
			if(args.length > 1){
				if(args[1].equalsIgnoreCase("software"))
					DevMode.reboot();
				else if(args[1].equalsIgnoreCase("all")){
					int time = 3;
					BackgroundTask task = null;
					
					if(Utility.is_numeric(args[2]))
						time = Integer.parseInt(args[2]);
					
					int i = 1;
					if(UtilityServersAPI.checkServersFile("Path", "path_", i)){
						String pathContent = UtilityServersAPI.getPath(i);
						if(pathContent != null){
							task = new BackgroundTask(time*1000, i, true);
							task.start();
						}else
							Utility.waitConfirm(i + ") " + UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNull"));
					}else
						Utility.waitConfirm(i + ") " + UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNotFound"));

				}
			}else
				System.out.println(BaseLang.translate("pm.cmdMode.tooFew"));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(BaseLang.translate("pm.cmdMode.tooFewMuch"));
		}
		
	}
}
