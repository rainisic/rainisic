package ElevatorV090.bate;

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
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import com.kakalion.html.*;
public class ListFrame extends JFrame implements Runnable, Publiclist {
	private static int floorNum = 16; // ¥����
	private static int listNum = 6; // ������
	private ListThread[] listThread; // ��Ӧ���ݵ��߳�����

	Container cp; // ����CP
	JPanel floorPanel = new JPanel(); // ������ʾÿһ¥���¥��ź����¼������

	JButton[] floorButton; // ¥���
	BasicArrowButton[] upButton; // ���ϼ�
	BasicArrowButton[] downButton; // ���¼�

	JButton dispUp, dispDown, dispFloor; // ���а�ť�����а�ť

	Color pressedUpDownColor = Color.RED; // Ϊ����״̬���ú�ɫ
	Color unPressedUpDownColor = new Color(170, 170, 200); // ��Ĭ�ϰ�ť���óɻ�ɫ

	int[] upState; // ������¼���ϼ���״̬
	int[] downState; // ������¼���¼���״̬

	private JMenuBar menuBar; // ���ɲ˵���
	private JMenu menu;
	private JMenuItem chooses[] = {
			// new JMenuItem("������(N)"),
			// new JMenuItem("¥����(N)"),
			new JMenuItem("ͳ������(T)"), new JMenuItem("���е���ʹ����Աģ��(A)"),
			new JMenuItem("�˳�(X)") };
	private JMenu manage;
	private JMenuItem mchooses[] = { new JMenuItem("ͨ���㷨"),
			new JMenuItem("��æʱ��¥���㷨"), new JMenuItem("��ż¥���㷨"),
			new JMenuItem("����¥���㷨") };
	private JMenu worktime;
	private JMenuItem wchooses[] = { new JMenuItem("6:00-8:00"),
			new JMenuItem("8:00-12:00"), new JMenuItem("12:00-14:00"),
			new JMenuItem("14:00-17:00"), new JMenuItem("17:00-19:00"),
			new JMenuItem("19:00-6:00") };
	boolean ListAiO = false;// �����˹�ģ�⿪�ذ�ť
	int aiTime , aiDegree ;
	IReport report=new Report();
	int a[][]=new int[16][2];
	int b[][]=new int[17][6];
	public ListFrame() {
		cp = this.getContentPane();
		cp.setLayout(new GridLayout(1, listNum + 1)); // Ϊ���ֹ���������

		floorPanel.setLayout(new GridLayout(floorNum + 1, 3)); // Ϊ¥��������ֲ�
		floorPanel.setBorder(new MatteBorder(2, 4, 2, 2, Color.yellow)); // �ɱ߿��ɻ�ɫ��
		floorButton = new JButton[floorNum];
		upButton = new BasicArrowButton[floorNum];
		downButton = new BasicArrowButton[floorNum];

		dispFloor = new JButton("��"); // ��������а�ť�ı�ͷ �͸���İ�ť
		dispFloor.setEnabled(false);
		dispUp = new JButton("��");
		dispUp.setEnabled(false);
		dispDown = new JButton("��");
		dispDown.setEnabled(false);
		floorPanel.add(dispFloor);
		floorPanel.add(dispUp);
		floorPanel.add(dispDown);

		MouseListener upListener = new UpButtonAction(); // ���ϼ���Listener

		// ��������
		for (int i = floorButton.length - 1; i >= 0; i--) {
			floorButton[i] = new JButton(String.valueOf(i + 1));
			// floorButton[i].setForeground(Color.green);
			floorButton[i].setForeground(Color.green);
			floorButton[i].setFont(new Font("Serif", Font.BOLD, 8));
			floorButton[i].setEnabled(false);
			upButton[i] = new BasicArrowButton(BasicArrowButton.NORTH);
			if (i != floorButton.length - 1) {
				upButton[i].addMouseListener(upListener);
			}
			upButton[i].setBackground(unPressedUpDownColor);
			downButton[i] = new BasicArrowButton(BasicArrowButton.SOUTH);
			if (i != 0) {
				downButton[i].addMouseListener(upListener);
			}
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

		for (int i = 0; i < chooses.length; i++) // �Բ˵������ݽ�������
		{
			menu.add(chooses[i]);
			if (i < chooses.length - 1) {
				menu.addSeparator();
			}
			chooses[i].setForeground(Color.darkGray);
			chooses[i].setFont(new Font("Serif", Font.BOLD, 12));
		}

		chooses[0].addActionListener(new ActionListener() // ����ͳ�����ݰ�ť
				{
					public void actionPerformed(ActionEvent arg0) {
						System.out.println(listText.time);
						try {
							Process p=Runtime.getRuntime().exec("explorer c:\\wocao.html");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					
					}
				});

		chooses[1].addActionListener(new ActionListener() // �������ݰ�ť
				{
					public void actionPerformed(ActionEvent arg0) {
						if (ListAiO) {
							ListAiO = false;
							chooses[1].setBackground(Color.gray);

						} else {
							ListAiO = true;
							aiDegree = 1;
							aiTime = 6001;
							listAi.setListAiAllData();
							chooses[1].setBackground(Color.green);
						}
					}
				});

		chooses[2].addActionListener(new ActionListener() // �����˳���ť
				{
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});

		manage = new JMenu("����(C)");
		manage.setFont(new Font("Serif", Font.BOLD, 12));
		manage.setForeground(Color.darkGray);
		manage.setMnemonic(KeyEvent.VK_C);

		for (int i = 0; i < mchooses.length; i++) // �Ե��Ȳ˵������ݽ�������
		{
			manage.add(mchooses[i]);
			if (i < mchooses.length - 1) {
				manage.addSeparator();
			}
			for (int j = 0; j < mchooses.length; j++) {
				mchooses[j].setBackground(Color.white);
			}
			mchooses[0].setBackground(Color.green);
			mchooses[i].setForeground(Color.darkGray);
			mchooses[i].setFont(new Font("Serif", Font.BOLD, 12));
		}

		mchooses[0].addActionListener(new ActionListener() // ��Ӧ�㷨һ
				{
					public void actionPerformed(ActionEvent arg0) {
						mcControl1();
					}
				});
		
		mchooses[1].addActionListener(new ActionListener() // ��Ӧ�㷨2
				{
					public void actionPerformed(ActionEvent arg0) {
						mcControl2();
					}
				});

		mchooses[2].addActionListener(new ActionListener() // ��Ӧ�㷨3
				{
					public void actionPerformed(ActionEvent arg0) {		
						mcControl3();
					}
				});

		mchooses[3].addActionListener(new ActionListener() // ��Ӧ�㷨4
				{
					public void actionPerformed(ActionEvent arg0) {
						mcControl4();

					}
				});

		worktime = new JMenu("����ʱ���(W)");
		worktime.setFont(new Font("Serif", Font.BOLD, 12));
		worktime.setForeground(Color.darkGray);
		worktime.setMnemonic(KeyEvent.VK_W);

		for (int i = 0; i < wchooses.length; i++) // �Թ���ʱ��β˵������ݽ�������
		{
			worktime.add(wchooses[i]);
			if (i < wchooses.length - 1) {
				worktime.addSeparator();
			}
			wchooses[i].setForeground(Color.darkGray);
			wchooses[i].setFont(new Font("Serif", Font.BOLD, 12));
		}

		wchooses[0].addActionListener(new ActionListener() // ��Ӧ�㷨6:00-8:00
				{
					public void actionPerformed(ActionEvent arg0) {
						mcControl4();
						mchooses2MethedControl(0);
					}
				});

		wchooses[1].addActionListener(new ActionListener() // ��Ӧ�㷨"8:00-12:00"
				{
					public void actionPerformed(ActionEvent arg0) {
						mcControl1();
						mchooses2MethedControl(1);
					}
				});

		wchooses[2].addActionListener(new ActionListener() // 12:00-14:00
				{
					public void actionPerformed(ActionEvent arg0) {
						mcControl3();
						mchooses2MethedControl(2);
					}
				});

		wchooses[3].addActionListener(new ActionListener() // 14:00-17:00"
				{
					public void actionPerformed(ActionEvent arg0) {
						mcControl1();
						mchooses2MethedControl(3);
					}
				});

		wchooses[4].addActionListener(new ActionListener() // 17:00-19:00
				{
					public void actionPerformed(ActionEvent arg0) {
						mcControl2();
						mchooses2MethedControl(4);
					}
				});

		wchooses[5].addActionListener(new ActionListener() // 19:00-6:00
				{
					public void actionPerformed(ActionEvent arg0) {
						mcControl1();
						mchooses2MethedControl(5);
					}
				});

		menuBar.add(menu);
		menuBar.add(manage);
		menuBar.add(worktime);
		setJMenuBar(menuBar);

		listThread = new ListThread[listNum];

		// ���������߳�
		for (int i = 0; i < listNum; i++) {
			ListThread list = new ListThread();
			list.setlistNum(i);
			cp.add(list);
			list.getThread().start();
			listThread[i] = list;
		}

		upState = new int[floorNum];
		downState = new int[floorNum];

		// ��ʼ�������״̬
		for (int i = 0; i < upState.length; i++) {
			upState[i] = 0;
			downState[i] = 0;
		}
		Thread manageThread = new Thread(this);
		manageThread.start(); // ���������߳�
	}

	// �����¼���Listener
	class UpButtonAction extends MouseAdapter implements MouseListener {
		public void mousePressed(MouseEvent e) {
			for (int i = 0; i < upButton.length; i++) {
				if (e.getSource() == upButton[i]) {
					UpButtonPressed(i);
					// upButton[i].setBackground(pressedUpDownColor);
					// upState[i] = 1;
				}

				if (e.getSource() == downButton[i]) {
					DownButtonPressed(i);
					// downButton[i].setBackground(pressedUpDownColor);
					// downState[i] = 1;
				}
			}
		}
	}

	public void UpButtonPressed(int i) {
		upButton[i].setBackground(pressedUpDownColor);
		upState[i] = 1;
		setlistUpButtonCalOn(i);
	}

	public void DownButtonPressed(int i) {
		downButton[i].setBackground(pressedUpDownColor);
		downState[i] = 1;
		setlistDownButtonCalOn(i);
	}

	public static int getFloorNum() {
		return floorNum;
	}
	
	

	// �����߳�run()����
	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// �������ϼ�
			for (int i = 0; i < upState.length; i++) {
				if (upState[i] == 1) {
					upLookForList(i);
				}
				if (upState[i] >= 5) {
					if (i == listThread[upState[i] - 5].getCurPos() + 1) {
						listThread[upState[i] - 5].setNumState(i);
					}
					if (i == listThread[upState[i] - 5].getCurPos()) {
						upState[i] = 0;
						upButton[i].setBackground(unPressedUpDownColor);
						// System.out.println("unPressedUpDownColor");
						setlistUpButtonCalOff(i);
					}
				}
			}

			// �������¼�
			for (int i = 0; i < downState.length; i++) {
				if (downState[i] == 1) {
					downLookForList(i);
				}
				if (downState[i] >= 5) {
					if (i == listThread[downState[i] - 5].getCurPos() - 1) {
						listThread[downState[i] - 5].setNumState(i);
					}
					if (i == listThread[downState[i] - 5].getCurPos()) {
						downState[i] = 0;
						downButton[i].setBackground(unPressedUpDownColor);
						setlistDownButtonCalOff(i);
					}
				}
			}

			// �����˹�����
			if (ListAiO) {
	
				if (aiTime > 3000 && aiDegree < 8) {
					aiTime = 0;
					aiDegree++;
					aiControl();
				}				
				aiPeopleControl();
				aiTime++;
				
				System.out.println("���������Ӧʱ��"+listText.listUpMax);
				System.out.println("���������Ӧʱ��"+listText.listDownMax);
			//	System.out.println("���������Ӧʱ��"+listText.listUpMax);
			}

			
		
			
		}
	}

