package org.usfirst.frc.team1619.robot.state.multi;

import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class MultiDriveTwoSet extends ParallelState {

	public MultiDriveTwoSet(Wrapper driveState, double elevatorInitSetpoint, double armInitSetpoint, double distance,
			double elevatorSetpoint, double armSetpoint, Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addState(driveState);
		this.addBackgroundState(new MultiElevatorAndArmTwoSetWrapper(elevatorInitSetpoint, armInitSetpoint, distance,
				elevatorSetpoint, armSetpoint));
	}

}
