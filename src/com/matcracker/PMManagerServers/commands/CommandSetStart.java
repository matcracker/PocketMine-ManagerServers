/* _____           _        _   __  __ _                   __  __                                   _____                              
 *|  __ \         | |      | | |  \/  (_)                 |  \/  |                                 / ____|                             
 *| |__) |__   ___| | _____| |_| \  / |_ _ __   ___ ______| \  / | __ _ _ __   __ _  __ _  ___ _ _| (___   ___ _ ____   _____ _ __ ___ 
 *|  ___/ _ \ / __| |/ / _ \ __| |\/| | | '_ \ / _ \______| |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '__\___ \ / _ \ '__\ \ / / _ \ '__/ __|
 *| |  | (_) | (__|   <  __/ |_| |  | | | | | |  __/      | |  | | (_| | | | | (_| | (_| |  __/ |  ____) |  __/ |   \ V /  __/ |  \__ \
 *|_|   \___/ \___|_|\_\___|\__|_|  |_|_|_| |_|\___|      |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_| |_____/ \___|_|    \_/ \___|_|  |___/
 *                                                                                   __/ |                                             
 *                                                                                  |___/                                              
 *Copyright (C) 2015-2017 @author matcracker
 *
 *This program is free software: you can redistribute it and/or modify 
 *it under the terms of the GNU Lesser General Public License as published by 
 *the Free Software Foundation, either version 3 of the License, or 
 *(at your option) any later version.
*/
	
package com.matcracker.PMManagerServers.commands;

import com.matcracker.PMManagerServers.API.APIManager;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class CommandSetStart {
	public static void command(String[] args){
		try{
			if(args.length > 1){
				if(args[1].equalsIgnoreCase("commander")){
					APIManager.setCommandsMode(true);
					System.out.println(UtilityColor.GREEN + BaseLang.translate("pm.cmdSetStart.commander"));
				}else if(args[1].equalsIgnoreCase("menu")){
					APIManager.setCommandsMode(false);
					System.out.println(UtilityColor.GREEN + BaseLang.translate("pm.cmdSetStart.menu"));
				}
			}else
				System.out.println(BaseLang.translate("pm.cmdMode.tooFew"));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(BaseLang.translate("pm.cmdMode.tooFewMuch"));
		}
	}
}
