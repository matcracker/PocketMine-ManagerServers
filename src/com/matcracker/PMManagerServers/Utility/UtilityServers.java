package com.matcracker.PMManagerServers.Utility;

import java.io.File;

import com.matcracker.PMManagerServers.API.UtilityServersAPI;

public class UtilityServers{
	static int nservers = UtilityServersAPI.getNumberServers();
	public static String[] path;
	public static File checknservers = new File("Data" + File.separator + "nservers.pm");
	public static Object[] checkPath = new Object[nservers];
	public static boolean[] checkNameServer = new boolean[nservers];
	public static String defaultServersName = "Server_Minecraft_PE";
	
	public static final String version = "0.1J";


}
