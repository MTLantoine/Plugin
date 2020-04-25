package tp_plugin.extension;

import java.io.*;
import java.lang.reflect.*;

import tp_plugin.plugin.*;


public class PluginFilter implements FilenameFilter {
	
	protected final String NAME_OF_PACKAGE = "tp_plugin.plugin.plugins";

	public boolean accept(File dir, String name) {
		if (this.isClass(name)){
			String className = this.toClassName(name);
			Class<?> c = this.getPluginClass(className);
			if (c == null){
				return false;
			}
			if (!(Plugin.class.isAssignableFrom(c))) {
				return false;
			}
			try {
				Constructor<?> constructor = c.getConstructor();
				if (constructor.getParameterCount() != 0) {
					return false;
				}
			} catch (NoSuchMethodException | SecurityException e) {
				return false;
			}
			return this.isInPackagePlugin(c);
		}
		return false;
	}
	
	public boolean isClass(String name) {
		return name.endsWith(".class");
	}
	
	public String toClassName(String name) {
		return name.substring(0, name.length() - 6);
	}
	
	public Class<?> getPluginClass(String className) {
		try { 	
			Class<?> c = Class.forName(className);
			return c;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	public boolean isInPackagePlugin(Class<?> c) {
		Package p = c.getPackage();
		if (p == null) {
			return false;
		}
		return c.getPackage().getName().equals(NAME_OF_PACKAGE);
	}

}
