package game;

import java.io.Serializable;
import data.ItemStatEffect;
import data.ItemType;

public class Item implements Serializable, ItemInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7887207427345117139L;
	
	private String name;
	private int durability;
	private ItemType type;
	private ItemStatEffect ise;
	private int isenum;

	private Item(String name, int durability, ItemType type) {
		super();
		this.name = name;
		this.durability = durability;
		this.type = type;
	}

	private Item(String name, int durability, ItemType type, int isenum){
		super();
		this.name = name;
		this.durability = durability;
		this.type = type;
		this.isenum = isenum;
	}

	private Item(String name, int durability, ItemType type, ItemStatEffect ise, int isenum){
		super();
		this.name = name;
		this.durability = durability;
		this.type = type;
		this.ise = ise;
		this.isenum = isenum;
	}

	public static Item createItem(String name, int durability, ItemType type) {
		return new Item(name, durability, type);
	}

	public static Item createItem(String name, int durability, ItemType type, int isenum) {
		return new Item(name, durability, type, isenum);
	}
	public static Item createItem(String name, int durability, ItemType type, ItemStatEffect ise, int isenum) {
		return new Item(name, durability, type, ise, isenum);
	}

	public int use()
	{
		durability--;
		if(durability > 0)
			return isenum;
		else
			return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public String toString() {
		String s = "";
		s += "Name: " + name + "\n";
		s += "Durability: " + durability + "\n";
		s += "Type: " + type.toString() + "\n";
		return s;
	}

}