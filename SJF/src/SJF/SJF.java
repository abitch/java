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
		//编程思想：首先将对象数组按到达时间排序，将第一个到达的完成时间等于服务时间，剩下的放入sort数组中
	//然后根据时间的演进，陆续把各个进程的服务时间放入server数组，然后用自定义的min2函数不断的找server数组的最小值，
	//返回索引，算出完成时间，将索引值设为-1，继续后一个计算
	//返回数组最小值
	public static int min2(int[] array){  
        int minIndex = 0;  
        for(int j=0;j<array.length;j++)			//避免索引值指向数组值为-1的位置
        {
        	if(array[j]!=-1){
        		minIndex = j;
        		break;
        	}
        }
        //找出最小值且不能为0或-1
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
		Process2 sort[]={b,c,d,e};   //排序后的数组
		int server[] = new int[4];
		int i = 0;
		int running_flag = 1;
		
		int p = a.finishTime;//p是代表前一个的完成时间
		
		while(i!=-1){
			//将到时的进程放入数组；
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
				//判断数组里是否有正值，即是否有进程到达
				for(int kong=0;kong<server.length;kong++){
					if(server[kong]>0){
						int n = min2(server);		//将数组最小值索引传递给n				
						p = i + server[n];
						sort[n].finishTime = p;		//算出完成时间
						server[n] = -1;				//代表去除数组中的值
						running_flag = 1;
						break;
					}		
				}							
			}
			i++;
			//当sort数组中的所有完成时间不为0时循环结束
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
		
		System.out.print("进程名称："+"\t");
		System.out.print("A"+"\t");
		System.out.print("B"+"\t");
		System.out.print("C"+"\t");
		System.out.print("D"+"\t");
		System.out.print("E"+"\n");
		System.out.print("到达时间："+"\t");
		
		System.out.print(a.getArriveTime()+"\t");
		System.out.print(b.getArriveTime()+"\t");
		System.out.print(c.getArriveTime()+"\t");
		System.out.print(d.getArriveTime()+"\t");
		System.out.print(e.getArriveTime()+"\n");
		System.out.print("服务时间：\t");
		System.out.print(a.getServerTime()+"\t");
		System.out.print(b.getServerTime()+"\t");
		System.out.print(c.getServerTime()+"\t");
		System.out.print(d.getServerTime()+"\t");
		System.out.print(e.getServerTime()+"\n");
		System.out.print("完成时间："+"\t");
		System.out.print(a.finishTime+"\t");
		System.out.print(b.finishTime+"\t");
		System.out.print(c.finishTime+"\t");
		System.out.print(d.finishTime+"\t");
		System.out.print(e.finishTime+"\n");
		System.out.print("周转时间："+"\t");
		System.out.print(a.finishTime-a.getArriveTime()+"\t");
		System.out.print(b.finishTime-b.getArriveTime()+"\t");
		System.out.print(c.finishTime-c.getArriveTime()+"\t");
		System.out.print(d.finishTime-d.getArriveTime()+"\t");
		System.out.print(e.finishTime-e.getArriveTime()+"\n");
	}
	
}





