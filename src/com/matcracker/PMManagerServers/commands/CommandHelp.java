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

import com.matcracker.PMManagerServers.utility.UtilityColor;

public class CommandHelp{
	public static void command(){
		System.out.println(UtilityColor.COLOR_YELLOW);
        System.out.println("/backup <(servername|servernumber)|all> : Create a backup of one or all servers");
        System.out.println("/clear : Clean the console screen.");
        System.out.println("/edit <performance|properties> [PerformanceType] [(servername|servernumber)]: Edit your server's performace or properties");
        System.out.println("/exit : Leave the program.");
        System.out.println("/help : Show help page");
        System.out.println("/language: Change language of program.");
        System.out.println("/menu : Return in the main menu");
        System.out.println("/restart <software|all> [time] : Restart PocketMine-ManagerServers or one or all servers.");
        System.out.println("/restore <(servername|servernumber)|all> : Restore a backup of one or all servers");
        System.out.println("/setstart <commander|menu> : Set the initial interface when the program starts");
        System.out.println("/start <(servername|servernumber)|all> : Start one or all servers");
        System.out.println("/stop : Stop all servers");
	}
}
