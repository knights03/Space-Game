package cargo;

import java.util.Hashtable;

public interface Cargo {

	public Hashtable<CargoType, Double> getCargo();
	public double getCapacity();
	public void unload(CargoType cargoType, double amount);
}
