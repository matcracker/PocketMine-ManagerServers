package com.matcracker.PMManagerServers.API;

import java.io.File;

import com.matcracker.PMManagerServers.Loaders.PluginsLoader;

public class PluginsAPI {
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
	 * @param pluginFileName without .jar
	 * @return author of plugin
	 */
	public static String getPluginAuthor(String pluginFileName){
		return (String) PluginsLoader.pluginExec(new File("plugins" + File.separator + pluginFileName + ".jar"), "getAuthor");
	}
	
	/**
	 * @param pluginFileName without .jar
	 * @return version of plugin
	 */
	public static String getPluginVersion(String pluginFileName){
		return (String) PluginsLoader.pluginExec(new File("plugins" + File.separator + pluginFileName + ".jar"), "getVersion");
	}
	
	/**
	 * @param pluginFileName without .jar
	 * @return API version used by plugin
	 */
	public static String getPluginAPI(String pluginFileName){
		return (String) PluginsLoader.pluginExec(new File("plugins" + File.separator + pluginFileName + ".jar"), "getAPIVersion");
	}
	
	/**
	 * @param pluginFileName
	 * @return name of plugin
	 */
	public static String getPluginName(String pluginFileName){
		return (String) PluginsLoader.pluginExec(new File("plugins" + File.separator + pluginFileName + ".jar"), "getName");
	}

}
