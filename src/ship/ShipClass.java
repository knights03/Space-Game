package ship;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShipClass {
	
	/*
	 * Stats:
	 * -ClassName
	 * -Armor
	 * -Speed
	 * -ItemSlots
	 * -AvgCost
	 * -
	 */
	
	private ShipClasses.Classes name;
	
	private ImageView sprite;
	
	private int armor, speed, itemSlots, avgCost, fuelCapacity, torpedoCapacity, cargoLimit;
	
	public ShipClass(ShipClasses.Classes name, int armor, int speed, int itemSlots, int avgCost, int fuelCapacity,
			int torpedoCapacity, int cargoLimit) {
		
		this.name = name;

		this.armor = armor;
		this.speed = speed;
		this.itemSlots = itemSlots;
		this.fuelCapacity = fuelCapacity;
		this.torpedoCapacity = torpedoCapacity;
		this.cargoLimit = cargoLimit;
		
		sprite = ShipFactory.instance.newShip(name);
		
		
	}
	
	public ShipClasses.Classes getName() {
		return name;
	}

	public int getArmor() {
		return armor;
	}

	public int getSpeed() {
		return speed;
	}

	public int getItemSlots() {
		return itemSlots;
	}

	public int getAvgCost() {
		return avgCost;
	}

	public int getFuelCapacity() {
		return fuelCapacity;
	}

	public int getTorpedoCapacity() {
		return torpedoCapacity;
	}

	public int getCargoLimit() {
		return cargoLimit;
	}
	
	public ImageView getSprite() {
		return sprite;
	}

	public void setSprite(ImageView sprite) {
		this.sprite = sprite;
	}

}
