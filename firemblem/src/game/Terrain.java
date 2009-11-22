package game;

import data.TerrainType;

//10/21/09

// TODO: Add HP effects

/**
 * @author Brian Clanton
 * @author Xiagu
 */
public class Terrain {

    /**
     * The percent increase in evasion.
     */
    public int evadeBoost;
    public int defenseBoost;
    public int moveEffect;
    public String name;
    public boolean occupied;
    public TerrainType type;

    public Terrain() {
	evadeBoost = 0;
	defenseBoost = 0;
	moveEffect = 0;
	name = "?";
	occupied = false;
	type = TerrainType.TILE;
    }// end default constructor

    /**
     * Constructs a Terrain object with the correct attributes for the given
     * TerrainType.
     * 
     * @param t
     *            the TerrainType of the new Terrain
     */
    public Terrain(TerrainType t) {
	type = t;
	occupied = false;
	moveEffect = 0;
    }// end constructor

    @Override
    public String toString() {
	return "" + name + "\ndefenseBoost=" + defenseBoost + "\nevadeBoost="
		+ evadeBoost + "\nmoveEffect=" + moveEffect + "\noccupied="
		+ occupied;
    }// end toString

    /**
     * This method gets the evade bonus given a characters base stat.
     * 
     * @param baseStat
     *            the original base stat
     * @return the modified base stat
     */
    public int getEvadeBonus(int baseStat) {
	return (int) Math.floor(baseStat + evadeBoost / 100 * baseStat);
    }// end getEvadeBonus

    /**
     * This method gets the defense bonus given a characters base stat.
     * 
     * @param baseStat
     *            the original base stat
     * @return the modified base stat
     */
    public int getDefenseBonus(int baseStat) {
	return baseStat + defenseBoost;
    }// end getDefenseBonus

    // TODO: Write getMoveDecrease
    // TODO: Write getHpIncrease

}// end class Terrain

