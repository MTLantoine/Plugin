package tp_plugin.plugin.plugins;

import tp_plugin.plugin.*;

public class ToLowerCasePlugin implements Plugin {

	/**
	 * 
	 * @param s the content of the text area
	 * @return the transformed text to replace the text area content
	 */
	public String transform(String s) {
		return s.toLowerCase();
	}

	/**
	 * 
	 * @return the label of the plugin
	 */
	public String getLabel() {
		return "To LOWER case";
	}

	/**
	 * 
	 * @return the help message
	 */
	public String helpMessage() {
		return "Transform each character in lower case";
	}

}
