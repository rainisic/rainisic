package ElevatorV100bate;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ListMa {

/*	static {
		try {
		//	UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		
	}*/
	
	
	public static void main(String[] args) {
		JFrame frame = new ListFrame();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setTitle("电梯控制模拟系统v0.9 Bate");
		frame.setLocation(50, 50);
		frame.setSize(1230, 660);
		frame.setResizable(true);
		frame.show();
	}
}
