package ElevatorV090.bate;

import java.util.Vector;
import javax.swing.*;

public class ListAi {
	boolean ListAiOff = false;  //AI����
	ListAiData listAiData ;      //AI����
	@SuppressWarnings("unchecked")
	Vector<ListAiData> ListAiData = new Vector(); // ���� �˹�ģ������
 
	
	public ListAi() {
		setListAiAllData();              //����AI���ݹ���
	}
	
	
	public void setListAiAllData()            // ���ø���AI����
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
	
	//�����˹�AI����
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
	
	public void setListAiData(int i,int DAiBornPos,int DAicurPos)  //����ai����
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
	
	public void setListAiMeth1()    //�����˹��㷨�ģ�����¥��ģʽ��
	{
		for(int i=1;i<200;i++){
			listAiData =(ListAiData)ListAiData.elementAt(i);
			if(listAiData.AicurPos != 1 && (i%2 ==0) )
			listAiData.AiBornPos = 1 ;
		}
	}
}

class ListAiData {               //����AI���ݳ�ʼ��

	int AiBornPos = 1, AicurPos,AiListNum;          //���ɳ���¥�㣬Ŀ��¥��
	boolean AiExist;                       // ����AI�Ƿ����
	ListAiData() {
		AiBornPos = (int)(Math.random()*16);         //�������16����
	    do{
		AicurPos = (int)(Math.random()*16);}
	    while(AiBornPos == AicurPos);	    
	    AiExist =true;
	    
	}
 public boolean getAiDirect(){          //ȡ��AI����¥����
	 if(AiBornPos < AicurPos)
	 {
		 return true;
	 }
	 else
	 {
	 return false;
	 }
 }
	
	
}