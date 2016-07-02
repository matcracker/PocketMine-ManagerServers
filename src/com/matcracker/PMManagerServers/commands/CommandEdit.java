package com.matcracker.PMManagerServers.commands;

import java.io.IOException;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.managers.editing.Performance;
import com.matcracker.PMManagerServers.managers.editing.Properties;
import com.matcracker.PMManagerServers.utility.Utility;

public class CommandEdit {
	public static void command(String[] args){
		try{
			if(args.length > 1){
				int server = -1;
				if(args[1].equalsIgnoreCase("properties")){
					if(Utility.is_numeric(args[2]))
						server = Integer.parseInt(args[2]);
					else{
						for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
							if(args[2].equalsIgnoreCase(UtilityServersAPI.getNameServer(i))){
								server = i;
								break;
							}
						}
					}
					try{
						Properties.editProperties(UtilityServersAPI.getPath(server));
					}catch (IOException ignored){}
				}else if(args[1].equalsIgnoreCase("performance")){
					if(Utility.is_numeric(args[3]))
						server = Integer.parseInt(args[3]);
					else{
						for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
							if(args[3].equalsIgnoreCase(UtilityServersAPI.getNameServer(i))){
								server = i;
								break;
							}
						}
					}
					
					try{
						switch(args[2].toUpperCase()){
							case "HIGH":
							case "MEDIUM":
							case "LOW":
								Performance.changePerformaceFile(UtilityServersAPI.getPath(server), args[2]);
								StatusAPI.setPerformance(BaseLang.translate("pm.status." + args[2].toLowerCase()), server);
							default:
								Performance.changePerformaceFile(UtilityServersAPI.getPath(server), "LOW");
								StatusAPI.setPerformance(BaseLang.translate("pm.status.low"), server);
						}
					}catch (IOException ignored){}
					
					Utility.waitConfirm(BaseLang.translate("pm.performance.complete"));
					
				}else
					System.out.println(BaseLang.translate("pm.cmdMode.tooFew"));
			}else
				System.out.println(BaseLang.translate("pm.cmdMode.tooFew"));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(BaseLang.translate("pm.cmdMode.tooFewMuch"));
		}
	}
}
