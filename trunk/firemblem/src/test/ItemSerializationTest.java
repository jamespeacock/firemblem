package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import data.ItemSerializer;

/**
 * Test this program with input in the form of "Vulnerary.i"
 * 
 * @author Cy Neita (stole code from Nilay Kumar, nyahahaha
 * 
 */
public class ItemSerializationTest {

    public static void main(String[] args) {
	ItemSerializer is = new ItemSerializer();
	try {
	    is.load("src/data/items.txt");
	    is.write("src/data/items/");
	    is.read("src/data/items/");
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

}
