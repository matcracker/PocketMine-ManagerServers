package com.matcracker.PMManagerServers.commands;

import com.matcracker.PMManagerServers.API.APIManager;

public class CommandSetStart {
	public static void command(String[] args){
		try{
			if(args.length > 1){
				if(args[1].equalsIgnoreCase("commander")){
					APIManager.setCommandsMode(true);
					System.out.println("&2Enable default start in commands mode");
				}else if(args[1].equalsIgnoreCase("menu")){
					APIManager.setCommandsMode(false);
					System.out.println("&2Enable default start in menu mode");
				}
			}else
				System.out.println("Too few arguments! Use &c/help&f for the commands list and usage");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Too few or too much arguments! Use &c/help&f for the commands list and usage");
		}
	}
}
