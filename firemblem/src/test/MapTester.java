package test;

import game.Cell;
import game.Character;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class MapTester
{
    public CharacterGenerator cgen;
    public Cell[][] map = new Cell[20][20];
    public HashMap<String, Character> characters;
    private int numCharacters;

    public static void main(String[]args) throws ClassNotFoundException, IOException, InterruptedException
    {
	new MapTester();
    }

    public MapTester() throws ClassNotFoundException, IOException, InterruptedException
    {
	// initialize all the cells to empty
	for (int i = 0; i < map.length; i++)
	    for (int j = 0; j < map.length; j++)
		map[i][j] = new Cell(Cell.TER_EMPTY, i, j);

	//load a previously saved map
	readMapFromFile("src/game/testmap.map");

	//get the characters for Player1
	generatePlayer1();
    }

    private void readMapFromFile(String filename) {
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
	    }
	}
    }

    /**
     * This is the part that needs to be fixed you guys.
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws InterruptedException
     */
    private void generatePlayer1() throws ClassNotFoundException, IOException, InterruptedException{
	JOptionPane getNumCharacters = new JOptionPane();
	getNumCharacters.setWantsInput(true);
	getNumCharacters.setInitialSelectionValue(1);
	numCharacters = Integer.parseInt(JOptionPane.showInputDialog("How many characters do you control?"));
	Character[] chars = new Character[numCharacters];
	for(int i = 0; i < numCharacters; i++){
	    cgen = new CharacterGenerator();
	    Thread.sleep(10000);
	    chars[i] = cgen.c1;
	    System.out.println(chars[i].name);
	}
    }
}