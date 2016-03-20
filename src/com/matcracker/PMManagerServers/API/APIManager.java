package com.matcracker.PMManagerServers.API;

import com.matcracker.PMManagerServers.Utility.Utility;

public class APIManager{
	private final String APIVersion = "0.1";
	
	public String getAPIVersion(){
		return APIVersion;
	}
	
	public String getVersion(){
		return Utility.version;
	}
	
	
}
