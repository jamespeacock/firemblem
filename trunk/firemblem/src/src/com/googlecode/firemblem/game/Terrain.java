//Brian Clanton

public class Terrain{
	//evade boost is a percentage, so in main program you will have to do evadeboost/100
	private int evadeBoost;
	private int defenseBoost;
	private int moveEffect;
	private String name;

	public Terrain(){
		evadeBoost=0;
		defenseBoost=0;
		moveEffect=0;
		name="?";
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

			case GRASS
				evadeBoost = 0;
				defenseBoost = 0;
				name = "Grass";
			break;
		}//end switch
	}//end constructor

	//i'll allow eclipse to generate getters and setters

}//end class Terrain

