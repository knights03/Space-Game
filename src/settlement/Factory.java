package settlement;

import java.util.ArrayList;

import game.Game;

public class Factory extends Building {
	
	private int employees;
	
	private ArrayList<Machinery> machinery;

	public Factory(String name, Game game) {
		super(name, BuildingType.FACTORY, game);
	}
	
	

}
