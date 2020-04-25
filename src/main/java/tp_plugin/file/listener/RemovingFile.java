package tp_plugin.file.listener;

import tp_plugin.file.event.*;
import tp_plugin.window.Window;

public class RemovingFile implements FileListener {

	/**
	 * 
	 * add a plugin
	 * @param event the event to add
	 */
	public void fileAdded(FileEvent event) {}

	/**
	 * 
	 * remove the selected plugin
	 * @param event the event to remove
	 */
	public void fileRemoved(FileEvent event) {
		System.out.println(".class : " + event.getFileName() + " supprimé détécté");
		Window.WINDOW.removePlugin(event.getFileName());
	}

}
