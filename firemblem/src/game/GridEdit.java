package game;

// we're gonna need to replace cell with terrain i believe - Brian Clanton

// Arsy: no we ain't. This class uses Cell just to draw the map.

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.Scanner;
import java.io.*;
import java.util.Date;
import java.awt.geom.AffineTransform;

/**
 * Presumably we'll eventually develop something prettier, but this'll do for
 * now.
 * 
 * @author Xiagu
 */
public class GridEdit extends JFrame {
    /**
     * The width of the drawing area.
     */
    public int width = 840;

    /**
     * The height of the drawing area.
     */
    public int height = 640;

    /**
     * The height/width of Fire Emblem tiles is 16. For ease of use, this
     * program doubles that size for editing purposes.
     */
    public static final int TILE_SIZE = 32;

    /**
     * The image used for double buffering. It is drawn on "off-screen", then
     * drawn to the screen after drawing is finished so that the drawing process
     * is invisible to the user.
     */
    public BufferedImage image = new BufferedImage(width, height,
	    BufferedImage.TYPE_INT_RGB);

    /**
     * The Grahpics2D object of <code>image</code>.
     */
    public Graphics2D buffer = (Graphics2D) image.getGraphics();

    /**
     * The panel where all the drawing takes place. This is used to avoid having
     * to deal with the window decorations (title bar, borders) taking up space.
     */
    public JPanel drawPanel;

