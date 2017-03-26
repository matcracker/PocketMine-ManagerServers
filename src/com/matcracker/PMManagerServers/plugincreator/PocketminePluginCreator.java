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
	
package com.matcracker.PMManagerServers.plugincreator;

import java.io.File;
import java.util.ArrayList;

import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;

public class PocketminePluginCreator{
	
	private PocketMinePluginYAML yaml;
	
	public PocketminePluginCreator(PocketMinePluginYAML yaml){
		this.yaml = yaml;
	}
	
	/**
	 * Main class
	 */
	private boolean listener = false;
	private String enabled_message;
	private String disabled_message;
	
	/**
	 * Imports
	 */
	private static ArrayList<String> imports = new ArrayList<String>();
	private String final_imports = "";
	
	/**
	 * Commands
	 */
	private static ArrayList<String> commands = new ArrayList<String>();
	protected boolean isCommandsEnabled = false;
	
	/**
	 * Miscellaneous
	 */
	protected String variable = "$vb";

	protected static String final_events = "";

	/**
	 * Get informations about initial class
	 */
	public void setClassStructure(){
		enabled_message = Utility.readString(BaseLang.translate("pm.serverPlugins.messageOnEnable") + " ", null);
		if(enabled_message.isEmpty()) enabled_message = yaml.getPluginName() + " enabled successfully!";
		
		disabled_message = Utility.readString(BaseLang.translate("pm.serverPlugins.messageOnDisable") + " ", null);
		if(disabled_message.isEmpty()) disabled_message = yaml.getPluginName() + " disabled successfully!";
	}
	
	/**
	 * Create a class file and must be configured with setClassStructure method (Example of file: Main.php, Task.php, etc...)
	 */
	public void createNewClass(){
		File plcr = new File("PluginsCreator" + File.separator + yaml.getPluginName() + File.separator + yaml.getNamespace());
		if(!plcr.exists()) plcr.mkdirs();
		
		Utility.writeStringData(new File(plcr + File.separator + yaml.getClassName() + ".php"), buildMainStructure());
	}
	
	public void setListener(boolean listener){
		this.listener = listener;
	}
	
	public boolean isListenerEnabled(){
		return listener;
	}
	
	public void setCommandsEnabled(boolean cmdEnabled){
		this.isCommandsEnabled = cmdEnabled;
	}
	

	public boolean isCommandEnabled(){
		return isCommandsEnabled;
	}
	
	public static ArrayList<String> getCommands(){
		return commands;
	}
	
	public static String getAllCommands(){
		if(commands.isEmpty()) return "";
		
		String cmds = "\n\tpublic function onCommand(CommandSender $sender, Command $command, $lbl, array $args[]){\n" +
					  "\t\t$cmd = strtolower($command->getName());\n";
		for(String s : commands)
			cmds += s + "\n";
		
		cmds +=	"\n\t\treturn false;\n" +
				"\t}";
		
		return cmds;
	}

	/**
	 * @param importz (it adds "use pocketmine\imports")
	 * Example: if importz = hello, in the List will be added -> use pocketmine\hello;
	 */
	public static void addImport(String importz){
		for(int i = 0; i < imports.size(); i++)
			if(imports.get(i).contains(importz))
				return;
		
		imports.add("use pocketmine\\" + importz + ";\n");
	}
	
	private void mergeImports(){
		for(String imports : PocketminePluginCreator.imports)
			final_imports += imports;
	}

	private String buildMainStructure(){
		String clazz = "class " + yaml.getClassName() + " extends PluginBase{";

		String onEnable = 
				"\tpublic function onEnable(){\n" +
				   		"\t\t$this->getServer()->getLogger()->info(\"" + enabled_message + "\");\n" +
				"\t}";
		
		addImport("plugin\\PluginBase");
		
		if(listener){
			addImport("event\\Listener");
			clazz = "class " + yaml.getClassName() + " extends PluginBase implements Listener{";
			onEnable = 
				"\tpublic function onEnable(){\n" +
				   		"\t\t$this->getServer()->getLogger()->info(\"" + enabled_message + "\");\n" +
				   		"\t\t$this->getServer()->getPluginManager()->registerEvents($this, $this);\n" +
				"\t}";
		}
		
		mergeImports();
		
		String finalClass =
			    "<?php\n" +
			    "\n" +
			    "namespace " + yaml.getNamespace() + "\n\n" +
			    final_imports +
			    "\n" +clazz + "\n" +
			   	//Start "onEnable"
			    onEnable +
			    //End "onEnable"
			    final_events + 
			    getAllCommands() +
			    //Start "onDisable"
			    "\n\n\tpublic function onDisable(){\n" + 
			   		"\t\t$this->getServer()->getLogger()->info(\"" + disabled_message + "\");\n" +
			    "\t}\n" +
			   //End "onDisable"
			    "}";
		return finalClass;
	}
	

		
}