package location;

import unit.Sprite;
import javafx.scene.Node;

public abstract class Location implements Sprite{
	private double x;
	private double y;
	
	private boolean discovered;
	
	private LocationType locationType;
	
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}
	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * @return the locationType
	 */
	public LocationType getLocationType() {
		return locationType;
	}
	/**
	 * @param locationType the locationType to set
	 */
	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}
	/**
	 * @return the discovered
	 */
	public boolean isDiscovered() {
		return discovered;
	}
	/**
	 * @param discovered the discovered to set
	 */
	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}
	public Location() {
		
		//Everywhere is discovered for testing sake
		//discovered = false;
		discovered = true;
	}
	
	@Override
	public String toString() {
		return String.format("x: %.1f y: %.1f", x, y);
	}
	
}
