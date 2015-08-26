package weapon;

import util.RandomGenerator;
import game.Item;
import game.ItemType;
import javafx.scene.paint.Color;

public class LaserBlaster implements Item {

	private ItemType itemType;
	
	private String name;
	private double damage;
	private double criticalChance;
	private Color color;
	private double range;
	private int cost;
	
	private double heatRate;
	private double coolRate;
	
	public LaserBlaster(String name, double damage, double critical, Color color, double range,
			int cost, double heatRate, double coolRate) {
		this.name = name;
		this.damage = damage;
		this.criticalChance = critical;
		this.color = color;
		this.range = range;
		this.cost = cost;
		this.heatRate = heatRate;
		this.coolRate = coolRate;
		
		itemType = ItemType.LASERBLASTER;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public double getHeatRate() {
		return heatRate;
	}

	public void setHeatRate(double heatRate) {
		this.heatRate = heatRate;
	}

	public double getCoolRate() {
		return coolRate;
	}

	public void setCoolRate(double coolRate) {
		this.coolRate = coolRate;
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

	/**
	 * @return the itemType
	 */
	public ItemType getItemType() {
		return itemType;
	}
	
	
}
