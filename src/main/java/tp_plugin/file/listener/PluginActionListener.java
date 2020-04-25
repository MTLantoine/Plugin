package tp_plugin.file.listener;

import java.awt.event.*;
import java.io.*;
import java.util.*;

import tp_plugin.file.FileChecker;

public class PluginActionListener implements ActionListener {
	
	/** the file checker */
	private FileChecker fileChecker;
	
	/** the list of currents files in the repository */
	private List<File> oldFiles;
	
	/**
	 * 
	 * @param fileChecker the file checker
	 */
	public PluginActionListener(FileChecker fileChecker) {
		this.fileChecker = fileChecker;
		this.oldFiles = new ArrayList<File>();
	}
	
	/**
	 * 
	 * @return the file checker
	 */
	public FileChecker getFileChecker() {
		return this.fileChecker;
	}
	
	/**
	 * 
	 * @return the file found in the dirname
	 */
	public File getFile() {
		return new File(this.getFileChecker().getDirName());
	}
	
	/**
	 * 
	 * @return the list of currents files in the repository 
	 */
	public List<File> getOldFiles() {
		return this.oldFiles;
	}

	/**
	 * 
	 * actualize the list of currents files in the list
	 */
	public void actionPerformed(ActionEvent e) {
		File[] temporaryFiles = this.getFile().listFiles();
		List<File> newFiles = new ArrayList<File>();
		for (File file : temporaryFiles) {
			if (this.getFileChecker().getFilenameFilter().accept(file, file.getName())) {				
				newFiles.add(file);
			}
		}
		
		this.handleAddFile(newFiles);
		this.handleRemoveFile(newFiles);
	}
	
	/**
	 * 
	 * add a new file to the list of currents files if this is necessary
	 * @param listFile the list of new files
	 */
	public synchronized void handleAddFile(List<File> listFile) {
		for (File file : listFile) {
			if (!(this.oldFiles.contains(file))) {
				this.oldFiles.add(file);
				this.getFileChecker().fireFileAdded(file.getName());
			}
		}
	}
	
	/**
	 * 
	 * remove a file from the list of currents files if this is necessary
	 * @param listFile the list of new files
	 */
	public synchronized void handleRemoveFile(List<File> listFile) {
		List<File> temporaryOldFiles = new ArrayList<File>(this.oldFiles);
		for (File file : temporaryOldFiles) {
			if (!(listFile.contains(file))) {
				this.oldFiles.remove(file);
				this.getFileChecker().fireFileRemoved(file.getName());
			}
		}
	}

}
