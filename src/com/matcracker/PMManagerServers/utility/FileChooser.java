package com.matcracker.PMManagerServers.utility;

import java.awt.Frame;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

public class FileChooser{

	private static JFrame frame = new JFrame();
	
    /**
	 * @param title that go on top of the JFrame
	 * @param description file description
	 * @param extension file extension
	 * @return file selector of files
	 */
    public static String get(String title, String description, String extension) {
    	JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setDialogTitle(title);
        
        fc.setFileFilter(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()){
					return true;
				}else{
					String path = f.getAbsolutePath().toLowerCase();
					if(path.endsWith(extension))
						return true;
				}
				return false;
			}
			
			@Override
			public String getDescription() {
				return description;
			}
		});
        
        frame.setExtendedState(Frame.ICONIFIED);
        frame.setExtendedState(Frame.NORMAL);
        frame.setVisible(true);
        
        int value = fc.showOpenDialog(frame.getGlassPane());

        if(JFileChooser.APPROVE_OPTION == value){
        	String name = fc.getSelectedFile().getName();
        	String path = fc.getSelectedFile().getAbsolutePath();
        	
        	if(path.endsWith(name)){
        		path = path.replaceAll(name, "");
        		return path;
        	}
        }else
        	frame.setVisible(false);
        
        return null;
    }
    
    /**
     * @param title that go on top of the JFrame
     * @return file selector of phar files
     */
    public static String getPhar(String title) {
    	JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setDialogTitle(title);
        
        fc.setFileFilter(new FileFilter() {
			
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
			
			@Override
			public String getDescription() {
				return "PHP Extension And Application Repository Archive Format";
			}
		});
        
        frame.setExtendedState(Frame.ICONIFIED);
        frame.setExtendedState(Frame.NORMAL);
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