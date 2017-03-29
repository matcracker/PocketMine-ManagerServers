/* _____           _        _   __  __ _                   __  __                                   _____                              
 *|  __ \         | |      | | |  \/  (_)                 |  \/  |                                 / ____|                             
 *| |__) |__   ___| | _____| |_| \  / |_ _ __   ___ ______| \  / | __ _ _ __   __ _  __ _  ___ _ _| (___   ___ _ ____   _____ _ __ ___ 
 *|  ___/ _ \ / __| |/ / _ \ __| |\/| | | '_ \ / _ \______| |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '__\___ \ / _ \ '__\ \ / / _ \ '__/ __|
 *| |  | (_) | (__|   <  __/ |_| |  | | | | | |  __/      | |  | | (_| | | | | (_| | (_| |  __/ |  ____) |  __/ |   \ V /  __/ |  \__ \
 *|_|   \___/ \___|_|\_\___|\__|_|  |_|_|_| |_|\___|      |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_| |_____/ \___|_|    \_/ \___|_|  |___/
 *                                                                                   __/ |                                             
 *                                                                                  |___/                                              
 *Copyright (C) 2015-2017 @author matcracker
 *
 *This program is free software: you can redistribute it and/or modify 
 *it under the terms of the GNU Lesser General Public License as published by 
 *the Free Software Foundation, either version 3 of the License, or 
 *(at your option) any later version.
*/
	
package com.matcracker.PMManagerServers.utility;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends Component{

	private static final long serialVersionUID = 1L;
	public static final String PHAR_DESCRIPTION = "PHP Extension And Application Repository Archive Format";
	
	private String title;
	
	/**
	 * @param title Title name of JFileChooser
	 */
	public FileChooser(String title) {
		if(!Desktop.isDesktopSupported()) return;
		this.setTitle(title);
	}
	
	/**
	 * @return the absolute path of choosed directory
	 */
	public String getDirectory(){
		JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setDialogTitle(title);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

       	if(JFileChooser.APPROVE_OPTION == fc.showOpenDialog(this))
       		if(fc.getSelectedFile().isDirectory())
       			return fc.getSelectedFile().getAbsolutePath();
       	
		return null;
	}

    /**
     * @param extensions
     * @param description
     * @return
     */
    public String getFile(String[] extensions, String description){
    	JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setDialogTitle(title);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(extensions != null && description != null)
        	fc.setFileFilter(new FileNameExtensionFilter(description, extensions));
       
       	if(JFileChooser.APPROVE_OPTION == fc.showOpenDialog(this))
       		return fc.getSelectedFile().getAbsolutePath();
       	
		return null;
    }
    
    /**
     * @param extension
     * @param description
     * @return
     */
    public String getFile(String extension, String description){
    	return getFile(new String[]{extension}, description);
    }
    
    /**
     * @return
     */
    public String getPharFile(){
    	return getFile(".phar", PHAR_DESCRIPTION);
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title != null)
			this.title = title;
		else
			this.title = "";
	}

}