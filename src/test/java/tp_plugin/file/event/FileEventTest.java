package tp_plugin.file.event;

import static org.junit.Assert.*;

import org.junit.*;

import tp_plugin.file.*;

public class FileEventTest {
	
	private FileEvent fileEvent;
	private FileChecker fileChecker;
	
	@Before
	public void initObjects() {
		this.fileChecker = new FileChecker(null, null);
		this.fileEvent = new FileEvent(this.fileChecker, "direction");
	}
	
	@Test
	public void getSourceTest() {
		assertEquals(this.fileChecker, this.fileEvent.getSource());
	}

	@Test
	public void getFileNameTest() {
		assertEquals("direction", this.fileEvent.getFileName());
	}

}
