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
	private static int floorNum = 16; //¥����
	private static int listNum = 6; //������
	private ListThread[] listThread; //��Ӧ���ݵ��߳�����

	Container cp;       //����CP
	JPanel floorPanel = new JPanel(); //������ʾÿһ¥���¥��ź����¼������

	JButton[] floorButton; //¥���
	BasicArrowButton[] upButton; //���ϼ�
	BasicArrowButton[] downButton; //���¼�

	JButton dispUp, dispDown, dispFloor;          //���а�ť�����а�ť

	Color pressedUpDownColor = Color.RED;           //Ϊ����״̬���ú�ɫ
	Color unPressedUpDownColor = new Color(170, 170, 200);    //��Ĭ�ϰ�ť���óɻ�ɫ

	int[] upState; //������¼���ϼ���״̬
	int[] downState; //������¼���¼���״̬

	private JMenuBar menuBar;             //���ɲ˵���
	private JMenu menu;
	private JMenuItem chooses[] = {
		//new JMenuItem("������(N)"), 
		//new JMenuItem("¥����(N)"),
		new JMenuItem("ͳ������(T)"),
		new JMenuItem("�˳�(X)")};
	private JMenu manage;
	private JMenuItem mchooses[] = {
			new JMenuItem("�㷨1"), 
			new JMenuItem("�㷨2"),
			new JMenuItem("�㷨3"),
			new JMenuItem("�㷨4")};

	public ListFrame()
	{
		cp = this.getContentPane();
		cp.setLayout(new GridLayout(1, listNum + 1));  //Ϊ���ֹ���������

		floorPanel.setLayout(new GridLayout(floorNum + 1, 3));    //Ϊ¥��������ֲ�
		floorPanel.setBorder(new MatteBorder(2, 4, 2, 2, Color.yellow));   //�ɱ߿��ɻ�ɫ��
		floorButton = new JButton[floorNum];
		upButton = new BasicArrowButton[floorNum];
		downButton = new BasicArrowButton[floorNum];

		dispFloor = new JButton("��");    //��������а�ť�ı�ͷ �͸���İ�ť
		dispFloor.setEnabled(false);
		dispUp = new JButton("��");
		dispUp.setEnabled(false);
		dispDown = new JButton("��");
		dispDown.setEnabled(false);                
		floorPanel.add(dispFloor);
		floorPanel.add(dispUp);
		floorPanel.add(dispDown);

		MouseListener upListener = new UpButtonAction(); //���ϼ���Listener

		//��������
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
		// ���ò˵�	
		menuBar = new JMenuBar();
		menu = new JMenu("�˵�(M)");
		menu.setFont(new Font("Serif", Font.BOLD, 12));
		menu.setForeground(Color.darkGray);
		menu.setMnemonic(KeyEvent.VK_M);
		
		for (int i = 0; i < chooses.length; i++)   //�Բ˵������ݽ�������
		{
			menu.add(chooses[i]);
			if (i < chooses.length - 1)
			{
				menu.addSeparator();
			}
			chooses[i].setForeground(Color.darkGray);
			chooses[i].setFont(new Font("Serif", Font.BOLD, 12));
		}

		chooses[0].addActionListener(new ActionListener()   //����ͳ�����ݰ�ť
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame statisticsframe = new JFrame();
				statisticsframe.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
				statisticsframe.setTitle("ͳ�Ʊ���");
				statisticsframe.setLocation(50, 50);
				statisticsframe.setSize(630, 660);
				statisticsframe.setResizable(true);
				statisticsframe.show();
			}
		});
		
		chooses[1].addActionListener(new ActionListener()  //�����˳���ť
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		
		
		manage = new JMenu("����(A)");
		manage.setFont(new Font("Serif", Font.BOLD, 12));
		manage.setForeground(Color.darkGray);
		manage.setMnemonic(KeyEvent.VK_A);

		for (int i = 0; i < mchooses.length; i++)   //�Բ˵������ݽ�������
		{
			manage.add(mchooses[i]);
			if (i < mchooses.length - 1)
			{
				manage.addSeparator();
			}
			mchooses[i].setForeground(Color.darkGray);
			mchooses[i].setFont(new Font("Serif", Font.BOLD, 12));
		}

		mchooses[0].addActionListener(new ActionListener()   //��Ӧ�㷨һ
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
			}
		});
		
		chooses[1].addActionListener(new ActionListener()  //��Ӧ�㷨һ
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

		//���������߳�
		for (int i = 0; i < listNum; i++)
		{
			ListThread list = new ListThread();
			cp.add(list);
			list.getThread().start();
			listThread[i] = list;
		}

		upState = new int[floorNum];
		downState = new int[floorNum];

		//��ʼ�������״̬
		for (int i = 0; i < upState.length; i++)
		{
			upState[i] = 0;
			downState[i] = 0;
		}

		Thread manageThread = new Thread(this);
		manageThread.start(); //���������߳�

	}

	//	���ϼ���Listener
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

	//�����߳�run()����
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

			//�������ϼ�
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

			//�������¼�
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

	//	Ѱ����Ӧ���ϼ�����ĵ���
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

	//	Ѱ����Ӧ���¼�����ĵ���
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
