package mining;

import game.Item;
import game.ItemType;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MiningTool implements Item {

	private ItemType itemType;
	
	private String name;
	
	private double power;
	private double range;
	
	private int cost;

	private Color beamColor;
	
	public MiningTool() {
		
	}
	
	public MiningTool(String name, double power, double range,
			int cost, Color beamColor) {
		
		this.name = name;
		this.power = power;
		this.range = range;
		this.cost = cost;
		this.beamColor = beamColor;
		
		itemType = ItemType.MININGTOOL;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the power
	 */
	public double getPower() {
		return power;
	}

	/**
	 * @return the range
	 */
	public double getRange() {
		return range;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(double power) {
		this.power = power;
	}

	/**
	 * @param range the range to set
	 */
	public void setRange(double range) {
		this.range = range;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	public Color getBeamColor() {
		return beamColor;
	}
	
	@Override
	public String toString() {
		return name;
	}

	/**
	 * @return the itemType
	 */
	public ItemType getItemType() {
		return itemType;
	}
}
