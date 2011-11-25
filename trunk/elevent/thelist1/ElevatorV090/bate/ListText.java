package ElevatorV090.bate;

import com.kakalion.html.IReport;
import com.kakalion.html.Report;

public class ListText implements Runnable {
	double listUpDownButton[][][] = new double[17][3][2]; // 外部按钮数组
	double listButtonData[][][] = new double[18][7][2]; // 电梯内部按钮数组

	Thread t = new Thread(this); // 设置线程
	IReport report = new Report(); // 打开外部表格窗体对象

	int a[][] = new int[17][2];   //传入数据：外部按钮信息
	int b[][] = new int[17][6];   //传入数据：电梯内部按钮

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
					listUpButtonAveTime+=a[i][j];      //算平均等待时间
					listDownButtonAveTime+=a[i][j];;   //算平均等待时间
					
				}
				
				for (int j = 0; j < 6; j++) {
					if (listButtonData[i][j][1] == 1) {
						listButtonData[i][j][0] += 0.1;
					} else {
						listButtonData[i][j][0] = 0;
					}
					b[i][j] = (int) listButtonData[i][j][0];
					
					//System.out.println(b[i][j]+" ");
					listButtonAveTime[j] +=b[i][j];   //算平均等待时间
				}
				
			}
			
			a[16][0] = listUpButtonAveTime/16;
			a[16][1] = listDownButtonAveTime/16;
			
			if(a[16][0]>listUpMax)
			{listUpMax=a[16][0];} //最大等待时间
			if(a[16][1]>listDownMax)
			{listDownMax=a[16][1];} //最大等待时间
			for (int j = 0; j < 6; j++) 
			{
				
				b[16][j]=listButtonAveTime[j]/16;
				listButtonAveTime[j]=0;
				if(b[16][j]>listWaMax[j])
				{listWaMax[j]=b[16][j];} //最大等待时间
			}
				
				listUpButtonAveTime = 0;
				listDownButtonAveTime=0;
			if (time % 5 == 0) {
				
			//	for(int i = 0;i<16;i++){
			//		a[i][1] =i;}
			//		b[i][1] = i;}
				report.setData(a, b); // 传入窗体数据
				report.ExportData("c:/wocao.html"); // 修改数据
			}
			if (time == 10000)
				time = 0;
		}
	}

	public int getTime() {
		return time;

	}
}
