package ship;

import game.Item;
import game.ItemType;

public class PowerArmor implements Item {
	
	private String name;
	private int cost;
	
	private int armorRating;
	
	public PowerArmor(String name, int armorRating, int cost) {
		this.name = name;
		this.armorRating = armorRating;
		this.cost = cost;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ItemType getItemType() {
		return ItemType.ARMOR;
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return cost;
	}
	
	public int getArmorRating() {
		return armorRating;
	}

}
