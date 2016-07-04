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
	
package com.matcracker.PMManagerServers.managers.editing;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.managers.Manager;
import com.matcracker.PMManagerServers.utility.ProcessManager;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

class BackgroudTask extends Thread{
	private boolean isRunning = false;
	private String serverPath;
	private int time;
	private int server;
	
	/**
	 * @param time
	 * @param serverPath
	 */
	public BackgroudTask(int time, String serverPath){
		this.time = time;
		this.serverPath = serverPath;
	}
	
	/**
	 * @param time
	 * @param server
	 * @param isRunning
	 */
	public BackgroudTask(int time, int server, boolean isRunning){
		this.time = time;
		this.serverPath = UtilityServersAPI.getPath(server);
		this.isRunning = isRunning;
	}
	
	/**
	 * @param time
	 * @param serverPath
	 * @param isRunning
	 */
	public BackgroudTask(int time, String serverPath, boolean isRunning){
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

public class Restarter {
	private static BackgroudTask task;
	private static int time;
	
	public static BackgroudTask getTask(){
		return task;
	}
	
	/**
	 * Kill the restarter task
	 */
	public static void killRestarter(){
		if(task != null){
			if(!task.isInterrupted() && task.isRunning()){
				task.interrupt();
				Utility.waitConfirm(BaseLang.translate("pm.restarter.killed"));
			}else
				Utility.waitConfirm(BaseLang.translate("pm.restarter.restarterNotRunning"));
		}else
			Utility.waitConfirm(BaseLang.translate("pm.restarter.restarterNotRunning"));
	}

	public static void restarterMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.restarter")));
		System.out.println("1- " + BaseLang.translate("pm.restarter.setupTimer"));
		//System.out.println("2- " + BaseLang.translate("pm.restarter.restartNow"));
		System.out.println("2- " + BaseLang.translate("pm.restarter.kill"));
		System.out.println("3- " + BaseLang.translate("pm.standard.back"));
		int option = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
		
		if(option == 1){
			//if(option == 1)
			setupTimer();
			startRestarter();
			
			/*if(option == 2)
				restartServer(server);*/
		}
		
		if(option == 2)
			killRestarter();
		
		if(option == 3)
			Manager.managerMenu();
		
		restarterMenu();
	}
	
	/*private static void restartServer(int server) throws IOException {
		String proc = "mintty.exe";
		if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
			String pathContent = UtilityServersAPI.getPath(server);
			if(pathContent != null){
				if(ProcessManager.isProcessEnabled(proc)){
					int pid = ProcessManager.getPIDByName(proc);
					ProcessManager.killProcess(pid);
					ProcessManager.startProcess(UtilityServersAPI.getPath(server));
					Utility.waitConfirm(BaseLang.translate("pm.restarter.restarted") + " " + UtilityServersAPI.getNameServer(server));
				}else
					Utility.waitConfirm(BaseLang.translate("pm.restarter.serverNotRunning"));
			}else
				Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNull"));
		}else
			Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNotFound"));
	}*/
	private static void setupTimer(){
		System.out.println("1- " + BaseLang.translate("pm.restarter.days"));
		System.out.println("2- " + BaseLang.translate("pm.restarter.hours"));
		System.out.println("3- " + BaseLang.translate("pm.restarter.minutes"));
		int opt = Utility.readInt(BaseLang.translate("pm.restarter.selectTypeTime") + " ", null);
		
		int timer = Utility.readInt(BaseLang.translate("pm.restarter.selectTime") + " ", "[" + BaseLang.translate("pm.restarter.defaultTime") + "]");
		
		if(timer > 0){
			if(opt == 1)
				time = timer * 24 * 3600; //Days
			else if(opt == 2)
				time = timer * 3600; //Hour
			else if(opt == 3)
				time = timer * 60; //Minutes
		}else
			time = 3;
		
		time = time * 1000; //Milliseconds
	}
	
	private static void startRestarter() throws IOException {
		int i = 1;
		if(UtilityServersAPI.checkServersFile("Path", "path_", i)){
			String pathContent = UtilityServersAPI.getPath(i);
			if(pathContent != null){
				task = new BackgroudTask(time, i, true);
				task.start();
				i++;
			}else
				Utility.waitConfirm(i + ") " + UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNull"));
		}else
			Utility.waitConfirm(i + ") " + UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNotFound"));
	}
}
