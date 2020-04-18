package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class map {

	private ObservableList<Tile> tileMap;
	private int[] dimensions;

	public map(String lien) throws IOException {
		this.tileMap = readMap(lien);
		this.dimensions = dimensionMap(lien);
	}

	public static ObservableList<Tile> readMap(String lien) throws IOException {
		int x = 0;
		int y = 0;
		ObservableList<Tile> map = FXCollections.observableArrayList();
		try (BufferedReader br = new BufferedReader(new FileReader(lien))) {
			String line;
			while ((line = br.readLine()) != null) {
				// line.readLine est un tableau
				for (int i = 0; i < line.split(",").length; i++) {
					// on lit chaque String du tableau séparé par une virgule
					switch (Integer.parseInt(line.split(",")[i])) {
					case 1:
						map.add(new Sky(x * 32, y * 32));
						break;
					case 2:
						map.add(new Water(x * 32, y * 32));
						break;
					case 3:
						map.add(new Lava(x * 32, y * 32));
						break;
					case 4:
						map.add(new Grass(x * 32, y * 32));

						break;
					case 5:
						map.add(new Stone(x * 32, y * 32));

						break;
					case 6:
						map.add(new Sand(x * 32, y * 32));

						break;
					case 7:
						map.add(new Hardrock(x * 32, y * 32));

						break;
					case 8:
						map.add(new Glass(x * 32, y * 32));

						break;
					case 9:
						map.add(new Dirt(x * 32, y * 32));

						break;
					case 10:
						map.add(new Cobble(x * 32, y * 32));

						break;
					case 11:
						map.add(new Oak(x * 32, y * 32));
						break;
					case 12:
						map.add(new Leaves(x * 32, y * 32));
						break;
					case 13:
						map.add(new Cobblestair(x * 32, y * 32));
						break;
					case 14:
						map.add(new Woodenstair(x * 32, y * 32));
						break;
					case 15:
						map.add(new Furnace(x * 32, y * 32));
						break;
					case 16:
						map.add(new Diamondore(x * 32, y * 32));
						break;
					case 17:
						map.add(new Coalore(x * 32, y * 32));
						break;
					case 18:
						map.add(new Goldore(x * 32, y * 32));
						break;
					case 19:
						map.add(new Ironore(x * 32, y * 32));
						break;
					case 20:
						map.add(new Woodenplank(x * 32, y * 32));
						break;
					case 21:
						map.add(new Chest(x * 32, y * 32));
						break;
					case 22:
						map.add(new Craftingtable(x * 32, y * 32));
						break;
					case 23:
						map.add(new Stone(x * 32, y * 32));
						break;
					}
					x++;
				}
				x = 0;
				y++;
			}
		}
		return map;
	}

	public static int[] dimensionMap(String lien) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(lien))) {
			String line;
			int rowNumber = 0;
			int columnNumber = 0;
			while ((line = br.readLine()) != null) {
				columnNumber = line.split(",").length;
				rowNumber++;
			}
			int[] dim = { rowNumber, columnNumber };
			return dim;
		}
	}

	public Tile getTile(int x, int y) {
		int i = 0;
		while (tileMap.size() > i && (tileMap.get(i).getX()!= x) || (tileMap.get(i).getY()!= y)) {
			i++;

		}
		if (tileMap.size() != i) {

			return tileMap.get(i);
		}
		return null;
	}

	public int getTileInArrayI(int x, int y) {
		int i = -1;
		for (Tile t : tileMap) {
			i++;
			if (tileMap.get(i).getX() == x && tileMap.get(i).getY() == y) {
				return i;
			}
		}
		return -1;
	}

	public ObservableList<Tile> getMap() {
		return this.tileMap;
	}

	public int[] getDimensions() {
		return dimensions;
	}

}