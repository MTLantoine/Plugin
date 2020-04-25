package tp_plugin.extension;

import java.io.*;

public class ExtensionFilter implements FilenameFilter {

	public boolean accept(File dir, String name) {
		return name.endsWith(".txt");
	}

}
