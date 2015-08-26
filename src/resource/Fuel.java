package resource;


public class Fuel extends Resource {
	
	public Fuel(int amount) {
		super(amount);
		setResourceType(ResourceType.FUEL);
	}
	
}
