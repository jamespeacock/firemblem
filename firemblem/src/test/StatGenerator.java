package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

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
		String [] statNames = {"hp", "pow", "def", "hit", "luc", "lvl", 
				"exp", "mov", "skl", "spd", "res", "attribute"};
		String [] enumNames = {"MYR", "LORD", "KNG", "FGT", "ARCH", "MAGE", "CLR"};
		int [][] statNums = new int [enumNames.length][statNames.length];
		
		try {
			outputStream = new PrintWriter(new FileOutputStream("src/data/stats.txt"));
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
		}
		catch (FileNotFoundException e) {
			System.out.println("Error opening the fine out.txt");
			System.exit(0);
		}
		
		System.out.println("done");

	}

}
