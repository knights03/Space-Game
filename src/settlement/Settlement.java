package settlement;

import util.GlobalVars;

public abstract class Settlement {
	
	public enum SettlementType {
		MARKET, WORKSHOP, FORGE, HQ, TAVERN, FUELSTATION,
		ARMORY
	}
	
	public enum SettlementSize {
		SMALL(GlobalVars.SMALL_MARKET),
		MEDIUM(GlobalVars.MEDIUM_MARKET),
		LARGE(GlobalVars.LARGE_MARKET),
		HUGE(GlobalVars.HUGE_MARKET);
		
		private int market;
		
		private SettlementSize(int marketSize) {
			market = marketSize;
		}
		
		public int getMarket() {
			return market;
		}
		
	}

	private String name;
	
	private SettlementType type;
	private SettlementSize size;
}
