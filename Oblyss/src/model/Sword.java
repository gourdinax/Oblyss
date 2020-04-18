package model;

public class Sword extends Weapon {
	
	public Sword () {
		super("epee1");
	}
	
	public void setResistance (Ressources r) {
		if(r.getId()=="wood1") {
			
		}
	}
	public void setDamage (Ressources r) {
		if (r.getId().equals("wood1")) {
			this.setDamage(1);
		}
	}
	

}
