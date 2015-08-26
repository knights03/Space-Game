package ship;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

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
	
	public ShipClass(String name, int armor, int speed, int itemSlots, int avgCost, int fuelCapacity, int torpedoCapacity, int cargoSpace) {
		this.name = name;
		
		shipStats.put("Armor", armor);
		shipStats.put("Speed", speed);
		shipStats.put("Item Slots", itemSlots);
		shipStats.put("Average Cost", avgCost);
		shipStats.put("Fuel Capacity", fuelCapacity);
		shipStats.put("Max Torpedoes", torpedoCapacity);
		shipStats.put("Cargo Space", cargoSpace);
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
	
	public int getCargoSpace() {
		return shipStats.get("Cargo Space");
	}
	
	public void customStat(String stat, int value) {
		shipStats.put(stat, value);
	}

}
