import java.util.LinkedList;
import java.util.Scanner;

class Config {
	public int memory;
	public int min;
	LinkedList<obj> linkedList = new LinkedList<obj>();
	public void menu(){
		for(int i=0;i<20;i++){
			System.out.print("*");
		}
		System.out.println();
		System.out.println("1.初始化");
		System.out.println("2.分配");
		System.out.println("3.回收");
		System.out.println("4.显示");
		System.out.println("0.退出");
		for(int i=0;i<20;i++){
			System.out.print("*");
		}
		System.out.println();
		Scanner sb = new Scanner(System.in);
		System.out.println("请选择：");
		int b = sb.nextInt();			
		switch(b){
		case 1:
			init();
			break;
		case 2:
			distribution();
			break;
		case 3:
			recovery();
			break;
		case 4:
			show();
		case 0:
			System.exit(0);
			break;
		default:
			System.out.println("输入错误");
		}
	}
	
	public void select(){	
		System.out.println("请输入内存大小：");
		Scanner sc = new Scanner(System.in);
		memory = sc.nextInt();
		//sc.close();
		System.out.println("请设置minsize：");
		Scanner sa = new Scanner(System.in);	
		min = sa.nextInt();
		//sa.close();		
		obj temp = new obj();
		temp.setLength(memory);
		temp.setStart(0);
		temp.setStop();
		temp.setName("");
		linkedList.add(temp);	
		while(true){
			menu();			
		}		
	}
	public void init(){		
		
		while(true){
			obj temp1 = new obj();
			int i=0;
			Scanner sa = new Scanner(System.in);
			System.out.println("请输入申请空间大小：");
			int size = sa.nextInt();
			for(;i<linkedList.size();i++){
				
				if(!linkedList.get(i).isStatus()){
					if(size<=linkedList.get(i).getLength()){
						
						temp1.setLength(size);	
						Scanner sb = new Scanner(System.in);
						System.out.println("请输入分区名字：");
						String name = sb.nextLine();			
						temp1.setName(name);
						temp1.setStatus(true);
						temp1.setStart(linkedList.get(i).getStart());
						temp1.setStop();
						linkedList.get(i).setLength(linkedList.get(i).getLength()-size);
						if(i==0)
							linkedList.get(i).setStart(size);
						else
							linkedList.get(i).setStart(linkedList.get(i-1).getStop()+1+size);
						linkedList.get(i).setName("");
						linkedList.add(i,temp1);
						
						break;
					}
					
				}				
			}
						
			if(i==linkedList.size()){
				System.out.println("剩余空间不足！");
				break;
			}
			System.out.println("是否继续申请空间y/n?");
			Scanner sd = new Scanner(System.in);
			char ch = sd.next().charAt(0);
			if(ch=='y'){
				continue;
			}else{
				break;
			}
		}		
	}
	
	public void distribution(){
		while(true){
			obj temp2 = new obj();
			int i=0;
			Scanner sa = new Scanner(System.in);
			System.out.println("请输入申请空间大小：");
			int size = sa.nextInt();			
			for(;i<linkedList.size();i++){
				
				if(!linkedList.get(i).isStatus()){
					if(size<=linkedList.get(i).getLength()){						
						Scanner sb = new Scanner(System.in);
						System.out.println("请输入分区名字：");
						String name = sb.nextLine();			
						temp2.setName(name);
						temp2.setStatus(true);
						temp2.setStart(linkedList.get(i).getStart());						
						if(linkedList.get(i).getLength()-size>=min){	
							linkedList.get(i).setLength(linkedList.get(i).getLength()-size);
							if(i==0)
								linkedList.get(i).setStart(size);
							else
								linkedList.get(i).setStart(linkedList.get(i-1).getStop()+1+size);
							temp2.setLength(size);	
							temp2.setStop();
							linkedList.add(i,temp2);
						}else{
							linkedList.get(i).setStatus(true);
							linkedList.get(i).setName(name);
						}
												
						break;
					}					
				}	
				
			}
				
			if(i==linkedList.size()){
				System.out.println("剩余空间不足！");
				break;
			}
			System.out.println("是否继续申请空间y/n?");
			Scanner sd = new Scanner(System.in);
			char ch = sd.next().charAt(0);
			if(ch=='y'){
				continue;
			}else{
				break;
			}
		}
		menu();
	}
	public void operate(int i){
		linkedList.get(i).setName("");
		linkedList.get(i).setStatus(false);
		linkedList.get(i).setStop();
	}
	public void recovery(){
		System.out.println("请输入要回收的空间：");
		Scanner sa = new Scanner(System.in);
		String name = sa.nextLine();
		for(int i=0;i<linkedList.size();i++){
			if(name.equals(linkedList.get(i).getName())){
				if(i==0){
					if(!linkedList.get(i+1).isStatus()){
						linkedList.get(i).setLength(linkedList.get(i).getLength()+linkedList.get(i+1).getLength());
						operate(i);
						linkedList.remove(i+1);
					}else{
						operate(i);
					}
					break;
				}
				else if(!linkedList.get(i+1).isStatus()&&!linkedList.get(i-1).isStatus()){
					linkedList.get(i-1).setLength(linkedList.get(i-1).getLength()+linkedList.get(i).getLength()+linkedList.get(i+1).getLength());
					operate(i-1);
					linkedList.remove(i+1);
					linkedList.remove(i);
					break;
				}
				else if(!linkedList.get(i+1).isStatus()){
					linkedList.get(i).setLength(linkedList.get(i+1).getLength()+linkedList.get(i).getLength());
					linkedList.remove(i+1);
					operate(i);
					break;
				}
				else if(!linkedList.get(i-1).isStatus()){
					linkedList.get(i-1).setLength(linkedList.get(i-1).getLength()+linkedList.get(i).getLength());
					operate(i-1);
					linkedList.remove(i);
					break;
				}
				else{
					operate(i);
					break;
				}				
			}
		}
		menu();
	}
	public void show(){
		String array[] = {"分区号","分区","起始地址","结束地址","大小","当前状态"};
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+"\t");
		}
		System.out.println();
		for(int j=0;j<linkedList.size();j++){
			System.out.print(j+1+"\t");
			System.out.print(linkedList.get(j).getName()+"\t");
			System.out.print(linkedList.get(j).getStart()+"\t");
			System.out.print(linkedList.get(j).getStop()+"\t");
			System.out.print(linkedList.get(j).getLength()+"\t");
			if(linkedList.get(j).isStatus())
				System.out.print("已分配\n");
			else
				System.out.print("空闲\n");
		}
		menu();
	}
}