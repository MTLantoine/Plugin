package tp_plugin.file.listener;

import java.lang.reflect.*;

import tp_plugin.file.event.*;
import tp_plugin.plugin.*;
import tp_plugin.window.Window;

public class AddingFile implements FileListener {
	
	/**
	 * 
	 * add a plugin
	 * @param event the event to add
	 */
	public void fileAdded(FileEvent event) {
		System.out.println("nouveau .class : " + event.getFileName() + " détécté");
		Plugin plugin = null;
		Class<?> c = null;
		try {
			c = Class.forName("tp_plugin.plugin.plugins." + event.getFileName().substring(0, event.getFileName().length() - 6));
			Constructor<?> constructor = null;
			try {
				constructor = c.getConstructor();
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			try {
				plugin = (Plugin) constructor.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Window.WINDOW.addPlugin(event.getFileName(), plugin);
	}

	/**
	 * 
	 * remove the selected plugin
	 * @param event the event to remove
	 */
	public void fileRemoved(FileEvent event) {}

}