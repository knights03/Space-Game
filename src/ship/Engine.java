package ship;

import game.Item;
import game.ItemType;

public class Engine implements Item {
	
	private String name;
	private int cost;
	private int speedMultiplier;

	
	public int getSpeedMultiplier() {
		return speedMultiplier;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public ItemType getItemType() {
		// TODO Auto-generated method stub
		return ItemType.ENGINE;
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

}
