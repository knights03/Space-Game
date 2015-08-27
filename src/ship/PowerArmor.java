package ship;

import game.Equippable;
import game.EquippableType;
import game.Item;
import game.ItemType;

public class PowerArmor extends Equippable {
	
	private int armorRating;
	
	public PowerArmor(String name, int weight, int armorRating) {
		super(name, weight);
		this.armorRating = armorRating;
	}

	@Override
	public EquippableType getEquippableType() {
		return EquippableType.ARMOR;
	}

	public int getArmorRating() {
		return armorRating;
	}

}
