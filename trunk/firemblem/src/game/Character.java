package game;
/*
 * Brian Clanton
 * 10/24/09
 * 
 * Formula Source: http://www.gamefaqs.com/portable/gbadvance/file/563015/36403
 */
import java.awt.Point;
import java.io.*;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.ImageIcon;

//this import will change
import test.AttackPhaseTester;

import data.CharacterType;
import data.StatType;
import data.WeaponType;

/**
 * 
 * @author Brian Clanton
 *
 */
public class Character {
	public String name;
	public Weapon [] inventory = new  Weapon [5];
	public int hp, str, def, luc, lvl, exp, mov, skl, spd, res, con;
	public int index = 0;
	public StatType status;
	public Point position;
	public Weapon activeWeapon = null;
	public ImageIcon classImage;
	public CharacterType type;

	public Character(){
		name = "";
		inventory = null;
		hp = 0;
		def = 0;
		luc = 0;
		lvl = 0;
		exp = 0;
		mov = 0;
		skl = 0;
		spd = 0;
		res = 0;
		con = 0;
		position = new Point(0,0);
		status = StatType.NONE; 
		type = null;
	}//end default constructor

	public Character(CharacterType e, int x, int y) {
		try{
			Scanner console = new Scanner(new FileReader("src/data/basestats.txt"));
			String s = "";
			while(!console.nextLine().contains("start"))
				s = console.nextLine();
			while(!s.contains(e.name()) && console.hasNext()){
				s = console.nextLine();
			}
			s = s.substring(s.indexOf("\t")).trim();
			name = s.substring(0, s.indexOf("\t"));
			s = s.substring(s.indexOf("\t")).trim();
			lvl = Integer.parseInt(s.substring(0, s.indexOf("\t")));
			s = s.substring(s.indexOf("\t")).trim();
			hp = Integer.parseInt(s.substring(0, s.indexOf("\t")));
			s = s.substring(s.indexOf("\t")).trim();
			str = Integer.parseInt(s.substring(0, s.indexOf("\t")));
			s = s.substring(s.indexOf("\t")).trim();
			skl = Integer.parseInt(s.substring(0, s.indexOf("\t")));
			s = s.substring(s.indexOf("\t")).trim();
			spd = Integer.parseInt(s.substring(0, s.indexOf("\t")));
			s = s.substring(s.indexOf("\t")).trim();
			luc = Integer.parseInt(s.substring(0, s.indexOf("\t")));
			s = s.substring(s.indexOf("\t")).trim();
			def = Integer.parseInt(s.substring(0, s.indexOf("\t")));
			s = s.substring(s.indexOf("\t")).trim();
			res = Integer.parseInt(s.substring(0, s.indexOf("\t")));
			s = s.substring(s.indexOf("\t")).trim();
			con = Integer.parseInt(s.substring(0, s.indexOf("\t")));
			s = s.substring(s.indexOf("\t")).trim();
			mov = Integer.parseInt(s.substring(0).trim());
			exp = 0;
		}
		catch (FileNotFoundException q){
		}
		position = new Point(x,y);
		status = StatType.NONE;
		classImage = new ImageIcon("images/" + name.toLowerCase() + ".gif");
		type = e;
	}//end constructor

	/**
	 * This method returns the terrain of the space that the unit is occupying.
	 * @param m
	 * @return terrain
	 */
	public Terrain getTerrain(Map m){
		return m.grid[position.x][position.y];
	}//end getTerrain

	/**
	 * 
	 * @param a
	 */
	public void addWeapon(Weapon a){
		inventory[index] = a;
		index++;
	}//end addWeapon

	/**
	 * Gets attack speed.
	 * @return attack speed
	 */
	public int getAttackSpeed(){
		return spd + con - activeWeapon.weight;
	}//end getAttackSpeed

	/**
	 * Returns true if the Character can perform a double attack. In order for this
	 * to happen, the Character's attack speed must be a least 4 points higher than
	 * the opponent's attack speed.
	 * @param target
	 * @return true if double attack, false if not
	 */
	public boolean doubleAttack(Character target){
		return (getAttackSpeed() - target.getAttackSpeed()) >= 4;
	}//end doubleAttack

