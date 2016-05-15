package com.matcracker.PMManagerServers.Loaders;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.matcracker.PMManagerServers.API.APIManager;
import com.matcracker.PMManagerServers.API.PluginInformation;
import com.matcracker.PMManagerServers.API.PluginStarter;

public class PluginsLoader{
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
		
	public final static File folder = new File("plugins");
	public static boolean pluginFound = false;
	public static boolean[] isLoaded;
	
	public static void loadPlugins(){		
		if(!folder.exists())
			folder.mkdir();

		for(File plugins : folder.listFiles()){
			if(plugins.isFile() && plugins.getName().endsWith(".jar")){
				System.out.println("&eLoading " + pluginExec(plugins, "getName"));
				pluginExec(plugins, "onEnable");
				
				if(!APIManager.getAPIVersion().equalsIgnoreCase((String) pluginExec(plugins, "getAPIVersion")))
					System.out.println("&3Be careful, this plugin API isn't updated!");
				
				
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e1){
					e1.printStackTrace();
				}
				
				pluginFound = true;
			}
		}

		if(!pluginFound){
			System.out.println("&6Any plugin found!");
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void unloadPlugins(){
		for(File plugins : folder.listFiles()){
			if(plugins.isFile() && plugins.getName().endsWith(".jar")){
				System.out.println("&eUnloading " + pluginExec(plugins, "getName"));
				pluginExec(plugins, "onDisable");
			
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	private static Class<?> getJarClass(File path){
		try{
			@SuppressWarnings("resource")
			JarFile jarFile = new JarFile(path.getAbsolutePath());
			Enumeration<JarEntry> e = jarFile.entries();
			
			URL[] urls = {
				new URL("jar:file:" + path.getAbsolutePath() +"!/") 
			};
			
			URLClassLoader cl = URLClassLoader.newInstance(urls);
			
			String className = "";
			
			while(e.hasMoreElements()){
			    JarEntry je = e.nextElement();
			    if(je.isDirectory() || !je.getName().endsWith(".class")){
			        continue;
			    }
			    className = je.getName().substring(0, je.getName().length() - 6);
			    className = className.replace('/', '.');
		
			}
			return cl.loadClass(className);
			
		}catch(ClassNotFoundException | IOException e){
			return null;
		}
	}
	
	public static Object pluginExec(File path, String methodName){
		Class<?> pluginClass = getJarClass(path);
		try{
			Method method = pluginClass.getDeclaredMethod(methodName);
			Object instance = pluginClass.newInstance();
			if(instance instanceof PluginStarter && instance instanceof PluginInformation)
				return method.invoke(instance);
			else{
				System.out.println("&cThis isn't a PocketMine-ManagerServers plugin!");
				System.out.println("&eUnloading " + path.getName());
			}
			
			Thread.sleep(1500);
		}catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InterruptedException e2){
			System.out.println("&cError on loading this plugin! Can't find method " + methodName);

		}
		return false;
	}
}
