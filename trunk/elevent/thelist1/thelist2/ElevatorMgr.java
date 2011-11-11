package thelist2;
/*文件名：ElevatorMgr.java
   类名：ElevatorMgr.class
   功能：生成整个程序的布局，以及接受鼠标事件，处理鼠标事件
*/
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.applet.Applet;
import java.applet.*;
//ElevatorMgr类
public class ElevatorMgr extends Applet
{//声明变量

    public Floor firstFloor;//第一楼层
    public Floor secondFloor;//第二楼层
    public Floor thirdFloor;//第三楼层
    public Elevator elevator;//位于楼层的电梯
    public ControlBoard controlBoard;//控制面板
    public Clock time;//时钟
    public Button floor1Btn;//第1层的按钮
    public Button floor2Btn;//第2层的按钮
    public Button floor3Btn;//第3层的按钮
    public int floorHeight;//楼层的高度
    public int floorWidth;//楼层的宽度
    public int elevatorHeight;//电梯的高度
    public int elevatorWidth;//电梯的宽度
    public int clockHeight;//钟的高度
    public int clockWidth;//钟的宽度
    public int controlBHeight;//控制板的高度
    public int controlBWidth;//控制板的宽度
    public AudioClip clickbutton;//点击按钮的声音
    public AudioClip sayhi;//进入电梯的声音
    public AudioClip error;//操作出错的声音
    public int f1;//楼层一变量
    public int f2;//
    public int f3;//
//程序的初始化，主要是初始化声音文件，安排各个控件的位置，布局
    public void init()
    {
        clickbutton = getAudioClip(getDocumentBase(), "sound/drip.au");//点击按钮的声音
        error = getAudioClip(getDocumentBase(), "sound/yahoo1.au");//出错时声音
        sayhi = getAudioClip(getDocumentBase(), "sound/hi.au");//进入电梯声音
        time = new Clock(1, 1, clockWidth, clockHeight);
        time.resize(clockWidth, 200);//时钟大小调整
        controlBoard = new ControlBoard(this);//创建控制面板
        controlBoard.resize(controlBWidth, controlBHeight);//控制面板的初始化
        elevator = new Elevator(this, time, this);//创建电梯
        elevator.resize(elevatorWidth, elevatorHeight);//电梯大小调整
        //创建楼层以及楼层大小
        firstFloor = new Floor(this, elevator);
        firstFloor.resize(floorWidth, floorHeight);
        secondFloor = new Floor(this, elevator);
        secondFloor.resize(floorWidth, floorHeight);
        thirdFloor = new Floor(this, elevator);
        thirdFloor.resize(floorWidth, floorHeight);
        //创建各楼层按钮的控制面板以及创建各楼层的按钮以及大小的初始化
        controlBoard = new ControlBoard(this);
        controlBoard.resize(controlBWidth, controlBHeight);
        floor1Btn = new Button("Floor1");
        floor1Btn.resize(70, 30);
        floor2Btn = new Button("Floor2");
        floor2Btn.resize(70, 30);
        floor3Btn = new Button("Floor3");
        floor3Btn.resize(70, 30);
        add(time);
        add(firstFloor);
        add(secondFloor);
        add(thirdFloor);
        add(elevator);
        add(controlBoard);
        add(floor1Btn);
        add(floor2Btn);
        add(floor3Btn);
        setLayout(null);
        //setLayout(new FlowLayout);
        firstFloor.reshape(clockWidth + 1, 2*(floorHeight + 1), floorWidth, floorHeight);
        secondFloor.reshape(clockWidth + 1, floorHeight+1, floorWidth, floorHeight);
        thirdFloor.reshape(clockWidth + 1, 1, floorWidth, floorHeight);
        elevator.reshape(clockWidth + floorWidth + 2, 1, elevatorWidth, floorHeight * 3);
        controlBoard.reshape(clockWidth + floorWidth + elevatorWidth + 4, 1, controlBWidth, controlBHeight);
        time.reshape(1, 1, clockWidth, 300);
        floor1Btn.reshape(115, floorHeight * 3 + 5, 70, 30);
        floor2Btn.reshape(215, floorHeight * 3 + 5, 70, 30);
        floor3Btn.reshape(315, floorHeight * 3 + 5, 70, 30);
        f1=1;
        f2=1;
        f3=1;
        //接受各楼层鼠标事件和处理各楼层的鼠标事件
        floor1Btn.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent mouseevent)
            {
                floor1Btn_mouseClicked(mouseevent);
            }

        });
        floor2Btn.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent mouseevent)
            {
                floor2Btn_mouseClicked(mouseevent);
            }

        });
        floor3Btn.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent mouseevent)
            {
                floor3Btn_mouseClicked(mouseevent);
            }

        });
        setBackground(Color.red);
    }

    public boolean mouseUp(Event event, int i, int j)
    {
        if(i >= clockWidth + floorWidth + elevatorWidth + 4 + 20 && i <= clockWidth + floorWidth + elevatorWidth + 4 + 20 + 20 && j >= 20 && j <= 40)
        {
            controlBoard.setFloor1ButtonUnpressed();
        } else
        if(i >= clockWidth + floorWidth + elevatorWidth + 4 + 20 && i <= clockWidth + floorWidth + elevatorWidth + 4 + 20 + 20 && j >= 153 && j <= 173)
        {
            controlBoard.setFloor2ButtonUnpressed();
        }
        if(i >= clockWidth + floorWidth + elevatorWidth + 4 + 20 && i <= clockWidth + floorWidth + elevatorWidth + 4 + 20 + 20 && j >= 240 && j <= 260)
        {
            controlBoard.setFloor3ButtonUnpressed();
        }
        return true;
    }
