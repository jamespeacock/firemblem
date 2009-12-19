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
    /**
     * <pre>
     * TODO: Implement Gibi's solution to TileImage association problems.
     * </pre>
     * 
     * Note: Andrew is Gibi, "me" is RS.
     * 
     * <pre>
     * Andrew: Dammit
     * Wave doesn't love me
     * Anyway
     * What I want to say
     * Is that have the stat boosts
     * 5:58 PM Be held in the terraintype enum
     * So then you can do stuff like
     * enum TerrainType {
     * me: they are in there
     * didn't I say that?
     * Andrew: Did you? I'm not sure
     * So then just have a bunch
     * Like
     * SNOW_PLAIN(snow_plane_img, blah blah stats)
     * FIRE_PLAIN (fire_plain_img, same stats as snow plain)
     * etc
     * 5:59 PM me: oh ew.
     * Andrew: and a constructor in the TerrainType
     * Which takes the img and the stats
     * Then later you can pass stuff in by saying
     * TerrainType.SNOW_PLAIN
     * and that would automatically set the tile correctly
     * and the stats
     * What don't you like about this?
     * 6:00 PM me: the number of different TerrainTypes we'd have to make
     * Andrew: But it would all be in the TerrainType class
     * 6:01 PM So you'd have like... 30 member of it... so?
     * If you don't like retyping the stats
     * Then you can make a code
     * 1 == plain
     * 2 == ocean
     * w/e
     * and have the constructor set the stats
     * 6:02 PM And you'd be passing in TerrainType.SNOW_PLAINS
     * Instead of TerrainType.PLAINS.img[1]\
     * me: question: how does java handle having enums reference other enums in their constructors?
     * like
     * will it figure out the right order or just explode
     * Andrew: ?
     * What do you mean, reference other enums?
     * 6:03 PM me: if, instead of using numbers in your previous example, you used enums
     * Andrew: Hmm... OOOH yeah you're right
     * Here's what I think is best:
     * enum TerrainType {
     * PLAINS,
     * SNOW_PLAINS(special_image_for_snow)
     * FIRE_PLAINS(special_image_for_fire);
     * 6:04 PM public TerrainType(){
     * // empty constructor
     * }
     * public TerrainType(special image, TerrainType parent){
     * this()
     * // do stuff specialwise
     * }
     * Oh also it should be SNOW_PLAINS(special_image, PLAINS)
     * Like that
     * me: right, I figured that out
     * Andrew: That gives you prettyness
     * me: could you then make PLAINS private or is that not allowed?
     * 6:05 PM Andrew: I don't think you can have a private enum variable
     * But you could always just say
     * private final static int PLAINS = 0;
     * me: true
     * Andrew: private final static int OCEAN = 0;
     * and do the same thing
     * Anyway
     * I believe this problem is solved?
     * 6:06 PM me: it was always solved, we were optimizing for prettyness/low amounts of work
     * Andrew: Well yes
     * But I believe we have found the optimal solution
     * me: like I could have TerrainTypes have arrays of possible images, but that makes no sense when you use it
     * 6:07 PM without more static ints
     * Andrew: Yeah
     * 6:11 PM me: thanks
     * Andrew: high five
     * me: &circ;5
     * -----------------
     * you will need to have the parent types be enums
     * because enum declarations are whiny bitches and need to be first in the file
     * otherwise they get...
     * ...angry
     * </pre>
     */
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
    public TerrainImage img;

    private TerrainType(int evadeBoost, int defenseBoost, String name) {
	this.evadeBoost = evadeBoost;
	this.defenseBoost = defenseBoost;
	this.name = name;
    }

    public String toString() {
	return name;
    }
}
