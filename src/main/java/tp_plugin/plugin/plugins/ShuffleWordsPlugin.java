package tp_plugin.plugin.plugins;

import java.util.*;

import tp_plugin.plugin.*;

public class ShuffleWordsPlugin implements Plugin{

	/**
	 * 
	 * @param s the content of the text area
	 * @return the transformed text to replace the text area content
	 */
	public String transform(String s) {
		String res = "";
		String word = "";
		for (int i = 0; i < s.length(); i++) {
			if (("" + s.charAt(i)).equals(" ") || ("" + s.charAt(i)).equals("\n")) {
				res += this.shuffleWord(word);
				word = "";
				res += s.charAt(i);
			} else {
				word += s.charAt(i);
				if (i == s.length() - 1) {
					res += this.shuffleWord(word);
				}
			}
		}
		return res;
	}
	
	/**
	 * 
	 * @param word the word to shuffle
	 * @return the shuffled word
	 */
	public String shuffleWord(String word) {
		String res = "";
		int len = word.length();
		List<String> chars = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			chars.add("" + word.charAt(i));
		}
		Collections.shuffle(chars);
		for (String s : chars) {
			res += s;
		}
		return res;
	}

	/**
	 * 
	 * @return the label of the plugin
	 */
	public String getLabel() {
		return "Shuffle words";
	}

	/**
	 * 
	 * @return the help message
	 */
	public String helpMessage() {
		return "Shuffle every letters in each word";
	}

}
