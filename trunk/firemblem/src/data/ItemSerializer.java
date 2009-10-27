package data;

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

import game.Item;
import data.ItemStatEffect;
import data.ItemType;

public class ItemSerializer {

	ArrayList<Item> cache = new ArrayList<Item>();

	public void load(String fileName) throws IOException {
		File file = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String[] line;
		String temp;
		while((temp = reader.readLine()) != null && (line = temp.split(",")) != null) {
			for(int i = 0; i < line.length; i++)
				line[i] = line[i].trim();
			if(line.length == 4){
				line[2] = line[2].toUpperCase();
				cache.add(Item.createItem(line[0], Integer.parseInt(line[1]), ItemType.valueOf(line[2]), Integer.parseInt(line[3])));
			} else if(line.length ==5) {
				line[2] = line[2].toUpperCase();
				line[3] = line[3].toUpperCase();
				cache.add(Item.createItem(line[0], Integer.parseInt(line[1]), ItemType.valueOf(line[2]), ItemStatEffect.valueOf(line[3]), Integer.parseInt(line[4])));
			} else if(line.length == 3) {
				line[2] = line[2].toUpperCase();
				cache.add(Item.createItem(line[0], Integer.parseInt(line[1]), ItemType.valueOf(line[2])));
			}
		}
	}

	public void write(String path) throws FileNotFoundException, IOException {
		ObjectOutputStream os;
		for(Item i : cache) {
			os = new ObjectOutputStream(new FileOutputStream(((path.charAt(path.length() - 1) == '/') ? path : path + "/") + i.getName() + ".i"));
			os.writeObject(i);
		}

	}

	public void read(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.print("Enter the data file to deserialize: ");
		Scanner  s = new Scanner(System.in);
		String name = s.nextLine();
		path += name;
		ObjectInputStream is =new ObjectInputStream(new FileInputStream(path));
		Object o = is.readObject();
		Item i =  (Item) o;
		System.out.println("Data read successfully: ");
		System.out.println("\n" + i.toString());
	}

}
