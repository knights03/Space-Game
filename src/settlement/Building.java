package settlement;

import game.Game;

public abstract class Building {
	
	private String name;
	private BuildingType buildingType;
		
	private Settlement settlement;
	
	private int level;
	
	public Building(String name, BuildingType buildingType, Settlement settlement) {
		this.name = name;
		this.buildingType = buildingType;
		this.settlement = settlement;
	}
	
	public Settlement getSettlement() {
		return settlement;
	}

}
