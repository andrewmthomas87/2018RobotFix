package org.usfirst.frc.team1619.robot.state.climbingAids;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class RampHoldWrapper extends Wrapper<RampHold> {

	@Override
	protected RampHold createState() {
		return new RampHold(in, out);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {
		return false;
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.climbingAids.getId();
	}

}