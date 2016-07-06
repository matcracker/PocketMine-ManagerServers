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

import java.io.File;
import java.io.IOException;

import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.rauschig.jarchivelib.CompressionType;

public class Zipper{	
	/**
	 * @param targetFile
	 * @param destinationFilePath
	 * @param format
	 * @param type
	 */
	public static void unzip(String targetFile, String destinationFilePath, ArchiveFormat format, CompressionType type){
		Archiver zip;
		if(type == null)
			zip = ArchiverFactory.createArchiver(format);
		else
			zip = ArchiverFactory.createArchiver(format, type);
		
		File target = new File(targetFile);
		File destination = new File(destinationFilePath);
		try{
			zip.extract(target, destination);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * @param name
	 * @param targetFolder
	 * @param destinationFilePath
	 * @param format
	 * @param type
	 */
	public static void zip(String name, String targetFolder, String destinationFilePath, ArchiveFormat format, CompressionType type){
		Archiver zip;
		if(type == null)
			zip = ArchiverFactory.createArchiver(format);
		else
			zip = ArchiverFactory.createArchiver(format, type);
		
		File target = new File(targetFolder);
		File destination = new File(destinationFilePath);
		
		if(name == null)
			name = target.getName().replaceAll(".zip", "");

		try{
			zip.create(name, destination, target);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
