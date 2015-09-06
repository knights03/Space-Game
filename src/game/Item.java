package game;

public abstract class Item {
	
	private String name;
	private double weight;
	
	private ItemType itemType;
	
	private boolean equippable;

	public Item(String name, double weight, ItemType itemType) {
		this.name = name;
		this.weight = weight;
		this.itemType = itemType;
	}
	
	public String getName() {
		return name;
	}
	
	public ItemType getItemType() {
		return itemType;
	}

}