	/**
	 * Sets a Character's equipped weapon.
	 * @param a
	 */
	public void setActiveWeapon(int a){
		activeWeapon = inventory[a];
	}//end setActiveWeapon

	/**
	 * Gets hit rate.
	 * will return number that represents a percentage
	 * ex: return 20 = 20%
	 * @param target
	 * @return hit rate
	 */
	public int getHitRate(Character target){
		return (int) ((int) 2 * skl + .5 * luc + activeWeapon.hit + getTriangleHitBoost(target) 
				/* + bonuses*/ - target.getEvade());
	}//end getHitRate

	/**
	 * Gets evade rate.
	 * will return number that represents a percentage
	 * ex: return 20 = 20%
	 * @return
	 */
	public int getEvade(){
		int e = 2 * getAttackSpeed() + luc;
		return getTerrain(AttackPhaseTester.g).getEvadeBonus(e);
	}//end getEvade

	/**
	 * Checks to see if the equipped weapon is magic.
	 * @return true if weapon is not magic, false if weapon is magic
	 */
	private boolean isNotMagic(){
		boolean check;
		check = activeWeapon.type != WeaponType.ANIMA && activeWeapon.type != WeaponType.LIGHT 
		&& activeWeapon.type != WeaponType.DARK;
		return check;
	}//end isNotMagic

	/**
	 * Gets damage.
	 * @param target
	 * @return damage
	 */
	public int getDamage(Character target){
		if(isNotMagic())
			return str + activeWeapon.attack + getTriangleAttackBoost(target) 
			/* + bonuses*/ - target.getEffectiveDefense();
		else
			return str + activeWeapon.attack /* + bonuses*/ - target.getEffectiveResistance();
	}//end getDamage

	/**
	 * Gets defense in battle.
	 * @return battle defense
	 */
	public int getEffectiveDefense(){
		return getTerrain(AttackPhaseTester.g).getDefenseBonus(def);
	}//end getEffectiveDefense

	/**
	 * Gets resistance in battle.
	 * @return battle resistance
	 */
	public int getEffectiveResistance(){
		return getTerrain(AttackPhaseTester.g).getDefenseBonus(res);
	}//end getEffectiveResistance

	/**
	 * Gets critical evade rate.
	 * will return number that represents a percentage
	 * ex: return 20 = 20%
	 * @return critical evade
	 */
	public int getCriticalEvade(){
		return getTerrain(AttackPhaseTester.g).getEvadeBonus(luc) /* + bonuses*/;
	}//end getCriticalEvade

	/**
	 * Gets critical hit rate.
	 * will return number that represents a percentage
	 * ex: return 20 = 20%
	 * @param target
	 * @return critical rate
	 */
	public int getCriticalRate(Character target){
		return (int) .5 * skl + activeWeapon.crit /* + bonuses*/ - target.getCriticalEvade();
	}//end getCriticalRate

	public int getStaffEvade(Point t){
		return res * 5 + (int) (position.distance(t)) * 2 /* + bonuses*/;
	}//end getStaffEvade

	public int getStaffHitRate(Character target){
		return 30 + str * 5 + skl /* + bonuses*/ - target.getStaffEvade(target.position);
	}//end getStaffHitRate

	public String attack(Character target){
		int hitRate, randHit, randCrit, critHitRate, critBonus = 1, damage;
		String actions = "";

		if(target.status != StatType.DEAD && status != StatType.DEAD && isInRange(target) && activeWeapon.type != WeaponType.STAFF){
			hitRate = getHitRate(target);
			randHit = (int) (Math.random()*101);
			//System.out.println("(randHit|hitRate): (" + randHit + "|" + hitRate + ")");
			if(randHit <= hitRate){
				actions += name + " attacks " + target.name + " and hits.\n";
				critHitRate = getCriticalRate(target);
				randCrit = (int) (Math.random()*101);
				//System.out.println("(randCrit|critHitRate): (" + randCrit + "|" + critHitRate + ")");
				if(randCrit<= critHitRate){
					critBonus = 3;
					actions += "Critical hit\n";
				}//end if
				damage = getDamage(target) * critBonus;
				target.hp -= damage;
				actions += name + " dealt " + damage + " damage to " + target.name + ".\n";
				if(target.hp <= 0){
					actions += target.name + " has been killed.\n";
					target.status = StatType.DEAD;
				}//end if
				activeWeapon.durability -= 1;
			}//end if
			else
				actions += name + " attacks " + target.name + " and misses.\n";
		}//end if
		if(activeWeapon.type == WeaponType.STAFF)
			actions += "Staves cannot be used to attack.\n";
		return actions;
	}//end attack

