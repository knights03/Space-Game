package game;

import java.util.ArrayList;

import location.Location;
import location.LocationType;
import mining.AsteroidCluster;
import util.LocationGenerator;

public class World {
	
	private String name;

	private double h, w;
	
	private ArrayList<Location> locations;
	private ArrayList<AsteroidCluster> asteroidClusters;
	
	public World(String name) {
		this.name = name;
		locations = new ArrayList<>();
	}
	
	public World(String name, int h, int w) {
		this(name);
		this.h = h;
		this.w = w;
	}
	
	public World(String name, int h, int w, int planetNum, int stationNum, int outpostNum) {
		this(name, h, w);
	}
	
	public double getH() {
		return h;
	}
	
	public double getW() {
		return w;
	}
	
	public void addLocation(Location location) {
		locations.add(location);
	}
	
	public ArrayList<Location> getLocations() {
		return locations;
	}
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(double h) {
		this.h = h;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(double w) {
		this.w = w;
	}

	/**
	 * @return the asteroidClusters
	 */
	public ArrayList<AsteroidCluster> getAsteroidClusters() {
		return asteroidClusters;
	}

	/**
	 * @param asteroidClusters the asteroidClusters to set
	 */
	public void setAsteroidClusters(ArrayList<AsteroidCluster> asteroidClusters) {
		this.asteroidClusters = asteroidClusters;
	}

	@Override
	public String toString() {
		StringBuilder worldString = new StringBuilder();
		
		worldString.append(name);
		
		worldString.append("\nPlanets");
		for(Location location : locations) {
			if(location.getLocationType() == LocationType.PLANET) {
				worldString.append(String.format("\n%s", location));
			}
		}
		
		worldString.append("\nStations");
		for(Location location : locations) {
			if(location.getLocationType() == LocationType.STATION) {
				worldString.append(String.format("\n%s", location));
			}
		}
		
		worldString.append("\nOutposts");
		for(Location location : locations) {
			if(location.getLocationType() == LocationType.OUTPOST) {
				worldString.append(String.format("\n%s", location));
			}
		}
		
		return worldString.toString();
	}
}
