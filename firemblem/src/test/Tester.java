package test;

import game.Terrain;
import data.TerrainType;

public class Tester {

    public static void main(String[] args) {
	Terrain t = new Terrain(TerrainType.WOODS);
	System.out.println(t);

    }

}
