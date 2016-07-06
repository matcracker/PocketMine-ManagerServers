package com.matcracker.PMManagerServers.managers.editing;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.ProcessManager;
import com.matcracker.PMManagerServers.utility.Utility;

public class BackgroundTask extends Thread{
	private boolean isRunning = false;
	private String serverPath;
	private int time;
	private int server;
	
	/**
	 * @param time
	 * @param serverPath
	 */
	public BackgroundTask(int time, String serverPath){
		this.time = time;
		this.serverPath = serverPath;
	}
	
	/**
	 * @param time
	 * @param server
	 * @param isRunning
	 */
	public BackgroundTask(int time, int server, boolean isRunning){
		this.time = time;
		this.serverPath = UtilityServersAPI.getPath(server);
		this.isRunning = isRunning;
	}
	
	/**
	 * @param time
	 * @param serverPath
	 * @param isRunning
	 */
	public BackgroundTask(int time, String serverPath, boolean isRunning){
		this.time = time;
		this.serverPath = serverPath;
		this.isRunning = isRunning;
	}
	
	public String getPath(){
		return this.serverPath;
	}
	
	public int getTime(){
		return this.time;
	}
	
	public int getServer(){
		return this.server;
	}
	
	public boolean isRunning(){
		return this.isRunning;
	}
	
	@Override
	public void run(){
		String proc = "mintty.exe";
		try{
			while(isRunning){
				if(ProcessManager.isProcessEnabled(proc)){
					int pid = ProcessManager.getPIDByName(proc);
					sleep(getTime());
					System.out.println("\n" + BaseLang.translate("pm.task.stopping"));
					ProcessManager.killProcess(pid);
					System.out.println("\n" + BaseLang.translate("pm.task.restarting"));
					sleep(3000);
					ProcessManager.startProcess(getPath() + File.separator + Utility.getStartName());
					run();
				}else{
					System.out.println("\n" + BaseLang.translate("pm.task.serverNotRunning"));
					setRunning(false);
				}
			}
		}catch(IOException | InterruptedException e){
			System.out.println("\n"  + BaseLang.translate("pm.task.error"));
			setRunning(false);
		}
	}
	
	public void setPath(String path){
		this.serverPath = path;
	}
	
	public void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public void setServer(int server){
		this.server = server;
	}
	
	public void setTime(int time){
		this.time = time;
	}
}