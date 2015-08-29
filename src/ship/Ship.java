package ship;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import cargo.Cargo;
import cargo.CargoType;
import cargo.NotEnoughSpace;
import game.Equippable;
import game.EquippableType;
import game.Item;
import game.ItemType;
import mining.MiningTool;
import weapon.LaserBlaster;

public class Ship {
	
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
	private ShipClass shipClass;
	
	private int torpedos;
	private double fuel;
	
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Equippable> equippedItems = new ArrayList<Equippable>();
	private ArrayList<Cargo> cargo = new ArrayList<Cargo>();
	
	private int cargoSpaceRemaining;
	
	public Ship(ShipClass shipClass) {
		this.shipClass = shipClass;
		fuel = shipClass.getFuelCapacity();
		cargoSpaceRemaining = shipClass.getCargoLimit();
	}
	
	
	public String getName() {
		return name;
	}
	
	public ShipClass getShipClass() {
		return shipClass;
	}
	
	/**
	 * Returns the ships armor. Gets the initial armor of the barebones ship class then cycles through the equipped items, checks if
	 * any of them are Power Armor and if Power Armor is in fact equipped gets the Armor's rating and adds the normal
	 * armor by the multiplier
	 * @return the calculated ship's armor rating
	 */
	public int getArmor() {
		int armor = shipClass.getArmor();
		
		// Check for Power Armor in equiped slots
		for(Equippable item : equippedItems) {
			if(item.getEquippableType() == EquippableType.ARMOR) {
				armor =+ ((PowerArmor) item).getArmorRating();
			}
		}
		
		return armor;
	}
	
	/**
	 * Returns the ships speed. Gets the initial speed of the barebones ship class then cycles through the equipped items, checks if
	 * any of them are Engines and if Engine is in fact equipped gets the Engine's speed multiplier and multiplies the normal
	 * speed by the multiplier
	 * @return the calculated ship's speed
	 */
	public int getSpeed() {
		int speed = shipClass.getSpeed();
		
		// Check for equiped engine
		for(Equippable item : equippedItems) {
			if(item.getEquippableType() == EquippableType.ENGINE) {
				speed *= ((Engine) item).getSpeedMultiplier();
			}
		}
		
		return speed;
	}
	
	public int getItemSlots() {
		return shipClass.getItemSlots();
	}
	
	/**
	 * Returns the equipped MiningTool, if one is equipped. Cycles through the equipped items list and returns an equipped mining tool. If
	 * none is found returns null
	 * @return The equipped mining tool, or null if none is equipped
	 */
	public MiningTool equippedMiningTool() {
		// Cycle through equipped item, looking for a mining tool, return it if found
		for(Equippable item : equippedItems) {
			if(item.getEquippableType() == EquippableType.MININGTOOL) 
				return (MiningTool) item;
		}
		
		// No mining tool found, return null
		return null;
	}
	
	/**
	 * Returns the equipped LaserBlaster, if one is equipped. Cycles through the equipped items list and returns an equipped laser blaster. If
	 * none is found returns null
	 * @return The equipped laser blaster, or null if none is equipped
	 */
	public LaserBlaster equippedLaserBlaster() {
		// Cycle through equipped item, looking for a laser blaster, return it if found
		for(Equippable item : equippedItems) {
			if(item.getEquippableType() == EquippableType.LASERBLASTER) 
				return (LaserBlaster) item;
		}

		// No laser blaster found, return null
		return null;

	}

	/**
	 * Calculates remaining cargo space. Loops through all items, adds up their combined weight, then subtracts it from
	 * ShipClass cargo limit
	 * @return The ship's remaining cargo space
	 */
	private int calculateRemainingCargoSpace() {
		int remainingSpace = shipClass.getCargoLimit();
		
		for(Item item : items) {
			remainingSpace -= item.getWeight();
		}
		
		cargoSpaceRemaining = remainingSpace;
		
		return remainingSpace;
	}
	
