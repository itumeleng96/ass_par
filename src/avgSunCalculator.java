import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.lang.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStreamReader;
import java.io.BufferedReader;

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
	static long startTime=0;
	static ForkJoinPool fjpool = new ForkJoinPool();
	static FileInputStream inputStream=null;
	static BufferedReader reader =null;


	public static void main(String args []){
		System.gc();
		inputFilename =args[0];  
		outputFilename=args[1];

		createTerrain(inputFilename);
			                    						   //create 2D array 
		createTrees();                                     //create tree objects 
		//time the summing algorithm 
		tick();
		tot_ans=sum(treeArr);
		System.out.println(tock()+"seconds");

		//write a text file
		writeFileOut(outputFilename);
	}
	static double sum(Tree[] TreeArr){
		SumArray t = new SumArray(TreeArr,0,TreeArr.length);
		return fjpool.invoke(t);

	}
	private static void tick(){
		startTime = System.currentTimeMillis();
	} 
	private static float tock(){
		return (System.currentTimeMillis()-startTime)/1000.0f;
	}
	private static void createTerrain(String inputFilename){
		//open file name 
		try{

			inputStream=new FileInputStream(inputFilename);
			reader = new BufferedReader(new InputStreamReader(inputStream));
		
		
			String line=reader.readLine();                   //first line of input 
			String [] arraySize=line.split(" ");
			arrXSize=Integer.parseInt(arraySize[0]);
			arrYSize=Integer.parseInt(arraySize[1]);

			String [] data = reader.readLine().split(" ");   //2nd line
			array = new double[arrYSize][arrXSize];               //create a square array
			int count =0;
			for (int i=0;i<arrYSize;i++){                         //populate with data
				for (int j=0;j<arrXSize;j++){
					array[i][j]=Double.parseDouble(data[count]);
					count++;
				}
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File error");
		}
		catch (IOException ex) {
            Logger.getLogger(avgSunSeq.class.getName()).log(Level.SEVERE, null, ex);
       	}
	}
	private static void createTrees(){
		try{
			numOfTrees=Integer.parseInt(reader.readLine()); //3rd line
		//vary num of trees 
		//numOfTrees = 10;
			treeTotals = new double[numOfTrees];                  //to store single tree exposures
			treeArr = new Tree[numOfTrees];
			for(int k=0;k<numOfTrees;k++){
				String [] Treedata= reader.readLine().split(" ");
				Tree tree=new Tree(Integer.parseInt(Treedata[1]),Integer.parseInt(Treedata[0]),Integer.parseInt(Treedata[2]));
				treeArr[k]=tree;
				//System.out.println(k);
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File error");
		}
		catch (IOException ex) {
            Logger.getLogger(avgSunSeq.class.getName()).log(Level.SEVERE, null, ex);
       	}
	}
	private static void writeFileOut(String outputFilename){
		try{
			FileWriter writer = new FileWriter(outputFilename,true);
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

}
