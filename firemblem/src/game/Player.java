package game;
/*
 * Brian Clanton
 * 10/30/09
 */
import java.util.ArrayList;

public class Player {
	private ArrayList<Character> units;
	private String name;
	private int unitNum;
	
	public Player(){
		units = new ArrayList<Character> ();
		name = "???";
		unitNum = 0;
	}//end default constructor
	
	public Player(String n){
		units = new ArrayList<Character> ();
		name = n;
		unitNum = 0;
	}//end constructor

	public ArrayList<Character> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Character> units) {
		this.units = units;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnitNum() {
		return unitNum;
	}

	public void setUnitNum(int unitNum) {
		this.unitNum = unitNum;
	}
	
}