	// Ѱ����Ӧ���ϼ�����ĵ���
	private boolean upLookForList(int floor) {
		int whichList = 0;
		int distance = floorNum;

		for (int j = 0; j < listThread.length; j++) {
			if ((listThread[j].isAbort() || (listThread[j].isUp() && floor >= listThread[j]
					.getCurPos()))
					&& !(listThread[j].getFloorlock(floor))) {
				int temp = Math.abs(floor - listThread[j].getCurPos());
				if (temp < distance) {
					whichList = j;
					distance = Math.abs(floor - listThread[j].getCurPos());
				}
			}
		}
		if (distance != floorNum) {
			upState[floor] = 5 + whichList;
			listThread[whichList].setTarPos(floor);
			// listThread[whichList].setNumState(floor);
			return true;
		} else {
			return false;
		}

	}

	// Ѱ����Ӧ���¼�����ĵ���
	private boolean downLookForList(int floor) {
		int whichList = 0;
		int distance = floorNum;

		for (int j = 0; j < listThread.length; j++) {
			if ((listThread[j].isAbort() || (listThread[j].isDown() && floor <= listThread[j]
					.getCurPos()))
					&& !(listThread[j].getFloorlock(floor))) {
				int temp = Math.abs(floor - listThread[j].getCurPos());
				if (temp < distance) {
					whichList = j;
					distance = Math.abs(floor - listThread[j].getCurPos());
				}
			}
		}

		if (distance != floorNum) {
			downState[floor] = 5 + whichList;

			listThread[whichList].setTarPos(floor);
			// listThread[whichList].setNumState(floor);

			return true;
		} else {
			return false;
		}

	}

