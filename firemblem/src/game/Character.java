package game;

/*
 * Brian Clanton 10/24/09
 * 
 * Formula Source: http://www.gamefaqs.com/portable/gbadvance/file/563015/36403
 */
import java.awt.Point;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.ImageIcon;

// this import will change
import test.AttackPhaseTester;

import data.CharacterType;
import data.ParseMethods;
import data.StatType;
import data.WeaponType;

/**
 * 
 * @author Brian Clanton
 * 
 */

public class Character {
    public String name;
    public ItemInterface[] inventory = new ItemInterface[5];
    public int hp, str, def, luc, lvl, exp, mov, skl, spd, res, con;
    public int currHp;
    public int index = 0;
    public StatType status;
    public Point position;
    public Weapon activeWeapon = null;
    public ImageIcon classImage;
    public CharacterType type;
    public int[] growthRates;
    public int classPower = 1, classBonusA, classBonusB;
    /**
     * The animation for an unselected character standing on the battlefield.
     */
    public CharacterImage map;
    
    /**
     * The animation for a selected character standing on the battlefield.
     */
    public CharacterImage selected;

    public Character() {
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
	position = new Point(0, 0);
	status = StatType.NONE;
	type = null;
    }// end default constructor

    public Character(CharacterType e, Weapon w, int x, int y) {
	try {
	    Scanner console = new Scanner(new FileReader(
		    "src/data/basestats.txt"));
	    String s = "";
	    while (!console.nextLine().contains("start"))
		s = console.nextLine();
	    while (!s.contains(e.name()) && console.hasNext()) {
		s = console.nextLine();
	    }
	    s = s.substring(s.indexOf("\t")).trim();
	    name = s.substring(0, s.indexOf("\t"));
	    s = s.substring(s.indexOf("\t")).trim();
	    lvl = Integer.parseInt(s.substring(0, s.indexOf("\t")));
	    s = s.substring(s.indexOf("\t")).trim();
	    hp = Integer.parseInt(s.substring(0, s.indexOf("\t")));
	    currHp = hp;
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
	    activeWeapon = w;
	} catch (FileNotFoundException q) {
	}
	position = new Point(x, y);
	status = StatType.NONE;
	classImage = new ImageIcon("images/" + name.toLowerCase() + ".gif");
	type = e;
    }// end constructor

    /**
     * This method returns the terrain of the space that the unit is occupying.
     * 
     * @param m
     * @return terrain
     */
    public Terrain getTerrain(Map m) {
	return m.grid[position.x][position.y];
    }// end getTerrain

    /**
     * 
     * @param a
     */
    public void add(ItemInterface a) {
	inventory[index] = a;
	index++;
    }// end add

    /**
     * Gets attack speed.
     * 
     * @return attack speed
     */
    public int getAttackSpeed() {
	if (spd + con - activeWeapon.weight > 0)
	    return spd + con - activeWeapon.weight;
	else
	    return 1;
    }// end getAttackSpeed

    /**
     * Returns true if the Character can perform a double attack. In order for
     * this to happen, the Character's attack speed must be a least 4 points
     * higher than the opponent's attack speed.
     * 
     * @param target
     * @return true if double attack, false if not
     */
    public boolean doubleAttack(Character target) {
	return (getAttackSpeed() - target.getAttackSpeed()) >= 4;
    }// end doubleAttack

    /**
     * Sets a Character's equipped weapon.
     * 
     * @param inventorySlot
     * @return if equipped weapon was changed
     */
    public boolean setActiveWeapon(int inventorySlot) {
	if (inventory[inventorySlot] instanceof Weapon) {
	    activeWeapon = (Weapon) inventory[inventorySlot];
	    return true;
	}
	return false;
    }// end setActiveWeapon

    /**
     * Gets hit rate. will return number that represents a percentage ex: return
     * 20 = 20%
     * 
     * @param target
     * @return hit rate
     */
    public int getHitRate(Character target) {
	if ((int) ((int) 2 * skl + .5 * luc + activeWeapon.hit
		+ getTriangleHitBoost(target)
	/* + bonuses */- target.getEvade()) > 0)
	    return (int) ((int) 2 * skl + .5 * luc + activeWeapon.hit
		    + getTriangleHitBoost(target)
	    /* + bonuses */- target.getEvade());
	else
	    return 1;
    }// end getHitRate

