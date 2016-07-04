package com.matcracker.PMManagerServers.commands;

import com.matcracker.PMManagerServers.DevMode;
import com.matcracker.PMManagerServers.lang.BaseLang;
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
				System.out.println(BaseLang.translate("pm.cmdMode.tooFew"));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(BaseLang.translate("pm.cmdMode.tooFewMuch"));
		}
		
	}
}
