package faction;

import util.GlobalVars;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Faction {
	
	private String name;
	private int funds;
	
	private Color primaryColor, secondaryColor;
	
	public Faction(String name, Color primary, Color secondary) {
		this.name = name;
		this.primaryColor = primary;
		this.secondaryColor = secondary;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the funds
	 */
	public int getFunds() {
		return funds;
	}

	/**
	 * @return the primaryColor
	 */
	public Color getPrimaryColor() {
		return primaryColor;
	}

	/**
	 * @return the secondaryColor
	 */
	public Color getSecondaryColor() {
		return secondaryColor;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param funds the funds to set
	 */
	public void setFunds(int funds) {
		this.funds = funds;
	}

	/**
	 * @param primaryColor the primaryColor to set
	 */
	public void setPrimaryColor(Color primaryColor) {
		this.primaryColor = primaryColor;
	}

	/**
	 * @param secondaryColor the secondaryColor to set
	 */
	public void setSecondaryColor(Color secondaryColor) {
		this.secondaryColor = secondaryColor;
	}
	
}
