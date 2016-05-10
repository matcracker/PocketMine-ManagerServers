package com.matcracker.PMManagerServers.Utility;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Loaders.PluginsLoader;

public class Utility{
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
	
	public static final String version = "0.1J";
	public static final String softwareName = "&a========================&e<&bPocketMine Manager Servers&e>&a============================";
	public static String defaultServersName = "Server_Minecraft_PE";
	
	public static InputStreamReader input = new InputStreamReader(System.in);
	public static BufferedReader keyword = new BufferedReader(input);
	
	/**
	 * Errors costants
	 */	
	public static final String inputError = "&cError during the chooise!";
	public static final String generalError =  "&cAn error occured!";
	
	/**
	 * Clean the screen
	 */
	public static void cleanScreen(){
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			System.out.println("Error during the console cleaning.");
		}

	}
	
	/**
	 * @param file 
	 * @param data
	 */
	public static void writeStringData(File file, String data){
		BufferedWriter writerData = null;
		
		try{
			writerData = new BufferedWriter(new FileWriter(file));
			writerData.write(data);
			
		}catch(IOException e){
			System.err.println("Error on writing data!");
			
		}finally{
			try{
				if(writerData != null)
					writerData.close();
			}catch(IOException e){
				System.err.println("Error on closing writer!");
			}
		}
	}
	
	/**
	 * @param file
	 * @param data
	 */
	public static void writeIntData(File file, int data){
		BufferedWriter writerData = null;
		
		try{
			writerData = new BufferedWriter(new FileWriter(file));
			writerData.write(new Integer(data).toString());
			
		}catch(IOException e){
			System.err.println("Error on writing data!");
		}
		
		try{
			if(writerData != null)
				writerData.close();
		}catch(IOException e1){
			System.err.println("Error on closing writer!");
		}
	}
	
	/**
	 * @param file
	 * @return int of file
	 */
	public static int readIntData(File file){
		FileReader readerData = null;
		String data = null;
		
		try{
			readerData = new FileReader(file);
		}catch (FileNotFoundException e1) {
			System.err.println("File not found!");
		}
		BufferedReader buffer = new BufferedReader(readerData);
	    

		try{
			data = buffer.readLine();
			if(readerData != null)
				readerData.close();
		}catch(IOException e){
			System.err.println("Error on closing reader!");
		}
			
		return Integer.parseInt(data);
	}
	
	/**
	 * @param file
	 * @return string of file
	 */
	public static String readStringData(File file){
		FileReader readerData = null;
		String data = null;
		
		try{
			readerData = new FileReader(file);
		}catch (FileNotFoundException e1) {
			System.err.println("File not found!");
		}
		BufferedReader buffer = new BufferedReader(readerData);
	    
		try{
			data = buffer.readLine();
			if(readerData != null)
				readerData.close();
		}catch(IOException e){
			System.err.println("Error on closing reader!");
		}
			
		return data;
	}
	
	/**
	 * @param type (url or software)
	 * @param content path
	 */
	public static void openSoftware(String type, String content){
		try{
			if(type.equalsIgnoreCase("url")){
				Desktop.getDesktop().browse(new URL(content).toURI());
				
			}else if(type.equalsIgnoreCase("software")){
				if(content.contains("/"))
					content = content.replaceAll("/", File.separator);
				
				Desktop.getDesktop().open(new File(content));
			}else{
				System.err.println("Wrong type");
			}
		}catch(IOException | URISyntaxException e){
			System.err.println("Error on opening software or URL");
		}
	}
	
	/**
	 * @param text Useful for wait a confirm from user
	 */
	public static void waitConfirm(String text){
		try{
			System.out.println(text);
			keyword.readLine();
		}catch (IOException e){
		}
	}
	
	/**
	 * Show all the servers
	 */
	public static void showServers(){
		int nservers = UtilityServersAPI.getNumberServers();
		for(int i = 0; i < nservers; i++){
			System.out.print((i+1) + ") " + UtilityServersAPI.getNameServer(i));
			System.out.println();
		}
	}
	
	/**
	 * @param text
	 * @param addition
	 * @return string input
	 */
	public static String readString(String text, String addition){
		String content = null;
		
		if(addition != null)
			System.out.println(addition);
		System.out.print(text);
		try{
			content = keyword.readLine();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		return content;
		
	}
	
	/**
	 * @param text
	 * @param addition
	 * @return int input
	 */
	public static int readInt(String text, String addition){
		String content = null;
		
		System.out.println();
		if(addition != null)
			System.out.println(addition);
		System.out.print(text);
		try{
			content = keyword.readLine();
		}catch (IOException e){
			e.printStackTrace();
		}
		if(is_numeric(content))
			return Integer.parseInt(content);
		else
			return -1;
		
	}
	
	/**
	 * @param content
	 * @return
	 */
	public static boolean is_numeric(String content){
		try{
			Integer.parseInt(content);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	/**
	 * Show plugins
	 */
	public static void showPlugins(){
		File folder = PluginsLoader.folder;
		
		if(PluginsLoader.pluginFound){
			int i = 1;
			for(File plugins : folder.listFiles()){
				if(plugins.isFile() && plugins.getName().endsWith(".jar")){
					System.out.println(i + "- " + plugins.getName().replace(".jar", ""));
					i++;
				}
			}
		}
	}
	
	/**
	 * @param lenght of word
	 * @return a string obfuscated
	 */
	public static String ubfuscated(int lenght){
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		
		StringBuilder sb = new StringBuilder(6);
	  	for(int i = 0; i < lenght; i++){
	  		sb.append(chars.charAt(new Random().nextInt(chars.length())));
	  	}
	  	
	  	return sb.toString();
	}
	
}