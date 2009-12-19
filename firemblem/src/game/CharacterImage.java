package game;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.InputMismatchException;

import data.ImageCollectionEnum;
import data.RegularTileSheet;

public enum CharacterImage implements ImageCollectionEnum {
    ;

    /**
     * The character animation associated with this character.
     */
    private Animation anim;

    /**
     * The character image associated with this character.
     */
    private BufferedImage img;

    /**
     * Whether or not this character image is animated.
     */
    private boolean isAnimated;
    
    public Animation getAnim() {
        return anim;
    }

    public BufferedImage getImg() {
        return img;
    }

    public boolean isAnimated() {
        return isAnimated;
    }
    
    /**
     * The sheet containing images for all of the tiles.
     * 
     * TODO: Find/merge together spritesheets for characters.
     */
    private RegularTileSheet tileSheet; /* = new RegularTileSheet(16, 16, Toolkit
	    .getDefaultToolkit()
	    .createImage("images/characterSprites.bmp"), 1, true);*/

    /**
     * Creates a CharacterImage using the tile at the specified location in
     * <code>tileSheet</code>.
     * 
     * @param tileX
     *            the x index of the tile
     * @param tileY
     *            the y index of the tile
     */
    private CharacterImage(int tileX, int tileY) {
	Raster r = tileSheet.getTile(tileX, tileY);
	img = new BufferedImage(r.getWidth(), r.getHeight(),
		BufferedImage.TYPE_INT_RGB);
	img.setData(r);
    }

    /**
     * Creates an animated CharacterImage using the tiles in <code>tileSheet</code>
     * at the locations given.
     * 
     * @param tileXs
     *            the x locations of the tiles, in frame order.
     * @param tileYs
     *            the y locations of the tiles, in frame order.
     */
    private CharacterImage(int[] tileXs, int[] tileYs) {
	if (tileXs.length != tileYs.length)
	    throw new InputMismatchException(
		    "tileXs and tileYs must have the same number of elements");
	BufferedImage temp;
	anim = new Animation(true);
	for (int i = 0; i < tileXs.length; i++) {
	    Raster r = tileSheet.getTile(tileXs[i], tileYs[i]);
	    temp = new BufferedImage(r.getWidth(), r.getHeight(),
		    BufferedImage.TYPE_INT_RGB);
	    temp.setData(r);
	    anim.add(temp);
	}
    }

}