    /**
     * Gets evade rate. will return number that represents a percentage ex:
     * return 20 = 20%
     * 
     * @return
     */
    public int getEvade() {
	int e = 2 * getAttackSpeed() + luc;
	return getTerrain(AttackPhaseTester.g).getEvadeBonus(e);
    }// end getEvade

    /**
     * Checks to see if the equipped weapon is magic.
     * 
     * @return true if weapon is not magic, false if weapon is magic
     */
    private boolean isNotMagic() {
	boolean check;
	check = activeWeapon.type != WeaponType.ANIMA
		&& activeWeapon.type != WeaponType.LIGHT
		&& activeWeapon.type != WeaponType.DARK;
	return check;
    }// end isNotMagic

    /**
     * Gets damage.
     * 
     * @param target
     * @return damage
     */
    public int getDamage(Character target) {
	if (isNotMagic())
	    if (str + activeWeapon.attack + getTriangleAttackBoost(target)
	    /* + bonuses */- target.getEffectiveDefense() > 0)
		return str + activeWeapon.attack
			+ getTriangleAttackBoost(target)
			/* + bonuses */- target.getEffectiveDefense();
	    else
		return 0;
	else if (str + activeWeapon.attack /* + bonuses */
		- target.getEffectiveResistance() > 0)
	    return str + activeWeapon.attack /* + bonuses */
		    - target.getEffectiveResistance();
	else
	    return 0;
    }// end getDamage

    /**
     * Gets defense in battle.
     * 
     * @return battle defense
     */
    public int getEffectiveDefense() {
	return getTerrain(AttackPhaseTester.g).getDefenseBonus(def);
    }// end getEffectiveDefense

    /**
     * Gets resistance in battle.
     * 
     * @return battle resistance
     */
    public int getEffectiveResistance() {
	return getTerrain(AttackPhaseTester.g).getDefenseBonus(res);
    }// end getEffectiveResistance

    /**
     * Gets critical evade rate. will return number that represents a percentage
     * ex: return 20 = 20%
     * 
     * @return critical evade
     */
    public int getCriticalEvade() {
	return getTerrain(AttackPhaseTester.g).getEvadeBonus(luc) /* + bonuses */;
    }// end getCriticalEvade

    /**
     * Gets critical hit rate. will return number that represents a percentage
     * ex: return 20 = 20%
     * 
     * @param target
     * @return critical rate
     */
    public int getCriticalRate(Character target) {
	if ((int) .5 * skl + activeWeapon.crit /* + bonuses */
		- target.getCriticalEvade() > 0)
	    return (int) .5 * skl + activeWeapon.crit /* + bonuses */
		    - target.getCriticalEvade();
	else
	    return 0;
    }// end getCriticalRate

    // TODO: Add some comments!!!

    public int getStaffEvade(Point t) {
	return res * 5 + (int) (position.distance(t)) * 2 /* + bonuses */;
    }// end getStaffEvade

    public int getStaffHitRate(Character target) {
	if (30 + str * 5 + skl /* + bonuses */
		- target.getStaffEvade(target.position) > 0)
	    return 30 + str * 5 + skl /* + bonuses */
		    - target.getStaffEvade(target.position);
	else
	    return 0;
    }// end getStaffHitRate

