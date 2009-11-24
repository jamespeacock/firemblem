package data;

import game.Animation;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.util.InputMismatchException;

// TODO: Find and name more of the tiles.

/**
 * Provides all of the different tiles and their associated image or animation.
 * 
 * @author Xiagu
 */
public enum TileImage {
	FLAT_PLAIN(1, 0),
	HUT(5, 3),
	ARMORY(14, 6),
	BRIDGE(10, 1),
	MOUNTAINS(1, 5),
	FOREST(15, 0),
	WOODS(33, 21),
	CARPET(1, 31),
	OUTPOST(6, 3),
	TREASURE_CHEST(4, 14),
	SHALLOW_SAND(11, 21),
	SEA(new int[] { 12, 13 }, new int[] { 6, 6 });

	/**
	 * The looping tile animation associated with this type of tile.
	 */
	public Animation anim;

	/**
	 * The tile image associated with this type of tile.
	 */
	public BufferedImage img;

	/**
	 * Whether or not this type of tile is animated.
	 */
	public boolean isAnimated;

	/**
	 * The sheet containing images for all of the tiles.
	 */
	private RegularTileSheet tileSheet = new RegularTileSheet(16, 16, Toolkit
			.getDefaultToolkit()
			.createImage("images/fire-emblem-map-tiles.bmp"), 1, true);

	/**
	 * Creates a TileImage using the tile at the specified location in
	 * <code>tileSheet</code>.
	 * 
	 * @param tileX
	 *            the x index of the tile
	 * @param tileY
	 *            the y index of the tile
	 */
	private TileImage(int tileX, int tileY) {
		Raster r = tileSheet.getTile(tileX, tileY);
		img = new BufferedImage(r.getWidth(), r.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		img.setData(r);
	}

	/**
	 * Creates an animated TileImage using the tiles in <code>tileSheet</code>
	 * at the locations given.
	 * 
	 * @param tileXs
	 *            the x locations of the tiles, in frame order.
	 * @param tileYs
	 *            the y locations of the tiles, in frame order.
	 */
	private TileImage(int[] tileXs, int[] tileYs) {
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
