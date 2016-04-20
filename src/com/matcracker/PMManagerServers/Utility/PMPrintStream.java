package com.matcracker.PMManagerServers.Utility;

import java.io.OutputStream;
import java.io.PrintStream;

public class PMPrintStream extends PrintStream{
	public PMPrintStream(final OutputStream outputStream) {
        super(outputStream);
    }
 
    @Override
    public void println(final String string) {
        super.println(UtilityColor.format(string, false));
    }
    
    @Override
    public void print(final String string) {
        super.print(UtilityColor.format(string, false));
    }
    
    public void printf(final String string) {
        super.printf(UtilityColor.format(string, false));
    }
}
