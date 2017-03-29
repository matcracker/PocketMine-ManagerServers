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

import com.matcracker.PMManagerServers.loaders.PluginsLoader;

public class PluginsAPI {
   
	private String pluginFileName;
	
	public PluginsAPI(){}
	
	/**
	 * @param pluginFileName the name of plugin (e.g. plugin.jar)
	 */
	public PluginsAPI(String pluginFileName) {
		setPluginFileName(pluginFileName);
	}
	
	/**
	 * @param pluginFile the file of plugin (e.g. plugin.jar)
	 */
	public PluginsAPI(File pluginFile){
		if(pluginFile != null)
			setPluginFileName(pluginFile.getName());
	}
	
	/*
	 * Return the name of plugin
	 */
	public String getPluginFileName() {
		return pluginFileName;
	}

	public void setPluginFileName(String pluginFileName) {
		if(pluginFileName != null){
			if(pluginFileName.endsWith(".jar"))
				pluginFileName = pluginFileName.replaceAll(".jar", "");
			this.pluginFileName = pluginFileName;
		}
	}

	/**
	 * @return API version used by plugin
	 */
	public String getPluginAPI(){
		return (String) PluginsLoader.pluginExec(new File("plugins" + File.separator + pluginFileName + ".jar"), "getAPIVersion");
	}
	
	/**
	 * @return author of plugin
	 */
	public String getPluginAuthor(){
		return (String) PluginsLoader.pluginExec(new File("plugins" + File.separator + pluginFileName + ".jar"), "getAuthor");
	}
	
	/**
	 * @return name of plugin
	 */
	public String getPluginName(){
		return (String) PluginsLoader.pluginExec(new File("plugins" + File.separator + pluginFileName + ".jar"), "getName");
	}
	
	/**
	 * @return version of plugin
	 */
	public String getPluginVersion(){
		return (String) PluginsLoader.pluginExec(new File("plugins" + File.separator + pluginFileName + ".jar"), "getVersion");
	}
	
	/**
	 * This method load only an specified plugin
	 */
	public void loadPlugin(){
		PluginsLoader.pluginExec(new File(PluginsLoader.folder + File.separator + pluginFileName + ".jar"), "onExecute");
	}
	
	/**
	 * This method unload only an specified plugin
	 */
	public void unloadPlugin(){
		PluginsLoader.pluginExec(new File(PluginsLoader.folder + File.separator + pluginFileName + ".jar"), "onDisable");
	}

}
