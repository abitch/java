package SJF;
import java.util.*;

class Process2 implements Comparable{
	private int arriveTime;
	private int serverTime;
	public int finishTime;
	
	Process2(int arriveTime,int serverTime)
	{
		this.arriveTime = arriveTime;
		this.serverTime = serverTime;
		
	}
	public int compareTo(Object o){
		Process2 pro=(Process2)o;
		if(arriveTime<pro.getArriveTime()){
			return -1;
		}
		if(arriveTime>pro.getArriveTime()){
			return 1;
		}
		return 0;
	}
	public int compareTo(Process2 o){
		if(serverTime<o.getArriveTime()){
			return -1;
		}
		if(serverTime>o.getArriveTime()){
			return 1;
		}
		return 0;
		
	}

	public int getServerTime() {
		return serverTime;
	}

	public void setServerTime(int serverTime) {
		this.serverTime = serverTime;
	}

	public int getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(int arriveTime) {
		this.arriveTime = arriveTime;
	}
}
public class SJF {
		//���˼�룺���Ƚ��������鰴����ʱ�����򣬽���һ����������ʱ����ڷ���ʱ�䣬ʣ�µķ���sort������
	//Ȼ�����ʱ����ݽ���½���Ѹ������̵ķ���ʱ�����server���飬Ȼ�����Զ����min2�������ϵ���server�������Сֵ��
	//����������������ʱ�䣬������ֵ��Ϊ-1��������һ������
	//����������Сֵ
	public static int min2(int[] array){  
        int minIndex = 0;  
        for(int j=0;j<array.length;j++)			//��������ֵָ������ֵΪ-1��λ��
        {
        	if(array[j]!=-1){
        		minIndex = j;
        		break;
        	}
        }
        //�ҳ���Сֵ�Ҳ���Ϊ0��-1
        for (int i = 0; i<array.length;i++){  
        	if(array[i]!=-1&array[i]!=0&array[i]<array[minIndex]){
        		minIndex = i;
        	}
        }  
        return minIndex;  
    }
	
	public static void main(String[] args){
		List proList = new ArrayList();
//		proList.add(new Process2(1,3));
//		proList.add(new Process2(0,4));
//		proList.add(new Process2(3,2));
//		proList.add(new Process2(4,4));
//		proList.add(new Process2(2,5));
		proList.add(new Process2(5,4));
		proList.add(new Process2(6,4));
		proList.add(new Process2(0,2));
		proList.add(new Process2(7,3));
		proList.add(new Process2(10,1));
		
		Collections.sort(proList);

		Process2 a = (Process2)proList.get(0);
		Process2 b = (Process2)proList.get(1);
		Process2 c = (Process2)proList.get(2);
		Process2 d = (Process2)proList.get(3);
		Process2 e = (Process2)proList.get(4);

		a.finishTime = a.getServerTime();
		Process2 sort[]={b,c,d,e};   //����������
		int server[] = new int[4];
		int i = 0;
		int running_flag = 1;
		
		int p = a.finishTime;//p�Ǵ���ǰһ�������ʱ��
		
		while(i!=-1){
			//����ʱ�Ľ��̷������飻
			for(int j=0;j<sort.length;j++){
				if(sort[j].getArriveTime()==i){
					server[j]=sort[j].getServerTime();
					break;
				}
										
			}
			if(i==p)
			{
				running_flag=0;				
			}
			if(running_flag==0){
				//�ж��������Ƿ�����ֵ�����Ƿ��н��̵���
				for(int kong=0;kong<server.length;kong++){
					if(server[kong]>0){
						int n = min2(server);		//��������Сֵ�������ݸ�n				
						p = i + server[n];
						sort[n].finishTime = p;		//������ʱ��
						server[n] = -1;				//����ȥ�������е�ֵ
						running_flag = 1;
						break;
					}		
				}							
			}
			i++;
			//��sort�����е��������ʱ�䲻Ϊ0ʱѭ������
			int ceshi=0;
			for(int jieshu =0;jieshu<sort.length;jieshu++){
				if(sort[jieshu].finishTime==0)
					break;
				else{
					ceshi++;
				}
			}
			if(ceshi==4)
				i=-1;
		}
		
		System.out.print("�������ƣ�"+"\t");
		System.out.print("A"+"\t");
		System.out.print("B"+"\t");
		System.out.print("C"+"\t");
		System.out.print("D"+"\t");
		System.out.print("E"+"\n");
		System.out.print("����ʱ�䣺"+"\t");
		
		System.out.print(a.getArriveTime()+"\t");
		System.out.print(b.getArriveTime()+"\t");
		System.out.print(c.getArriveTime()+"\t");
		System.out.print(d.getArriveTime()+"\t");
		System.out.print(e.getArriveTime()+"\n");
		System.out.print("����ʱ�䣺\t");
		System.out.print(a.getServerTime()+"\t");
		System.out.print(b.getServerTime()+"\t");
		System.out.print(c.getServerTime()+"\t");
		System.out.print(d.getServerTime()+"\t");
		System.out.print(e.getServerTime()+"\n");
		System.out.print("���ʱ�䣺"+"\t");
		System.out.print(a.finishTime+"\t");
		System.out.print(b.finishTime+"\t");
		System.out.print(c.finishTime+"\t");
		System.out.print(d.finishTime+"\t");
		System.out.print(e.finishTime+"\n");
		System.out.print("��תʱ�䣺"+"\t");
		System.out.print(a.finishTime-a.getArriveTime()+"\t");
		System.out.print(b.finishTime-b.getArriveTime()+"\t");
		System.out.print(c.finishTime-c.getArriveTime()+"\t");
		System.out.print(d.finishTime-d.getArriveTime()+"\t");
		System.out.print(e.finishTime-e.getArriveTime()+"\n");
	}
	
}





