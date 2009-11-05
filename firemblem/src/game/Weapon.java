package game;

import java.io.Serializable;

import data.WeaponType;

public class Weapon implements Serializable {

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
	public Weapon(String name, WeaponType type, int attack, int hit,
			int crit, int weight, int durability, int range) {
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
}
