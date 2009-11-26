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
import java.util.Map;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.ParseMethods;
import data.TerrainType;
import data.WeaponSerializer;
import data.WeaponType;

public class CharacterChooser extends JFrame {

    private JComboBox one, two, three, four, five, six;
    private JLabel type1, type2;
    private JPanel pane, panel1, panel2;
    private String[] characterNames;
    private String[] array, array2;
    private JButton finalize;
    private String[] terrainNames;
    private static HashMap<String, Weapon> weaponsMap;
    public String w1, w2;

    // TODO: Add some comments, Cy.
    public CharacterChooser() throws ClassNotFoundException, IOException {
	super("Character Chooser - Brian Clanton 11/6/09");
	pane = new JPanel();
	pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
	panel1 = new JPanel(new FlowLayout());
	panel2 = new JPanel(new FlowLayout());

	generateHashMap();

	type1 = new JLabel("Character One Options");
	type2 = new JLabel("Character Two Options");

	type1.setHorizontalAlignment(SwingConstants.CENTER);
	type1.setAlignmentX(Component.CENTER_ALIGNMENT);
	type2.setHorizontalAlignment(SwingConstants.CENTER);
	type2.setAlignmentX(Component.CENTER_ALIGNMENT);

	readCharacterNames();
	readTerrainNames();

	one = new JComboBox(characterNames);
	two = new JComboBox(characterNames);
	three = new JComboBox(terrainNames);
	four = new JComboBox(terrainNames);
	five = new JComboBox(getWeaponsList(one.getSelectedIndex()));
	six = new JComboBox(getWeaponsList(two.getSelectedIndex()));

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

	two.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		two.validate();
		array2 = getWeaponsList(two.getSelectedIndex());
		six.removeAllItems();
		for (int i = 0; i < array2.length; i++) {
		    six.addItem(array2[i]);
		}
		w2 = (String) six.getSelectedItem();
		pane.validate();
	    }
	});

	finalize = new JButton("Finalize");
	finalize.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		incorporateChanges();
	    }
	});
	finalize.setAlignmentX(Component.CENTER_ALIGNMENT);

	panel1.add(one);
	panel1.add(five);
	panel1.add(three);
	panel2.add(two);
	panel2.add(six);
	panel2.add(four);

	pane.add(type1);
	pane.add(panel1);
	pane.add(type2);
	pane.add(panel2);
	pane.add(finalize);

	add(pane);
	setVisible(true);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setSize(365, 175);
	setAlwaysOnTop(true);
    }// end constructor

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
		weaponsMap.put(s.substring(0, s.indexOf(",")), w);
	    }
	} catch (FileNotFoundException e) {
	    System.out.println("hee");
	}
    }

    public String[] getWeaponsList(int n) {
	try {
	    Scanner console = new Scanner(
		    new FileReader("src/data/weapons.txt"));
	    ArrayList<String> weaponsList = new ArrayList<String>();
	    String s = console.nextLine();

	    /*
	     * HashSet<String> set =
	     * ParseMethods.toCharacterType(characterNames[n]).usableWeapons;
	     * while(console.hasNext()){ s = console.nextLine(); if() }
	     */

	    switch (ParseMethods.toCharacterType(characterNames[n])) {
	    case GNRL:
		while (!s.contains(", Sword,"))
		    s = console.nextLine();
		while (s.contains(", Sword,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		while (s.contains(", Lance,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		while (s.contains(", Axe,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		break;

	    case HERO:
		while (!s.contains(", Sword,"))
		    s = console.nextLine();
		while (s.contains(", Sword,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		while (!s.contains(", Axe,"))
		    s = console.nextLine();
		while (s.contains(", Axe,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		break;

	    case THF:
	    case MYR:
	    case MCNRY:
	    case LORD:
		while (!s.contains(", Sword,"))
		    s = console.nextLine();
		while (s.contains(", Sword,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		break;

	    case PRT:
	    case FGT:
	    case JNYMN:
	    case BRSKR:
		while (!s.contains(", Axe,"))
		    s = console.nextLine();
		while (s.contains(", Axe,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		break;

	    case ARCH:
		while (!s.contains(", Bow,"))
		    s = console.nextLine();
		while (s.contains(", Bow,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		break;

	    case PUPIL:
	    case MAGE:
		while (!s.contains(", Anima,"))
		    s = console.nextLine();
		while (s.contains(", Anima,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		break;

	    case TRBDR:
	    case CLR:
		while (!s.contains(", Staff,"))
		    s = console.nextLine();
		while (s.contains(", Staff,") && console.hasNext()) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		break;

	    case RCRT:
	    case PKNG:
	    case KNG:
		while (!s.contains(", Lance,"))
		    s = console.nextLine();
		while (s.contains(", Lance,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		break;

	    case FKNG:
	    case CVLR:
	    case PLDN:
		while (!s.contains(", Sword,"))
		    s = console.nextLine();
		while (s.contains(", Sword,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		while (s.contains(", Lance,")) {
		    weaponsList.add(s.substring(0, s.indexOf(",")).trim());
		    s = console.nextLine();
		}
		break;
	    }
	    Object[] ar = weaponsList.toArray();
	    String[] ar2 = new String[ar.length];
	    ar2 = new String[ar.length];
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

    private void readTerrainNames() {
	try {
	    Scanner r = new Scanner(new FileReader("src/test/terrains.txt"));
	    String line = "";
	    ArrayList<String> t = new ArrayList<String>();

	    while (r.hasNext()) {
		line = r.nextLine();
		t.add(line);
	    }// end while

	    terrainNames = new String[t.size()];
	    for (int i = 0; i < t.size(); i++)
		terrainNames[i] = t.get(i);
	}// end try
	catch (FileNotFoundException e) {
	    System.out.println("File not found.");
	    System.exit(0);
	}// end catch
    }// end readTypes

    private void incorporateChanges() {
	int characterChoiceOne = one.getSelectedIndex(), characterChoiceTwo = two
		.getSelectedIndex(), terrainChoiceOne = three
		.getSelectedIndex(), terrainChoiceTwo = four.getSelectedIndex();
	Character c1, c2;
	TerrainType t1, t2;

	finalize.setEnabled(false);
	one.setEnabled(false);
	two.setEnabled(false);

	c1 = new Character(ParseMethods
		.toCharacterType(characterNames[characterChoiceOne]),
		getActiveWeapon((String) five.getSelectedItem()), 0, 0);
	c2 = new Character(ParseMethods
		.toCharacterType(characterNames[characterChoiceTwo]),
		getActiveWeapon((String) six.getSelectedItem()), 0, 1);

	t1 = ParseMethods.toTerrainType(terrainNames[terrainChoiceOne]);
	t2 = ParseMethods.toTerrainType(terrainNames[terrainChoiceTwo]);

	dispose();
	AttackPhaseTester test = new AttackPhaseTester(c1, c2, t1, t2);

    }// end incorporateChanges

    public static Weapon getActiveWeapon(String s) {
	return weaponsMap.get(s);
    }// end getActiveWeapon

}
