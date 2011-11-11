package ElevatorV080;

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
	private final int UP = 1, DN = -1, ABORT = 0; //电梯的状态 
	private static int floorNum; //楼层数
	private int direction; //电梯运行的方向或停止
	private int curPos; //电梯目前处于的楼层位置
	private boolean[] numState; //电梯内部数字键的状态

	private int tarPos; //电梯到达的目标位置
	private boolean[] floorlock; //设置电梯锁
	private Thread thread; //自身线程
	int listNum; //电梯编号
	private int spfloor = 0; //电梯的特殊楼层
	private boolean spflooroff = false; //特殊电梯开关的状态

	private Color numColor0 = new Color(192, 160, 190), numColor1 = Color.green,  numColor2=Color.gray;
	private Color floorColor0 = new Color(100, 100, 100),
		floorColor1 = Color.blue;

	JButton[] listButton; //代表电梯的数组
	JButton[] numButton; //电梯内部数字按钮
	JLabel dispState,  dispFloor;

	public ListThread()
	{
		floorNum = ListFrame.getFloorNum();
		direction = ABORT;
		curPos = 0;
		tarPos = 0;

		//对电梯内部的数字键进行状态初始化
		numState = new boolean[floorNum];
		for (int i = 0; i < numState.length; i++)
		{
			numState[i] = false;
		}
		
		//对电梯内部的电梯锁进行状态初始化
		floorlock = new boolean[floorNum];
		for (int i = 0; i < numState.length; i++)
		{
			floorlock[i] = false;
		}

		//产生自身线程
		thread = new Thread(this);

		//面板布局
		setLayout(new GridLayout(floorNum+1, 2));
		this.setBorder(new MatteBorder(2, 2, 2, 2, Color.orange));
		listButton = new JButton[floorNum];
		numButton = new JButton[floorNum];

		dispFloor = new JLabel("楼层号",  SwingConstants.CENTER);
		//dispFloor.setForeground(new Color(217, 123, 2));
		dispState = new JLabel("停止", SwingConstants.CENTER);
		dispState.setForeground(new Color(217, 123, 2));

		this.add(dispFloor);
		this.add(dispState);

		MouseListener numListener = new NumButtonAction();
		//设置属性
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

	//电梯内部数字键的监听器类
	class NumButtonAction extends MouseAdapter implements MouseListener
	{

		public void mousePressed(MouseEvent e)
		{
			for (int i = 0; i < numButton.length; i++)
			{
				//System.out.println();
				if (e.getSource() == numButton[i] && !floorlock[i])
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

	//电梯线程的run()方法
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
				dispState.setText("上行");
				moveUp();
				direction = ABORT;
				dispState.setText("停止");				
				if(spflooroff){
					this.setTarPos(spfloor);
						}
			} else if (tarPos < curPos)
			{
				direction = DN;
				dispState.setText("下行");
				moveDn();
				direction = ABORT;
				dispState.setText("停止");
				if(spflooroff){
					this.setTarPos(spfloor);
						}
			}

		}
	}

	//电梯向上运行
	public void moveUp()
	{
		int oldPos = curPos;
		for (int i = curPos + 1; i <= tarPos; i++)
		{
			try
			{
				dispState.setText("上行");
				Thread.sleep(500);
				listButton[i].setBackground(floorColor1);
				
				if (i > oldPos)
				{
					listButton[i - 1].setBackground(floorColor0);
				}
				
				if (numState[i])
				{				
					dispState.setText("开门");
					Thread.sleep(1000);
					
					dispState.setText("关门");
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

	//	电梯向下运行
	public void moveDn()
	{
		int oldPos = curPos;
		for (int i = curPos - 1; i >= tarPos; i--)
		{
			try
			{
				Thread.sleep(500);
				listButton[i].setBackground(Color.blue);
				
				if (i < oldPos)
				{
					listButton[i + 1].setBackground(floorColor0);
				}
				
				if (numState[i])
				{
					dispState.setText("开门");
					Thread.sleep(1000);			
					dispState.setText("关门");
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

	public void setNumState(int i)
	{
		numState[i] = true;
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

	public void setFloorlock(int i)
	{
		floorlock[i] = true;
		numButton[i].setBackground(numColor2);
		
	}
	
	public void setFloorunlock(int i)
	{
		floorlock[i] = false;
		numButton[i].setBackground(numColor0);
		
	}
	
	public boolean getFloorlock(int i)
	{
		return floorlock[i];
	}

	public void setlistNum(int i)
	{
		listNum = i;
		dispFloor.setText("楼层号"+ (i+1));
		
	}
	
	public int getlistNum()
	{
		return listNum;
	}
	
	public void setSpfloor(int i)
	{
		spfloor = i;
	}
	
	public int getSpfloor()
	{
		return spfloor;
	}
	
	public void setspfloorOff(int i)
	{
		if(i == 1)
		spflooroff = true;
		else{
			spflooroff = false;		
		}
	}
	
	public boolean getspfloorOff()
	{
		return spflooroff;
	}
	
}
