package resource;

public enum ResourceType {
	FUEL("Fuel"),
	SHEKEL("Shekel"),
	TORPEDO("Torpedo");
	
	private String name;
	
	private ResourceType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
