package test;

import java.util.*;
import java.io.*;

import data.CharacterType;

public class Randomness
{
	public static void main(String []args)
	{
		try{
			Scanner console = new Scanner(new FileReader("src/data/basestats.txt"));
			CharacterType e = CharacterType.MAGE;
			String s = "";
			while(!console.nextLine().contains("start"))
				s = console.nextLine();
			System.out.println(s);
			while(!s.contains(e.name()) && console.hasNext()){
				s = console.nextLine();
			}
			s = s.substring(s.indexOf("\t")).trim();
			System.out.println(s);
			console.next();
			String name = console.next();
			System.out.println(name);
			int lvl = console.nextInt();
			int hp = console.nextInt();
			int str = console.nextInt();
			int skl = console.nextInt();
			int spd = console.nextInt();
			int luc = console.nextInt();
			int def = console.nextInt();
			int res = console.nextInt();
			int con = console.nextInt();
			int mov = console.nextInt();
		}
		catch (FileNotFoundException e){
			System.out.println("not found");
		}
	}
}