package game;
/*
 * Brian Clanton
 * 10/24/09
 * 
 * Formula Source: http://www.gamefaqs.com/portable/gbadvance/file/563015/36403
 */
import data.CharacterType;

public class Character {
	private String name;
	private Weapon [] inventory = new  Weapon [5];
	private int hp, str, def, luc, lvl, exp, mov, skl, spd, res, con;
	private int position = 0, mapX, mapY;
	
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
		mapX = 0;
		mapY = 0;
	}//end default constructor
	
	public Character(CharacterType e) {
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
	}//end constructor
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Weapon[] getInventory() {
		return inventory;
	}
	public void setInventory(Weapon[] inventory) {
		this.inventory = inventory;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getLuc() {
		return luc;
	}
	public void setLuc(int luc) {
		this.luc = luc;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getMov() {
		return mov;
	}
	public void setMov(int mov) {
		this.mov = mov;
	}
	public int getSkl() {
		return skl;
	}
	public void setSkl(int skl) {
		this.skl = skl;
	}
	public int getSpd() {
		return spd;
	}
	public void setSpd(int spd) {
		this.spd = spd;
	}
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	public int getCon() {
		return con;
	}
	public void setCon(int con) {
		this.con = con;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getMapX() {
		return mapX;
	}

	public void setMapX(int mapX) {
		this.mapX = mapX;
	}

	public int getMapY() {
		return mapY;
	}

	public void setMapY(int mapY) {
		this.mapY = mapY;
	}

	public void addWeapon(Weapon a){
		inventory[position] = a;
		position++;
	}//end addWeapon
	
	public int getAttackSpeed(Weapon a){
		return spd + con - a.getWeight();
	}//end getAttackSpeed
	
	/*
	 * These are the methods to be added:
	 * updateTerrainBonuses
	 * getHitRate
	 * getEvade
	 * getDamage
	 * getCriticalEvade
	 * getCriticalRate
	 * getBattleDefense
	 * getDefenseBonus
	 * getEvadeBonus
	 * getDamageBonus
	 * getHitRateBonus
	 * getStaffHitRate
	 * getStaffEvade
	 */
	
}
