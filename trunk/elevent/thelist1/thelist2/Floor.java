package thelist2;
/*文件名：Floor.java
  类名：Floor.class
  功能：生成楼层的布局，以及乘客小人在楼层里移动
*/
import java.awt.*;
import java.applet.Applet;
import java.applet.*;
//Floor 类定义
public class Floor extends Applet
    implements Runnable
{
//声明Floor类成员变量类型
    public AudioClip stepsound;//乘客走路的声音
    public boolean occupied1;//乘客1是否在该楼层
    public boolean occupied2;//乘客2是否在该楼层
    public boolean occupied3;//乘客3是否在该楼层
    public boolean occupied4;//乘客4是在否该楼层
    public boolean occupied5;//乘客5是否在该楼层
    public boolean occupied6;//乘客6是否在该楼层
    public boolean occupied7;//乘客7是否在该楼层
    public boolean occupied8;//乘客8是否在该楼层
    public boolean occupied9;//乘客9是否在该楼层
    public boolean occupied0;//乘客0是否在该楼层
    public boolean moving;//乘客在楼层的移动
    public int destination;//目的地是几层
    public int elevator_door;//电梯门
    public int leave_elevator_door;//离开电梯门
    public int location;//位置
    public int xPos1;//
    public int xPos2;
    public int xPos3;
    public int xPos4;
    public int xPos5;
    public int xPos6;
    public int xPos7;
    public int xPos8;
    public int xPos9;
    public int xPos0;
    public int who;
    public int personDestination;//乘客的目的地
    private Elevator elevator;//电梯在大楼里
    private Thread thread;//线程
    private Image floor;//电梯图像
    private Image person1;//第1个乘客的图像
    private Image person2;//第2个乘客的图像
    private Image person3;//第3个乘客的图像
    private Image person4;//第4个乘客的图像
    private Image person5;//第5个乘客的图像
    private Image person6;//第6个乘客的图像
    private Image person7;//第7个乘客的图像
    private Image person8;//第8个乘客的图像
    private Image person9;//第9个乘客的图像
    private Image person0;//第10个乘客的图像
    private Applet applet;

    public Floor(Applet applet1, Elevator elevator1)
    {//初始化，10个乘客一开始都没在电梯里
        occupied1 = false;
        occupied2 = false;
        occupied3 = false;
        occupied4 = false;
        occupied5 = false;
        occupied6 = false;
        occupied7 = false;
        occupied8 = false;
        occupied9 = false;
        occupied0 = false;
        moving = false;
        elevator_door = 1;//进入电梯
        leave_elevator_door = 2;//离开电梯
        who = 1;//第一个乘客
        location = 1;//电梯位于第一层
        //十个乘客的起始位置
        xPos1 = -30;
        xPos2 = -30;
        xPos3 = -30;
        xPos4 = -30;
        xPos5 = -30;
        xPos6 = -30;
        xPos7 = -30;
        xPos8 = -30;
        xPos9 = -30;
        xPos0 = -30;
        stepsound = applet1.getAudioClip(applet1.getDocumentBase(), "sound/thin.bell.au");//电梯的声音
        person1 = applet1.getImage(applet1.getDocumentBase(), "image/girl1.gif");//获取乘客1的图像
        person2 = applet1.getImage(applet1.getDocumentBase(), "image/girl2.gif");//获取乘客2的图像
        person3 = applet1.getImage(applet1.getDocumentBase(), "image/girl3.gif");//获取乘客3的图像
        person4 = applet1.getImage(applet1.getDocumentBase(), "image/girl4.gif");//获取乘客4的图像
        person5 = applet1.getImage(applet1.getDocumentBase(), "image/girl5.gif");//获取乘客5的图像
        person6 = applet1.getImage(applet1.getDocumentBase(), "image/girl6.gif");//获取乘客6的图像
        person7 = applet1.getImage(applet1.getDocumentBase(), "image/girl7.gif");//获取乘客7的图像
        person8 = applet1.getImage(applet1.getDocumentBase(), "image/girl8.gif");//获取乘客8的图像
        person9 = applet1.getImage(applet1.getDocumentBase(), "image/girl9.gif");//获取乘客9的图像
        person0 = applet1.getImage(applet1.getDocumentBase(), "image/girl0.gif");//获取乘客10的图像
        floor = applet1.getImage(applet1.getDocumentBase(), "image/floor1.jpg");//获取楼层的图像
        elevator = elevator1;
        applet = applet1;
    }
//构造方法
    public void occupy1()
    {//乘客1在该楼层
        if(!occupied1)
        {
            occupied1 = true;
            repaint();
        }
    }
    public void occupy2()
    {
        if(!occupied2)
        {
            occupied2 = true;
            repaint();
        }
    }
    
    public void occupy3()
    {
        if(!occupied3)
        {
            occupied3 = true;
            repaint();
        }
    }
    public void occupy4()
    {
        if(!occupied4)
        {
            occupied4 = true;
            repaint();
        }
    }
    public void occupy5()
    {
        if(!occupied5)
        {
            occupied5 = true;
            repaint();
        }
    }
    public void occupy6()
    {
        if(!occupied6)
        {
            occupied6 = true;
            repaint();
        }
    }
    public void occupy7()
    {
        if(!occupied7)
        {
            occupied7 = true;
            repaint();
        }
    }
    public void occupy8()
    {
        if(!occupied8)
        {
            occupied8 = true;
            repaint();
        }
    }
    public void occupy9()
    {
        if(!occupied9)
        {
            occupied9 = true;
            repaint();
        }
    }
    public void occupy0()
    {
        if(!occupied0)
        {
            occupied0 = true;
            repaint();
        }
    }

    public void unoccupy1()
    {
        if(occupied1)
        {
            occupied1 = false;
            repaint();
        }
    }
    public void unOccupy2()
    {
        if(occupied2)
        {
            occupied2 = false;
            repaint();
        }
    }
    
    public void unOccupy3()
    {
        if(occupied3)
        {
            occupied3 = false;
            repaint();
        }
    }
    public void unOccupy4()
    {
        if(occupied4)
        {
            occupied4 = false;
            repaint();
        }
    }
    public void unOccupy5()
    {
        if(occupied5)
        {
            occupied5 = false;
            repaint();
        }
    }
    public void unOccupy6()
    {
        if(occupied6)
        {
            occupied6 = false;
            repaint();
        }
    }
    public void unOccupy7()
    {
        if(occupied7)
        {
            occupied7 = false;
            repaint();
        }
    }
    public void unOccupy8()
    {
        if(occupied8)
        {
            occupied8 = false;
            repaint();
        }
    }
    public void unOccupy9()
    {
        if(occupied9)
        {
            occupied9 = false;
            repaint();
        }
    }
    public void unOccupy0()
    {
        if(occupied0)
        {
            occupied0 = false;
            repaint();
        }
    }

    public void pause(int i)
    {
        try
        {
            Thread.sleep(i);
            return;
        }
        catch(InterruptedException interruptedexception)//例外处理
        {
            return;
        }
    }

    public void setDestination(int i,int w)
    {
        destination = i;//目的地是第i层
        who = w;//第w位乘客
        start();//调用启动方法
    }
//电梯开始运行
    public void start()
    {
        thread = new Thread(this);
        thread.start();
    }
//电梯停止运行
    public void stop()
    {
        if(thread != null)
        {
            thread.stop();
            thread = null;
        }
    }
//
    public void run()
    {
    	if(who==1)
    	{
        	if(destination == elevator_door)
        	{
        	moving = true;
            	while(xPos1 < 190) 
            	{
                	xPos1++;
                	repaint();
                	stepsound.play();
                	pause(20);
            	}
            	cleanUp();
        	}
        	if(destination == leave_elevator_door)
        	{
            		moving = true;
            		while(xPos1 > 1) 
            		{
                		xPos1--;
                		repaint();
                		stepsound.play();
                		pause(20);
            		}
            		cleanUp();
            		unoccupy1();
        	}
	}
	else if(who==2)
	{
		if(destination == elevator_door)
        	{
        	moving = true;
            	while(xPos2 < 180) 
            	{
                	xPos2++;
                	repaint();
                	stepsound.play();
                	pause(20);
            	}
            	cleanUp();
        	}
        	if(destination == leave_elevator_door)
        	{
            		moving = true;
            		while(xPos2 > 1) 
            		{
                		xPos2--;
                		repaint();
                		stepsound.play();
                		pause(20);
            		}
            		cleanUp();
            		unOccupy2();
        	}
	}
	else if(who==3)
	{
		if(destination == elevator_door)
        	{
        	moving = true;
            	while(xPos3 < 170) 
            	{
                	xPos3++;
                	repaint();
                	stepsound.play();
                	pause(20);
            	}
            	cleanUp();
        	}
        	if(destination == leave_elevator_door)
        	{
            		moving = true;
            		while(xPos3 > 1) 
            		{
                		xPos3--;
                		repaint();
                		stepsound.play();
                		pause(20);
            		}
            		cleanUp();
            		unOccupy3();
        	}
	}
	else if(who==4)
	{
		if(destination == elevator_door)
        	{
        	moving = true;
            	while(xPos4 < 160) 
            	{
                	xPos4++;
                	repaint();
                	stepsound.play();
                	pause(20);
            	}
            	cleanUp();
        	}
        	if(destination == leave_elevator_door)
        	{
            		moving = true;
            		while(xPos4 > 1) 
            		{
                		xPos4--;
                		repaint();
                		stepsound.play();
                		pause(20);
            		}
            		cleanUp();
            		unOccupy4();
        	}
	}
	else if(who==5)
	{
		if(destination == elevator_door)
        	{
        	moving = true;
            	while(xPos5 < 150) 
            	{
                	xPos5++;
                	repaint();
                	stepsound.play();
                	pause(20);
            	}
            	cleanUp();
        	}
        	if(destination == leave_elevator_door)
        	{
            		moving = true;
            		while(xPos5 > 1) 
            		{
                		xPos5--;
                		repaint();
                		stepsound.play();
                		pause(20);
            		}
            		cleanUp();
            		unOccupy5();
        	}
	}
	else if(who==6)
	{
		if(destination == elevator_door)
        	{
        	moving = true;
            	while(xPos6 < 140) 
            	{
                	xPos6++;
                	repaint();
                	stepsound.play();
                	pause(20);
            	}
            	cleanUp();
        	}
        	if(destination == leave_elevator_door)
        	{
            		moving = true;
            		while(xPos6 > 1) 
            		{
                		xPos6--;
                		repaint();
                		stepsound.play();
                		pause(20);
            		}
            		cleanUp();
            		unOccupy6();
        	}
	}
	else if(who==7)
	{
		if(destination == elevator_door)
        	{
        	moving = true;
            	while(xPos7 < 130) 
            	{
                	xPos7++;
                	repaint();
                	stepsound.play();
                	pause(20);
            	}
            	cleanUp();
        	}
        	if(destination == leave_elevator_door)
        	{
            		moving = true;
            		while(xPos7 > 1) 
            		{
                		xPos7--;
                		repaint();
                		stepsound.play();
                		pause(20);
            		}
            		cleanUp();
            		unOccupy7();
        	}
	}
	else if(who==8)
	{
		if(destination == elevator_door)
        	{
        	moving = true;
            	while(xPos8 < 120) 
            	{
                	xPos8++;
                	repaint();
                	stepsound.play();
                	pause(20);
            	}
            	cleanUp();
        	}
        	if(destination == leave_elevator_door)
        	{
            		moving = true;
            		while(xPos8 > 1) 
            		{
                		xPos8--;
                		repaint();
                		stepsound.play();
                		pause(20);
            		}
            		cleanUp();
            		unOccupy8();
        	}
	}
	else if(who==9)
	{
		if(destination == elevator_door)
        	{
        	moving = true;
            	while(xPos9 < 110) 
            	{
                	xPos9++;
                	repaint();
                	stepsound.play();
                	pause(20);
            	}
            	cleanUp();
        	}
        	if(destination == leave_elevator_door)
        	{
            		moving = true;
            		while(xPos9 > 1) 
            		{
                		xPos9--;
                		repaint();
                		stepsound.play();
                		pause(20);
            		}
            		cleanUp();
            		unOccupy9();
        	}
	}
	else if(who==0)
	{
		if(destination == elevator_door)
        	{
        	moving = true;
            	while(xPos0 < 100) 
            	{
                	xPos0++;
                	repaint();
                	stepsound.play();
                	pause(20);
            	}
            	cleanUp();
        	}
        	if(destination == leave_elevator_door)
        	{
            		moving = true;
            		while(xPos0 > 1) 
            		{
                		xPos0--;
                		repaint();
                		stepsound.play();
                		pause(20);
            		}
            		cleanUp();
            		unOccupy0();
        	}
	}
    }

    public void cleanUp()
    {
        moving = false;
        destination = 0;
    }

    public void paint(Graphics g)
    {
        g.drawImage(floor, 1, 1, this);
        if(occupied1)
        {
            g.drawImage(person1, xPos1, 40, this);
        }
        if(occupied2)
        {
            g.drawImage(person2, xPos2, 40, this);
        }
        if(occupied3)
        {
            g.drawImage(person3, xPos3, 40, this);
        }
        if(occupied4)
        {
            g.drawImage(person4, xPos4, 40, this);
        }
        if(occupied5)
        {
            g.drawImage(person5, xPos5, 40, this);
        }
        if(occupied6)
        {
            g.drawImage(person6, xPos6, 40, this);
        }
        if(occupied7)
        {
            g.drawImage(person7, xPos7, 40, this);
        }
        if(occupied8)
        {
            g.drawImage(person8, xPos8, 40, this);
        }
        if(occupied9)
        {
            g.drawImage(person9, xPos9, 40, this);
        }
        if(occupied0)
        {
            g.drawImage(person0, xPos0, 40, this);
        }
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}
