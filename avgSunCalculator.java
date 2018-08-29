import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class avgSunCalculator{
	static String inputFilename;
	static String outputFilename;

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
		String line=inputStream.nextLine();
		System.out.println(line);
	}

}