package tp_plugin.file.listener;

import java.util.*;

import tp_plugin.file.event.FileEvent;

public interface FileListener extends EventListener {
	
	/**
	 * 
	 * add a plugin
	 * @param event the event to add
	 */
	public void fileAdded(FileEvent event);

	/**
	 * 
	 * remove the selected plugin
	 * @param event the event to remove
	 */
	public void fileRemoved(FileEvent event);

}
