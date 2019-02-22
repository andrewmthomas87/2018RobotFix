package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class MultiDrive extends ParallelState {

	public MultiDrive(Wrapper driveState, double elevatorSetpoint, double armSetpoint, Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addState(driveState);
		this.addBackgroundState(new MultiElevatorAndArmSetWrapper(elevatorSetpoint, armSetpoint));
	}

}