	public void mchoosesmethed1() {
		for (int i = 0; i < listNum; i++) {
			for (int j = 0; j < floorNum; j++) {
				listThread[i].setFloorunlock(j);
			}
		}
	}

	public void mchoosesmethed2() {
		for (int i = 0; i < listNum; i++) {
			if (i % 2 == 0) {
				for (int j = 0; j < floorNum; j++) {
					if (j % 2 == 0 && j != 0) {
						listThread[i].setFloorlock(j);
					} else {
						listThread[i].setFloorunlock(j);
					}
				}
			}

			else {
				for (int j = 0; j < floorNum; j++) {
					if (j % 2 == 1 && j != 0) {
						listThread[i].setFloorlock(j);
					} else {
						listThread[i].setFloorunlock(j);
					}
				}
			}
		}
	}

	public void mchoosesmethed3() {
		int maxNum = floorNum * 2; // �߷��㷨���ú���

		for (int i = 0; i < maxNum; i++) {

			for (int j = 0; j < floorNum; j++) {
				if (j > maxNum / 2) {
					listThread[i].setFloorlock(j);
				} else {
					listThread[i].setFloorunlock(j);
				}
			}

			if (i % 2 == 1)
				maxNum = maxNum / 2;
		}

	}

	public void mchoosesmethed4() {
		mchoosesmethed1();
		for (int i = 0; i < listNum; i++) {
			listThread[i].setspfloorOff(1);
		}
	}

