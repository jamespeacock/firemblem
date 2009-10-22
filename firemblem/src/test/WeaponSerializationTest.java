package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import data.WeaponSerializer;

/** Test this program with input in the form of "Fenrir.w"
 * 
 * @author Nilay Kumar
 *
 */
public class WeaponSerializationTest {

	public static void main(String[] args){
		WeaponSerializer ws = new WeaponSerializer();
		try {
			ws.load("src/data/weapons.txt");
			ws.write("src/data/weapons/");
			ws.read("src/data/weapons/");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
