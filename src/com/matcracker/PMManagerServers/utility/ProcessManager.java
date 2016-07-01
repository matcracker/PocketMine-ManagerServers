package com.matcracker.PMManagerServers.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessManager{
	static int PID;
	
	/**
	 * @return
	 */
	public static int getPID(){
		return PID;
	}
	
	public static int getPIDByName(String process) throws IOException{
		String line;
		Process p;
		 
		if(Utility.getOSName().contains("Windows"))
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
	 * @param pid
	 */
	public static void setPID(int pid){
		PID = pid;
	}
		
	/**
	 * @param process
	 * @throws IOException
	 */
	public static void killProcess(String process) throws IOException{
		if(Utility.getOSName().contains("Windows"))
			Runtime.getRuntime().exec("taskkill /F /IM " + process);
		else
			Runtime.getRuntime().exec("pkill -f " + process);
	}
	
	/**
	 * @param pid
	 * @throws IOException
	 */
	public static void killProcess(int pid) throws IOException{
		if(Utility.getOSName().contains("Windows"))
			Runtime.getRuntime().exec("taskkill /pid " + pid);
		else
			Runtime.getRuntime().exec("kill " + pid);
	}
	
	/**
	 * @param path
	 * @throws IOException
	 */
	public static void startProcess(String path) throws IOException{
		Runtime.getRuntime().exec(path);
	}
	
	/**
	 * @param process
	 * @return
	 * @throws IOException
	 */
	public static boolean isProcessEnabled(String process) throws IOException{
		 String list;
		 Process p;
		 
		 if(Utility.getOSName().contains("Windows"))
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
	
	public static void getListOfProcesses() throws IOException{
		 String line;
		 Process p;
		 
		 if(Utility.getOSName().contains("Windows"))
			 p = Runtime.getRuntime().exec("tasklist.exe");
		 else
			 p = Runtime.getRuntime().exec("ps");
	
	     BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	     while((line = input.readLine()) != null)
	    	 System.out.println(line);
	     
	     input.close();
	}
}
