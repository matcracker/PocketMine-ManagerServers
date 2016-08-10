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
	
package com.matcracker.PMManagerServers.API;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.matcracker.PMManagerServers.utility.Utility;

public class PluginCreatorAPI{

	PluginEventsAPI plev = new PluginEventsAPI(this);
	PluginCommandsAPI plcmd = new PluginCommandsAPI(this);
	
	/**
	 * plugin.yml
	 */
	protected String pluginName = "Plugin";
	protected String className = "Main";
	protected String version = "1.0";
	protected String author = "anyone";
	protected String apiVersion = "2.0.0";
	protected String namespace = "src\\" + author;
	
	/**
	 * Main class
	 */
	protected boolean pluginBase = true;
	protected boolean listener = false;
	protected String enabled_message = pluginName + " enabled successfully!";
	protected String disabled_message = pluginName + " disabled successfully!";
	
	/**
	 * Imports
	 */
	private List<String> imports = new ArrayList<>();
	private String final_imports = "";
	
	/**
	 * Miscellaneous
	 */
	protected String variable = "$param";

	protected String final_events = "";
	protected String final_commands = "";
	
	/**
	 * @return instance of PluginCommandsAPI
	 */
	public PluginCommandsAPI getPluginCommands(){
		return plcmd;
	}
	
	/**
	 * @return instance of PluginEventsAPI
	 */
	public PluginEventsAPI getPluginEvents(){
		return plev;
	}
	
	/**
	 * Get informations about plugin.yml
	 */
	public void getPluginYAML(){
		String pluginName = Utility.readString("Name of plugin: ", null);
		if(!pluginName.isEmpty()) this.pluginName = pluginName;
		
		String namespace = Utility.readString("Namespace of plugin: ", "[Example: src\\author\\pluginname]");
		if(!namespace.isEmpty()) this.namespace = namespace;
		
		String className = Utility.readString("Main class name: ", "[Example: Main]");
		if(className.isEmpty()) this.className = className;
		
		String version = Utility.readString("Version of plugin: ", null);
		if(!version.isEmpty()) this.version = version;
		
		String author = Utility.readString("Author of plugin: ", null);
		if(!author.isEmpty()) this.author = author;
		
		String apiVersion = Utility.readString("API Version: ", "[Recommended 2.0.0]");
		if(!apiVersion.isEmpty()) this.apiVersion = apiVersion;
	}
	
	/**
	 * Get informations about initial class
	 */
	public void setClassStructure(){
		enabled_message = Utility.readString("Write a message when plugin is enabled: ", null);
		if(enabled_message == null) setClassStructure();
		
		disabled_message = Utility.readString("Write a message when plugin is disabled: ", null);
		if(disabled_message == null) setClassStructure();
	}
	
	/**
	 * Create a class file and must be configured with setClassStructure method (Example of file: Main.php, Task.php, etc...)
	 */
	public void createNewClass(){
		File plcr = new File("PluginsCreator" + File.separator + pluginName + File.separator + namespace);
		if(!plcr.exists()) plcr.mkdirs();
		
		Utility.writeStringData(new File(plcr + File.separator + className + ".php"), buildMainStructure());
	}
	
	/**
	 * Create the file plugin.yml (It's a good thing use getPluginYAML() method first)
	 */
	public void createPluginYAML(){
		File plcr = new File("PluginsCreator" + File.separator + pluginName);
		if(!plcr.exists()) plcr.mkdirs();
		
		Utility.writeStringData(new File(plcr + File.separator + "plugin.yml"), buildPluginYAML());
	}
	
	private String buildPluginYAML(){
		String temp = namespace.replaceAll("src/", "");
		return "name: " + pluginName + "\n" + 
			   "author: " + author + "\n" +
			   "version: " + version + "\n" +
			   "api: [" + apiVersion + "]\n" +
			   "main: " + temp + "\\" + className;
	}

	/**
	 * @param variable (Example: $variable);
	 */
	public void setVariableName(String variable){
		Random rnd = new Random();
		
		if(Utility.is_numeric(variable)) return;

		if(variable == null || variable.equalsIgnoreCase(""))
			variable = this.variable + rnd.nextInt((15 - 1) + 1) + 1;
		
		if(!variable.startsWith("$"))
			variable = "$" + variable;
		
		this.variable = variable;
	}

	/**
	 * @param imports (it adds "use pocketmine\imports")
	 * Example: if imports = hello, in the List will be added -> use pocketmine\hello;
	 */
	public void addImport(String imports){
		for(int i = 0; i < this.imports.size(); i++)
			if(this.imports.get(i).contains(imports))
				return;
		
		this.imports.add("use pocketmine\\" + imports + ";\n");
	}
	
	private void mergeImports(){
		for(int i = 0; i < imports.size(); i++)
			final_imports = final_imports + imports.get(i);
	}

