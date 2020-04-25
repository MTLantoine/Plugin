package tp_plugin.plugin.plugins;

import tp_plugin.plugin.*;

public class CesarCodePlugin implements Plugin {

	/**
	 * 
	 * @param s the content of the text area
	 * @return the transformed text to replace the text area content
	 */
	public String transform(String s) {
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
		    	if (Character.isLetter(c)) {
		    		if (Character.isLowerCase(c)) {
		    			c = (char) (97 + (c - 'a' + 1) % 26);
		    		} else {
		    			c = (char) (65 + (c - 'A' + 1) % 26);
		    		}
		    	}
		    res += c;
		  }
		  return res;
	}

	/**
	 * 
	 * @return the label of the plugin
	 */
	public String getLabel() {
		return "Cesar code";
	}

	/**
	 * 
	 * @return the help message
	 */
	public String helpMessage() {
		return "Apply a Cesar code to the text";
	}

}
