package tp_plugin.file.listener;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

import tp_plugin.file.*;
import tp_plugin.filter.*;

public class PluginActionListenerTest {
	
	private FileChecker fileChecker;
	private PluginFilter filenameFilter;
	private PluginActionListener pluginActionListener;
	
	@Before
	public void initObjects() {
		this.filenameFilter = new PluginFilter();
		this.fileChecker = new FileChecker(this.filenameFilter, "direction");
		this.pluginActionListener = new PluginActionListener(this.fileChecker);
	}
	
	@Test
	public void getFileCheckerTest() {
		assertEquals(this.fileChecker, this.pluginActionListener.getFileChecker());
	}
	
	@Test
	public void getFileTest() {
		File file = new File("direction");
		assertEquals(file, this.pluginActionListener.getFile());
	}
	
	@Test
	public void getOldFilesTest() {
		assertTrue(this.pluginActionListener.getOldFiles().isEmpty());
	}
	
	@Test
	public void handleAddFileTest() {
		List<File> listFile = new ArrayList<File>();
		listFile.add(new File("direction"));
		assertTrue(this.pluginActionListener.getOldFiles().isEmpty());
		this.pluginActionListener.handleAddFile(listFile);
		assertEquals(1, this.pluginActionListener.getOldFiles().size());
	}
	
	@Test
	public void handleRemoveFileTest() {
		List<File> listFile = new ArrayList<File>();
		List<File> cloneListFile = new ArrayList<File>(listFile);
		listFile.add(new File("direction"));
		assertTrue(this.pluginActionListener.getOldFiles().isEmpty());
		this.pluginActionListener.handleAddFile(listFile);
		assertEquals(1, this.pluginActionListener.getOldFiles().size());
		this.pluginActionListener.handleRemoveFile(cloneListFile);
		assertTrue(this.pluginActionListener.getOldFiles().isEmpty());
	}

}
