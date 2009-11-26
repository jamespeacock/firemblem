package game;

import data.TerrainType;

// 10/21/09

/**
 * @author Brian Clanton
 * @author Xiagu
 */
public class Terrain {

    public int moveEffect;
    public boolean occupied;
    public TerrainType type;

    public Terrain() {
	moveEffect = 0;
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
	return "" + type.name + "\ndefenseBoost=" + type.defenseBoost
		+ "\nevadeBoost=" + type.evadeBoost + "\nmoveEffect="
		+ moveEffect + "\noccupied=" + occupied;
    }// end toString

    /**
     * This method gets the evade bonus given a characters base stat.
     * 
     * @param baseStat
     *            the original base stat
     * @return the modified base stat
     */
    public int getEvadeBonus(int baseStat) {
	return (int) Math.floor(baseStat + type.evadeBoost / 100 * baseStat);
    }// end getEvadeBonus

    /**
     * This method gets the defense bonus given a characters base stat.
     * 
     * @param baseStat
     *            the original base stat
     * @return the modified base stat
     */
    public int getDefenseBonus(int baseStat) {
	return baseStat + type.defenseBoost;
    }// end getDefenseBonus

    // TODO: Write getMoveDecrease
    // TODO: Write getHpIncrease

}// end class Terrain

