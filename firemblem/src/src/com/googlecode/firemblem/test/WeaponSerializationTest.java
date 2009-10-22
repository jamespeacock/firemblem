package com.googlecode.firemblem.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.googlecode.firemblem.data.WeaponSerializer;

/** Test this program with input in the form of "Fenrir.w"
 * 
 * @author Nilay Kumar
 *
 */
public class WeaponSerializationTest {

	public static void main(String[] args){
		WeaponSerializer ws = new WeaponSerializer();
		try {
			ws.load("src/com/googlecode/firemblem/data/weapons.txt");
			ws.write("src/com/googlecode/firemblem/data/weapons/");
			ws.read("src/com/googlecode/firemblem/data/weapons/");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