	public void mcControl1()  //�㷨1ʹ�ÿ���
	{
		boolean mchoosesMethedControlOff = true;
		for (int i = 0; i < listNum; i++) {
			if(!listThread[i].isAbort())
				mchoosesMethedControlOff = false;
		}
		if(mchoosesMethedControlOff){
		mchoosesmethed1();
		mchoosesMethedControl(0);}
		
	}
	
	public void mcControl2()       //�㷨2ʹ�ÿ���
	{
		boolean mchoosesMethedControlOff = true;
		for (int i = 0; i < listNum; i++) {
			if(!listThread[i].isAbort())
				mchoosesMethedControlOff = false;
		}
		if(mchoosesMethedControlOff){
		mchoosesmethed3();
		mchoosesMethedControl(1);}
	}
	
	public void mcControl3()  //�㷨3ʹ�ÿ���
	{
		boolean mchoosesMethedControlOff = true;
		for (int i = 0; i < listNum; i++) {
			if(!listThread[i].isAbort())
				mchoosesMethedControlOff = false;
		}
		if(mchoosesMethedControlOff){
		mchoosesmethed2();
		mchoosesMethedControl(2);}
		
	}
	
	public void mcControl4()  //�㷨4ʹ�ÿ���
	{
		boolean mchoosesMethedControlOff = true;
		for (int i = 0; i < listNum; i++) {
			if(!listThread[i].isAbort())
				mchoosesMethedControlOff = false;
		}
		if(mchoosesMethedControlOff){
		mchoosesmethed4();
		mchoosesMethedControl(3);}
		
	}
	
