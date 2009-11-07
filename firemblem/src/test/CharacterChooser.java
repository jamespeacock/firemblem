package test;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import data.CharacterType;
import data.WeaponType;
import game.Character;
import game.Weapon;

public class CharacterChooser extends JFrame{
	
	private JComboBox one, two;
	private JLabel type1, type2;
	private JPanel pane, panel1, panel2;
	private String [] characterNames;
	private CharacterType[] characterTypes = {CharacterType.MYR, CharacterType.LORD, CharacterType.KNG, CharacterType.CLR,
			CharacterType.MAGE, CharacterType.ARCH, CharacterType.FGT};
	private JButton finalize;
	
	public CharacterChooser(){
		super("Character Chooser - Brian Clanton 11/6/09");
		pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		
		type1 = new JLabel("Choose Character One Type: ");
		type2 = new JLabel("Choose Character Two Type: ");
		
		type1.setHorizontalAlignment(SwingConstants.CENTER);
		type2.setHorizontalAlignment(SwingConstants.CENTER);
		
		readTypes();
		one = new JComboBox(characterNames);
		two = new JComboBox(characterNames);
		
		finalize = new JButton("Finalize");
		finalize.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				incorporateChanges();
			}
		});
		finalize.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel1.add(type1);
		panel1.add(one);
		panel2.add(type2);
		panel2.add(two);
		pane.add(panel1);
		pane.add(panel2);
		pane.add(finalize);
		
		add(pane);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(365, 150);
		setAlwaysOnTop(true);
	}//end constructor
	
	private void readTypes(){
		try{
			Scanner r = new Scanner(new FileReader("src/test/CharacterChooserData.txt"));
			String line = "";
			ArrayList<String> t = new ArrayList<String> ();
			
			while(!line.equals("types:"))
				line = r.nextLine();
			while(r.hasNext()){
				line = r.nextLine();
				t.add(line);
			}//end while
			
			characterNames = new String [t.size()];
			for(int i = 0; i < t.size(); i++)
				characterNames[i] = t.get(i);
		}//end try
		catch(FileNotFoundException e){
			System.out.println("File not found.");
			System.exit(0);
		}//end catch
	}//end readTypes
	
	private void incorporateChanges(){
		int choiceOne = one.getSelectedIndex(), choiceTwo = two.getSelectedIndex();
		Character c1, c2;
		
		finalize.setEnabled(false);
		one.setEnabled(false);
		two.setEnabled(false);
		
		c1 = new Character(characterTypes[choiceOne], 0, 0);
		c2 = new Character(characterTypes[choiceTwo], 0, 1);
		
		c1.activeWeapon = getActiveWeapon(c1);
		c2.activeWeapon = getActiveWeapon(c2);
		
		dispose();
		AttackPhaseTester test = new AttackPhaseTester(c1, c2);		
		
	}//end incorporateChanges
		
	public static Weapon getActiveWeapon(Character c){
		Weapon w = null;
		switch(c.type){
			case MYR:
				w = new Weapon("Iron Sword", WeaponType.SWORD, 5, 90, 0, 5, 45, 1);
			break;
			
			case FGT:
				w = new Weapon("Iron Axe", WeaponType.AXE, 8, 75, 0, 10, 45, 1);
			break;
			
			case LORD:
				w = new Weapon("Rapier", WeaponType.SWORD, 7, 95, 10, 5, 40, 1);
			break;
			
			case ARCH:
				w = new Weapon("Iron Bow", WeaponType.BOW, 6, 85, 0, 5, 45, 2);
			break;
			
			case MAGE:
				w = new Weapon("Fire", WeaponType.ANIMA, 5, 90, 0, 4, 40, 2);
			break;
			
			case CLR:
				w = new Weapon("Heal", WeaponType.STAFF, 7, 0, 0, 0, 30, 1);
			break;
			
			case KNG:
				w = new Weapon("Iron Lance", WeaponType.LANCE, 7, 80, 0, 8, 45, 1);
			break;
		}//end switch
		return w;
	}//end getActiveWeapon
}
