package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Xiagu
 *
 */
public class Cell
{
	public static final int TER_EMPTY = 0,
							TER_PATH = 1,						
							TER_TOWER = 2,
							TER_CASTLE = 3,
							TER_ENTRANCE = 4,
							NUMSPEC = 5;
	public int xLoc, zLoc, terrain, name;
	boolean noNZ, noNX, noPZ, noPX;
	public float[] ambient;
	
	public Cell(int ter, int x, int z)
	{
		terrain = ter;
		xLoc = x;
		zLoc = z;
		name = 0;
	}
	
	public void draw(Graphics g, int x, int y)
	{
		switch(terrain)
		{
		case Cell.TER_CASTLE:
			g.setColor(Color.red);
			break;
		case Cell.TER_TOWER:
			g.setColor(Color.blue);
			break;
		case Cell.TER_EMPTY:
			g.setColor(Color.green.darker());
			break;
		case Cell.TER_PATH:
			g.setColor(Color.yellow);
			break;
		case Cell.TER_ENTRANCE:
			g.setColor(Color.magenta.darker().darker().darker());
		}
		
		g.fillRect(x, y, 40, 40);
	}
}
