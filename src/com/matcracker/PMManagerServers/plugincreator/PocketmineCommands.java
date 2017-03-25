package com.matcracker.PMManagerServers.plugincreator;

import java.util.ArrayList;
import java.util.Random;

import com.matcracker.PMManagerServers.utility.Utility;

public class PocketmineCommands{
	
	/**
	 * Commands
	 */
	private ArrayList<String> lines = new ArrayList<String>();
	protected String command = "";
	private String variable;

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
		String code = "";
		
		if(variable != null)
			code = "\n\t\t\t\t" + variable + " = " + line + ";\n";
		else
			code = "\n\t\t\t\t" + line + ";\n";
		
		lines.add(code);
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
		command = null;
		temp_command = null;
		variable = null;
		lines.clear();
	}
	
	private String cmdContext;
	
	private void addContext(){
		if(lines.isEmpty())
			cmdContext = "\n";
		else
			for(String s : lines)
				cmdContext += (s + "\n");
	}
	
	private String temp_command;
	
	public void buildCommand(){
		PocketminePluginCreator.addImport("command\\CommandSender");
		PocketminePluginCreator.addImport("command\\Command");
		
		addContext();
		
		temp_command = "\n\t\t\tif($cmd == \"" + command + "\"){\n" + 
				  cmdContext +
			      "\n\t\t\t}\n";

	}
	
	public void saveCommand(){
		if(temp_data != null)
			PocketminePluginCreator.getCommands().add(temp_command);
	}
	
	private String temp_data;
	
	/**
	 * @return
	 */
	public String getTemporaryData(){
		return temp_data;
	}

	public void addCommandsStructure(){
		temp_data = "\n\t\tpublic function onCommand(CommandSender $sender, Command $command, $lbl, array $args[]){\n" +
					"\t\t\t$cmd = strtolower($command->getName());\n" +
					temp_command +
					"\n\t\t\treturn false;\n" +
					"\t\t}";
	}
		
	/* 	
	public void addArgument(int numArg, String argValue, String content){
		if(numArg < 0 || argValue.isEmpty()) return;
		
		String cont = "\n\t\t\t\t$args[" + numArg + "] = strlower($args[" + numArg + "]);";
		cont += "\n\t\t\t\t" +
					  "if($args[" + numArg + "] == \"" + argValue + "\"{\n" +
					  "\t\t\t\t\t" + content + ";\n" +
					  "\t\t\t\t}";
		
		this.cmdContext += cont;
		super.variable = "$param";
	}

	*/
	


}
