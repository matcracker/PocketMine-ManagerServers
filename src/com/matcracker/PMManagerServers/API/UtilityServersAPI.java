package com.matcracker.PMManagerServers.API;

import java.io.File;

import com.matcracker.PMManagerServers.Utility.Utility;
import com.matcracker.PMManagerServers.Utility.UtilityServers;

public class UtilityServersAPI{
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
		
	public static int getNumberServers(){
		return Utility.readIntData(new File("Data" + File.separator + "nservers.pm"));
	}
	
	/**
	 * 
	 * @param index from 0 to 9
	 * @param content 
	 */
	public static void setNameServer(int index, String content){
		index++;
		Utility.writeStringData(new File("ServersName" + File.separator + "ServerName_" + index + ".pm"), content);
	}
	
	public static void setNumberServer(int content){
		if(content > 0)
			Utility.writeIntData(new File("Data" + File.separator + "nservers.pm"), content);
		else
			System.err.println("Can't set a negative value!");
	}
	
	/**
	 * 
	 * @param index from 0 to 9
	 * @return
	 */
	public static String getNameServer(int index){
		index++;
		return Utility.readStringData(new File("ServersName" + File.separator + "ServerName_" + index + ".pm"));
	}

	public static String getDefaultServerName(){
		return UtilityServers.defaultServersName;
	}
	
	public static void setDefaultServerName(String name){
		UtilityServers.defaultServersName = name;
	}
	
	/**
	 * 
	 * @param index from 0 to 9
	 * @return
	 */
	public static String getPath(int index){
		return Utility.readStringData(new File("Path" + File.separator + "Path_" + index + ".pm"));
	}
	
	/**
	 * 
	 * @param index from 0 to 9
	 * @param path
	 */
	
	public static void setPath(int index, String path){
		index++;
		if(path != null)
			Utility.writeStringData(new File("Path" + File.separator + "Path_" + index + ".pm"), path);
		else
			System.err.println("Path can't be null!");
			
	}
	
	
	
}
