package ElevatorV030;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

@SuppressWarnings("serial")
public class Elevator extends JFrame implements publicframe, ActionListener {
	Button f1, f2, f3, f4, f5, f6;
	int purfloor = 1, floor, curfloor = 1;
	int openandclose = 0, opentime = 0, closeime = 0, floormove = 40;
	int direct = 0, doorabout = 0;
	
	
	Button bf1, bf2, bf3, bf4, bf5, bf6;
	int bpurfloor = 1, bfloor, bcurfloor = 1;
	int bopenandclose = 0, bopentime = 0, bcloseime = 0, bfloormove = 40;
	int bdirect = 0, bdoorabout = 0;
	
	Button[] upButton; //向上键
	Button[] downButton; //向下键
	int[] upButtonState , downButtonState;
	
	int upmax = 1 ,downmax = 1;
	
	epanel p = new epanel();
	
	

	Elevator() {
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


		f1.addActionListener(this);
		f2.addActionListener(this);
		f3.addActionListener(this);
		f4.addActionListener(this);
		f5.addActionListener(this);
		f6.addActionListener(this);
		
		
		//第二个电梯。。。。。。。。。。
		bf1 = new Button("1");
		bf2 = new Button("2");
		bf3 = new Button("3");
		bf4 = new Button("4");
		bf5 = new Button("5");
		bf6 = new Button("6");

		bf1.setBounds(250, 160, 30, 30);
		bf2.setBounds(250, 200, 30, 30);
		bf3.setBounds(250, 240, 30, 30);
		bf4.setBounds(250, 280, 30, 30);
		bf5.setBounds(250, 320, 30, 30);
		bf6.setBounds(250, 360, 30, 30);

		frame.add(bf1);
		frame.add(bf2);
		frame.add(bf3);
		frame.add(bf4);
		frame.add(bf5);
		frame.add(bf6);


		bf1.addActionListener(this);
		bf2.addActionListener(this);
		bf3.addActionListener(this);
		bf4.addActionListener(this);
		bf5.addActionListener(this);
		bf6.addActionListener(this);
		
		
		upButton = new Button[6];
		downButton = new Button[6];
		for (int i = 0; i < 6; i++)
		{
			upButton[i] = new Button("△");
			upButton[i].addActionListener(this);
			upButton[i].setBounds(420, 160 + i*40, 30, 30);
			downButton[i] = new Button("▽");;
			downButton[i].addActionListener(this);
			downButton[i].setBounds(470, 160 + i*40, 30, 30);
			//downButton[i].setBackground(Color.red);
			frame.add(upButton[i]);
			frame.add(downButton[i]);
			
		}
		upButtonState = new int[6];
		downButtonState = new int[6];
		frame.add(p);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Elevator g = new Elevator();
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
		if (b == bf1) {
			bpurfloor = 1;
		} else if (b == bf2) {
			bpurfloor = 2;
		} else if (b == bf3) {
			bpurfloor = 3;
		} else if (b == bf4) {
			bpurfloor = 4;
		} else if (b == bf5) {
			bpurfloor = 5;
		} else if (b == bf6) {
			bpurfloor = 6;
		}
		
		for (int i = 0; i < 6; i++)
		{
			if(b==upButton[i]){
				upButton[i].setBackground(Color.red);
				upButtonState[i]=1;
			}
			if(b==downButton[i]){
			downButton[i].setBackground(Color.red);	
			downButtonState[i]=1;
			}
		}
	}

	public void control() {
		if (getdirect() == 1) {
			floormove += 1;
			if (floormove % 40 == 0) {
				curfloor++;
				if (getdirect() == 0 ||upButtonState[bcurfloor-1] ==1)
					openandclose = 1;
				upButtonState[bcurfloor-1] =0;
			}

		} else if (getdirect() == (-1)) {
			floormove -= 1;
			if (floormove % 40 == 0) {
				curfloor--;
				if (getdirect() == 0|| downButtonState[bcurfloor-1]==1)
					openandclose = 1;
				downButtonState[bcurfloor-1] =0;
			}
		} else if (getdirect() == 0) {
		  	upButtonState[bcurfloor-1] =0;
			downButtonState[bcurfloor-1] =0;
		}
	}
	
