import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Arrays;

public class avgSunSeq{
	Tree tree;
	static String inputFilename;
	static String outputFilename;
	static double [][] array =null;
	static Tree [] treeArr=null;
	static int arrXSize;
	static int arrYSize;
	static double tot_ans=0;
	static int numOfTrees;
	static double [] treeTotals=null;

	public static void main(String args []){
		inputFilename =args[0];                            //text file input name  
		outputFilename=args[1];                            //text file output name 

		Scanner inputStream=null;
		try{
			inputStream=new Scanner(new FileInputStream(inputFilename));
		}
		catch(FileNotFoundException e){
			System.out.println("File error");
		}
		String line=inputStream.nextLine();                   //first line of input 
		String [] arraySize=line.split(" ");
		arrXSize=Integer.parseInt(arraySize[0]);
		arrYSize=Integer.parseInt(arraySize[1]);

		String [] data = inputStream.nextLine().split(" ");   //2nd line
		
		array = new double[arrYSize][arrXSize];               //create a square array
		int count =0;
		for (int i=0;i<arrYSize;i++){                         //populate with data
			for (int j=0;j<arrXSize;j++){
				array[i][j]=Double.parseDouble(data[count]);
				count++;
				System.out.println(count);
			}
		}

		//create tree objects 
		numOfTrees= Integer.parseInt(inputStream.nextLine()); //3rd line
		//vary num of trees 
		//numOfTrees = 10;
		treeTotals = new double[numOfTrees];                  //to store single tree exposures
		treeArr = new Tree[numOfTrees];
		for(int k=0;k<numOfTrees;k++){
				String [] Treedata= inputStream.nextLine().split(" ");
				Tree tree=new Tree(Integer.parseInt(Treedata[1]),Integer.parseInt(Treedata[0]),Integer.parseInt(Treedata[2]));
				treeArr[k]=tree;
			
		}

		sumArray(array);
		//write to text file output
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
	public static void sumArray(double array[][]){
		//sequential addition of Trees 
		for (int i=0;i < treeArr.length;i++){
				int loop_x=treeArr[i].x_pos+treeArr[i].extent;
				int loop_y=treeArr[i].y_pos+treeArr[i].extent;
				//sum the sun exposure on one tree
				//check if tree's edges are within the terain
				if(loop_x>arrXSize){
					loop_x=arrXSize;
				}
				if(loop_y>arrYSize){
					loop_y=arrYSize;
				}
				//do it
				//variable for single tree exposure
				double  single_ans=0;
				for(int k=treeArr[i].y_pos;k<loop_y;k++){
					for(int j=treeArr[i].x_pos;j<loop_x;j++){
						single_ans+=array[k][j];
					}
				} 
				treeTotals[i]=single_ans;
				tot_ans+=single_ans;
			}
	}


}
