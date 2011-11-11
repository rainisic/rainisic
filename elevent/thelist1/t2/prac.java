package t2;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class prac extends Applet implements Runnable, ActionListener {
	Thread thread;
//当前状态
	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int OPEN = 2;
	private static final int CLOSE = 3;
	private static final int WAIT = 4;
//楼层确定
	private boolean F1 = false;
	private boolean F2 = false;
	private boolean F3 = false;
	private boolean F4 = false;
	private boolean F5 = false;
	private boolean target = false; //楼层锁定
	private int state = WAIT; //当前状态
	private int t, currentfloor;//绘画参数,当前楼层
	private int x, y, width, height, openx,block;//openx用来画门,block用来控制门的大小
//双缓冲
private boolean painted = false; 
	Image BufferImage;
	Graphics BufferGraphics;
	Button f1, f2, f3, f4, f5;
	Label lab;

	public void init() {
		setSize(300, 500);
		setLayout(null);
		lab = new Label("0");
		
		f1 = new Button("1");
		f2 = new Button("2");
		f3 = new Button("3");
		f4 = new Button("4");
		f5 = new Button("5");
		
		lab.setBounds(120, 160, 20, 20);
		f1.setBounds(200, 160, 30, 30);
		f2.setBounds(200, 200, 30, 30);
		f3.setBounds(200, 240, 30, 30);
		f4.setBounds(200, 280, 30, 30);
		f5.setBounds(200, 320, 30, 30);

		BufferImage = createImage(getSize().width,getSize().height);
		BufferGraphics = BufferImage.getGraphics();
		
		currentfloor = 0;
		openx = 0;
		t = 0;
		x = 20;
		y = 10;
		width = 80;
		height = 400;
		block=20;

		f1.addActionListener(this);
		f2.addActionListener(this);
		f3.addActionListener(this);
		f4.addActionListener(this);
		f5.addActionListener(this);

		add(lab);
		add(f1);
		add(f2);
		add(f3);
		add(f4);
		add(f5);
	}

	public void paint(Graphics g) {
		BufferGraphics.setColor(Color.white);
		BufferGraphics.fillRect(0, 0, getSize().width,getSize().height);
		BufferGraphics.setColor(Color.black);
		BufferGraphics.fillRect(x, y + 9, width + 1, height + 3);
		BufferGraphics.setColor(Color.white);
		BufferGraphics.drawRect(x + 1+block, y + height / 5 * 4 - t + 10+block, (width - 2) / 2 - openx-block,
				height / 5-block);
		BufferGraphics.fillRect(x + 1 + (width - 2) / 2 - openx,
				y + height / 5 * 4 - t + 10+block, openx * 2, height / 5 + 1-block);
		BufferGraphics.drawRect(x + 1 + (width - 2) / 2 + openx,
				y + height / 5 * 4 - t + 10+block, (width - 2) / 2 - openx-block,
				height / 5-block);
		

		if(state==UP)
			BufferGraphics.setColor(Color.red);
		else
			BufferGraphics.setColor(Color.black);
		BufferGraphics.drawString("△",120, 200);
		
		
		
		if(state==DOWN)
			BufferGraphics.setColor(Color.red);
		else
			BufferGraphics.setColor(Color.black);			
		BufferGraphics.drawString("▽",120, 220);
		lab.setText(String.valueOf(currentfloor));
		painted = true;
		g.drawImage(BufferImage,0,0,this);
	}
	public void update(Graphics g){
		paint(g);
	}
	public void control() {
		if (state == UP) {
			if (t == y + height / 5 * 4)
				t = y + height / 5 * 4;
			else {
				t = t + 1;
			}
		} else if (state == DOWN) {
			if (t == 0)
				t = 0;
			else {
				t = t - 1;
			}
		} else if (state == OPEN) {
			if ((width - 2) / 2 -block== openx) {
				openx = (width - 2) / 2-block;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {}
				state = CLOSE;
				
			} else {
				openx = openx + 1;
			}
		} else if (state == CLOSE) {
			if (openx == 0) {
				openx = 0;
				state = WAIT;
			} else {
				openx = openx - 1;
			}
		}
	}
//更新楼层状态
	public void updatefloor() {
		int floor = t / (height / 5) + 1;
		int num = t % (height / 5);

		if (num == 0)
			currentfloor = floor;
		else if (state == DOWN)
			currentfloor = floor + 1;

		if (num == 0) {
			switch (currentfloor) {
			case 1:
				if (F1) {
					state = OPEN;
					target = false;
					F1 = false;
				}
				break;
			case 2:
				if (F2) {
					state = OPEN;
					target = false;
					F2 = false;
				}
				break;
			case 3:
				if (F3) {
					state = OPEN;
					target = false;
					F3 = false;
				}
				break;
			case 4:
				if (F4) {
					state = OPEN;
					target = false;
					F4 = false;
				}
				break;
			case 5:
				if (F5) {
					state = OPEN;
					target = false;
					F5 = false;
				}
				break;
			default:
				state = WAIT;
				break;
			}
		}

	}
//判断上下楼
	public void upordown() {
		if (state != OPEN && state != CLOSE) {

			if (currentfloor < 5 && F5) {
				state = UP;
			} else if (currentfloor < 4 && F4) {
				state = UP;
			} else if (currentfloor < 3 && F3) {
				state = UP;
			} else if (currentfloor < 2 && F2) {
				state = UP;
			}

			if (currentfloor > 1 && F1) {
				state = DOWN;
			} else if (currentfloor > 2 && F2) {
				state = DOWN;
			} else if (currentfloor > 3 && F3) {
				state = DOWN;
			} else if (currentfloor > 4 && F4) {
				state = DOWN;
			}
		}

	}

	public void run() {
		while (true) {
			repaint();
			control();
			upordown();
			updatefloor();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			if(painted){
				painted = false;
			}
		}
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.interrupt();
			thread = null;
		}
	}

	public void actionPerformed(ActionEvent e) {
		Button b = (Button) e.getSource();
		if (b == f1) {
			if (!target) {
				F1 = true;
				F2 = false;
				F3 = false;
				F4 = false;
				F5 = false;
				target = true;
			}
		} else if (b == f2) {
			if (!target) {
				F1 = false;
				F2 = true;
				F3 = false;
				F4 = false;
				F5 = false;
				target = true;
			}
		} else if (b == f3) {
			if (!target) {
				F1 = false;
				F2 = false;
				F3 = true;
				F4 = false;
				F5 = false;
				target = true;
			}
		} else if (b == f4) {
			if (!target) {
				F1 = false;
				F2 = false;
				F3 = false;
				F4 = true;
				F5 = false;
				target = true;
			}
		} else if (b == f5) {
			if (!target) {
				F1 = false;
				F2 = false;
				F3 = false;
				F4 = false;
				F5 = true;
				target = true;
			}
		}
	}
}