    /**
     * Updates 10 times per second. Calls repaint() (supposedly) every 10th of a
     * second.
     */
    Timer graphicsTimer = new Timer(100, new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	    repaint();
	}
    });

    /**
     * TODO: Support maps of other sizes (see other tasks).
     * 
     * The 2D array containing all the Cells in the map.
     */
    public Cell[][] map = new Cell[20][20];

    /**
     * The time since the last save, in milliseconds.
     */
    public long timeSinceLastSave = 0;

    /**
     * The time the map was last saved, in milliseconds.
     */
    public long timeLastSaved = new Date().getTime();

    /**
     * The filename of the map.
     */
    public String filename;

    // public BufferedImage cursor = new BufferedImage(12, 21,
    // BufferedImage.TYPE_INT_RGB);

    /**
     * Constructs a new <code>GridEdit</code> object. Currently all maps will be
     * 20x20, but eventually maps of different sizes will be supported.
     */
    public GridEdit() {
	setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

	// Is in another panel to avoid messing with the sizes of window
	// decorations >_<
	drawPanel = new JPanel() {
	    public void paint(Graphics g) {
		super.paint(g);

		buffer.clearRect(0, 0, width, height);

		for (int i = 0; i < map.length; i++) {
		    for (int j = 0; j < map.length; j++) {
			map[i][j].draw(buffer, i * TILE_SIZE, j * TILE_SIZE,
				TILE_SIZE);
		    }
		}

		buffer.setColor(Color.black);
		for (int x = 0; x < map.length; x++) {
		    for (int y = 0; y < map.length; y++) {
			buffer
				.drawLine(0, y * TILE_SIZE, height, y
					* TILE_SIZE);
			buffer
				.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE,
					height);
		    }
		}

		// Draw labels on the right.
		// TODO: Replace with drawing Terrain images.
		AffineTransform savedAT = buffer.getTransform();
		buffer.translate(height, 0);
		drawIcons(buffer);
		buffer.setTransform(savedAT);

		g.drawImage(image, 0, 0, null);
	    }

	};
	drawPanel.setPreferredSize(new Dimension(width, height));
	drawPanel.addMouseListener(new MouseAdapter() {
	    public void mousePressed(MouseEvent evt) {
		int mouseX = evt.getX();
		int mouseY = evt.getY();

		if (mouseX <= height) {
		    if (evt.getButton() == MouseEvent.BUTTON1) {
			// LMB - INCREMENT
			map[mouseX / TILE_SIZE][mouseY / TILE_SIZE].terrain++;
			map[mouseX / TILE_SIZE][mouseY / TILE_SIZE].terrain %= Cell.NUMSPEC;
		    }
		    if (evt.getButton() == MouseEvent.BUTTON3) {
			// RMB - DECREMENT
			map[mouseX / TILE_SIZE][mouseY / TILE_SIZE].terrain--;
			map[mouseX / TILE_SIZE][mouseY / TILE_SIZE].terrain = (map[mouseX
				/ TILE_SIZE][mouseY / TILE_SIZE].terrain + Cell.NUMSPEC)
				% Cell.NUMSPEC;
		    } else if (evt.getButton() == MouseEvent.BUTTON2) {
			// SCROLL WHEEL - SAVE
			try {
			    PrintWriter writer = new PrintWriter(filename);
			    for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
				    // Only saves non-empty cells :D
				    if (map[i][j].terrain != Cell.TER_EMPTY) {
					writer.println("" + i + ' ' + j + ' '
						+ map[i][j].terrain);
				    }
				}
			    }
			    writer.close();
			    timeLastSaved = new Date().getTime();
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			    System.exit(1);
			}
		    }
		} else {
		    /**
		     * <pre>
		     * TODO: Eventually re-implement this feature, but only after the Terrain-image thing is finished. (This was code for letting you pick the color you wanted to fill cells with, basically.)
		     * </pre>
		     */
		    // try {
		    // if (mouseX <= 900 && mouseX >= 850) {
		    // if (mouseY >= 50 && mouseY <= 100) {
		    // cursor = ImageIO.read(new File("1_cursor.png"));
		    // Cursor mouse = Toolkit.getDefaultToolkit()
		    // .createCustomCursor(null,
		    // new Point(0, 0), "Green");
		    // window.setCursor(mouse);
		    // } else if (mouseY >= 150 && mouseY <= 200) {
		    // } else if (mouseY >= 250 && mouseY <= 300) {
		    // }
		    // }
		    // } catch (IOException e) {
		    // }
		}
	    }
	});

	add(drawPanel);

	// for buffer.clearRect
	buffer.setBackground(Color.black);

	// initialize all the cells to empty
	for (int i = 0; i < map.length; i++)
	    for (int j = 0; j < map.length; j++)
		map[i][j] = new Cell(Cell.TER_EMPTY, i, j);

	// choose file
	JFileChooser filePicker = new JFileChooser("..");
	filePicker.setFileFilter(new FileNameExtensionFilter(
		"FirEmblem++ MAP files", "map"));
	if (filePicker.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
	    filename = filePicker.getSelectedFile().getPath();
	    if (filename == null || filename.equals(""))
		System.exit(0);
	} else
	    // was canceled
	    System.exit(1);

	readMapFromFile();

	setTitle("Map Editor");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	pack();
	graphicsTimer.start();
	setVisible(true);
    }

    /**
     * Attempts to read map data from file specified by the global filename
     * variable. This is ugly and disgusting, but there isn't a way around it
     * because the saving function needs the filename, and I don't know how it
     * could be passed in. <br>
     * <br>
     * If the file specified by filename doesn't exist (causing Scanner to throw
     * a FileNotFoundException), then this code creates a PrintWriter to said
     * filename and prints "" to it to create it.
     * 
     * @author Xiagu
     */
    private void readMapFromFile() {
	int cur, curX, curY;
	try {
	    Scanner reader = new Scanner(new FileReader(filename));
	    // TODO: Read map dimensions from file as well.
	    while (reader.hasNext()) {
		if (reader.hasNextInt()) {
		    curX = reader.nextInt();
		    curY = reader.nextInt();
		    cur = reader.nextInt();
		    map[curX][curY].terrain = cur;
		}
	    }
	    reader.close();

	} catch (FileNotFoundException e) {
	    try {
		PrintWriter fileMaker = new PrintWriter(new File(filename));
		fileMaker.print("");
		fileMaker.close();
		// TODO: Ask for dimensions of new map.
	    } catch (FileNotFoundException e1) {
		// Some crazy shit happened and the file couldn't be created
		System.out.println("You really fucked this up somehow.");
	    }
	}
    }

    /**
     * Just a lame ol' little main class for testing purposes. Makes a new
     * GridEdit and sets its location to 20, 20.
     * 
     * @param aaarrggghhs
     */
    public static void main(String aaarrggghhs[]) {
	GridEdit window = new GridEdit();
	window.setLocation(20, 20);
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g);

	timeSinceLastSave = new Date().getTime() - timeLastSaved;

	setTitle("Map Editor - " + timeSinceLastSave / 1000);
    }

    /**
     * Draws a white-bordered colored square next to the name of what it is on
     * the right.
     * 
     * @param buffer
     */
    private void drawIcons(Graphics2D buffer) {
	buffer.setColor(Color.green);
	buffer.fillRect(50, 50, 50, 50);
	buffer.drawString("Empty", 110, 75);
	buffer.setColor(Color.white);
	buffer.drawRect(50, 50, 50, 50);

	buffer.setColor(Color.yellow);
	buffer.fillRect(50, 150, 50, 50);
	buffer.drawString("Path", 110, 175);
	buffer.setColor(Color.white);
	buffer.drawRect(50, 150, 50, 50);

	buffer.setColor(Color.blue);
	buffer.fillRect(50, 250, 50, 50);
	buffer.drawString("Tower", 110, 275);
	buffer.setColor(Color.white);
	buffer.drawRect(50, 250, 50, 50);

	buffer.setColor(Color.red);
	buffer.fillRect(50, 350, 50, 50);
	buffer.drawString("Castle", 110, 375);
	buffer.setColor(Color.white);
	buffer.drawRect(50, 350, 50, 50);

	buffer.setColor(Color.magenta.darker().darker());
	buffer.fillRect(50, 450, 50, 50);
	buffer.drawString("Entrance", 110, 475);
	buffer.setColor(Color.white);
	buffer.drawRect(50, 450, 50, 50);
    }
}
