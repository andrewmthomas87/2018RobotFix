package org.usfirst.frc.team1619.robot.state.collector;

import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class CollectorHoldWrapper extends Wrapper<CollectorHold> {

	@Override
	protected CollectorHold createState() {
		return new CollectorHold(in, out);
	}

	@Override
	protected boolean isReady() {
		return RobotState.isCubeAcquired;
	}

	@Override
	protected boolean isDone() {
		return !RobotState.isCubeAcquired;

	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.collector.getId();
	}
}
