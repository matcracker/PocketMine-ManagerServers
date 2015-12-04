package com.matcracker.PMManagerServers.CollectionOfUtility;

import java.io.IOException;

public class CollectionsOfUtility{

	public final static void cleanScreen(){
		/*System.out.print("\033[H\033[2J");
		System.out.flush();*/
		
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}