package tp_plugin.action;

import java.awt.event.*;
import java.io.*;
import java.util.*;

import tp_plugin.file.FileChecker;

public class PluginActionListener implements ActionListener {
	
	private FileChecker fileChecker;
	private List<File> oldFiles;
	
	public PluginActionListener(FileChecker fileChecker) {
		this.fileChecker = fileChecker;
		this.oldFiles = new ArrayList<File>();
	}
	
	public FileChecker getFileChecker() {
		return this.fileChecker;
	}
	
	public File getFile(String dirName) {
		return new File(this.getFileChecker().getDirName());
	}

	public void actionPerformed(ActionEvent e) {
		File[] temporaryFiles = this.getFile(this.getFileChecker().getDirName()).listFiles();
		List<File> newFiles = new ArrayList<File>();
		for (File file : temporaryFiles) {
			if (this.getFileChecker().getFilenameFilter().accept(file, file.getName())) {				
				newFiles.add(file);
			}
		}
		
		this.handleAddFile(newFiles);
		this.handleRemoveFile(newFiles);
	}
	
	public synchronized void handleAddFile(List<File> listFile) {
		for (File file : listFile) {
			if (!(this.oldFiles.contains(file))) {
				this.oldFiles.add(file);
				this.getFileChecker().fireFileAdded(file.getName());
			}
		}
	}
	
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
