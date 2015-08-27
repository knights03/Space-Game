package weapon;

import util.RandomGenerator;
import game.Equippable;
import game.EquippableType;
import game.Item;
import game.ItemType;
import javafx.scene.paint.Color;

public class LaserBlaster extends Equippable {

	private double damage;
	private double criticalChance;
	private Color color;
	private double range;

	
	public LaserBlaster(String name, int weight, double damage, double critical, Color color, double range) {
		super(name, weight);
		this.damage = damage;
		this.criticalChance = critical;
		this.color = color;
		this.range = range;
	}
	
	@Override
	public EquippableType getEquippableType() {
		return EquippableType.LASERBLASTER;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public double getCriticalChance() {
		return criticalChance;
	}

	public void setCriticalChance(double criticalChance) {
		this.criticalChance = criticalChance;
	}
	
	
	public double getLaserDamage() {
		// TODO Auto-generated method stub
		
		double critical = RandomGenerator.instance.getDouble(100);
		
		if(critical < criticalChance) {
			critical = damage / 2;
		} else {
			critical = 0;
		}
		
		return damage - RandomGenerator.instance.lowRandomInt((int) (damage * 0.5), 0) + critical;
	}
	
	
}
