package settlement;

import java.util.ArrayList;

import faction.Faction;

public class Settlement {
	
	private String name;
	private int population;
	
	private Faction faction;
	
	private ArrayList<Building> buildings;
	
	public Settlement(String name, Faction faction) {
		this.name = name;
		this.faction = faction;
	}

}
