package settlement;

import java.util.ArrayList;

import game.Game;
import machinery.Machinery;

public class Factory extends Building {
	
	private int employees;
	
	private int maxMachines;
	
	private Warehouse warehouse;
	
	private ArrayList<Machinery> machinery = new ArrayList<Machinery>();

	public Factory(String name, Settlement settlement, Warehouse warehouse) {
		super(name, BuildingType.FACTORY, settlement);
		this.warehouse = warehouse;
	}
	
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	public void addMachinery(Machinery machine) {
		machinery.add(machine);
		machine.setFactory(this);
	}
	
	

}
