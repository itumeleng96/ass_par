import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumArray extends RecursiveTask<Double> {
	
	static int SEQ_THRESHOLD = 2
	int lo;
	int hi;
	Tree[] arr;

	SumArray(Tree[] arr,int lo,int hi){this.lo=lo,this.hi=hi,this.arr=arr}

	public Double compute(){

	
	}

}