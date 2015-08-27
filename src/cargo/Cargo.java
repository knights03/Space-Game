package cargo;

import game.Item;
import game.ItemType;
import game.Sellable;
import mining.Iron;

public abstract class Cargo implements Item {
	private String name;
	private int amount;
	private CargoType cargoType;
	
	public static Cargo newCargo(CargoType type, int amount) {
		switch(type) {
		case IRON:
			return new Iron(amount);
		default:
			return null;
		}
	}
	
	
	public Cargo(CargoType cargoType, int amount) {
		this.cargoType = cargoType;
		this.amount = amount;
	}
	
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void add(int addAmount) {
		this.amount += addAmount;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public ItemType getItemType() {
		// TODO Auto-generated method stub
		return ItemType.CARGO;
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public CargoType getCargoType() {
		return cargoType;
	}
}
