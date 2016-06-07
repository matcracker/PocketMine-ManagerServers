package com.matcracker.PMManagerServers;

import java.io.IOException;

import com.matcracker.PMManagerServers.commands.CommandHelp;
import com.matcracker.PMManagerServers.lang.LangSelector;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class CommandsMode {
	
	protected static void menu() throws IOException {
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", "Commands Mode"));
		
		String cmd = "";
		
		while(!cmd.equalsIgnoreCase("menu")){
			String[] args = Utility.readString(UtilityColor.COLOR_WHITE + ">", null).replace("/", "").split(" ");
			cmd = args[0];
			
			switch(cmd){
				case "backup": break;
				case "clear": menu(); System.out.println(UtilityColor.COLOR_GREEN + "Console cleaned!"); break;
				case "edit": break;
				case "exit": System.exit(0); break;
				case "help": CommandHelp.command(); break;
				case "language": LangSelector.langMenu(); break;
				case "menu": Main.mainMenu(); break;
				case "restart": break;
				case "restore":  break;
				case "set start":  break;
				case "start": break;
				case "stop": break;
				default:
					System.out.println(UtilityColor.COLOR_RED + "Command not found!");
			}
		}
	}
}