	private String buildMainStructure(){
		String clazz = "class " + className + " extends PluginBase{";
		String onEnable = 
				"\tpublic function onEnable(){\n" +
				   		"\t\t$this->getServer()->getLogger()->info(\"" + enabled_message + "\");\n" +
				"\t}";
		addImport("plugin\\PluginBase");
		if(listener){
			addImport("event\\Listener");
			clazz = "class " + className + " extends PluginBase implements Listener{";
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
			    "namespace " + namespace + "\n\n" +
			    final_imports +
			    "\n" +clazz + "\n" +
			   	//Start "onEnable"
			    onEnable +
			    //End "onEnable"
			    this.final_events + 
			    this.final_commands +			    
			    //Start "onDisable"
			    "\n\n\tpublic function onDisable(){\n" + 
			   		"\t\t$this->getServer()->getLogger()->info(\"" + disabled_message + "\");\n" +
			    "\t}\n" +
			   //End "onDisable"
			    "}";
		return finalClass;
	}
	
	/**
	 * @param type
	 * @return
	 */
	public CodeStructures toCodeStructure(int type){
		if(type < 0 || type > CodeStructures.values().length) return CodeStructures.NOTHING;
		
		return CodeStructures.values()[type];
	}
	
	/**
	 * @param type
	 * @param condition
	 * @return
	 */
	public String getStructure(CodeStructures type){
		String cond = "", result = "", code = "", total = "";
		switch(type){
			case IF:
				cond = Utility.readString("Write condition: ", "[IF]");
				do{
					result = Utility.readString("Write result of IF structure: ", "[IF] Write 'stop' for go out the IF results");
					if(result.equalsIgnoreCase("stop")) break;
					
					total = total + "\n\t\t\t" + result;
				}while(!result.equalsIgnoreCase("stop"));
				
				String elsee = "", total2 = "";
				do{
					elsee = Utility.readString("Write result of ELSE structure: ", "[ELSE] Write 'n' if you don't want add ELSE structure or write 'stop' for go out the ELSE results");
					if(elsee.equalsIgnoreCase("stop") || elsee.equalsIgnoreCase("n")) break;
					
					total2 = total2 + "\n\t\t\t" + elsee;
				}while(!elsee.equalsIgnoreCase("stop"));
				
				//if(condition){ result;
				code = code + 
						"if(" + cond + "){\n" + 
						total;
				//}else{ result;
				if(!elsee.equalsIgnoreCase("n")){
					code = code +
						"\n\t\t}else{\n" + 
						total2;
				}
				//}
				code = code + "\n\t\t}";
				return code;
				
			case ELSE_IF:
				String elseif = "";
				cond = Utility.readString("Write condition: ", "[IF]");
				do{
					result = Utility.readString("Write result of IF structure: ", "[IF] Write 'stop' for go out the IF results");
					if(result.equalsIgnoreCase("stop")) break;
					
					total = total + "\n\t\t\t" + result;
				}while(!result.equalsIgnoreCase("stop"));
				//if(condition){ result;
				code = code + 
						"if(" + cond + "){\n" + 
						total;
				
				do{
					total2 = "";
					elseif = Utility.readString("Write condition: ", "[ELSEIF] Write 'stop' for go out ELSE_IF structure");
					if(elseif.equalsIgnoreCase("stop")) break;
					String result_elseif = "";
					
					do{
						result_elseif = Utility.readString("Write result of ELSE_IF structure: ", null);
						if(result_elseif.equalsIgnoreCase("stop")) break;
						total2 = total2 + "\n\t\t\t" + result_elseif;
					}while(!result_elseif.equalsIgnoreCase("stop"));
					
					code = code + 
							"\n\t\t}elseif(" + elseif + "){" + 
							total2;
				}while(!elseif.equalsIgnoreCase("stop"));

				String total3 = "";
				do{
					elsee = Utility.readString("Write result of ELSE structure: ", "[ELSE] Write 'n' if you don't want add ELSE structure or write 'stop' for go out the ELSE results");
					if(elsee.equalsIgnoreCase("stop") || elsee.equalsIgnoreCase("n")) break;
					
					total3 = total3 + "\n\t\t\t" + elsee;
				}while(!elsee.equalsIgnoreCase("stop"));

				//}elseif(condition){ result;
				if(!elsee.equalsIgnoreCase("n")){
					//}else{ result;
					code = code +
							"\n\t\t}else{\n" + 
							total3;
				}
				//}
				code = code + "\n\t\t}";
				return code;
				
			case FOR:
				setVariableName(Utility.readString("Select counter name: ", "[Example: $i, $c]"));
				String start = Utility.readString("Starting value of counter: ", null);
				cond = Utility.readString("Write condition: ", "[FOR]");
				do{
					result = Utility.readString("Write result of FOR structure: ", "Write 'stop' for go out the results");
					if(result.equalsIgnoreCase("stop")) break;
					
					total = total + "\n\t\t\t" + result;
				}while(!result.equalsIgnoreCase("stop"));
				
				//for(variable = start; cond; variable++){ result; }
				code = code + 
						"for(" + variable + " = " + start + "; " + cond + "; "+ variable + "++){\n" + 
							"\t\t" + total +
						"\n\t\t}";
				return code;
				
			case WHILE:
				cond = Utility.readString("Write condition: ", "[WHILE]");
				do{
					result = Utility.readString("Write result of WHILE structure: ", "Write 'stop' for go out the results");
					if(result.equalsIgnoreCase("stop")) break;
					
					total = total + "\n\t\t\t" + result;
				}while(!result.equalsIgnoreCase("stop"));
				//while(condition){ result; }
				code = code + 
						"while(" + cond + "){\n" +
						total +
						"\n\t\t}";
				return code;
				
			case DO_WHILE:
				cond = Utility.readString("Write condition: ", "[DO_WHILE]");
				do{
					result = Utility.readString("Write result of DO_WHILE structure: ", "Write 'stop' for go out the results");
					if(result.equalsIgnoreCase("stop")) break;
					
					total = total + "\n\t\t\t" + result;
				}while(!result.equalsIgnoreCase("stop"));
				//do{ result; }while(cond);
				code = code + 
						"do{\n" + 
						total +
						"\n\t\t}while(" + cond + ");";
				return code;
				
			case NOTHING:
				return "";
			default:
				return "Type not valid!";
		}
	}
	
	public enum CodeStructures{
		IF,
		ELSE_IF,
		FOR,
		WHILE,
		DO_WHILE,
		NOTHING;
	}
		
}