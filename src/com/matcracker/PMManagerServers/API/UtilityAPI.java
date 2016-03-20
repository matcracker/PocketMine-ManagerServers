package com.matcracker.PMManagerServers.API;

import java.io.File;
import com.matcracker.PMManagerServers.Utility.Utility;

public class UtilityAPI{
		
		public static int getNumberServers(String folder){
			if(folder.contains("/"))
				folder = folder.replace("/", File.separator);
			
			return Utility.readIntData(new File(folder));
		}
		
		public static void setNameServer(File file, String content){
			 Utility.writeStringData(file, content);
		}
		
		public static void setNumberServer(File file, int content){
			Utility.writeIntData(file, content);
		}
		
		public static String getNameServer(String folder){
			if(folder.contains("/"))
				folder = folder.replace("/", File.separator);
			
			return Utility.readStringData(new File(folder));
		}
		
		
	
	
	
}
