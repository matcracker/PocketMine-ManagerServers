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

package com.matcracker.PMManagerServers.managers.editing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.managers.Manager;
import com.matcracker.PMManagerServers.plugincreator.CodeUtility.CodeStructures;
import com.matcracker.PMManagerServers.plugincreator.PocketMineAPI.CommandsParameter;
import com.matcracker.PMManagerServers.plugincreator.PocketMineAPI.EventsParameter;
import com.matcracker.PMManagerServers.plugincreator.PocketMineConfig;
import com.matcracker.PMManagerServers.plugincreator.CodeUtility;
import com.matcracker.PMManagerServers.plugincreator.PocketMineAPI;
import com.matcracker.PMManagerServers.plugincreator.PocketMinePluginYAML;
import com.matcracker.PMManagerServers.plugincreator.PocketmineCommands;
import com.matcracker.PMManagerServers.plugincreator.PocketmineEvents;
import com.matcracker.PMManagerServers.plugincreator.PocketminePluginCreator;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class ServerPlugins{
	private static PocketMinePluginYAML yaml;
	private static PocketminePluginCreator plcr;
	private static PocketmineCommands plcmd;
	private static PocketmineEvents plev;
	private static PocketMineConfig plconf;
	
	public static void pluginsMenu(){
		Utility.cleanScreen();
		System.out.println(Utility.setTitle('=', UtilityColor.GREEN, Utility.softwareName));
		System.out.println(Utility.setTitle(UtilityColor.RED, BaseLang.translate("pm.title.serverPlugins")));
		System.out.println("1- " + BaseLang.translate("pm.serverPlugins.pluginList"));
		System.out.println("2- " + BaseLang.translate("pm.serverPlugins.deletePlugin"));
		System.out.println("3- " + BaseLang.translate("pm.serverPlugins.createPlugin"));
		System.out.println("4- " + BaseLang.translate("pm.standard.back"));
		
		int opt = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
		
		if(opt == 1)
			getPluginsList();
		
		if(opt == 2)
			deletePlugin();
		
		if(opt == 3)
			createPlugin();
		
		if(opt == 4)
			Manager.managerMenu();
		
		pluginsMenu();
	}
	
	private static void createPlugin() {
		Utility.cleanScreen();
		System.out.println(Utility.setTitle(UtilityColor.YELLOW, BaseLang.translate("pm.title.createPlugin")));
		System.out.println("1- " + BaseLang.translate("pm.serverPlugins.createPluginYAML"));
		System.out.println("2- " + BaseLang.translate("pm.serverPlugins.createPluginStructure"));
		System.out.println("3- " + BaseLang.translate("pm.serverPlugins.createPluginConfig"));
		System.out.println("4- " + BaseLang.translate("pm.serverPlugins.save"));
		System.out.println("5- " + BaseLang.translate("pm.standard.back"));
		int opt = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", "[" + BaseLang.translate("pm.serverPlugins.notShutdown") + "]");
		
		if(opt == 5)
			pluginsMenu();
		
		if(opt == 1){
			yaml = new PocketMinePluginYAML();
			yaml.requestYAMLData();
			yaml.createPluginYAML();
			Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.serverPlugins.pluginYAMLCreated"));
		}
		

		
		if(yaml != null){
			if(plcr == null)
				plcr = new PocketminePluginCreator(yaml);
			
			if(opt == 2)
				pluginCreatorMenu();
			
			if(opt == 3)
				configurationCreator();			
			
			if(opt == 4){
				if(plcr != null){
					plcr.createNewClass();
					Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.serverPlugins.classCreated"));
				}
				if(plcmd != null)
					plcmd.clearData();
			}

		}else
			Utility.waitConfirm(BaseLang.translate("pm.serverPlugins.YAMLNotSet"));

		createPlugin();
	}
	
	private static void configurationCreator(){
		plconf = new PocketMineConfig(yaml, Utility.readString(BaseLang.translate("pm.serverPlugins.configName") + " ", "[" + BaseLang.translate("pm.serverPlugins.defaultConfigName") + "]"));
		boolean finished = false;
		
		do{
			Utility.cleanScreen();
			System.out.println(Utility.setTitle(UtilityColor.YELLOW, BaseLang.translate("pm.serverPlugins.configOf") + " " + plconf.getConfigName()));
			System.out.println(UtilityColor.BLUE + "---------------------------------");
			System.out.println(UtilityColor.PURPLE + BaseLang.translate("pm.serverPlugins.currentConfig") + ": ");
			for(String line : plconf.getConfig().split("\n"))
				System.out.println("\t" + line);
			System.out.println(UtilityColor.FORMAT_RESET + UtilityColor.BLUE + "---------------------------------" + UtilityColor.WHITE);
			System.out.println("1- " + BaseLang.translate("pm.serverPlugins.addKeyValue"));
			System.out.println("2- " + BaseLang.translate("pm.serverPlugins.customConfig"));
			System.out.println("3- " + BaseLang.translate("pm.serverPlugins.cancelConfig"));
			System.out.println("4- " + BaseLang.translate("pm.serverPlugins.saveConfig"));
			System.out.println("5- " + BaseLang.translate("pm.standard.back"));
			int conf = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
			
			if(conf == 1)
				plconf.addValue(Utility.readString(BaseLang.translate("pm.serverPlugins.writeKey") + " ", null), Utility.readString(BaseLang.translate("pm.serverPlugins.writeValue") + " ", "[" + BaseLang.translate("pm.serverPlugins.moreValues") + "]"));
			
			if(conf == 2)
				plconf.addLine(Utility.readString(BaseLang.translate("pm.serverPlugins.customText") + " ", null));
			
			if(conf == 3)
				plconf.clearConfig();
			
			if(conf == 4){
				finished = true;
				plconf.saveConfig();
				Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.serverPlugins.configurationSaved"));
			}
			
			if(conf == 5)
				break;
			
		}while(!finished);
			
	}
	
	private static void pluginCreatorMenu(){
		boolean finish = false;
		do{
			Utility.cleanScreen();
			System.out.println(Utility.setTitle(UtilityColor.YELLOW, BaseLang.translate("pm.title.createStructure")));
			System.out.println("1- " + BaseLang.translate("pm.serverPlugins.addStructure") + " (onEnable/onDisable).");
			System.out.println("2- " + BaseLang.translate("pm.serverPlugins.addEvents"));
			System.out.println("3- " + BaseLang.translate("pm.serverPlugins.addCommands"));
			System.out.println("4- " + BaseLang.translate("pm.standard.back"));
			int struct = Utility.readInt(BaseLang.translate("pm.serverPlugins.selectStructure") + " ", "[" + BaseLang.translate("pm.serverPlugins.recommended") + "]");
			
			if(struct == 4)
				break;
			
			//Class builder
			if(struct == 1)
				plcr.setClassStructure();
			
			//Events Builder
			if(struct == 2){
				plev = new PocketmineEvents();
				PocketMineAPI pmev = plev.getPocketMineEvents();
				plcr.setListener(true);
				
				Utility.cleanScreen();
				System.out.println(Utility.setTitle(UtilityColor.YELLOW, BaseLang.translate("pm.title.eventSelector")));
				System.out.println("1- " + BaseLang.translate("pm.serverPlugins.events.blocks"));
				System.out.println("2- " + BaseLang.translate("pm.serverPlugins.events.entities"));
				System.out.println("3- " + BaseLang.translate("pm.serverPlugins.events.inventories"));
				System.out.println("4- " + BaseLang.translate("pm.serverPlugins.events.levels"));
				System.out.println("5- " + BaseLang.translate("pm.serverPlugins.events.players"));
				System.out.println("6- " + BaseLang.translate("pm.serverPlugins.events.plugins"));
				System.out.println("7- " + BaseLang.translate("pm.serverPlugins.events.server"));
				System.out.println("8- " + BaseLang.translate("pm.standard.back"));
				int event = Utility.readInt(BaseLang.translate("pm.serverPlugins.selectEvent") + " ", null);
				
				if(event == 8) return;
				
				String[] events = new String[pmev.entityEvents.length];
				
				if(event == 1)
					events = pmev.blockEvents;
				
				if(event == 2)
					events = pmev.entityEvents;
				
				if(event == 3)
					events = pmev.inventoryEvents;
				
				if(event == 4)
					events = pmev.levelEvents;
				
				if(event == 5)
					events = pmev.playerEvents;
				
				if(event == 6)
					events = pmev.pluginEvents;
				
				if(event == 7)
					events = pmev.serverEvents;
				
				int i = 0;
				for(i = 0; i < events.length; i++)
					System.out.printf("%d) %s\n", (i+1), pmev.getEvent(i, events));
			
				System.out.println((i+1) + ") " + BaseLang.translate("pm.standard.back"));

				int type = Utility.readInt(BaseLang.translate("pm.choice.type") + " ", null);
				
				if(type == (i+1) || type > events.length) return;
				
				if(type > 0){
					do{
						i = 0;
						Utility.cleanScreen();
						plev.addEvent(pmev.getEvent(type-1, events));
						System.out.println(Utility.setTitle(UtilityColor.YELLOW, BaseLang.translate("pm.title.parameterSelector")));
						System.out.println(UtilityColor.BLUE + "---------------------------------");
						System.out.println(UtilityColor.PURPLE + BaseLang.translate("pm.serverPlugins.currentCode") + ": ");
						if(!plev.events.isEmpty()) System.out.println(plev.getEventsContent());
						System.out.println(plev.getEventContent());
						System.out.println(UtilityColor.FORMAT_RESET + UtilityColor.BLUE + "---------------------------------" + UtilityColor.WHITE);
						
						boolean[] accepted = pmev.filterParam(pmev.getEvent(type-1, events));
						for(i = 0; i < accepted.length; i++){
							if(accepted[i])
								System.out.printf("%d) %s\n", (i+1), EventsParameter.values()[i].getName());
						}
						System.out.println((i+1) + ") " + BaseLang.translate("pm.serverPlugins.customLineCode"));
						System.err.println((i+2) + ") " + BaseLang.translate("pm.serverPlugins.cancelCode"));
						System.out.println((i+3) + ") " + BaseLang.translate("pm.serverPlugins.addCodeStructure"));
						System.out.println((i+4) + ") " + BaseLang.translate("pm.serverPlugins.leaveEditor"));
						
						int param = Utility.readInt(BaseLang.translate("pm.serverPlugins.selectParameter") + " ", null);
						
						if(param <= 0) return;
						
						if(param == (i+2)) plev.removeEventsContext();
						
						if(param == (i+3)){
							for(int c = 0; c < CodeStructures.values().length; c++)
								System.out.println((c+1) + ") " + CodeStructures.values()[c].toString());
							
							int code = Utility.readInt(BaseLang.translate("pm.serverPlugins.selectCodeStructure") + " ", null);
							plev.addEventContext(param-1, true, CodeUtility.getStructure(CodeUtility.toCodeStructure(code-1)));
						}
						
						if(param == (i+4)){
							plev.saveEvent();
							plev.setEventSetted(true);
							plev.sendDataToCreator();
							Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.serverPlugins.eventAdded"));
						}
						
						if(!plev.isEventSetted()){
							if(param <= (i+1)){
								//Custom
								if(param == (i+1)){
									String custom = Utility.readString(BaseLang.translate("pm.serverPlugins.writeCode") + "\n" , "[" + BaseLang.translate("pm.serverPlugins.escapeChar") + "]");
									plev.addEventContext(param-1, true, custom);
								}else{
									if(accepted[param-1]){
										if(param != 9){
											String vb = Utility.readString(BaseLang.translate("pm.serverPlugins.selectVariableName") + " ", "[" + BaseLang.translate("pm.standard.example") + " $player, $block, ect...]");
											plev.setVariable(vb);
										}
										plev.addEventContext(param-1, false, EventsParameter.values()[param-1].getName());
									}
								}
							}
						}
					}while(!plev.isEventSetted());
					plev.setEventSetted(false);
				}
			}
			
			//Commands Builder
			if(struct == 3){
				plcmd = new PocketmineCommands();
				boolean added = false;
				plcr.setCommandsEnabled(true);
				String cont = "", argName = "";
				int numArg = 0;
				do{
					plcmd.addCommandsStructure();
					Utility.cleanScreen();
					System.out.println(Utility.setTitle(UtilityColor.YELLOW, BaseLang.translate("pm.title.commandsEditor")));
					System.out.println(UtilityColor.BLUE + "---------------------------------");
					System.out.println(UtilityColor.PURPLE + BaseLang.translate("pm.serverPlugins.currentCode") + " ");
					System.out.println(plcmd.getTemporaryData());
					System.out.println(UtilityColor.FORMAT_RESET + UtilityColor.BLUE + "---------------------------------" + UtilityColor.WHITE);
					System.out.println("1- " + BaseLang.translate("pm.serverPlugins.addCommand"));
					System.out.println("2- " + BaseLang.translate("pm.serverPlugins.addCommandContent"));
					System.out.println("3- " + BaseLang.translate("pm.serverPlugins.addCodeStructure"));
					System.err.println("4- " + BaseLang.translate("pm.serverPlugins.cancelCode"));
					System.out.println("5- " + BaseLang.translate("pm.serverPlugins.saveCommand"));
					if(plcmd.isArgumentMode())
						System.out.println("6- " + BaseLang.translate("pm.serverPlugins.saveArgument"));
					int type = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
					
					if(type == 1){
						plcmd.saveCommand();
						plcmd.clearData();
						plcmd.setCommand(Utility.readString(BaseLang.translate("pm.serverPlugins.writeCommand") + " ", null));
					}
					
					if(type == 2 && plcmd.getCommand() != null){
						int i = 0;
						for(i = 0; i < CommandsParameter.values().length; i++)
							System.out.printf("%d) %s\n", (i+1), CommandsParameter.values()[i].toString());
						
						System.out.println((i+1) + ") " + BaseLang.translate("pm.serverPlugins.addArgument") + "  ($args[" + numArg + "])");
						System.out.println((i+2) + ") " + BaseLang.translate("pm.serverPlugins.customLineCode"));
						int code = Utility.readInt(BaseLang.translate("pm.serverPlugins.selectParameter") + " ", null);
						
						if(code == (i+2)){
							cont = Utility.readString(BaseLang.translate("pm.serverPlugins.writeCode") + "\n" , "[" + BaseLang.translate("pm.serverPlugins.escapeChar") + "]");
							plcmd.addLine(cont);
						}else if(code == (i+1)){
							plcmd.setArgumentMode(true);
							argName = Utility.readString(BaseLang.translate("pm.serverPlugins.argName") + " ", null);
							plcmd.setArgument(argName);
							plcmd.setArgumentPosition(numArg);
							plcmd.buildArgument();
							numArg++;
						}else{
							if(code != 2){
								String vb = Utility.readString(BaseLang.translate("pm.serverPlugins.selectVariableName") + " ", "[" + BaseLang.translate("pm.standard.example")  + " $player, $block, ect...]");
								plcmd.setVariable(vb);
							}
							
							cont = CommandsParameter.values()[code-1].getName();
							
							if(code == 2){
								String message = Utility.readString(BaseLang.translate("pm.serverPlugins.messageToSend") + " ", null);
								cont = cont.replaceAll("/message/", message);
							}
							
							plcmd.addLine(cont);
						}
					}
					
					if(type == 3){
						for(int c = 0; c < CodeStructures.values().length; c++)
							System.out.println((c+1) + ") " + CodeStructures.values()[c].toString());
						
						int code_struct = Utility.readInt(BaseLang.translate("pm.serverPlugins.selectCodeStructure") + " ", null);
						cont = CodeUtility.getStructure(CodeUtility.toCodeStructure(code_struct));
						plcmd.addLine(cont);
					}
					
					if(type == 4)
						plcmd.clearData();
					
					if(type == 5){
						if(plcmd.isArgumentMode()){
							plcmd.setArgumentMode(false);
							plcmd.addLine("}");
						}
						plcmd.saveCommand();
						added = true;
						Utility.waitConfirm(UtilityColor.GREEN + BaseLang.translate("pm.serverPlugins.commandsAdded"));
					}
					
					if(type == 6 && plcmd.isArgumentMode()){
						plcmd.setArgumentMode(false);
						plcmd.addLine("}");
						Utility.waitConfirm(UtilityColor.YELLOW + BaseLang.translate("pm.serverPlugins.argumentsAdded"));
					}
					
					if(type != 4 && type > 0)
						plcmd.buildCommand();
				}while(!added);
				plcr.setCommandsEnabled(false);
			}
		}while(!finish);
	}

	private static void deletePlugin(){
		Utility.showServers();
		int server = Utility.readInt(BaseLang.translate("pm.choice.server") + " ", null);
		
		if(server >= 1 && server <= UtilityServersAPI.getNumberServers()){
			if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
				String path = UtilityServersAPI.getPath(server);
				if(path != null){
					File pluginsPath = new File(path + "plugins" + File.separator);
					List<File> files = new ArrayList<>();
					
					int i = 1;
					boolean found = false;
					for(File file : pluginsPath.listFiles()){
						if(file.isFile() && file.getName().endsWith(".phar")){
							found = true;
							System.out.println(i + ") " + file.getName());
							files.add(file);
						}
					}

					if(found){
						int opt = 0;
						do{
							opt = Utility.readInt(BaseLang.translate("pm.serverPlugins.selectPlugin") + " ", null);
							try{
								File plug = files.get(opt-1);
								files.get(opt-1).delete();
								Utility.waitConfirm(UtilityColor.GREEN + plug.getName() + " " + BaseLang.translate("pm.serverPlugins.deleted"));
							}catch(IndexOutOfBoundsException e){
								Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.serverPlugins.errorDeleting"));
							}
						}while(opt < i || opt > i);
					}else
						Utility.waitConfirm(UtilityColor.RED + BaseLang.translate("pm.serverPlugins.pluginNotFound"));
					
				}else
					Utility.waitConfirm(BaseLang.translate("pm.errors.pathNull"));
			}else
				Utility.waitConfirm(BaseLang.translate("pm.errors.pathNotFound"));
		}
		pluginsMenu();
	}

	private static void getPluginsList(){
		Utility.showServers();
		int server = Utility.readInt(BaseLang.translate("pm.choice.server") + " ", null);
		
		if(server >= 1 && server <= UtilityServersAPI.getNumberServers()){
			if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
				String path = UtilityServersAPI.getPath(server);
				if(path != null){
					path = path + "plugins/";
					File files = new File(path);
					int i = 1;
					for(File file : files.listFiles())
						if(file.isFile() && file.getName().endsWith(".phar"))
							System.out.println(i + ") " + file.getName());
					Utility.waitConfirm(BaseLang.translate("pm.standard.enter"));
				}else
					Utility.waitConfirm(BaseLang.translate("pm.errors.pathNull"));
			}else
				Utility.waitConfirm(BaseLang.translate("pm.errors.pathNotFound"));
		}
		pluginsMenu();
	}
}


