import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumArray extends RecursiveTask<Double> {
	static int SEQ_THRESHOLD = 50000;
	int lo;
	int hi;
	Tree[] treeArr;
	Double ans=0.0;


	SumArray(Tree[] treeArr,int lo ,int hi){this.treeArr=treeArr;this.hi=hi;this.lo=lo;}

	protected Double compute(){
		if(hi-lo <= SEQ_THRESHOLD){
			double total=0;
			for (int i=lo;i < hi;i++){
				int loop_x=treeArr[i].x_pos+treeArr[i].extent;
				int loop_y=treeArr[i].y_pos+treeArr[i].extent;
				//sum the sun exposure on one tree
				//check if tree's edges are within the terain
				if(loop_x>avgSunCalculator.arrXSize){
					loop_x=avgSunCalculator.arrXSize;
				}
				if(loop_y>avgSunCalculator.arrYSize){
					loop_y=avgSunCalculator.arrYSize;
				}
				//do it
				double single_ans=0;
				for(int k=treeArr[i].y_pos;k<loop_y;k++){
					for(int j=treeArr[i].x_pos;j<loop_x;j++){
						single_ans+=avgSunCalculator.array[k][j];
					}
				}
			 avgSunCalculator.treeTotals[i]=single_ans;
			 total+=single_ans;
			}
			return total;
		}else{
			SumArray left = new SumArray(treeArr,lo,(hi+lo)/2);
			SumArray right = new SumArray(treeArr,(hi+lo)/2,hi);
			
			left.fork();
			Double right_ans=right.compute();
			Double  left_ans=left.join();
			return  right_ans+left_ans;
		} 

	}

}