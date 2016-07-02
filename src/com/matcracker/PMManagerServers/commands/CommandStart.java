package com.matcracker.PMManagerServers.commands;

import java.io.File;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class CommandStart {
	public static void command(String[] args){
		try{
			if(args.length > 1){
				if(args[1].equalsIgnoreCase("all")){
					for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
						if(UtilityServersAPI.checkServersFile("Path", "path_", i)){
							String pathContent = UtilityServersAPI.getPath(i);
							if(pathContent != null)
								Utility.openSoftware("software", pathContent + File.separator + "start.cmd");
							else
								Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNull"));
						}else
							Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNotFound"));
					}
				}else{
					int server = -1;
					if(Utility.is_numeric(args[1])){
						server = Integer.parseInt(args[1]);
					}else{
						for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
							if(args[1].equalsIgnoreCase(UtilityServersAPI.getNameServer(i))){
								server = i;
								break;
							}
						}
					}
					
					if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
						String pathContent = UtilityServersAPI.getPath(server);
						if(pathContent != null)
							Utility.openSoftware("software", pathContent + File.separator + "start.cmd");
						else
							Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNull"));
					}else
						Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNotFound"));
				}
			}else
				System.out.println("Too few arguments! Use &c/help&f for the commands list and usage");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Too few or too much arguments! Use &c/help&f for the commands list and usage");
		}
	}
}
