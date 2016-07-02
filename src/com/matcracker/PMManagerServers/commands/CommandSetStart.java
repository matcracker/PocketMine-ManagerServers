package com.matcracker.PMManagerServers.commands;

import com.matcracker.PMManagerServers.API.APIManager;
import com.matcracker.PMManagerServers.lang.BaseLang;

public class CommandSetStart {
	public static void command(String[] args){
		try{
			if(args.length > 1){
				if(args[1].equalsIgnoreCase("commander")){
					APIManager.setCommandsMode(true);
					System.out.println("&2Enabled default start in commands mode");
				}else if(args[1].equalsIgnoreCase("menu")){
					APIManager.setCommandsMode(false);
					System.out.println("&2Enabled default start in menu mode");
				}
			}else
				System.out.println(BaseLang.translate("pm.cmdMode.tooFew"));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(BaseLang.translate("pm.cmdMode.tooFewMuch"));
		}
	}
}
