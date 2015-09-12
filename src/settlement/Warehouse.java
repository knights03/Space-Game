package settlement;

import java.util.Hashtable;

import cargo.CargoType;
import game.Game;
import javafx.scene.paint.Color;
import util.GlobalVars;

public class Warehouse extends Building {
	
	private double maxStorage;
	private int employees;
	
	private Hashtable<CargoType, Double> cargo;

	public Warehouse(String name, Game game) {
		super(name, BuildingType.WAREHOUSE, game);
		maxStorage = GlobalVars.WAREHOUSE_LVL1_STORAGE;
		
		cargo = getGame().emptyCargoTable();
	}
	
	public void unloadCargo(CargoType cargoType, double amount) {
		double cargoSpaceRemaining = maxStorage;
		
		for(CargoType type : CargoType.values()) {
			cargoSpaceRemaining -= cargo.get(type);
			System.out.printf("%s: %.1f", type, cargo.get(type));
		}
		
		double currentAmount = cargo.get(cargoType);
		
		if(cargoSpaceRemaining >= amount) {
			cargo.put(cargoType, currentAmount + amount);
			getGame().getTextNotifier().addText(String.format("%.1ft of %s unloaded", amount, cargoType));
		} else if(cargoSpaceRemaining > 0 && cargoSpaceRemaining < amount) {
			cargo.put(cargoType, currentAmount + cargoSpaceRemaining);
			getGame().getTextNotifier().addText(String.format("Only %.1ft of %s could be unloaded", cargoSpaceRemaining, cargoType), Color.ORANGE);
		} else if(cargoSpaceRemaining <= 0) {
			getGame().getTextNotifier().addText("Not enough space in warehouse", Color.RED);
		}
		
	}
	
	public void retrieveCargo(CargoType cargoType, double amount) {
		
		double amountRetrieved = getGame().getPlayer().getShip().loadCargo(cargoType, amount);
		
		double previousAmount = cargo.get(cargoType);
		
		cargo.put(cargoType, previousAmount - amountRetrieved);
	}

}
