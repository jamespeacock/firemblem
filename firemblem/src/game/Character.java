package game;
/*
 * Brian Clanton
 * 10/24/09
 * 
 * Formula Source: http://www.gamefaqs.com/portable/gbadvance/file/563015/36403
 */
import java.awt.Point;
import javax.swing.ImageIcon;

//this import will change
import test.AttackPhaseTester;

import data.CharacterType;
import data.StatType;
import data.WeaponType;

public class Character {
	public String name;
	public Weapon [] inventory = new  Weapon [5];
	public int hp, str, def, luc, lvl, exp, mov, skl, spd, res, con;
	public int index = 0;
	public StatType status;
	public Point position;
	public Weapon activeWeapon = null;
	public ImageIcon classImage;
	
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
	}//end default constructor
	
	public Character(CharacterType e, int x, int y) {
		switch(e){
		case MYR:
			name = "Myrmidon";
			lvl = 1;
			hp = 19;
			str = 6;
			skl = 9;
			spd = 10;
			luc = 3;
			def = 3;
			res = 1;
			con = 5;
			mov = 5;
			exp = 0;
		break;

		case LORD:
			name = "Lord";
			lvl = 1;
			hp = 18;
			str = 5;
			skl = 5;
			spd = 7;
			luc = 7;
			def = 5;
			res = 0;
			con = 6;
			mov = 5;
			exp = 0;
		break;

		case KNG:
			name = "Knight";
			lvl = 1;
			hp = 20;
			str = 7;
			skl = 4;
			spd = 3;
			luc = 4;
			def = 11;
			res = 0;
			con = 14;
			mov = 4;
			exp = 0;
		break;

		case FGT:
			name = "Fighter";
			lvl = 1;
			hp = 28;
			str = 8;
			skl = 3;
			spd = 5;
			luc = 4;
			def = 3;
			res = 0;
			con = 13;
			mov = 5;
			exp = 0;
		break;

		case ARCH:
			name = "Archer";
			lvl = 1;
			hp = 18;
			str = 4;
			skl = 4;
			spd = 5;
			luc = 2;
			def = 4;
			res = 0;
			con = 7;
			mov = 5;
			exp = 0;
		break;

		case MAGE:
			name = "Mage";
			lvl = 1;
			hp = 16;
			str = 5;
			skl = 5;
			spd = 4;
			luc = 4;
			def = 2;
			res = 7;
			con = 4;
			mov = 5;
			exp = 0;
		break;

		case CLR:
			name = "Cleric";
			lvl = 1;
			hp = 16;
			str = 1;
			skl = 6;
			spd = 8;
			luc = 8;
			def = 0;
			res = 6;
			con = 4;
			mov = 5;
			exp = 0;
		break;

		}//end switch
		position = new Point(x,y);
		status = StatType.NONE;
		classImage = new ImageIcon("images/" + name.toLowerCase() + ".gif");
	}//end constructor

	public Terrain getTerrain(Map m){
		return m.grid[(int) position.getX()][(int) position.getY()];
	}//end getTerrain
	
	public void addWeapon(Weapon a){
		inventory[index] = a;
		index++;
	}//end addWeapon
	
	public int getAttackSpeed(){
		return spd + con - activeWeapon.weight;
	}//end getAttackSpeed
	
	public void setActiveWeapon(int a){
		activeWeapon = inventory[a];
	}//end setActiveWeapon
	
	public int getHitRate(Character target){
		return (int) ((int) 2 * skl + .5 * luc + activeWeapon.hit  /* + bonuses*/ - target.getEvade());
	}//end getHitRate
	
	public int getEvade(){
		int e = 2 * getAttackSpeed() + luc;
		return getTerrain(AttackPhaseTester.g).getEvadeBonus(e);
	}//end getEvade
	
	private boolean isNotMagic(){
		boolean check;
		check = activeWeapon.type != WeaponType.ANIMA && activeWeapon.type != WeaponType.LIGHT 
			&& activeWeapon.type != WeaponType.DARK;
		return check;
	}//end isNotMagic
	
	public int getDamage(Character target){
		if(isNotMagic())
			return str + activeWeapon.attack /* + bonuses*/ - target.getEffectiveDefense();
		else
			return str + activeWeapon.attack /* + bonuses*/ - target.getEffectiveResistance();
	}//end getDamage
	
	public int getEffectiveDefense(){
		return getTerrain(AttackPhaseTester.g).getDefenseBonus(def);
	}//end getEffectiveDefense
	
	public int getEffectiveResistance(){
		return getTerrain(AttackPhaseTester.g).getDefenseBonus(res);
	}//end getEffectiveResistance
	
	/*
	 * this applies to getCriticalEvade and getCriticalRate
	 * will return number that represents a percentage
	 * ex: return 20 = 20%
	 */
	public int getCriticalEvade(){
		return getTerrain(AttackPhaseTester.g).getEvadeBonus(luc) /* + bonuses*/;
	}//end getCriticalEvade
	
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
		
		if(target.hp > 0 && hp > 0){
			hitRate = getHitRate(target);
			randHit = (int) (Math.random()*100);
			//System.out.println("(randHit|hitRate): (" + randHit + "|" + hitRate + ")");
			if(randHit <= hitRate){
				actions += name + " attacks " + target.name + " and hits.\n";
				critHitRate = getCriticalRate(target);
				randCrit = (int) (Math.random()*100);
				//System.out.println("(randCrit|critHitRate): (" + randCrit + "|" + critHitRate + ")");
				if(randCrit<= critHitRate){
					critBonus = 2;
					actions += "Critical hit\n";
				}//end if
				damage = getDamage(target) * critBonus;
				target.hp -= damage;
				actions += name + " dealt " + damage + " damage to " + target.name + ".\n";
				if(target.hp <= 0)
					actions += target.name + " has been killed.\n";
				activeWeapon.durability -= 1;
			}//end if
			else{
				actions += name + " attacks " + target.name + " and misses.\n";
			}
		}//end if
		return actions;
	}//end attack
	
	
	/*
	 * I'm not sure where the map is going to be located in the main program, this is why this 
	 * class has errors.
	 */
	
}
