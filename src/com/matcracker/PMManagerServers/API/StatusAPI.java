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
	
package com.matcracker.PMManagerServers.API;

import java.io.File;

import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;

public class StatusAPI {
	
	/**
	 * @param index from 1 to max number of server
	 * @return status of server performance
	 */
	public static String getBackuped(int index){
		String status = Utility.readStringData(new File("Backups" + File.separator + "Status" + File.separator + "BackupStatus_"+ index + ".pm"));
		
		if(status != null)
			return status;
		
		return BaseLang.translate("pm.status.error");
	}
	
	/**
	 * @param index from 1 to max number of server
	 * @return status of server performance
	 */
	public static String getPerformace(int index) {
		String status = Utility.readStringData(new File("Performance" + File.separator + "PerformanceStatus_" + index + ".pm"));
		
		if(status != null)
			return status;
		
		return BaseLang.translate("pm.status.error");
	}
	
	/**
	 * @param index from 1 to max number of server
	 * @return status of server
	 */
	public static String getStatus(int index){
		String status = Utility.readStringData(new File("Installations" + File.separator + "Status" + File.separator + "Status_" + index + ".pm"));
		
		if(status != null)
			return status;
		
		return BaseLang.translate("pm.status.error");
	}
	
	/**
	 * @param index from 1 to max number of server
	 * @return version of server
	 */
	public static String getVersion(int index){
		String status =  Utility.readStringData(new File("Installations" + File.separator + "Version" + File.separator + "Status_" + index + ".pm"));
		
		if(status != null)
			return status;
		
		return BaseLang.translate("pm.status.error");
	}
	
	/**
	 * @param status name of performance status(Only Personal, High, Medium, Low)
	 * @param index
	 */
	public static void setBackuped(String status, int index){
		if(status != null)
			Utility.writeStringData(new File("Backups" + File.separator + "Status" + File.separator + "BackupStatus_"+ index + ".pm"), status);
		else
			Utility.waitConfirm("Status can't be null!");
	}
	
	/**
	 * @param status name of performance status(Only Personal, High, Medium, Low)
	 * @param index
	 */
	public static void setPerformance(String status, int index) {
		if(status != null)
			Utility.writeStringData(new File("Performance" + File.separator + "PerformanceStatus_" + index + ".pm"), status);
		else
			Utility.waitConfirm("Status can't be null!");
	}
	
	/**
	 * @param status name of status(Only Downloaded, Not downloaded, Installed, Not installed)
	 * @param index from 1 to max number of server
	 */
	public static void setStatus(String status, int index){
		if(status != null)
			Utility.writeStringData(new File("Installations" + File.separator + "Status" + File.separator + "Status_" + index + ".pm"), status);
		else
			Utility.waitConfirm("Status can't be null!");
	}
	
	/**
	 * @param version name of version (Only Stable, Beta, Dev, Soft)
	 * @param index from 1 to max number of server
	 */
	public static void setVersion(String version, int index){
		if(version != null)
			Utility.writeStringData(new File("Installations" + File.separator + "Version" + File.separator + "Status_" + index + ".pm"), version);
		else
			Utility.waitConfirm("Version can't be null!");
	}
	
}
