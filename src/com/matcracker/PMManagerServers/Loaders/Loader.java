package com.matcracker.PMManagerServers.Loaders;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.Main;
import com.matcracker.PMManagerServers.Utility.Utility;

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
	
	static int nservers = 0;
	private static String[] path = {"", "", "", "", "", "", "", "", "", ""};
	private static String[] nameServers = {"", "", "", "", "", "", "", "", "", ""};
	static String[] numberServers = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth"};
	static String[] numberServers2 = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth"};
	
	static File checknservers = new File("Data" + File.separator + "nservers.pm");
	static Object[] checkPath = new Object[] {false, false, false, false, false, false, false, false, false, false};
	static boolean[] checkNameServer = new boolean[] {false, false, false, false, false, false, false, false, false, false};
	
	public static void startLoader() throws InterruptedException{
		File dirPath = new File("Path");
		File dirServername = new File("ServersName");
		File dirData = new File("Data");
		File dirPerformance = new File("Performance");
		File dirUtils = new File("Utils");
		File dirInstallation = new File("Installations");
		File dirLanguages = new File("Languages");
		File dirBackups = new File("Backups");
		File dirBackupsStatus = new File("Backups" + File.separator + "Status");
		File dirBackupServers = new File("Backups" + File.separator + "Servers");
		File checkLicense = new File("LICENSE.pdf");
		
		//TODO: Add checklicense in the if
		if(dirPath.exists() && dirServername.exists() && dirData.exists() && dirPerformance.exists() && dirUtils.exists() 
			&& dirInstallation.exists()	&& dirLanguages.exists() && dirBackups.exists()){
			return;
		}else{
			System.out.println("Preparing the first start...");
			Thread.sleep(1500);
			
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

			for(int i = 0; i <= 100; i++)
				System.out.println("Loading resource " + i + "%");
					
		}
			
		
	}
	
	public static void completeLoader() throws IOException{
		if(checknservers.exists()){
			nservers = Utility.readData(new File("Data" + File.separator + "nservers.pm"));
			return;
		}else{
			do{
				Utility.cleanScreen();
				System.out.println("========================<PocketMine Manager Servers>============================");
				System.out.println("-------------------------<Complete the informations>----------------------------");
				System.out.print("How many servers do you want to manage? <1/2/3/.../10> : ");
				
				try{
					nservers = Integer.valueOf(Utility.keyword.readLine());
				
				}catch (NumberFormatException | IOException e){
					System.out.println(Utility.inputError);
				}
				
				if(nservers > 10){
					System.out.println("ERROR! You have exceeded the maximum number of servers available. Please reduce the amount!");
					System.in.read();
					
				}else if(nservers <= 0){
					System.out.println("ERROR! You have to manage one or more server! (MAX TEN!!)");
					System.in.read();
				}
			}while(nservers > 10 || nservers <= 0);
			
			Utility.writeIntData(new File("Data" + File.separator +"nservers.pm"), nservers);
			
		}
		
		Utility.checking(checkNameServer, checkPath);
		
		Utility.cleanScreen();
		System.out.println("========================<PocketMine Manager Servers>============================");
		System.out.println("-------------------------<Complete the informations>----------------------------");
		System.out.printf("If you do not enter a name for your server , by default it will be '%s'\n", Utility.defaultServersName);
		
		if(nservers >= 1){
			if(!checkNameServer[nservers - 1]){
				return;
			}else{
				Utility.selection(nservers, nameServers, numberServers, numberServers2);

				for(int i = 1; i <= nservers; i++)
					Utility.writeStringData(new File("ServersName" + File.separator + "ServerName_" + i + ".pm"), nameServers[i-1]);
			}
		}else{
			System.out.println("An error occured!");
		}
		
		System.out.println("Complete! Press ENTER to continue.");
		System.in.read();

	}
}