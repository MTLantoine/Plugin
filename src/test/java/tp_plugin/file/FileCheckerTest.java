package tp_plugin.file;

import static org.junit.Assert.*;

import java.io.FilenameFilter;

import org.junit.*;

import tp_plugin.file.listener.AddingFile;
import tp_plugin.filter.*;

public class FileCheckerTest {
	
	private FileChecker fileChecker;
	private PluginFilter pluginFilter;
	private MockFileListener mockFileListener;
	
	@Before
	public void initObjects() {
		this.pluginFilter = new PluginFilter();
		this.fileChecker = new FileChecker(this.pluginFilter, "repository");
		this.mockFileListener = new MockFileListener();
	}

	@Test
	public void getFilenameFilterTest() {
		assertEquals(this.pluginFilter, this.fileChecker.getFilenameFilter());
	}
	
	@Test
	public void getDirNameTest() {
		assertEquals("repository", this.fileChecker.getDirName());
	}
	
	@Test
	public void getFileListenersTest() {
		assertTrue(this.fileChecker.getFileListeners().isEmpty());
	}
	
	@Test
	public void addFileListenerTest() {
		assertTrue(this.fileChecker.getFileListeners().isEmpty());
    	this.fileChecker.addFileListener(this.mockFileListener);
    	assertEquals(1, this.fileChecker.getFileListeners().size());
    	this.fileChecker.addFileListener(this.mockFileListener);
    	assertEquals(1, this.fileChecker.getFileListeners().size());
	}
	
	@Test
	public void removeFileListenerTest() {
		assertTrue(this.fileChecker.getFileListeners().isEmpty());
    	this.fileChecker.addFileListener(this.mockFileListener);
    	assertEquals(1, this.fileChecker.getFileListeners().size());
    	this.fileChecker.removeFileListener(this.mockFileListener);
    	assertTrue(this.fileChecker.getFileListeners().isEmpty());
	}
	
	@Test
	public void fireFileAddedTest() {
    	this.fileChecker.addFileListener(this.mockFileListener);
		assertEquals(0, this.mockFileListener.getCmpt());
		this.fileChecker.fireFileAdded(null);
		assertEquals(1, this.mockFileListener.getCmpt());
	}
	
	@Test
	public void fireFileRemovedTest() {
    	this.fileChecker.addFileListener(this.mockFileListener);
    	assertEquals(0, this.mockFileListener.getCmpt());
		this.fileChecker.fireFileRemoved(null);
		assertEquals(-1, this.mockFileListener.getCmpt());
	}
	
	@Test
	public void updatingFileTest() {
		MockFileChecker mockFileChecker = new MockFileChecker(null, null);
		assertEquals(0, mockFileChecker.getCmpt());
		mockFileChecker.updatingFile();
		assertEquals(1, mockFileChecker.getCmpt());
	}
	
	public class MockFileChecker extends FileChecker {
		
		private int cmpt;
		
		public MockFileChecker(FilenameFilter filter, String dirName) {
			super(filter, dirName);
			this.cmpt = 0;
		}
		
		public void updatingFile() {
			this.cmpt++;
		}
		
		public int getCmpt() {
			return this.cmpt;
		}
		
	}

}
