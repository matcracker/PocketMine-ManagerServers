package com.matcracker.PMManagerServers.Utility;

import java.io.File;

public class UtilityServers{
	public static int nservers = 0;
	public static String[] path = {"", "", "", "", "", "", "", "", "", ""};
	public static String[] nameServers = {"", "", "", "", "", "", "", "", "", ""};
	public static String[] numberServers = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth"};
	public static String[] numberServers2 = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth"};
	
	public static File checknservers = new File("Data" + File.separator + "nservers.pm");
	public static Object[] checkPath = new Object[] {false, false, false, false, false, false, false, false, false, false};
	public static boolean[] checkNameServer = new boolean[] {false, false, false, false, false, false, false, false, false, false};

	public static String defaultServersName = "Server_Minecraft_PE";
	
	public static final String version = "0.1J";


}
