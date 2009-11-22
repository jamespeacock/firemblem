package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.Scanner;

import data.ParseMethods;
import data.WeaponType;

public class Weapon implements Serializable, ItemInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8648673134412314167L;

	//Brian Clanton changed hit and crit from floats to ints to match his pattern for percentages.
	public String name;
	public WeaponType type;
	public int hit;
	public int crit;
	public int attack;
	public int weight;
	public int durability;
	public int range;

	/**
	 * @param name
	 * @param type
	 * @param hit
	 * @param crit
	 * @param attack
	 * @param weight
	 * @param durability
	 * @param range
	 */
	// Brian Clanton made this temporarily public
	// also changed order of parameters for testing
	public Weapon(String name, WeaponType type, int hit, int crit,
			int attack, int weight, int durability, int range) {
		super();
		this.name = name;
		this.type = type;
		this.hit = hit;
		this.crit = crit;
		this.attack = attack;
		this.weight = weight;
		this.durability = durability;
		this.range = range;
	}
	

	public Weapon (String s){
		try{
			Scanner console = new Scanner(new FileReader("src/data/weapons.txt"));
			String line = console.nextLine();
			while(console.hasNext() && !line.contains(s+",")){
				line = console.nextLine();
			}
			if(line.contains("Staff")){
				name = s;
				line = line.substring(line.indexOf(",")+1).trim();
				attack = Integer.parseInt(line.substring(0, line.indexOf(",")));
				line = line.substring(line.indexOf(",")+1).trim();
				durability = Integer.parseInt(line.substring(0, line.indexOf(",")));
				line = line.substring(line.indexOf(",")+1).trim();
				type = ParseMethods.toWeaponType(line.substring(0,line.indexOf(",")));
				line = line.substring(line.indexOf(",")+1).trim();
				range = Integer.parseInt(line.trim());
				hit = -1;
				crit = -1;
				weight = -1;
			}
			else{
				name = s;
				line = line.substring(line.indexOf(",")+1).trim();
				attack = Integer.parseInt(line.substring(0, line.indexOf(",")));
				line = line.substring(line.indexOf(",")+1).trim();
				hit = Integer.parseInt(line.substring(0, line.indexOf(",")));
				line = line.substring(line.indexOf(",")+1).trim();
				crit = Integer.parseInt(line.substring(0, line.indexOf(",")));
				line = line.substring(line.indexOf(",")+1).trim();
				weight = Integer.parseInt(line.substring(0, line.indexOf(",")));
				line = line.substring(line.indexOf(",")+1).trim();
				durability = Integer.parseInt(line.substring(0, line.indexOf(",")));
				line = line.substring(line.indexOf(",")+1).trim();
				type = ParseMethods.toWeaponType(line.substring(0,line.indexOf(",")));
				line = line.substring(line.indexOf(",")+1).trim();
				range = Integer.parseInt(line.trim());
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Error");
			System.exit(0);
		}
	}



	public static Weapon forgeWeapon(String name, WeaponType type, int hit,
			int crit, int attack, int weight, int durability, int range) {
		if (type != WeaponType.STAFF)
			return new Weapon(name, type, hit, crit, attack, weight,
					durability, range);
		else
			return new Weapon(name, type, -1, -1, attack, -1, durability,
					range);
	}

	@Override
	public String toString() {
		String s = "";
		s += "Name: " + name;
		s += "\nType: " + type.toString();
		s += "\nAttack: " + attack;
		s += "\nHit: " + hit + "%";
		s += "\nCritical: " + crit + "%";
		s += "\nWeight: " + weight;
		s += "\nDurability: " + durability;
		s += "\nRange: " + range;
		return s;
	}
	
	public int use(){
		durability--;
		//this if will be changed once the appropriate enum is added.
		if(durability > 0)
			return 1;
		else
			return 0;
	}
}
