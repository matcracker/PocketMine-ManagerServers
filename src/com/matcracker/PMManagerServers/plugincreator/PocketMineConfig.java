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
import java.util.ArrayList;

import com.matcracker.PMManagerServers.utility.Utility;

public class PocketMineConfig {
	
	private ArrayList<String> lines = new ArrayList<String>();
	private String configName;
	private PocketMinePluginYAML yaml;
	
	public PocketMineConfig(PocketMinePluginYAML yaml, String configName){
		this.yaml = yaml;
		setConfigName(configName);
	}
	
	private void setConfigName(String configName){
		if(configName == null || configName.isEmpty())
			configName = "config.yml";
		
		if(!configName.endsWith(".yml"))
			configName += ".yml";
		
		this.configName = configName;
	}
	
	public String getConfigName(){
		return configName;
	}
	
	public String getConfig(){
		String sx = "";
		
		for(String s : lines)
			sx += s;
		
		return sx;
	}
	private final String space = "    ";
	/**
	 * @param key
	 * @param value
	 */
	public void addValue(String key, String value){
		if(key == null || value == null) return;
		
		if(value.contains(";")){
			lines.add(key + ":\n");
			for(String val : value.split(";"))
				lines.add(space + "- " + val + "\n");
			
		}else
			lines.add(key + ": " + value + "\n");
	}
	
	public void addValues(String key, String[] values){
		if(key == null || values == null) return;
		
		lines.add(key + ":\n");
		for(String val : values)
			lines.add(space + "- " + val + "\n");
		
	}
	
	public void addLine(String line){
		lines.add(line + "\n");
	}
	
	public void clearConfig(){
		lines.clear();
	}
	
	public void saveConfig(){
		String data = getConfig();
		
		File config = new File("PluginsCreator" + File.separator + yaml.getPluginName() + File.separator + "resources");
		if(!config.exists()) config.mkdirs();
		
		Utility.writeStringData(new File(config + File.separator + configName), data);
	}
	
	
	
}
