package game;

public class Character {
	private String name;
	private Weapon [] inventory = new  Weapon [5];
	private int hp, pow, def, hit, luc, lvl, exp, mov, skl, spd, res, attribute;
	
	public Character(){
		name = "";
		inventory = null;
		hp = 0;
		pow = 0;
		def = 0;
		hit = 0;
		luc = 0;
		lvl = 0;
		exp = 0;
		mov = 0;
		skl = 0;
		spd = 0;
		res = 0;
		attribute = -1;
	}//end default constructor
	
	public Character(int e) {
		
	}
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
	public int getPow() {
		return pow;
	}
	public void setPow(int pow) {
		this.pow = pow;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
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
	public int getAttribute() {
		return attribute;
	}
	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}
	
	
	
	
}
