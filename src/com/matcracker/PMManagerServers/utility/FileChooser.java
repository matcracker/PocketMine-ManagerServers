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
	
package com.matcracker.PMManagerServers.utility;

import java.awt.Desktop;
import java.awt.Frame;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

public class FileChooser{

	private static JFrame frame = new JFrame();
	
    /**
	 * @param title that go on top of the JFrame
	 * @param description file description
	 * @param extension file extension
	 * @return file selector of files
	 */
    public static String get(String title, final String description, final String extension){
    	if(!Desktop.isDesktopSupported()) return null;
    	
    	JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setDialogTitle(title);
        
        fc.setFileFilter(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()){
					return true;
				}else{
					String path = f.getAbsolutePath().toLowerCase();
					if(path.endsWith(extension))
						return true;
				}
				return false;
			}
			
			@Override
			public String getDescription() {
				return description;
			}
		});
        
        frame.setExtendedState(Frame.ICONIFIED);
        frame.setExtendedState(Frame.NORMAL);
        frame.setVisible(true);
        
        int value = fc.showOpenDialog(frame.getGlassPane());

        if(JFileChooser.APPROVE_OPTION == value){
        	String name = fc.getSelectedFile().getName();
        	String path = fc.getSelectedFile().getAbsolutePath();
        	
        	if(path.endsWith(name)){
        		path = path.replaceAll(name, "");
        		return path;
        	}
        }else
        	frame.setVisible(false);
        
        return null;
    }
    
    /**
     * @param title that go on top of the JFrame
     * @return file selector of phar files
     */
    public static String getPhar(String title) {
    	if(!Desktop.isDesktopSupported()) return null;
    	JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setDialogTitle(title);
        
        fc.setFileFilter(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()){
					return true;
				}else{
					String path = f.getAbsolutePath().toLowerCase();
					if(path.endsWith(".phar"))
						return true;
				}
				return false;
			}
			
			@Override
			public String getDescription() {
				return "PHP Extension And Application Repository Archive Format";
			}
		});
        
        frame.setExtendedState(Frame.ICONIFIED);
        frame.setExtendedState(Frame.NORMAL);
        frame.setVisible(true);
        
        int value = fc.showOpenDialog(frame.getGlassPane());

        if(JFileChooser.APPROVE_OPTION == value){
        	String path = fc.getSelectedFile().getAbsolutePath();
        	
        	if(path.endsWith("PocketMine-MP.phar")){
        		path = path.replaceAll("PocketMine-MP.phar", "");
        		return path;
        	}
        }else
        	frame.setVisible(false);
        return null;
    }

}