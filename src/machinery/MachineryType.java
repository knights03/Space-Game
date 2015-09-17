package machinery;

public enum MachineryType {
	
	/*
	 * Crafting guide:
	 * Unprocessed metal + SMELTER = Processed metal
	 * Processed metal + Processed metal + SMELTER = Metal Alloy
	 * Processed metal + PLATEPRESS = Plate metal
	 * 
	 * Gas + CONDENSINGVAT = Liquid
	 * Liquid + Liquid + CHEMVAT = Compound
	 * 
	 * 
	 * Plate metal + Metal Alloy + Gas = Laser Blaster
	 * Plate metal + Plate metal + Compound = Power Armor
	 * Metal Alloy + 
	 */
	
	SMELTER, // Turns unprocessed metal into processed metal
	ARMORY, // Used to turn certain processed goods into weapons
	PLATEPRESS, //Used to turn processed metal into plate metal, used for armor and lasterblasters
	
	CONDENSINGVAT, // Used for turning gas (collecting in nebulas) into liquid
	CHEMVAT, // Used for mixing liquids into compounds
	
	

}
