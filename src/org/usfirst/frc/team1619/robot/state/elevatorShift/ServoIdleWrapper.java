package org.usfirst.frc.team1619.robot.state.elevatorShift;

import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class ServoIdleWrapper extends Wrapper<ServoIdle> {

	@Override
	protected ServoIdle createState() {
		return new ServoIdle(in, out);
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
