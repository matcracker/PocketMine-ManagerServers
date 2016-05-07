package com.matcracker.PMManagerServers.Manager.Editing;

import java.io.IOException;

import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Manager.Manager;
import com.matcracker.PMManagerServers.Utility.Utility;

public class Editor {
  /** _____           _        _   __  __ _                   __  __                                   _____                              
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

	public static void editorMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(BaseLang.translate("pm.title.editor"));
		System.out.println("1- " + BaseLang.translate("pm.editor.properties"));
		System.out.println("2- " + BaseLang.translate("pm.editor.performance"));
		System.out.println("3- " + BaseLang.translate("pm.standard.back"));
		int sel = Utility.readInt(BaseLang.translate("pm.chooise.option") + " ", null);
		
		if(sel == 1)
			Properties.propertiesMenu();
		
		if(sel == 2)
			Performance.performanceMenu();
		
		if(sel == 3)
			Manager.managerMenu();
		
		editorMenu();
		
	}
}
