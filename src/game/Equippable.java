package game;

public abstract class Equippable implements Item {
	
	private String name;
	private int weight;
	
	public Equippable(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public abstract EquippableType getEquippableType();

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public ItemType getItemType() {
		// TODO Auto-generated method stub
		return ItemType.EQUIPPABLE;
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

}
