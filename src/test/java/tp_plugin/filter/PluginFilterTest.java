package tp_plugin.filter;

import static org.junit.Assert.*;

import org.junit.*;

public class PluginFilterTest {
	
	private PluginFilter pluginFilter;
	private String pluginClass;
	
	@Before
	public void initObjects() {
		this.pluginFilter = new PluginFilter();
		this.pluginClass = "PluginNull.class";
	}

	@Test
	public void acceptTest() {
		assertTrue(this.pluginFilter.accept(null, "PluginNull.class"));
	}
	
	@Test
	public void isClassTest() {
		assertTrue(this.pluginFilter.isClass(this.pluginClass));
	}
	
	@Test
	public void toClassNameTest() {
		assertEquals("PluginNull", this.pluginFilter.toClassName(this.pluginClass));
	}

}
