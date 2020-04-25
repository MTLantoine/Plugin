package tp_plugin.file;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.Timer;

import tp_plugin.file.event.*;
import tp_plugin.file.listener.*;

public class FileChecker {
	
	/** the filter to accept the wanted file */
	private FilenameFilter filter;
	
	/** the direction name to inspect */
	private String dirName;
	
	/** the list of file listeners */
	private ArrayList<FileListener> fileListeners;
	
	/** the counter to check the dirname */
	private int delay = 1000;
	
	/**
	 * 
	 * @param filter the filter to accept the wanted file
	 * @param dirName the direction name to inspect
	 */
	public FileChecker(FilenameFilter filter, String dirName) {
		this.filter = filter;
		this.dirName = dirName;
		this.fileListeners = new ArrayList<FileListener>();
	}
	
	/**
	 * 
	 * @return the filter
	 */
	public FilenameFilter getFilenameFilter() {
		return this.filter;
	}
	
	/**
	 * 
	 * @return the direction name to inspect
	 */
	public String getDirName() {
		return this.dirName;
	}
	
	/**
	 * 
	 * @return the list of file listeners
	 */
	public ArrayList<FileListener> getFileListeners() {
		return this.fileListeners;
	}
	
	/**
	 * 
	 * add a new listener to the list
	 * @param fileListener the list of file listeners
	 */
	public synchronized void addFileListener(FileListener fileListener) {
		if (!this.fileListeners.contains(fileListener)) {			
			this.fileListeners.add(fileListener);
		}
	}
	
	/**
	 * 
	 * remove a listener from the list
	 * @param fileListener the list of file listeners
	 */
	public synchronized void removeFileListener(FileListener fileListener) {
		this.fileListeners.remove(fileListener);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param dirName the direction name to inspect
	 */
	public void fireFileAdded(String dirName) {
		ArrayList<FileListener> cloneFileListeners = (ArrayList<FileListener>) this.fileListeners.clone();
		FileEvent event = new FileEvent(this, dirName);
		for (FileListener listener : cloneFileListeners) {	
			listener.fileAdded(event);
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param dirName the direction name to inspect
	 */
	public void fireFileRemoved(String dirName) {
		ArrayList<FileListener> cloneFileListeners = (ArrayList<FileListener>) this.fileListeners.clone();
		FileEvent event = new FileEvent(this, dirName);
		for (FileListener listener : cloneFileListeners) {
			listener.fileRemoved(event);
		}
	}
	
	/**
	 * 
	 * launch the "every X seconds, check the file"
	 */
	public void updatingFile() {
		ActionListener fileDetector = new PluginActionListener(this);
		Timer timer = new Timer(this.delay, fileDetector);
		while(true) {
			timer.start();
		}
	}

}
