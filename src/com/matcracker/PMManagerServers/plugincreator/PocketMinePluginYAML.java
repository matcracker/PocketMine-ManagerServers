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

package com.matcracker.PMManagerServers.plugincreator;

import java.io.File;

import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;

public class PocketMinePluginYAML {
	/**
	 * plugin.yml
	 */
	private String pluginName = "PluginByPM-MS";
	private String className = "Main";
	private String version = "1.0";
	private String author = "PocketMine-ManagerServers";
	private String apiVersion = "3.0.0";
	private String namespace = "src\\" + author;
	
	public PocketMinePluginYAML(){}
	
	public PocketMinePluginYAML(String pluginName, String className, String version, String author, String apiVersion, String namespace){
		setPluginName(pluginName);
		setClassName(className);
		setVersion(version);
		setAuthor(author);
		setAPIVersion(apiVersion);
		setNamespace(namespace);
	}
	
	public String getPluginName(){
		return pluginName;
	}
	
	public void setPluginName(String pluginName){
		this.pluginName = pluginName;
	}
	
	public String getClassName(){
		return className;
	}
	
	public void setClassName(String className){
		this.className = className;
	}
	
	public String getVersion(){
		return version;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getAPIVersion(){
		return apiVersion;
	}
	
	public void setAPIVersion(String apiVersion){
		this.apiVersion = apiVersion;
	}
	
	public String getNamespace(){
		return namespace;
	}
	
	public void setNamespace(String namespace){
		this.namespace = namespace;
	}
	
	/**
	 * Get informations about plugin.yml
	 */
	public void requestYAMLData(){
		String pluginName = Utility.readString(BaseLang.translate("pm.serverPlugins.pluginName") + " ", null);
		if(!pluginName.isEmpty()) this.pluginName = pluginName;
		
		String namespace = Utility.readString(BaseLang.translate("pm.serverPlugins.pluginNamespace") + " ", "[" + BaseLang.translate("pm.standard.example") + " src\\author\\pluginname]");
		if(!namespace.isEmpty()) this.namespace = namespace;
		
		String className = Utility.readString(BaseLang.translate("pm.serverPlugins.pluginMainClass") + " ", "[" + BaseLang.translate("pm.standard.example") + " Main]");
		if(!className.isEmpty()) this.className = className;
		
		String version = Utility.readString(BaseLang.translate("pm.serverPlugins.pluginVersion") + " ", null);
		if(!version.isEmpty()) this.version = version;
		
		String author = Utility.readString(BaseLang.translate("pm.serverPlugins.pluginAuthor") + " ", null);
		if(!author.isEmpty()) this.author = author;
		
		String apiVersion = Utility.readString(BaseLang.translate("pm.serverPlugins.pluginAPI") + " ", "[" + BaseLang.translate("pm.standard.example") + " 3.0.0]");
		if(!apiVersion.isEmpty()) this.apiVersion = apiVersion;
	}
	
	/**
	 * Create the file plugin.yml (It's a good thing use getPluginYAML() method first)
	 */
	public void createPluginYAML(){
		File plcr = new File("PluginsCreator" + File.separator + pluginName);
		if(!plcr.exists()) plcr.mkdirs();
		
		Utility.writeStringData(new File(plcr + File.separator + "plugin.yml"), buildPluginYAML());
	}
	
	protected String buildPluginYAML(){
		String temp = namespace.replaceAll("src/", "");
		return "name: " + pluginName + "\n" + 
			   "author: " + author + "\n" +
			   "version: " + version + "\n" +
			   "api: [" + apiVersion + "]\n" +
			   "main: " + temp + "\\" + className;
	}

}
