package machinery;

import cargo.CargoType;

public class MachineCargo {
	
	private CargoType type;
	private double amount;
	
	public MachineCargo(CargoType type, double amount) {
		this.type = type;
		this.amount = amount;
	}
	
	public CargoType getType() {
		return type;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void reduceAmount(double reduction) {
		amount -= reduction;
	}
	
	public boolean depleted() {
		if(amount <= 0) {
			return true;
		} else {
			return false;
		}
	}

}
