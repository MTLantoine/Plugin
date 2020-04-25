package tp_plugin;
import tp_plugin.file.*;
import tp_plugin.file.listener.*;
import tp_plugin.filter.*;
import tp_plugin.window.Window;

import java.applet.*;

public class Main extends Applet
{
    public static void main(String[] args)
    {
    	
    	Window.WINDOW.run();
    	
    	String fileName = "repository";
    	AddingFile af = new AddingFile();
    	RemovingFile rf = new RemovingFile();
    	FileChecker fc = new FileChecker(new PluginFilter(), fileName);
    	
    	fc.addFileListener(af);
    	fc.addFileListener(rf);
    	fc.updatingFile();

        
    }
    
}
