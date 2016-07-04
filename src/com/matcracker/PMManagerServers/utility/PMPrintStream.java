package com.matcracker.PMManagerServers.utility;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class PMPrintStream extends PrintStream{
	
	private boolean debug = false;
	
	public PMPrintStream() throws UnsupportedEncodingException{
        super(System.out, true, "UTF-8");
    }
 
    @Override
    public void print(final String string) {
        super.print(UtilityColor.format(string, debug));
    }
    
    public void printf(final String string) {
        super.printf(UtilityColor.format(string, debug));
    }
    
    @Override
    public void println(final String string){
        super.println(UtilityColor.format(string, debug));
    }
    
}
