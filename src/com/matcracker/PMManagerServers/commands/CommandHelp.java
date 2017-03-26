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

import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class CommandHelp{
	public static void command(){
		System.out.println(UtilityColor.YELLOW);
        System.out.println("/backup <(servername|servernumber)|all> : " + BaseLang.translate("pm.cmdHelp.backup"));
        System.out.println("/clear : " + BaseLang.translate("pm.cmdHelp.clear"));
        System.out.println("/edit <performance|properties> [PerformanceType] [(servername|servernumber)]: " + BaseLang.translate("pm.cmdHelp.edit"));
        System.out.println("/exit : " + BaseLang.translate("pm.cmdHelp.exit"));
        System.out.println("/help : " + BaseLang.translate("pm.cmdHelp.help"));
        System.out.println("/language : " + BaseLang.translate("pm.cmdHelp.language"));
        System.out.println("/menu : " + BaseLang.translate("pm.cmdHelp.menu"));
        System.out.println("/restart <software|all> [time] : " + BaseLang.translate("pm.cmdHelp.restart"));
        System.out.println("/restore <(servername|servernumber)|all> : " + BaseLang.translate("pm.cmdHelp.restore"));
        System.out.println("/setstart <commander|menu> : " + BaseLang.translate("pm.cmdHelp.setstart"));
        System.out.println("/start <(servername|servernumber)|all> : " + BaseLang.translate("pm.cmdHelp.start"));
        System.out.println("/stop : " + BaseLang.translate("pm.cmdHelp.stop"));
	}
}