    public String attack(Character target) {
	int hitRate, randHit, randCrit, critHitRate, critBonus = 1, damage;
	String actions = "";

	if (target.status != StatType.DEAD && status != StatType.DEAD
		&& isInRange(target) && activeWeapon.type != WeaponType.STAFF) {
	    hitRate = getHitRate(target);
	    randHit = (int) (Math.random() * 101);
	    // System.out.println("(randHit|hitRate): (" + randHit + "|" +
	    // hitRate + ")");
	    if (randHit <= hitRate) {
		actions += name + " attacks " + target.name + " and hits.\n";
		critHitRate = getCriticalRate(target);
		randCrit = (int) (Math.random() * 101);
		// System.out.println("(randCrit|critHitRate): (" + randCrit +
		// "|" + critHitRate + ")");
		if (randCrit <= critHitRate) {
		    critBonus = 3;
		    actions += "Critical hit\n";
		}// end if
		damage = getDamage(target) * critBonus;
		target.currHp -= damage;
		actions += name + " dealt " + damage + " damage to "
			+ target.name + ".\n";
		if (target.currHp <= 0) {
		    actions += target.name + " has been killed.\n";
		    target.status = StatType.DEAD;

		    exp = exp
			    + (31 + (target.lvl + target.classBonusA) - (lvl + classBonusA))
			    / classPower;

		    if (exp
			    + ((target.lvl * target.classPower) + target.classBonusB)
			    - (((lvl * classPower) + classBonusB)) <= 0)
			exp = exp
				+ ((target.lvl * target.classPower) + target.classBonusB)
				- (((lvl * classPower) + classBonusB) / 2);
		    else
			exp = exp
				+ ((target.lvl * target.classPower) + target.classBonusB)
				- ((lvl * classPower) + classBonusB);
		    if (exp >= 100) {
			exp = exp - 100;
			levelUp();
		    }

		}// end if
		activeWeapon.durability -= 1;
	    }// end if
	    else {
		actions += name + " attacks " + target.name + " and misses.\n";
		exp = exp
			+ (31 + (target.lvl + target.classBonusA) - (lvl + classBonusA))
			/ classPower;
		if (exp >= 100) {
		    exp = exp - 100;
		    levelUp();
		}
	    }
	}// end if
	if (activeWeapon.type == WeaponType.STAFF)
	    actions += "Staves cannot be used to attack.\n";
	return actions;
    }// end attack

    public int getTriangleHitBoost(Character target) {
	if (isNotMagic() && target.isNotMagic()) {
	    if (activeWeapon.type == WeaponType.SWORD
		    && target.activeWeapon.type == WeaponType.AXE
		    || activeWeapon.type == WeaponType.AXE
		    && target.activeWeapon.type == WeaponType.LANCE
		    || activeWeapon.type == WeaponType.LANCE
		    && target.activeWeapon.type == WeaponType.SWORD)
		return 10;
	    else if (activeWeapon.type == WeaponType.AXE
		    && target.activeWeapon.type == WeaponType.SWORD
		    || activeWeapon.type == WeaponType.LANCE
		    && target.activeWeapon.type == WeaponType.AXE
		    || activeWeapon.type == WeaponType.SWORD
		    && target.activeWeapon.type == WeaponType.LANCE)
		return -10;
	}// end if
	else if (!isNotMagic() && !target.isNotMagic()) {
	    if (activeWeapon.type == WeaponType.ANIMA
		    && target.activeWeapon.type == WeaponType.LIGHT
		    || activeWeapon.type == WeaponType.LIGHT
		    && target.activeWeapon.type == WeaponType.DARK
		    || activeWeapon.type == WeaponType.DARK
		    && target.activeWeapon.type == WeaponType.ANIMA)
		return 10;
	    else if (activeWeapon.type == WeaponType.LIGHT
		    && target.activeWeapon.type == WeaponType.ANIMA
		    || activeWeapon.type == WeaponType.ANIMA
		    && target.activeWeapon.type == WeaponType.DARK
		    || activeWeapon.type == WeaponType.DARK
		    && target.activeWeapon.type == WeaponType.LIGHT)
		return -10;
	}// end else if
	return 0;
    }// end getTriangleHitBoost

    public int getTriangleAttackBoost(Character target) {
	if (isNotMagic() && target.isNotMagic()) {
	    if (activeWeapon.type == WeaponType.SWORD
		    && target.activeWeapon.type == WeaponType.AXE
		    || activeWeapon.type == WeaponType.SWORD
		    && target.activeWeapon.type == WeaponType.LANCE
		    || activeWeapon.type == WeaponType.LANCE
		    && target.activeWeapon.type == WeaponType.SWORD)
		return 1;
	    else if (activeWeapon.type == WeaponType.AXE
		    && target.activeWeapon.type == WeaponType.SWORD
		    || activeWeapon.type == WeaponType.LANCE
		    && target.activeWeapon.type == WeaponType.AXE
		    || activeWeapon.type == WeaponType.SWORD
		    && target.activeWeapon.type == WeaponType.LANCE)
		return -1;
	}// end if
	else if (!isNotMagic() && !target.isNotMagic()) {
	    if (activeWeapon.type == WeaponType.ANIMA
		    && target.activeWeapon.type == WeaponType.LIGHT
		    || activeWeapon.type == WeaponType.LIGHT
		    && target.activeWeapon.type == WeaponType.DARK
		    || activeWeapon.type == WeaponType.DARK
		    && target.activeWeapon.type == WeaponType.ANIMA)
		return 1;
	    else if (activeWeapon.type == WeaponType.LIGHT
		    && target.activeWeapon.type == WeaponType.ANIMA
		    || activeWeapon.type == WeaponType.ANIMA
		    && target.activeWeapon.type == WeaponType.DARK
		    || activeWeapon.type == WeaponType.DARK
		    && target.activeWeapon.type == WeaponType.LIGHT)
		return -1;
	}// end else if
	return 0;
    }// end getTriangleAttackBoost

