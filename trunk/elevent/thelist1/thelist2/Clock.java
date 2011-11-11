package thelist2;


/*�ļ�����Clock.java
   ������Clock.class
   ���ܣ������ӵĲ��֣��Լ��ӵ�ָ����˶�
*/
import java.awt.*;
import java.applet.Applet;
import java.applet.*;
//��������
public class Clock extends Canvas
{//��������ĸ�����Ա����

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
    public float interval;//ָ��һ���߶��ٶ�
    public Color handColor;//ָ�����ɫ
//����Ĺ��췽��
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
    //circle ����������Բ
    private void circle(int i, int j, int k, Graphics g)
    {
        int l = 2 * k;
        int i1 = 2 * k;
        int j1 = 0;
        char c = '\u0168';
        g.drawArc(margin, margin, l, i1, j1, c);
    }

    public void paint(Graphics g)
    {//��Ҫʱ�����ɵ���
        if(lastx != x || lasty != y)
        {
            g.setColor(getBackground());
            g.drawLine(centerx, centery, lastx, lasty);
        }
        g.setColor(handColor);//������ɫ
        circle(centerx, centery, r, g);
        g.drawLine(centerx, centery, x, y);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
//������ʱ�ķ���
    public void pause(int i)
    {
        try
        {
            Thread.sleep(i);
        }
        catch(InterruptedException interruptedexception) { }
    }
}
