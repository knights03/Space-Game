package mining;

import game.Equippable;
import game.EquippableType;
import game.Item;
import game.ItemType;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MiningTool extends Equippable {

	private double power;
	private double range;
	private Color beamColor;

	public MiningTool(String name, int weight, double power, double range, Color beamColor) {
		
		super(name, weight);
		this.power = power;
		this.range = range;
		this.beamColor = beamColor;
	}
	
	@Override
	public EquippableType getEquippableType() {
		return EquippableType.MININGTOOL;
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


	public Color getBeamColor() {
		return beamColor;
	}


}
