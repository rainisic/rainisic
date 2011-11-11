package ElevatorV070.copy;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;

public class ListFrame extends JFrame implements Runnable
{
	private static int floorNum = 16; //楼层数
	private static int listNum = 6; //电梯数
	private ListThread[] listThread; //对应电梯的线程数组

	Container cp;       //容器CP
	JPanel floorPanel = new JPanel(); //用来显示每一楼层的楼层号和上下键的面板

	JButton[] floorButton; //楼层号
	BasicArrowButton[] upButton; //向上键
	BasicArrowButton[] downButton; //向下键

	JButton dispUp, dispDown, dispFloor;          //上行按钮，下行按钮

	Color pressedUpDownColor = Color.RED;           //为下行状态布置红色
	Color unPressedUpDownColor = new Color(170, 170, 200);    //将默认按钮设置成灰色

	int[] upState; //用来记录向上键的状态
	int[] downState; //用来记录向下键的状态

	private JMenuBar menuBar;             //生成菜单栏
	private JMenu menu;
	private JMenuItem chooses[] = {
		//new JMenuItem("电梯数(N)"), 
		//new JMenuItem("楼层数(N)"),
		new JMenuItem("统计数据(T)"),
		new JMenuItem("退出(X)")};
	private JMenu manage;
	private JMenuItem mchooses[] = {
			new JMenuItem("算法1"), 
			new JMenuItem("算法2"),
			new JMenuItem("算法3"),
			new JMenuItem("算法4")};

