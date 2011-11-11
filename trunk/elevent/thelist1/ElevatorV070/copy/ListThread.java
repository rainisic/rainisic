package ElevatorV070.copy;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class ListThread extends JPanel implements Runnable
{
	private final int UP = 1, DN = -1, ABORT = 0; //���ݵ�״̬
	private static int floorNum; //¥����
	private int direction; //�������еķ����ֹͣ
	private int curPos; //����Ŀǰ���ڵ�¥��λ��
	private boolean[] numState; //�����ڲ����ּ���״̬
	private int tarPos; //���ݵ����Ŀ��λ��
	private Thread thread; //�����߳�

	private Color numColor0 = new Color(192, 160, 190), numColor1 = Color.green;
	private Color floorColor0 = new Color(100, 100, 100),
		floorColor1 = Color.blue;

	JButton[] listButton; //������ݵ�����
	JButton[] numButton; //�����ڲ����ְ�ť
	JLabel dispState,  dispFloor;

	public ListThread()
	{
		floorNum = ListFrame.getFloorNum();
		direction = ABORT;
		curPos = 0;
		tarPos = 0;

		//�Ե����ڲ������ּ�����״̬��ʼ��
		numState = new boolean[floorNum];
		for (int i = 0; i < numState.length; i++)
		{
			numState[i] = false;
		}

		//���������߳�
		thread = new Thread(this);

		//��岼��
		setLayout(new GridLayout(floorNum+1, 2));
		this.setBorder(new MatteBorder(2, 2, 2, 2, Color.orange));
		listButton = new JButton[floorNum];
		numButton = new JButton[floorNum];

		dispFloor = new JLabel("¥���",  SwingConstants.CENTER);
		//dispFloor.setForeground(new Color(217, 123, 2));
		dispState = new JLabel("ֹͣ", SwingConstants.CENTER);
		dispState.setForeground(new Color(217, 123, 2));

		this.add(dispFloor);
		this.add(dispState);

		MouseListener numListener = new NumButtonAction();
		//��������
		for (int i = listButton.length - 1; i >= 0; i--)
		{
			numButton[i] = new JButton(String.valueOf(i + 1));
			numButton[i].addMouseListener(numListener);
			//numButton[i].setForeground(numColor0);
			numButton[i].setBackground(numColor0);
			listButton[i] = new JButton();
			listButton[i].setEnabled(false);
			listButton[i].setBackground(floorColor0);
			this.add(numButton[i]);
			this.add(listButton[i]);
		}
		listButton[curPos].setBackground(floorColor1);
	}

	//�����ڲ����ּ��ļ�������
	class NumButtonAction extends MouseAdapter implements MouseListener
	{

		public void mousePressed(MouseEvent e)
		{
			for (int i = 0; i < numButton.length; i++)
			{
				if (e.getSource() == numButton[i])
				{
					numState[i] = true;
					numButton[i].setBackground(numColor1);
					if (direction == ABORT)
					{
						tarPos = i;
					}

					if (direction == UP)
					{
						tarPos = getMaxPressedNum();
					}
					if (direction == DN)
					{
						tarPos = getMinPressedNum();
					}
				}
			}
		}
	}

	//�����̵߳�run()����
	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		if (direction == UP || direction == DN)
			{
				try
				{
					Thread.sleep(100);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				direction = ABORT;
				//System.out.println("if (direction == UP || direction == DN)");
			}
			
			if (tarPos > curPos)
			{
				direction = UP;
				dispState.setText("����");
				moveUp();
				direction = ABORT;
				dispState.setText("ֹͣ");
			} else if (tarPos < curPos)
			{
				direction = DN;
				dispState.setText("����");
				moveDn();
				direction = ABORT;
				dispState.setText("ֹͣ");
			}

		}
	}

	//������������
	public void moveUp()
	{
		int oldPos = curPos;
		for (int i = curPos + 1; i <= tarPos; i++)
		{
			try
			{
				dispState.setText("����");
				Thread.sleep(600);
				listButton[i].setBackground(floorColor1);
				
				if (i > oldPos)
				{
					listButton[i - 1].setBackground(floorColor0);
				}
				
				if (numState[i])
				{				
					dispState.setText("����");
					Thread.sleep(1000);
					
					dispState.setText("����");
					numButton[i].setBackground(numColor0);
					Thread.sleep(1000);
				}			
				curPos = i;

			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		clearState();
	}

	//	������������
	public void moveDn()
	{
		int oldPos = curPos;
		for (int i = curPos - 1; i >= tarPos; i--)
		{
			try
			{
				Thread.sleep(600);
				listButton[i].setBackground(Color.blue);
				
				if (i < oldPos)
				{
					listButton[i + 1].setBackground(floorColor0);
				}
				
				if (numState[i])
				{
					dispState.setText("����");
					Thread.sleep(1000);			
					dispState.setText("����");
					numButton[i].setBackground(numColor0);
					Thread.sleep(1000);
				}
				curPos = i;


			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		clearState();
	}

	public Thread getThread()
	{
		return thread;
	}

	private int getMinPressedNum()
	{
		int min = 0;
		for (int i = 0; i < numState.length; i++)
		{
			if (numState[i])
			{
				min = i;
				break;
			}
		}
		return min;
	}

	private int getMaxPressedNum()
	{
		int max = 0;
		for (int i = numState.length - 1; i >= 0; i--)
		{
			if (numState[i])
			{
				max = i;
				break;
			}
		}
		return max;
	}

	private void clearState()
	{
		for (int i = 0; i < numState.length; i++)
		{
			if (numState[i])
			{
				numState[i] = false;
				numButton[i].setBackground(numColor0);
			}
		}
	}

	public int getDirection()
	{
		return direction;
	}

	public int getTarPos()
	{
		return tarPos;
	}

	public void setDirection(int i)
	{
		direction = i;
	}

	public void setTarPos(int i)
	{
		if (direction == ABORT)
		{
			tarPos = i;
			numState[i] = true;
			if (curPos > tarPos)
			{
				direction = DN;
			}
			if (curPos < tarPos)
				{
					direction = UP;
				}				
		}
		
		if (direction == UP && i > tarPos)
		{
			tarPos = i;
			numState[i] = true;
		}

		if (direction == DN && i < tarPos)
		{
			tarPos = i;
			numState[i] = true;
		}
	}

	public boolean isUp()
	{
		return direction == UP;
	}

	public boolean isDown()
	{
		return direction == DN;
	}

	public boolean isAbort()
	{
		return direction == ABORT;
	}

	public int getCurPos()
	{
		return curPos;
	}

	public void setDirectionUp()
	{
		direction = UP;
	}

	public void setDirectionDn()
	{
		direction = DN;
	}

	public void setDirectionAbort()
	{
		direction = ABORT;
	}


}
