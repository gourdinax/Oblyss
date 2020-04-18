package model;

public abstract class Weapon {
	
	private String id;
	private int resistance;
	private double damage;
	
	public Weapon (String id) {
		this.id=id;
		this.resistance=0;
		this.damage=0;
	}
	
	public void looseResistance(int r) {
		this.resistance=r;
	}
	
	public void setDamage(double d) {
		this.damage=d;
	}
	
	public abstract void setResistance(Ressources r);
	
	public abstract void setDamage(Ressources r);
}
