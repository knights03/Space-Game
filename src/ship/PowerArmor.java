package ship;

import game.Item;
import game.ItemType;

public class PowerArmor extends Item {
	
	private int armorRating;
	
	public PowerArmor(String name, double weight, int armorRating) {
		super(name, weight, ItemType.ARMOR);
		this.armorRating = armorRating;
	}

	public int getArmorRating() {
		return armorRating;
	}

}