	public int getTriangleHitBoost(Character target){
		if(isNotMagic() && target.isNotMagic()){
			if(activeWeapon.type == WeaponType.SWORD && target.activeWeapon.type == WeaponType.AXE ||
					activeWeapon.type == WeaponType.SWORD && target.activeWeapon.type == WeaponType.LANCE ||
					activeWeapon.type == WeaponType.LANCE && target.activeWeapon.type == WeaponType.SWORD)
				return 10;
			else if(activeWeapon.type == WeaponType.AXE && target.activeWeapon.type == WeaponType.SWORD ||
					activeWeapon.type == WeaponType.LANCE && target.activeWeapon.type == WeaponType.AXE ||
					activeWeapon.type == WeaponType.SWORD && target.activeWeapon.type == WeaponType.LANCE)
				return -10;
		}//end if
		else if(!isNotMagic() && !target.isNotMagic()){
			if(activeWeapon.type == WeaponType.ANIMA && target.activeWeapon.type == WeaponType.LIGHT ||
					activeWeapon.type == WeaponType.LIGHT && target.activeWeapon.type == WeaponType.DARK ||
					activeWeapon.type == WeaponType.DARK && target.activeWeapon.type == WeaponType.ANIMA)
				return 10;
			else if(activeWeapon.type == WeaponType.LIGHT && target.activeWeapon.type == WeaponType.ANIMA ||
					activeWeapon.type == WeaponType.ANIMA && target.activeWeapon.type == WeaponType.DARK ||
					activeWeapon.type == WeaponType.DARK && target.activeWeapon.type == WeaponType.LIGHT)
				return -10;
		}//end else if
		return 0;
	}//end getTriangleHitBoost

	public int getTriangleAttackBoost(Character target){
		if(isNotMagic() && target.isNotMagic()){
			if(activeWeapon.type == WeaponType.SWORD && target.activeWeapon.type == WeaponType.AXE ||
					activeWeapon.type == WeaponType.SWORD && target.activeWeapon.type == WeaponType.LANCE ||
					activeWeapon.type == WeaponType.LANCE && target.activeWeapon.type == WeaponType.SWORD)
				return 1;
			else if(activeWeapon.type == WeaponType.AXE && target.activeWeapon.type == WeaponType.SWORD ||
					activeWeapon.type == WeaponType.LANCE && target.activeWeapon.type == WeaponType.AXE ||
					activeWeapon.type == WeaponType.SWORD && target.activeWeapon.type == WeaponType.LANCE)
				return -1;
		}//end if
		else if(!isNotMagic() && !target.isNotMagic()){
			if(activeWeapon.type == WeaponType.ANIMA && target.activeWeapon.type == WeaponType.LIGHT ||
					activeWeapon.type == WeaponType.LIGHT && target.activeWeapon.type == WeaponType.DARK ||
					activeWeapon.type == WeaponType.DARK && target.activeWeapon.type == WeaponType.ANIMA)
				return 1;
			else if(activeWeapon.type == WeaponType.LIGHT && target.activeWeapon.type == WeaponType.ANIMA ||
					activeWeapon.type == WeaponType.ANIMA && target.activeWeapon.type == WeaponType.DARK ||
					activeWeapon.type == WeaponType.DARK && target.activeWeapon.type == WeaponType.LIGHT)
				return -1;
		}//end else if
		return 0;
	}//end getTriangleAttackBoost

	private boolean isInRange(Character target){
		return activeWeapon.range >= (int) position.distance(target.position) ;
	}//end isInRange

}//end class Character
