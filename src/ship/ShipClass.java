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
	
	private String name;
	private Hashtable<String, Integer> shipStats = new Hashtable<String, Integer>();
	
	private ImageView sprite;
	
	public ShipClass(String name, int armor, int speed, int itemSlots, int avgCost, int fuelCapacity,
			int torpedoCapacity, int cargoLimit, String spriteImage) {
		
		this.name = name;
		
		shipStats.put("Armor", armor);
		shipStats.put("Speed", speed);
		shipStats.put("Item Slots", itemSlots);
		shipStats.put("Average Cost", avgCost);
		shipStats.put("Fuel Capacity", fuelCapacity);
		shipStats.put("Max Torpedoes", torpedoCapacity);
		shipStats.put("Cargo Limit", cargoLimit);
		
		Image image = new Image(spriteImage);
		
		
		sprite = new ImageView(new Image(spriteImage));
		
		sprite.setLayoutX((image.getWidth()/2)*-1);
		sprite.setLayoutY((image.getHeight()/2)*-1);
		
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getStat(String stat) throws StatNotPresent {
		
		if(shipStats.contains(stat)) {
			return shipStats.get(stat);
		} else {
			throw new StatNotPresent(String.format("Stat '%s' not present in this ship class", stat));
		}
		
	}
	
	public int getArmor() {
		return shipStats.get("Armor");
	}
	
	public int getSpeed() {
		return shipStats.get("Speed");
	}
	
	public int getItemSlots() {
		return shipStats.get("Item Slots");
	}
	
	public int getAvgCost() {
		return shipStats.get("Average Cost");
	}
	
	public int getFuelCapacity() {
		return shipStats.get("Fuel Capacity");
	}
	
	public int getMaxTorpedoes() {
		return shipStats.get("Max Torpedoes");
	}
	
	public int getCargoLimit() {
		return shipStats.get("Cargo Limit");
	}
	
	public ImageView getSprite() {
		return sprite;
	}

	public void setSprite(ImageView sprite) {
		this.sprite = sprite;
	}

	public void customStat(String stat, int value) {
		shipStats.put(stat, value);
	}

}
