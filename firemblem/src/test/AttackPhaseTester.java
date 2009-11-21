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

import javax.swing.BoxLayout;
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
import data.ParseMethods;
import data.StatType;
import data.TerrainType;
import game.Map;

public class AttackPhaseTester extends JFrame{

	private JPanel pane, stats, b;
	private JLabel img1, img2, name1 = new JLabel(),
		name2 = new JLabel(), weapon1 = new JLabel(), weapon2 = new JLabel(), 
		hp1 = new JLabel(), hp2  = new JLabel(), ter1 = new JLabel(), ter2 = new JLabel();
	public Character one, two;
	private JButton start, reset;
	private JTextArea log = new JTextArea(10, 500);
	private boolean characterOneTurn = true;
	private int max1, max2;
	private JScrollPane l;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem settings;
	public static Map g = new Map(4,4);	
	
	class AttackButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
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
			}//end if
			else
				hp1.setText("" + one.hp + " / " + max1);
			if(two.status == StatType.DEAD){
				hp2.setText("DEAD");
				start.setEnabled(false);
				start.setText("Time to Reset");
			}//end if
			else
				hp2.setText("" + two.hp + " / " + max2);
			characterOneTurn = !characterOneTurn;
		}
	}//end AttackButtonListener
	
	class ResetButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			initialize();
		}
	}//end ResetButtonListener
	
	class CharacterSettingsListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispose();
			CharacterChooser c = new CharacterChooser();
		}
	}//end CharacterSetttingsListener
	
	public static void main(String[] args) {
		ParseMethods.initializeCharacterSearchMap();
		ParseMethods.initializeTerrainSearchMap();
		JFrame.setDefaultLookAndFeelDecorated(true);
		CharacterChooser a = new CharacterChooser();
	}//end main
	
	public AttackPhaseTester(Character c1, Character c2, TerrainType t1, TerrainType t2){
		super("Attack Phase Tester - Brian Clanton 10/30/09");
		pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		stats = new JPanel(new GridLayout(5, 2));
		b = new JPanel(new FlowLayout());
		
		one = c1;
		two = c2;
		
		g.changeAll(TerrainType.GRASS);
		g.changeSpace(c1.position.x, c1.position.y, t1);
		g.changeSpace(c2.position.x, c2.position.y, t2);
		
		start = new JButton();
		start.addActionListener(new AttackButtonListener());
		reset = new JButton("Reset");
		reset.addActionListener(new ResetButtonListener());
		
		settings = new JMenuItem("Change Characters...");
		settings.addActionListener(new CharacterSettingsListener());
		menu = new JMenu("Settings");
		menu.add(settings);
		menuBar = new JMenuBar();
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		initialize();
		
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
		
		b.add(start);
		b.add(reset);
		
		l = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		/*t = new JPanel();
		t.add(l);
		*/
		
		pane.add(stats);
		pane.add(b);
		pane.add(l);
		
		setContentPane(pane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 700);
		setVisible(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
	}//end constructor
	
	private void initialize(){
		start.setEnabled(true);
		start.setText(one.name + "'s Attack");
		one = new Character(one.type, 0, 0);
		two = new Character(two.type, 0, 1);
		one.activeWeapon = CharacterChooser.getActiveWeapon(one);
		two.activeWeapon = CharacterChooser.getActiveWeapon(two);
		weapon1.setText(one.activeWeapon.name);
		weapon2.setText(two.activeWeapon.name);
		max1 = one.hp;
		max2 = two.hp;
		hp1.setText("" + one.hp + " / " + max1);
		hp2.setText("" + two.hp + " / " + max2);
		name1.setText(one.name);
		name2.setText(two.name);
		ter1.setText(g.grid[one.position.x][one.position.y].name);
		ter2.setText(g.grid[two.position.x][two.position.y].name);
		log.setText("");
	}//end initialize	

}
