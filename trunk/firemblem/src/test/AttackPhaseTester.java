/*
 * Brian Clanton
 * 10/30/09
 * 
 * Sorry images are cut off, will figure out how to fix later.
 */
package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import game.Character;
import data.CharacterType;
import data.ParseMethods;
import data.StatType;
import data.TerrainType;
import game.Map;

/**
 * 
 * @author Brian Clanton
 *
 */

public class AttackPhaseTester extends JFrame{

	private static CharacterChooser a;
	private JPanel pane, stats, b;
	private JLabel img1, img2, name1 = new JLabel(),
	name2 = new JLabel(), weapon1 = new JLabel(), weapon2 = new JLabel(), 
	hp1 = new JLabel(), hp2  = new JLabel(), ter1 = new JLabel(), ter2 = new JLabel();
	public Character one, two;
	private JButton start, next, reset;
	private JTextArea log = new JTextArea(10, 500);
	private boolean characterOneTurn = true;
	private int currHp1, currHp2;
	private JScrollPane l;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem settings;
	public static Map g = new Map(4,4);	

	//Action listener for the attack button
	class AttackButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			next.setEnabled(false);
			String a = "";
			if(characterOneTurn){
				a += one.attack(two);
				a += two.attack(one);
				if(one.doubleAttack(two) && one.status != StatType.DEAD && two.status != StatType.DEAD){
					a += one.name + " gets a Double Attack\n";						
					a += one.attack(two);
				}//end if
				else if(two.doubleAttack(one) && one.status != StatType.DEAD && two.status != StatType.DEAD){
					a += two.name + " gets a Double Attack\n";
					a += two.attack(one);
				}//end if
				start.setText(two.name + "'s Attack");
			}//end if
			else{
				a += two.attack(one);
				a += one.attack(two);
				if(two.doubleAttack(one) && one.status != StatType.DEAD && two.status != StatType.DEAD){
					a += two.name + " gets a Double Attack\n";
					a += two.attack(one);
				}//end if
				else if(one.doubleAttack(two) && one.status != StatType.DEAD && two.status != StatType.DEAD){
					a += one.name + " gets a Double Attack\n";						
					a += one.attack(two);
				}//end if
				start.setText(one.name + "'s Attack");
			}//end else
			log.setText(log.getText() + "\n" + a);
			if(one.status == StatType.DEAD){
				hp1.setText("DEAD");
				start.setEnabled(false);
				start.setText("Time to Reset");
				next.setEnabled(false);
			}//end if
			else
				hp1.setText("" + one.currHp + " / " + one.hp);
			if(two.status == StatType.DEAD){
				hp2.setText("DEAD");
				start.setEnabled(false);
				start.setText("Time to Reset");
				next.setEnabled(true);
			}//end if
			else
				hp2.setText("" + two.currHp + " / " + two.hp);
			characterOneTurn = !characterOneTurn;
		}//end actionPerformed
	}//end AttackButtonListener

	//Action listener for reset button
	class ResetButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			initialize();
		}
	}//end ResetButtonListener

	//Action listener for menu item
	class CharacterSettingsListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispose();
			try {
				CharacterChooser c = new CharacterChooser();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}//end CharacterSetttingsListener

	class NextButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(one.hp > 0)
				next();
			else
				initialize();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ParseMethods.initializeCharacterSearchMap();
		ParseMethods.initializeTerrainSearchMap();
		JFrame.setDefaultLookAndFeelDecorated(true);
		a = new CharacterChooser();
	}//end main

	public AttackPhaseTester(Character c1, Character c2, TerrainType t1, TerrainType t2){
		super("Attack Phase Tester - Brian Clanton 10/30/09");
		//initialization of containers
		pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		stats = new JPanel(new GridLayout(5, 2));
		b = new JPanel(new FlowLayout());

		//initialization of characters
		one = c1;
		two = c2;

		//initialization of map
		g.changeAll(TerrainType.GRASS);
		g.changeSpace(c1.position.x, c1.position.y, t1);
		g.changeSpace(c2.position.x, c2.position.y, t2);

		//initialization of JButtons
		start = new JButton();
		start.addActionListener(new AttackButtonListener());
		next = new JButton("Next Opponent");
		next.addActionListener(new NextButtonListener());
		next.setEnabled(false);
		reset = new JButton("Reset");
		reset.addActionListener(new ResetButtonListener());

		//initialization of JMenu
		settings = new JMenuItem("Change Characters...");
		settings.addActionListener(new CharacterSettingsListener());
		menu = new JMenu("Settings");
		menu.add(settings);
		menuBar = new JMenuBar();
		menuBar.add(menu);
		setJMenuBar(menuBar);

		initialize();

		//centers text in JLabels
		name1.setHorizontalAlignment(SwingConstants.CENTER);
		name2.setHorizontalAlignment(SwingConstants.CENTER);
		weapon1.setHorizontalAlignment(SwingConstants.CENTER);
		weapon2.setHorizontalAlignment(SwingConstants.CENTER);
		hp1.setHorizontalAlignment(SwingConstants.CENTER);
		hp2.setHorizontalAlignment(SwingConstants.CENTER);
		ter1.setHorizontalAlignment(SwingConstants.CENTER);
		ter2.setHorizontalAlignment(SwingConstants.CENTER);

		log.setEditable(false);

		img1 = new JLabel(one.classImage);
		img2 = new JLabel(two.classImage);

		//objects added to stats JPanel
		stats.add(name1);
		stats.add(name2);
		stats.add(img1);
		stats.add(img2);
		stats.add(weapon1);
		stats.add(weapon2);
		stats.add(ter1);
		stats.add(ter2);
		stats.add(hp1);
		stats.add(hp2);

		//objects added to button JPanel
		b.add(start);
		b.add(next);
		b.add(reset);

		//makes the log scrollable
		l = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		//panels added to container
		pane.add(stats);
		pane.add(b);
		pane.add(l);

		setContentPane(pane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 700);
		setVisible(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
	}//end constructor

	private void next(){
		start.setEnabled(true);
		start.setText(one.name + "'s Attack");
		int t = (int)(Math.random()*21);
		CharacterType newType = getCharacterType(t);
		two = new Character(newType, Character.getDefaultWeapon(newType), 0, 1);
		weapon2.setText(two.activeWeapon.name);
		currHp2 = two.hp;
		hp2.setText("" + currHp2 + " / " + two.hp);
		stats.remove(img2);
		two.classImage = new ImageIcon("images/" + two.name.toLowerCase() + ".gif");
		img2 = new JLabel(two.classImage);
		stats.add(img2, 3);
		stats.validate();
		name1.setText(one.name);
		name2.setText(two.name);
		ter1.setText(g.grid[one.position.x][one.position.y].type.name);
		ter2.setText(g.grid[two.position.x][two.position.y].type.name);
		characterOneTurn = true;
		log.setText("");
	}

	private void initialize(){
		start.setEnabled(true);
		start.setText(one.name + "'s Attack");
		one = new Character(one.type, one.activeWeapon, 0, 0);
		two = new Character(two.type, two.activeWeapon, 0, 1);
		weapon1.setText(one.activeWeapon.name);
		weapon2.setText(two.activeWeapon.name);
		currHp1 = one.hp;
		currHp2 = two.hp;
		hp1.setText("" + currHp1 + " / " + one.hp);
		hp2.setText("" + currHp2 + " / " + two.hp);
		name1.setText(one.name);
		name2.setText(two.name);
		ter1.setText(g.grid[one.position.x][one.position.y].type.name);
		ter2.setText(g.grid[two.position.x][two.position.y].type.name);
		log.setText("");
	}//end initialize	

	// TODO: comments please
	public CharacterType getCharacterType(int t){
		switch(t){
		case 1: 
			return CharacterType.MYR;
		case 2: 
			return CharacterType.LORD;
		case 3: 
			return CharacterType.KNG;
		case 4: 
			return CharacterType.CLR;
		case 5: 
			return CharacterType.MAGE;
		case 6: 
			return CharacterType.ARCH;
		case 7: 
			return CharacterType.FGT;
		case 8: 
			return CharacterType.RCRT;
		case 9: 
			return CharacterType.CVLR;
		case 10: 
			return CharacterType.PLDN;
		case 11: 
			return CharacterType.BRSKR;
		case 12: 
			return CharacterType.GNRL;
		case 13: 
			return CharacterType.MCNRY;
		case 14: 
			return CharacterType.PKNG;
		case 15: 
			return CharacterType.JNYMN;
		case 16: 
			return CharacterType.PUPIL;
		case 17: 
			return CharacterType.HERO;
		case 18: 
			return CharacterType.TRBDR;
		case 19: 
			return CharacterType.PRT;
		case 20: 
			return CharacterType.FKNG;
		case 21: 
			return CharacterType.THF;
		}
		return CharacterType.RCRT;
	}
}//end class AttackPhaseTester