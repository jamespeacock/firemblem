package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Brian Clanton
 * 10/24/09
 * 
 * This program creates a text file containing the appropriate switch statement for the Character constructor.
 * I got lazy and didn't feel like writing it all out.
 * For now, I only put the basic types for the enums (there are no upper class character).
 * I also haven't filled in the statNums table because I don't know the stats of the characters.
 */
public class StatGenerator {

	public static void main(String[] args) {
		PrintWriter outputStream = null;
		String [] statNames = {"lvl", "hp", "str", "skl", "spd", "luc", "def", "res", "con", "mov", "exp"};
		String [] enumNames = {"MYR", "LORD", "KNG", "FGT", "ARCH", "MAGE", "CLR"};
		int [][] statNums = new int [enumNames.length][statNames.length];
		int value;
		String stuff = "";
		
		try{
			Scanner r = new Scanner (new FileReader("src/data/basestats.txt"));
			while(!stuff.equals("start")){
				stuff = r.nextLine();
			}//end while
			
			for(int i = 0; i < statNums.length; i++)
				for(int j = 0; j < statNums[i].length - 1 && r.hasNextInt(); j++){
					value = r.nextInt();
					statNums[i][j] = value;
				}//end for
			r.close();
			System.out.println("done reading");
		}//end try
		catch (FileNotFoundException e){
			System.out.println("File not found");
		}//end catch
		
		try {
			outputStream = new PrintWriter(new FileOutputStream("src/data/SwitchStatement.txt"));
			outputStream.println("switch(e){");
			int i, j;
			for(i = 0; i < enumNames.length; i++){
				outputStream.println("\tcase " + enumNames[i] + ":");
				for(j = 0; j < statNames.length; j++)
					outputStream.println("\t\t" + statNames[j] + " = " + statNums[i][j] + ";");
				outputStream.println("\tbreak;\n");
			}//end for
			outputStream.print("}//end switch");
			outputStream.close();
			System.out.println("done writing");
		}
		catch (FileNotFoundException e) {
			System.out.println("Error opening the fine out.txt");
			System.exit(0);
		}
		
		System.out.println("done");

	}

}
