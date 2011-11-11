package ElevatorV070.copy;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class ListMain {

	public static void main(String[] args) {
		JFrame frame = new ListFrame();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setTitle("电梯控制模拟系统");
		frame.setLocation(50, 50);
		frame.setSize(1230, 660);
		frame.setResizable(true);
		frame.show();
	}
}
