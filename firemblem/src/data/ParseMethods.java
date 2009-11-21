package data;

import java.util.TreeMap;

public class ParseMethods {
	public static TreeMap<String, CharacterType> characterSearchMap = new TreeMap<String, CharacterType> ();
	public static TreeMap<String, TerrainType> terrainSearchMap = new TreeMap<String, TerrainType> ();
	
	public static void initializeCharacterSearchMap(){
		String [] names = {"Myrmidon", "Lord", "Knight", "Cleric", "Mage", "Archer", "Fighter", 
				"Recruit", "Cavalier", "Paladin", "Berserker", "General", "Mercenary", "Pegasus Knight", 
				"Journeyman"};
		CharacterType[] types = {CharacterType.MYR, CharacterType.LORD, CharacterType.KNG, CharacterType.CLR,
				CharacterType.MAGE, CharacterType.ARCH, CharacterType.FGT, CharacterType.RCRT, CharacterType.CVLR, CharacterType.PLDN, CharacterType.BRSKR, 
				CharacterType.GNRL, CharacterType.MCNRY, CharacterType.PKNG, CharacterType.JNYMN};
		
		for(int i = 0; i < names.length; i++)
			characterSearchMap.put(names[i], types[i]);
	}//end intitializeCharacterSearchMap
	
	public static CharacterType toCharacterType(String a){
		if(characterSearchMap.containsKey(a))
			return characterSearchMap.get(a);
		else
			return null;
	}//end toTerrainType
	
	public static void initializeTerrainSearchMap(){
		String [] names = {"Woods", "Sand", "High Mountains", "Mountains", "Pillar", "Throne", "Gate", "Sea", 
				"Village", "Tile", "Grass"};
		TerrainType [] types = {TerrainType.WOODS, TerrainType.SAND, TerrainType.HIGHMTNS, TerrainType.MTNS, 
				TerrainType.PILLAR, TerrainType.THRONE, TerrainType.GATE, TerrainType.SEA, TerrainType.VILLAGE,
				TerrainType.TILE, TerrainType.GRASS};
		terrainSearchMap = new TreeMap<String, TerrainType>();
		
		for(int i = 0; i < names.length; i++)
			terrainSearchMap.put(names[i], types[i]);
	}//end initializeSearchMap
	
	public static TerrainType toTerrainType(String a){
		if(terrainSearchMap.containsKey(a))
			return terrainSearchMap.get(a);
		else
			return null;
	}//end toTerrainType
}//end class ParseMethods
