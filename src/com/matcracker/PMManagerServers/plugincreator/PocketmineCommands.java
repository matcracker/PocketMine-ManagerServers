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

import java.util.ArrayList;
import java.util.Random;

import com.matcracker.PMManagerServers.utility.Utility;

public class PocketmineCommands{
	
	/**
	 * Commands
	 */
	private ArrayList<String> lines = new ArrayList<String>();
	protected String command;
	private String variable;
	private String cmdContext;
	private String temp_command = "";
	private String temp_data;
	private boolean argumentMode = false;

	public PocketmineCommands(){}
	
	public PocketmineCommands(String command, String lines[]){
		setCommand(command);
		setLines(convertArray(lines));
	}
	
	public PocketmineCommands(String command, ArrayList<String> lines){
		setCommand(command);
		setLines(lines);
	}
	
	public void setVariable(String variable){
		if(Utility.is_numeric(variable)) return;

		if(variable == null || variable.equalsIgnoreCase(""))
			variable = "vb" + new Random().nextInt(20);
		
		if(!variable.startsWith("$"))
			variable = "$" + variable;
		
		this.variable = variable;
	}
	
	public String getVariable(){
		return variable;
	}
	
	public void setCommand(String command){
		if(!command.isEmpty())
			this.command = command;
	}
	
	public String getCommand(){
		return command;
	}
	
	public void addLine(String line){
		String code = "\n\t\t\t";
		
		if(argumentMode)
			code += "\t";
		
		if(variable != null)
			code += variable + " = " + line + ";\n";
		else
			if(line.endsWith("}"))
				code += line + "\n";
			else
				code += line + ";\n";
		
		lines.add(code);
		variable = null;
	}
	
	public ArrayList<String> getLines(){
		return lines;
	}
	
	public void setLines(ArrayList<String> lines){
		this.lines = lines;
	}
	
	private ArrayList<String> convertArray(String[] vector){
		ArrayList<String> newArray = new ArrayList<String>();
		
		for(String s : vector)
			newArray.add(s);
		
		return newArray;
	}
	
	public void clearData(){
		command = "";
		temp_command = null;
		variable = null;
		argument = null;
		argPosition = 0;
		argumentMode = false;
		lines.clear();
	}
	
	private void addContext(){
		cmdContext = "";
		if(lines.isEmpty())
			cmdContext = "\n";
		else
			for(String s : lines)
				cmdContext += (s + "\n");
	}

	public void buildCommand(){
		PocketminePluginCreator.addImport("command\\CommandSender");
		PocketminePluginCreator.addImport("command\\Command");
		
		addContext();
		
		temp_command = "\n\t\tif($cmd == \"" + command + "\"){\n" + 
				  cmdContext +
			      "\n\t\t}\n";

	}
	
	public void saveCommand(){
		addLine("return true");
		buildCommand();
		if(temp_command != null)
			PocketminePluginCreator.getCommands().add(temp_command);
	}
	
	/**
	 * @return
	 */
	public String getTemporaryData(){
		if(temp_data != null)
			return temp_data;
		else
			return "";
	}

	public void addCommandsStructure(){
		temp_data = "\n\tpublic function onCommand(CommandSender $sender, Command $command, $lbl, array $args[]){\n" +
					"\t\t$cmd = strtolower($command->getName());\n" +
					temp_command +
					"\n\t\treturn false;\n" +
					"\t}";
	}
	
	/**
	 * Argument part
	 */
	private String argument;
	private int argPosition;

	/**
	 * 
	 * @param numArg define the position of array ($args[numArg])
	 * @param argValue
	 * @param content
	 */
	public void buildArgument(){
		lines.add("\t\t\t$args[" + argPosition + "] = strtolower($args[" + argPosition + "]);\n");
		lines.add("\t\t\tif($args[" + argPosition + "] == \"" + argument + "\"){");
	}
	
	public void setArgument(String argument){
		this.argument = argument;
	}
	
	public String getArgument(){
		return argument;
	}
	
	public boolean isArgumentMode() {
		return argumentMode;
	}

	public void setArgumentMode(boolean argumentMode) {
		this.argumentMode = argumentMode;
	}

	public int getArgumentPosition(){
		return argPosition;
	}

	public void setArgumentPosition(int argPosition){
		if(argPosition < 0) argPosition = 0;
		
		this.argPosition = argPosition;
	}

	


}
