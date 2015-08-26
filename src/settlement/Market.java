package settlement;

import java.util.ArrayList;

import cargo.Cargo;
import game.Sellable;
import resource.Fuel;
import resource.Torpedo;
import util.RandomGenerator;

public class Market extends Settlement {

	private ArrayList<Sellable> sellList;
	
	public Market(SettlementSize size) {
		sellList.add(new Torpedo(size.getMarket()));
		sellList.add(new Fuel(size.getMarket()));
	}
}
