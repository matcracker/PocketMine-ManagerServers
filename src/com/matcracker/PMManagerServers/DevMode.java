package com.matcracker.PMManagerServers;

import java.io.IOException;
import java.util.Calendar;

import com.matcracker.PMManagerServers.API.APIManager;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.ProcessManager;
import com.matcracker.PMManagerServers.utility.Utility;

public class DevMode{
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
	
	public static void devMenu(String menu) throws IOException{
		if(APIManager.isDevMode()){
			System.out.println("\n&e==={&bDEVMODE&e}===&5");
			System.out.println("6- " + BaseLang.translate("pm.devmenu.memory") + " (MB/GB)");
			System.out.println("7- " + BaseLang.translate("pm.devmenu.systemInfo"));
			System.out.println("8- Commands mode");
			System.out.println("9- " + "Get list of enabled processes");
			System.out.println("10- " + BaseLang.translate("pm.devmenu.restart") + "&f\n");
			
			if(menu.equalsIgnoreCase("6"))
				getMemoryUsage();
			
			if(menu.equalsIgnoreCase("7"))
				systemInfo();
			
			if(menu.equalsIgnoreCase("8"))
				CommandsMode.menu();
			
			if(menu.equalsIgnoreCase("9")){
				ProcessManager.getListOfProcesses();
				Utility.waitConfirm(BaseLang.translate("pm.standard.enter"));
				Main.mainMenu();
			}
					
			if(menu.equalsIgnoreCase("10"))
				reboot();
		}
	}
	
	/**
	 * Show all the memories usage
	 */
	public static void getMemoryUsage(){
		Runtime runtime = Runtime.getRuntime();

		double mb = 1024 * 1024;

		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		long usedMemory = (allocatedMemory - freeMemory);
		long totalFreeMemory =(freeMemory + (maxMemory - allocatedMemory));
		
		System.out.printf("Free memory: %.2f MB\n", freeMemory / mb);
		System.out.printf("Allocated memory: %.2f MB\n", allocatedMemory / mb);
		System.out.printf("Max memory: %.2f MB\n", maxMemory / mb);
		System.out.printf("Total free memory: %.2f MB\n", totalFreeMemory / mb);
        System.out.printf("Used Memory: %.2f MB\n", usedMemory / mb);

        System.out.println();
		Utility.waitConfirm(BaseLang.translate("pm.standard.enter"));
		Main.mainMenu();
	}
	
	/**
	 * Show the system's information
	 */
	public static void systemInfo(){
		Calendar calendar = Calendar.getInstance();
		String strDate = calendar.getTime().toString();
		
		System.out.println("&eSystem Information&f");
		System.out.println("Current time is " + strDate);
		System.out.println("OS Name: " + Utility.getOSName());
		System.out.println("OS Version: " + System.getProperty("os.version"));
		System.out.println("OS Architecture: " + System.getProperty("os.arch"));
		System.out.println("Java version: " + System.getProperty("java.version"));
		System.out.println("Java vendor: " + System.getProperty("java.vendor"));
		System.out.println("Bit Architecture: " + System.getProperty("sun.arch.data.model") + " bit");
		
		System.out.println();
		Utility.waitConfirm(BaseLang.translate("pm.standard.enter"));
		Main.mainMenu();
	}
	
	/**
	 * Reboot software
	 */
	public static void reboot(){
		try{
			if(Utility.getOSName().contains("Windows"))
				Utility.openSoftware("software", "run.bat");
		}catch(IllegalArgumentException e){
			System.out.println("run.bat or run.sh not found");
		}
		System.exit(0);
	}
}
