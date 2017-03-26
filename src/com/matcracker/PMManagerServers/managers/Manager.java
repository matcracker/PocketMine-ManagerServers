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
	
package com.matcracker.PMManagerServers.managers;

import com.matcracker.PMManagerServers.PMMS;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.managers.editing.Editor;
import com.matcracker.PMManagerServers.managers.editing.Rescuer;
import com.matcracker.PMManagerServers.managers.editing.Restarter;
import com.matcracker.PMManagerServers.managers.editing.ServerPlugins;
import com.matcracker.PMManagerServers.utility.Utility;

public class Manager {
	public static void managerMenu(){
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.manager")));
		System.out.println("1- " + BaseLang.translate("pm.manager.open"));
		System.out.println("2- " + BaseLang.translate("pm.manager.edit"));
		System.out.println("3- " + BaseLang.translate("pm.manager.rescuer"));
		System.out.println("4- " + BaseLang.translate("pm.manager.restart"));
		System.out.println("5- " + BaseLang.translate("pm.manager.commandSender"));
		System.out.println("6- " + BaseLang.translate("pm.manager.serverPlugins"));
		System.out.println("7- " + BaseLang.translate("pm.standard.back"));
		int option = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
		
		if(option == 1)
			Opener.openerMenu();
		
		if(option == 2)
			Editor.editorMenu();
		
		if(option == 3)
			Rescuer.rescuerMenu();
		
		if(option == 4)
			Restarter.restarterMenu();
		
		if(option == 5)
			CommandsSender.commandSenderMenu();
		
		if(option == 6)
			ServerPlugins.pluginsMenu();
		
		if(option == 7)
			PMMS.mainMenu();
				
		managerMenu();
		
	}
}
