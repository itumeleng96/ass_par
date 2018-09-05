import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class avgSunSeq{
	Tree tree;
	static String inputFilename;
	static String outputFilename;
	static double [][] array =null;
	static Tree [] treeArr=null;

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
		int arrXSize=Integer.parseInt(arraySize[0]);
		int arrYSize=Integer.parseInt(arraySize[1]);
		//get data on oneline of text 

		String [] data = inputStream.nextLine().split(" "); 
		//create a square array
		array = new double[arrYSize][arrXSize];
		int count =0;
		for (int i=0;i<arrYSize;i++){
			for (int j=0;j<arrXSize;j++){
				array[i][j]=Double.parseDouble(data[count]);
				count++;
			}
		}

		//create tree array
		//create tree objects 
		int numOfTrees= Integer.parseInt(inputStream.nextLine());
		treeArr = new Tree[numOfTrees];
		for(int k=0;k<=numOfTrees;k++){
			String [] Treedata= inputStream.nextLine().split(" ");
			Tree tree=new Tree(Integer.parseInt(Treedata[0]),Integer.parseInt(Treedata[1]),Integer.parseInt(Treedata[2]));
			treeArr[k]=tree;
		}

		sumArray(array);
	}
	public static void sumArray(double array[][]){
		System.out.println(array);
	}


}
