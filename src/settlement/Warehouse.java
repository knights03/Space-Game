package settlement;

import java.util.Hashtable;

import cargo.Cargo;
import cargo.CargoType;
import game.Game;
import javafx.scene.paint.Color;
import util.GlobalVars;

public class Warehouse extends Building implements Cargo {
	
	private double maxStorage;
	
	private Hashtable<CargoType, Double> cargo;

	public Warehouse(String name, Game game) {
		super(name, BuildingType.WAREHOUSE, game);
		maxStorage = GlobalVars.WAREHOUSE_LVL1_STORAGE;
		
		cargo = getGame().emptyCargoTable();
	}
	

	@Override
	public double getCapacity() {
		// TODO Auto-generated method stub
		return maxStorage;
	}

	@Override
	public Hashtable<CargoType, Double> getCargo() {
		// TODO Auto-generated method stub
		return cargo;
	}

	@Override
	public void unload(CargoType cargoType, double amount) {
		double currentAmount = cargo.get(cargoType);
		
		cargo.put(cargoType, currentAmount - amount);
		
	}

}
