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
	
package com.matcracker.PMManagerServers.utility;

import java.util.List;

import java.io.File;
import java.util.ArrayList;

public class PluginCreator{
	/**
	 * plugin.yml
	 */
	PMEvents pmev = new PMEvents();
	
	protected String pluginName = "";
	protected String className = "Main";
	protected String version = "1.0";
	protected String author = "";
	protected String apiVersion = "2.0.0";
	protected String namespace = "src/" + author;
	
	/**
	 * Main class
	 */
	protected boolean pluginBase = true;
	protected boolean listener = false;
	protected String enabled_message = pluginName + " enabled successfully!";
	protected String disabled_message = pluginName + " disabled successfully!";
	
	/**
	 * Events
	 */
	private List<String> events = new ArrayList<>();
	protected String context = "";;
	protected String final_context_events = "";

	private boolean eventSetted = false;
	
	/**
	 * @return instance of PMEvents
	 */
	public PMEvents getEvents(){
		return pmev;
	}
	
	/**
	 * Get informations about plugin.yml
	 */
	public void getPluginYAML(){
		pluginName = Utility.readString("Name of plugin: ", null);
		namespace = Utility.readString("Namespace of plugin: ", "[Example: src/author/pluginname]");
		className = Utility.readString("Main class name: ", "[Example: Main]");
		version = Utility.readString("Version of plugin: ", null);
		author = Utility.readString("Author of plugin: ", null);
		apiVersion = Utility.readString("API Version: ", "[Recommended 2.0.0]");
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
		
	public void createNewClass(){
		File plcr = new File("PluginsCreator" + File.separator + pluginName + File.separator + namespace);
		if(!plcr.exists()) plcr.mkdirs();
		
		Utility.writeStringData(new File(plcr + File.separator + className + ".php"), buildMainStructure().replaceAll("/34/", String.valueOf((char) 34)));
	}
	
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
			   "main: " + temp + "/" + className;
	}
	
	/**
	 * @param listener
	 */
	public void setListener(boolean listener){
		this.listener = listener;
	}
			
	/**
	 * @param event (Example: BlockBreakEvent)
	 * @param context content of event
	 */
	public void addEvent(String event){
		String shortEvent = pmev.getReducedEvents(event);
		String ev = "";
		
		ev = ev +
			"\n" +  "\n" +
			"\tpublic function " + shortEvent + "(" + event + " $event){" + "\n" +
			this.context + "\n" +
			"\t}";
		this.events.add(ev);
	}
	
	private void mergeEvents(){
		for(int i = 0; i < events.size(); i++)
			final_context_events = final_context_events + events.get(i); 
	}
	
	/**
	 * @param type (Example: getPlayer, getBlock)
	 * @param content if null you can set the context how do you want
	 */
	public void addContext(int type, boolean custom, String content){
		String cont = "";
		
		if(custom)
			cont = cont + content;
		else
			cont = cont +
				"\t\t$event->" + pmev.toParameter(type).getName() + "();" +
				"\n";
		
		this.context = context + cont;
	}
		
	private String buildMainStructure(){
		String clazz = "class " + className + " extends PluginBase{";
		if(listener){
			clazz = "class " + className + " extends PluginBase implements Listener{";
			mergeEvents();
		}
		
		String finalClass =
			   "<?php\n" +
			   "\n" +
			   "namespace " + namespace + "\n" +
			   "\n" +clazz + "\n" +
			   	//Start "onEnable"
			   	"\tpublic function onEnable(){\n" +
			   		"\t\t$this->getLogger()->info(/34/" + enabled_message + "/34/);\n" +
			   	"\t}" +
			   	//End "onEnable"
			   	final_context_events + 
			   	//Start "onDisable"
			   	"\n\n\tpublic function onDisable(){\n" + 
			   		"\t\t$this->getLogger()->info(/34/" + disabled_message + "/34/);\n" +
			   	"\t}\n" +
			   	//End "onDisable"
			   "}";
		
		return finalClass;
	}

	public boolean isEventSetted() {
		return eventSetted;
	}

	public void setEventSetted(boolean eventSetted) {
		this.eventSetted = eventSetted;
	}
		
}