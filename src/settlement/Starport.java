package settlement;

import java.util.ArrayList;

import game.Dockable;
import game.Game;

public class Starport extends Building {
	
	private int docks = 1;
	
	private ArrayList<Dockable> dockedShips = new ArrayList<Dockable>();

	public Starport(String name, Game game) {
		super(name, BuildingType.STARPORT, game);
	}
	
	public void dockShip(Dockable ship) {
		dockedShips.add(ship);
	}
	
	public int remainingDocks() {
		return docks - dockedShips.size();
	}

}