	public ListFrame()
	{
		cp = this.getContentPane();
		cp.setLayout(new GridLayout(1, listNum + 1));  //为布局管理器分列

		floorPanel.setLayout(new GridLayout(floorNum + 1, 3));    //为楼层管理器分层
		floorPanel.setBorder(new MatteBorder(2, 4, 2, 2, Color.yellow));   //吧边框搞成黄色的
		floorButton = new JButton[floorNum];
		upButton = new BasicArrowButton[floorNum];
		downButton = new BasicArrowButton[floorNum];

		dispFloor = new JButton("层");    //添加上下行按钮的表头 和各层的按钮
		dispFloor.setEnabled(false);
		dispUp = new JButton("上");
		dispUp.setEnabled(false);
		dispDown = new JButton("下");
		dispDown.setEnabled(false);                
		floorPanel.add(dispFloor);
		floorPanel.add(dispUp);
		floorPanel.add(dispDown);

		MouseListener upListener = new UpButtonAction(); //向上键的Listener

		//设置属性
		for (int i = floorButton.length - 1; i >= 0; i--)
		{
			floorButton[i] = new JButton(String.valueOf(i + 1));
			//floorButton[i].setForeground(Color.green);
			floorButton[i].setForeground(Color.green);
			floorButton[i].setFont(new Font("Serif", Font.BOLD, 8));
			floorButton[i].setEnabled(false);
			upButton[i] = new BasicArrowButton(BasicArrowButton.NORTH);
			upButton[i].addMouseListener(upListener);
			upButton[i].setBackground(unPressedUpDownColor);
			downButton[i] = new BasicArrowButton(BasicArrowButton.SOUTH);
			downButton[i].addMouseListener(upListener);
			downButton[i].setBackground(unPressedUpDownColor);
			floorPanel.add(floorButton[i]);
			floorPanel.add(upButton[i]);
			floorPanel.add(downButton[i]);
		}

		cp.add(floorPanel);
		// 设置菜单	
		menuBar = new JMenuBar();
		menu = new JMenu("菜单(M)");
		menu.setFont(new Font("Serif", Font.BOLD, 12));
		menu.setForeground(Color.darkGray);
		menu.setMnemonic(KeyEvent.VK_M);
		
		for (int i = 0; i < chooses.length; i++)   //对菜单的内容进行生成
		{
			menu.add(chooses[i]);
			if (i < chooses.length - 1)
			{
				menu.addSeparator();
			}
			chooses[i].setForeground(Color.darkGray);
			chooses[i].setFont(new Font("Serif", Font.BOLD, 12));
		}

		chooses[0].addActionListener(new ActionListener()   //制作统计数据按钮
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame statisticsframe = new JFrame();
				statisticsframe.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
				statisticsframe.setTitle("统计报表");
				statisticsframe.setLocation(50, 50);
				statisticsframe.setSize(630, 660);
				statisticsframe.setResizable(true);
				statisticsframe.show();
			}
		});
		
		chooses[1].addActionListener(new ActionListener()  //制作退出按钮
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		
		
		manage = new JMenu("调度(A)");
		manage.setFont(new Font("Serif", Font.BOLD, 12));
		manage.setForeground(Color.darkGray);
		manage.setMnemonic(KeyEvent.VK_A);

		for (int i = 0; i < mchooses.length; i++)   //对菜单的内容进行生成
		{
			manage.add(mchooses[i]);
			if (i < mchooses.length - 1)
			{
				manage.addSeparator();
			}
			mchooses[i].setForeground(Color.darkGray);
			mchooses[i].setFont(new Font("Serif", Font.BOLD, 12));
		}

		mchooses[0].addActionListener(new ActionListener()   //响应算法一
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
			}
		});
		
		chooses[1].addActionListener(new ActionListener()  //响应算法一
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});

		menuBar.add(menu);
		menuBar.add(manage);
		setJMenuBar(menuBar);

		listThread = new ListThread[listNum];

		//创建电梯线程
		for (int i = 0; i < listNum; i++)
		{
			ListThread list = new ListThread();
			cp.add(list);
			list.getThread().start();
			listThread[i] = list;
		}

		upState = new int[floorNum];
		downState = new int[floorNum];

		//初始化方向键状态
		for (int i = 0; i < upState.length; i++)
		{
			upState[i] = 0;
			downState[i] = 0;
		}

		Thread manageThread = new Thread(this);
		manageThread.start(); //启动调度线程

	}

	//	向上键的Listener
	class UpButtonAction extends MouseAdapter implements MouseListener
	{
		public void mousePressed(MouseEvent e)
		{
			for (int i = 0; i < upButton.length; i++)
			{
				if (e.getSource() == upButton[i])
				{
					upButton[i].setBackground(pressedUpDownColor);
					upState[i] = 1;
				}

				if (e.getSource() == downButton[i])
				{
					downButton[i].setBackground(pressedUpDownColor);
					downState[i] = 1;
				}
			}
		}
	}

	public static int getFloorNum()
	{
		return floorNum;
	}

	//调度线程run()方法
	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(10);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			//处理向上键
			for (int i = 0; i < upState.length; i++)
			{
				if (upState[i] == 1)
				{
					upLookForList(i);
				}
				if (upState[i] >= 5)
				{
					if (i == listThread[upState[i] - 5].getCurPos())
					{
						upState[i] = 0;
						upButton[i].setBackground(unPressedUpDownColor);
						//System.out.println("unPressedUpDownColor");
					}
				}
			}

			//处理向下键
			for (int i = 0; i < downState.length; i++)
			{
				if (downState[i] == 1)
				{
					downLookForList(i);
				}
				if (downState[i] >= 5)
				{
					if (i == listThread[downState[i] - 5].getCurPos())
					{
						downState[i] = 0;
						downButton[i].setBackground(unPressedUpDownColor);
					}
				}
			}
		}
	}

	//	寻找响应向上键最近的电梯
	private boolean upLookForList(int floor)
	{
		int whichList = 0;
		int distance = floorNum;

		for (int j = 0; j < listThread.length; j++)
		{
			if (listThread[j].isAbort()
				|| (listThread[j].isUp() && floor >= listThread[j].getCurPos()))
			{
				int temp = Math.abs(floor - listThread[j].getCurPos());
				if (temp < distance)
				{
					whichList = j;
					distance = Math.abs(floor - listThread[j].getCurPos());
				}
			}
		}

		if (distance != floorNum)
		{
			upState[floor] = 5 + whichList;
			listThread[whichList].setTarPos(floor);
			return true;
		} else
		{
			return false;
		}

	}

	//	寻找响应向下键最近的电梯
	private boolean downLookForList(int floor)
	{
		int whichList = 0;
		int distance = floorNum;

		for (int j = 0; j < listThread.length; j++)
		{
			if (listThread[j].isAbort()
				|| (listThread[j].isDown() && floor <= listThread[j].getCurPos()))
			{
				int temp = Math.abs(floor - listThread[j].getCurPos());
				if (temp < distance)
				{
					whichList = j;
					distance = Math.abs(floor - listThread[j].getCurPos());
				}
			}
		}

		if (distance != floorNum)
		{
			downState[floor] = 5 + whichList;
			listThread[whichList].setTarPos(floor);
			return true;
		} else
		{
			return false;
		}

	}

}
