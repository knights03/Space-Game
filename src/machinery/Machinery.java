package machinery;

import settlement.Factory;

public abstract class Machinery {
	
	private String name;
	private int avgCost;
	private MachineryType type;
	private Factory factory;
	
	public Machinery(String name, MachineryType type) {
		this.name = name;
		this.type = type;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	
	public Factory getFactory() {
		return factory;
	}

	
}
