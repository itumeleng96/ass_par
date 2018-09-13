import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.lang.*;

public class avgSunCalculator{
	static String inputFilename;
	static String outputFilename;
	static int arrXSize=0;
	static int arrYSize=0;
	static double [][] array =null;
	static Tree[] treeArr=null;
	static int numOfTrees=0;
	static double tot_ans=0;
	static double [] treeTotals=null;

	public static void main(String args []){
		inputFilename =args[0];  
		outputFilename=args[1];

		Scanner inputStream=null;
		try{
			inputStream=new Scanner(new FileInputStream(inputFilename));
		}
		catch(FileNotFoundException e){
			System.out.println("File error");
		}
		String line=inputStream.nextLine(); //get first line 
		String [] arraySize=line.split(" ");
		arrXSize=Integer.parseInt(arraySize[0]);
		arrYSize=Integer.parseInt(arraySize[1]);
		//get data on oneline of text 

		String [] data = inputStream.nextLine().split(" "); 
		//create a square array
		array = new double[arrYSize][arrXSize];
		int count =0;
		for (int i=0;i<arrYSize;i++){
			for (int j=0;j<arrXSize;j++){
				array[i][j]=Double.parseDouble(data[count]);
				count++;
				System.out.println(count);
			}
		}
		//create tree objects 
		int numOftTrees= Integer.parseInt(inputStream.nextLine());
		numOfTrees= 3000;

		treeArr = new Tree[numOfTrees];
		treeTotals= new double[numOfTrees];
		for(int k=0;k<numOfTrees;k++){
			String [] Treedata= inputStream.nextLine().split(" ");
			Tree tree=new Tree(Integer.parseInt(Treedata[1]),Integer.parseInt(Treedata[0]),Integer.parseInt(Treedata[2]));
			treeArr[k]=tree;
		}
		//time the summing algorithm 
		long timeInit=System.currentTimeMillis(); 
		tot_ans=sum(treeArr);
		long timeFinal=System.currentTimeMillis()-timeInit;
		System.out.println(timeFinal+"ms");

		//write a text file 
		try{
			FileWriter writer = new FileWriter(args[1],true);
			writer.write(String.valueOf(tot_ans/numOfTrees)+"\n");
			writer.write(String.valueOf(numOfTrees)+"\n");
			for(int h=0;h<treeTotals.length;h++){
	 			writer.write(String.valueOf(treeTotals[h]));
	 			writer.write("\r\n");
			}
			writer.close();
	 	}catch(IOException e){
	 		e.printStackTrace();
	 	}
	}
	static double sum(Tree[] TreeArr){
		SumArray t = new SumArray(TreeArr,0,TreeArr.length);
		return ForkJoinPool.commonPool().invoke(t);

	}

}
