package test;

import game.Weapon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import data.WeaponSerializer;

public class Randomness
{
	public static void main(String []args)
	{
		HashMap<String, Weapon> weaponsMap;
		WeaponSerializer ws = new WeaponSerializer();
		weaponsMap = new HashMap<String, Weapon>();
		String s = ""; String ss = "";
		try{
			Scanner reader = new Scanner(new FileReader("src/data/weapons.txt"));
			s = reader.nextLine();
		}
		catch (FileNotFoundException e) {
			System.out.println("asdasd");
		}
		try{
			Scanner console = new Scanner(new FileReader("../data/weapons.txt"));
			while(console.hasNext()){
				s = console.nextLine();
				ss = s.substring(0, s.indexOf(","));
				weaponsMap.put(s.substring(0,s.indexOf(",")), ws.get(ss));
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println(s);
			System.out.println("hee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("hey");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("hwewe");
		}
	}
}