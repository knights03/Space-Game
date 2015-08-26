package ship;

import java.util.ArrayList;

import cargo.NotEnoughSpace;
import game.Item;
import game.ItemType;

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
	
	private ArrayList<Item> equippedItems = new ArrayList<Item>();
	private ArrayList<Item> cargo = new ArrayList<Item>();
	
	
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
		for(Item item : equippedItems) {
			if(item.getItemType() == ItemType.ARMOR) {
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
		for(Item item : equippedItems) {
			if(item.getItemType() == ItemType.ENGINE) {
				speed *= ((Engine) item).getSpeedMultiplier();
			}
		}
		
		return speed;
	}
	
	public int getItemSlots() {
		return shipClass.getItemSlots();
	}
	
	/**
	 * Loads an item into the ships cargo bay. Compares the amount of items in the cargo bay to the ship classes cargo space, if there is 
	 * enough room adds the item to the cargo bay. If not enough space throws NotEnoughSpace
	 * @param item The item to be added to the cargo bay
	 * @throws NotEnoughSpace 
	 */
	public void loadCargo(Item item) throws NotEnoughSpace {
		if(cargo.size() < shipClass.getCargoSpace()) 
			cargo.add(item);
		else 
			throw new NotEnoughSpace("Not enough cargo space");
		
	}
	
	/**
	 * Equips an item. Compares the amount of items currently equipped to the ship classes item slots, if there is enough room adds
	 * the item to the ships equipped item list. If not enough slots throws NotEnoughSpace
	 * @param item The item to be equipped
	 * @throws NotEnoughSpace
	 */
	public void equipItem(Item item) throws NotEnoughSpace {
		if(equippedItems.size() < shipClass.getItemSlots()) 
			equippedItems.add(item);
		else
			throw new NotEnoughSpace("Too many equipped items");
		
	
	}
	
	
}
