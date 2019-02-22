package org.usfirst.frc.team1619.robot.framework.util;

import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class DelayWrapper extends Wrapper<Delay> {

	private long delay;

	public DelayWrapper(long delay) {
		this.delay = delay;
	}

	@Override
	protected Delay createState() {
		return new Delay(in, out, delay);
	}

	@Override
	protected boolean isReady() {

		return true;
	}

	@Override
	protected boolean isDone() {
		// Get the value of isDelayFinished to decide if the state isDone.
		return this.getState().getIsDelayFinished();
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return true;
	}
}