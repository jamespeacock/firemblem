package game;

import java.io.Serializable;

import data.WeaponType;

public class Weapon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8648673134412314167L;
	
	private String name;
	private WeaponType type;
	private float hit;
	private float crit;
	private int attack;
	private int weight;
	private int durability;
	private int range;

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
	private Weapon(String name, WeaponType type, float hit, float crit,
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

	public static Weapon forgeWeapon(String name, WeaponType type, float hit,
			float crit, int attack, int weight, int durability, int range) {
		if (type != WeaponType.STAFF)
			return new Weapon(name, type, hit, crit, attack, weight,
					durability, range);
		else
			return new Weapon(name, type, -1.0f, -1.0f, attack, -1, durability,
					range);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public WeaponType getType() {
		return type;
	}

	/**
	 * @return the hit
	 */
	public float getHit() {
		return hit;
	}

	/**
	 * @return the crit
	 */
	public float getCrit() {
		return crit;
	}

	/**
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @return the durability
	 */
	public int getDurability() {
		return durability;
	}

	/**
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

	@Override
	public String toString() {
		String s = "";
		s += "Name: " + name + "\n";
		s += "Type: " + type.toString() + "\n";
		s += "Attack: " + attack + "\n";
		s += "Hit: " + hit + "\n";
		s += "Critical: " + crit + "\n";
		s += "Weight: " + weight + "\n";
		s += "Durability: " + durability + "\n";
		s += "Range: " + range;
		return s;
	}
}
