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
	
package com.matcracker.PMManagerServers.utility;

public class UtilityColor{

	public static final String BACKGROUND_BLUE = "\u001B[104m";
	public static final String BACKGROUND_CYAN = "\u001B[106m";
	public static final String BACKGROUND_DARK_BLACK = "\u001B[40m";
	public static final String BACKGROUND_DARK_BLUE = "\u001B[44m";
	public static final String BACKGROUND_DARK_CYAN = "\u001B[46m";
	public static final String BACKGROUND_DARK_GRAY = "\u001B[100m";
	public static final String BACKGROUND_DARK_GREEN = "\u001B[42m";
	public static final String BACKGROUND_DARK_PURPLE = "\u001B[45m";
	public static final String BACKGROUND_DARK_RED = "\u001B[41m";
	public static final String BACKGROUND_DARK_YELLOW = "\u001B[43m";
	public static final String BACKGROUND_GRAY = "\u001B[47m";
	public static final String BACKGROUND_GREEN = "\u001B[102m";
	public static final String BACKGROUND_PURPLE = "\u001B[105m";
	public static final String BACKGROUND_RED = "\u001B[101m";
	public static final String BACKGROUND_WHITE = "\u001B[107m";
	public static final String BACKGROUND_YELLOW = "\u001B[103m";
	public static final String COLOR_BLACK = "\u001B[30m";
	public static final String COLOR_BLUE = "\u001B[94m";
	public static final String COLOR_CYAN = "\u001B[96m";
	public static final String COLOR_DARK_BLUE = "\u001B[34m";
	public static final String COLOR_DARK_CYAN = "\u001B[36m";
	public static final String COLOR_DARK_GRAY = "\u001B[90m";
	public static final String COLOR_DARK_GREEN = "\u001B[32m";
	public static final String COLOR_DARK_PURPLE = "\u001B[35m";
	public static final String COLOR_DARK_RED = "\u001B[31m";
	public static final String COLOR_DARK_YELLOW = "\u001B[33m";
	public static final String COLOR_GRAY = "\u001B[37m";
	public static final String COLOR_GREEN = "\u001B[92m";
	public static final String COLOR_PURPLE = "\u001B[95m";
	public static final String COLOR_RED = "\u001B[91m";
	public static final String COLOR_WHITE = "\u001B[97m";
	public static final String COLOR_YELLOW = "\u001B[93m";
	public static final String FORMAT_BOLD = "\u001B[1m";
	public static final String FORMAT_HIDDEN = "\u001B[4m";
	public static final String FORMAT_ITALIC = "\u001B[3m";
	public final static String FORMAT_OBFUSCATED = Utility.ubfuscated(6);
	public static final String FORMAT_RESET = "\u001B[0m";
	public static final String FORMAT_UNDERLINE = "\u001B[4m";
	
	private static String colorTranslator(String phrase){
		phrase = phrase.replaceAll("&0", COLOR_BLACK);
		phrase = phrase.replaceAll("&1", COLOR_DARK_BLUE);
		phrase = phrase.replaceAll("&2", COLOR_DARK_GREEN);
		phrase = phrase.replaceAll("&3", COLOR_DARK_CYAN);
		phrase = phrase.replaceAll("&4", COLOR_DARK_RED);
		phrase = phrase.replaceAll("&5", COLOR_DARK_PURPLE);
		phrase = phrase.replaceAll("&6", COLOR_DARK_YELLOW);
		phrase = phrase.replaceAll("&7", COLOR_GRAY);
		phrase = phrase.replaceAll("&8", COLOR_DARK_GRAY);
		phrase = phrase.replaceAll("&9", COLOR_BLUE);
		phrase = phrase.replaceAll("&a", COLOR_GREEN);
		phrase = phrase.replaceAll("&b", COLOR_CYAN);
		phrase = phrase.replaceAll("&c", COLOR_RED);
		phrase = phrase.replaceAll("&d", COLOR_PURPLE);
		phrase = phrase.replaceAll("&e", COLOR_YELLOW);
		phrase = phrase.replaceAll("&f", COLOR_WHITE);
		phrase = phrase.replaceAll("&k", FORMAT_OBFUSCATED);
		phrase = phrase.replaceAll("&l", FORMAT_BOLD);
		phrase = phrase.replaceAll("&n", FORMAT_UNDERLINE);
		phrase = phrase.replaceAll("&o", FORMAT_ITALIC);
		phrase = phrase.replaceAll("&r", FORMAT_RESET);
		
		return phrase;
	}
		
	protected static String format(String content, boolean debug){
		String phrase = content;
		if(!debug && phrase.contains("&"))
			phrase = colorTranslator(content);
		return phrase;
	}
}
