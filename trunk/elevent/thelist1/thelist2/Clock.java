package thelist2;


/*文件名：Clock.java
   类名：Clock.class
   功能：生成钟的布局，以及钟的指针的运动
*/
import java.awt.*;
import java.applet.Applet;
import java.applet.*;
//声明钟类
public class Clock extends Canvas
{//声明钟类的各个成员变量

    private int lastx;
    private int lasty;
    private int x;
    private int y;
    private int centerx;
    private int centery;
    private int margin;
    private int r;
    private int handLength;
    private int initx;
    private int inity;
    public int step;
    public float interval;//指针一次走多少度
    public Color handColor;//指针的颜色
//钟类的构造方法
    public Clock(int i, int j, int k, int l)
    {
        step = 0;
        interval = 3.6F;
        margin = 5;
        centerx = i + k / 2;
        centery = j + l / 2;
        r = k / 2 - margin;
        handLength = r - margin;
        x = i + k / 2;
        y = margin + 4;
        initx = x;
        inity = y;
        lastx = x;
        lasty = y;
        handColor = Color.blue;
        setBackground(Color.cyan);
        resize(k, l);
    }

    public void cleanup()
    {
        step = 0;
        lastx = x;
        lasty = y;
        x = initx;
        y = inity;
        repaint();
    }

    public void addtime()
    {
        lastx = x;
        lasty = y;
        step++;
        x = (int)(Math.cos(((float)step * interval * 3.14F) / 180F - 1.57F) * (double)handLength + (double)centerx);
        y = (int)(Math.sin(((float)step * interval * 3.14F) / 180F - 1.57F) * (double)handLength + (double)centerx);
        repaint();
    }
    //circle 方法用来画圆
    private void circle(int i, int j, int k, Graphics g)
    {
        int l = 2 * k;
        int i1 = 2 * k;
        int j1 = 0;
        char c = '\u0168';
        g.drawArc(margin, margin, l, i1, j1, c);
    }

    public void paint(Graphics g)
    {//必要时擦掉旧的线
        if(lastx != x || lasty != y)
        {
            g.setColor(getBackground());
            g.drawLine(centerx, centery, lastx, lasty);
        }
        g.setColor(handColor);//设置颜色
        circle(centerx, centery, r, g);
        g.drawLine(centerx, centery, x, y);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
//用于延时的方法
    public void pause(int i)
    {
        try
        {
            Thread.sleep(i);
        }
        catch(InterruptedException interruptedexception) { }
    }
}
