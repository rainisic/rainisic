package thelist2;
/*文件名：ControlBoard.java
   类名：ControlBoard.class
   功能：生成控制面板的布局
*/
import java.awt.*;
import java.applet.Applet;
import java.applet.*;

public class ControlBoard extends Applet
{

    private Image imageFloorButtonUnpressed;
    private Image imageFloorButtonPressed;
    private Image imageFloorButtonLit;
    private Image imageUpOff;
    private Image imageUpOn;
    private Image imageDownOff;
    private Image imageDownOn;
    public boolean floor1ButtonPressed;
    public boolean floor2ButtonPressed;
    public boolean floor3ButtonPressed;
    public boolean floor1ButtonLit;
    public boolean floor2ButtonLit;
    public boolean floor3ButtonLit;
    public boolean upOn;
    public boolean downOn;
    private Applet applet;
   //控制面板的构造
    public ControlBoard(Applet applet1)
    {
        floor1ButtonPressed = false;
        floor2ButtonPressed = false;
        floor1ButtonLit = false;
        floor2ButtonLit = false;
        upOn = false;
        downOn = false;
        imageFloorButtonUnpressed = applet1.getImage(applet1.getDocumentBase(), "image/floorButtonUnpressed.png");
        imageFloorButtonPressed = applet1.getImage(applet1.getDocumentBase(), "image/floorButtonPressed.png");
        imageFloorButtonLit = applet1.getImage(applet1.getDocumentBase(), "image/floorButtonLit.png");
        imageUpOff = applet1.getImage(applet1.getDocumentBase(), "image/up.png");
        imageDownOff = applet1.getImage(applet1.getDocumentBase(), "image/down.png");
        imageUpOn = applet1.getImage(applet1.getDocumentBase(), "image/upOn.png");
        imageDownOn = applet1.getImage(applet1.getDocumentBase(), "image/downOn.png");
        setBackground(Color.darkGray);
        applet = applet1;
    }

    public void setFloor1ButtonPressed()
    {
        floor1ButtonPressed = true;
        repaint();
    }

    public void setFloor2ButtonPressed()
    {
        floor2ButtonPressed = true;
        repaint();
    }

    public void setFloor3ButtonPressed()
    {
        floor3ButtonPressed = true;
        repaint();
    }

    public void setFloor1ButtonUnpressed()
    {
        floor1ButtonPressed = false;
        repaint();
    }

    public void setFloor2ButtonUnpressed()
    {
        floor2ButtonPressed = false;
        repaint();
    }

    public void setFloor3ButtonUnpressed()
    {
        floor3ButtonPressed = false;
        repaint();
    }

    public void setFloor1ButtonLit()
    {
        floor1ButtonLit = true;
        repaint();
    }

    public void setFloor2ButtonLit()
    {
        floor2ButtonLit = true;
        repaint();
    }

    public void setFloor3ButtonLit()
    {
        floor2ButtonLit = true;
        repaint();
    }

    public void setUpOn()
    {
        upOn = true;
        repaint();
    }

    public void setUpOff()
    {
        upOn = false;
        repaint();
    }

    public void setDownOn()
    {
        downOn = true;
        repaint();
    }

    public void setDownOff()
    {
        downOn = false;
        repaint();
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.red);
        g.drawLine(0, 67, 120, 67);
        g.drawLine(0, 133, 120, 133);
        g.setColor(Color.lightGray);
        g.fillRect(17, 69, 31, 60);
        if(floor1ButtonPressed)
        {
            g.drawImage(imageFloorButtonPressed, 20, 20, this);
        } else
        if(floor1ButtonLit)
        {
            g.drawImage(imageFloorButtonLit, 20, 20, this);
        } else
        {
            g.drawImage(imageFloorButtonUnpressed, 20, 20, this);
        }
        if(floor2ButtonPressed)
        {
            g.drawImage(imageFloorButtonPressed, 20, 153, this);
        } else
        if(floor1ButtonLit)
        {
            g.drawImage(imageFloorButtonLit, 20, 153, this);
        } else
        {
            g.drawImage(imageFloorButtonUnpressed, 20, 153, this);
        }

        if(floor3ButtonPressed)
        {
            g.drawImage(imageFloorButtonPressed, 20, 240, this);
        } else
        if(floor3ButtonLit)
        {
            g.drawImage(imageFloorButtonLit, 20, 240, this);
        } else
        {
            g.drawImage(imageFloorButtonUnpressed, 20, 240, this);
        }

        if(upOn)
        {
            g.drawImage(imageUpOn, 20, 70, this);
        } else
        {
            g.drawImage(imageUpOff, 20, 70, this);
        }
        if(downOn)
        {
            g.drawImage(imageDownOn, 20, 100, this);
        } else
        {
            g.drawImage(imageDownOff, 20, 100, this);
        }
    }
}
