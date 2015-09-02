package game;

import javafx.scene.paint.Color;
import mining.MiningTool;
import weapon.LaserBlaster;

public class GameItems {

	
	/*
	 * Laser Blasters
	 * 
	 * LaserBlaster defensePulsor = new LaserBlaster(blasterName, blasterWeight, blasterDamage,
	 *		blasterCritical, blasterColor, blasterRange);
	 * 
	 */
	
	static LaserBlaster class1DefensePulsor = new LaserBlaster("Class 1 Defense Pulsor", 1, 700,
			10, Color.LAWNGREEN, 150);
	
	/*
	 * Mining Tools
	 * 
	 * 	MiningTool protonChisel = new MiningTool(miningName, miningWeight, miningPower,
	 *		miningRange, miningBeamColor);
	 * 
	 */

	
	static MiningTool protonChisel = new MiningTool("Proton Chisel", 1, 2,
			50, Color.AQUAMARINE);

}
