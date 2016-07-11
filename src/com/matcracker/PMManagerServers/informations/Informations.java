/* _____           _        _   __  __ _                   __  __                                   _____                              
 *|  __ \         | |      | | |  \/  (_)                 |  \/  |                                 / ____|                             
 *| |__) |__   ___| | _____| |_| \  / |_ _ __   ___ ______| \  / | __ _ _ __   __ _  __ _  ___ _ _| (___   ___ _ ____   _____ _ __ ___ 
 *|  ___/ _ \ / __| |/ / _ \ __| |\/| | | '_ \ / _ \______| |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '__\___ \ / _ \ '__\ \ / / _ \ '__/ __|
 *| |  | (_) | (__|   <  __/ |_| |  | | | | | |  __/      | |  | | (_| | | | | (_| | (_| |  __/ |  ____) |  __/ |   \ V /  __/ |  \__ \
 *|_|   \___/ \___|_|\_\___|\__|_|  |_|_|_| |_|\___|      |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_| |_____/ \___|_|    \_/ \___|_|  |___/
 *                                                                                   __/ |                                             
 *                                                                                  |___/                                              
 *Copyright (C) 2015-2016 @author matcracker
 *
 *This program is free software: you can redistribute it and/or modify 
 *it under the terms of the GNU Lesser General Public License as published by 
 *the Free Software Foundation, either version 3 of the License, or 
 *(at your option) any later version.
*/
	
package com.matcracker.PMManagerServers.informations;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.PMMS;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class Informations {
	
	public static void informationsMenu() throws IOException{
		final String gitlink = "https://github.com/matcracker/PocketMine-ManagerServers-Java";
		final String twitterlink = "https://twitter.com/matcracker98";
		
		Utility.cleanScreen();
	    
		System.out.println(Utility.softwareName);
		System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.informations")));
		System.out.println("1- " + BaseLang.translate("pm.informations.license"));
		System.out.println("2- " + BaseLang.translate("pm.informations.credits"));
		System.out.println("3- " + BaseLang.translate("pm.informations.more"));
		System.out.println("4- " + BaseLang.translate("pm.informations.disclaimer"));
		System.out.println("5- " + BaseLang.translate("pm.standard.back"));
		
		int info = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);
		
		if(info == 1){
			File license = new File("LICENSE.pdf");
			if(license.exists())
				Utility.openSoftware("software", license.toString());
			else
				Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.informations.bad"));
		}
		
		if(info == 2){
			Utility.cleanScreen();
			System.out.println(Utility.softwareName);
			System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.credits")));
			System.out.println("This program is free software made by matcracker: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or at your option) any later version.");
			System.out.println("\nThis program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.");
			System.out.println("\nYou should have received a copy of the GNU Lesser General Public License along with this program.  If not, see in this program in the section 'Informations ->  License'.");
			Utility.waitConfirm("\n" + BaseLang.translate("pm.standard.enter"));
		}
		
		if(info == 3){
			Utility.cleanScreen();
			System.out.println(Utility.softwareName);
			System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.moreinformations")));
			System.out.println("1- GitHub");
			System.out.println("2- Twitter");
			System.out.println("3- " + BaseLang.translate("pm.standard.back"));
			
			int more = Utility.readInt(BaseLang.translate("pm.choice.option") + " ", null);

			if(more == 1)
				Utility.openSoftware("url", gitlink);
			
			if(more == 2)
				Utility.openSoftware("url", twitterlink);
		}
		
		if(info == 4){
			Utility.cleanScreen();
			System.out.println(Utility.softwareName);
			System.out.println(Utility.setTitle("&c", BaseLang.translate("pm.title.disclaimer")));
			System.out.println("I do not assume responsibility for the use of this program if being deleted folders or files, for you, important.");
			System.out.println("The use is personal and therefore the connections you supply will have to be primarily related to the creation program server 'PocketMine-MP' or relative.");
			Utility.waitConfirm(BaseLang.translate("pm.standard.enter"));
		}
		
		if(info == 5)
			PMMS.mainMenu();
		
		informationsMenu();
	}
}
