package settlement;

import game.Game;

public abstract class Building {
	
	private String name;
	private BuildingType buildingType;
	
	private Game game;
	
	private int level;
	
	public Building(String name, BuildingType buildingType, Game game) {
		this.name = name;
		this.buildingType = buildingType;
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}

}
