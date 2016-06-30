package com.matcracker.PMManagerServers.commands;

import java.io.File;

import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;
import com.matcracker.PMManagerServers.utility.Zipper;

import net.lingala.zip4j.exception.ZipException;

public class CommandRescuer {
	public static void commandBackup(String[] args){
		try{
			if(args.length > 1){
				if(args[1].equalsIgnoreCase("all")){
					for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
						if(StatusAPI.getBackuped(i).equalsIgnoreCase(BaseLang.translate("pm.status.noBackuped"))){
							String backupPath = "Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(i) + ".zip";
							try{
								Zipper.zip(UtilityServersAPI.getPath(i), backupPath, null);
							}catch (ZipException e) {
								e.printStackTrace();
							}
						}else
							System.out.println(UtilityColor.COLOR_RED + BaseLang.translate("pm.rescuer.existBackup"));
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
					
					String backupPath = "Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(server) + ".zip";
					if(StatusAPI.getBackuped(server).equalsIgnoreCase(BaseLang.translate("pm.status.noBackuped"))){
						try{
							System.out.println(BaseLang.translate("pm.rescuer.create"));
							Zipper.zip(UtilityServersAPI.getPath(server), backupPath, null);
							StatusAPI.setBackuped(BaseLang.translate("pm.status.backuped"), server);
							Utility.waitConfirm(UtilityColor.COLOR_GREEN + BaseLang.translate("pm.rescuer.backuped"));
						}catch (ZipException e){
							e.printStackTrace();
						}
					}else
						Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.rescuer.existBackup"));
				}
			}else
				System.out.println("Too few arguments! Use &c/help&f for the commands list and usage");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Too few or too much arguments! Use &c/help&f for the commands list and usage");
		}
	}
	
	public static void commandRestore(String[] args){
		try{
			if(args.length > 1){
				String extractServersPath = "";
				String destinationPath = "Backups" + File.separator + "Servers" + File.separator + "Extracted";
				if(args[1].equalsIgnoreCase("all")){
					for(int i = 1; i <= UtilityServersAPI.getNumberServers(); i++){
						extractServersPath = "Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(i) + ".zip";
						if(StatusAPI.getBackuped(i).equalsIgnoreCase(BaseLang.translate("pm.status.backuped"))){
							System.out.println(i + ")"+ BaseLang.translate("pm.rescuer.extracting"));
							Zipper.unzip(extractServersPath, destinationPath, null);
							System.out.println(i + ")" + BaseLang.translate("pm.rescuer.extracted"));
						}else
							System.out.println(UtilityColor.COLOR_RED + BaseLang.translate("pm.rescuer.noBackup"));
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
					extractServersPath = "Backups" + File.separator + "Servers" + File.separator + UtilityServersAPI.getNameServer(server) + ".zip";
					
					if(StatusAPI.getBackuped(server).equalsIgnoreCase(BaseLang.translate("pm.status.backuped"))){
						System.out.println(BaseLang.translate("pm.rescuer.extracting"));
						Zipper.unzip(extractServersPath, destinationPath, null);
						StatusAPI.setBackuped(BaseLang.translate("pm.status.backuped"), server);
						Utility.waitConfirm(UtilityColor.COLOR_GREEN + BaseLang.translate("pm.rescuer.extracted"));
					}else
						Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.rescuer.noBackup"));
				}
			}else
				System.out.println("Too few arguments! Use &c/help&f for the commands list and usage");
		
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Too few or too much arguments! Use &c/help&f for the commands list and usage");
		}
	}
}
