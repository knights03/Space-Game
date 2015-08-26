package settlement;

import faction.Faction;
import util.GlobalVars;

public class Headquarters extends Settlement {

	private Faction faction;
	
	private int xp;
	
	private int memberCount;
	private double memberRate;
	
	
	/* 
	 * 
	 * Player can buy size upgrades for headquarters or complete
	 * missions for xp towards size upgrades. Member rate inceases
	 * with larger HQs and total member accounts for planetary
	 * control
	 * 
	 */
	
	public Headquarters(Faction faction, SettlementSize size) {
		this.faction = faction;
		
		adjustMemberRate(size);
	}
	
	public void adjustMemberRate(SettlementSize size) {
		switch(size) {
		case SMALL:
			memberRate = GlobalVars.SMALL_HQ_MEMBER_RATE;
			break;
		case MEDIUM:
			memberRate = GlobalVars.MEDIUM_HQ_MEMBER_RATE;
			break;
		case LARGE:
			memberRate = GlobalVars.LARGE_HQ_MEMBER_RATE;
			break;
		case HUGE:
			memberRate = GlobalVars.HUGE_HQ_MEMBER_RATE;
			break;
		}
	}
	
	public void adjustMemberCount() {
		memberCount += memberRate;
	}
}
