package game;

import data.TerrainType;
//package com.googlecode.firemblem.game;

//Brian Clanton
//10/21/09

public class Terrain{
	//evade boost is a percentage
	public int evadeBoost;
	public int defenseBoost;
	public int moveEffect;
	public String name;
	public boolean occupied;
	public TerrainType type;

	public Terrain(){
		evadeBoost=0;
		defenseBoost=0;
		moveEffect=0;
		name="?";
		occupied=false;
		type = TerrainType.GRASS;
	}//end default constructor

	public Terrain(TerrainType t){
		type = t;
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
		moveEffect=0;
	}//end constructor

	@Override
	public String toString() {
		return "" + name + "\ndefenseBoost=" + defenseBoost + "\nevadeBoost="
				+ evadeBoost + "\nmoveEffect=" + moveEffect + "\noccupied=" + occupied;
	}
	
	public int getEvadeBonus(int baseStat){
		return (int) Math.floor(baseStat + evadeBoost/100 * baseStat);
	}//end getEvadeBonus

	

}//end class Terrain

