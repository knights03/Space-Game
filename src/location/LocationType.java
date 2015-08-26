package location;

public enum LocationType {
	PLANET("Planet"),
	STATION("Station"),
	OUTPOST("Outpost");

	
	private String name;
	
	private LocationType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
