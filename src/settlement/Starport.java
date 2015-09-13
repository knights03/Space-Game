package settlement;

import java.util.ArrayList;

import game.Dockable;
import game.Game;

public class Starport extends Building {
	
	private int docks = 0;
	
	private ArrayList<Dockable> dockedShips = new ArrayList<Dockable>();

	public Starport(String name, Game game) {
		super(name, BuildingType.STARPORT, game);
	}

}
