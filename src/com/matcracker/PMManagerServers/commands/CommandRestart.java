package com.matcracker.PMManagerServers.commands;

import com.matcracker.PMManagerServers.DevMode;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class CommandRestart {
	public static void command(String[] args){
		try{
			if(args.length > 1){
				if(args[1].equalsIgnoreCase("software"))
					DevMode.reboot();
				else
					System.out.println(UtilityColor.COLOR_PURPLE + "Not implemented yet!");				
			}else
				System.out.println("Too few arguments! Use &c/help&f for the commands list and usage");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Too few or too much arguments! Use &c/help&f for the commands list and usage");
		}
		
	}
}
