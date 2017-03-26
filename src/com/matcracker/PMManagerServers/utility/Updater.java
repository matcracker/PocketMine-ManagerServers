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
	
package com.matcracker.PMManagerServers.utility;

import java.io.File;
import java.io.IOException;

import org.rauschig.jarchivelib.ArchiveFormat;

import com.matcracker.PMManagerServers.DevMode;
import com.matcracker.PMManagerServers.API.APIManager;

public class Updater{
	private final String tag = "https://github.com/matcracker/PocketMine-ManagerServers/releases/download/v";
	private String newVersion;
	private String finalURL;
	
	public boolean findUpdate(){
		String v = APIManager.getVersion();
		String[] split = v.split("\\.");
		
		int v1 = Integer.parseInt(split[0]);
		int v2 = Integer.parseInt(split[1]);
		int v3 = Integer.parseInt(split[2]);

		String vTag, tryTag; //Tag version (0.0.0 <-> x.y.z)
		
		for(int x = v1; x <= v1 + 1; x++){
			//System.out.println("X = " + x);
			for(int y = v2; y <= v2 + 1; y++){
				//System.out.println("Y = " + y);
				for(int z = 0; z <= 10; z++){
					//System.out.println("Z = " + z);
					vTag = x + "." + y + "." + z;
					//System.out.println("vTag = " + vTag);
					tryTag = tag + vTag + "/PocketMine-ManagerServers_v" + vTag + ".zip";
					if(Utility.existURL(tryTag) && !vTag.equalsIgnoreCase(v) && (x > v1 || y > v2 || z > v3)){
						this.newVersion = vTag;
						this.finalURL = tryTag;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @param updater if true auto-search update for software
	 */
	public void setAutoSearch(boolean updater){
		Utility.writeStringData(new File("Data" + File.separator + "updater.pm"), String.valueOf(updater));
	}
	
	/**
	 * @return true if the software auto-find update
	 */
	public boolean getAutoSearch(){
		return Boolean.parseBoolean(Utility.readStringData(new File("Data" + File.separator + "updater.pm")));
	}
	
	public void updateResources(){
		System.out.println(UtilityColor.GREEN + "Find new stable update! Version: " + UtilityColor.YELLOW + this.newVersion);
		String conf = Utility.readString(UtilityColor.WHITE + "Do you want update the software? <Y/n>: ", null);
		//Example: https://github.com/matcracker/PocketMine-ManagerServers/releases/download/v1.1/PocketMine-ManagerServers_v1.1.zip
		if(conf.equalsIgnoreCase("y")){
			try{
				Utility.downloadFile(this.finalURL, "Utils");
				String zip = "Utils" + File.separator + "PocketMine-ManagerServers_v" + this.newVersion + ".zip";
				System.out.println(UtilityColor.WHITE + "Unzipping update...");
				Zipper.unzip(zip, ".", ArchiveFormat.ZIP, null);
				System.out.println("Rebooting...");
				DevMode.reboot();
			}catch(IOException e){
				Utility.waitConfirm(UtilityColor.RED + "Something goes wrong!");
				return;
			}
		}
	}
	
	/**
	 * @return the new version format
	 */
	public String getUpdatedVersion(){
		return this.newVersion;
	}
}
