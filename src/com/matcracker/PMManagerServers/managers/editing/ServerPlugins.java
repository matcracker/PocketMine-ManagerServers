package com.matcracker.PMManagerServers.managers.editing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.matcracker.PMManagerServers.API.PluginCommandsAPI;
import com.matcracker.PMManagerServers.API.PluginCreatorAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.API.PluginCreatorAPI.CodeStructures;
import com.matcracker.PMManagerServers.API.PluginEventsAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.managers.Manager;
import com.matcracker.PMManagerServers.utility.PocketMineAPI;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;
import com.matcracker.PMManagerServers.utility.PocketMineAPI.CommandsParameter;
import com.matcracker.PMManagerServers.utility.PocketMineAPI.EventsParameter;

public class ServerPlugins{
	
	static PluginCreatorAPI plcr = new PluginCreatorAPI();
	static PluginCommandsAPI plcmd = new PluginCommandsAPI(plcr);
	static PluginEventsAPI plev = new PluginEventsAPI(plcr);
	private static int numArg = 0;
	
	public static void pluginsMenu(){
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle(UtilityColor.RED, "Server Plugins"));
		System.out.println("1- " + "Get list of plugins on your server.");
		System.out.println("2- " + "Delete a plugin.");
		System.out.println("3- " + "Create a plugin.");
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
		System.out.println(Utility.setTitle(UtilityColor.YELLOW, "Create plugin"));
		System.out.println("1- " + "Create plugin.yml");
		System.out.println("2- " + "Create plugin structure");
		System.out.println("3- " + "Save and finish");
		System.out.println("4- " + BaseLang.translate("pm.standard.back"));
		int opt = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", "[Don't shut down the software when you are creating a plugin! You can lose all your progress]");
		
		if(opt == 1){
			plcr.getPluginYAML();
			plcr.createPluginYAML();
			Utility.waitConfirm(UtilityColor.GREEN + "File plugin.yml created!");
		}
		
		if(opt == 2)
			pluginCreatorMenu();
		
		if(opt == 3){
			plcr.createNewClass();
			Utility.waitConfirm(UtilityColor.GREEN + "Class created!");
			plcr = new PluginCreatorAPI();
		}
		
		if(opt == 4)
			pluginsMenu();
		
		createPlugin();
	}
	
