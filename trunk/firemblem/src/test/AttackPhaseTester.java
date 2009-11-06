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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import game.Character;
import game.Weapon;
import data.CharacterType;
import data.TerrainType;
import data.WeaponType;
import game.Map;

public class AttackPhaseTester extends JFrame{

	private JPanel pane, stats, b;
	private JLabel img1, img2, name1 = new JLabel(),
		name2 = new JLabel(), weapon1 = new JLabel(), weapon2 = new JLabel(), 
		hp1 = new JLabel(), hp2  = new JLabel(), ter1 = new JLabel(), ter2 = new JLabel();
	private Character one, two;
	private JFrame container;
	private JButton start, reset;
	private JTextArea log = new JTextArea();
	private boolean characterOneTurn = true;
	private int max1, max2;
	private JScrollPane l;
	
	class AttackButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String a = "";
			if(characterOneTurn){
				a += one.attack(two);
				a += two.attack(one);
				if(one.doubleAttack(two) && two.hp > 0){
					a += "Double Attack\n";						
					a += one.attack(two);
				}//end if
			}//end if
			else{
				a += two.attack(one);
				a += one.attack(two);
				if(two.doubleAttack(one) && one.hp > 0){
					a += "Double Attack\n";
					a += two.attack(one);
				}//end if
			}//end else
			log.setText(log.getText() + "\n" + a);
			if(one.hp < 1){
				hp1.setText("DEAD");
				start.setEnabled(false);
			}//end if
			else
				hp1.setText("" + one.hp + " / " + max1);
			if(two.hp < 1){
				hp2.setText("DEAD");
				start.setEnabled(false);
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
	
	public static Map g = new Map(4,4);
	
	public static void main(String[] args) {
		AttackPhaseTester test = new AttackPhaseTester();
		JFrame.setDefaultLookAndFeelDecorated(true);
	}//end main
	
	public AttackPhaseTester(){
		pane = new JPanel(new GridLayout(3, 1));
		stats = new JPanel(new GridLayout(5, 2));
		b = new JPanel(new FlowLayout());
		
		g.changeAll(TerrainType.GRASS);
		g.changeSpace(0, 0, TerrainType.MTNS);
		
		start = new JButton("Attack");
		start.addActionListener(new AttackButtonListener());
		reset = new JButton("Reset");
		reset.addActionListener(new ResetButtonListener());
		
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
		
		l = new JScrollPane(log);
		
		pane.add(stats);
		pane.add(b);
		pane.add(l);
		
		container = new JFrame("Attack Phase Tester - Brian Clanton 10/30/09");
		container.setContentPane(pane);
		container.setVisible(true);
		container.setDefaultCloseOperation(EXIT_ON_CLOSE);
		container.setSize(500, 700);
	}
	
	private void initialize(){
		one = new Character(CharacterType.MYR, 0, 0);
		two = new Character(CharacterType.FGT, 0, 1);
		one.activeWeapon = new Weapon("Iron Sword", WeaponType.SWORD, 5, 90, 0, 5, 45, 1);
		two.activeWeapon = new Weapon("Iron Axe", WeaponType.AXE, 8, 75, 0, 10, 45, 1);
		
		start.setEnabled(true);
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