	public void bcontrol() {
		if (bgetdirect() == 1) {
			bfloormove += 1;
			if (bfloormove % 40 == 0) {
				bcurfloor++;
				if (bgetdirect() == 0 || upButtonState[bcurfloor-1] ==1)
					bopenandclose = 1;
				upButtonState[bcurfloor-1] =0;
			}

		} else if (bgetdirect() == (-1)) {
			bfloormove -= 1;
			if (bfloormove % 40 == 0) {
				bcurfloor--;
				if (bgetdirect() == 0 || downButtonState[bcurfloor-1]==1)
					bopenandclose = 1;
				downButtonState[bcurfloor-1] =0;
			}
		} else if (bgetdirect() == 0) {
			upButtonState[bcurfloor-1] =0;
			downButtonState[bcurfloor-1] =0;
		}
	}

	public int getCurPos() {
		return purfloor;
	}
	public int bgetCurPos() {
		return bpurfloor;
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
	public int bgetdirect() {
		if (bpurfloor > bcurfloor) {
			bdirect = 1;
			return 1;
		} else if (bpurfloor < bcurfloor) {
			return bdirect = -1;
		} else if (bpurfloor == bcurfloor) {
			return bdirect = 0;
		}

		return bdirect;
	}

	public void openandclosemove() {
		upButtonState[curfloor-1] =0;
		downButtonState[curfloor-1] =0;
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
				upmax = curfloor ;
				downmax = curfloor;
			}
		}

	}

	public void bopenandclosemove() {
		upButtonState[bcurfloor-1] =0;
		downButtonState[bcurfloor-1] =0;
		bdoorabout = 1;
		if (bopentime < 20 && bcloseime == 0)
			bopentime += 1;
		else if (bopentime == 20 || (bcloseime == 1)) {
			bopentime -= 1;
			bcloseime = 1;
			if (bopentime == 0) {
				bcloseime = 0;
				bdoorabout = 0;
				bopenandclose = 0;
				
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
			
			
			g1.setColor(Color.black);
			g1.drawRect(200, 80, 180, 320);
			g1.fillRect(350, 120 + bcurfloor + bfloormove, 20, 30);
			g1.setColor(Color.white);
			g1.fillRect(360 - bopentime, 120 + bcurfloor + bfloormove,
					bopentime * 2, 30);

			if (bdirect == -1)
				g1.setColor(Color.red);
			else
				g1.setColor(Color.black);
			g1.drawString("△", 320, 200);

			if (bdirect == 1)
				g1.setColor(Color.red);
			else
				g1.setColor(Color.black);
			g1.drawString("▽", 320, 220);
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
				
				if ((bgetdirect() == 0 && bopenandclose == 1)
						|| bopenandclose == 1) {
					bopenandclosemove();
				} else if (bdoorabout == 0) {
					bcontrol();
				}
				
				controller();
				
				System.out.println(curfloor);
				System.out.println(bcurfloor);
			}

		}

	}
	public void controller() {
		// TODO Auto-generated method stub

		//处理上行调度
		for(int i=0;i<6;i++)
		{
			if(upButtonState[i]==1){
				upmax=i+1;			
			}			
			
		}
		if((getdirect() == 0 && openandclose == 0)&&bpurfloor!=upmax)
		{ purfloor = upmax; 
		}
		if((bgetdirect() == 0 && bopenandclose == 0)&&purfloor!=upmax)
		{ bpurfloor = upmax;
		}
		
		
		//处理下行调度
		
		for(int i=0;i<6;i++)
		{
			if(downButtonState[i]==1){
				downmax=i+1;			
			}		
		}
		if((getdirect() == 0 && openandclose == 0)&&bpurfloor!=downmax)
		{ purfloor = downmax; 
		}
		if((bgetdirect() == 0 && bopenandclose == 0)&&purfloor!=downmax)
		{ bpurfloor = downmax;
		 }
		
		
		
		//重新描绘所有上下性按钮

		for(int i=0;i<6;i++)
		{
			if(upButtonState[i]==1){
				upButton[i].setBackground(Color.red);
			}else
				upButton[i].setBackground(Color.gray);
			if(downButtonState[i]==1){
				downButton[i].setBackground(Color.red);
			}else
				downButton[i].setBackground(Color.gray);
			
		}
	}

} // 建立窗口

