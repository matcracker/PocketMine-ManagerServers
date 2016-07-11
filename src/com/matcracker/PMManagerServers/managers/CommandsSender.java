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
	
package com.matcracker.PMManagerServers.managers;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class CommandsSender{
	private String path;
	private int server;
	
	/**
	 * @param server
	 */
	public CommandsSender(int server){
		this.server = server;
		this.path = UtilityServersAPI.getPath(server);
		
		if(this.path == null)
			Utility.waitConfirm("Path is null, this can be cause errors!");
		
	}
	
	/**
	 * @param server number
	 * @param path of server
	 */
	public CommandsSender(int server, String path){
		this.server = server;
		this.path = path;
		
		if(this.path == null)
			Utility.waitConfirm("Path is null, this can be cause errors!");
		
	}
	
	protected static void commandSenderMenu() throws IOException{
		String cmd;
		
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", "CommandSender"));
		Utility.showServers();
		System.out.println((UtilityServersAPI.getNumberServers() + 1) + ") " + BaseLang.translate("pm.standard.back"));
		int server = Utility.readInt(BaseLang.translate("pm.choice.server") + " ", null);
		
		if(server == (UtilityServersAPI.getNumberServers() + 1))
			Manager.managerMenu();
		
		if(server >= 1 && server <= UtilityServersAPI.getNumberServers()){
			String pathContent = UtilityServersAPI.getPath(server);
			if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
				if(pathContent != null){
					System.out.println("[If server is offline, just back online the commands are executed]");
					System.out.println("[Assicure that you have got the plugin PM-ManagerServers]");
					do{
						do{
							cmd = Utility.readString(UtilityColor.COLOR_PURPLE + "Command to send: " + UtilityColor.COLOR_WHITE, null).replace("/", "");
						}while(cmd.equalsIgnoreCase("") || cmd.equalsIgnoreCase(" "));
						
						if(cmd.equalsIgnoreCase("exit"))
							commandSenderMenu();
						
						CommandsSender sender = new CommandsSender(server, pathContent);
						sender.sendCommand(cmd);
						
					}while(!cmd.equalsIgnoreCase("exit"));
				}else
					Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNull"));
			}else
				Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNotFound"));
		}else
			Utility.waitConfirm("Server not avaiable!");
			
		commandSenderMenu();
	}
	
	/**
	 * @param path of server
	 */
	public void setServer(int server){
		this.server = server;
		
		if(UtilityServersAPI.getPath(server) == null)
			Utility.waitConfirm("Path is null, this can be cause errors!");
	}
	
	/**
	 * @param path of server
	 */
	public void setPath(String path){
		this.path = path;
		
		if(this.path == null)
			Utility.waitConfirm("Path is null, this can be cause errors!");
	}
		
	/**
	 * @return path of server
	 */
	
	public String getPath(){
		return this.path;
	}
	
	/**
	 * @return number of server
	 */
	public int getServer(){
		return this.server;
	}
	/**
	 * @param command to send in PocketMine
	 */
	public void sendCommand(String command){
		try{
			if(!existPlugin()){
				System.out.println("The plugin isn't enabled!");
				String conf = Utility.readString("Do you want to download it? <Y/n>: ", null);
				if(conf.equalsIgnoreCase("Y")){
					String phar = "https://github.com/matcracker/PM-ManagerServers-PHP/releases/download/v1.0.0/PM-ManagerServers.phar";
					Utility.downloadFile(phar, this.path + "plugins");
				}
				
				conf = Utility.readString("Do you want to start the server? <Y/n>: ", null);
				
				if(conf.equalsIgnoreCase("Y"))
					Utility.openSoftware("software", this.path + Utility.getStartName());
				
				
				return;
			}
			
			File pluginDir = new File(this.path + "plugins" + File.separator + "PMManagerServers");
			
			if(!pluginDir.isDirectory() && !pluginDir.exists()) return;
			
			pluginDir = new File(pluginDir + File.separator + command);
			
			Utility.writeStringData(pluginDir, ""); //The parameter command is useless but can be useful
		}catch(Exception e){
			System.out.println("Error during the executing of command: " + command);
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * @return true if the plugin is in plugins folder
	 */
	private boolean existPlugin(){
		File plugin = new File(this.path + "plugins" + File.separator + "PM-ManagerServers.phar");
		
		if(plugin.exists())
			return true;
		
		return false;
	}
	
}
