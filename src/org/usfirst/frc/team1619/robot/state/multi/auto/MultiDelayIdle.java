package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.framework.util.DelayWrapper;

public class MultiDelayIdle extends ParallelState {

	public MultiDelayIdle(long delay, double elevatorSetpoint, double armSetpoint, Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addState(new DelayWrapper(delay));
		this.addBackgroundState(new MultiElevatorAndArmIdleWrapper(elevatorSetpoint, armSetpoint));
	}

}
