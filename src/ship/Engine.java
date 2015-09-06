package ship;

import game.Item;
import game.ItemType;

public class Engine extends Item {
	
	private int speedMultiplier;
	
	public Engine(String name, double weight, int speedMultiplier) {
		super(name, weight, ItemType.ENGINE);
		this.speedMultiplier = speedMultiplier;
	}
	
	public int getSpeedMultiplier() {
		return speedMultiplier;
	}

}
