package tp_plugin.plugin.plugins;

import tp_plugin.plugin.*;

public class SignaturePlugin implements Plugin {

	/**
	 * 
	 * @param s the content of the text area
	 * @return the transformed text to replace the text area content
	 */
	public String transform(String s) {
		return s + "\n\nAntoine";
	}

	/**
	 * 
	 * @return the label of the plugin
	 */
	public String getLabel() {
		return "Signature";
	}

	/**
	 * 
	 * @return the help message
	 */
	public String helpMessage() {
		return "Write the name of the developper of this project";
	}

}
