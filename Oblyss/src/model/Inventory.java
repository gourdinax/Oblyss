package model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

//	private Item[] hotbar;
	//inventaire
	private ObservableList<Item> invList;
	//fabricables
	private ObservableList<Item> itemList;
//	private Craft craft;
	private	IntegerProperty[] inventory;

	public Inventory() {
		this.invList = FXCollections.observableArrayList();
		this.itemList = FXCollections.observableArrayList(new WoodenPickaxe());
		
		this.inventory = new SimpleIntegerProperty[30];
		for(int i=0;i<30;i++) {
			this.inventory[i]=new SimpleIntegerProperty();
			this.inventory[i].set(0);
		}
	}
	
	public void increment(int i) {
		this.inventory[i].set(inventory[i].get()+1);
	}
	
	public void decrement(int i) {
		this.inventory[i].set(inventory[i].get()-1);
	}
	
	public final IntegerProperty[] getInventory() {
		return  this.inventory;
	}
	public final int getCount(int i) {
		return inventory[i].get();
	}

	public ObservableList<Item> getInvList() {
		return this.invList;
	}
	
	public ObservableList<Item> getItemList() {
		return this.itemList;
	}
	
	public void removeItemFromInv(int[] num, Item[] items) {
		//on fait un try/catch pour que la méthode puisse ne pas s'éxecuter si il manque des ressources
		try {
			//on boucle avec le nombre d'items en paramètre
			for (int i = 0; i < items.length; i++) {
				//on enlève les items de l'inventaire 
				for (int j = 0; j < num[i]; j++) {
					invList.remove(items[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("Il vous manque des ressources...");
		}
	}
	
	public void getItem(String str) {
		switch(str) {
			case "WoodenPickaxe":
				System.out.println("wooden_pickaxe");
				int[] num = {3, 2};
				Item[] items = {invList.get(0),invList.get(3)};
				removeItemFromInv(num, items);
				invList.add(new WoodenPickaxe());
				break;
		}
	}

}
