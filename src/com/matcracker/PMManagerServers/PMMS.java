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
	
package com.matcracker.PMManagerServers;

import java.io.UnsupportedEncodingException;

import com.matcracker.PMManagerServers.API.APIManager;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.informations.Informations;
import com.matcracker.PMManagerServers.installer.ManagerInstaller;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.loaders.Loader;
import com.matcracker.PMManagerServers.loaders.PluginsLoader;
import com.matcracker.PMManagerServers.managers.Manager;
import com.matcracker.PMManagerServers.settings.Settings;
import com.matcracker.PMManagerServers.utility.PMPrintStream;
import com.matcracker.PMManagerServers.utility.Updater;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class PMMS{

	private static boolean pluginsLoaded = false;
	
	public static void main(String[] args){
		if(args.length != 1){
			System.out.println(BaseLang.translate("pm.mainMenu.argsError"));
			System.out.println(BaseLang.translate("pm.standard.example") + " java -jar PocketMine-ManagerServers.jar -pc");
		}
		
		if(args[0].equalsIgnoreCase("-server"))
			APIManager.setServerMode(true);
		
		try{
			System.setOut(new PMPrintStream());
			System.setErr(new PMPrintStream());
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		if(args[0].equalsIgnoreCase("-travis")){
			System.out.println(Utility.setTitle('=', UtilityColor.GREEN, Utility.softwareName));
			System.out.println(UtilityColor.GREEN + "Software builded successfully!");
			System.out.println("&fUse argument '-pc' or '-server' to run the software correctly");
			System.exit(0);
		}
		
		Loader.startLoader();
		
		checkUpdate();
				
		if(!pluginsLoaded){
			PluginsLoader.loadPlugins();
			pluginsLoaded = true;
		}
		

		if(APIManager.isCommandsMode()){
			CommandsMode.menu();
		}else
			mainMenu();
		
	}
	
	public static void checkUpdate(){
		Updater up = new Updater();
		
		if(!UtilityServersAPI.checkServersFile("Data", "updater", -1)){
			String conf = Utility.readString(BaseLang.translate("pm.updater.alwaysCheck") + " <Y/n>: ", null);
			if(conf.equalsIgnoreCase("y"))
				up.setAutoSearch(true);
			else
				up.setAutoSearch(false);
		}
		
		if(up.isAutoSearch()){
			System.out.println(BaseLang.translate("pm.updater.searchingUpdate"));
			if(up.findUpdate())
				up.updateResources();
			else
				System.out.println(UtilityColor.RED + BaseLang.translate("pm.updater.noUpdate"));
		}
	}
	
	public static void mainMenu(){
		String menu = "", quit = "n";
		int i = 0;

		while(quit.equalsIgnoreCase("n")){
			boolean devMode = APIManager.isDevMode();
			
			Utility.cleanScreen();
			System.out.println(Utility.setTitle('=', UtilityColor.GREEN, Utility.softwareName));
			System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.mainMenu")));
			System.out.printf("&eDeveloped by matcracker		   Version: %s Alpha %d	        API: %s\n", APIManager.getVersion(), APIManager.getAlphaVersion(), APIManager.getAPIVersion()); //Alpha version
			//System.out.printf("&eDeveloped by matcracker		           Version: %s         API: %s\n", APIManager.getVersion(), APIManager.getAlphaVersion(), APIManager.getAPIVersion()); //Stable version
			System.out.println("&f1- " + BaseLang.translate("pm.mainMenu.download-install"));
			System.out.println("2- " + BaseLang.translate("pm.mainMenu.manager"));
			System.out.println("3- " + BaseLang.translate("pm.mainMenu.options"));
			System.out.println("4- " + BaseLang.translate("pm.mainMenu.informations"));
			System.out.println("5- " + BaseLang.translate("pm.mainMenu.exit") + "\n");

			if(devMode)
				DevMode.devMenu(menu);

			menu = Utility.readString(BaseLang.translate("pm.choice.ask")+ " ", null);
			
			if(menu.equalsIgnoreCase("1"))
				ManagerInstaller.managerInstallerMenu();
				
			else if(menu.equalsIgnoreCase("2"))
				Manager.managerMenu();

			else if(menu.equalsIgnoreCase("3"))
				Settings.settingsMenu();
			
			else if(menu.equalsIgnoreCase("4"))
				Informations.informationsMenu();
			
			else if(menu.equalsIgnoreCase("5")){
				Utility.cleanScreen();
				System.out.println(Utility.setTitle('=', UtilityColor.GREEN, Utility.softwareName));
				System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.exit")));
				quit = Utility.readString(BaseLang.translate("pm.choice.exit") + " <y/N>: ", null);

				if(quit.equalsIgnoreCase("y")){
					PluginsLoader.unloadPlugins();
					System.exit(0);
				}
			}
			
			if(i >= 3)
				i = 0;
			
			if(menu.equalsIgnoreCase("dev"))
				i++;	

			if(i == 3){
				if(!devMode){
					Utility.waitConfirm(UtilityColor.YELLOW +  BaseLang.translate("pm.devmenu.enabled"));
					APIManager.setDevMode(true);
					
				}else{
					Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.devmenu.disabled"));
					APIManager.setDevMode(false);
				}
			}
		}
	}
}