package com.matcracker.PMManagerServers.API;

import java.util.ArrayList;
import java.util.List;

public class PluginCommandsAPI{
	
	protected PluginCreatorAPI api;
	
	/**
	 * Commands
	 */
	public List<String> commands = new ArrayList<>();
	protected List<String> cmdContext = new ArrayList<>();
	protected String final_commands = "";
	protected boolean isCommandsEnabled = false;
	
	public PluginCommandsAPI(PluginCreatorAPI api){
		this.api = api;
	}
	
	protected String buildCommandStructure(){
		String content = "";
		
		api.addImport("command\\CommandSender");
		api.addImport("command\\Command");
		
		if(isCommandEnabled())
			content = content + 
				"\n\t\tpublic function onCommand(CommandSender $sender, Command $command, $lbl, array $args[]){\n" +
				"\t\t\t$cmd = strtolower($command->getName());\n" +
				final_commands + "\n" +
				"\t\t\treturn false;\n" +
				"\t\t}";
		
		return content;
	}
	
	/**
	 * @return
	 */
	public String getCommandsContent(){
		mergeCommands();
		return buildCommandStructure();
	}
	
	protected void mergeCommands(){
		if(commands.isEmpty()) return;
		
		for(int i = 0; i < commands.size(); i++){
			if(final_commands.contains(commands.get(i))) return;
			
			final_commands = final_commands + "\n\n\t\t" +
					"if($cmd == \"" + commands.get(i) + "\"){\n" + 
					"\t\t\t" + cmdContext.get(i) +
					"\n\t\t}";
		}
	}
	
	public void addCommand(String command){
		this.commands.add(command);
	}
	
	public void addCommandContext(String content){
		String cont = "";
		
		this.cmdContext.add(cont);
		api.variable = "$param";
	}

	public void removeCommandsContext(){
		cmdContext.clear();
		this.final_commands = "";
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
