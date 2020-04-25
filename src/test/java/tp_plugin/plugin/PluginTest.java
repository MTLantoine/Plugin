package tp_plugin.plugin;

import static org.junit.Assert.*;

import org.junit.*;

public abstract class PluginTest {
	
	protected Plugin plugin;
	protected abstract Plugin createPlugin();
	
	@Before
	public void initObjects() {
		this.plugin = this.createPlugin();
	}

	@Test
	public void transformTest() {
		assertTrue(this.plugin.transform("text") instanceof String);
	}

	@Test
	public void getLabelTest() {
		assertTrue(this.plugin.getLabel() instanceof String);
	}

	@Test
	public void helpMessageTest() {
		assertTrue(this.plugin.helpMessage() instanceof String);
	}

}
