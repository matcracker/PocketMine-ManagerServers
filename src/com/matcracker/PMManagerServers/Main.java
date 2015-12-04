package com.matcracker.PMManagerServers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.matcracker.PMManagerServers.CollectionOfUtility.CollectionsOfUtility;
import com.matcracker.PMManagerServers.Installer.Installator;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String menu = null, quit = "n";
		final String inputError = "Error during the chooise!";
		
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader keyword = new BufferedReader(input);
		
		while(quit.equalsIgnoreCase("n")){
			System.out.println("========================<PocketMine Manager Servers>============================");
			System.out.println("=================================<Main menù>====================================");
			System.out.println("Developed by matcracker                                            Version: 0.1J");
			System.out.println("1- Install PocketMine-MP");
			System.out.println("2- Manage Servers");
			System.out.println("3- Options");
			System.out.println("4- Informations");
			System.out.println("5- Exit\n");
			
			try{
				System.out.print("What would you like to do? ");
				menu = keyword.readLine();
			}catch(Exception e){
				System.out.println(inputError);
			}
			
			if(menu.equalsIgnoreCase("1"))
				Installator.installator();
			CollectionsOfUtility.cleanScreen();
		}
	}

}
