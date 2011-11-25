package ElevatorV090.bate;

import com.kakalion.html.IReport;
import com.kakalion.html.Report;

public class ListText implements Runnable {
	double listUpDownButton[][][] = new double[17][3][2]; // �ⲿ��ť����
	double listButtonData[][][] = new double[18][7][2]; // �����ڲ���ť����

	Thread t = new Thread(this); // �����߳�
	IReport report = new Report(); // ���ⲿ��������

	int a[][] = new int[17][2];   //�������ݣ��ⲿ��ť��Ϣ
	int b[][] = new int[17][6];   //�������ݣ������ڲ���ť

	int listUpMax,listDownMax;
	int listWaMax[] = new int [6] ;
	int listUpButtonAveTime,listDownButtonAveTime;
	int listButtonAveTime[] = new int[6];
	int time = 0;

	ListText() {
		t.start();
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 2; j++) {
				    listUpDownButton[i][j][1] = 0;
					listUpDownButton[i][j][0] =0;
				    a[i][j] = (int) listUpDownButton[i][j][0];
	
				listButtonData[i][j][1] =0;
				listButtonData[i][j][0]= 0;
			}
		}
		
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				time++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// a[time%15][time%2] = aiTime;
			// b[time%15][time%2] = aiTime;
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 2; j++) {
					if (listUpDownButton[i][j][1] == 1) {
						listUpDownButton[i][j][0] += 0.1;
					} else {
						listUpDownButton[i][j][0] = 0;
					}
					a[i][j] = (int) listUpDownButton[i][j][0];
					//System.out.println(a[i][j]+" ");
					listUpButtonAveTime+=a[i][j];      //��ƽ���ȴ�ʱ��
					listDownButtonAveTime+=a[i][j];;   //��ƽ���ȴ�ʱ��
					
				}
				
				for (int j = 0; j < 6; j++) {
					if (listButtonData[i][j][1] == 1) {
						listButtonData[i][j][0] += 0.1;
					} else {
						listButtonData[i][j][0] = 0;
					}
					b[i][j] = (int) listButtonData[i][j][0];
					
					//System.out.println(b[i][j]+" ");
					listButtonAveTime[j] +=b[i][j];   //��ƽ���ȴ�ʱ��
				}
				
			}
			
			a[16][0] = listUpButtonAveTime/16;
			a[16][1] = listDownButtonAveTime/16;
			
			if(a[16][0]>listUpMax)
			{listUpMax=a[16][0];} //���ȴ�ʱ��
			if(a[16][1]>listDownMax)
			{listDownMax=a[16][1];} //���ȴ�ʱ��
			for (int j = 0; j < 6; j++) 
			{
				
				b[16][j]=listButtonAveTime[j]/16;
				listButtonAveTime[j]=0;
				if(b[16][j]>listWaMax[j])
				{listWaMax[j]=b[16][j];} //���ȴ�ʱ��
			}
				
				listUpButtonAveTime = 0;
				listDownButtonAveTime=0;
			if (time % 5 == 0) {
				
			//	for(int i = 0;i<16;i++){
			//		a[i][1] =i;}
			//		b[i][1] = i;}
				report.setData(a, b); // ���봰������
				report.ExportData("c:/wocao.html"); // �޸�����
			}
			if (time == 10000)
				time = 0;
		}
	}

	public int getTime() {
		return time;

	}
}
