package com.matcracker.PMManagerServers;

import java.util.Calendar;

import com.matcracker.PMManagerServers.API.APIManager;
import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Utility.Utility;

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
	
	public static void devMenu(String menu){
		if(APIManager.isDevMode()){
			System.out.println("\n&e==={&bDEVMODE&e}===&5");
			System.out.println("6- Memory usage (MB/GB)");
			System.out.println("7- System information");
			System.out.println("8- Restart PocketMine-ManagerServers&f\n");
			
			if(menu.equalsIgnoreCase("6"))
				getMemoryUsage();
			
			if(menu.equalsIgnoreCase("7"))
				systemInfo();
			
			if(menu.equalsIgnoreCase("8"))
				reboot();
		}
		

	}
	
	public static void getMemoryUsage(){
		Runtime runtime = Runtime.getRuntime();

		int mb = 1024 * 1024;
		int gb = mb * 1024;
		
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		long usedMemory = (allocatedMemory - freeMemory);
		
		System.out.println("Free memory: " + (freeMemory / mb) + " MB");
		System.out.println("Allocated memory: " + (allocatedMemory / mb) + " MB");
		System.out.println("Max memory: " + (maxMemory / gb) + " GB");
		System.out.println("Total free memory: " + ((freeMemory + (maxMemory - allocatedMemory)) / gb) + " GB");
        System.out.println("Used Memory: " + (usedMemory / mb) + " MB");

        System.out.println();
		Utility.waitConfirm(BaseLang.translate("pm.standard.enter"));
		Main.mainMenu();
	}
	
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
	
	public static void reboot(){
		try{
			Utility.openSoftware("software", "run.bat");
		}catch(NullPointerException e){
			System.out.println("run.bat not found");
		}
		System.exit(0);
	}
}
