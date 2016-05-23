package com.matcracker.PMManagerServers.Languages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Utility.Utility;

public class BaseLang {
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
		
	/**
	 * @param param -> the translation (pm.words)
	 * @return string translated
	 */
	public static String translate(String param){
		try{
			param = param.replaceAll(param, getStringKey(getLanguage(), param));
		}catch(NullPointerException e){
			return param;
		}
		return param;
	}
	
	/**
	 * @param lang Can be a string number or ISO 639-1
	 */
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
	
	/**
	 * @return language ISO 639-1
	 */
	public static String getLanguage(){
		return Utility.readStringData(new File("Data" + File.separator + "langSel.pm")).toLowerCase();
	}
	
	/**
	 * @return bool
	 */
	public static boolean isLanguageSet(){
		if(UtilityServersAPI.checkServersFile("Data", "langSel", -1)){
			String lang = Utility.readStringData(new File("Data" + File.separator + "langSel.pm"));
			if(lang != null)
				return true;
		}
		return false;
	}
	
	/**
	 * @param fileName
	 * @param key
	 * @return key content
	 */
	private static String getStringKey(String fileName, String key){
		InputStreamReader fis = null;
		try{
			fis = new InputStreamReader(new FileInputStream("Languages" + File.separator + fileName + ".ini"), "UTF-8");
		}catch (FileNotFoundException | UnsupportedEncodingException e){
		}
		try{
			Properties props = new Properties();
			props.load(fis);
			if(props.getProperty(key) != null)
				fis.close();
			return props.getProperty(key);
		}catch(IOException ex){
		}
		
		return key;
		
	}
}
