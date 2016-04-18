package com.matcracker.PMManagerServers.Utility;

import java.util.Random;

public class UtilityColor{
	public static final String FORMAT_RESET = "\u001B[0m";
	public static final String FORMAT_BOLD = "\u001B[1m";
	public static final String FORMAT_ITALIC = "\u001B[3m";
	public static final String FORMAT_UNDERLINE = "\u001B[4m";
	public static final String FORMAT_HIDDEN = "\u001B[4m";
	public static final String COLOR_BLACK = "\u001B[30m";
	public static final String COLOR_DARK_RED = "\u001B[31m";
	public static final String COLOR_DARK_GREEN = "\u001B[32m";
	public static final String COLOR_DARK_YELLOW = "\u001B[33m";
	public static final String COLOR_DARK_BLUE = "\u001B[34m";
	public static final String COLOR_DARK_PURPLE = "\u001B[35m";
	public static final String COLOR_DARK_CYAN = "\u001B[36m";
	public static final String COLOR_GRAY = "\u001B[37m";
	public static final String BACKGROUND_DARK_BLACK = "\u001B[40m";
	public static final String BACKGROUND_DARK_RED = "\u001B[41m";
	public static final String BACKGROUND_DARK_GREEN = "\u001B[42m";
	public static final String BACKGROUND_DARK_YELLOW = "\u001B[43m";
	public static final String BACKGROUND_DARK_BLUE = "\u001B[44m";
	public static final String BACKGROUND_DARK_PURPLE = "\u001B[45m";
	public static final String BACKGROUND_DARK_CYAN = "\u001B[46m";
	public static final String BACKGROUND_GRAY = "\u001B[47m";
	public static final String COLOR_DARK_GRAY = "\u001B[90m";
	public static final String COLOR_RED = "\u001B[91m";
	public static final String COLOR_GREEN = "\u001B[92m";
	public static final String COLOR_YELLOW = "\u001B[93m";
	public static final String COLOR_BLUE = "\u001B[94m";
	public static final String COLOR_PURPLE = "\u001B[95m";
	public static final String COLOR_CYAN = "\u001B[96m";
	public static final String COLOR_WHITE = "\u001B[97m";
	public static final String BACKGROUND_DARK_GRAY = "\u001B[100m";
	public static final String BACKGROUND_RED = "\u001B[101m";
	public static final String BACKGROUND_GREEN = "\u001B[102m";
	public static final String BACKGROUND_YELLOW = "\u001B[103m";
	public static final String BACKGROUND_BLUE = "\u001B[104m";
	public static final String BACKGROUND_PURPLE = "\u001B[105m";
	public static final String BACKGROUND_CYAN = "\u001B[106m";
	public static final String BACKGROUND_WHITE = "\u001B[107m";
	
	public static String format(String content){
		String phrase = null;
		phrase = colorTranslator(content + "&r");
		return phrase;
	}
		
	private static String colorTranslator(String phrase){
		if(phrase.contains("&0"))
			phrase = phrase.replaceAll("&0", COLOR_BLACK);
		
		if(phrase.contains("&1"))
			phrase = phrase.replaceAll("&1", COLOR_DARK_BLUE);
		
		if(phrase.contains("&2"))
			phrase = phrase.replaceAll("&2", COLOR_DARK_GREEN);
		
		if(phrase.contains("&3"))
			phrase = phrase.replaceAll("&3", COLOR_DARK_CYAN);
		
		if(phrase.contains("&4"))
			phrase = phrase.replaceAll("&4", COLOR_DARK_RED);
		
		if(phrase.contains("&5"))
			phrase = phrase.replaceAll("&5", COLOR_DARK_PURPLE);
		
		if(phrase.contains("&6"))
			phrase = phrase.replaceAll("&6", COLOR_DARK_YELLOW);
		
		if(phrase.contains("&7"))
			phrase = phrase.replaceAll("&7", COLOR_GRAY);
		
		if(phrase.contains("&8"))
			phrase = phrase.replaceAll("&8", COLOR_DARK_GRAY);
		
		if(phrase.contains("&9"))
			phrase = phrase.replaceAll("&9", COLOR_BLUE);
		
		if(phrase.contains("&a"))
			phrase = phrase.replaceAll("&a", COLOR_GREEN);
		
		if(phrase.contains("&b"))
			phrase = phrase.replaceAll("&b", COLOR_CYAN);
		
		if(phrase.contains("&c"))
			phrase = phrase.replaceAll("&c", COLOR_RED);
		
		if(phrase.contains("&d"))
			phrase = phrase.replaceAll("&d", COLOR_PURPLE);
		
		if(phrase.contains("&e"))
			phrase = phrase.replaceAll("&e", COLOR_YELLOW);
		
		if(phrase.contains("&f"))
			phrase = phrase.replaceAll("&f", COLOR_WHITE);
		
		if(phrase.contains("&k")){//Fun xD
			String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			
			StringBuilder sb = new StringBuilder(6);
		  	for(int i = 0; i < 6; i++) 
		  		sb.append(chars.charAt(new Random().nextInt(chars.length())));
		  	
		  	phrase = phrase.replaceAll("&k", sb.toString());
		}
		
		if(phrase.contains("&l"))
			phrase = phrase.replaceAll("&l", FORMAT_BOLD);
		
		if(phrase.contains("&n"))
			phrase = phrase.replaceAll("&n", FORMAT_UNDERLINE);
		
		if(phrase.contains("&o"))
			phrase = phrase.replaceAll("&o", FORMAT_ITALIC);
		
		if(phrase.contains("&r"))
			phrase = phrase.replaceAll("&r", FORMAT_RESET);
		
		return phrase;
	}
}
