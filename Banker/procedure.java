
class procedure {
	public int Max[];
	public int Allocation[];
	public int Need[];
	boolean Finish = false;
	public procedure(int[] max, int[] allocation, int[] need) {
		Max = max;
		Allocation = allocation;
		Need = need;
	}
	public boolean compareNeed(int[] Request){
		boolean flag = true;
		for(int j=0;j<Need.length;j++){
			if(Request[j]<=Need[j]){
				continue;
			}else{
				flag = false;
				break;
			}
		}
		return flag;
	}
	public boolean compareAvailable(int[] Request,int[] Available){
		boolean flag = true;
		for(int j=0;j<Available.length;j++){
			if(Request[j]<=Available[j]){
				continue;
			}else{
				flag = false;
				break;
			}
		}
		return flag;
	}
}
