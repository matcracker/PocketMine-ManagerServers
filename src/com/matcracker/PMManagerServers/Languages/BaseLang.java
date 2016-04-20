package com.matcracker.PMManagerServers.Languages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.matcracker.PMManagerServers.Utility.Utility;

public class BaseLang {
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
	 * 
	 * @param param -> the translation (pm.words)
	 * @return
	 * @throws IOException 
	 */
	public static String translate(String param){
		param = param.replaceAll(param, getStringKey(getLanguage(), param));
		
		return param;
	}
	
	public static String getStringKey(String fileName, String key){
		try{
			Properties props = new Properties();
			props.load(new FileInputStream("Languages" + File.separator + fileName + ".ini"));
			return props.getProperty(key);
			 
		}catch(IOException ex){
			return key;
		}
		
	}
	
	public static void setLanguage(String lang){
		switch(lang){
			case "en":
			case "9":
				lang = "en";
				break;
			case "it":
			case "16":
				lang = "it";
				break;
			default:
				lang = "en";
		}
		
		Utility.writeStringData(new File("Data" + File.separator + "langSel.pm"), lang);
	}
	
	public static String getLanguage(){
		return Utility.readStringData(new File("Data" + File.separator + "langSel.pm")).toLowerCase();
	}
}
