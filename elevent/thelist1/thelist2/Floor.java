package thelist2;
/*�ļ�����Floor.java
  ������Floor.class
  ���ܣ�����¥��Ĳ��֣��Լ��˿�С����¥�����ƶ�
*/
import java.awt.*;
import java.applet.Applet;
import java.applet.*;
//Floor �ඨ��
public class Floor extends Applet
    implements Runnable
{
//����Floor���Ա��������
    public AudioClip stepsound;//�˿���·������
    public boolean occupied1;//�˿�1�Ƿ��ڸ�¥��
    public boolean occupied2;//�˿�2�Ƿ��ڸ�¥��
    public boolean occupied3;//�˿�3�Ƿ��ڸ�¥��
    public boolean occupied4;//�˿�4���ڷ��¥��
    public boolean occupied5;//�˿�5�Ƿ��ڸ�¥��
    public boolean occupied6;//�˿�6�Ƿ��ڸ�¥��
    public boolean occupied7;//�˿�7�Ƿ��ڸ�¥��
    public boolean occupied8;//�˿�8�Ƿ��ڸ�¥��
    public boolean occupied9;//�˿�9�Ƿ��ڸ�¥��
    public boolean occupied0;//�˿�0�Ƿ��ڸ�¥��
    public boolean moving;//�˿���¥����ƶ�
    public int destination;//Ŀ�ĵ��Ǽ���
    public int elevator_door;//������
    public int leave_elevator_door;//�뿪������
    public int location;//λ��
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
    public int personDestination;//�˿͵�Ŀ�ĵ�
    private Elevator elevator;//�����ڴ�¥��
    private Thread thread;//�߳�
    private Image floor;//����ͼ��
    private Image person1;//��1���˿͵�ͼ��
    private Image person2;//��2���˿͵�ͼ��
    private Image person3;//��3���˿͵�ͼ��
    private Image person4;//��4���˿͵�ͼ��
    private Image person5;//��5���˿͵�ͼ��
    private Image person6;//��6���˿͵�ͼ��
    private Image person7;//��7���˿͵�ͼ��
    private Image person8;//��8���˿͵�ͼ��
    private Image person9;//��9���˿͵�ͼ��
    private Image person0;//��10���˿͵�ͼ��
    private Applet applet;

    public Floor(Applet applet1, Elevator elevator1)
    {//��ʼ����10���˿�һ��ʼ��û�ڵ�����
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
        elevator_door = 1;//�������
        leave_elevator_door = 2;//�뿪����
        who = 1;//��һ���˿�
        location = 1;//����λ�ڵ�һ��
        //ʮ���˿͵���ʼλ��
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
        stepsound = applet1.getAudioClip(applet1.getDocumentBase(), "sound/thin.bell.au");//���ݵ�����
        person1 = applet1.getImage(applet1.getDocumentBase(), "image/girl1.gif");//��ȡ�˿�1��ͼ��
        person2 = applet1.getImage(applet1.getDocumentBase(), "image/girl2.gif");//��ȡ�˿�2��ͼ��
        person3 = applet1.getImage(applet1.getDocumentBase(), "image/girl3.gif");//��ȡ�˿�3��ͼ��
        person4 = applet1.getImage(applet1.getDocumentBase(), "image/girl4.gif");//��ȡ�˿�4��ͼ��
        person5 = applet1.getImage(applet1.getDocumentBase(), "image/girl5.gif");//��ȡ�˿�5��ͼ��
        person6 = applet1.getImage(applet1.getDocumentBase(), "image/girl6.gif");//��ȡ�˿�6��ͼ��
        person7 = applet1.getImage(applet1.getDocumentBase(), "image/girl7.gif");//��ȡ�˿�7��ͼ��
        person8 = applet1.getImage(applet1.getDocumentBase(), "image/girl8.gif");//��ȡ�˿�8��ͼ��
        person9 = applet1.getImage(applet1.getDocumentBase(), "image/girl9.gif");//��ȡ�˿�9��ͼ��
        person0 = applet1.getImage(applet1.getDocumentBase(), "image/girl0.gif");//��ȡ�˿�10��ͼ��
        floor = applet1.getImage(applet1.getDocumentBase(), "image/floor1.jpg");//��ȡ¥���ͼ��
        elevator = elevator1;
        applet = applet1;
    }
//���췽��
    public void occupy1()
    {//�˿�1�ڸ�¥��
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
        catch(InterruptedException interruptedexception)//���⴦��
        {
            return;
        }
    }

    public void setDestination(int i,int w)
    {
        destination = i;//Ŀ�ĵ��ǵ�i��
        who = w;//��wλ�˿�
        start();//������������
    }
//���ݿ�ʼ����
    public void start()
    {
        thread = new Thread(this);
        thread.start();
    }
//����ֹͣ����
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
