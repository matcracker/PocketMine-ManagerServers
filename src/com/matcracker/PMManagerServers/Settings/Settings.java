package com.matcracker.PMManagerServers.Settings;

import java.io.IOException;

import com.matcracker.PMManagerServers.Main;
import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Languages.LangSelector;
import com.matcracker.PMManagerServers.Utility.Utility;

public class Settings{
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
	* 
	*/
	
	public static void settingsMenu() throws IOException{
	    Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(BaseLang.translate("pm.title.settings"));
		System.out.println("1- " + BaseLang.translate("pm.settings.language"));
		System.out.println("2- " + BaseLang.translate("pm.settings.serversManager"));
		System.out.println("3- " + BaseLang.translate("pm.settings.reset"));
		System.out.println("4- " + BaseLang.translate("pm.settings.plugManager"));
		System.out.println("5- " + BaseLang.translate("pm.standard.back"));
		
		System.out.print("\nChoose option: ");
		String options = Utility.keyword.readLine();
		
		if(options.equalsIgnoreCase("1"))
			LangSelector.langMenu();
		
		if(options.equalsIgnoreCase("2"))
			PMServersManager.serverManagerMenu();
		
		if(options.equalsIgnoreCase("3"))
			Resetter.resetterMenu();
		
		if(options.equalsIgnoreCase("4"))
			PluginManager.plugMenu();
		
		if(options.equalsIgnoreCase("5"))
			Main.mainMenu();
		
		settingsMenu();
	}
}
