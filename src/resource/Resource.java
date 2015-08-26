package resource;

import game.Sellable;

public abstract class Resource implements Sellable{
	private int amount;
	private ResourceType resourceType;
	
	public Resource() {
		this.amount = 0;
	}
	
	public Resource(int amount) {
		this.amount = amount;
	}
	
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	
	public void add(int addAmount) {
		this.amount += addAmount;
	}
	
	@Override
	public String toString() {
		return String.format("%s: %d", resourceType, amount);
	}
}
