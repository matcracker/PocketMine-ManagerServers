package com.matcracker.PMManagerServers;

import java.io.IOException;

import com.matcracker.PMManagerServers.commands.CommandEdit;
import com.matcracker.PMManagerServers.commands.CommandHelp;
import com.matcracker.PMManagerServers.commands.CommandRescuer;
import com.matcracker.PMManagerServers.commands.CommandRestart;
import com.matcracker.PMManagerServers.commands.CommandSetStart;
import com.matcracker.PMManagerServers.commands.CommandStart;
import com.matcracker.PMManagerServers.commands.CommandStop;
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
			cmd = args[0].toLowerCase();
			
			switch(cmd){
				case "backup": CommandRescuer.commandBackup(args); break;
				case "clear": menu(); System.out.println(UtilityColor.COLOR_GREEN + "Console cleaned!"); break;
				case "edit": CommandEdit.command(args); break;
				case "exit": System.exit(0); break;
				case "help": CommandHelp.command(); break;
				case "language": LangSelector.langMenu(); break;
				case "menu": Main.mainMenu(); break;
				case "restart": CommandRestart.command(args); break;
				case "restore": CommandRescuer.commandRestore(args);   break;
				case "setstart": CommandSetStart.command(args); break;
				case "start": CommandStart.command(args); break;
				case "stop": CommandStop.command(args); break;
				default:
					System.out.println(UtilityColor.COLOR_RED + "Command not found!");
			}
		}
	}
}
