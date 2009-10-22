package com.googlecode.firemblem.game;

//Brian Clanton
//10/21/09

public class Terrain{
	//evade boost is a percentage, so in main program you will have to do evadeboost/100
	private int evadeBoost;
	private int defenseBoost;
	private int moveEffect;
	private String name;
	private boolean occupied;

	public Terrain(){
		evadeBoost=0;
		defenseBoost=0;
		moveEffect=0;
		name="?";
		occupied=false;
	}//end default constructor

	public Terrain(TerrainType t){
		switch(t){
			case WOODS:
				evadeBoost = 20;
				defenseBoost = 1;
				//moveEffect = 
				name = "Forest";
			break;

			case SAND:
				evadeBoost = 5;
				defenseBoost = 0;
				name = "Sand";
			break;

			case HIGHMTNS:
				evadeBoost = 40;
				defenseBoost = 0;
				name = "High Mountains";
			break;

			case MTNS:
				evadeBoost = 30;
				defenseBoost = 1;
				name = "Mountains";
			break;

			case PILLAR:
				evadeBoost = 20;
				defenseBoost = 1;
				name = "Pillar";
			break;
			
			case THRONE:
				evadeBoost = 30;
				defenseBoost = 3;
				name = "Throne";
			break;

			case GATE:
				evadeBoost = 30;
				defenseBoost = 3;
				name = "Castle Gate";
			break;
			
			case SEA:
				evadeBoost = 10;
				defenseBoost = 0;
				name = "Sea";
			break;

			case VILLAGE:
				evadeBoost = 10;
				defenseBoost = 0;
				name = "Village";
			break;

			case TILE:
				evadeBoost = 0;
				defenseBoost = 0;
				name = "Tile";
			break;

			case GRASS:
				evadeBoost = 0;
				defenseBoost = 0;
				name = "Grass";
			break;
		}//end switch
		occupied=false;
	}//end constructor

	public int getEvadeBoost() {
		return evadeBoost;
	}

	public void setEvadeBoost(int evadeBoost) {
		this.evadeBoost = evadeBoost;
	}

	public int getDefenseBoost() {
		return defenseBoost;
	}

	public void setDefenseBoost(int defenseBoost) {
		this.defenseBoost = defenseBoost;
	}

	public int getMoveEffect() {
		return moveEffect;
	}

	public void setMoveEffect(int moveEffect) {
		this.moveEffect = moveEffect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	@Override
	public String toString() {
		return "" + name + "\ndefenseBoost=" + defenseBoost + "\nevadeBoost="
				+ evadeBoost + "\nmoveEffect=" + moveEffect + "\noccupied=" + occupied;
	}
	

	

}//end class Terrain

