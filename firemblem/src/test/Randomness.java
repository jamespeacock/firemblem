package test;

import game.Character;
import data.CharacterType;

public class Randomness
{
	public static void main(String []args)
	{
		Character c = new Character();
		c.type = CharacterType.MYR;
		c.getGrowthRates();
		for(int i : c.growthRates)
			System.out.println(i);
	}
}