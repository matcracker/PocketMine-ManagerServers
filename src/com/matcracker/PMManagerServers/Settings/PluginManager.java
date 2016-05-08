package com.matcracker.PMManagerServers.Settings;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Loaders.PluginsLoader;
import com.matcracker.PMManagerServers.Utility.Utility;

public class PluginManager {
	
	protected static void plugMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println("-------------------------<Plugin manager>-------------------------&f");
		System.out.println("1- Show list of enabled plugins");
		System.out.println("2- Enable plugin");
		System.out.println("3- Disable plugin");
		System.out.println("4- Use plugins");
		System.out.println("5- " + BaseLang.translate("pm.standard.back"));
		int opt = Utility.readInt("Select option: ", null);

		
		if(opt == 4){
			if(PluginsLoader.pluginFound){
				String name = null;
				File plugin;
				do{
					Utility.showPlugins();
					name = Utility.readString("Write plugin name to use: ", null);
					plugin = new File("plugins" + File.separator + name + ".jar");
					PluginsLoader.pluginExec(plugin, "execute");
					
				}while(!plugin.exists());
			}else
				System.out.println("&cAny plugin found!");
		}
			
		
		if(opt == 5)
			Settings.settingsMenu();
		
		plugMenu();
		
		
	}
}
