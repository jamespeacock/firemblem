package data;

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

    // Grid

    Terrain[][] terrainMap;

    public MapPanel(/* Grid theGrid */) {
	// retrieve Terrain[][] array from Grid instance using appropriate
	// method
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g);

	for (int i = 0; i < terrainMap.length; i++) {
	    for (int j = 0; j < terrainMap[0].length; j++) {
		if (terrainMap[i][j].type.img.isAnimated)
		    g.drawImage(terrainMap[i][j].type.img.anim.next(), 16 * i,
			    16 * j, null);
		else
		    g.drawImage(terrainMap[i][j].type.img.img, 16 * i, 16 * j,
			    null);
	    }
	}
	
	// TODO: Write Character drawing code. Assume a HashSet is in Grid.
    }
}
