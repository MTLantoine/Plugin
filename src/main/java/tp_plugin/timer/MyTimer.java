package tp_plugin.timer;

import java.awt.event.*;

import javax.swing.Timer;

public class MyTimer {
	
	private int delay;
	private Timer timer;
	
	public MyTimer() {
		this.delay = 1000;
		this.timer = new Timer(this.delay, new TimeListener());
	}
	
	public void start() {
		this.timer.start();
	}
	
	class TimeListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println(java.util.Calendar.getInstance().getTimeInMillis());
		}
		
	}

}
