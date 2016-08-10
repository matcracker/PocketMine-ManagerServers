package com.matcracker.PMManagerServers.API;

import java.util.ArrayList;
import java.util.List;

import com.matcracker.PMManagerServers.utility.PocketMineAPI.CommandsParameter;

public class PluginCommandsAPI{
	
	protected PluginCreatorAPI api;
	
	/**
	 * Commands
	 */
	public List<String> commands = new ArrayList<>();
	protected String cmdContext = "";
	protected String cmd = "";
	protected String final_commands = "";
	protected boolean isCommandsEnabled = false;
	protected String final_structure = "";
	
	public PluginCommandsAPI(PluginCreatorAPI api){
		this.api = api;
	}
		
	/**
	 * @return
	 */
	public String getCommandsContent(){
		return final_structure;
	}
	
	public void sendToAPI(){
		api.final_commands = buildCommandsStructure();
	}
	
	protected String buildCommandsStructure(){
		if(commands.isEmpty()) return "";
		
		api.addImport("command\\CommandSender");
		api.addImport("command\\Command");
		
		return this.commands.get(0);
	}
	
	public String getCommandContent(){
		return this.cmdContext;
	}

	public void addCommandsStructure(){
		final_structure =
				"\n\t\tpublic function onCommand(CommandSender $sender, Command $command, $lbl, array $args[]){\n" +
				"\t\t\t$cmd = strtolower($command->getName());\n" +
				this.cmd + "\n" +
				"\t\t\treturn false;\n" +
				"\t\t}";
	}
	
	public void saveCommand(){
		this.commands.add(final_structure);
		final_commands = "";
		removeCommandsContext();
	}
	private String oldCmd = "";
	
	public void addCommand(String command){
		if(command.isEmpty()) return;
		
		if(!oldCmd.equalsIgnoreCase(command)){
			this.cmdContext = "";
			this.cmd = cmd + 
					"\n\t\t\tif($cmd == \"" + command + "\"){\n" + 
					this.cmdContext +
					"\n\t\t\t}\n";
		}else
			this.cmd =
				"\n\t\t\tif($cmd == \"" + command + "\"){" + 
				this.cmdContext +
				"\n\t\t\t}\n";
		oldCmd = command;
	}
	
	public void addCommandContext(int type, boolean custom, String content){
		if(content.isEmpty() || type < CommandsParameter.values().length) return;
		String cont = "";
		
		if(custom)
			cont = cont + "\n\t\t\t\t" + content + ";\n";
		else{
			cont = cont + "\n\t\t\t\t";
		
			cont = cont + 
				api.variable + " = " + CommandsParameter.values()[type].getName() + ";" +
				"\n";
		}
		this.cmdContext = this.cmdContext + cont;
		api.variable = "$param";
	}
	
	/**
	 * @param numArg
	 * @param argValue
	 * @param content
	 */
	public void addArgument(int numArg, String argValue, String content){
		if(numArg < 0 || argValue.isEmpty()) return;
		
		String cont = "\n\t\t\t\t$args[" + numArg + "] = strlower($args[" + numArg + "]);";
		cont = cont + "\n\t\t\t\t" +
					  "if($args[" + numArg + "] == \"" + argValue + "\"{\n" +
					  "\t\t\t\t\t" + content + ";\n" +
					  "\t\t\t\t}";
		
		this.cmdContext = this.cmdContext + cont;
		api.variable = "$param";
	}

	public void removeCommandsContext(){
		this.cmdContext = "";
	}
	
	/**
	 * @param cmdEnabled
	 */
	public void setCommandsEnabled(boolean cmdEnabled){
		this.isCommandsEnabled = cmdEnabled;
	}
	
	/**
	 * @return
	 */
	public boolean isCommandEnabled(){
		return isCommandsEnabled;
	}
}
