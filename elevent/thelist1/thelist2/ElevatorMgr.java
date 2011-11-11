package thelist2;
/*�ļ�����ElevatorMgr.java
   ������ElevatorMgr.class
   ���ܣ�������������Ĳ��֣��Լ���������¼�����������¼�
*/
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.applet.Applet;
import java.applet.*;
//ElevatorMgr��
public class ElevatorMgr extends Applet
{//��������

    public Floor firstFloor;//��һ¥��
    public Floor secondFloor;//�ڶ�¥��
    public Floor thirdFloor;//����¥��
    public Elevator elevator;//λ��¥��ĵ���
    public ControlBoard controlBoard;//�������
    public Clock time;//ʱ��
    public Button floor1Btn;//��1��İ�ť
    public Button floor2Btn;//��2��İ�ť
    public Button floor3Btn;//��3��İ�ť
    public int floorHeight;//¥��ĸ߶�
    public int floorWidth;//¥��Ŀ��
    public int elevatorHeight;//���ݵĸ߶�
    public int elevatorWidth;//���ݵĿ��
    public int clockHeight;//�ӵĸ߶�
    public int clockWidth;//�ӵĿ��
    public int controlBHeight;//���ư�ĸ߶�
    public int controlBWidth;//���ư�Ŀ��
    public AudioClip clickbutton;//�����ť������
    public AudioClip sayhi;//������ݵ�����
    public AudioClip error;//�������������
    public int f1;//¥��һ����
    public int f2;//
    public int f3;//
//����ĳ�ʼ������Ҫ�ǳ�ʼ�������ļ������Ÿ����ؼ���λ�ã�����
    public void init()
    {
        clickbutton = getAudioClip(getDocumentBase(), "sound/drip.au");//�����ť������
        error = getAudioClip(getDocumentBase(), "sound/yahoo1.au");//����ʱ����
        sayhi = getAudioClip(getDocumentBase(), "sound/hi.au");//�����������
        time = new Clock(1, 1, clockWidth, clockHeight);
        time.resize(clockWidth, 200);//ʱ�Ӵ�С����
        controlBoard = new ControlBoard(this);//�����������
        controlBoard.resize(controlBWidth, controlBHeight);//�������ĳ�ʼ��
        elevator = new Elevator(this, time, this);//��������
        elevator.resize(elevatorWidth, elevatorHeight);//���ݴ�С����
        //����¥���Լ�¥���С
        firstFloor = new Floor(this, elevator);
        firstFloor.resize(floorWidth, floorHeight);
        secondFloor = new Floor(this, elevator);
        secondFloor.resize(floorWidth, floorHeight);
        thirdFloor = new Floor(this, elevator);
        thirdFloor.resize(floorWidth, floorHeight);
        //������¥�㰴ť�Ŀ�������Լ�������¥��İ�ť�Լ���С�ĳ�ʼ��
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
        //���ܸ�¥������¼��ʹ����¥�������¼�
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
//��갴���¼�
    public boolean mouseDown(Event event, int i, int j)
    {//������ĵ��ݰ�ť�����˰�ťʹ�õ�������¥
        if(i >= clockWidth + floorWidth + elevatorWidth + 4 + 20 && i <= clockWidth + floorWidth + elevatorWidth + 4 + 20 + 20 && j >= 20 && j <= 40)
        {//�������˶�
            if(elevator.moving)
            {
                showStatus("wait while moving!!!");//��ʾ״̬�������˶�ʱҪ�ȴ�
            } else
            if(elevator.location != 3)//���ݵ�λ�ò�������
            {
                controlBoard.setFloor1ButtonPressed();//����λ��һ��
                clickbutton.play();//����Ϊ�����˶��������������Ϊ����
                elevator.setDestination(3);//Ŀ�ĵ�Ϊ����
                 controlBoard.setFloor2ButtonPressed();//����λ��2��
                clickbutton.play();//����Ϊ�����˶��������������Ϊ����
                elevator.setDestination(3);//Ŀ�ĵ�Ϊ����
            }
        } else  //�ڶ���ĵ��ݰ�ť�����˰�ťʹ�õ����϶���
        if(i >= clockWidth + floorWidth + elevatorWidth + 4 + 20 && i <= clockWidth + floorWidth + elevatorWidth + 4 + 20 + 20 && j >= 153 && j <= 173)
        {//�������˶�
            if(elevator.moving)
            {
                showStatus("wait while moving!!!");//��ʾ״̬�������˶�ʱҪ�ȴ�
            } else
            if(elevator.location != 2)//���ݵ�λ�ò���2��
            {
                controlBoard.setFloor1ButtonPressed();//����λ��һ��
                clickbutton.play();//����Ϊ�����˶��������������Ϊ����
                elevator.setDestination(2);//Ŀ�ĵ�Ϊ2��
                 controlBoard.setFloor3ButtonPressed();//����λ��3��
                clickbutton.play();//����Ϊ�����˶��������������Ϊ����
                 elevator.setDestination(2);  //Ŀ�ĵ�Ϊ2��
            }
        } else  //��һ��ĵ��ݰ�ť�����˰�ťʹ�õ�����һ¥
        
        
        if(i >= clockWidth + floorWidth + elevatorWidth + 4 + 20 && i <= clockWidth + floorWidth + elevatorWidth + 4 + 20 + 20 && j >= 240 && j <= 260)
        {//�������˶�
            if(elevator.moving)
            {
                showStatus("wait while moving!!!");//��ʾ״̬�������˶�ʱҪ�ȴ�
            } else
            if(elevator.location != 1)//���ݵ�λ�ò���1��
            {
                controlBoard.setFloor3ButtonPressed();//����λ��3��
                clickbutton.play();//����Ϊ�����˶��������������Ϊ����
                elevator.setDestination(1);//Ŀ�ĵ�Ϊ1��
               controlBoard.setFloor2ButtonPressed();//����λ��2��
                clickbutton.play();//����Ϊ�����˶��������������Ϊ����
                elevator.setDestination(1);//Ŀ�ĵ�Ϊ1�� 
            }
        } else   
        
        //ʹ��һ��˿ͽ������
        
        if(i >= clockWidth + 190 && i <= clockWidth + 200 && j >= 215 && j <= 290)
        {//�˿�1��¥����
            if(firstFloor.occupied1)
            {//���ݲ���һ��
                if(elevator.location != 1)
                {
                    showStatus("Elevator is not on the First Floor! Call it first.");//��ʾ���ݲ���һ���״̬��Ҫ�������һ��
                    error.play();//���ô�������ķ���
                } else //�������˶�ʱ
                
                if(elevator.moving)
                {
                    showStatus("Person can't jump onto a moving elevator!!!");//��ʾ�˿Ͳ��������˶����ݵ�״̬
                } else  //�˿�1���ڵ�����
                if(elevator.occupied1)
                {
                    showStatus("There is already someone on the elevator!");//��ʾ����״̬
                } else
                {
                    showStatus("Person on the First Floor is boarding the Elevator");//��ʾ�������״̬
                    firstFloor.unoccupy1();//�˿�1����¥����
                    elevator.occupy1();//�˿�1�ڵ�����
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
        
        
        
        
        //ʹ�ö���ĳ˿ͽ������
        
        
        
        
        
        
   
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
        
        
        
        //ʹ������ĳ˿ͽ������
        
        
        
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
        
        
        
       //ʹ�����������ĳ˿��߳��� 
        
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
        
        
        
        
        //ʹ�ö��������ĳ˿��߳���
        
        
        
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
        
        
        
        
        
        //ʹ��һ�������ĳ˿��߳���
        
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
    //ElevatorMgr��Ĺ��췽������ʼ���ռ�Ĵ�С
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
    //ʹ��һ�����ɳ˿�С��
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
    //ʹ�ڶ������ɳ˿�С��
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
     //ʹ���������ɳ˿�С��
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
    //����ָʾ�Ƶ�������
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