	public void mchoosesMethedControl(int a) {   //�㷨��ʾ����


		for (int i = 0; i < mchooses.length; i++) {
			mchooses[i].setBackground(Color.white);
		}

		mchooses[a].setBackground(Color.green);

		if (a == 3) {
		} else {
			for (int i = 0; i < listNum; i++) {
				listThread[i].setspfloorOff(0);
			}
		}
		for (int i = 0; i < upState.length; i++) {
			upState[i] = 0;
			downState[i] = 0;
			upButton[i].setBackground(unPressedUpDownColor);
			downButton[i].setBackground(unPressedUpDownColor);
			
			setlistUpButtonCalOff(i);
			setlistDownButtonCalOff(i);
		}
		
	}
	

	public void mchooses2MethedControl(int a) { //ʱ����ʾ����

		boolean mchoosesMethedControlOff = true;
		for (int i = 0; i < listNum; i++) {
			if(!listThread[i].isAbort())
				mchoosesMethedControlOff = false;
		}
		if(mchoosesMethedControlOff){
		for (int i = 0; i < wchooses.length; i++) {
			wchooses[i].setBackground(Color.white);
		}
		wchooses[a].setBackground(Color.green);

		for (int i = 0; i < upState.length; i++) {
			upState[i] = 0;
			downState[i] = 0;
			upButton[i].setBackground(unPressedUpDownColor);
			downButton[i].setBackground(unPressedUpDownColor);
			setlistUpButtonCalOff(i);
			setlistDownButtonCalOff(i);
		}
		}
	}

	public void aiControl() {
		int aiBornNum;
		for (int i = aiDegree*20; i < (aiDegree*20+20); i++) {
			aiBornNum = listAi.getAiBornPos(i);
            // System.out.println(listAi.getListAiData(i).AiBornPos);
            // System.out.println(listAi.getListAiData(i).AicurPos);
			if (listAi.getListAiData(i).getAiDirect()) {
				UpButtonPressed(aiBornNum);
			} else {
				DownButtonPressed(aiBornNum);
			}
			}
			
		}

	
	public void aiPeopleControl() {
		int aiBornNum;
		for (int i = aiDegree*20; i < (aiDegree*20+20); i++) {
			aiBornNum = listAi.getAiBornPos(i);
			for (int j = 0; j < listNum; j++) {
				if(aiBornNum == listThread[j].getCurPos() 
						&&(listThread[j].isAbort() || (listThread[j].isUp()))
						&& listAi.getListAiData(i).getAiDirect()
						&& !listThread[j].getFloorlock(listAi.getListAiData(i).AicurPos)
						&& listThread[j].listPeople()<11
						&& listAi.getListAiData(i).AiExist)
				{
				listThread[j].listNumPressed(listAi.getListAiData(i).AicurPos);
				listThread[j].setlistPeople(listThread[j].listPeople()+1);
				listAi.getListAiData(i).AiExist = false;
				}
				
				if(aiBornNum == listThread[j].getCurPos() 
						&&(listThread[j].isAbort() || (listThread[j].isDown()))
						&& !listAi.getListAiData(i).getAiDirect()
						&& !listThread[j].getFloorlock(listAi.getListAiData(i).AicurPos)
						&& listThread[j].listPeople()<10
						&& listAi.getListAiData(i).AiExist)
				{
				listThread[j].listNumPressed(listAi.getListAiData(i).AicurPos);
				listThread[j].setlistPeople(listThread[j].listPeople()+1);
				listAi.getListAiData(i).AiExist = false;
				}
			}
			
		}

	}
	public void setlistUpButtonCalOn(int i)    //���õ��ݰ�ť��Ӧͳ�ƿ���
	{
		listText.listUpDownButton[i][0][1] = 1 ;
	}
	public void setlistUpButtonCalOff(int i)    //���õ��ݰ�ť��Ӧͳ��
	{
		listText.listUpDownButton[i][0][1] = 0 ;
	}
	public void setlistDownButtonCalOn(int i)    //���õ��ݰ�ť��Ӧͳ�ƿ���
	{
		listText.listUpDownButton[i][1][1] = 1 ;
	}
	public void setlistDownButtonCalOff(int i)    //���õ��ݰ�ť��Ӧͳ��
	{
		listText.listUpDownButton[i][1][1] = 0 ;
	}

}
