package data;

import game.Character;
import game.Map;
import game.Terrain;

import java.awt.Graphics;

import javax.swing.JPanel;

// TODO: Write Grid

/**
 * Provides a swing panel to add to the GUI display. This panel will draw the
 * tiles and characters as appropriate.
 * 
 * @author Xiagu
 */
public class MapPanel extends JPanel {

    public Map map;
    
    public Terrain[][] terrainGrid;
    
    /**
     * 
     * @param m
     */
    public MapPanel(Map m){
	map = m;
	terrainGrid = m.grid;
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g);

	for (int i = 0; i < terrainGrid.length; i++) {
	    for (int j = 0; j < terrainGrid[0].length; j++) {
		if (terrainGrid[i][j].type.img.isAnimated())
		    g.drawImage(terrainGrid[i][j].type.img.getAnim().next(), 16 * i,
			    16 * j, null);
		else
		    g.drawImage(terrainGrid[i][j].type.img.getImg(), 16 * i, 16 * j,
			    null);
	    }
	}
	
	for(Character ch : map.units) {
	    g.drawImage(ch.map.getAnim().next(), 16*ch.position.x, 16*ch.position.y, null);
	}
	
	// TODO: add the cursor-thingy. 
    }
}
