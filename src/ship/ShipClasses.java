package ship;

public class ShipClasses {
	
	public enum Classes {
		AKIRA, EXCELSIOR, INTREPID
	}
	
	//name, armor, speed, itemslots, cost, fuel, torpedos, cargo
	public static ShipClass akira = new ShipClass(Classes.AKIRA, 10, 300, 2, 150, 500, 3, 70);
	public static ShipClass excelsior = new ShipClass(Classes.EXCELSIOR, 300, 75, 4, 10000, 4000, 25, 1200);
	public static ShipClass intrepid = new ShipClass(Classes.INTREPID, 70, 220, 2, 500, 600, 10, 200);

}
