package model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class HotBar {

	private	IntegerProperty[] hotBar;
	
	public HotBar() {
		this.hotBar = new SimpleIntegerProperty[6];
		for(int i=0;i<6;i++) {
			this.hotBar[i]=new SimpleIntegerProperty();
			this.hotBar[i].set(0);
		}
	}
	
	public void increment(int i) {
		this.hotBar[i].set(hotBar[i].get()+1);
	}
	
	public void decrement(int i) {
		this.hotBar[i].set(hotBar[i].get()-1);
	}
	
	public final IntegerProperty[] getHotBar() {
		return  this.hotBar;
	}
	
	public final int getCount(int i) {
		return hotBar[i].get();
	}

}