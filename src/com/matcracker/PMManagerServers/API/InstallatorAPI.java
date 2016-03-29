package com.matcracker.PMManagerServers.API;

import java.io.File;

import com.matcracker.PMManagerServers.Utility.Utility;

public class InstallatorAPI {
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
	
	/**
	 * @param version name of version (Only Stable, Beta, Dev, Soft)
	 * @param index from 0 to max number of server
	 */
	public static void setVersion(String version, int index){
		index++;
		Utility.writeStringData(new File("Installations" + File.separator + "Version" + File.separator + "Status_" + index + ".pm"), version);
	}
	
	/**
	 * @param index from 0 to max number of server
	 * @return version of server
	 */
	public static String getVersion(int index){
		index++;
		return Utility.readStringData(new File("Installations" + File.separator + "Version" + File.separator + "Status_" + index + ".pm"));
	}
	
	/**
	 * @param status name of status(Only Downloaded, Not downloaded, Installed, Not installed)
	 * @param index from 0 to max number of server
	 */
	public static void setStatus(String status, int index){
		index++;
		Utility.writeStringData(new File("Installations" + File.separator + "Status" + File.separator + "Status_" + index + ".pm"), status);
	}
	
	/**
	 * @param index from 0 to max number of server
	 * @return status of server
	 */
	public static String getStatus(int index){
		index++;
		return Utility.readStringData(new File("Installations" + File.separator + "Status" + File.separator + "Status_" + index + ".pm"));
	}
	
}
