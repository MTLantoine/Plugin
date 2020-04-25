package tp_plugin.window;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;

import javax.swing.*;

import tp_plugin.plugin.*;

public class Window {
	
	/** the one and only window from the project */
	public static final Window WINDOW = new Window();
	
	/** the frame */
	private JFrame frame;
	
	/** the text area */
	private JTextArea textArea;
	
	/** the panel with the text area inside */
	private JScrollPane panel;
	
	/** the menu bar */
	private JMenuBar menuBar;
	
	/** the file menu */
	private JMenu file;
	
	/** the tool menu */
	private JMenu tools;

	/** the help menu */
	private JMenu help;
	
	/** the map of the the added plugins */
	private Map<String, Map<JMenuItem, JMenuItem>> mapPlugins;
	
	/**
	 * 
	 * create a window with a text area
	 */
	public Window() {
		this.frame = new JFrame("Plugin");
		this.textArea = new JTextArea(50, 100);
		this.panel = new JScrollPane(this.textArea);
		this.menuBar = new JMenuBar();
		this.file = new JMenu();
		this.tools = new JMenu();
		this.help = new JMenu();
		this.mapPlugins = new HashMap<String, Map<JMenuItem, JMenuItem>>();
	}
	
	/**
	 * 
	 * create the interface with the basics elements
	 */
	public void run() {
		this.initFrame();
		this.initMenu();
	}
	
	/**
	 * 
	 * @param fileName the name of the added plugin
	 * @param plugin the added plugin
	 */
	public void addPlugin(String fileName, Plugin plugin) {
		Map<JMenuItem, JMenuItem> mapItems = new HashMap<JMenuItem, JMenuItem>();
		mapItems.put(this.createToolItem(plugin), this.createHelpItem(plugin));
		this.mapPlugins.put(fileName, mapItems);
	}
	
	/**
	 * 
	 * create a menu item for the tools menu and has appropriates actions
	 * @param plugin the referent plugin
	 * @return a menu item to add to the tools menu
	 */
	public JMenuItem createToolItem(Plugin plugin) {
		JMenuItem item = new JMenuItem(plugin.getLabel());
		item.addActionListener(new TransformTextListener(plugin));
		this.tools.add(item);
		return item;
	}
	
	/**
	 * 
	 * create a menu item for the help menu and has appropriates actions
	 * @param plugin the referent plugin
	 * @return a menu item to add to the help menu
	 */
	public JMenuItem createHelpItem(Plugin plugin) {
		JMenuItem item = new JMenuItem(plugin.getLabel());
		item.addActionListener(new HelpListener(plugin));
		this.help.add(item);
		return item;
	}
	
	/**
	 * 
	 * remove the menu item in function of the removed plugin
	 * @param fileName the name of the plugin class
	 */
	public void removePlugin(String fileName) {
		Map<JMenuItem, JMenuItem> mapItems = this.mapPlugins.get(fileName);
		JMenuItem toolItem;
		JMenuItem helpItem;
		for (Map.Entry<JMenuItem, JMenuItem> entry : mapItems.entrySet()) {
			toolItem = entry.getKey();
			helpItem = entry.getValue();
			
			this.tools.remove(toolItem);
			this.help.remove(helpItem);
		}
	}
	
	/**
	 * 
	 * @return the text area
	 */
	public String getTextArea() {
		return this.textArea.getText();
	}
	
	/**
	 * 
	 * create the initial window
	 */
	public void initFrame() {
		this.frame.addWindowListener(new CloseWindowEvent());
		this.frame.setLocationRelativeTo(null);
		this.frame.setLocation(300, 200);
		this.frame.setVisible(true);
		this.frame.add(this.panel, BorderLayout.CENTER);
		this.frame.setJMenuBar(this.menuBar);
		this.frame.pack();
		this.textArea.setFont(new Font("Arial Black", Font.BOLD, 15));
	}
	
	/**
	 * 
	 * implements the basics tools for the window
	 */
	public void initMenu() {

		this.file.setText("File");
		this.tools.setText("Tools");
		this.help.setText("Help");
		
		this.menuBar.add(file);
		this.menuBar.add(tools);
		this.menuBar.add(help);

		JMenuItem newFile = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open...");
		JMenuItem exit = new JMenuItem("Exit");

		newFile.addActionListener(new NewFileListener());
		open.addActionListener(new OpenFileListener());
		exit.addActionListener(new CloseWindowEvent());
		
		this.file.add(newFile);
		this.file.add(open);
		this.file.add(exit);
	}
	
	/**
	 * 
	 * @param line the line added to the text area
	 */
	public void setTextAreaContent(String line) {
		this.textArea.setText(this.textArea.getText() + line);
	}
	
	/**
	 * 
	 * @param line the line which replace the ancient one
	 */
	public void newTextAreaContent(String line) {
		this.textArea.setText(line);
	}
	
	private class TransformTextListener implements ActionListener {

		/** the added plugin */
		private Plugin plugin;
		
		/**
		 * 
		 * @param plugin the added plugin
		 */
		public TransformTextListener(Plugin plugin) {
			this.plugin = plugin;
		}
		
		/**
		 * 
		 * @return the added plugin
		 */
		public Plugin getPlugin() {
			return this.plugin;
		}
		
		/**
		 * 
		 * replace the ancien text area content by a new one
		 */
		public void actionPerformed(ActionEvent e) {
			WINDOW.newTextAreaContent(this.getPlugin().transform(WINDOW.getTextArea()));
		}
		
	}
	
	private class HelpListener implements ActionListener {
		
		/** the added plugin */
		private Plugin plugin;
		
		/**
		 * 
		 * @param plugin the added plugin
		 */
		public HelpListener(Plugin plugin) {
			this.plugin = plugin;
		}
		
		/**
		 * 
		 * @return the added plugin
		 */
		public Plugin getPlugin() {
			return this.plugin;
		}

		/**
		 * 
		 * open a message to inform the user
		 */
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, this.getPlugin().helpMessage());
		}
		
	}
	
	private class CloseWindowEvent extends WindowAdapter implements ActionListener {
		
		/**
		 * 
		 * finish the executed program 
		 */
		public void windowClosing(java.awt.event.WindowEvent e) {
			System.exit(0);
		}
		
		/**
		 * 
		 * finish the executed program 
		 */
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}
	
	private class NewFileListener implements ActionListener {
		
		/**
		 * 
		 * rest the text area content
		 */
		public void actionPerformed(ActionEvent e) {
			Window.this.newTextAreaContent(null);
		}
	}
	
	private class OpenFileListener implements ActionListener {

		/**
		 * 
		 * select a file that the user chosen
		 */
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser(".");
			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				this.getFileContent(selectedFile);
			}
		}
		
		/**
		 * 
		 * edit the text area content in function of the selected file
		 * @param file the selected file
		 */
		public void getFileContent(File file) {
			String path = file.getAbsolutePath();
			BufferedReader buffer = null;
			try {
				buffer = new BufferedReader(new FileReader(path));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String line;
			try {
				while ((line = buffer.readLine()) != null) {
					Window.this.setTextAreaContent(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
