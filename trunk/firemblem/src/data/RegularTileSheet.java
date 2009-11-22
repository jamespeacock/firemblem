package data;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.util.Vector;

/**
 * Provides a representation of a regular rectangular tile sheet and methods for
 * accessing the raster data contained in the tiles.
 * 
 * @author Xiagu
 */
public class RegularTileSheet implements RenderedImage {

	private BufferedImage sheet;

	private int tileWidth, tileHeight;

	/**
	 * If the tile border runs around the outside of the sheet or not.
	 */
	private boolean outsideBorder;

	/**
	 * The size of the tile borders in pixels.
	 */
	private int borderSize;

	/**
	 * The x distance from x=0 to where the tile grid begins.
	 */
	private int xGridOffset;

	/**
	 * The y distance from y=0 to where the tile grid begins.
	 */
	private int yGridOffset;

	/**
	 * Constructs a RegularTileSheet with no borders, starting at (0, 0).
	 * 
	 * @param tileWidth
	 *            the width of each tile
	 * @param tileHeight
	 *            the height of each tile
	 * @param img
	 *            the tile sheet image
	 */
	public RegularTileSheet(int tileWidth, int tileHeight, Image img) {
		this(tileWidth, tileHeight, img, 0, false, 0, 0);
	}

	/**
	 * Constructs a RegularTileSheet, starting at (0, 0).
	 * 
	 * @param tileWidth
	 *            the width of each tile
	 * @param tileHeight
	 *            the height of each tile
	 * @param img
	 *            the tile sheet image
	 * @param borderSize
	 *            the size, in pixels, of the border of each tile (the
	 *            horizontal and vertical distance between where one tile stops
	 *            and the next begins)
	 * @param outsideBorder
	 *            whether or not the border goes around the outside of the sheet
	 */
	public RegularTileSheet(int tileWidth, int tileHeight, Image img,
			int borderSize, boolean outsideBorder) {
		this(tileWidth, tileHeight, img, borderSize, outsideBorder, 0, 0);
	}

	/**
	 * Constructs a RegularTileSheet.
	 * 
	 * @param tileWidth
	 *            the width of each tile
	 * @param tileHeight
	 *            the height of each tile
	 * @param img
	 *            the tile sheet image
	 * @param borderSize
	 *            the size, in pixels, of the border of each tile (the
	 *            horizontal and vertical distance between where one tile stops
	 *            and the next begins)
	 * @param outsideBorder
	 *            whether or not the border goes around the outside of the sheet
	 * @param xOffset
	 *            the x distance from x=0 to where the tile grid begins
	 * @param yOffset
	 *            the y distance from y=0 to where the tile grid begins
	 */
	public RegularTileSheet(int tileWidth, int tileHeight, Image img,
			int borderSize, boolean outsideBorder, int xOffset, int yOffset) {
		sheet = new BufferedImage(img.getHeight(null), img.getWidth(null),
				BufferedImage.TYPE_INT_RGB);
		sheet.createGraphics().drawImage(img, 0, 0, null);

		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.borderSize = borderSize;
		this.outsideBorder = outsideBorder;

	}

	public WritableRaster copyData(WritableRaster raster) {
		return sheet.copyData(raster);
	}

	public ColorModel getColorModel() {
		return sheet.getColorModel();
	}

	public Raster getData() {
		return sheet.getData();
	}

	public Raster getData(Rectangle rect) {
		return sheet.getData(rect);
	}

	public int getHeight() {
		return sheet.getHeight();
	}

	public int getMinTileX() {
		return 0;
	}

	public int getMinTileY() {
		return 0;
	}

	public int getMinX() {
		return sheet.getMinX();
	}

	public int getMinY() {
		return sheet.getMinY();
	}

	public int getNumXTiles() {
		return sheet.getWidth() / (tileWidth+borderSize) + 1;
	}

	public int getNumYTiles() {
		return sheet.getHeight() / (tileHeight+borderSize) + 1;
	}

	public Object getProperty(String name) {
		return sheet.getProperty(name);
	}

	public String[] getPropertyNames() {
		return sheet.getPropertyNames();
	}

	public SampleModel getSampleModel() {
		return sheet.getSampleModel();
	}

	public Vector<RenderedImage> getSources() {
		return sheet.getSources();
	}

	public Raster getTile(int tileX, int tileY) {
		int x1, y1, x2, y2;
		x1 = (tileWidth + borderSize) * tileX;
		y1 = (tileHeight + borderSize) * tileY;
		x2 = (tileWidth + borderSize) * tileX + tileWidth;
		y2 = (tileHeight + borderSize) * tileY + tileHeight;
		if (outsideBorder) {
			x1 -= borderSize;
			y1 -= borderSize;
			x2 -= borderSize;
			y2 -= borderSize;
		}
		return sheet.getData(new Rectangle(x1, y1, x2, y2));
	}

	public int getTileGridXOffset() {
		return xGridOffset;
	}

	public int getTileGridYOffset() {
		return yGridOffset;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getWidth() {
		return sheet.getWidth();
	}
}
