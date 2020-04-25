package tp_plugin.file;

import tp_plugin.file.event.*;
import tp_plugin.file.listener.*;

public class MockFileListener implements FileListener {
	
	private int cmpt;
	
	public MockFileListener() {
		this.cmpt = 0;
	}
	
	public void fileAdded(FileEvent event) {
		this.cmpt++;
	}

	public void fileRemoved(FileEvent event) {
		this.cmpt--;
	}
	
	public int getCmpt() {
		return this.cmpt;
	}

}
