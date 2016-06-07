package com.matcracker.PMManagerServers.API;

import java.io.File;
import com.matcracker.PMManagerServers.Main;
import com.matcracker.PMManagerServers.utility.Utility;

public class APIManager{
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
	
	private static final String APIVersion = "1.2";
	private static final String softwareVersion = "1.1";
	
	/**
	 * @return API version
	 */
	public static String getAPIVersion(){
		return APIVersion;
	}
	
	/**
	 * @return version of software
	 */
	public static String getVersion(){
		return softwareVersion;
	}
	
	/**
	 * @param mode
	 */
	public static void setDevMode(boolean mode){
		Utility.writeStringData(new File("Data" + File.separator + "devmode.pm"), String.valueOf(mode));
	}
	
	/**
	 * @return true if devmode is enabled, false if not
	 */
	public static boolean isDevMode(){
		String value = Utility.readStringData(new File("Data" + File.separator + "devmode.pm"));
		
		if(value.equalsIgnoreCase("true"))
			return true;
		else
			return false;
	}
	
	/**
	 * @param mode
	 */
	public static void setCommandsMode(boolean mode){
		Utility.writeStringData(new File("Data" + File.separator + "commandsmode.pm"), String.valueOf(mode));
	}
	
	/**
	 * @return true if commandsmode is enabled, false if not
	 */
	public static boolean isCommandsMode(){
		String value = Utility.readStringData(new File("Data" + File.separator + "commandsmode.pm"));
		
		if(value.equalsIgnoreCase("true"))
			return true;
		else
			return false;
	}
	
	/**
	 * @return true if user enabled the software in "server mode"
	 */
	public static boolean isServerMode(){
		return Main.isServerVersion;
	}
	
	/**
	 * @param mode if true enable a mode for Server's OS
	 */
	public static void setServerMode(boolean mode){
		Main.isServerVersion = mode;
	}
	
}
