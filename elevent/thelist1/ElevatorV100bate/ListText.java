package ElevatorV100bate;

import java.util.Random;

import com.kakalion.html.IReport;
import com.kakalion.html.Report;

public class ListText implements Runnable {
	double listUpDownButton[][][] = new double[17][3][2]; // 外部按钮数组

	double listButtonData[][][] = new double[18][7][2]; // 电梯内部按钮数组

	Thread t = new Thread(this); // 设置线程
	IReport report = new Report(); // 打开外部表格窗体对象
	int listAiTime[][] = new int[200][4];   //传入数据：外部按钮信息
	int a[][] = new int[17][2];   //传入数据：外部按钮信息
	int b[][] = new int[17][6];   //传入数据：电梯内部按钮
	double aiPeopleText[][][] = new double[200][4][2];
    int aiPeopleAve[] = new int[4];
   
    int aiPeopleAveMxR[] = new int[4];
	int aiPeopleAveMx[] = new int[4];
	int listUpMax[]= new int[4],listDownMax[]= new int[4];
	int listWaMax[] = new int [6] ;
	int listUpButtonAveTime,listDownButtonAveTime;
	int listButtonAveTime[] = new int[6];
	int time = 0;
    int listMeth;
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
		setAiPeopleAveMxR();
		
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
			
			if(a[16][0]>listUpMax[listMeth])
			{listUpMax[listMeth]=a[16][0];} //最大等待时间
			if(a[16][1]>listDownMax[listMeth])
			{listDownMax[listMeth]=a[16][1];} //最大等待时间
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
			//	listupdownmeth();
			}
			if (time == 10000)
				time = 0;
			aiPeopleAve[listMeth]=0;
			for(int i =1;i<20 ;i++)
			{  
			if(aiPeopleText[i][listMeth][1]>1 &&aiPeopleText[i][listMeth][0]< 20)
				{aiPeopleText[i][listMeth][0]+=0.1;}
			aiPeopleAve[listMeth]+=(int)aiPeopleText[i][listMeth][0];
			}
			aiPeopleAve[listMeth] = aiPeopleAve[listMeth]/20;
				if(aiPeopleAve[listMeth]>aiPeopleAveMx[listMeth])
				{aiPeopleAveMx[listMeth]=aiPeopleAve[listMeth];}
		}
	}

	public int getTime() {
		return time;
		
	}
	public double[][][] getListUpDownButton() {
		return listUpDownButton;
	}

	public void setListUpDownButton(double[][][] listUpDownButton) {
		this.listUpDownButton = listUpDownButton;
	}

	public double[][][] getListButtonData() {
		return listButtonData;
	}

	public void setListButtonData(double[][][] listButtonData) {
		this.listButtonData = listButtonData;
	}

	public int[] getListUpMax() {
		return listUpMax;
	}

	public void setListUpMax(int[] listUpMax) {
		this.listUpMax = listUpMax;
	}

	public int[] getListDownMax() {
		return listDownMax;
	}

	public void setListDownMax(int[] listDownMax) {
		this.listDownMax = listDownMax;
	}

	public int[] getListWaMax() {
		return listWaMax;
	}

	public void setListWaMax(int[] listWaMax) {
		this.listWaMax = listWaMax;
	}

	public int getListUpButtonAveTime() {
		return listUpButtonAveTime;
	}

	public void setListUpButtonAveTime(int listUpButtonAveTime) {
		this.listUpButtonAveTime = listUpButtonAveTime;
	}

	public int getListDownButtonAveTime() {
		return listDownButtonAveTime;
		
	}

	public void setListDownButtonAveTime(int listDownButtonAveTime) {
		this.listDownButtonAveTime = listDownButtonAveTime;
	}
	
	public void listupdownmeth() {
		if(listUpMax[0]!=0 && listUpMax[0]< 7)
		this.listUpMax[0]++;if(aiPeopleAveMx[listMeth]<getAiPeopleAveMxR(listMeth))aiPeopleAveMx[listMeth]++;
		if(listDownMax[0]!=0 && listDownMax[0]< 4)
		this.listDownMax[0]++;
	}
	
	 public double[][][] getAiPeopleText() {
			return aiPeopleText;
		}

		public void setAiPeopleText(double[][][] aiPeopleText) {
			this.aiPeopleText = aiPeopleText;
		}
		
		public void setAiPeopleTextZore() {
			for(int i =1;i<21 ;i++)
			{  	aiPeopleText[i][listMeth][0]=0;
			}
		}
		public int getAiPeopleAveMxR(int i) {
			return aiPeopleAveMxR[i];
		}


		public void setAiPeopleAveMxR() {
			this.aiPeopleAveMxR[0] = (int)(Math.random()*4) + 20 ;
			this.aiPeopleAveMxR[1] = (int)(Math.random()*4) + 10 ;
			this.aiPeopleAveMxR[2] = (int)(Math.random()*4) + 15 ;
			this.aiPeopleAveMxR[3] = (int)(Math.random()*4) + 14 ;
		}
		public int[] getAiPeopleAve() {
			return aiPeopleAve;
		}

		public void setAiPeopleAve(int[] aiPeopleAve) {
			this.aiPeopleAve = aiPeopleAve;
		}

		public int[] getAiPeopleAveMx() {
			return aiPeopleAveMx;
		}

		public void setAiPeopleAveMx(int[] aiPeopleAveMx) {
			this.aiPeopleAveMx = aiPeopleAveMx;
		}
	

	public int[] getListButtonAveTime() {
		return listButtonAveTime;
	}

	public void setListButtonAveTime(int[] listButtonAveTime) {
		this.listButtonAveTime = listButtonAveTime;
	}

	public int getListMeth() {
		return listMeth;
		
	}

	public void setListMeth(int listMeth) {
		this.listMeth = listMeth;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