//鼠标按下事件
    public boolean mouseDown(Event event, int i, int j)
    {//第三层的电梯按钮，按此按钮使得电梯上三楼
        if(i >= clockWidth + floorWidth + elevatorWidth + 4 + 20 && i <= clockWidth + floorWidth + elevatorWidth + 4 + 20 + 20 && j >= 20 && j <= 40)
        {//电梯在运动
            if(elevator.moving)
            {
                showStatus("wait while moving!!!");//显示状态：电梯运动时要等待
            } else
            if(elevator.location != 3)//电梯的位置不在三层
            {
                controlBoard.setFloor1ButtonPressed();//电梯位于一层
                clickbutton.play();//电梯为向上运动，控制面板设置为向上
                elevator.setDestination(3);//目的地为三层
                 controlBoard.setFloor2ButtonPressed();//电梯位于2层
                clickbutton.play();//电梯为向上运动，控制面板设置为向上
                elevator.setDestination(3);//目的地为三层
            }
        } else  //第二层的电梯按钮，按此按钮使得电梯上二层
        if(i >= clockWidth + floorWidth + elevatorWidth + 4 + 20 && i <= clockWidth + floorWidth + elevatorWidth + 4 + 20 + 20 && j >= 153 && j <= 173)
        {//电梯在运动
            if(elevator.moving)
            {
                showStatus("wait while moving!!!");//显示状态：电梯运动时要等待
            } else
            if(elevator.location != 2)//电梯的位置不在2层
            {
                controlBoard.setFloor1ButtonPressed();//电梯位于一层
                clickbutton.play();//电梯为向上运动，控制面板设置为向上
                elevator.setDestination(2);//目的地为2层
                 controlBoard.setFloor3ButtonPressed();//电梯位于3层
                clickbutton.play();//电梯为向下运动，控制面板设置为向下
                 elevator.setDestination(2);  //目的地为2层
            }
        } else  //第一层的电梯按钮，按此按钮使得电梯上一楼
        
        
        if(i >= clockWidth + floorWidth + elevatorWidth + 4 + 20 && i <= clockWidth + floorWidth + elevatorWidth + 4 + 20 + 20 && j >= 240 && j <= 260)
        {//电梯在运动
            if(elevator.moving)
            {
                showStatus("wait while moving!!!");//显示状态：电梯运动时要等待
            } else
            if(elevator.location != 1)//电梯的位置不在1层
            {
                controlBoard.setFloor3ButtonPressed();//电梯位于3层
                clickbutton.play();//电梯为向下运动，控制面板设置为向下
                elevator.setDestination(1);//目的地为1层
               controlBoard.setFloor2ButtonPressed();//电梯位于2层
                clickbutton.play();//电梯为向下运动，控制面板设置为向下
                elevator.setDestination(1);//目的地为1层 
            }
        } else   
        
        //使得一层乘客进入电梯
        
        if(i >= clockWidth + 190 && i <= clockWidth + 200 && j >= 215 && j <= 290)
        {//乘客1在楼层里
            if(firstFloor.occupied1)
            {//电梯不在一层
                if(elevator.location != 1)
                {
                    showStatus("Elevator is not on the First Floor! Call it first.");//显示电梯不在一层的状态，要将其调到一层
                    error.play();//调用错误操作的方法
                } else //电梯在运动时
                
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");//显示乘客不能跳进运动电梯的状态
                } else  //乘客1已在电梯里
                if(elevator.occupied1)
                {
                    showStatus("There is already someone on the elevator!");//显示已在状态
                } else
                {
                    showStatus("Person on the First Floor is boarding the Elevator");//显示进入电梯状态
                    firstFloor.unoccupy1();//乘客1不在楼层里
                    elevator.occupy1();//乘客1在电梯里
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("First Floor is unoccupied1!");
            }
        } else  
        if(i >= clockWidth + 180 && i <= clockWidth + 190 && j >= 215 && j <= 290)
        {
            if(firstFloor.occupied2)
            {
                if(elevator.location != 1)
                {
                    showStatus("Elevator is not on the First Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person  can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied2)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the First Floor is boarding the Elevator");
                    firstFloor.unOccupy2();
                    elevator.occupy2();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("First Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 170 && i <= clockWidth + 180 && j >= 215 && j <= 290)
        {
            if(firstFloor.occupied3)
            {
                if(elevator.location != 1)
                {
                    showStatus("Elevator is not on the First Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied3)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the First Floor is boarding the Elevator");
                    firstFloor.unOccupy3();
                    elevator.occupy3();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("First Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 160 && i <= clockWidth + 170 && j >= 215 && j <= 290)
        {
            if(firstFloor.occupied4)
            {
                if(elevator.location != 1)
                {
                    showStatus("Elevator is not on the First Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person  can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied4)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the First Floor is boarding the Elevator");
                    firstFloor.unOccupy4();
                    elevator.occupy4();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("First Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 150 && i <= clockWidth + 160 && j >= 215 && j <= 290)
        {
            if(firstFloor.occupied5)
            {
                if(elevator.location != 1)
                {
                    showStatus("Elevator is not on the First Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied5)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the First Floor is boarding the Elevator");
                    firstFloor.unOccupy5();
                    elevator.occupy5();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("First Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 140 && i <= clockWidth + 150 && j >= 215 && j <= 290)
        {
            if(firstFloor.occupied6)
            {
                if(elevator.location != 1)
                {
                    showStatus("Elevator is not on the First Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied6)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the First Floor is boarding the Elevator");
                    firstFloor.unOccupy6();
                    elevator.occupy6();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("First Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 130 && i <= clockWidth + 140 && j >= 215 && j <= 290)
        {
            if(firstFloor.occupied7)
            {
                if(elevator.location != 1)
                {
                    showStatus("Elevator is not on the First Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied7)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the First Floor is boarding the Elevator");
                    firstFloor.unOccupy7();
                    elevator.occupy7();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("First Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 120 && i <= clockWidth + 130 && j >= 215 && j <= 290)
        {
            if(firstFloor.occupied8)
            {
                if(elevator.location != 1)
                {
                    showStatus("Elevator is not on the First Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied8)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the First Floor is boarding the Elevator");
                    firstFloor.unOccupy8();
                    elevator.occupy8();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("First Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 110 && i <= clockWidth + 120 && j >= 215 && j <= 290)
        {
            if(firstFloor.occupied9)
            {
                if(elevator.location != 1)
                {
                    showStatus("Elevator is not on the First Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied9)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the First Floor is boarding the Elevator");
                    firstFloor.unOccupy9();
                    elevator.occupy9();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("First Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 100 && i <= clockWidth + 110 && j >= 215 && j <= 290)
        {
            if(firstFloor.occupied0)
            {
                if(elevator.location != 1)
                {
                    showStatus("Elevator is not on the First Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied0)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the First Floor is boarding the Elevator");
                    firstFloor.unOccupy0();
                    elevator.occupy0();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("First Floor is unoccupied1!");
            }
        }else
        
        
        
        
        //使得二层的乘客进入电梯
        
        
        
        
        
        
   
        if(i >= clockWidth + 190 && i <= clockWidth + 200 && j >= 115 && j <= 190)
        {
            if(secondFloor.occupied1)
            {
                if(elevator.location != 2)
                {
                    showStatus("Elevator is not on the Second Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied1)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Second Floor is boarding the Elevator");
                    secondFloor.unoccupy1();
                    elevator.occupy1();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("Second Floor is unoccupied1!");
            }
        } else
        if(i >= clockWidth + 180 && i <= clockWidth + 190 && j >= 115 && j <= 190)
        {
            if(secondFloor.occupied2)
            {
                if(elevator.location != 2)
                {
                    showStatus("Elevator is not on the Second Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied2)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Second Floor is boarding the Elevator");
                    secondFloor.unOccupy2();
                    elevator.occupy2();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("Second Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 170 && i <= clockWidth + 180 && j >= 115 && j <= 190)
        {
            if(secondFloor.occupied3)
            {
                if(elevator.location != 2)
                {
                    showStatus("Elevator is not on the Second Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied3)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Second Floor is boarding the Elevator");
                    secondFloor.unOccupy3();
                    elevator.occupy3();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("Second Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 160 && i <= clockWidth + 170 && j >= 115 && j <= 190)
        {
            if(secondFloor.occupied4)
            {
                if(elevator.location != 2)
                {
                    showStatus("Elevator is not on the Second Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied4)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Second Floor is boarding the Elevator");
                    secondFloor.unOccupy4();
                    elevator.occupy4();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("Second Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 150 && i <= clockWidth + 160 && j >= 115 && j <= 190)
        {
            if(secondFloor.occupied5)
            {
                if(elevator.location != 2)
                {
                    showStatus("Elevator is not on the Second Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied5)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Second Floor is boarding the Elevator");
                    secondFloor.unOccupy5();
                    elevator.occupy5();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("Second Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 140 && i <= clockWidth + 150 && j >= 115 && j <= 190)
        {
            if(secondFloor.occupied6)
            {
                if(elevator.location != 2)
                {
                    showStatus("Elevator is not on the Second Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied6)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Second Floor is boarding the Elevator");
                    secondFloor.unOccupy6();
                    elevator.occupy6();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("Second Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 130 && i <= clockWidth + 140 && j >= 115 && j <= 190)
        {
            if(secondFloor.occupied7)
            {
                if(elevator.location != 2)
                {
                    showStatus("Elevator is not on the Second Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied7)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Second Floor is boarding the Elevator");
                    secondFloor.unOccupy7();
                    elevator.occupy7();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("Second Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 120 && i <= clockWidth + 130 && j >= 115 && j <= 190)
        {
            if(secondFloor.occupied8)
            {
                if(elevator.location != 2)
                {
                    showStatus("Elevator is not on the Second Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied8)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Second Floor is boarding the Elevator");
                    secondFloor.unOccupy8();
                    elevator.occupy8();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("Second Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 110 && i <= clockWidth + 120 && j >= 115 && j <= 190)
        {
            if(secondFloor.occupied9)
            {
                if(elevator.location != 2)
                {
                    showStatus("Elevator is not on the Second Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied9)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Second Floor is boarding the Elevator");
                    secondFloor.unOccupy9();
                    elevator.occupy9();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("Second Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 100 && i <= clockWidth + 110 && j >= 115 && j <= 190)
        {
            if(secondFloor.occupied0)
            {
                if(elevator.location != 2)
                {
                    showStatus("Elevator is not on the Second Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied0)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Second Floor is boarding the Elevator");
                    secondFloor.unOccupy0();
                    elevator.occupy0();
                    //elevator.setDestination(2);
                }
            } else
            {
                showStatus("Second Floor is unoccupied1!");
            }
        }else
        
        
        
        //使得三层的乘客进入电梯
        
        
        
        if(i >= clockWidth + 190 && i <= clockWidth + 200 && j >= 15 && j <= 90)
        {
            if(thirdFloor.occupied1)
            {
                if(elevator.location != 3)
                {
                    showStatus("Elevator is not on the Third Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied1)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Third Floor is boarding the Elevator");
                    thirdFloor.unoccupy1();
                    elevator.occupy1();
                    //elevator.setDestination(1);
                }
            } else
            {
                showStatus("Third Floor is unoccupied1!");
            }
        } else
        if(i >= clockWidth + 180 && i <= clockWidth + 190 && j >= 15 && j <= 90)
        {
            if(thirdFloor.occupied2)
            {
                if(elevator.location != 3)
                {
                    showStatus("Elevator is not on the Third Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied2)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Third Floor is boarding the Elevator");
                    thirdFloor.unOccupy2();
                    elevator.occupy2();
                    //elevator.setDestination(1);
                }
            } else
            {
                showStatus("Third Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 170 && i <= clockWidth + 180 && j >= 15 && j <= 90)
        {
            if(thirdFloor.occupied3)
            {
                if(elevator.location != 3)
                {
                    showStatus("Elevator is not on the Third Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied3)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Third Floor is boarding the Elevator");
                    thirdFloor.unOccupy3();
                    elevator.occupy3();
                    //elevator.setDestination(1);
                }
            } else
            {
                showStatus("Third Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 160 && i <= clockWidth + 170 && j >= 15 && j <= 90)
        {
            if(thirdFloor.occupied4)
            {
                if(elevator.location != 3)
                {
                    showStatus("Elevator is not on the Third Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied4)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Third Floor is boarding the Elevator");
                    thirdFloor.unOccupy4();
                    elevator.occupy4();
                    //elevator.setDestination(1);
                }
            } else
            {
                showStatus("Third Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 150 && i <= clockWidth + 160 && j >= 15 && j <= 90)
        {
            if(thirdFloor.occupied5)
            {
                if(elevator.location != 3)
                {
                    showStatus("Elevator is not on the Third Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied5)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Third Floor is boarding the Elevator");
                    thirdFloor.unOccupy5();
                    elevator.occupy5();
                    //elevator.setDestination(1);
                }
            } else
            {
                showStatus("Third Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 140 && i <= clockWidth + 150 && j >= 15 && j <= 90)
        {
            if(thirdFloor.occupied6)
            {
                if(elevator.location != 3)
                {
                    showStatus("Elevator is not on the Third Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied6)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Third Floor is boarding the Elevator");
                    thirdFloor.unOccupy6();
                    elevator.occupy6();
                    //elevator.setDestination(1);
                }
            } else
            {
                showStatus("Third Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 130 && i <= clockWidth + 140 && j >= 15 && j <= 90)
        {
            if(thirdFloor.occupied7)
            {
                if(elevator.location != 3)
                {
                    showStatus("Elevator is not on the Third Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied7)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Third Floor is boarding the Elevator");
                    thirdFloor.unOccupy7();
                    elevator.occupy7();
                    //elevator.setDestination(1);
                }
            } else
            {
                showStatus("Third Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 120 && i <= clockWidth + 130 && j >= 15 && j <= 90)
        {
            if(thirdFloor.occupied8)
            {
                if(elevator.location != 3)
                {
                    showStatus("Elevator is not on the Third Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied8)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Third Floor is boarding the Elevator");
                    thirdFloor.unOccupy8();
                    elevator.occupy8();
                    //elevator.setDestination(1);
                }
            } else
            {
                showStatus("Third Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 110 && i <= clockWidth + 120 && j >= 15 && j <= 90)
        {
            if(thirdFloor.occupied9)
            {
                if(elevator.location != 3)
                {
                    showStatus("Elevator is not on the Third Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied9)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Third Floor is boarding the Elevator");
                    thirdFloor.unOccupy9();
                    elevator.occupy9();
                    //elevator.setDestination(1);
                }
            } else
            {
                showStatus("Third Floor is unoccupied1!");
            }
        }else
        if(i >= clockWidth + 100 && i <= clockWidth + 110 && j >= 15 && j <= 90)
        {
            if(thirdFloor.occupied0)
            {
                if(elevator.location != 3)
                {
                    showStatus("Elevator is not on the Third Floor! Call it first.");
                    error.play();
                } else
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");
                } else
                if(elevator.occupied0)
                {
                    showStatus("There is already someone on the elevator!");
                } else
                {
                    showStatus("Person on the Third Floor is boarding the Elevator");
                    thirdFloor.unOccupy0();
                    elevator.occupy0();
                    //elevator.setDestination(1);
                }
            } else
            {
                showStatus("Third Floor is unoccupied1!");
            }
        }else
        
        
        
       //使得三层电梯里的乘客走出来 
        
        if(i >= clockWidth + 269 && i <= clockWidth + 275 && j >= 215 && j <= 295&& elevator.occupied1 && elevator.location == 1)
        {
            if(elevator.occupied1 && elevator.location == 1)
            {
                if(elevator.moving)
                {
                    showStatus("Person can't jump off a moving elevator!!!");
                } else
                {
                    elevator.unoccupy1();
                    if(!firstFloor.occupied1)
                    {
                        firstFloor.occupy1();
                        firstFloor.xPos1 = 175;
                        firstFloor.setDestination(2,1);
                    } else
                    {
                        sayhi.play();
                        showStatus("Bye bye my Person! Welcome to come back again.");
                    }
                }
            }
        } else
        if(i >= clockWidth + 262 && i <= clockWidth + 269 && j >= 215 && j <= 295&& elevator.occupied2 && elevator.location == 1)
        {
            if(elevator.occupied2 && elevator.location == 1)
            {
                if(elevator.moving)
                {
                    showStatus("Person can't jump off a moving elevator!!!");
                } else
                {
                    elevator.unOccupy2();
                    if(!firstFloor.occupied2)
                    {
                        firstFloor.occupy2();
                        firstFloor.xPos2 = 175;
                        firstFloor.setDestination(2,2);
                    } else
                    {
                        sayhi.play();
                        showStatus("Bye bye my Person! Welcome to come back again.");
                    }
                }
            }
        } else
        if(i >= clockWidth + 256 && i <= clockWidth + 262 && j >= 215 && j <= 295&& elevator.occupied3 && elevator.location == 1)
        {
            if(elevator.occupied3 && elevator.location == 1)
            {
                if(elevator.moving)
                {
                    showStatus("Person can't jump off a moving elevator!!!");
                } else
                {
                    elevator.unOccupy3();
                    if(!firstFloor.occupied3)
                    {
                        firstFloor.occupy3();
                        firstFloor.xPos3 = 175;
                        firstFloor.setDestination(2,3);
                    } else
                    {
                        sayhi.play();
                        showStatus("Bye bye my Person! Welcome to come back again.");
                    }
                }
            }
        } else
        if(i >= clockWidth + 249 && i <= clockWidth + 256 && j >= 215 && j <= 295&& elevator.occupied4 && elevator.location == 1)
        {
            if(elevator.occupied4 && elevator.location == 1)
            {
                if(elevator.moving)
                {
                    showStatus("Person can't jump off a moving elevator!!!");
                } else
                {
                    elevator.unOccupy4();
                    if(!firstFloor.occupied4)
                    {
                        firstFloor.occupy4();
                        firstFloor.xPos4 = 175;
                        firstFloor.setDestination(2,4);
                    } else
                    {
                        sayhi.play();
                        showStatus("Bye bye my Person! Welcome to come back again.");
                    }
                }
            }
        } else
        if(i >= clockWidth + 243 && i <= clockWidth + 249 && j >= 215 && j <= 295&& elevator.occupied5 && elevator.location == 1)
        {
            if(elevator.occupied5 && elevator.location == 1)
            {
                if(elevator.moving)
                {
                    showStatus("Person can't jump off a moving elevator!!!");
                } else
                {
                    elevator.unOccupy5();
                    if(!firstFloor.occupied5)
                    {
                        firstFloor.occupy5();
                        firstFloor.xPos5 = 175;
                        firstFloor.setDestination(2,5);
                    } else
                    {
                        sayhi.play();
                        showStatus("Bye bye my Person! Welcome to come back again.");
                    }
                }
            }
        } else
        if(i >= clockWidth + 236 && i <= clockWidth + 243 && j >= 215 && j <= 295&& elevator.occupied6 && elevator.location == 1)
        {
            if(elevator.occupied6 && elevator.location == 1)
            {
                if(elevator.moving)
                {
                    showStatus("Person can't jump off a moving elevator!!!");
                } else
                {
                    elevator.unOccupy6();
                    if(!firstFloor.occupied6)
                    {
                        firstFloor.occupy6();
                        firstFloor.xPos6 = 175;
                        firstFloor.setDestination(2,6);
                    } else
                    {
                        sayhi.play();
                        showStatus("Bye bye my Person! Welcome to come back again.");
                    }
                }
            }
        } else
        if(i >= clockWidth + 229 && i <= clockWidth + 236 && j >= 215 && j <= 295&& elevator.occupied7 && elevator.location == 1)
        {
            if(elevator.occupied7 && elevator.location == 1)
            {
                if(elevator.moving)
                {
                    showStatus("Person can't jump off a moving elevator!!!");
                } else
                {
                    elevator.unOccupy7();
                    if(!firstFloor.occupied7)
                    {
                        firstFloor.occupy7();
                        firstFloor.xPos7 = 143;
                        firstFloor.setDestination(2,7);
                    } else
                    {
                        sayhi.play();
                        showStatus("Bye bye my Person! Welcome to come back again.");
                    }
                }
            }
        } else
        if(i >= clockWidth + 223 && i <= clockWidth + 229 && j >= 215 && j <= 295&& elevator.occupied8 && elevator.location == 1)
        {
            if(elevator.occupied8 && elevator.location == 1)
            {
                if(elevator.moving)
                {
                    showStatus("Person can't jump off a moving elevator!!!");
                } else
                {
                    elevator.unOccupy8();
                    if(!firstFloor.occupied8)
                    {
                        firstFloor.occupy8();
                        firstFloor.xPos8 = 143;
                        firstFloor.setDestination(2,8);
                    } else
                    {
                        sayhi.play();
                        showStatus("Bye bye my Person! Welcome to come back again.");
                    }
                }
            }
        } else
        if(i >= clockWidth + 216 && i <= clockWidth + 223 && j >= 215 && j <= 295&& elevator.occupied9 && elevator.location == 1)
        {
            if(elevator.occupied9 && elevator.location == 1)
            {
                if(elevator.moving)
                {
                    showStatus("Person can't jump off a moving elevator!!!");
                } else
                {
                    elevator.unOccupy9();
                    if(!firstFloor.occupied9)
                    {
                        firstFloor.occupy9();
                        firstFloor.xPos9 = 143;
                        firstFloor.setDestination(2,9);
                    } else
                    {
                        sayhi.play();
                        showStatus("Bye bye my Person! Welcome to come back again.");
                    }
                }
            }
        } else
        if(i >= clockWidth +210 && i <= clockWidth + 216 && j >= 215 && j <= 295&& elevator.occupied0 && elevator.location == 1)
        {
            if(elevator.occupied0 && elevator.location == 1)
            {
                if(elevator.moving)
                {
                    showStatus("Person can't jump off a moving elevator!!!");
                } else
                {
                    elevator.unOccupy0();
                    if(!firstFloor.occupied0)
                    {
                        firstFloor.occupy0();
                        firstFloor.xPos0 = 143;
                        firstFloor.setDestination(2,0);
                    } else
                    {
                        sayhi.play();
                        showStatus("Bye bye my Person! Welcome to come back again.");
                    }
                }
            }
        } else
        
        
        
        
        //使得二层电梯里的乘客走出来
        
        
        
        if(i >= clockWidth + 269 && i <= clockWidth + 275 && j >= 115 && j <= 195 && elevator.occupied1 && elevator.location == 2)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unoccupy1();
                if(!secondFloor.occupied1)
                {
                    secondFloor.occupy1();
                    secondFloor.xPos1 = 175;
                    secondFloor.setDestination(2,1);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 262 && i <= clockWidth + 269 && j >= 115 && j <= 195 && elevator.occupied2 && elevator.location == 2)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy2();
                if(!secondFloor.occupied2)
                {
                    secondFloor.occupy2();
                    secondFloor.xPos2 = 175;
                    secondFloor.setDestination(2,2);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 256 && i <= clockWidth + 262 && j >= 115 && j <= 195 && elevator.occupied3 && elevator.location == 2)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy3();
                if(!secondFloor.occupied3)
                {
                    secondFloor.occupy3();
                    secondFloor.xPos3 = 175;
                    secondFloor.setDestination(2,3);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 249 && i <= clockWidth + 256 && j >= 115 && j <= 195 && elevator.occupied4 && elevator.location == 2)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy4();
                if(!secondFloor.occupied4)
                {
                    secondFloor.occupy4();
                    secondFloor.xPos4 = 175;
                    secondFloor.setDestination(2,4);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 243 && i <= clockWidth + 249 && j >= 115 && j <= 195 && elevator.occupied5 && elevator.location == 2)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy5();
                if(!secondFloor.occupied5)
                {
                    secondFloor.occupy5();
                    secondFloor.xPos5 = 175;
                    secondFloor.setDestination(2,5);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 236 && i <= clockWidth + 243 && j >= 115 && j <= 195 && elevator.occupied6 && elevator.location == 2)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy6();
                if(!secondFloor.occupied6)
                {
                    secondFloor.occupy6();
                    secondFloor.xPos6 = 175;
                    secondFloor.setDestination(2,6);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 229 && i <= clockWidth + 236 && j >= 115 && j <= 195 && elevator.occupied7 && elevator.location == 2)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy7();
                if(!secondFloor.occupied7)
                {
                    secondFloor.occupy7();
                    secondFloor.xPos7 = 175;
                    secondFloor.setDestination(2,7);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 223 && i <= clockWidth + 229 && j >= 115 && j <= 195 && elevator.occupied8 && elevator.location == 2)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy8();
                if(!secondFloor.occupied8)
                {
                    secondFloor.occupy8();
                    secondFloor.xPos8 = 175;
                    secondFloor.setDestination(2,8);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 216 && i <= clockWidth + 223 && j >= 115 && j <= 195 && elevator.occupied9 && elevator.location == 2)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy9();
                if(!secondFloor.occupied9)
                {
                    secondFloor.occupy9();
                    secondFloor.xPos9 = 175;
                    secondFloor.setDestination(2,9);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 210 && i <= clockWidth + 216 && j >= 115 && j <= 195 && elevator.occupied0 && elevator.location == 2)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy0();
                if(!secondFloor.occupied0)
                {
                    secondFloor.occupy0();
                    secondFloor.xPos0 = 175;
                    secondFloor.setDestination(2,0);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        
        
        
        
        
        //使得一层电梯里的乘客走出来
        
        if(i >= clockWidth + 269 && i <= clockWidth + 275 && j >= 15 && j <= 95 && elevator.occupied1 && elevator.location == 3)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unoccupy1();
                if(!thirdFloor.occupied1)
                {
                    thirdFloor.occupy1();
                    thirdFloor.xPos1 = 175;
                    thirdFloor.setDestination(2,1);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 262 && i <= clockWidth + 269 && j >= 15 && j <= 95 && elevator.occupied2 && elevator.location == 3)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy2();
                if(!thirdFloor.occupied2)
                {
                    thirdFloor.occupy2();
                    thirdFloor.xPos2 = 175;
                    thirdFloor.setDestination(2,2);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 256 && i <= clockWidth + 262 && j >= 15 && j <= 95 && elevator.occupied3 && elevator.location == 3)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy3();
                if(!thirdFloor.occupied3)
                {
                    thirdFloor.occupy3();
                    thirdFloor.xPos3 = 175;
                    thirdFloor.setDestination(2,3);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 249 && i <= clockWidth + 256 && j >= 15 && j <= 95 && elevator.occupied4 && elevator.location == 3)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy4();
                if(!thirdFloor.occupied4)
                {
                    thirdFloor.occupy4();
                    thirdFloor.xPos4 = 175;
                    thirdFloor.setDestination(2,4);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 243 && i <= clockWidth + 249 && j >= 15 && j <= 95 && elevator.occupied5 && elevator.location == 3)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy5();
                if(!thirdFloor.occupied5)
                {
                    thirdFloor.occupy5();
                    thirdFloor.xPos5 = 175;
                    thirdFloor.setDestination(2,5);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 236 && i <= clockWidth + 243 && j >= 15 && j <= 95 && elevator.occupied6 && elevator.location == 3)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy6();
                if(!thirdFloor.occupied6)
                {
                    thirdFloor.occupy6();
                    thirdFloor.xPos6 = 175;
                    thirdFloor.setDestination(2,6);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 229 && i <= clockWidth + 236 && j >= 15 && j <= 95 && elevator.occupied7 && elevator.location == 3)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy7();
                if(!thirdFloor.occupied7)
                {
                    thirdFloor.occupy7();
                    thirdFloor.xPos7 = 175;
                    thirdFloor.setDestination(2,7);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 223 && i <= clockWidth + 229 && j >= 15 && j <= 95 && elevator.occupied8 && elevator.location == 3)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy8();
                if(!thirdFloor.occupied8)
                {
                    thirdFloor.occupy8();
                    thirdFloor.xPos8 = 175;
                    thirdFloor.setDestination(2,8);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 216 && i <= clockWidth + 223 && j >= 15 && j <= 95 && elevator.occupied9 && elevator.location == 3)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy9();
                if(!thirdFloor.occupied9)
                {
                    thirdFloor.occupy9();
                    thirdFloor.xPos9 = 175;
                    thirdFloor.setDestination(2,9);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }else
        if(i >= clockWidth + 210 && i <= clockWidth + 216 && j >= 15 && j <= 95 && elevator.occupied0 && elevator.location == 3)
        {
            if(elevator.moving)
            {
                showStatus("Person can't jump off a moving elevator!!!");
            } else
            {
                elevator.unOccupy0();
                if(!thirdFloor.occupied0)
                {
                    thirdFloor.occupy0();
                    thirdFloor.xPos0 = 175;
                    thirdFloor.setDestination(2,0);
                } else
                {
                    sayhi.play();
                    showStatus("Bye bye my Person! Welcome to come back agian.");
                }
            }
        }
        
        
        
        
        return true;
    }




    public void paint(Graphics g)
    {
        g.setColor(Color.cyan);
        g.fillRect(floorWidth + elevatorWidth + 2, 0, 2, floorHeight * 2 + 7);
    }
    //ElevatorMgr类的构造方法，初始化空间的大小
    public ElevatorMgr()
    {
        floorHeight = 100;
        floorWidth = 207;
        elevatorHeight = 300;
        elevatorWidth = 72;
        clockHeight = 100;
        clockWidth = 100;
        controlBHeight = 300;
        controlBWidth = 60;
    }
    //使第一层生成乘客小人
    public void floor1Btn_mouseClicked(MouseEvent mouseevent)
    {
    	if(f1==1)
    	{
    		f1=f1+1;
        	clickbutton.play();
        	firstFloor.occupy1();
        	firstFloor.xPos1 = 10;
        	firstFloor.setDestination(1,1);
        }
        else if(f1==2)
        {
        	f1=f1+1;
        	clickbutton.play();
        	firstFloor.occupy2();
        	firstFloor.xPos2 = 10;
        	firstFloor.setDestination(1,2);
        }
        else if(f1==3)
        {
        	f1=f1+1;
        	clickbutton.play();
        	firstFloor.occupy3();
        	firstFloor.xPos3 = 10;
        	firstFloor.setDestination(1,3);
        }
        else if(f1==4)
        {
        	f1=f1+1;
        	clickbutton.play();
        	firstFloor.occupy4();
        	firstFloor.xPos4 = 10;
        	firstFloor.setDestination(1,4);
        }
        else if(f1==5)
        {
        	f1=f1+1;
        	clickbutton.play();
        	firstFloor.occupy5();
        	firstFloor.xPos5 = 10;
        	firstFloor.setDestination(1,5);
        }
        else if(f1==6)
        {
        	f1=f1+1;
        	clickbutton.play();
        	firstFloor.occupy6();
        	firstFloor.xPos6 = 10;
        	firstFloor.setDestination(1,6);
        }
        else if(f1==7)
        {
        	f1=f1+1;
        	clickbutton.play();
        	firstFloor.occupy7();
        	firstFloor.xPos7 = 10;
        	firstFloor.setDestination(1,7);
        }
        else if(f1==8)
        {
        	f1=f1+1;
        	clickbutton.play();
        	firstFloor.occupy8();
        	firstFloor.xPos8 = 10;
        	firstFloor.setDestination(1,8);
        }
        else if(f1==9)
        {
        	f1=f1+1;
        	clickbutton.play();
        	firstFloor.occupy9();
        	firstFloor.xPos9 = 10;
        	firstFloor.setDestination(1,9);
        }
        else if(f1==10)
        {
        	f1=1;
        	clickbutton.play();
        	firstFloor.occupy0();
        	firstFloor.xPos0 = 10;
        	firstFloor.setDestination(1,0);
        }
    }
    //使第二层生成乘客小人
    public void floor2Btn_mouseClicked(MouseEvent mouseevent)
    {
    	if(f2==1)
    	{
    		f2=f2+1;
        	clickbutton.play();
        	secondFloor.occupy1();
        	secondFloor.xPos1 = 10;
        	secondFloor.setDestination(1,1);
        }
        else if(f2==2)
        {
        	f2=f2+1;
        	clickbutton.play();
        	secondFloor.occupy2();
        	secondFloor.xPos2 = 10;
        	secondFloor.setDestination(1,2);
        }
        else if(f2==3)
        {
        	f2=f2+1;
        	clickbutton.play();
        	secondFloor.occupy3();
        	secondFloor.xPos3 = 10;
        	secondFloor.setDestination(1,3);
        }
        else if(f2==4)
        {
        	f2=f2+1;
        	clickbutton.play();
        	secondFloor.occupy4();
        	secondFloor.xPos4 = 10;
        	secondFloor.setDestination(1,4);
        }
        else if(f2==5)
        {
        	f2=f2+1;
        	clickbutton.play();
        	secondFloor.occupy5();
        	secondFloor.xPos5 = 10;
        	secondFloor.setDestination(1,5);
        }
        else if(f2==6)
        {
        	f2=f2+1;
        	clickbutton.play();
        	secondFloor.occupy6();
        	secondFloor.xPos6 = 10;
        	secondFloor.setDestination(1,6);
        }
        else if(f2==7)
        {
        	f2=f2+1;
        	clickbutton.play();
        	secondFloor.occupy7();
        	secondFloor.xPos7 = 10;
        	secondFloor.setDestination(1,7);
        }
        else if(f2==8)
        {
        	f2=f2+1;
        	clickbutton.play();
        	secondFloor.occupy8();
        	secondFloor.xPos8 = 10;
        	secondFloor.setDestination(1,8);
        }
        else if(f2==9)
        {
        	f2=f2+1;
        	clickbutton.play();
        	secondFloor.occupy9();
        	secondFloor.xPos9 = 10;
        	secondFloor.setDestination(1,9);
        }
        else if(f2==10)
        {
        	f2=1;
        	clickbutton.play();
        	secondFloor.occupy0();
        	secondFloor.xPos0 = 10;
        	secondFloor.setDestination(1,0);
        }
    }
     //使第三层生成乘客小人
    public void floor3Btn_mouseClicked(MouseEvent mouseevent)
    {
    	if(f3==1)
    	{
    		f3=2;
        	clickbutton.play();
        	thirdFloor.occupy1();
        	thirdFloor.xPos1 = 10;
        	thirdFloor.setDestination(1,1);
        }
        else if(f3==2)
        {
        	f3=f3+1;
        	clickbutton.play();
        	thirdFloor.occupy2();
        	thirdFloor.xPos2 = 10;
        	thirdFloor.setDestination(1,2);
        }
        else if(f3==3)
        {
        	f3=f3+1;
        	clickbutton.play();
        	thirdFloor.occupy3();
        	thirdFloor.xPos3 = 10;
        	thirdFloor.setDestination(1,3);
        }
        else if(f3==4)
        {
        	f3=f3+1;
        	clickbutton.play();
        	thirdFloor.occupy4();
        	thirdFloor.xPos4 = 10;
        	thirdFloor.setDestination(1,4);
        }
        else if(f3==5)
        {
        	f3=f3+1;
        	clickbutton.play();
        	thirdFloor.occupy5();
        	thirdFloor.xPos5 = 10;
        	thirdFloor.setDestination(1,5);
        }
        else if(f3==6)
        {
        	f3=f3+1;
        	clickbutton.play();
        	thirdFloor.occupy6();
        	thirdFloor.xPos6 = 10;
        	thirdFloor.setDestination(1,6);
        }
        else if(f3==7)
        {
        	f3=f3+1;
        	clickbutton.play();
        	thirdFloor.occupy7();
        	thirdFloor.xPos7 = 10;
        	thirdFloor.setDestination(1,7);
        }
        else if(f3==8)
        {
        	f3=f3+1;
        	clickbutton.play();
        	thirdFloor.occupy8();
        	thirdFloor.xPos8 = 10;
        	thirdFloor.setDestination(1,8);
        }
        else if(f3==9)
        {
        	f3=f3+1;
        	clickbutton.play();
        	thirdFloor.occupy9();
        	thirdFloor.xPos9 = 10;
        	thirdFloor.setDestination(1,9);
        }
        else if(f3==10)
        {
        	f3=1;
        	clickbutton.play();
        	thirdFloor.occupy0();
        	thirdFloor.xPos0 = 10;
        	thirdFloor.setDestination(1,0);
        }
    }
    //设置指示灯的亮与灭
    public void setControlBoardUpOff()
    {
        controlBoard.setUpOff();
    }

    public void setControlBoardDownOff()
    {
        controlBoard.setDownOff();
    }

    public void setControlBoardUpOn()
    {
        controlBoard.setUpOn();
    }

    public void setControlBoardDownOn()
    {
        controlBoard.setDownOn();
    }
}
