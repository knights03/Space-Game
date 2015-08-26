package location;

import java.util.LinkedList;

import unit.Unit;

public class Path {

	private LinkedList<Location> waypoints;
	private boolean loop;
	private boolean active;
	


	public Path() {
		waypoints = new LinkedList<>();
	}
	
	public Path(Location... nodes) {
		this();
		for(Location location : nodes) {
			waypoints.add(location);
		}
	}
	
	public LinkedList<Location> getWaypoints() {
		return waypoints;
	}

	public boolean isLooped() {
		return loop;
	}

	public void setWaypoints(LinkedList<Location> pathNodes) {
		this.waypoints = pathNodes;
	}
	
	public void addWaypoints(Location... nodes) {
		for(Location location : nodes) {
			waypoints.add(location);
		}
	}

	public void setLooped(boolean loop) {
		this.loop = loop;
	}
	
	/*
	public void assignPath(Unit unit) {

		if(loop == true) {
			while(active) {
				unit.setDestination(waypoints.)
			}
		}
	}*/

}
