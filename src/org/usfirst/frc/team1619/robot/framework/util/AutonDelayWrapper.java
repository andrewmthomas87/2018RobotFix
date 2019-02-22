package org.usfirst.frc.team1619.robot.framework.util;

import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class AutonDelayWrapper extends Wrapper<AutonDelay> {
	
	@Override
	protected AutonDelay createState() {
		return new AutonDelay(in, out);
	}

	@Override
	protected boolean isReady() {
		
		return true;
	}

	@Override
	protected boolean isDone() {
		//Get the value of isDelayFinished to decide if the state isDone.
		return this.getState().getIsDelayFinished();
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return true;
	}
}