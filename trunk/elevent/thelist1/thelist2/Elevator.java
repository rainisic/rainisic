package thelist2;
/*文件名：Elevator.java
  类名：Elevator.class
  功能：生成电梯的布局，以及电梯在楼层之间的运动
*/
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.applet.Applet;
import java.applet.*;
//声明变量
public class Elevator extends Applet
    implements Runnable
{

    public AudioClip arriving;
    public AudioClip music;
    public int yPos;
    public int destination;
    public int location;
    public boolean occupied1;
    public boolean occupied2;
    public boolean occupied3;
    public boolean occupied4;
    public boolean occupied5;
    public boolean occupied6;
    public boolean occupied7;
    public boolean occupied8;
    public boolean occupied9;
    public boolean occupied0;
    public boolean moving;
    private Image elevator;
    private Image person1;
    private Image person2;
    private Image person3;
    private Image person4;
    private Image person5;
    private Image person6;
    private Image person7;
    private Image person8;
    private Image person9;
    private Image person0;
    private Clock time;
    private Thread thread;
    private ElevatorMgr EM;
    //电梯类的构造方法，变量的初始化
    public Elevator(Applet applet, Clock clock, ElevatorMgr elevatormgr)
    {
        yPos = 202;
        location = 1;
        occupied1 = false;
        occupied2 = false;
        moving = false;
        arriving = applet.getAudioClip(applet.getDocumentBase(), "sound/ding.au");
        music = applet.getAudioClip(applet.getDocumentBase(), "sound/spacemusic.au");
        elevator = applet.getImage(applet.getDocumentBase(), "image/elevator1.jpg");
        person1 = applet.getImage(applet.getDocumentBase(), "image/girl1.gif");
        person2 = applet.getImage(applet.getDocumentBase(), "image/girl2.gif");
        person3 = applet.getImage(applet.getDocumentBase(), "image/girl3.gif");
        person4 = applet.getImage(applet.getDocumentBase(), "image/girl4.gif");
        person5 = applet.getImage(applet.getDocumentBase(), "image/girl5.gif");
        person6 = applet.getImage(applet.getDocumentBase(), "image/girl6.gif");
        person7 = applet.getImage(applet.getDocumentBase(), "image/girl7.gif");
        person8 = applet.getImage(applet.getDocumentBase(), "image/girl8.gif");
        person9 = applet.getImage(applet.getDocumentBase(), "image/girl9.gif");
        person0 = applet.getImage(applet.getDocumentBase(), "image/girl0.gif");
        time = clock;
        EM = elevatormgr;
        setBackground(Color.white);
    }

    public void occupy1()
    {
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
        catch(InterruptedException interruptedexception)
        {
            return;
        }
    }

    public void setDestination(int i)
    {
        if(i != destination)
        {
            destination = i;
            thread = null;
            start();
        }
    }

    public void start()
    {
        thread = null;
        thread = new Thread(this);
        thread.start();
    }

    public void stop()
    {
        if(thread != null)
        {
            thread.stop();
            thread = null;
        }
    }

    public void run()
    {
        if(destination == 3)
        {
            location = destination;
            moving = true;
            music.play();
            EM.setControlBoardUpOn();
            while(yPos > 1) 
            {
                yPos--;
                repaint();
                time.addtime();
                pause(30);
                if(yPos == 5)
                {
                    music.stop();
                }
                if(yPos == 1)
                {
                    arriving.play();
                }
            }
            EM.setControlBoardUpOff();
            cleanUp();
        }
        if(destination == 1)
        {
            location = destination;
            moving = true;
            music.play();
            EM.setControlBoardDownOn();
            while(yPos < 202) 
            {
                yPos++;
                repaint();
                time.addtime();
                pause(30);
                if(yPos == 198)
                {
                    music.stop();
                }
                if(yPos == 102)
                {
                    arriving.play();
                }
            }
            EM.setControlBoardDownOff();
            cleanUp();
        }
        if(destination == 2)
        {
            moving = true;
            music.play();
            EM.setControlBoardDownOn();
            if(location==3)
            {
            	while(yPos < 102) 
            	{
                	yPos++;
                	repaint();
                	time.addtime();
                	pause(30);
                	if(yPos == 100)
                	{
                    	music.stop();
                	}
                	if(yPos == 102)
                	{
                    	arriving.play();
                	}
            	}
            }
            else if(location==1)
            {
            	while(yPos > 102) 
            	{
                	yPos--;
                	repaint();
                	time.addtime();
                	pause(30);
                	if(yPos == 104)
                	{
                    	music.stop();
                	}
                	if(yPos == 102)
                	{
                    	arriving.play();
                	}
            	}
            }
            
            location = destination;
            EM.setControlBoardDownOff();
            cleanUp();
        }
    }

    public void cleanUp()
    {
        moving = false;
        destination = 0;
        time.cleanup();
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.orange);
        g.setFont(new Font("TimesRoman", 1, 48));
        g.drawString("1", 21, 245);
        g.drawString("2", 21, 145);
        g.drawString("3", 21, 45);
        g.drawImage(elevator, 1, yPos, this);
        if(occupied1)
        {
            g.drawImage(person1, 60, yPos + 45, this);
        }
        if(occupied2)
        {
            g.drawImage(person2, 54, yPos + 45, this);
        }
        if(occupied3)
        {
            g.drawImage(person3, 47, yPos + 45, this);
        }
        if(occupied4)
        {
            g.drawImage(person4, 41, yPos + 45, this);
        }
        if(occupied5)
        {
            g.drawImage(person5, 34, yPos + 45, this);
        }
        if(occupied6)
        {
            g.drawImage(person6, 28, yPos + 45, this);
        }
        if(occupied7)
        {
            g.drawImage(person7, 21, yPos + 45, this);
        }
        if(occupied8)
        {
            g.drawImage(person8, 15, yPos + 45, this);
        }
        if(occupied9)
        {
            g.drawImage(person9, 8, yPos + 45, this);
        }
        if(occupied0)
        {
            g.drawImage(person0, 2, yPos + 45, this);
        }
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}