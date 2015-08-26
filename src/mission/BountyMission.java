package mission;

import unit.Unit;

public class BountyMission extends Mission  implements Completable {
	
	private Unit bountyTarget;

	/**
	 * @return the bountyTarget
	 */
	public Unit getBountyTarget() {
		return bountyTarget;
	}

	/**
	 * @param bountyTarget the bountyTarget to set
	 */
	public void setBountyTarget(Unit bountyTarget) {
		this.bountyTarget = bountyTarget;
	}

	@Override
	public boolean checkCompletion() {
		// TODO Auto-generated method stub
		return false;
	}

}
