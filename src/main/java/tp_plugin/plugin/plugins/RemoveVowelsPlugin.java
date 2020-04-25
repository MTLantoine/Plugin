package tp_plugin.plugin.plugins;

import java.util.*;

import tp_plugin.plugin.*;

public class RemoveVowelsPlugin implements Plugin {
	
	/** the list of vowels */
	private List<String> listVowels;
	
	/**
	 * 
	 * construct the plugin
	 */
	public RemoveVowelsPlugin() {
		this.listVowels = new ArrayList<String>();
		this.addLetters("aeiouy");
	}
	
	/**
	 * 
	 * @return the list of vowels
	 */
	public List<String> getListVowels() {
		return this.listVowels;
	}
	
	/**
	 * 
	 * @param letters the added letters to the list of vowels
	 */
	public void addLetters(String letters) {
		for (int i = 0; i < letters.length(); i++) {
			this.listVowels.add("" + letters.charAt(i));
		}
	}
	
	/**
	 * 
	 * @param s the content of the text area
	 * @return the transformed text to replace the text area content
	 */
	public String transform(String s) {
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			if (!this.listVowels.contains(("" + s.charAt(i)).toLowerCase())) {
				res += s.charAt(i);
			}
		}
		return res;
	}

	/**
	 * 
	 * @return the label of the plugin
	 */
	public String getLabel() {
		return "Remove Vowels";
	}

	/**
	 * 
	 * @return the help message
	 */
	public String helpMessage() {
		return "Removes all the vowels from the text";
	}

}
