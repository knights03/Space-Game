package cargo;

import game.Item;
import game.Sellable;

public abstract class Cargo implements Item {
	private int amount;
	
	public Cargo() {
		this.amount = 0;
	}
	
	public Cargo(int amount) {
		this.amount = amount;
	}
	
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void add(int addAmount) {
		this.amount += addAmount;
	}
	

}
