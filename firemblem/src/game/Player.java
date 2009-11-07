package game;
/*
 * Brian Clanton
 * 10/30/09
 */
import java.util.ArrayList;

public class Player {
	public ArrayList<Character> units;
	public String name;
	public int unitNum;
	
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
	
}
