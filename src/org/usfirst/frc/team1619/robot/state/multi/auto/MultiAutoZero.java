package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.state.multi.MultiZeroWrapper;

public class MultiAutoZero extends MultiSubsystemSequencerState {

	public MultiAutoZero(double elevatorInitSetpoint, double armInitSetpoint, double distance, double elevatorSetpoint,
			double armSetpoint, Set<Integer> subsystemIds) {
		super(subsystemIds);
		this.add(new MultiZeroWrapper());
		this.add(new MultiElevatorAndArmSetWrapper(elevatorInitSetpoint, armInitSetpoint));
		this.add(new MultiElevatorAndArmDistanceIdleWrapper(elevatorInitSetpoint, armInitSetpoint, distance));
		this.add(new MultiElevatorAndArmSetWrapper(elevatorSetpoint, armSetpoint));
		this.add(new MultiElevatorAndArmIdleWrapper(elevatorSetpoint, armSetpoint));
	}

}
