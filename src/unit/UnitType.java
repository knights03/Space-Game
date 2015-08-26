package unit;

public enum UnitType {
	BATTLESHIP("Battleship"),
	CRUISER("Cruiser"),
	DESTROYER("Destroyer"),
	FIGHTER("Fighter"),
	FRIGATE("Frigate"),
	TRANSPORT("Transport");
	
	private String name;
	
	private UnitType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
