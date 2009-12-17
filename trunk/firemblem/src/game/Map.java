package game;

import java.util.Arrays;
import java.util.HashSet;

import data.TerrainType;

public class Map {
    public Terrain[][] grid;
    public HashSet<Character> units;

    public Map() {
	grid = null;
	units = new HashSet<Character>();
    }// end default constructor

    public Map(int width, int height) {
	grid = new Terrain[height][width];
	units = new HashSet<Character>();
    }// end constructor

    public void changeSpace(int row, int col, TerrainType t) {
	grid[row][col] = new Terrain(t);
    }// end changeSpace

    public void changeRow(int row, TerrainType t) {
	for (int i = 0; i < grid.length; i++)
	    grid[row][i] = new Terrain(t);
    }// end changeRow

    public void changeColumn(int column, TerrainType t) {
	for (int i = 0; i < grid[column].length; i++)
	    grid[i][column] = new Terrain(t);
    }// end changeColumn

    public void changeAll(TerrainType t) {
	for (int i = 0; i < grid.length; i++)
	    for (int j = 0; j < grid[i].length; j++)
		grid[i][j] = new Terrain(t);
    }// end changeAll

    /**
     * Resizes the Map to newWidth by newHeight
     * 
     * @param newWidth
     * @param newHeight
     */
    public void resize(int newWidth, int newHeight) {
	Arrays.copyOf(grid, newHeight);
	for (int i = 0; i < grid.length; i++) {
	    grid[i] = Arrays.copyOf(grid[i], newWidth);
	}
    }

    public void print() {
	for (int i = 0; i < grid.length; i++) {
	    for (int j = 0; j < grid[i].length; j++)
		System.out.print(grid[i][j].type.name + " ");
	    System.out.println();
	}// end for
    }// end print

}// end class Map
