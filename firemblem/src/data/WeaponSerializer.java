package data;

import game.Weapon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class WeaponSerializer {

	ArrayList<Weapon> cache = new ArrayList<Weapon>();

	public void load(String fileName) throws IOException {
		File file = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String[] line;
		String temp;
		while((temp = reader.readLine()) != null && (line = temp.split(",")) != null) {
			//System.out.print(Arrays.toString(line));
			for(int i = 0; i < line.length; i++)
				line[i] = line[i].trim();
			if(line.length == 8) {
				line[6] = line[6].toUpperCase();
				cache.add(Weapon.forgeWeapon(line[0], WeaponType.valueOf(line[6]), Float.parseFloat(line[2]),
						Float.parseFloat(line[3]), Integer.parseInt(line[1]), Integer.parseInt(line[4]),
						Integer.parseInt(line[5]), Integer.parseInt(line[7])));
			} else if(line.length == 5) {
				line[3] = line[3].toUpperCase();
				cache.add(Weapon.forgeWeapon(line[0], WeaponType.valueOf(line[3]), -1.0f, -1.0f, Integer.parseInt(line[1]), -1,
						Integer.parseInt(line[2]), Integer.parseInt(line[4])));
			}
		}
	}

	public void write(String path) throws FileNotFoundException, IOException {
		ObjectOutputStream os;
		for(Weapon w : cache) {
			os = new ObjectOutputStream(new FileOutputStream(((path.charAt(path.length() - 1) == '/') ? path : path + "/") + w.getName() + ".w"));
			os.writeObject(w);
		}

	}
	
	public void read(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.print("Enter the data file to deserialize: ");
		Scanner  s = new Scanner(System.in);
		String name = s.nextLine();
		path += name;
		ObjectInputStream is =new ObjectInputStream(new FileInputStream(path));
		Object o = is.readObject();
		Weapon w =  (Weapon) o;
		System.out.println("Data read successfully: ");
		System.out.println("\n" + w.toString());
	}

}
