package util;

import java.util.ArrayList;

import game.Game;
import game.World;
import location.Location;
import location.Outpost;
import location.Planet;
import location.Station;

public class LocationGenerator {
	
	public static LocationGenerator instance = new LocationGenerator();
	
	public enum LocationEnum {
		PLANET, STATION, OUTPOST;
	}
	
	public Location newLocation(LocationEnum locationType, Game game, World world) {
		switch(locationType) {
		case PLANET:
			return new Planet(game, world);
		case STATION:
			return new Station(world);
		case OUTPOST:
			return new Outpost(world);
		default:
			return new Planet(game, world);
		}
	}
	
	public ArrayList<Location> locationCluster(double x1, double y1, double x2, double y2,
			int planetNum, int stationNum, int outpostNum, Game game, World world) {
		
		ArrayList<Location> newLocationCluster = new ArrayList<>();
		Location newLocation;
		
		for(int i = 0; i < planetNum; i++) {
			newLocation = new Planet(game, world);
			newLocation.setX(RandomGenerator.instance.getDouble(x2, x1, 12));
			newLocation.setY(RandomGenerator.instance.getDouble(y2, y1, 12));
			
			newLocationCluster.add(newLocation);
		}
		
		for(int i = 0; i < stationNum; i++) {
			newLocation = new Station(world);
			newLocation.setX(RandomGenerator.instance.getDouble(x2, x1, 12));
			newLocation.setY(RandomGenerator.instance.getDouble(y2, y1, 12));
			
			newLocationCluster.add(newLocation);
		}
		
		for(int i = 0; i < outpostNum; i++) {
			newLocation = new Outpost(world);
			newLocation.setX(RandomGenerator.instance.getDouble(x2, x1, 12));
			newLocation.setY(RandomGenerator.instance.getDouble(y2, y1, 12));
			
			newLocationCluster.add(newLocation);
		}
		
		return newLocationCluster;
		
	}

}
