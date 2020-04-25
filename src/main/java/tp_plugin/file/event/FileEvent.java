package tp_plugin.file.event;

import java.util.*;

public class FileEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
	/** the name of the file */
	private String fileName;

	/**
	 * 
	 * @param source the source that implemented the event
	 * @param fileName the name of the file
	 */
	public FileEvent(Object source, String fileName) {
		super(source);
		this.fileName = fileName;
	}
	
	/**
	 * 
	 * @return the name of the file
	 */
	public String getFileName() {
		return this.fileName;
	}
	
}
