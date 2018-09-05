import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumArray extends RecursiveTask<Double> {
	Tree canopy;
	static int SEQ_THRESHOLD = 1;
	int lo;
	int hi;
	Tree[] arr;
	double[][] array;
	double ans=0;

	SumArray(double[][] array,Tree[] arr,int lo ,int hi){this.array=array;this.arr=arr;this.hi=hi;this.lo=lo;}

	public Double compute(){
		if(hi-lo==SEQ_THRESHOLD){
			for (int i=lo;i < hi;++i){
				int loop_x=arr[i].x_pos+arr[i].extent;
				int loop_y=arr[i].y_pos+arr[i].extent;
				//sum the sun exposure on one tree
				//check if tree's edges are within the terain
				if(loop_x>avgSunCalculator.arrXSize){
					loop_x=avgSunCalculator.arrXSize;
				}
				else if(loop_x>avgSunCalculator.arrXSize){
					loop_y=avgSunCalculator.arrYSize;
				}
				//do it
				for(int k=arr[i].y_pos;k<loop_y;k++){
					for(int j=arr[i].x_pos;j<loop_x;j++){
						ans+=array[k][j];
					}
				} 
			}
			return ans;
		}else{
			SumArray left = new SumArray(array,arr,lo,(hi+lo)/2);
			SumArray right = new SumArray(array,arr,(hi+lo)/2,hi);
			left.fork();
			double right_ans=right.compute();
			double  left_ans=left.join();
			return  right_ans+left_ans;
		} 

	}

}