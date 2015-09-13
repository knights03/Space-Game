package ship;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import cargo.Cargo;
import cargo.CargoType;
import cargo.NotEnoughSpace;
import game.Game;
import game.HigherLevelRequired;
import game.Item;
import game.ItemType;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import mining.MiningTool;
import weapon.LaserBlaster;

public class Ship implements Cargo {
	
	/*
	 * Stats:
	 * -ClassName
	 * -Armor
	 * -Speed
	 * -ItemSlots
	 * -AvgCost
	 * -
	 */

	private Game game;
	
	private String name;
	private ShipClass shipClass;
	private ImageView sprite;
	
	private int torpedos;
	private double fuel;
	
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Item> equippedItems = new ArrayList<Item>();
	
	private Hashtable<CargoType, Double> cargoManifest = new Hashtable<CargoType, Double>();
	
	private int cargoSpaceRemaining;
	
	public Ship(ShipClass shipClass, Game game) {
		this.shipClass = shipClass;
		this.game = game;
		fuel = shipClass.getFuelCapacity();
		cargoSpaceRemaining = shipClass.getCargoLimit();
		
		sprite = ShipFactory.instance.newShip(shipClass.getName());
		
		cargoManifest = game.emptyCargoTable();
		
	}
	
	
	public String getName() {
		return name;
	}
	
	public ImageView getSprite() {
		return sprite;
	}


	public void setSprite(ImageView sprite) {
		this.sprite = sprite;
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
	 * Returns the equipped MiningTool, if one is equipped. Cycles through the equipped items list and returns an equipped mining tool. If
	 * none is found returns null
	 * @return The equipped mining tool, or null if none is equipped
	 */
	public MiningTool equippedMiningTool() {
		// Cycle through equipped item, looking for a mining tool, return it if found
		for(Item item : equippedItems) {
			if(item.getItemType() == ItemType.MININGTOOL) 
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
		for(Item item : equippedItems) {
			if(item.getItemType() == ItemType.LASERBLASTER) 
				return (LaserBlaster) item;
		}

		// No laser blaster found, return null
		return null;

	}


	/**
	 * Equips an item. Compares the amount of items currently equipped to the ship classes item slots, if there is enough room adds
	 * the item to the ships equipped item list. If not enough slots throws NotEnoughSpace
	 * @param item The item to be equipped
	 * @throws NotEnoughSpace
	 */
	public void equipItem(Item item) throws NotEnoughSpace, HigherLevelRequired {
		if(equippedItems.size() < shipClass.getItemSlots()) {
			equippedItems.add(item);
			game.getTextNotifier().addText(String.format("%s successfully equipped", item.getName()));
		} else
			throw new NotEnoughSpace("Too many equipped items");
		
	
	}


	public ArrayList<Item> getItems() {
		return items;
	}


	public ArrayList<Item> getEquippedItems() {
		return equippedItems;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}



	@Override
	public double getCapacity() {
		// TODO Auto-generated method stub
		return shipClass.getCargoLimit();
	}


	@Override
	public Hashtable<CargoType, Double> getCargo() {
		// TODO Auto-generated method stub
		return cargoManifest;
	}


	@Override
	public void unload(CargoType cargoType, double amount) {
		double currentAmount = cargoManifest.get(cargoType);
		
		cargoManifest.put(cargoType, currentAmount - amount);
		
	}
	
	
}
