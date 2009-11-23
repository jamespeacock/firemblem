package game;

import data.TerrainType;

public class Map {
	public Terrain [][] grid;
	
	public Map(){
		grid = null;
	}//end default constructor
	
	public Map(int x, int y){
		grid = new Terrain [x][y];
	}//end constructor

	public void changeSpace(int x, int y, TerrainType t){
		grid[x][y] = new Terrain(t);
	}//end changeSpace
	
	public void changeRow(int row, TerrainType t){
		for(int i = 0; i < grid.length; i++)
			grid[row][i] = new Terrain(t);
	}//end changeRow
	
	public void changeColumn(int column, TerrainType t){
		for(int i = 0; i < grid[column].length; i++)
			grid[i][column] = new Terrain(t);
	}//end changeColumn
	
	public void changeAll(TerrainType t){
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[i].length; j++)
				grid[i][j] = new Terrain(t);
	}//end changeAll
	
	public void print(){
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++)
				System.out.print(grid[i][j].type.name + " ");
			System.out.println();
		}//end for
	}//end print
	
}//end class Map