	private static void pluginCreatorMenu(){
		boolean finish = false;
		do{
			Utility.cleanScreen();
			System.out.println(Utility.setTitle(UtilityColor.YELLOW, "Create structure"));
			System.out.println("1- Add standard structure (onEnable/onDisable)");
			System.out.println("2- Add events structure");
			System.out.println("3- Add commands structure");
			System.out.println("4- " + BaseLang.translate("pm.standard.back"));
			int struct = Utility.readInt("Select structure type: ", "[Recommend, start from step 1]");
			
			if(struct == 4)
				break;
			
			//Class builder
			if(struct == 1)
				plcr.setClassStructure();
			
			//Events Builder
			if(struct == 2){
				PocketMineAPI pmev = plev.getPocketMineEvents();
				plev.setListener(true);
				
				Utility.cleanScreen();
				System.out.println(Utility.setTitle(UtilityColor.YELLOW, "Events Selector"));
				System.out.println("1- Blocks");
				System.out.println("2- Entities");
				System.out.println("3- Inventories");
				System.out.println("4- Levels");
				System.out.println("5- Players");
				System.out.println("6- Plugin");
				System.out.println("7- " + BaseLang.translate("pm.standard.back"));
				int event = Utility.readInt("Select event to add: ", null);
				
				if(event == 7) return;
				
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
				
				int i = 0;
				for(i = 0; i < events.length; i++)
					System.out.printf("%d) %s\n", (i+1), pmev.getEvent(i, events));
			
				System.out.println((i+1) + ") " + BaseLang.translate("pm.standard.back"));

				int type = Utility.readInt("Select type: ", null);
				
				if(type == (i+1) || type > events.length) return;
				
				if(type > 0){
					do{
						i = 0;
						Utility.cleanScreen();
						plev.addEvent(pmev.getEvent(type-1, events));
						System.out.println(Utility.setTitle(UtilityColor.YELLOW, "Parameter Selector"));
						System.out.println(UtilityColor.BLUE + "---------------------------------");
						System.out.println(UtilityColor.PURPLE + "Current code: ");
						if(!plev.events.isEmpty()) System.out.println(plev.getEventsContent());
						System.out.println(plev.getEventContent());
						System.out.println(UtilityColor.FORMAT_RESET + UtilityColor.BLUE + "---------------------------------" + UtilityColor.WHITE);
						
						boolean[] accepted = pmev.filterParam(pmev.getEvent(type-1, events));
						for(i = 0; i < accepted.length; i++){
							if(accepted[i])
								System.out.printf("%d) %s\n", (i+1), EventsParameter.values()[i].getName());
						}
						System.out.println((i+1) + ") " + "Add custom line code");
						System.err.println((i+2) + ") " + "Cancel code");
						System.out.println((i+3) + ") " + "Add code structure (If, for, while...)");
						System.out.println((i+4) + ") " + "Save and leave event editor");
						
						int param = Utility.readInt("Select parameter: ", null);
						
						if(param <= 0) return;
						
						if(param == (i+2)) plev.removeEventsContext();
						
						if(param == (i+3)){
							for(int c = 0; c < CodeStructures.values().length; c++)
								System.out.println((c+1) + ") " + CodeStructures.values()[c].toString());
							
							int code = Utility.readInt("Select structure code type: ", null);
							plev.addEventContext(param-1, true, plcr.getStructure(plcr.toCodeStructure(code-1)));
						}
						
						if(param == (i+4)){
							plev.saveEvent();
							plev.setEventSetted(true);
							plev.sendToAPI();
							Utility.waitConfirm(UtilityColor.GREEN + "Event added correctly!");
						}
						
						if(!plev.isEventSetted()){
							if(param <= (i+1)){
								//Custom
								if(param == (i+1)){
										String custom = Utility.readString("Write the code to insert:\n" , "[Use escapes char]");
										plev.addEventContext(param-1, true, custom);
									
								}else{
									if(accepted[param-1]){
										if(param != 9){
											String vb = Utility.readString("Select name of variable: ", "[Example: $player, $block, ect...]");
											plcr.setVariableName(vb);
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
				boolean added = false;
				plcmd.setCommandsEnabled(true);
				String command = "", cont = "", argName = "";
				do{
					plcmd.addCommandsStructure();
					Utility.cleanScreen();
					System.out.println(Utility.setTitle(UtilityColor.YELLOW, "Commands Structure"));
					System.out.println(UtilityColor.BLUE + "---------------------------------");
					System.out.println(UtilityColor.PURPLE + "Current code: ");
					if(!plcmd.commands.isEmpty()) System.out.println(plcmd.getCommandContent());
					System.out.println(plcmd.getCommandsContent());
					System.out.println(UtilityColor.FORMAT_RESET + UtilityColor.BLUE + "---------------------------------" + UtilityColor.WHITE);
					System.out.println("1- " + "Add command");
					System.out.println("2- " + "Add command content");
					System.out.println("3- " + "Add code structure (If, for, while...)");
					System.err.println("4- " + "Cancel code");
					System.out.println("5- " + "Save and leave commands editor");
					int type = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
					
					if(type == 1)
						command = Utility.readString("Write command name: ", null);

					if(type == 2 && !command.equalsIgnoreCase("")){
						int i = 0;
						for(i = 0; i < CommandsParameter.values().length; i++)
							System.out.printf("%d) %s\n", (i+1), CommandsParameter.values()[i].toString());
						
						System.out.println((i+1) + "- " + "Add arguments on command");
						System.out.println((i+2) + "- " + "Add custom line code");
						int code = Utility.readInt("Select parameter: ", null);
						
						if(code == (i+2)){
							cont = Utility.readString("Write the code to insert:\n" , "[Use escapes char]");
							plcmd.addCommandContext(code-1, true, cont);
						}else if(code == (i+1)){
							argName = Utility.readString("Name of argument: ", null);
							
						}else{
							if(code != 2){
								String vb = Utility.readString("Select name of variable: ", "[Example: $player, $block, ect...]");
								plcr.setVariableName(vb);
							}
							
							cont = CommandsParameter.values()[code-1].getName();
							
							if(code == 2){
								String message = Utility.readString("Message to send: ", null);
								cont = cont.replaceAll("/message/", message);
							}
							
							if(code != (i+1))
								plcmd.addCommandContext(code-1, false, cont);
							else{
								plcmd.addArgument(numArg, argName, cont);
								numArg++;
							}
	
						}
					}
					
					if(type == 3 && !command.equalsIgnoreCase("")){
						for(int c = 0; c < CodeStructures.values().length; c++)
							System.out.println((c+1) + ") " + CodeStructures.values()[c].toString());
						
						int code_struct = Utility.readInt("Select structure code type: ", null);
						cont = plcr.getStructure(plcr.toCodeStructure(code_struct));
					}
					
					if(!command.equalsIgnoreCase("") && !cont.equalsIgnoreCase(""))
						plcmd.addCommand(command);
					
					if(type == 4)
						plcmd.removeCommandsContext();
					
					if(type == 5){
						plcmd.saveCommand();
						plcmd.sendToAPI();
						added = true;
						Utility.waitConfirm(UtilityColor.GREEN + "Command(s) added!");
					}
				}while(!added);
				plcmd.setCommandsEnabled(false);
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
							opt = Utility.readInt("Select plugin to delete: ", null);
							try{
								File plug = files.get(opt-1);
								files.get(opt-1).delete();
								Utility.waitConfirm(UtilityColor.GREEN + plug.getName() + " deleted successfully!");
							}catch(IndexOutOfBoundsException e){
								Utility.waitConfirm(UtilityColor.RED + "Error on deleting plugin file!");
							}
						}while(opt < i || opt > i);
					}else
						Utility.waitConfirm(UtilityColor.RED + "Plugins don't found!");
					
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


