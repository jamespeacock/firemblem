package data;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WeaponTextConverter {

    public static void main(String[] args) {
	readFile();
	System.out.println("done");
    }
    
    public static void readFile(){
	try {
	    Scanner reader = new Scanner(new FileReader("src/data/weapons.txt"));
	    PrintWriter outputStream = new PrintWriter(new FileOutputStream("src/data/weapons.xml"));
	    outputStream.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
	    outputStream.println("<weaponList>\n");
	    String [] line;
	    while(reader.hasNextLine()){
		outputStream.println("\t<weapon>");
		line = reader.nextLine().split(", ");
		outputStream.println("\t\t" + "<name>" + line[0] + "</name>");
		outputStream.println("\t\t" + "<attack>" + line[1] + "</attack>");
		if(line.length == 5){
		    outputStream.println("\t\t" + "<hit>" + "-1" + "</hit>");
		    outputStream.println("\t\t" + "<crit>" + "-1" + "</crit>");
		    outputStream.println("\t\t" + "<weight>" + "-1" + "</weight>");
		    outputStream.println("\t\t" + "<durability>" + line[2] + "</durability>");
		    outputStream.println("\t\t" + "<type>" + line[3] + "</type>");
		    outputStream.println("\t\t" + "<range>" + line[4] + "</range>");
		}//end if
		else {
		    outputStream.println("\t\t" + "<hit>" + line[2] + "</hit>");
		    outputStream.println("\t\t" + "<crit>" + line[3] + "</crit>");
		    outputStream.println("\t\t" + "<weight>" + line[4] + "</weight>");
		    outputStream.println("\t\t" + "<durability>" + line[5] + "</durability>");
		    outputStream.println("\t\t" + "<type>" + line[6] + "</type>");
		    outputStream.println("\t\t" + "<range>" + line[7] + "</range>");
		}//end else
		outputStream.println("\t</weapon>\n");
	    }//end while
	    outputStream.print("</weaponList>");
	    outputStream.close();
	}
	catch(IOException e){
	    System.out.println("error reading and writing file.");
	}
    }

}
