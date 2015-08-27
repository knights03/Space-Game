package mining;

import cargo.Cargo;
import cargo.CargoType;
import economy.ExchangeRates;
import game.ItemType;

public class Iron extends Cargo {
	
	
	public Iron(int amount) {
		super(CargoType.IRON, amount);
	}

	@Override
	public String getName() {
		return String.format("%dt Unprocessed Iron", getAmount());
	}

	@Override
	public ItemType getItemType() {
		return ItemType.CARGO;
	}

	public int getValue() {
		return (int) Math.round((ExchangeRates.getValue(CargoType.IRON)*getAmount()));
	}
	
	@Override
	public String toString() {
		return String.format("%dt Unprocessed Iron", getAmount());
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return getAmount();
	}
	

}
