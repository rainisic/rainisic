package project;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Gamestar extends JFrame implements publicframe, ActionListener {
	Button f1, f2, f3, f4, f5, f6;
	int purfloor = 1, floor, curfloor = 1;
	int openandclose = 0, opentime = 0, closeime = 0, floormove = 40;
	int direct = 0, doorabout = 0;
	epanel p = new epanel();

	Gamestar() {
		f1 = new Button("1");
		f2 = new Button("2");
		f3 = new Button("3");
		f4 = new Button("4");
		f5 = new Button("5");
		f6 = new Button("6");

		f1.setBounds(50, 160, 30, 30);
		f2.setBounds(50, 200, 30, 30);
		f3.setBounds(50, 240, 30, 30);
		f4.setBounds(50, 280, 30, 30);
		f5.setBounds(50, 320, 30, 30);
		f6.setBounds(50, 360, 30, 30);

		frame.add(f1);
		frame.add(f2);
		frame.add(f3);
		frame.add(f4);
		frame.add(f5);
		frame.add(f6);
		frame.add(p);

		f1.addActionListener(this);
		f2.addActionListener(this);
		f3.addActionListener(this);
		f4.addActionListener(this);
		f5.addActionListener(this);
		f6.addActionListener(this);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Gamestar g = new Gamestar();
		frame.setTitle("电梯控制系统");
		frame.setSize(640, 480);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} // 控制整个主类开始

	@Override
	public void actionPerformed(ActionEvent e) {
		Button b = (Button) e.getSource();
		if (b == f1) {
			purfloor = 1;
		} else if (b == f2) {
			purfloor = 2;
		} else if (b == f3) {
			purfloor = 3;
		} else if (b == f4) {
			purfloor = 4;
		} else if (b == f5) {
			purfloor = 5;
		} else if (b == f6) {
			purfloor = 6;
		}
		// TODO Auto-generated method stub
	}

	public void control() {
		if (getdirect() == 1) {
			floormove += 1;
			if (floormove % 40 == 0) {
				curfloor++;
				if (getdirect() == 0)
					openandclose = 1;
			}

		} else if (getdirect() == (-1)) {
			floormove -= 1;
			if (floormove % 40 == 0) {
				curfloor--;
				if (getdirect() == 0)
					openandclose = 1;
			}
		} else if (getdirect() == 0) {
		}
	}

	public int getCurPos() {
		return purfloor;
	}

	public int getdirect() {
		if (purfloor > curfloor) {
			direct = 1;
			return 1;
		} else if (purfloor < curfloor) {
			return direct = -1;
		} else if (purfloor == curfloor) {
			return direct = 0;
		}

		return direct;
	}

	public void openandclosemove() {
		doorabout = 1;
		if (opentime < 20 && closeime == 0)
			opentime += 1;
		else if (opentime == 20 || (closeime == 1)) {
			opentime -= 1;
			closeime = 1;
			if (opentime == 0) {
				closeime = 0;
				doorabout = 0;
				openandclose = 0;
			}
		}

	}

	@SuppressWarnings("serial")
	class epanel extends JPanel implements Runnable, publicframe {

		Thread elevent; // 设置线程

		int x, y = 3, cout, c; // 变量

		int width = 0, height = 0, openx = 0, block, t = 0;

		epanel() { // 结构体
			elevent = new Thread(this);
			elevent.start();
			this.setFocusable(true);
		}

		public void paint(Graphics g) {
			Graphics2D g1 = (Graphics2D) g;
			g1.setColor(Color.white);
			g1.fillRect(0, 0, getSize().width, getSize().height);
			g1.setColor(Color.black);
			g1.drawRect(30, 80, 180, 320);
			g1.fillRect(150, 120 + curfloor + floormove, 20, 30);
			g1.setColor(Color.white);
			g1.fillRect(160 - opentime, 120 + curfloor + floormove,
					opentime * 2, 30);

			if (direct == -1)
				g1.setColor(Color.red);
			else
				g1.setColor(Color.black);
			g1.drawString("△", 120, 200);

			if (direct == 1)
				g1.setColor(Color.red);
			else
				g1.setColor(Color.black);
			g1.drawString("▽", 120, 220);
		} // 画板啊

		public void run() {
			while (true) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();

				System.out.println(getdirect());

				if ((getdirect() == 0 && openandclose == 1)
						|| openandclose == 1) {
					openandclosemove();
				} else if (doorabout == 0) {
					control();
				}
				System.out.println(opentime);
				System.out.println(curfloor);
			}

		}

	}

} // 建立窗口

