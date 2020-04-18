package entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Personnage {

	private String id;
	private double hp;
	private double damage;
	private int speed;
	private DoubleProperty posx;
	private DoubleProperty posy;
	private Image image;
	private ImageView imageView;

	public Personnage(String id, double hp, double damage, int speed, double posx, double posy) {
		this.id = id;
		this.hp = hp;
		this.damage = damage;
		this.speed = speed;
		this.posx = new SimpleDoubleProperty(posx);
		this.posy = new SimpleDoubleProperty(posy);
		this.image = new Image("/image/" + id + ".png");
		this.imageView = new ImageView(new Image("/image/" + id + ".png"));

	}

	public void dealDamage(Personnage p) {
		p.setHp(p.getHp() - this.damage);
	}

	public double getHp() {
		return this.hp;
	}

	public double getDamage() {
		return this.damage;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public DoubleProperty getPosxP() {
		return this.posx;
	}

	public DoubleProperty getPosyP() {
		return this.posy;
	}

	public void setPosx(double x) {
		this.posx.set(x);
	}
	
	public void setPosy(double y) {
		this.posy.set(y);
	}

	public int getSpeed() {
		return this.speed;
	}

	public String getId() {
		return this.id;
	}

	public Image getImage() {
		return this.image;
	}

	public ImageView getImageView() {
		return this.imageView;
	}
}
