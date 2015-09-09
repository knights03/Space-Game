package ship;

import java.util.ArrayList;

import util.RandomGenerator;

public class ShipClasses {
	/*
	 * Ship classes needed
	 * 
	 * Immense battleship (GBC Hive ships) - Globox
	 * 
	 * Regular battleships (Good all around, very very slow) - Terror
	 * Attack destroyer (medium all around) - 
	 * Missile cruiser (slow, high torpedo, high armor)
	 * Vanguard frigate (fast, high armor, low torpedo, low cargo)
	 * Pursuit fighter (very fast, low armor, no torpedos or cargo)
	 * Frontline fighter (fast, low armor, low torpedos, low cargo, cheap)
	 * 
	 * Fuel tanker (very slow, lots of armor, tons of fuel)
	 * Barge (very slow, lots of armor, tons of cargo)
	 * Freighter (slow, lots of armor, medium item slots)
	 * Skidsteer (very fast, no armor, low cost, one item slot, medium cargo)
	 * Engineering Frigate (medium speed, lots of item slots, low armor, medium cargo)
	 * 
	 */
	
	public enum Classes {
		AKIRA, EXCELSIOR, INTREPID,
		
		PLACEHOLDER,
		
		IMMENSEBATTLESHIP, REGULARBATTLESHIP, ATTACKDESTROYER, MISSILECRUISER, VANGUARDFRIGATE,
		PURSUITFIGHTER, FRONTLINEFIGHTER,
		
		FUELTANKER, BARGE, FREIGHTER, SKIDSTEER, ENGINEERINGFRIGATE
	}
	
	
	public static ArrayList<ShipClass> classList = new ArrayList<ShipClass>();
	
	//name, armor, speed, itemslots, cost, fuel, torpedos, cargo
	public static ShipClass akira = new ShipClass(Classes.AKIRA, 10, 300, 2, 150, 500, 3, 70);
	public static ShipClass excelsior = new ShipClass(Classes.EXCELSIOR, 300, 75, 4, 10000, 4000, 25, 1200);
	public static ShipClass intrepid = new ShipClass(Classes.INTREPID, 70, 220, 2, 500, 600, 10, 200);
	
	public static ShipClass immenseBattleship = new ShipClass(Classes.IMMENSEBATTLESHIP, 70000, 10, 20, 300000000, 10000, 125, 5000);
	public static ShipClass regularBattleship = new ShipClass(Classes.REGULARBATTLESHIP, 7500, 40, 12, 65000000, 4000, 80, 2750);
	public static ShipClass attackDestroyer = new ShipClass(Classes.ATTACKDESTROYER, 5000, 60, 5, 14000000, 3000, 35, 1500);
	public static ShipClass missileCruiser = new ShipClass(Classes.MISSILECRUISER, 8000, 50, 4, 12000000, 3000, 140, 300);
	public static ShipClass vanguardFrigate = new ShipClass(Classes.VANGUARDFRIGATE, 6000, 300, 4, 1200000, 3000, 12, 25);
	public static ShipClass pursuitFighter = new ShipClass(Classes.PURSUITFIGHTER, 150, 400, 1, 3000, 7000, 1, 10);
	public static ShipClass frontlineFighter = new ShipClass(Classes.FRONTLINEFIGHTER, 400, 300, 2, 5000, 2000, 3, 15);
	
	public static ShipClass fuelTanker = new ShipClass(Classes.FUELTANKER, 9000, 15, 10, 12000000, 50000, 20, 400);
	public static ShipClass barge = new ShipClass(Classes.BARGE, 8500, 20, 7, 3000000, 7000, 12, 120000);
	public static ShipClass freighter = new ShipClass(Classes.FREIGHTER, 4000, 45, 12, 790000, 6000, 10, 7000);
	public static ShipClass skidsteer = new ShipClass(Classes.SKIDSTEER, 55, 500, 1, 1200, 1000, 0, 500);
	public static ShipClass engineeringFrigate = new ShipClass(Classes.ENGINEERINGFRIGATE, 400, 225, 20, 12000, 3000, 1, 450);
	
	
	public ShipClasses() {
		classList.add(akira);
		classList.add(excelsior);
		classList.add(intrepid);
	}
	
	public static ShipClass randomShipClass() {
		return classList.get(RandomGenerator.instance.getInt(classList.size()));
	}

}
