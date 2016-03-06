package com.matcracker.PMManagerServers.Utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

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
	*Copyright (C) 2015 @author matcracker
	*
	*This program is free software: you can redistribute it and/or modify 
	*it under the terms of the GNU Lesser General Public License as published by 
	*the Free Software Foundation, either version 3 of the License, or 
	*(at your option) any later version.
	*/
	
	public static final String COLOR_RESET = "\u001B[0m";
	public static final String COLOR_BLACK = "\u001B[30m";
	public static final String COLOR_RED = "\u001B[31m";
	public static final String COLOR_GREEN = "\u001B[32m";
	public static final String COLOR_YELLOW = "\u001B[33m";
	public static final String COLOR_BLUE = "\u001B[34m";
	public static final String COLOR_PURPLE = "\u001B[35m";
	public static final String COLOR_CYAN = "\u001B[36m";
	public static final String COLOR_WHITE = "\u001B[37m";
	
	public static InputStreamReader input = new InputStreamReader(System.in);
	public static BufferedReader keyword = new BufferedReader(input);
	public static String defaultServersName = "Server_Minecraft_PE";
	public static final String inputError =  "Error during the chooise!";
	
	public final static void cleanScreen(){
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			System.out.println("Error during the console cleaning.");
		}

	}
	
	public static void checking(boolean[] checkNameServer, Object[] checkPath){
		for(int i = 1; i <= 10; i++){
			checkNameServer[i-1] = new File("ServersName" + File.separator + "ServerName_" + i + ".pm") != null;
			checkPath[i-1] = new File("Path" + File.separator + "path_" + i + ".pm");
			
			if(checkNameServer[i-1])
				checkNameServer[i-1] = true;
		}
	}
	
	public static void writeStringData(File file, String data) throws IOException{
		BufferedWriter writerData = null;
		
		try{
			writerData = new BufferedWriter(new FileWriter(file));
			writerData.write(data);
			
		}catch(IOException e){
			e.printStackTrace();
			
		}finally{
			try{
				if(writerData != null)
					writerData.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void writeIntData(File file, int data) throws IOException{
		BufferedWriter writerData = null;
		
		try{
			writerData = new BufferedWriter(new FileWriter(file));
			writerData.write(new Integer(data).toString());
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		try{
			if(writerData != null)
				writerData.close();
		}catch(IOException e1){
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public static int readData(File file) throws FileNotFoundException{
			FileReader readerData = new FileReader(file);
			int data = 0;
		    
		    try{
		        readerData = new FileReader(file);
		        char[] chars = new char[(int) file.length()];
		        data = readerData.read(chars);
		    }catch (IOException e){
				e.printStackTrace();
			}
		    
	        if(readerData !=null){
				try{
					readerData.close();
				}catch (IOException e){
					e.printStackTrace();
				}
	        }
			return data;
	}
	
	public static void selection(int nservers, String[] nameServers, String[] numberServers, String[] numberServers2){
		for(int i = 1; i <= nservers; i++){
			defaultServersName = "Server_Minecraft_PE_" + i;
			System.out.printf("%d) Name of %s server?: ", i, numberServers[i-1]);
			
			try{
				nameServers[i-1] = keyword.readLine();
				
				if(nameServers[i-1].contains(" ")){
					System.out.println("\nSorry, but you can't insert space from name");
					System.in.read();
					Loader.completeLoader();
					
				}else if(nameServers[i-1].equalsIgnoreCase("")){
					nameServers[i-1] = defaultServersName;
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
}