package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {

	private String name;
	private boolean traversable;
	private int id;
	private int x;
	private int y;
	private ImageView imV;
	private Image im;

	
	public Tile(String name, int id, boolean traversable,int  x, int y) {
		this.name = name;
		this.traversable = traversable;
		this.x=x;
		this.y=y;
		this.id = id;
		this.imV = new ImageView(new Image("/image/"+name+".png"));
		this.im = new Image("/image/"+name+".png");
	}

	public boolean setTraversable() {
		return true;
	}

	public int getId() {
		return this.id;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public Image getImage() {
		return im;
	}
	
	public ImageView getImageView() {
		return imV;
	}
	
	public String getName() {
		 return this.name;
	}
	 
	 public boolean getTraversable() {
		 return this.traversable;
	 }
	 
	 public void setImage(String name) {
			this.imV = new ImageView(new Image("/image/"+name+".png"));
	 }
}
