package test;

import game.Character;
import game.Weapon;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import data.ParseMethods;
import data.TerrainType;
import data.WeaponSerializer;
import data.WeaponType;

public class CharacterGenerator extends JFrame {

    public Character c1;
    private JComboBox one, five;
    private JTextField text;
    private JLabel type1;
    private JPanel pane, panel1;
    private String[] characterNames;
    private String[] array;
    private JButton finalize;
    private static HashMap<String, Weapon> weaponsMap;
    public String w1;

    public static void main(String[]args) throws ClassNotFoundException, IOException{
	new CharacterGenerator();
    }
    /**
     * Generates the character chooser panel
     * 
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public CharacterGenerator() throws ClassNotFoundException, IOException {
	super("Character Generator");

	pane = new JPanel();

	pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
	panel1 = new JPanel(new FlowLayout());

	generateHashMap();
	readCharacterNames();

	type1 = new JLabel("Character Options");

	type1.setHorizontalAlignment(SwingConstants.CENTER);
	type1.setAlignmentX(Component.CENTER_ALIGNMENT);


	one = new JComboBox(characterNames);
	five = new JComboBox(getWeaponsList(one.getSelectedIndex()));
	text = new JTextField("Name", 10);

	one.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		one.validate();
		array = getWeaponsList(one.getSelectedIndex());
		five.removeAllItems();
		for (int i = 0; i < array.length; i++) {
		    five.addItem(array[i]);
		}
		w1 = (String) five.getSelectedItem();
		pane.validate();
	    }
	});

	finalize = new JButton("Finalize");
	finalize.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		incorporateChanges();
		dispose();
	    }
	});
	finalize.setAlignmentX(Component.CENTER_ALIGNMENT);

	panel1.add(text);
	panel1.add(one);
	panel1.add(five);

	pane.add(type1);
	pane.add(panel1);
	pane.add(finalize);

	add(pane);

	setVisible(true);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setBounds(500, 300, 400, 175);
	setAlwaysOnTop(true);
    }// end constructor

    /**
     * Generates the Weapon HashMap that holds all the Weapons using the name as
     * a key
     * 
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void generateHashMap() throws ClassNotFoundException, IOException {
	try {
	    Scanner console = new Scanner(
		    new FileReader("src/data/weapons.txt"));
	    weaponsMap = new HashMap<String, Weapon>();
	    String s = "";
	    String ss = "";
	    while (console.hasNext()) {
		s = console.nextLine();
		ss = s.substring(0, s.indexOf(","));
		Weapon w = new Weapon(ss);
		weaponsMap.put(ss, w);
	    }
	} catch (FileNotFoundException e) {
	}
    }

    /**
     * Creates the list of possible Weapons for a character based on the CharacterType
     * 
     * @param n
     * @return
     */
    public String[] getWeaponsList(int n) {
	try {
	    Scanner console = new Scanner(
		    new FileReader("src/data/weapons.txt"));
	    ArrayList<String> weaponsList = new ArrayList<String>();
	    String s = console.nextLine();
	    Iterator<String> itr;

	    ParseMethods.initializeCharacterSearchMap();

	    HashSet<String> set = ParseMethods
	    .toCharacterType(characterNames[n]).usableWeapons;
	    while (console.hasNext()) {
		s = console.nextLine();
		itr = set.iterator();
		while (itr.hasNext()) {
		    if (s.contains(itr.next()))
			weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		}// end while
	    }// end while

	    Object[] ar = weaponsList.toArray();
	    String[] ar2 = new String[ar.length];
	    for (int i = 0; i < ar.length; i++)
		ar2[i] = (String) ar[i];
	    return ar2;
	} catch (FileNotFoundException e) {
	    return null;
	}
    }

    private void readCharacterNames() {
	try {
	    Scanner r = new Scanner(new FileReader(
	    "src/test/charactertypes.txt"));
	    String line = "";
	    ArrayList<String> t = new ArrayList<String>();

	    while (r.hasNext()) {
		line = r.nextLine();
		t.add(line);
	    }// end while

	    characterNames = new String[t.size()];
	    for (int i = 0; i < t.size(); i++)
		characterNames[i] = t.get(i);
	}// end try
	catch (FileNotFoundException e) {
	    System.out.println("File not found.");
	    System.exit(0);
	}// end catch
    }// end readTypes

    private Character incorporateChanges() {
	int characterChoiceOne = one.getSelectedIndex();
	finalize.setEnabled(false);
	one.setEnabled(false);
	five.setEnabled(false);

	c1 = new Character(ParseMethods
		.toCharacterType(characterNames[characterChoiceOne]),
		getActiveWeapon((String) five.getSelectedItem()), 0, 0);
	c1.setName(text.getSelectedText());
	return c1;
    }// end incorporateChanges

    public static Weapon getActiveWeapon(String s) {
	return weaponsMap.get(s);
    }// end getActiveWeapon

}
