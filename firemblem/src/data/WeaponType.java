package data;

/**
 * 
 * @author Brian Clanton
 * 
 */
public enum WeaponType {

    SWORD("Sword"),
    LANCE("Lance"),
    AXE("Axe"),
    BOW("Bow"),
    ANIMA("Anima"),
    LIGHT("Light"),
    DARK("Dark"),
    MONSTER("Monster"),
    STAFF("Staff"),
    DUMMY("Dummy");

    public String name;

    private WeaponType(String a) {
	name = a;
    }

    public String toString() {
	return name;
    }
}
