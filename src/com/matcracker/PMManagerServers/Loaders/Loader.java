package com.matcracker.PMManagerServers.Loaders;

import java.io.File;

public class Loader {
  /** _____           _        _   __  __ _                   __  __                                   _____                              
	*|  __ \         | |      | | |  \/  (_)                 |  \/  |                                 / ____|                             
	*| |__) |__   ___| | _____| |_| \  / |_ _ __   ___ ______| \  / | __ _ _ __   __ _  __ _  ___ _ _| (___   ___ _ ____   _____ _ __ ___ 
	*|  ___/ _ \ / __| |/ / _ \ __| |\/| | | '_ \ / _ \______| |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '__\___ \ / _ \ '__\ \ / / _ \ '__/ __|
	*| |  | (_) | (__|   <  __/ |_| |  | | | | | |  __/      | |  | | (_| | | | | (_| | (_| |  __/ |  ____) |  __/ |   \ V /  __/ |  \__ \
	*|_|   \___/ \___|_|\_\___|\__|_|  |_|_|_| |_|\___|      |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_| |_____/ \___|_|    \_/ \___|_|  |___/
	*                                                                                   __/ |                                             
	*                                                                                  |___/                                              
	*Copyright (C) 2015 @author matcracker
	*
	*This program is free software: you can redistribute it and/or modify 
	*it under the terms of the GNU Lesser General Public License as published by 
	*the Free Software Foundation, either version 3 of the License, or 
	*(at your option) any later version.
	*/
	public static void startLoader() throws InterruptedException{
		File dirPath = new File("Path");
		File dirServername = new File("ServersName");
		File dirData = new File("Data");
		File dirPerformance = new File("Performance");
		File dirUtils = new File("Utils");
		File dirInstallation = new File("Installations");
		File dirLanguages = new File("Languages");
		File dirBackups = new File("Backups");
		File dirBackupsStatus = new File("Backups/Status");
		File dirBackupServers = new File("Backups/Servers");
		
		if(dirPath.exists() && dirServername.exists() && dirData.exists() && dirPerformance.exists() && dirUtils.exists() 
			&& dirInstallation.exists()	&& dirLanguages.exists() && dirBackups.exists()){
			
		}else{
			System.out.println("Preparing the first start...");
			Thread.sleep(1000);
			dirPath.mkdir();
			dirServername.mkdir();
			dirData.mkdir();
			dirPerformance.mkdir();
			dirUtils.mkdir();
			dirInstallation.mkdir();
			dirLanguages.mkdir();
			dirBackups.mkdir();
			dirBackupsStatus.mkdir();
			dirBackupServers.mkdir();
			
			for(int i = 1; i <= 10; i++){
				
			}
			
			for(int i = 0; i <= 100; i++)
				System.out.println("Loading resource " + i + "%");
			
		
		}
			
		
	}
	
	public static void completeLoad(){
		
	}
}
