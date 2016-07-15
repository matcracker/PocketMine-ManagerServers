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
	
package com.matcracker.PMManagerServers.API;

import java.io.File;

import com.matcracker.PMManagerServers.utility.Utility;

public class UtilityServersAPI{	
	/**
	 * @param folder name of folder
	 * @param name of file
	 * @param index from 1 to max number of server
	 * @return true if exist
	 */
	public static boolean checkServersFile(String folder, String name, int index){
		File file;
		if(index < 0){
			file = new File(folder + File.separator + name + ".pm");
		}else{
			file = new File(folder + File.separator + name + index + ".pm");
		}
		
		if(file.exists())
			return true;
		else
			return false;
	
	}
	
	/**
	 * @return Server_Minecraft_PE
	 */
	public static String getDefaultServerName(){
		return Utility.defaultServersName;
	}
	
	/**
	 * @return path of download
	 */
	@Deprecated
	public static String getDownloadPath(){
		return Utility.readStringData(new File("Path" + File.separator + "downloadPath.pm"));
	}
	
	/**
	 * @param index from 1 to max number of server
	 * @return name of server
	 */
	public static String getNameServer(int index){
		String server = Utility.readStringData(new File("ServersName" + File.separator + "ServerName_" + index + ".pm"));
		
		if(server != null)
			return server;
		
		return "Error on getting server name!";
	}
	
	/**
	 * @return number of path
	 */
	public static int getNumberPaths(){
		File folder = new File("Path");
		return folder.listFiles().length;
	}
	
	/**	
	 * @return number of server
	 */
	public static int getNumberServers(){
		return Utility.readIntData(new File("Data" + File.separator + "nservers.pm"));
	}
	
	/**
	 * @param index from 1 to max number of server
	 * @return content of path
	 */
	public static String getPath(int index){
		String path = Utility.readStringData(new File("Path" + File.separator + "path_" + index + ".pm"));
		
		if(path != null)
			return path;
					
		return "Error on getting path!";
	}
	
	/**
	 * @param name of default server
	 */
	public static void setDefaultServerName(String name){
		if(name != null)
			Utility.defaultServersName = name;
		else
			Utility.waitConfirm("Can't set a null value!");
	}
	
	/**
	 * @param path of download
	 */
	@Deprecated
	public static void setDownloadPath(String path){
		if(path != null)
			Utility.writeStringData(new File("Path" + File.separator + "downloadPath.pm"), path);
		else
			System.err.println("Path can't be null!");
			
	}
	
	/**
	 * @param index from 1 to max number of server
	 * @param content name of server
	 */
	public static void setNameServer(int index, String content){
		if(content != null)
			Utility.writeStringData(new File("ServersName" + File.separator + "ServerName_" + index + ".pm"), content);
	}
	
	/**
	 * @param content number of server
	 */
	public static void setNumberServer(int content){
		if(content > 0)
			Utility.writeIntData(new File("Data" + File.separator + "nservers.pm"), content);
		else
			System.err.println("Can't set a negative value!");
	}
	
	/**
	 * @param index from 1 to max number of server
	 * @param path file path
	 */
	public static void setPath(int index, String path){
		if(path != null)
			Utility.writeStringData(new File("Path" + File.separator + "path_" + index + ".pm"), path);
		else
			System.err.println("Path can't be null!");
			
	}
}
