package com.matcracker.PMManagerServers.Languages;

import java.io.IOException;

import com.matcracker.PMManagerServers.Utility.Utility;

public class LangSelector {
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
		
	public static void langMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		if(!BaseLang.isLanguageSet())
			System.out.println("&c----------------------------<Selection Languages>-------------------------------&f");
		else
			System.out.println(BaseLang.translate("pm.title.language"));
		
		System.out.println("1) Afrikaans\t\t\t16) Italian");
		System.out.println("2) Arabic\t\t\t17) Japanese");
		System.out.println("3) Catalan\t\t\t18) Korean");
		System.out.println("4) Chinese Simplified\t\t19) Norwegian");
		System.out.println("5) Chinese Traditional\t\t20) Polish");
		System.out.println("6) Czech\t\t\t21) Portuguese");
		System.out.println("7) Danish\t\t\t22) Portuguese, Brazilian");
		System.out.println("8) Dutch\t\t\t23) Romanian");
		System.out.println("9) English\t\t\t24) Russian");
		System.out.println("10) Finnish\t\t\t25) Serbian(Cyrillic)");
		System.out.println("11) French\t\t\t26) Spanish");
		System.out.println("12) German\t\t\t27) Swedish");
		System.out.println("13) Greek\t\t\t28) Turkish");
		System.out.println("14) Hebrew\t\t\t29) Ukrainian");
		System.out.println("15) Hungarian\t\t\t30) Vietnamese");
		System.out.println();
		String lang = Utility.readString("Choose language: ", null);
		
		BaseLang.setLanguage(lang);
	}
}
