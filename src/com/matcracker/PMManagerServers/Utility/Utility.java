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

import com.matcracker.PMManagerServers.Loaders.Loader;

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
	
	public static final String COLOR_RESET = "\u001B[0m";
	public static final String COLOR_BLACK = "\u001B[30m";
	public static final String COLOR_RED = "\u001B[31m";
	public static final String COLOR_GREEN = "\u001B[32m";
	public static final String COLOR_YELLOW = "\u001B[33m";
	public static final String COLOR_BLUE = "\u001B[34m";
	public static final String COLOR_PURPLE = "\u001B[35m";
	public static final String COLOR_CYAN = "\u001B[36m";
	public static final String COLOR_WHITE = "\u001B[37m";
	
	public static String defaultServersName = "Server_Minecraft_PE";
	
	public static InputStreamReader input = new InputStreamReader(System.in);
	public static BufferedReader keyword = new BufferedReader(input);
	
	/**
	 * Errors costants
	 */	
	public static final String inputError =  "Error during the chooise!";
	public static final String generalError =  "An error occured!";
	
	public static void cleanScreen(){
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			System.out.println("Error during the console cleaning.");
			
		}

	}
	
	public static void checking(int nservers){
		Object[] checkPath = new Object[nservers];
		boolean[] checkNameServer = new boolean[nservers];
		for(int i = 1; i <= nservers; i++){
			checkNameServer[i-1] = new File("ServersName" + File.separator + "ServerName_" + i + ".pm") != null;
			checkPath[i-1] = new File("Path" + File.separator + "path_" + i + ".pm");
			
			if(checkNameServer[i-1])
				checkNameServer[i-1] = true;
		}
	}
	
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
	
	public static int readIntData(File file){
			FileReader readerData = null;
			int data = 0;
			
			try{
				readerData = new FileReader(file);
			}catch (FileNotFoundException e1) {
				System.err.println("File not found!");
			}		
		    
		    try{
		        readerData = new FileReader(file);
		        char[] chars = new char[(int) file.length()];
		        data = readerData.read(chars);
		    }catch (IOException e){
		    	System.err.println("Error on reading data!");
			}
		    
	        if(readerData !=null){
				try{
					readerData.close();
				}catch (IOException e){
					System.err.println("Error on closing reader!");
				}
	        }
			return data;
	}
	
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
	
	public static void deleteFolder(String folder, int index){
		File dir = new File(folder);

		for(int i = 0; i < index; i++)
			dir.delete();
	}
	
	public static void deleteFile(String folder){
		File dir = new File(folder);
		File[] files = dir.listFiles();
		
		for(File file : files){
			if(!file.delete())
				System.err.println("Failed to delete " + file);
		}
	}
	
	public static void selection(int nservers, String[] nameServers, String[] path){
		for(int i = 1; i <= nservers; i++){
			defaultServersName = "Server_Minecraft_PE_" + i;
			System.out.printf("%d) Name of %d° server?: ", i, i);
			
			try{
				nameServers[i-1] = keyword.readLine();
				
				if(nameServers[i-1].contains(" ")){
					System.out.println("\nSorry, but you can't insert space from name");
					Utility.keyword.readLine();
					Loader.completeLoader();
					
				}else if(nameServers[i-1].equalsIgnoreCase("")){
					nameServers[i-1] = defaultServersName;
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		
		for(int i = 1; i <= nservers; i++){
			System.out.printf("\n%d) Path of %d° server?: ", i, i);
			path[i-1] = FileChooser.get("Select " + i + "° path of PocketMine-MP.phar");
		}
	}
	
}