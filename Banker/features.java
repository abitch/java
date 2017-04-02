
import java.util.Scanner;

class features {
	static int Available[] = {3,3,2};
	static int Wor[] = new int[]{Available[0],Available[1],Available[2]};
	procedure pro[] = new procedure[5];
	public features(){		
		pro[0] = new procedure(new int[]{7,5,3},new int[]{0,1,0},new int[]{7,4,3});
		pro[1] = new procedure(new int[]{3,2,2},new int[]{2,0,0},new int[]{1,2,2});
		pro[2] = new procedure(new int[]{9,0,2},new int[]{3,0,2},new int[]{6,0,0});
		pro[3] = new procedure(new int[]{2,2,2},new int[]{2,1,1},new int[]{0,1,1});
		pro[4] = new procedure(new int[]{4,3,3},new int[]{0,0,2},new int[]{4,3,1});
	}
	
	public void menu(){
		while(true){
			System.out.println("\t1、T0时刻安全性");
			System.out.println("\t2、请求资源");
			System.out.println("\t3、当前资源分配情况");
			System.out.println("\t0、退出");
			select();
		}
		
	}
	public void select(){
		Scanner sc = new Scanner(System.in);
		System.out.println("请选择：");
		int a = sc.nextInt();
		
		switch(a){
		case 1:
			if(step1(Wor))
				System.out.println("right");;
			break;
		case 2:
			step2();
			break;
		case 3:
			step3();
			break;
		case 0:
			System.exit(0);
			break;
		default:
			System.out.println("输入错误");
		}
	}
	public boolean step1(int Work[]){
		boolean flag;
		int count = 0;  //循环次数
		int array[] = new int[]{Work[0],Work[1],Work[2]};
		String sec[]=new String[5];
		int num = 0;
		while(true){
			int sum = 0;   //计算还有几个进程没有完成			
			for(int i=0;i<pro.length;i++){
				if(!pro[i].Finish){
					int m = 0;
					//需求资源和可用资源进行比较
					for(int j=0;j<3;j++){		
						if(pro[i].Need[j]<=Work[j]&&pro[i].Need[j]<=pro[i].Max[j]){
							m++;   //若三个资源数都小于可用资源，且小于他们的最大需求量则m+1
						}else{    //只要有一个不符合需求则结束循环找下一个进程
							break;
						}
					}
					if(m==3){
						for(int j=0;j<3;j++){
							Work[j]=pro[i].Allocation[j]+Work[j];
						}
						pro[i].Finish=true;
						sec[num] = "P"+i;
						num++;
					}
				}
				//若这个进程不符合要求，则比较下一个进程
				else{
					continue;
				}
			}
			for(int i=0;i<pro.length;i++){
				if(pro[i].Finish){
					sum++;
				}
			}
			if(sum==5){		
				for(int i=0;i<sec.length;i++){
					System.out.print(sec[i]+"\t");
				}
				System.out.print("\n");
				flag = true;
				Available = array;
				break;
			}
			//如果一轮循环之后sum还等于0的 话就说明可用资源不够用
			count++;
			//循环5次，一次最少完成一个进程，最多循环5次结束进程
			if(count==10){
				flag = false;
				break;
			}
		}
		for(int i=0;i<pro.length;i++){
			pro[i].Finish = false;
		}
		return flag;		
	}
	public void step2(){
		Wor = new int[]{Available[0],Available[1],Available[2]};
		
		Scanner sa = new Scanner(System.in);
		System.out.println("请输入要操作的进程：");
		int a = sa.nextInt();
		Scanner sc = new Scanner(System.in);
		int[] ia = new int[3]; 
		System.out.print("输入请求的资源用空格隔开:"); 
		for(int i = 0;i<ia.length;i++){ 
			ia[i] =sc.nextInt();
		}
		
		boolean one = pro[a].compareNeed(ia);
		if(one){
			boolean two = pro[a].compareAvailable(ia,Wor);
			if(two){
				for(int j=0;j<Wor.length;j++){
					Wor[j] = Wor[j] - ia[j];
					
					pro[a].Need[j] = pro[a].Need[j] - ia[j];
					pro[a].Allocation[j] = pro[a].Allocation[j] + ia[j];					
				}			
				if(!step1(Wor)){
					for(int j=0;j<Wor.length;j++){
						pro[a].Need[j] = pro[a].Need[j] + ia[j];
						pro[a].Allocation[j] = pro[a].Allocation[j] - ia[j];					
					}
					System.out.println("wrong");
				}
				
			}else{
				System.out.println("请求的资源大于可利用的资源");
			}
		}else{
			System.out.println("请求的资源大于所需要的资源");
		}
		
	}
	public void step3(){
			String str[] = {"P0","P1","P2","P3","P4"};
			for(int i=0;i<5;i++){
				System.out.print(str[i]+"\t");
				for(int j=0;j<3;j++){
					System.out.print(pro[i].Max[j]+" ");
				}
				System.out.print("\t");
				for(int j=0;j<3;j++){
					System.out.print(pro[i].Allocation[j]+" ");
				}
				System.out.print("\t");
				for(int j=0;j<3;j++){
					System.out.print(pro[i].Need[j]+" ");
				}
				if(i==0){
					System.out.print("\t");
					for(int m=0;m<3;m++)
					{
						System.out.print(Available[m]+" ");
					}
				}
				System.out.println();
			}
		
	}
}
