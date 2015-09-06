package ship;

public class ShipClasses {
	
	public enum Classes {
		AKIRA, EXCELSIOR
	}
	
	public static ShipClass akira = new ShipClass(Classes.AKIRA, 10, 300, 2, 150, 500, 3, 70, "file:images/ships/akira.png");

}
