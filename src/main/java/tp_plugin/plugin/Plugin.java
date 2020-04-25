package tp_plugin.plugin;

public interface Plugin {

	/**
	 * 
	 * @param s the content of the text area
	 * @return the transformed text to replace the text area content
	 */
	public String transform(String s);

	/**
	 * 
	 * @return the label of the plugin
	 */
	public String getLabel();

	/**
	 * 
	 * @return the help message
	 */
	public String helpMessage();

}
