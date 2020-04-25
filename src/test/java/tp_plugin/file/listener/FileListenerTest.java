package tp_plugin.file.listener;

import static org.junit.Assert.*;

import org.junit.*;

import tp_plugin.file.event.FileEvent;

public class FileListenerTest {
	
	private MockFileListener mockFileListener;
	private FileEvent fileEvent;
	
	@Before
	public void initObjects() {
		this.mockFileListener = new MockFileListener();
		this.fileEvent = new FileEvent(this.mockFileListener, "direction");
	}

	@Test
	public void fileAddedTest() {
		assertEquals(0, this.mockFileListener.getCmpt());
		this.mockFileListener.fileAdded(this.fileEvent);
		assertEquals(1, this.mockFileListener.getCmpt());
	}
	
	@Test
	public void fileRemovedTest() {
		assertEquals(0, this.mockFileListener.getCmpt());
		this.mockFileListener.fileRemoved(this.fileEvent);
		assertEquals(-1, this.mockFileListener.getCmpt());
	}
	
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

}
