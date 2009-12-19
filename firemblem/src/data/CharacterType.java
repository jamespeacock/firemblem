package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

/*
 * Brian Clanton 10/24/09
 */

public enum CharacterType {
    MYR("Myrmidon"),
    LORD("Lord"),
    KNG("Knight"),
    FGT("Fighter"),
    ARCH("Archer"),
    MAGE("Mage"),
    CLR("Cleric"),
    RCRT("Recruit"),
    CVLR("Cavalier"),
    BRSKR("Beserker"),
    PLDN("Paladin"),
    GNRL("General"),
    MCNRY("Mercenary"),
    PKNG("Pegasus Knight"),
    JNYMN("Journeyman"),
    PUPIL("Pupil"),
    HERO("Hero"),
    TRBDR("Troubador"),
    PRT("Pirate"),
    FKNG("Falcoknight"),
    THF("Thief");

    public HashSet<String> usableWeapons;
    public String name;

    private static String[] textFile;
    private static boolean textFileRead = false;

    private CharacterType(String a) {
	name = a;
	usableWeapons = new HashSet<String>();
	String[] f = read();
	String line;
	for (int i = 0; i < f.length; i++) {
	    line = f[i];
	    if (line.contains(name) || line.contains("all"))
		usableWeapons.add(line.substring(0, line.indexOf(':')));
	}// end for
    }// end constructor

    private static String[] read() {
	if (!textFileRead) {
	    try {
		textFile = new String[10];
		Scanner reader = new Scanner(new File(
			"src/data/usableweapons.txt"));
		for (int i = 0; i < 10; i++)
		    textFile[i] = reader.nextLine().trim();
		textFileRead = true;
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    }
	}// end if
	return textFile;
    }// end read

    public String toString() {
	return name;
    }
}
