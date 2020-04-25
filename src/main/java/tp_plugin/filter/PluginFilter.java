package tp_plugin.filter;

import java.io.*;
import java.lang.reflect.*;

import tp_plugin.plugin.*;


public class PluginFilter implements FilenameFilter {
	
	/** the name of the package to check */
	protected final String NAME_OF_PACKAGE = "tp_plugin.plugin.plugins";

	/**
	 * 
	 * @param dir the file to check
	 * @param name the name of the checked file
	 */
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
	
	/**
	 * 
	 * @param name the name of the file to check
	 * @return <code>true</code> if the file is a class, <code>false</code> else
	 */
	public boolean isClass(String name) {
		return name.endsWith(".class");
	}
	
	/**
	 * 
	 * @param name the name of the class
	 * @return the transformed class name
	 */
	public String toClassName(String name) {
		return name.substring(0, name.length() - 6);
	}
	
	/**
	 * 
	 * @param className the new class name
	 * @return the class concerned by the class name
	 */
	public Class<?> getPluginClass(String className) {
		try { 	
			Class<?> c = Class.forName("tp_plugin.plugin.plugins." + className);
			return c;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param c the class to check
	 * @return <code>true</code> if the file is in the package, <code>false</code> else
	 */
	public boolean isInPackagePlugin(Class<?> c) {
		Package p = c.getPackage();
		if (p == null) {
			return false;
		}
		return c.getPackage().getName().equals(NAME_OF_PACKAGE);
	}

}