	/**
	 * Collects all cargo objects in the ships cargo bay of the same type and combines them into one, stacked object.
	 * Starts by creating a hashtable of all possible item types at zero then loops through all items. Every item has its
	 * amount added to its respective variable, then 
	 */
	private void organizeCargo() {
		Hashtable<CargoType, Integer> cargoAmounts = new Hashtable<CargoType, Integer>();
		
		// Initialize hashmap with all CargoType amounts set to zero
		for(CargoType type : CargoType.values()) {
			cargoAmounts.put(type, 0);
		}
		
		// Loop through each cargo item. For each item loop through each CargoType to see if it matches with that Cargo
		// item. If it does increment the hashmap index
		for(Cargo item : cargo) { 
			for(CargoType type : CargoType.values()) {
				if(item.getCargoType() == type) {
					int currentAmount = cargoAmounts.get(type);
					cargoAmounts.put(type, currentAmount + item.getAmount());
				}
			}
		}
		
		// Reset the Cargo list field
		cargo = new ArrayList<Cargo>();
		
		// Loop through each CargoType
		for(CargoType type : CargoType.values()) {
			// If the CargoType's entry in the hashmap is greater than 0, add a new
			// Cargo object to the cargo list field
			if(cargoAmounts.get(type) > 0) {
				cargo.add(Cargo.newCargo(type, cargoAmounts.get(type)));
			}
		}
	
	}

	private void organizeItemsCargo() {
		Hashtable<CargoType, Integer> cargoAmounts = new Hashtable<CargoType, Integer>();

		// Initialize hashmap with all CargoType amounts set to zero
		for(CargoType type : CargoType.values()) {
			cargoAmounts.put(type, 0);
		}

		// Loop through each cargo item. For each item loop through each CargoType to see if it matches with that Cargo
		// item. If it does increment the hashmap index
		for(Item item : items) { 
			if(item.getItemType() == ItemType.CARGO) {
				for(CargoType type : CargoType.values()) {
					if(((Cargo) item).getCargoType() == type) {
						int currentAmount = cargoAmounts.get(type);
						cargoAmounts.put(type, currentAmount + ((Cargo) item).getAmount());
					}
				}
			}
		}

		// Reset the Cargo list field
		cargo = new ArrayList<Cargo>();

		// Loop through each CargoType
		for(CargoType type : CargoType.values()) {
			// If the CargoType's entry in the hashmap is greater than 0, add a new
			// Cargo object to the cargo list field
			if(cargoAmounts.get(type) > 0) {
				cargo.add(Cargo.newCargo(type, cargoAmounts.get(type)));
			}
		}

	}
	
	
	/**
	 * Loads an item into the ships cargo bay. Compares the calculated remaining cargo space using
	 * calculateRemainingCargoSpace() to the ship classes cargo space, if there is 
	 * enough room adds the item to the cargo bay. If not enough space throws NotEnoughSpace
	 * @param item The item to be added to the cargo bay
	 */
	public void loadItem(Item item) {
		if(calculateRemainingCargoSpace() >= item.getWeight()) {
			items.add(item);
			if(item.getItemType() == ItemType.CARGO)
				cargo.add((Cargo) item);
			
			organizeCargo();
		}
		else {
			if(calculateRemainingCargoSpace() > 0 && item.getItemType() == ItemType.CARGO)
				cargo.add(Cargo.newCargo(((Cargo) item).getCargoType(), calculateRemainingCargoSpace()));
			
			organizeCargo();
		}
		
		for(Item itemPrint : cargo) {
			System.out.println(itemPrint);
		}
		
	}
	
	/**
	 * Equips an item. Compares the amount of items currently equipped to the ship classes item slots, if there is enough room adds
	 * the item to the ships equipped item list. If not enough slots throws NotEnoughSpace
	 * @param item The item to be equipped
	 * @throws NotEnoughSpace
	 */
	public void equipItem(Equippable item) throws NotEnoughSpace {
		if(equippedItems.size() < shipClass.getItemSlots()) 
			equippedItems.add(item);
		else
			throw new NotEnoughSpace("Too many equipped items");
		
	
	}


	public ArrayList<Item> getItems() {
		return items;
	}


	public ArrayList<Equippable> getEquippedItems() {
		return equippedItems;
	}


	public ArrayList<Cargo> getCargo() {
		return cargo;
	}


	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}


	public void setEquippedItems(ArrayList<Equippable> equippedItems) {
		this.equippedItems = equippedItems;
	}


	public void setCargo(ArrayList<Cargo> cargo) {
		this.cargo = cargo;
	}
	
	
}
