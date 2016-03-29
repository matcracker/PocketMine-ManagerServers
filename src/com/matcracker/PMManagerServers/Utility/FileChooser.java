package com.matcracker.PMManagerServers.Utility;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

public class FileChooser{

	private static JFrame frame = new JFrame();
        
    public static String get(String title) {
    	JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setDialogTitle(title);
        
        fc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "PHP Extension And Application Repository Archive Format";
			}
			
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()){
					return true;
				}else{
					String path = f.getAbsolutePath().toLowerCase();
					if(path.endsWith(".phar"))
						return true;
				}
				return false;
			}
		});
        
        frame.setExtendedState(JFrame.ICONIFIED);
        frame.setExtendedState(JFrame.NORMAL);
        frame.setVisible(true);
        
        int value = fc.showOpenDialog(frame.getGlassPane());

        if(JFileChooser.APPROVE_OPTION == value){
        	String path = fc.getSelectedFile().getAbsolutePath();
        	
        	if(path.endsWith("PocketMine-MP.phar")){
        		path = path.replaceAll("PocketMine-MP.phar", "");
        		return path;
        	}
        }else
        	frame.setVisible(false);
        
        return null;
    }

}