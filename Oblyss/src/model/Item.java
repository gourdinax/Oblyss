package model;

public abstract class Item {

	private static String name;
	
	public Item(String name) {
		this.name = name;
	}
	
	public static String getName() {
		return name;
	}
	
}
