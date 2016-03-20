package com.matcracker.PMManagerServers.Informations;

import java.io.IOException;

import com.matcracker.PMManagerServers.Utility.Utility;

public class Informations {
  /** _____           _        _   __  __ _                   __  __                                   _____                              
	*|  __ \         | |      | | |  \/  (_)                 |  \/  |                                 / ____|                             
	*| |__) |__   ___| | _____| |_| \  / |_ _ __   ___ ______| \  / | __ _ _ __   __ _  __ _  ___ _ _| (___   ___ _ ____   _____ _ __ ___ 
	*|  ___/ _ \ / __| |/ / _ \ __| |\/| | | '_ \ / _ \______| |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '__\___ \ / _ \ '__\ \ / / _ \ '__/ __|
	*| |  | (_) | (__|   <  __/ |_| |  | | | | | |  __/      | |  | | (_| | | | | (_| | (_| |  __/ |  ____) |  __/ |   \ V /  __/ |  \__ \
	*|_|   \___/ \___|_|\_\___|\__|_|  |_|_|_| |_|\___|      |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_| |_____/ \___|_|    \_/ \___|_|  |___/
	*                                                                                   __/ |                                             
	*                                                                                  |___/                                              
	*Copyright (C) 2015 @author matcracker
	*
	*This program is free software: you can redistribute it and/or modify 
	*it under the terms of the GNU Lesser General Public License as published by 
	*the Free Software Foundation, either version 3 of the License, or 
	*(at your option) any later version.
	*/
	
	public static void informationsMenu() throws IOException{
		Utility.cleanScreen();
	    
		System.out.println("========================<PocketMine Manager Servers>============================");
		System.out.println("--------------------------------<Informations>----------------------------------");
		System.out.println("1- License");
		System.out.println("2- Credits");
		System.out.println("3- More informations");
		System.out.println("4- Disclaimer");
		System.out.println("5- Back");
		
		System.out.print("\nChoose information: ");
		String info = Utility.keyword.readLine();
		
		if(info.equalsIgnoreCase("1")){
			//File license = new File("LICENSE.pdf");
				//if(license.isFile() && license.exists())
				
		}
		
		if(info.equalsIgnoreCase("2")){
			Utility.cleanScreen();
			System.out.println("========================<PocketMine Manager Servers>============================");
			System.out.println("-----------------------------------<Credits>------------------------------------");
			System.out.println("This program is free software made by matcracker: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or at your option) any later version.");
			System.out.println();
			System.out.println("This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.");
			System.out.println();
			System.out.println("You should have received a copy of the GNU Lesser General Public License along with this program.  If not, see in this program in the section 'Informations ->  License'.");
			System.out.println();
			System.out.println("Press ENTER go to back");
			System.in.read();
			Informations.informationsMenu();
		}
			
	}
}
