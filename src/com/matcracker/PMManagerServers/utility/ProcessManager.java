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
	
package com.matcracker.PMManagerServers.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessManager{
	private static int PID;
	
	public static void getListOfProcesses() throws IOException{
		 String line;
		 Process p;

		 if(Utility.getOSName().equalsIgnoreCase("Windows"))
			 p = Runtime.getRuntime().exec("tasklist.exe");
		 else
			 p = Runtime.getRuntime().exec("ps");
	
	     BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	     while((line = input.readLine()) != null)
	    	 System.out.println(line);
	     
	     input.close();
	}
		
	/**
	 * @return
	 */
	public static int getPID(){
		return PID;
	}
	
	public static int getPIDByName(String process) throws IOException{
		String line;
		Process p;
		 
		if(Utility.getOSName().equalsIgnoreCase("Windows"))
			p = Runtime.getRuntime().exec("tasklist.exe");
		else
			p = Runtime.getRuntime().exec("ps");

	    BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    while((line = input.readLine()) != null) //Find the interested line of the list
	         if(line.contains(process))
	     		break;
	    
	    input.close();
	    String[] sector = line.trim().split(" "); //Split the line content in an array an find the numeric PID
	    
	    for(int i = 0; i < sector.length; i++){
	    	if(Utility.is_numeric(sector[i])){
	    		int pid = Integer.parseInt(sector[i]);
	    		setPID(pid);
	    		return pid;
	    	}
	    }
	    
		return 0;
	}
		
	/**
	 * @param process
	 * @return
	 * @throws IOException
	 */
	public static boolean isProcessEnabled(String process) throws IOException{
		 String list;
		 Process p;
		 
		 if(Utility.getOSName().equalsIgnoreCase("Windows"))
			 p = Runtime.getRuntime().exec("tasklist.exe");
		 else
			 p = Runtime.getRuntime().exec("ps");
	
	     BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	     while((list = input.readLine()) != null){
	         if(list.contains(process)){
	        	 return true;
	         }
	     }
	     input.close();
	     return false;
	}
	
	/**
	 * @param pid
	 * @throws IOException
	 */
	public static void killProcess(int pid) throws IOException{
		if(Utility.getOSName().equalsIgnoreCase("Windows"))
			Runtime.getRuntime().exec("taskkill /pid " + pid);
		else
			Runtime.getRuntime().exec("kill " + pid);
	}
	
	/**
	 * @param process
	 * @throws IOException
	 */
	public static void killProcess(String process) throws IOException{
		if(Utility.getOSName().equalsIgnoreCase("Windows"))
			Runtime.getRuntime().exec("taskkill /F /IM " + process);
		else
			Runtime.getRuntime().exec("pkill -f " + process);
	}
	
	/**
	 * @param pid
	 */
	private static void setPID(int pid){
		PID = pid;
	}

	
	/**
	 * Start a process
	 * @param path
	 * @throws IOException
	 */
	public static void startProcess(String path) throws IOException{
		Runtime.getRuntime().exec(path);
	}
}
