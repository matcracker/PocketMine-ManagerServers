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
	
package com.matcracker.PMManagerServers;

import com.matcracker.PMManagerServers.commands.CommandEdit;
import com.matcracker.PMManagerServers.commands.CommandHelp;
import com.matcracker.PMManagerServers.commands.CommandRescuer;
import com.matcracker.PMManagerServers.commands.CommandRestart;
import com.matcracker.PMManagerServers.commands.CommandSetStart;
import com.matcracker.PMManagerServers.commands.CommandStart;
import com.matcracker.PMManagerServers.commands.CommandStop;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.lang.LangSelector;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class CommandsMode {
	
	protected static void menu(){
		Utility.cleanScreen();
		System.out.println(Utility.setTitle('=', UtilityColor.GREEN, Utility.softwareName));
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.commandsMode")));
		
		String cmd = "";
		
		while(!cmd.equalsIgnoreCase("menu")){
			String[] args = Utility.readString(UtilityColor.WHITE + ">", null).replace("/", "").split(" ");
			cmd = args[0].toLowerCase();
			
			switch(cmd){
				case "backup": CommandRescuer.commandBackup(args); break;
				case "clear": menu(); System.out.println(UtilityColor.GREEN +BaseLang.translate("pm.cmdMode.consoleClean")); break;
				case "edit": CommandEdit.command(args); break;
				case "exit": System.exit(0); break;
				case "help": CommandHelp.command(); break;
				case "language": LangSelector.langMenu(); break;
				case "menu": PMMS.mainMenu(); break;
				case "restart": CommandRestart.command(args); break;
				case "restore": CommandRescuer.commandRestore(args);   break;
				case "setstart": CommandSetStart.command(args); break;
				case "start": CommandStart.command(args); break;
				case "stop": CommandStop.command(args); break;
				default:
					System.out.println(UtilityColor.RED + BaseLang.translate("pm.cmdMode.cmdNotFound"));
			}
		}
	}
}
