package org.usfirst.frc.team1619.robot.state.multi;

import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiElevatorAndArmIdleWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiElevatorAndArmSetWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiElevatorAndArmDistanceIdleWrapper;

public class MultiElevatorAndArmTwoSet extends MultiSubsystemSequencerState {

	public MultiElevatorAndArmTwoSet(double elevatorInitSetpoint, double armInitSetpoint, double distance,
			double elevatorSetpoint, double armSetpoint, Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.add(new MultiElevatorAndArmSetWrapper(elevatorInitSetpoint, armInitSetpoint));
		this.add(new MultiElevatorAndArmDistanceIdleWrapper(elevatorInitSetpoint, armInitSetpoint, distance));
		this.add(new MultiElevatorAndArmSetWrapper(elevatorSetpoint, armSetpoint));
		this.add(new MultiElevatorAndArmIdleWrapper(elevatorSetpoint, armSetpoint));
	}

}