    public void getGrowthRates() {
	try {
	    Scanner console = new Scanner(new FileReader(
		    "src/data/leveluprates.txt"));
	    String s = console.nextLine();
	    while (!s.contains(type.name()))
		s = console.nextLine();
	    s = console.next();
	    growthRates = new int[7];
	    for (int i = 0; i < growthRates.length; i++)
		growthRates[i] = Integer.parseInt(console.next().trim())
			+ (int) (Math.random() * 10) - 5;
	} catch (FileNotFoundException e) {
	}
    }

    public void levelUp() {
	getGrowthRates();
	lvl++;
	if ((int) (Math.random() * 101) <= growthRates[0])
	    hp++;
	if ((int) (Math.random() * 101) <= growthRates[1])
	    str++;
	if ((int) (Math.random() * 101) <= growthRates[2])
	    skl++;
	if ((int) (Math.random() * 101) <= growthRates[3])
	    spd++;
	if ((int) (Math.random() * 101) <= growthRates[4])
	    luc++;
	if ((int) (Math.random() * 101) <= growthRates[5])
	    def++;
	if ((int) (Math.random() * 101) <= growthRates[6])
	    res++;
    }

    public void setClassInfo() {
	switch (type) {
	case JNYMN:
	case RCRT:
	case PUPIL:
	    classPower = 1;
	    classBonusA = 0;
	    classBonusB = 0;
	    break;

	case CLR:
	case TRBDR:
	case THF:
	    classPower = 1;
	    classBonusA = 0;
	    classBonusB = 0;
	    break;

	case MYR:
	case MCNRY:
	case LORD:
	case PRT:
	case FGT:
	case ARCH:
	case MAGE:
	case PKNG:
	case KNG:
	case CVLR:
	    classPower = 3;
	    classBonusA = 20;
	    classBonusB = 0;
	    break;

	case GNRL:
	case HERO:
	case BRSKR:
	case FKNG:
	case PLDN:
	    classPower = 3;
	    classBonusA = 20;
	    classBonusB = 60;
	    break;
	}
    }

    public static Weapon getDefaultWeapon(CharacterType t) {
	switch (t) {
	case GNRL:
	case RCRT:
	case PKNG:
	case KNG:
	case FKNG:
	case CVLR:
	case PLDN:
	    return new Weapon("Iron Lance");

	case HERO:
	case THF:
	case MYR:
	case MCNRY:
	case LORD:
	    return new Weapon("Iron Sword");

	case PRT:
	case FGT:
	case JNYMN:
	case BRSKR:
	    return new Weapon("Iron Axe");

	case ARCH:
	    return new Weapon("Iron Bow");

	case PUPIL:
	case MAGE:
	    return new Weapon("Fire");

	case TRBDR:
	case CLR:
	    return new Weapon("Heal");
	}
	return new Weapon("Dummy");
    }

    public void setName(String s){
	name = s;
    }
    
    /**
     * 
     * @return a representation of the character's variables
     */
    public String toString() {
	return "{Name:"+name+
		" lvl:"+lvl+
		" hp:"+hp+
		" str:"+str+
		" lvl:"+lvl+
		" skl:"+skl+
		" spd:"+spd+
		" luc:"+luc+
		" def:"+def+
		" res:"+res+
		" con:"+con+
		" mov:"+mov+
		" exp:"+exp+
		" wpn:"+activeWeapon.toString().substring(5, activeWeapon.toString().indexOf("\n"))+
		"}";
    }
    
    private boolean isInRange(Character target) {
	return activeWeapon.range >= (int) position.distance(target.position);
    }// end isInRange

}// end class Character