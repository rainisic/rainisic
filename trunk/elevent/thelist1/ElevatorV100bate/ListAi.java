package ElevatorV100bate;

import java.util.Vector;
import javax.swing.*;

public class ListAi {
	boolean ListAiOff = false;  //AI开关
	ListAiData listAiData ;      //AI数据
	@SuppressWarnings("unchecked")
	Vector<ListAiData> ListAiData = new Vector(); // 定义 人工模拟容器
    int aiPeopleAveMxR[] = new int[4];
	


	public ListAi() {
		setListAiAllData();              //进行AI数据构造
		setAiPeopleAveMxR();
	}
	
	
	public void setListAiAllData()            // 设置更新AI数据
	{
		for(int i=1;i<200;i++){
			if(ListAiData.size()<200){
			listAiData = new ListAiData();
			ListAiData.addElement(listAiData);}
			else{
				listAiData =(ListAiData)ListAiData.elementAt(i);
				listAiData.AiExist = true ;	
			}
		}
	}
	
	//设置人工AI开关
	public void setListAiOff(boolean i)
	{
		if(i)
			ListAiOff = true;
		else{
			ListAiOff = false;		
		}
	}
	
	public boolean getListAiOff()
	{
		return ListAiOff;
	}
	
	public void setListAiData(int i,int DAiBornPos,int DAicurPos)  //设置ai数据
	{
		listAiData =(ListAiData)ListAiData.elementAt(i);
		listAiData.AiBornPos = i ;
		listAiData.AiBornPos = i ;	
	}
	
	public ListAiData getListAiData(int i)
	{
		return listAiData =(ListAiData)ListAiData.elementAt(i);
	}
	
	public int getAiBornPos(int i)
	{
		listAiData =(ListAiData)ListAiData.elementAt(i);
		
		return listAiData.AiBornPos;
	}
	
	public void setListAiMeth1()    //设置人工算法的（特殊楼层模式）
	{
		for(int i=1;i<200;i++){
			listAiData =(ListAiData)ListAiData.elementAt(i);
			if(listAiData.AicurPos != 1 && (i%2 ==0) )
			listAiData.AiBornPos = 1 ;
		}
	}
	
	public void setListAiMeth2()    //设置人工算法的（特殊楼层模式）
	{
		for(int i=1;i<200;i++){
			listAiData =(ListAiData)ListAiData.elementAt(i);
			if(listAiData.AicurPos != 1 && (i%2 ==0) )
			listAiData.AicurPos = 1 ;
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

}

class ListAiData {               //进行AI数据初始化

	int AiBornPos = 1, AicurPos,AiListNum,AiExitTime=0;          //生成出生楼层，目标楼层

	boolean AiExist,AiExt;                       // 设置AI是否存在
	ListAiData() {
		AiBornPos = (int)(Math.random()*16);         //随机生成16数字
	    do{
		AicurPos = (int)(Math.random()*16);}
	    while(AiBornPos == AicurPos);	    
	    AiExist =true;
	    
	}
 public boolean getAiDirect(){          //取得AI上下楼方向
	 if(AiBornPos < AicurPos)
	 {
		 return true;
	 }
	 else
	 {
	 return false;
	 }
 }
	public int getAiBornPos() {
		return AiBornPos;
	}
	public void setAiBornPos(int aiBornPos) {
		AiBornPos = aiBornPos;
	}
	public int getAicurPos() {
		return AicurPos;
	}
	public void setAicurPos(int aicurPos) {
		AicurPos = aicurPos;
	}
	
}