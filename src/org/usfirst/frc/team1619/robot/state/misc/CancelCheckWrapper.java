package org.usfirst.frc.team1619.robot.state.misc;

import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class CancelCheckWrapper extends Wrapper<CancelCheck> {

	@Override
	protected CancelCheck createState() {
		return new CancelCheck(in, out);
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
		return true;
	}

}
