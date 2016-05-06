package com.matcracker.PMManagerServers.Utility;

import java.io.OutputStream;
import java.io.PrintStream;

public class PMPrintStream extends PrintStream{
	
	private boolean debug = false;
	
	public PMPrintStream(final OutputStream outputStream) {
        super(outputStream);
    }
 
    @Override
    public void println(final String string) {
        super.println(UtilityColor.format(string, debug));
    }
    
    @Override
    public void print(final String string) {
        super.print(UtilityColor.format(string, debug));
    }
    
    public void printf(final String string) {
        super.printf(UtilityColor.format(string, debug));
    }
}
