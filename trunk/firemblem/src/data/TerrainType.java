package data;

// 

/**
 * Provides the numerical effects of terrains on gameplay.
 * 
 * <pre>
 * TODO: Add effects on movement somehow. You should probably discuss your strategy via email first.
 * </pre>
 * 
 * @author Brian Clanton
 * @author Xiagu
 */
public enum TerrainType {
    WOODS(20, 1, "Forest"),
    SAND(5, 0, "Sand"),
    HIGHMTNS(40, 0, "High Mountains"),
    MTNS(30, 1, "Mountains"),
    PILLAR(20, 1, "Pillar"),
    THRONE(30, 3, "Throne"),
    GATE(30, 3, "Castle Gate"),
    SEA(10, 0, "Sea"),
    VILLAGE(10, 0, "Village"),
    TILE(0, 0, "Tile"),
    GRASS(0, 0, "Grass");

    public int evadeBoost, defenseBoost;
    public String name;

    private TerrainType(int evadeBoost, int defenseBoost, String name) {
	this.evadeBoost = evadeBoost;
	this.defenseBoost = defenseBoost;
	this.name = name;
    }
    
    public String toString(){
    	return name;
    }
}
