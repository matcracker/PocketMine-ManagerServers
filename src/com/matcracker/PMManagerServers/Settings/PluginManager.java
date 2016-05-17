package com.matcracker.PMManagerServers.Settings;

import java.io.File;
import java.io.IOException;
import com.matcracker.PMManagerServers.Languages.BaseLang;
import com.matcracker.PMManagerServers.Loaders.PluginsLoader;
import com.matcracker.PMManagerServers.Utility.Utility;

public class PluginManager {
	
	public static void plugMenu() throws IOException{
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println(BaseLang.translate("pm.title.pluginManager"));
		System.out.println("1- " + BaseLang.translate("pm.plugManager.list"));
		System.out.println("2- " + BaseLang.translate("pm.plugManager.use"));
		System.out.println("3- " + BaseLang.translate("pm.standard.back"));
		int opt = Utility.readInt(BaseLang.translate("pm.chooise.option") + " ", null);
		
		if(opt == 1){
			Utility.showPlugins();
			Utility.waitConfirm(BaseLang.translate("pm.standard.enter"));
		}
		
		if(opt == 2){
			if(PluginsLoader.pluginFound){
				String name = null;
				File plugin;
				do{
					Utility.showPlugins();
					name = Utility.readString(BaseLang.translate("pm.plugManager.name") + ": ", null);
					plugin = new File("plugins" + File.separator + name + ".jar");
					if(!Utility.is_numeric(name) && plugin.exists())
						PluginsLoader.pluginExec(plugin, "execute");
					
				}while(!plugin.exists() || Utility.is_numeric(name));
			}else
				System.out.println(BaseLang.translate("pm.plugManager.noPlugins"));
		}
			
		
		if(opt == 3)
			Settings.settingsMenu();
		
		plugMenu();
		
		
	}
}
