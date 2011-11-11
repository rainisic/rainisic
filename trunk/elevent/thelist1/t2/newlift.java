package t2;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
public class newlift extends java.applet.Applet implements ActionListener,Runnable{
    Panel Floor_Panel=new Panel();
    Panel Elevator_Panel=new Panel();
    Panel Indication_Panel=new Panel();
    Panel Floor1_Panel=new Panel();
    Panel Floor2_Panel=new Panel();
    Panel Floor3_Panel=new Panel();
    Panel Floor4_Panel=new Panel();
    Panel Floor5_Panel=new Panel();
    Panel Floor6_Panel=new Panel();
    Label FloorLabel1=new Label("F1");
    Label FloorLabel2=new Label("F2");
    Label FloorLabel3=new Label("F3");
    Label FloorLabel4=new Label("F4");
    Label FloorLabel5=new Label("F5");
    Label FloorLabel6=new Label("F6");
    FloorButton UpButton1=new FloorButton(1,true,"∧");
    FloorButton DnButton1=new FloorButton(1,false,"∨");
    FloorButton UpButton2=new FloorButton(2,true,"∧");
    FloorButton DnButton2=new FloorButton(2,false,"∨");
    FloorButton UpButton3=new FloorButton(3,true,"∧");
    FloorButton DnButton3=new FloorButton(3,false,"∨");
    FloorButton UpButton4=new FloorButton(4,true,"∧");
    FloorButton DnButton4=new FloorButton(4,false,"∨");
    FloorButton UpButton5=new FloorButton(5,true,"∧");
    FloorButton DnButton5=new FloorButton(5,false,"∨");
    FloorButton UpButton6=new FloorButton(6,true,"∧");
    FloorButton DnButton6=new FloorButton(6,false,"∨");
    Panel SubInd_Panel1=new Panel();
    Panel SubInd_Panel2=new Panel();
    Panel SubInd_Panel3=new Panel();
    Button Start_Button=new Button("启动电梯");
    Button Stop_Button=new Button("关闭电梯");
    Label Indication_Prompt=new Label("电梯所在层");
    Label State_Prompt=new Label("电梯状态");
    Label Indication_Light=new Label("1层");
    Label State_Light=new Label("停止");
    Label ETitleLabel=new Label("电梯内楼层按钮板");
    Label EastLabel=new Label();
    Label WestLabel=new Label();
    Label SouthLabel=new Label();
    DesFlButton DesFl_Button1=new DesFlButton(1,"1");
    DesFlButton DesFl_Button2=new DesFlButton(2,"2");
    DesFlButton DesFl_Button3=new DesFlButton(3,"3");
    DesFlButton DesFl_Button4=new DesFlButton(4,"4");
    DesFlButton DesFl_Button5=new DesFlButton(5,"5");
    DesFlButton DesFl_Button6=new DesFlButton(6,"6");
    Image Elevator_Image;
    TUpQueue EUpQueue=new TUpQueue();
    TDnQueue EDnQueue=new TDnQueue();
    Elevator theElevator=new Elevator(0,1,true);
    Thread t2=new Thread(this,"t2");
    int ElePositionPix=366;
    boolean isSuspend=true;
    public void init() {
        GridBagLayout gbl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        setLayout(gbl);
        gbc.gridx=0;gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.WEST;
        gbc.weightx=1;gbc.weighty=1;
        gbc.insets=new Insets(0,0,0,0);
        gbl.setConstraints(Floor_Panel,gbc);
        add(Floor_Panel);
        gbc.gridx=1;gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.NORTHWEST;
        gbc.weightx=1;gbc.weighty=1;
        gbc.insets=new Insets(0,0,0,0);
        gbl.setConstraints(Elevator_Panel,gbc);
        add(Elevator_Panel);
        gbc.gridx=2;gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.weightx=1;gbc.weighty=1;
        gbc.insets=new Insets(0,0,0,0);
        gbl.setConstraints(Indication_Panel,gbc);
        add(Indication_Panel);
        UpButton1.addActionListener(this);
        UpButton2.addActionListener(this);
        UpButton3.addActionListener(this);
        UpButton4.addActionListener(this);
        UpButton5.addActionListener(this);
        DnButton2.addActionListener(this);
        DnButton3.addActionListener(this);
        DnButton4.addActionListener(this);
        DnButton5.addActionListener(this);
        DnButton6.addActionListener(this);
        DesFl_Button1.addActionListener(this);
        DesFl_Button2.addActionListener(this);
        DesFl_Button3.addActionListener(this);
        DesFl_Button4.addActionListener(this);
        DesFl_Button5.addActionListener(this);
        DesFl_Button6.addActionListener(this);
        Floor_Panel.setLayout(new GridLayout(6,1));
        Floor6_Panel.setBackground(new Color(50,220,220));
        Floor5_Panel.setBackground(new Color(50,190,190));
        Floor4_Panel.setBackground(new Color(50,160,160));
        Floor3_Panel.setBackground(new Color(50,130,130));
        Floor2_Panel.setBackground(new Color(50,100,100));
        Floor1_Panel.setBackground(new Color(50,70,70));
        Floor_Panel.add(Floor6_Panel);
        Floor_Panel.add(Floor5_Panel);
        Floor_Panel.add(Floor4_Panel);
        Floor_Panel.add(Floor3_Panel);
        Floor_Panel.add(Floor2_Panel);
        Floor_Panel.add(Floor1_Panel);
        Floor1_Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        FloorLabel1.setForeground(new Color(255,0,0));
        FloorLabel1.setFont(new Font("TimesRoman",Font.BOLD,18));
        UpButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UpButton1.setFont(new Font("TimesRoman",Font.BOLD,10));
        DnButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DnButton1.setFont(new Font("TimesRoman",Font.BOLD,10));
        DnButton1.setEnabled(false);
        Floor1_Panel.add(FloorLabel1);
        Floor1_Panel.add(UpButton1);
        Floor1_Panel.add(DnButton1);
        Floor2_Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        FloorLabel2.setForeground(new Color(255,0,0));
        FloorLabel2.setFont(new Font("TimesRoman",Font.BOLD,18));
        UpButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UpButton2.setFont(new Font("TimesRoman",Font.BOLD,10));
        DnButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DnButton2.setFont(new Font("TimesRoman",Font.BOLD,10));
        Floor2_Panel.add(FloorLabel2);
        Floor2_Panel.add(UpButton2);
        Floor2_Panel.add(DnButton2);
        Floor3_Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        FloorLabel3.setForeground(new Color(255,0,0));
        FloorLabel3.setFont(new Font("TimesRoman",Font.BOLD,18));
        UpButton3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UpButton3.setFont(new Font("TimesRoman",Font.BOLD,10));
        DnButton3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DnButton3.setFont(new Font("TimesRoman",Font.BOLD,10));
        Floor3_Panel.add(FloorLabel3);
        Floor3_Panel.add(UpButton3);
        Floor3_Panel.add(DnButton3);
        Floor4_Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        FloorLabel4.setForeground(new Color(255,0,0));
        FloorLabel4.setFont(new Font("TimesRoman",Font.BOLD,18));
        UpButton4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UpButton4.setFont(new Font("TimesRoman",Font.BOLD,10));
        DnButton4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DnButton4.setFont(new Font("TimesRoman",Font.BOLD,10));
        Floor4_Panel.add(FloorLabel4);
        Floor4_Panel.add(UpButton4);
        Floor4_Panel.add(DnButton4);
        Floor5_Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        FloorLabel5.setForeground(new Color(255,0,0));
        FloorLabel5.setFont(new Font("TimesRoman",Font.BOLD,18));
        UpButton5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UpButton5.setFont(new Font("TimesRoman",Font.BOLD,10));
        DnButton5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DnButton5.setFont(new Font("TimesRoman",Font.BOLD,10));
        Floor5_Panel.add(FloorLabel5);
        Floor5_Panel.add(UpButton5);
        Floor5_Panel.add(DnButton5);
        Floor6_Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        FloorLabel6.setForeground(new Color(255,0,0));
        FloorLabel6.setFont(new Font("TimesRoman",Font.BOLD,18));
        UpButton6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UpButton6.setFont(new Font("TimesRoman",Font.BOLD,10));
        DnButton6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DnButton6.setFont(new Font("TimesRoman",Font.BOLD,10));
        Floor6_Panel.add(FloorLabel6);
        Floor6_Panel.add(UpButton6);
        Floor6_Panel.add(DnButton6);
        Indication_Panel.setLayout(new GridLayout(2,1));
        SubInd_Panel1.setBackground(new Color(150,170,170));
        SubInd_Panel2.setBackground(new Color(180,200,200));
        Indication_Panel.add(SubInd_Panel1);
        Indication_Panel.add(SubInd_Panel2);
        SubInd_Panel1.setLayout(new GridLayout(6,1,5,5));
        Indication_Prompt.setAlignment(1);
        Indication_Light.setAlignment(1);
        Indication_Light.setForeground(new Color(255,0,0));
        Indication_Light.setFont(new Font("TimesRoman",Font.BOLD,18));
        State_Prompt.setAlignment(1);
        State_Light.setAlignment(1);
        State_Light.setForeground(new Color(255,0,0));
        State_Light.setFont(new Font("TimesRoman",Font.BOLD,18));
        SubInd_Panel1.add(Start_Button);
        SubInd_Panel1.add(Stop_Button);
        SubInd_Panel1.add(Indication_Prompt);
        SubInd_Panel1.add(Indication_Light);
        SubInd_Panel1.add(State_Prompt);
        SubInd_Panel1.add(State_Light);
        SubInd_Panel2.setLayout(new BorderLayout(5,10));
        ETitleLabel.setAlignment(1);
        SubInd_Panel2.add("North",ETitleLabel);
        SubInd_Panel2.add("Center",SubInd_Panel3);
        SubInd_Panel2.add("East",EastLabel);
        SubInd_Panel2.add("West",WestLabel);
        SubInd_Panel2.add("South",SouthLabel);
        SubInd_Panel3.setLayout(new GridLayout(2,3,5,10));
        SubInd_Panel3.add(DesFl_Button1);
        SubInd_Panel3.add(DesFl_Button2);
        SubInd_Panel3.add(DesFl_Button3);
        SubInd_Panel3.add(DesFl_Button4);
        SubInd_Panel3.add(DesFl_Button5);
        SubInd_Panel3.add(DesFl_Button6);
        Start_Button.setBackground(Color.red);
        Start_Button.setEnabled(false);
        Elevator_Image=getImage(getDocumentBase(),"Img02.gif");
        
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() instanceof FloorButton)
        {
            FloorButton vfb=(FloorButton)(e.getSource());
            if(vfb.IsUp)
                if((theElevator.getPosition()>=vfb.Floor)&&(theElevator.getState()==1))
                    EUpQueue.InsertData(vfb.Floor,false);
                else
                    EUpQueue.InsertData(vfb.Floor,true);
            else
                
                if((theElevator.getPosition()<=vfb.Floor)&&(theElevator.getState()==-1))  
                    EDnQueue.InsertData(vfb.Floor,false);
                else
                    EDnQueue.InsertData(vfb.Floor,true);
            vfb.setBackground(Color.red);
                    
        }
        else if(e.getSource() instanceof DesFlButton)
        {
            DesFlButton vfb=(DesFlButton)(e.getSource());
            if(vfb.Floor>theElevator.Position)
                EUpQueue.InsertData(vfb.Floor,true);
            else if(vfb.Floor<theElevator.Position)
                EDnQueue.InsertData(vfb.Floor,true);
            else
                if(vfb.Floor==theElevator.Position)
                {
                    if(theElevator.getState()==-1)
                        EUpQueue.InsertData(vfb.Floor,true);
                }
                vfb.setBackground(Color.red);
        }
        else if(e.getSource()==Start_Button)
        {
            Start_Button.setBackground(Color.red);
            Start_Button.setEnabled(false);
            Stop_Button.setBackground(Color.lightGray);
            Start_Button.setEnabled(true);
            resume();
            
        }
        else if(e.getSource()==Stop_Button)
        {
            Start_Button.setBackground(Color.lightGray);
            
            Stop_Button.setBackground(Color.red);
            Start_Button.setEnabled(false); 
            suspend1();
        }
    }
    public void start()
    {
        isSuspend=false;
        t2.start();
    }
    public void suspend1()
    {
        isSuspend=true;
        EUpQueue.ClearQueue();
       EDnQueue.ClearQueue();
        ResetState();
        if((366-ElePositionPix)!=0)
            EUpQueue.InsertData(1,true);
        else
        {
            Start_Button.setEnabled(true);
            suspend();
        }
    }
    public void suspend()
    {
        try{
            t2.suspend();
        }catch(SecurityException e){}
    }
    public void resume()
    {
        isSuspend=false;
        EnabledState();
        t2.resume();
    }
    public void stop()
    {
        if(t2.isAlive())
            t2.stop();
        
    }
    void ResetState()
    {
        UpButton1.setBackground(Color.lightGray);
        UpButton2.setBackground(Color.lightGray);
        UpButton3.setBackground(Color.lightGray);
        UpButton4.setBackground(Color.lightGray);
        UpButton5.setBackground(Color.lightGray);
        DnButton2.setBackground(Color.lightGray);
        DnButton3.setBackground(Color.lightGray);
        DnButton4.setBackground(Color.lightGray);
        DnButton5.setBackground(Color.lightGray);
        DnButton6.setBackground(Color.lightGray);
        DesFl_Button1.setBackground(Color.lightGray);
        DesFl_Button2.setBackground(Color.lightGray);
        DesFl_Button3.setBackground(Color.lightGray);
        DesFl_Button4.setBackground(Color.lightGray);
        DesFl_Button5.setBackground(Color.lightGray);
        DesFl_Button6.setBackground(Color.lightGray);
        UpButton1.setEnabled(false);
        UpButton2.setEnabled(false);
        UpButton3.setEnabled(false);
        UpButton4.setEnabled(false);
        UpButton5.setEnabled(false);
        DnButton2.setEnabled(false);
        DnButton3.setEnabled(false);
        DnButton4.setEnabled(false);
        DnButton5.setEnabled(false);
        DnButton6.setEnabled(false);
        DesFl_Button1.setEnabled(false);
        DesFl_Button2.setEnabled(false);
        DesFl_Button3.setEnabled(false);
        DesFl_Button4.setEnabled(false);
        DesFl_Button5.setEnabled(false);
        DesFl_Button6.setEnabled(false);
        
    }
    void EnabledState()
    {
        
        UpButton1.setEnabled(true);
        UpButton2.setEnabled(true);
        UpButton3.setEnabled(true);
        UpButton4.setEnabled(true);
        UpButton5.setEnabled(true);
        DnButton2.setEnabled(true);
        DnButton3.setEnabled(true);
        DnButton4.setEnabled(true);
        DnButton5.setEnabled(true);
        DnButton6.setEnabled(true);
        DesFl_Button1.setEnabled(true);
        DesFl_Button2.setEnabled(true);
        DesFl_Button3.setEnabled(true);
        DesFl_Button4.setEnabled(true);
        DesFl_Button5.setEnabled(true);
        DesFl_Button6.setEnabled(true);
        
    }
    public void run()
    {
        String ThreadName;
        while(true)
        {
            try{
                Thread.currentThread().sleep(500);
            }catch(InterruptedException e){}
            ThreadName=Thread.currentThread().getName();
            if(ThreadName.equals("t2"))
            {
                if(theElevator.getState()==1)
                {
                    if((theElevator.getDesFloor()>EUpQueue.GetData(0))&&(theElevator.getPosition()<EUpQueue.GetData(0)))
                       theElevator.setDesFloor(EUpQueue.GetData(0));
                    ElePositionPix=ElePositionPix-2;
                    if(((432-ElePositionPix)%66)==0)
                    {
                        theElevator.setposition((432-ElePositionPix)/66);
                    }
                    Indication_Light.setText(Integer.toString((432-ElePositionPix)/66)+"层");
                    repaint();
                }
                else if(theElevator.getState()==-1)
                {
                   if((theElevator.getDesFloor()<EUpQueue.GetData(0))&&(theElevator.getPosition()>EUpQueue.GetData(0)))
                       theElevator.setDesFloor(EUpQueue.GetData(0)); 
                   ElePositionPix=ElePositionPix+2;
                   if(((432-ElePositionPix)%66)==0)
                    {
                        theElevator.setposition((432-ElePositionPix)/66);
                    }
                    Indication_Light.setText(Integer.toString((432-ElePositionPix)/66)+"层");
                    repaint();
                
                }
                if(ElePositionPix==432-theElevator.getDesFloor()*66)
                {
                    if((theElevator.getState()==1)||(theElevator.getState()==-1))
                    {
                        if(theElevator.getPosition()==DesFl_Button1.getFloor())
                            DesFl_Button1.setBackground(Color.lightGray);
                        if(theElevator.getPosition()==DesFl_Button2.getFloor())
                            DesFl_Button2.setBackground(Color.lightGray);
                        if(theElevator.getPosition()==DesFl_Button3.getFloor())
                            DesFl_Button3.setBackground(Color.lightGray);
                        if(theElevator.getPosition()==DesFl_Button4.getFloor())
                            DesFl_Button4.setBackground(Color.lightGray);
                        if(theElevator.getPosition()==DesFl_Button5.getFloor())
                            DesFl_Button5.setBackground(Color.lightGray);
                        if(theElevator.getPosition()==DesFl_Button6.getFloor())
                            DesFl_Button6.setBackground(Color.lightGray);
                        
                    }
                    if(theElevator.getDesFloorFromWhere()==1)
                    {
                        if(theElevator.getPosition()==UpButton1.getFloor())
                            UpButton1.setBackground(Color.lightGray);
                        if(theElevator.getPosition()==UpButton2.getFloor())
                            UpButton2.setBackground(Color.lightGray);
                        if(theElevator.getPosition()==UpButton3.getFloor())
                            UpButton3.setBackground(Color.lightGray);
                        if(theElevator.getPosition()==UpButton4.getFloor())
                            UpButton4.setBackground(Color.lightGray);
                        if(theElevator.getPosition()==UpButton5.getFloor())
                            UpButton5.setBackground(Color.lightGray);
                        EUpQueue.DeleteData(0);
                    }
                    else if(theElevator.getDesFloorFromWhere()==-1)
                    {
                       if(theElevator.getPosition()==DnButton2.getFloor())
                            DnButton2.setBackground(Color.lightGray);
                       if(theElevator.getPosition()==DnButton3.getFloor())
                            DnButton3.setBackground(Color.lightGray);
                       if(theElevator.getPosition()==DnButton4.getFloor())
                            DnButton4.setBackground(Color.lightGray);
                       if(theElevator.getPosition()==DnButton5.getFloor())
                            DnButton5.setBackground(Color.lightGray);
                       if(theElevator.getPosition()==DnButton6.getFloor())
                            DnButton6.setBackground(Color.lightGray);
                        EDnQueue.DeleteData(0);
                    }
                    if((EUpQueue.GetEndPointer()<0)&&(EDnQueue.GetEndPointer()<0))
                    {
                        theElevator.Stop();
                        State_Light.setText("停止");
                        if(isSuspend)
                        {
                            Start_Button.setEnabled(true);
                            suspend();
                        }
                    }
                    try{
                        t2.sleep(5000);
                    }catch(InterruptedException e){}
                    if(theElevator.getState()==0)
                    {
                        if(EUpQueue.GetEndPointer()>=0)
                        {
                           theElevator.setDesFloor(EUpQueue.GetData(0));
                           theElevator.setDesFloorFromWhere(1);
                           if(theElevator.getPosition()>theElevator.getDesFloor())
                           {
                               theElevator.StartDn();
                               ElePositionPix=ElePositionPix+2;
                               State_Light. setText("下行");
                           }
                           else if(theElevator.getPosition()<theElevator.getDesFloor())
                           {
                               theElevator.StartUp();
                               ElePositionPix=ElePositionPix-2;
                               State_Light. setText("上行");
                           }
                        }
                        else if(EDnQueue.GetEndPointer()>=0)
                        {
                            theElevator.setDesFloor(EDnQueue.GetData(0));
                            theElevator.setDesFloorFromWhere(-1);
                            if(theElevator.getPosition()>theElevator.getDesFloor())
                           {
                               theElevator.StartDn();
                               ElePositionPix=ElePositionPix+2;
                               State_Light. setText("下行");
                           }
                           else if(theElevator.getPosition()<theElevator.getDesFloor())
                           {
                               theElevator.StartUp();
                               ElePositionPix=ElePositionPix-2;
                               State_Light. setText("上行");
                           }
                        }
                    }
                    else if(theElevator.getState()==1)
                    {
                        if((EUpQueue.GetEndPointer()>=0)&&(EUpQueue.GetData(0)>theElevator.getPosition()))
                        {
                            theElevator.setDesFloor(EUpQueue.GetData(0));
                            theElevator.setDesFloorFromWhere(1);
                            ElePositionPix=ElePositionPix-2;
                        }
                        else if((EDnQueue.GetEndPointer()>=0)&&(EDnQueue.GetData(0)>theElevator.getPosition()))
                        {
                            theElevator.setDesFloor(EDnQueue.GetData(0));
                            theElevator.setDesFloorFromWhere(-1);
                            ElePositionPix=ElePositionPix-2;
                        }
                        else if(EDnQueue.GetEndPointer()>=0)
                        {
                            theElevator.setDesFloorFromWhere(-1);
                            theElevator.Stop();
                            EDnQueue.DeleteData(0);
                        }
                        else
                        {
                            theElevator.setDesFloor(EDnQueue.GetData(0));
                            theElevator.setDesFloorFromWhere(-1);
                            theElevator.StartDn();
                            ElePositionPix=ElePositionPix+2;
                            State_Light. setText("下行");
                        }
                    }
                    else if(EUpQueue.GetEndPointer()>=0)
                    {
                            theElevator.setDesFloor(EUpQueue.GetData(0));
                            theElevator.setDesFloorFromWhere(1);
                            theElevator.StartDn();
                            ElePositionPix=ElePositionPix+2;
                            State_Light. setText("下行");
                    }
                }
                else if(theElevator.getState()==-1)
                {
                   if((EDnQueue.GetEndPointer()>=0)&&(EDnQueue.GetData(0)>theElevator.getPosition())) 
                   {
                            theElevator.setDesFloor(EDnQueue.GetData(0));
                            theElevator.setDesFloorFromWhere(-1);
                            ElePositionPix=ElePositionPix+2; 
                   }
                   else if((EUpQueue.GetEndPointer()>=0)&&(EUpQueue.GetData(0)>theElevator.getPosition()))
                   {
                            theElevator.setDesFloor(EUpQueue.GetData(0));
                            theElevator.setDesFloorFromWhere(1);
                            ElePositionPix=ElePositionPix+2; 
                   }
                   else if(EUpQueue.GetEndPointer()>=0)
                   {
                       if(EUpQueue.GetData(0)>theElevator.getPosition())
                       {
                            theElevator.setDesFloorFromWhere(1);
                            theElevator.Stop();
                            EUpQueue.DeleteData(0);
                       }
                       else
                       {
                            theElevator.setDesFloor(EUpQueue.GetData(0));
                            theElevator.setDesFloorFromWhere(1);
                            theElevator.StartUp();
                            ElePositionPix=ElePositionPix-2;
                            State_Light. setText("上行");
                       }
                   }
                   else if(EDnQueue.GetEndPointer()>=0)
                   {
                            theElevator.setDesFloor(EDnQueue.GetData(0));
                            theElevator.setDesFloorFromWhere(-1);
                            theElevator.StartUp();
                            ElePositionPix=ElePositionPix-2;
                            State_Light. setText("上行");
                   }
                }
            }
        
     }
   }
   public void paint(Graphics g)
   {
       g.drawImage(Elevator_Image,300,ElePositionPix,this);
   }
}
class FloorButton extends Button
{
    int Floor;
    boolean IsUp;
    FloorButton(int VFloor,boolean VIsUp,String VLabel){
        super(VLabel);
        Floor=VFloor;
        IsUp=VIsUp;
    }
    public int getFloor(){
        return Floor;
        
    }
}
class DesFlButton extends Button{
    int Floor;
    DesFlButton(int VFloor,String VLabel)
    {
        super(VLabel);
        Floor=VFloor;
    }
    public int getFloor(){
        return Floor;
    }
}
abstract class TQueue
{
    int EndPointer;
    int EndPrePointer;
    int StackArray[]=new int[100];
    TQueue()
    {
        for(int i=0;i<=99;i++)
        {
            StackArray[i]=0;
        }
        EndPointer=-1;
        EndPrePointer=-1;
    }
    public int GetEndPointer()
    {
        return EndPointer;
    }
     public int GetEndPrePointer()
    {
        return EndPrePointer;
    }
     public void SetEndPointer(int VPointer)
     {
         EndPointer=VPointer;
     }
      public void SetEndPrePointer(int VPrePointer)
     {
         EndPrePointer=VPrePointer;
     }
      public abstract boolean InsertData(int Data,boolean VPre);
      public boolean DeleteData(int VPointer)
      {
          if( EndPointer!=-1)
          {
              if((VPointer>EndPointer)||(VPointer<0))
                  return false;
              for(int i=VPointer;i<=EndPointer;i++)
                  StackArray[i]=StackArray[i+1];
              EndPointer--;
              if(VPointer<=EndPrePointer)
              {
                 EndPrePointer--;
              }
          }
          return true;
      }
      public boolean ClearQueue()
      {
          for(int i=0;i<=99;i++)
          {
              StackArray[i]=0;
          }
          EndPointer=-1;
          EndPrePointer=-1;
          return true;
      }
      public int GetData(int VPointer)
      {
          if((VPointer>EndPointer)||(VPointer<0))
              return -1;
          return StackArray[VPointer];
      }
}
class TUpQueue extends TQueue
{
    public boolean InsertData(int VData,boolean VPre)
    {
        boolean ReturnValue=false;
        boolean isRepeat=false;
        if(EndPointer==-1)
        {
            StackArray[0]=VData;
            EndPointer=0;
            ReturnValue=true;
            EndPrePointer=0;
        }
        else if((EndPointer>=0)&&(EndPointer<99))
        {
            if(!VPre)
            {
                for(int Pointer=EndPointer;Pointer>EndPrePointer;Pointer--)
                {
                   if(StackArray[Pointer]==VData)
                       isRepeat=true;
                }
                if(!isRepeat)
                {
                    if(EndPointer==EndPrePointer)
                        StackArray[EndPointer+1]=VData;
                    else
                    {
                        for(int Pointer=EndPointer;Pointer>EndPrePointer;Pointer--)
                        {
                            if(StackArray[Pointer]<=VData)
                            {
                                StackArray[Pointer+1]=VData;
                                break;
                            }
                            StackArray[Pointer+1]=StackArray[Pointer];
                            StackArray[Pointer]=VData;
                        }
                    }
                    EndPointer++;
                }
            }
            else
            {
                for(int Pointer=EndPrePointer;Pointer>=0;Pointer--)
                {
                    if(StackArray[Pointer]==VData)
                        isRepeat=true;
                }
                if(!isRepeat)
                {
                    for(int Pointer=EndPointer;Pointer>EndPrePointer;Pointer--)
                    {
                       StackArray[Pointer+1]=StackArray[Pointer]; 
                    }
                    for(int Pointer=EndPrePointer;Pointer>=0;Pointer--)
                    {
                       if(StackArray[Pointer]<=VData)
                            {
                                StackArray[Pointer+1]=VData;
                                break;
                            }
                            StackArray[Pointer+1]=StackArray[Pointer];
                            StackArray[Pointer]=VData; 
                    }
                    EndPointer++;
                    EndPrePointer++;
                }
            }
            ReturnValue=true;
        }
        return ReturnValue;
    }
}
class TDnQueue extends TQueue
{
    public boolean InsertData(int VData,boolean VPre)
    {
        boolean ReturnValue=false;
        boolean isRepeat=false;
        if(EndPointer==-1)
        {
            StackArray[0]=VData;
            EndPointer=0;
            ReturnValue=true;
            EndPrePointer=0;
        }
        else if((EndPointer>=0)&&(EndPointer<99))
        {
            if(!VPre)
            {
                for(int Pointer=EndPointer;Pointer>EndPrePointer;Pointer--)
                {
                   if(StackArray[Pointer]==VData)
                       isRepeat=true;
                }
                if(!isRepeat)
                {
                    if(EndPointer==EndPrePointer)
                        StackArray[EndPointer+1]=VData;
                    else
                    {
                        for(int Pointer=EndPointer;Pointer>EndPrePointer;Pointer--)
                        {
                            if(StackArray[Pointer]<=VData)
                            {
                                StackArray[Pointer+1]=VData;
                                break;
                            }
                            StackArray[Pointer+1]=StackArray[Pointer];
                            StackArray[Pointer]=VData;
                        }
                    }
                    EndPointer++;
                }
            }
            else
            {
                for(int Pointer=EndPrePointer;Pointer>=0;Pointer--)
                {
                    if(StackArray[Pointer]==VData)
                        isRepeat=true;
                }
                if(!isRepeat)
                {
                    for(int Pointer=EndPointer;Pointer>EndPrePointer;Pointer--)
                    {
                       StackArray[Pointer+1]=StackArray[Pointer]; 
                    }
                    for(int Pointer=EndPrePointer;Pointer>=0;Pointer--)
                    {
                       if(StackArray[Pointer]<=VData)
                            {
                                StackArray[Pointer+1]=VData;
                                break;
                            }
                            StackArray[Pointer+1]=StackArray[Pointer];
                            StackArray[Pointer]=VData; 
                    }
                    EndPointer++;
                    EndPrePointer++;
                }
            }
            ReturnValue=true;
        }
        return ReturnValue;
    }
}
class Elevator
{
    int State;
    int Position;
    int DesFloor;
    boolean IsOverWeight;
    boolean IsNormal;
    int DesFloorFromWhere;
    public Elevator(int VState,int VPosition,boolean VIsNormal)
    {
        State=VState;
        Position=VPosition;
        IsNormal=VIsNormal;
        DesFloor=1;
    }
    public void setDesFloorFromWhere(int VWh)
    {
        DesFloorFromWhere=VWh;
    }
    public int getDesFloorFromWhere()
    {
        return DesFloorFromWhere;
    }
    public void StartUp()
    {
        State=1;
    }
    public void StartDn()
    {
        State=-1;
    }
    public void Stop()
    {
        State=0;
    }
    public int getState()
    {
        return State;
    }
    public void setposition(int VPosition)
    {
        Position=VPosition;
    }
    public int getPosition()
    {
        return Position;
    }
    public void setDesFloor(int VDesFloor)
    {
        DesFloor=VDesFloor;
    }
    public int getDesFloor()
    {
        return DesFloor;
    }
    public boolean Warn()
    {
        return true;
    }
    public boolean CancelWarn()
    {
        return true;
    }
}
