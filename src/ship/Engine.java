package ship;

import game.Equippable;
import game.EquippableType;
import game.Item;
import game.ItemType;

public class Engine extends Equippable {
	
	private int speedMultiplier;
	
	public Engine(String name, int weight, int speedMultiplier) {
		super(name, weight);
		this.speedMultiplier = speedMultiplier;
	}

	@Override
	public EquippableType getEquippableType() {
		return EquippableType.ENGINE;
	}
	
	public int getSpeedMultiplier() {
		return speedMultiplier;
	}

}
