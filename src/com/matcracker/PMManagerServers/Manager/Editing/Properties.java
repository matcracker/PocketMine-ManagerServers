package com.matcracker.PMManagerServers.Manager.Editing;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Utility.Utility;

public class Properties {
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
	protected static void propertiesMenu(){
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(BaseLang.translate("pm.title.properties"));
		Utility.showServers();
		int server = Utility.readInt(BaseLang.translate("pm.chooise.server") + " ", null);
		
		if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
			String pathContent = UtilityServersAPI.getPath(server-1);
			if(pathContent != null){
				try{
					editProperties(pathContent);
				}catch (IOException e){
					e.printStackTrace();
				}
			}else
				Utility.waitConfirm(BaseLang.translate("pm.errors.pathNull"));
		}else
			Utility.waitConfirm(BaseLang.translate("pm.errors.pathNotFound"));
	}
	
	private static void editProperties(String path) throws IOException{
		File prop = new File(path + "server.properties");
		String configMessages[] = {
				"Motd <by default Server Minecraft PE>: ",
				"Server port <by default 19132>: ",
				"White-list <on/off>: ",
				"Announce player achivements <on/off>: ",
				"Spawn Protection <by default is 20>: ",
				"Max Players <by default is 20>: ",
				"Allow flight <on/off>: ",
				"Spawn Animals <on/off>: ",
				"Spawn mobs <on/off>: ",
				"Gamemode [0/1/2] <by default is 0>: ",
				"Force gamemode <on/off>: ",
				"Hardcore <on/off>: ",
				"Pvp <on/off>: ",
				"Difficulty [0/1/2] <by default is 1>: ",
				"Generator-Settings: ",
				"Level name <by default is world>: ",
				"Level seed: ",
				"Level type <by default is DEFAULT>: ",
				"Enable query <on/off>: ",
				"Enable rcon <on/off>: ",
				"Rcon password: ",
				"Auto-Save <on/off>: "
		};
		
		String configOptions[] = {
				"motd=",
                "server-port=",
                "white-list=",
                "announce-player-achievements=",
                "spawn-protection=",
                "max-players=",
                "allow-flight=",
                "spawn-animals=",
                "spawn-mobs=",
                "gamemode=",
                "force-gamemode=",
                "hardcore=",
                "pvp=",
                "difficulty=",
                "generator-settings=",
                "level-name=",
                "level-seed=",
                "level-type=",
                "enable-query=",
                "enable-rcon=",
                "rcon.password=",
                "auto-save="
		};
		String configDefault[] = {
				"Server Minecraft PE",
				"19132",
				"on",
				"on",
				"20",
				"20",
				"off",
				"off",
				"off",
				"0",
				"off",
				"off",
				"on",
				"1",
				"",
				"world",
				"",
				"DEFAULT",
				"off",
				"on",
				Utility.ubfuscated(6),
				"on"				
		};
		
		if(prop.exists()){
			Calendar calendar = Calendar.getInstance();
			String strDate = calendar.getTime().toString();
			String propFile = 
					"#Properties Config file\n" +
					"#" + strDate + "\n";
			String[] config = new String[configDefault.length];
			
			System.out.println("#Properties Config File");
			System.out.println("#" + strDate);
			for(int i = 0; i < configMessages.length; i++){
				System.out.print(configMessages[i]);
				config[i] = Utility.keyword.readLine();
				if(config[i].equalsIgnoreCase(""))
					config[i] = configDefault[i];
				
				propFile = propFile + configOptions[i] + config[i] + "\n";
			}
			
			String confirm = Utility.readString(BaseLang.translate("pm.properties.confirm") + " <Y/n>: ", null);
			
			if(confirm.equalsIgnoreCase("y"))
				Utility.writeStringData(new File(path + "server.properties"), propFile);
			else
				editProperties(path);
			
		}
	}
}
