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
        System.out.println("/restart <software|(servername|servernumber)|all> : Restart PocketMine-ManagerServers or one or all servers.");
        System.out.println("/restore <(servername|servernumber)|all> : Restore a backup of one or all servers");
        System.out.println("/setstart <commander|menu> : Set the initial interface when the program starts");
        System.out.println("/start <(servername|servernumber)|all> : Start one or all servers");
        System.out.println("/stop <(servername|servernumber)|all> : Stop one or all servers");
	}
}
